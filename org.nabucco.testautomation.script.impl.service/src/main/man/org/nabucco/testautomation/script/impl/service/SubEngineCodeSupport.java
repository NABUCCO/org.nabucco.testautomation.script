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
package org.nabucco.testautomation.script.impl.service;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.testautomation.script.facade.component.ScriptComponentLocator;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineOperationCode;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeListMsg;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeSearchMsg;
import org.nabucco.testautomation.script.facade.service.search.SearchScript;
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
	
	public void resolveSubEngineCodeShallow(Metadata metadata) throws ResolveException {
		
		SubEngineCode subEngine = metadata.getSubEngine();
		SubEngineOperationCode operation = metadata.getOperation();
		
		if (subEngine != null) {
			subEngine = SubEngineCodeCache.getInstance().getSubEngineCode(subEngine.getId());
			metadata.setSubEngine(subEngine);
		}
		
		if (operation != null) {
			operation = SubEngineCodeCache.getInstance().getOperationCode(operation.getId());
			operation.getActionList().size();
			metadata.setOperation(operation);
		}
	}
	
	public void resolveSubEngineCodeDeep(Metadata metadata) throws ResolveException {
		
		resolveSubEngineCodeShallow(metadata);
		
		for (Metadata child : metadata.getChildren()) {
			resolveSubEngineCodeDeep(child);
		}
	}
	
	public void initCache(ServiceMessageContext ctx) {
		
		try {
			SearchScript search = ScriptComponentLocator.getInstance()
					.getComponent().getSearchScript();
			ServiceRequest<SubEngineCodeSearchMsg> rq = new ServiceRequest<SubEngineCodeSearchMsg>(
					ctx);
			rq.setRequestMessage(new SubEngineCodeSearchMsg());
			SubEngineCodeListMsg rs = search.searchSubEngineCode(rq)
					.getResponseMessage();
			List<SubEngineCode> subEngineCodes = rs.getSubEngineCodeList();

			// Init Cache
			SubEngineCodeCache.getInstance().init(subEngineCodes);
		} catch (Exception ex) {
			NabuccoLoggingFactory.getInstance()
					.getLogger(SubEngineCodeSupport.class)
					.error(ex, "Could not initialize SubEngineCode");
		}
	}
	
}
