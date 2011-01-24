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
package org.nabucco.testautomation.script.impl.service.search;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.impl.service.search.QuerySupport;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.message.MetadataListMsg;
import org.nabucco.testautomation.script.facade.message.MetadataSearchMsg;
import org.nabucco.testautomation.script.impl.service.SubEngineCodeSupport;
import org.nabucco.testautomation.script.impl.service.cache.SubEngineCodeCache;
import org.nabucco.testautomation.script.impl.service.search.SearchMetadataServiceHandler;


/**
 * SearchMetadataServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class SearchMetadataServiceHandlerImpl extends SearchMetadataServiceHandler {

	private static final long serialVersionUID = 1L;
	
	@Override
	public MetadataListMsg searchMetadata(MetadataSearchMsg msg)
			throws SearchException {
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("select m from Metadata m");

		List<String> filter = new ArrayList<String>();
		
		Identifier identifier = msg.getIdentifier();
		Name name = msg.getName();
		Description description = msg.getDescription();
		SubEngineCode subEngine = msg.getSubEngine();
        
		if (identifier != null && identifier.getValue() != null) {
			filter.add("m.id = :id");
		} 
		if (name != null && name.getValue() != null) {
			filter.add("m.name LIKE '" + name.getValue() + "%'");
		}
		if (description != null && description.getValue() != null) {
			filter.add("m.description LIKE :description");
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

        Query query = super.getEntityManager().createQuery(
				queryString.toString());
		
        if (identifier != null && identifier.getValue() != null) {
			query.setParameter("id", identifier.getValue());
		} 
		if (description != null && description.getValue() != null) {
			query.setParameter("description", QuerySupport.searchParameter(description));
		}
		if (subEngine != null) {
			query.setParameter("subEngine", subEngine);
		}
        
		@SuppressWarnings("unchecked")
		List<Metadata> resultList = query.getResultList();
		
		this.getEntityManager().clear();
		
		if (!SubEngineCodeCache.getInstance().isInitialized()) {
			SubEngineCodeSupport.getInstance().initCache(this.getEntityManager());
		}
		
		for (Metadata metadata : resultList) {
			metadata.setDatatypeState(DatatypeState.PERSISTENT);
			SubEngineCodeSupport.getInstance().resolveSubEngineCodeShallow(metadata);
		}
		
		MetadataListMsg rs = new MetadataListMsg();
		rs.getMetadataList().addAll(resultList);
		return rs;
	}

}
