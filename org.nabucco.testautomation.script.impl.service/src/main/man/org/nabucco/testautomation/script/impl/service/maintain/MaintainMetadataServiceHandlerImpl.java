/*
* Copyright 2010 PRODYNA AG
*
* Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.opensource.org/licenses/eclipse-1.0.php or
* http://www.nabucco-source.org/nabucco-license.html
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.nabucco.testautomation.script.impl.service.maintain;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionAccessor;
import org.nabucco.framework.base.facade.datatype.visitor.DatatypeVisitor;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceExceptionMapper;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceHelper;
import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyUsageType;
import org.nabucco.testautomation.facade.datatype.visitor.PropertyModificationVisitor;
import org.nabucco.testautomation.script.facade.datatype.comparator.MetadataSorter;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.impl.service.DynamicCodeSupport;
import org.nabucco.testautomation.script.impl.service.PropertySupport;
import org.nabucco.testautomation.script.impl.service.SubEngineCodeSupport;
import org.nabucco.testautomation.script.impl.service.cache.SubEngineCodeCache;
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
	
	private PersistenceHelper persistenceHelper;
	
	@Override
	public MetadataMsg maintainMetadata(MetadataMsg msg) throws MaintainException {

		Metadata metadata = msg.getMetadata();

		try {
			// initialize PersistenceHelper
			this.persistenceHelper = new PersistenceHelper(super.getEntityManager());
			
			if (metadata.getDatatypeState() == DatatypeState.PERSISTENT) {
	            DatatypeVisitor visitor = new MetadataModificationVisitor(
	            		metadata);
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
				metadata = null;
				break;
			case TRANSIENT:
				break;
			case PERSISTENT:
				break;
			default:
				throw new MaintainException("Datatype state '"
						+ metadata.getDatatypeState()
						+ "' is not valid for Metadata.");
			}
		} catch (Exception ex) {
			throw new MaintainException("Error maintaining Metadata",
					PersistenceExceptionMapper.resolve(ex,
							Metadata.class.getName(), metadata.getId()));
		}
		
		this.persistenceHelper.flush();
        this.persistenceHelper = null;

		if (metadata != null) {
			load(metadata);
			
			// Detach Entity
			this.getEntityManager().clear();
			
			// Sort
			metadataSorter.sort(metadata);
			
			try {
				if (!SubEngineCodeCache.getInstance().isInitialized()) {
					SubEngineCodeSupport.getInstance().initCache(this.getContext());
				}
				SubEngineCodeSupport.getInstance().resolveSubEngineCodeDeep(metadata);
			} catch (SearchException e) {
				super.getLogger().error(e, "Could not resolve SubEngineCodes");
			}
		}
		
		msg.setMetadata(metadata);
		return msg;
	}
	
	private void load(Metadata metadata) {
		
		metadata.setDatatypeState(DatatypeState.PERSISTENT);
		List<Metadata> children = metadata.getChildren();
		List<MetadataLabel> labelList = metadata.getLabelList();

		for (MetadataLabel label : labelList) {
			label.setDatatypeState(DatatypeState.PERSISTENT);
			
			try {
				PropertySupport.getInstance().resolveProperties(label,
						getContext());
			} catch (Exception e) {
				super.getLogger().error(
						e,
						"Could not resolve PropertyList for MetadataLabel '"
								+ label.getId() + "'");
			}
			try {
				DynamicCodeSupport.getInstance().resolveDynamicCodes(label,
						getContext());
			} catch (Exception e) {
				super.getLogger().error(
						e,
						"Could not resolve DynamicCodes for MetadataLabel '"
								+ label.getId() + "'");
			}
		}
		
		for (Metadata child : children) {
			load(child);
		}
	}

	private Metadata create(Metadata entity) throws PersistenceException {
		
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
		entity = this.persistenceHelper.persist(entity);
		entity.setIdentificationKey(PREFIX + entity.getId());
		entity.setDatatypeState(DatatypeState.MODIFIED);
		entity = this.persistenceHelper.persist(entity);
		return entity;
	}
	
	private MetadataLabel create(MetadataLabel entity) throws PersistenceException {
		
		PropertyList propertyList = entity.getPropertyList();
		
		if (propertyList != null) {
			try {
				propertyList.setUsageType(PropertyUsageType.METADTA_PARAM);
				propertyList = PropertySupport.getInstance().maintainPropertyList(propertyList, getContext());
				entity.setPropertyList(propertyList);
			} catch (Exception e) {
				throw new PersistenceException("Could not maintain PropertyList");
			}
		}
		
		// Create MetadataLabel
		entity = this.persistenceHelper.persist(entity);
		return entity;
	}

	private Metadata update(Metadata entity) throws PersistenceException {

		// Create or update children
		List<Metadata> children = entity.getChildren();
		List<Metadata> removed = NabuccoCollectionAccessor.getInstance().getUnassignedList(children);
		
		for (int i = 0; i < children.size(); i++) {
			Metadata createdChild = update(children.get(i));
			children.set(i, createdChild);
		}
		
		// Delete removed Metadata
		for (Metadata metadata : removed) {
			metadata.setDatatypeState(DatatypeState.DELETED);
			delete(metadata);
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
			entity = this.persistenceHelper.persist(entity);
			entity.setIdentificationKey(PREFIX + entity.getId());
			entity.setDatatypeState(DatatypeState.MODIFIED);
		}
		
		// Update Metadata
		entity = this.persistenceHelper.persist(entity);
		return entity;
	}
	
	private MetadataLabel update(MetadataLabel entity) throws PersistenceException {
		
		// Update Properties
		PropertyList propertyList = update(entity.getPropertyList());	
		entity.setPropertyList(propertyList);
		
		// Update MetadataLabel
		entity = this.persistenceHelper.persist(entity);
		return entity;
	}
	
	private PropertyList update(PropertyList entity) throws PersistenceException {
		
		if (entity != null) {
			
			try {
				if (entity.getDatatypeState() == DatatypeState.PERSISTENT) {
					PropertyModificationVisitor visitor = new PropertyModificationVisitor(entity);
					entity.accept(visitor);
				}
			
				if (entity.getDatatypeState() != DatatypeState.PERSISTENT) {
					entity.setUsageType(PropertyUsageType.METADTA_PARAM);
					entity = PropertySupport.getInstance().maintainPropertyList(entity, getContext());
				}
			} catch (Exception e) {
				throw new PersistenceException("Could not maintain PropertyList");
			}
		}
		return entity;
	}

	private void delete(Metadata entity) throws PersistenceException {

		if (entity.getId() == null) {
			throw new IllegalArgumentException("Metadata is not persistent.");
		}
		
		if (entity.getDatatypeState() != DatatypeState.DELETED) {
			return;
		}
		
		// Delete children
		List<Metadata> children = entity.getChildren();
		
		for (int i = 0; i < children.size(); i++) {
			Metadata child = children.get(i);
			child.setDatatypeState(DatatypeState.DELETED);
			delete(child);
		}
		
		// Delete MetadataLabels
		List<MetadataLabel> labelList = entity.getLabelList();
		
		for (MetadataLabel label : labelList) {
			label.setDatatypeState(DatatypeState.DELETED);
			delete(label);
		}
		
		// Delete Metadata
		this.persistenceHelper.persist(entity);
	}
	
	private void delete(MetadataLabel entity) throws PersistenceException {
		
		if (entity.getDatatypeState() != DatatypeState.DELETED) {
			return;
		}
		
		PropertyList propertyList = entity.getPropertyList();
		
		if (propertyList != null) {
			propertyList.setDatatypeState(DatatypeState.DELETED);
			try {
				PropertySupport.getInstance().maintainPropertyList(propertyList, getContext());
			} catch (Exception e) {
				throw new PersistenceException("Could not delete PropertyList");
			}
		}
		
		// Delete MetadataLabel
		this.persistenceHelper.persist(entity);
	}

}
