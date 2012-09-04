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
package org.nabucco.testautomation.script.impl.service.resolve;

import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.base.impl.service.maintain.PersistenceCleaner;
import org.nabucco.testautomation.script.facade.datatype.comparator.MetadataSorter;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.MetadataSearchMsg;
import org.nabucco.testautomation.script.impl.service.PropertySupport;
import org.nabucco.testautomation.script.impl.service.SubEngineCodeSupport;
import org.nabucco.testautomation.script.impl.service.cache.SubEngineCodeCache;

/**
 * ResolveMetadataServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ResolveMetadataServiceHandlerImpl extends ResolveMetadataServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final MetadataSorter metadataSorter = new MetadataSorter();

    @Override
    protected MetadataMsg resolveMetadata(MetadataSearchMsg msg) throws ResolveException {

        if (msg.getIdentifier() == null || msg.getIdentifier().getValue() == null) {
            throw new ResolveException("Mandatory Identifier is null");
        }

        Metadata metadata = getMetadata(msg.getIdentifier().getValue());

        if (metadata == null) {
            throw new ResolveException("Metadata with id '" + msg.getIdentifier().getValue() + "' not found");
        }

        resolveMetadata(metadata);

        // Detach Entity
        try {
            this.getPersistenceManager().clear();
            metadata.accept(new PersistenceCleaner());
        } catch (Exception e) {
            throw new ResolveException(e);
        }

        // Sort
        metadataSorter.sort(metadata);

        if (!SubEngineCodeCache.getInstance().isInitialized()) {
            SubEngineCodeSupport.getInstance().initCache(this.getContext());
        }

        SubEngineCodeSupport.getInstance().resolveSubEngineCodeDeep(metadata);

        MetadataMsg rs = new MetadataMsg();
        rs.setMetadata(metadata);
        return rs;
    }

    private Metadata getMetadata(Long id) throws ResolveException {
        try {
            String queryString = "FROM Metadata m WHERE m.id = :id";
            NabuccoQuery<Metadata> query = super.getPersistenceManager().createQuery(queryString);
            query.setParameter("id", id);
            Metadata result = (Metadata) query.getSingleResult();
            return result;
        } catch (PersistenceException e) {
            throw new ResolveException(e);
        }
    }

    private void resolveMetadata(Metadata metadata) throws ResolveException {

        // Resolve MetadataLabels
        resolveMetadataLabels(metadata);

        for (Metadata child : metadata.getChildren()) {
            child.setParent(metadata);
            resolveMetadata(child);
        }
    }

    private void resolveMetadataLabels(Metadata metadata) throws ResolveException {

        for (MetadataLabel label : metadata.getLabelList()) {

            try {
                PropertySupport.getInstance().resolveProperties(label, getContext());
            } catch (Exception e) {
                throw new ResolveException("Could not resolve PropertyList for MetadataLabel '" + label.getId() + "'",
                        e);
            }
        }
    }

}
