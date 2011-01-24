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

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.testautomation.script.facade.component.ScriptComponent;
import org.nabucco.testautomation.script.facade.component.ScriptComponentLocator;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.message.TestScriptListMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptSearchMsg;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainTestScript;
import org.nabucco.testautomation.script.facade.service.search.SearchTestScript;


/**
 * ScriptSupport
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ScriptSupport {

	private static ScriptSupport instance;
	
	private MaintainTestScript maintain;
	
	private SearchTestScript search;

	private ScriptSupport() throws ConnectionException, ServiceException {
		ScriptComponent scriptComponent = ScriptComponentLocator.getInstance().getComponent();
		this.maintain = scriptComponent.getMaintainTestScript();
		this.search = scriptComponent.getSearchTestScript();
	}
	
	public static synchronized ScriptSupport getInstance() throws ServiceException, ConnectionException {
		
		if (instance == null) {
			instance = new ScriptSupport();
		}
		return instance;
	}
	
	public TestScript maintainTestScript(TestScript testScript, ServiceMessageContext ctx) throws PersistenceException {
		try {
			ServiceRequest<TestScriptMsg> rq = new ServiceRequest<TestScriptMsg>(ctx);
			TestScriptMsg msg = new TestScriptMsg();
			msg.setTestScript(testScript);
			rq.setRequestMessage(msg);
			return maintain.maintainTestScript(rq).getResponseMessage().getTestScript();
		} catch (MaintainException e) {
			throw new PersistenceException(e);
		}
	}
	
	public List<TestScript> searchTestScriptByFolder(Long folderId, ServiceMessageContext ctx) throws SearchException {
		ServiceRequest<TestScriptSearchMsg> rq = new ServiceRequest<TestScriptSearchMsg>(ctx);
		TestScriptSearchMsg msg = new TestScriptSearchMsg();
		rq.setRequestMessage(msg);
		msg.setFolderId(new Identifier(folderId));
		TestScriptListMsg rs = this.search.searchTestScript(rq).getResponseMessage();
		return rs.getTestScriptList();
	}
}
