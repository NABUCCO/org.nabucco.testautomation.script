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
package org.nabucco.testautomation.script.impl.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineActionCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineOperationCode;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.impl.service.cache.SubEngineCodeCache;


/**
 * SubEngineCodeSupport
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class SubEngineCodeSupport {

	private static SubEngineCodeSupport instance;
	
	private SubEngineCodeSupport() {}
	
	public static SubEngineCodeSupport getInstance() {
		
		if (instance == null) {
			instance = new SubEngineCodeSupport();
		}
		return instance;
	}
	
	public void resolveSubEngineCodeShallow(Metadata metadata) throws SearchException {
		
		SubEngineCode subEngine = metadata.getSubEngine();
		SubEngineOperationCode operation = metadata.getOperation();
		
		if (subEngine != null) {
			subEngine = SubEngineCodeCache.getInstance().getSubEngineCode(subEngine.getId());
			metadata.setSubEngine(subEngine);
		}
		
		if (operation != null) {
			operation = SubEngineCodeCache.getInstance().getOperationCode(operation.getId());
			List<SubEngineActionCode> actionList = operation.getActionList();
			
			for (SubEngineActionCode subEngineActionCode : actionList) {
				subEngineActionCode.setDatatypeState(DatatypeState.PERSISTENT);
			}
			metadata.setOperation(operation);
		}
	}
	
	public void resolveSubEngineCodeDeep(Metadata metadata) throws SearchException {
		
		resolveSubEngineCodeShallow(metadata);
		
		for (Metadata child : metadata.getChildren()) {
			resolveSubEngineCodeDeep(child);
		}
	}
	
	public void initCache(EntityManager em) {
		
		Query query = em.createQuery("SELECT c FROM SubEngineCode c");
		
		@SuppressWarnings("unchecked")
		List<SubEngineCode> resultList = query.getResultList();
		
		// Init Cache
		SubEngineCodeCache.getInstance().init(resultList);
	}
	
}
