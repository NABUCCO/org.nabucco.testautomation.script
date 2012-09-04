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

import java.util.List;

import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.impl.service.SubEngineCodeSupport;
import org.nabucco.testautomation.script.impl.service.cache.SubEngineCodeCache;

/**
 * ResolveParentsServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ResolveParentsServiceHandlerImpl extends ResolveParentsServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected MetadataMsg resolveParents(MetadataMsg msg) throws ResolveException {

        Metadata metadata = msg.getMetadata();

        if (metadata == null || metadata.getId() == null) {
            throw new ResolveException("No Metadata for resolving parents");
        }

        resolveParents(metadata);

        try {
            // Detach Entity
            this.getPersistenceManager().clear();
        } catch (PersistenceException e) {
            getLogger().error("Could not detach entities", e);
        }

        if (!SubEngineCodeCache.getInstance().isInitialized()) {
            SubEngineCodeSupport.getInstance().initCache(this.getContext());
        }
        SubEngineCodeSupport.getInstance().resolveSubEngineCodeShallow(metadata);

        MetadataMsg rs = new MetadataMsg();
        rs.setMetadata(metadata);
        return rs;
    }

    private void resolveParents(Metadata metadata) throws ResolveException {

        Metadata parent = null;

        while ((parent = findParent(metadata)) != null) {
            metadata.setParent(parent);
            metadata = parent;
        }
    }

    private Metadata findParent(Metadata metadata) throws ResolveException {

        try {
            NabuccoQuery<Metadata> query = super.getPersistenceManager().createQuery(
                    "FROM Metadata m WHERE :child MEMBER OF m.childrenJPA");
            query.setParameter("child", metadata);
            List<Metadata> parentList = query.getResultList();
            
            if (parentList.isEmpty()) {
                return null;
            }
            return parentList.get(0);
        } catch (PersistenceException e) {
            throw new ResolveException("Error resolving parents for Metadata ["
                    + metadata.getId() + "] '" + metadata.getIdentificationKey() + "'");
        }
    }

}
