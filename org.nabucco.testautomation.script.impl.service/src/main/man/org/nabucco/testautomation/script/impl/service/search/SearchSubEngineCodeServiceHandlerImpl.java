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
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineActionCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineOperationCode;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeListMsg;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeSearchMsg;
import org.nabucco.testautomation.script.impl.service.search.SearchSubEngineCodeServiceHandler;


/**
 * SearchSubEngineCodeServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class SearchSubEngineCodeServiceHandlerImpl extends SearchSubEngineCodeServiceHandler {

	private static final long serialVersionUID = 1L;
	
	@Override
	public SubEngineCodeListMsg searchSubEngineCode(SubEngineCodeSearchMsg msg)
			throws SearchException {
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("select c from SubEngineCode c");

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

        Query query = super.getEntityManager().createQuery(
				queryString.toString());
		
        if (msg.getIdentifier() != null && msg.getIdentifier().getValue() != null) {
			query.setParameter("id", msg.getIdentifier().getValue());
		} 
		if (msg.getCode() != null && msg.getCode().getValue() != null) {
			query.setParameter("code", msg.getCode());
		}
        
		@SuppressWarnings("unchecked")
		List<SubEngineCode> resultList = query.getResultList();
		
		for (SubEngineCode subEngineCode : resultList) {
			load(subEngineCode);
		}
		
		SubEngineCodeListMsg rs = new SubEngineCodeListMsg();
		rs.getSubEngineCodeList().addAll(resultList);
		return rs;
	}
	
	protected void load(SubEngineCode subEngineCode) {
		subEngineCode.setDatatypeState(DatatypeState.PERSISTENT);
		
		for (SubEngineOperationCode operation : subEngineCode.getOperationList()) {
			operation.setDatatypeState(DatatypeState.PERSISTENT);
			
			for (SubEngineActionCode action : operation.getActionList()) {
				action.setDatatypeState(DatatypeState.PERSISTENT);
			}
		}
	}

}
