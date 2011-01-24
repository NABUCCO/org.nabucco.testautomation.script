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
package org.nabucco.testautomation.script.service;

import org.junit.Before;
import org.junit.Test;
import org.nabucco.framework.base.test.RuntimeTestSupport;
import org.nabucco.testautomation.script.facade.component.ScriptComponent;
import org.nabucco.testautomation.script.facade.component.ScriptComponentLocator;


public class DictionaryTest extends RuntimeTestSupport {


	@Before
	public void setUp() throws Exception {
		ScriptComponent component = super.getComponent(ScriptComponentLocator
				.getInstance());
	}

	@Test
	public void testAction() throws Exception {

	}
	
	@Test
	public void testLoop() throws Exception {

	}
	
//	private TestScript produceTestScript() throws Exception {
//
//		ServiceRequest<EmptyServiceMessage> rq = new ServiceRequest<EmptyServiceMessage>(
//				getServiceContext());
//		EmptyServiceMessage msg = new EmptyServiceMessage();
//		rq.setRequestMessage(msg);
//		ServiceResponse<TestScriptMsg> rs = produce.produceTestScript(rq);
//		return rs.getResponseMessage().getTestScript();
//	}
//
//	private ServiceRequest<TestScriptMsg> createTestScriptRequest(
//			TestScript script) {
//
//		ServiceRequest<TestScriptMsg> rq = new ServiceRequest<TestScriptMsg>(
//				getServiceContext());
//		TestScriptMsg msg = new TestScriptMsg();
//		msg.setTestScript(script);
//		rq.setRequestMessage(msg);
//		return rq;
//	}
//	
//	private ServiceRequest<TestScriptSearchMsg> createSearchTestScriptRequest(String name, String description) {
//		
//		ServiceRequest<TestScriptSearchMsg> rq = new ServiceRequest<TestScriptSearchMsg>(getServiceContext());
//		TestScriptSearchMsg msg = new TestScriptSearchMsg();
//		
//		if (description != null) {
//			Description desc = new Description();
//			desc.setValue(description);
//			msg.setDescription(desc);
//		}
//		if (name != null) {
//			Name propertyName = new Name();
//			propertyName.setValue(name);
//			msg.setName(propertyName);
//		}
//		rq.setRequestMessage(msg);
//		return rq;
//	}
	
}
