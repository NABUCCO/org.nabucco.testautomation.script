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

import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineOperationCode;
import org.nabucco.testautomation.script.facade.datatype.comparator.SubEngineActionSorter;
import org.nabucco.testautomation.script.facade.datatype.comparator.SubEngineOperationSorter;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeListMsg;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeSearchMsg;


/**
 * SearchSubEngineCodeServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class SearchSubEngineCodeServiceHandlerImpl extends SearchSubEngineCodeServiceHandler {

	private static final long serialVersionUID = 1L;

	private SubEngineOperationSorter operationSorter = new SubEngineOperationSorter();
	
	private SubEngineActionSorter actionSorter = new SubEngineActionSorter();

	@Override
	public SubEngineCodeListMsg searchSubEngineCode(SubEngineCodeSearchMsg msg)
			throws SearchException {
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("FROM SubEngineCode c");

		List<String> filter = new ArrayList<String>();
        
		if (msg.getIdentifier() != null && msg.getIdentifier().getValue() != null) {
			filter.add("c.id = :id");
		} 
		if (msg.getName() != null && msg.getName().getValue() != null) {
			filter.add("c.name LIKE '" + msg.getName().getValue() + "%'");
		}
		if (msg.getCode() != null && msg.getCode().getValue() != null) {
			filter.add("c.code = :code");
		}
        
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
        queryString.append(" ORDER BY c.name");

        try {
            NabuccoQuery<SubEngineCode> query = super.getPersistenceManager().createQuery(
            		queryString.toString());
            
            if (msg.getIdentifier() != null && msg.getIdentifier().getValue() != null) {
            	query.setParameter("id", msg.getIdentifier().getValue());
            } 
            if (msg.getCode() != null && msg.getCode().getValue() != null) {
            	query.setParameter("code", msg.getCode());
            }
            
            List<SubEngineCode> resultList = query.getResultList();

            // Load deep
            for (SubEngineCode subEngineCode : resultList) {
            	load(subEngineCode);
            }
            
            this.getPersistenceManager().clear();
            
            // Sort
            for (SubEngineCode subEngineCode : resultList) {
            	operationSorter.sort(subEngineCode.getOperationList());
            	
            	for (SubEngineOperationCode operation : subEngineCode.getOperationList()) {
            		actionSorter.sort(operation.getActionList());
            	}
            }
            
            SubEngineCodeListMsg rs = new SubEngineCodeListMsg();
            rs.getSubEngineCodeList().addAll(resultList);
            return rs;
        } catch (PersistenceException e) {
            throw new SearchException("Error while searching for SubEngineCode", e);
        }
	}
	
    protected void load(SubEngineCode subEngineCode) {

        for (SubEngineOperationCode operation : subEngineCode.getOperationList()) {
            operation.getActionList().size();
		}
	}

}
