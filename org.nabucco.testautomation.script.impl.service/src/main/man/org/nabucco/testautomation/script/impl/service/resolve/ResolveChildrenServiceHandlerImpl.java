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
import org.nabucco.framework.base.impl.service.maintain.PersistenceCleaner;
import org.nabucco.testautomation.script.facade.datatype.comparator.MetadataSorter;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.impl.service.SubEngineCodeSupport;
import org.nabucco.testautomation.script.impl.service.cache.SubEngineCodeCache;

/**
 * ResolveChildrenServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ResolveChildrenServiceHandlerImpl extends ResolveChildrenServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final MetadataSorter metadataSorter = new MetadataSorter();

    @Override
    protected MetadataMsg resolveChildren(MetadataMsg msg) throws ResolveException {

        Metadata metadata = msg.getMetadata();

        if (metadata == null || metadata.getId() == null) {
            throw new ResolveException("Invalid Metadata in getChildren()");
        }

        Metadata parent = metadata.getParent();
        metadata = resolveChildren(metadata);
        metadata.setParent(parent);

        try {
            this.getPersistenceManager().clear();
            metadata.accept(new PersistenceCleaner());
        } catch (Exception e) {
            throw new ResolveException(e);
        }

        metadataSorter.sort(metadata.getChildren());

        if (!SubEngineCodeCache.getInstance().isInitialized()) {
            SubEngineCodeSupport.getInstance().initCache(this.getContext());
        }

        SubEngineCodeSupport.getInstance().resolveSubEngineCodeShallow(metadata);

        for (Metadata child : metadata.getChildren()) {
            SubEngineCodeSupport.getInstance().resolveSubEngineCodeShallow(child);
        }

        MetadataMsg rs = new MetadataMsg();
        rs.setMetadata(metadata);
        return rs;
    }

    private Metadata resolveChildren(Metadata metadata) throws ResolveException {

        try {
            metadata = super.getPersistenceManager().find(Metadata.class, metadata.getId());
            List<Metadata> children = metadata.getChildren();

            for (Metadata child : children) {
                child.setParent(metadata);
            }
            return metadata;
        } catch (PersistenceException e) {
            throw new ResolveException(e);
        }
    }

}
