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
package org.nabucco.testautomation.script.impl.service.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineActionCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineOperationCode;


/**
 * SubEngineCodeCache
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class SubEngineCodeCache {

	private static SubEngineCodeCache instance;
	
	private final Map<Long, SubEngineCode> codeCache = new HashMap<Long, SubEngineCode>();

	private final Map<Long, SubEngineOperationCode> operationCache = new HashMap<Long, SubEngineOperationCode>();
	
	private final Map<Long, SubEngineActionCode> actionCache = new HashMap<Long, SubEngineActionCode>();
	
	private boolean initialized = false;
	
	private SubEngineCodeCache() {}
	
	public static synchronized SubEngineCodeCache getInstance() {
		
		if (instance == null) {
			instance = new SubEngineCodeCache();
		}
		return instance;
	}
	
	public synchronized SubEngineCode getSubEngineCode(Long id) {
		return this.codeCache.get(id);
	}
	
	public synchronized SubEngineOperationCode getOperationCode(Long id) {
		return this.operationCache.get(id);
	}
	
	public synchronized SubEngineActionCode getActionCode(Long id) {
		return this.actionCache.get(id);
	}
	
	public boolean isInitialized() {
		return this.initialized;
	}
	
	public synchronized void clear() {
		this.codeCache.clear();
		this.operationCache.clear();
		this.actionCache.clear();
		this.initialized = false;
	}
	
	public synchronized void init(List<SubEngineCode> codeList) {
		
		for (SubEngineCode subEngineCode : codeList) {
			subEngineCode.setDatatypeState(DatatypeState.PERSISTENT);
			this.codeCache.put(subEngineCode.getId(), subEngineCode);
			
			for (SubEngineOperationCode operation : subEngineCode.getOperationList()) {
				operation.setDatatypeState(DatatypeState.PERSISTENT);
				this.operationCache.put(operation.getId(), operation);
				
				for (SubEngineActionCode action : operation.getActionList()) {
					action.setDatatypeState(DatatypeState.PERSISTENT);
					this.actionCache.put(action.getId(), action);
				}
			}
		}
		this.initialized = true;
	}
	
}
