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
package org.nabucco.testautomation.script.impl.service.search;

import java.util.ArrayList;
import java.util.List;

import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.testautomation.property.facade.service.search.WildcardSupport;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.message.MetadataListMsg;
import org.nabucco.testautomation.script.facade.message.MetadataSearchMsg;
import org.nabucco.testautomation.script.impl.service.SubEngineCodeSupport;
import org.nabucco.testautomation.script.impl.service.cache.SubEngineCodeCache;

/**
 * SearchMetadataServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class SearchMetadataServiceHandlerImpl extends SearchMetadataServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    public MetadataListMsg searchMetadata(MetadataSearchMsg msg) throws SearchException {

        StringBuilder queryString = new StringBuilder();
        queryString.append("FROM Metadata m");

        List<String> filter = new ArrayList<String>();

        Identifier identifier = msg.getIdentifier();
        Name name = msg.getName();
        Description description = msg.getDescription();
        SubEngineCode subEngine = msg.getSubEngine();

        if (identifier != null && identifier.getValue() != null) {
            filter.add("m.id = :id");
        }

        if (name != null && name.getValue() != null) {
            filter.add("m.name LIKE '" + WildcardSupport.parse(name.getValue()) + "'");
        }

        if (msg.getMetadataKey() != null && msg.getMetadataKey().getValue() != null) {
            filter.add("m.identificationKey LIKE '" + WildcardSupport.parse(msg.getMetadataKey().getValue()) + "'");
        }

        if (description != null && description.getValue() != null) {
            filter.add("m.description LIKE '" + WildcardSupport.parse(msg.getDescription().getValue()) + "'");
        }

        if (subEngine != null) {
            filter.add("m.subEngine = :subEngine");
        }

        // find only root-metadata
        filter.add("metadata_children_id IS NULL");

        // append filter criteria
        int filterSize = filter.size();

        if (filterSize > 0) {
            queryString.append(" WHERE ");
            int c = 1;
            for (String str : filter) {
                queryString.append(str);

                if (c++ < filterSize) {
                    queryString.append(" AND ");
                }
            }
        }
        queryString.append(" ORDER BY m.name");

        try {
            NabuccoQuery<Metadata> query = super.getPersistenceManager().createQuery(queryString.toString());

            if (identifier != null && identifier.getValue() != null) {
                query.setParameter("id", identifier.getValue());
            }

            if (subEngine != null) {
                query.setParameter("subEngine", subEngine);
            }

            List<Metadata> resultList = query.getResultList();

            this.getPersistenceManager().clear();

            if (!SubEngineCodeCache.getInstance().isInitialized()) {
                SubEngineCodeSupport.getInstance().initCache(this.getContext());
            }

            for (Metadata metadata : resultList) {
                SubEngineCodeSupport.getInstance().resolveSubEngineCodeShallow(metadata);
            }

            MetadataListMsg rs = new MetadataListMsg();
            rs.getMetadataList().addAll(resultList);
            return rs;
        } catch (ResolveException e) {
            throw new SearchException("Error resolving Metadata", e);
        } catch (PersistenceException e) {
            throw new SearchException("Error while searching for Metadata", e);
        }
    }

}
