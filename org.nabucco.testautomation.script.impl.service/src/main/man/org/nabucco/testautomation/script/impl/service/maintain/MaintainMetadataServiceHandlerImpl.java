/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.testautomation.script.impl.service.maintain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.code.Code;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionAccessor;
import org.nabucco.framework.base.facade.datatype.visitor.DatatypeVisitor;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceExceptionMapper;
import org.nabucco.testautomation.property.facade.datatype.PropertyList;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyUsageType;
import org.nabucco.testautomation.property.facade.datatype.visitor.PropertyModificationVisitor;
import org.nabucco.testautomation.script.facade.datatype.comparator.MetadataSorter;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.impl.service.PropertySupport;
import org.nabucco.testautomation.script.impl.service.SubEngineCodeSupport;
import org.nabucco.testautomation.script.impl.service.cache.SubEngineCodeCache;
import org.nabucco.testautomation.script.impl.service.maintain.support.ScriptMaintainSupport;
import org.nabucco.testautomation.script.impl.service.maintain.visitor.MetadataModificationVisitor;

/**
 * MaintainMetadataServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class MaintainMetadataServiceHandlerImpl extends MaintainMetadataServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String PREFIX = "META-";

    private static final MetadataSorter metadataSorter = new MetadataSorter();

    private ScriptMaintainSupport support;

    private Map<Long, Code> codeMap;

    @Override
    public MetadataMsg maintainMetadata(MetadataMsg msg) throws MaintainException {

        Metadata metadata = msg.getMetadata();
        this.codeMap = new HashMap<Long, Code>();

        try {
            // initialize PersistenceHelper
            this.support = new ScriptMaintainSupport(super.getPersistenceManager());

            if (metadata.getDatatypeState() == DatatypeState.PERSISTENT) {
                DatatypeVisitor visitor = new MetadataModificationVisitor(metadata);
                metadata.accept(visitor);
            }

            switch (metadata.getDatatypeState()) {

            case CONSTRUCTED:
                throw new MaintainException("Metadata is not initialized.");
            case INITIALIZED:
                metadata = this.create(metadata);
                break;
            case MODIFIED:
                metadata = this.update(metadata);
                break;
            case DELETED:
                this.delete(metadata);
                getLogger().info("Metadata '" + metadata.getName() + "' [" + metadata.getId() + "] deleted");
                break;
            case TRANSIENT:
                break;
            case PERSISTENT:
                break;
            default:
                throw new MaintainException("Datatype state '"
                        + metadata.getDatatypeState() + "' is not valid for Metadata.");
            }

            this.getPersistenceManager().flush();
            this.support = null;

        } catch (Exception ex) {
            throw new MaintainException("Error maintaining Metadata", PersistenceExceptionMapper.resolve(ex,
                    Metadata.class.getName(), metadata.getId()));
        }

        if (metadata != null) {
            resolve(metadata);

            // Detach Entity
            try {
                this.getPersistenceManager().clear();

                // Sort
                metadataSorter.sort(metadata);

                if (!SubEngineCodeCache.getInstance().isInitialized()) {
                    SubEngineCodeSupport.getInstance().initCache(this.getContext());
                }
                SubEngineCodeSupport.getInstance().resolveSubEngineCodeDeep(metadata);
            } catch (ResolveException e) {
                super.getLogger().error(e, "Could not resolve SubEngineCodes");
            } catch (PersistenceException e) {
                super.getLogger().error(e, "Could not clear PersistenceManager");
            }
            getLogger()
                    .info("Metadata '" + metadata.getName() + "' [" + metadata.getId() + "] successfully maintained");
        }

        msg.setMetadata(metadata);
        return msg;
    }

    private void resolve(Metadata metadata) {

        List<Metadata> children = metadata.getChildren();
        List<MetadataLabel> labelList = metadata.getLabelList();

        for (MetadataLabel label : labelList) {

            try {
                PropertySupport.getInstance().resolveProperties(label, getContext());
            } catch (Exception e) {
                super.getLogger().error(e, "Could not resolve PropertyList for MetadataLabel '" + label.getId() + "'");
            }

            if (label.getBrandTypeRefId() != null) {
                label.setBrandType(this.codeMap.get(label.getBrandTypeRefId()));
            }

            if (label.getReleaseTypeRefId() != null) {
                label.setReleaseType(this.codeMap.get(label.getReleaseTypeRefId()));
            }

            if (label.getEnvironmentTypeRefId() != null) {
                label.setEnvironmentType(this.codeMap.get(label.getEnvironmentTypeRefId()));
            }
        }

        for (Metadata child : children) {
            resolve(child);
        }
    }

    private Metadata create(Metadata entity) throws MaintainException {

        // Create children
        List<Metadata> children = entity.getChildren();

        for (int i = 0; i < children.size(); i++) {
            Metadata createdChild = create(children.get(i));
            children.set(i, createdChild);
        }

        // Create MetadataLabels
        List<MetadataLabel> labelList = entity.getLabelList();

        for (int i = 0; i < labelList.size(); i++) {
            MetadataLabel label = create(labelList.get(i));
            labelList.set(i, label);
        }

        // Create Metadata
        entity = this.support.maintain(entity);
        entity.setIdentificationKey(PREFIX + entity.getId());
        entity.setDatatypeState(DatatypeState.MODIFIED);
        entity = this.support.maintain(entity);
        return entity;
    }

    private MetadataLabel create(MetadataLabel entity) throws MaintainException {

        PropertyList propertyList = entity.getPropertyList();

        if (propertyList != null) {
            propertyList.setUsageType(PropertyUsageType.METADTA_PARAM);
            propertyList = PropertySupport.getInstance().maintainPropertyList(propertyList, getContext());
            entity.setPropertyList(propertyList);
        }

        if (!this.codeMap.containsKey(entity.getEnvironmentTypeRefId())) {
            this.codeMap.put(entity.getEnvironmentTypeRefId(), entity.getEnvironmentType());
        }

        if (!this.codeMap.containsKey(entity.getReleaseTypeRefId())) {
            this.codeMap.put(entity.getReleaseTypeRefId(), entity.getReleaseType());
        }

        if (!this.codeMap.containsKey(entity.getBrandTypeRefId())) {
            this.codeMap.put(entity.getBrandTypeRefId(), entity.getBrandType());
        }

        // Create MetadataLabel
        entity = this.support.maintain(entity);
        return entity;
    }

    private Metadata update(Metadata entity) throws MaintainException {

        // Create or update children
        List<Metadata> children = entity.getChildren();
        List<Metadata> removed = NabuccoCollectionAccessor.getInstance().getUnassignedList(children);

        for (int i = 0; i < children.size(); i++) {
            Metadata createdChild = update(children.get(i));
            children.set(i, createdChild);
        }

        // Delete removed Metadata
        for (Metadata metadata : removed) {
            if(metadata.getDatatypeState() == DatatypeState.DELETED) {
                delete(metadata);
            }
        }

        List<MetadataLabel> labelList = entity.getLabelList();
        List<MetadataLabel> removedLabels = NabuccoCollectionAccessor.getInstance().getUnassignedList(labelList);

        for (int i = 0; i < labelList.size(); i++) {
            MetadataLabel updatedLabel = update(labelList.get(i));
            labelList.set(i, updatedLabel);
        }

        // Delete removed MetadataLabel
        for (MetadataLabel label : removedLabels) {
            delete(label);
        }

        // Generate MetadataKey
        if (entity.getDatatypeState() == DatatypeState.INITIALIZED) {
            entity = this.support.maintain(entity);
            entity.setIdentificationKey(PREFIX + entity.getId());
            entity.setDatatypeState(DatatypeState.MODIFIED);
        }

        // Update Metadata
        entity = this.support.maintain(entity);
        return entity;
    }

    private MetadataLabel update(MetadataLabel entity) throws MaintainException {

        // Update Properties
        PropertyList propertyList = update(entity.getPropertyList());
        entity.setPropertyList(propertyList);

        if (!this.codeMap.containsKey(entity.getEnvironmentTypeRefId())) {
            this.codeMap.put(entity.getEnvironmentTypeRefId(), entity.getEnvironmentType());
        }

        if (!this.codeMap.containsKey(entity.getReleaseTypeRefId())) {
            this.codeMap.put(entity.getReleaseTypeRefId(), entity.getReleaseType());
        }

        if (!this.codeMap.containsKey(entity.getBrandTypeRefId())) {
            this.codeMap.put(entity.getBrandTypeRefId(), entity.getBrandType());
        }

        // Update MetadataLabel
        entity = this.support.maintain(entity);
        return entity;
    }

    private PropertyList update(PropertyList entity) throws MaintainException {

        if (entity != null) {

            if (entity.getDatatypeState() == DatatypeState.PERSISTENT) {
                PropertyModificationVisitor visitor = new PropertyModificationVisitor(entity);
                try {
                    entity.accept(visitor);
                } catch (VisitorException e) {
                    throw new MaintainException(e);
                }
            }

            if (entity.getDatatypeState() != DatatypeState.PERSISTENT) {
                entity.setUsageType(PropertyUsageType.METADTA_PARAM);
                entity = PropertySupport.getInstance().maintainPropertyList(entity, getContext());
            }
        }
        return entity;
    }

    private void delete(Metadata entity) throws MaintainException {

        if (entity.getDatatypeState() != DatatypeState.DELETED && entity.getDatatypeState() != DatatypeState.INITIALIZED) {
            return;
        }

        // Delete children
        List<Metadata> children = entity.getChildren();

        for (int i = 0; i < children.size(); i++) {
            Metadata child = children.get(i);
            if(child.getDatatypeState() != DatatypeState.INITIALIZED) {
                child.setDatatypeState(DatatypeState.DELETED);
            }
            delete(child);
        }

        // Delete MetadataLabels
        List<MetadataLabel> labelList = entity.getLabelList();

        for (MetadataLabel label : labelList) {
            if(label.getDatatypeState() != DatatypeState.INITIALIZED) {
                label.setDatatypeState(DatatypeState.DELETED);
            }
            delete(label);
        }

        // Delete Metadata
        if (entity.getId() != null) {
            this.support.maintain(entity);
        }
    }

    private void delete(MetadataLabel entity) throws MaintainException {

        if (entity.getDatatypeState() != DatatypeState.DELETED && entity.getDatatypeState() != DatatypeState.INITIALIZED) {
            return;
        }

        PropertyList propertyList = entity.getPropertyList();

        if (propertyList != null) {
            if(propertyList.getDatatypeState() != DatatypeState.INITIALIZED) {
                propertyList.setDatatypeState(DatatypeState.DELETED);
                PropertySupport.getInstance().maintainPropertyList(propertyList, getContext());
            }
            entity.setPropertyList(null);
        }

        // Delete MetadataLabel
        if (entity.getId() != null) {
            this.support.maintain(entity);
        }
    }

}
