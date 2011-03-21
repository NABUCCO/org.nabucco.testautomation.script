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
import org.nabucco.framework.base.impl.service.search.QuerySupport;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.message.TestScriptListMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptSearchMsg;
import org.nabucco.testautomation.script.impl.service.search.SearchTestScriptServiceHandler;


/**
 * SearchTestScriptServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class SearchTestScriptServiceHandlerImpl extends SearchTestScriptServiceHandler {

	private static final long serialVersionUID = 1L;

	@Override
	public TestScriptListMsg searchTestScript(TestScriptSearchMsg msg)
			throws SearchException {

		StringBuilder queryString = new StringBuilder();
		queryString.append("FROM TestScript s");

		List<String> filter = new ArrayList<String>();
		
		if (msg.getOwner() != null && msg.getOwner().getValue() != null) {
			filter.add("s.owner = :owner");
		}
		
		if (msg.getIdentifier() != null && msg.getIdentifier().getValue() != null) {
			filter.add("s.id = :id");
		} else {
		
			if (msg.getName() != null && msg.getName().getValue() != null) {
				filter.add("s.name LIKE '" + msg.getName().getValue() + "%'");
			}
			
			if (msg.getTestScriptKey() != null && msg.getTestScriptKey().getValue() != null) {
				filter.add("s.testScriptKey LIKE '" + msg.getTestScriptKey().getValue() + "%'");
			}
	
			if (msg.getDescription() != null && msg.getDescription().getValue() != null) {
				filter.add("s.description LIKE :description");
			}
			
			if (msg.getFolderId() != null && msg.getFolderId().getValue() != null) {
				filter.add("folder_id = " + msg.getFolderId().getValue());
			}
			
			if (msg.getHasFolder() != null) {
				if (msg.getHasFolder().getValue()) {
					filter.add("folder_id is not null");
				} else {
					filter.add("folder_id is null");
				}
			}
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
		queryString.append(" ORDER BY s.name");

		Query query = super.getEntityManager().createQuery(
				queryString.toString());

		if (msg.getOwner() != null && msg.getOwner().getValue() != null) {
			query.setParameter("owner", msg.getOwner());
		}
		
		if (msg.getIdentifier() != null && msg.getIdentifier().getValue() != null) {
			query.setParameter("id", msg.getIdentifier().getValue());
		} 
		
		if (msg.getDescription() != null && msg.getDescription().getValue() != null) {
			query.setParameter("description", QuerySupport.searchParameter(msg.getDescription()));
		}
		
		@SuppressWarnings("unchecked")
		List<TestScript> resultList = query.getResultList();
		TestScriptListMsg rs = new TestScriptListMsg();
		
		for (TestScript testScript : resultList) {
			testScript.setDatatypeState(DatatypeState.PERSISTENT);
		}
		
		rs.getTestScriptList().addAll(resultList);
		return rs;
	}

}
