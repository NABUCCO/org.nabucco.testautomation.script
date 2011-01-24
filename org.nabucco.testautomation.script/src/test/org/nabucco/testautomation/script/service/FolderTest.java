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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.test.RuntimeTestSupport;
import org.nabucco.testautomation.script.facade.component.ScriptComponent;
import org.nabucco.testautomation.script.facade.component.ScriptComponentLocator;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.message.FolderMsg;


public class FolderTest extends RuntimeTestSupport {

	private ScriptComponent component;
	
	private static Folder root;
	
	@Before
	public void setUp() throws Exception {
		component = super.getComponent(ScriptComponentLocator
				.getInstance());
	}
	
	@Test
	public void testProduceFolder() {
		
		ServiceRequest<EmptyServiceMessage> rq = new ServiceRequest<EmptyServiceMessage>(getServiceContext());
		rq.setRequestMessage(new EmptyServiceMessage());
		try {
			root = component.getProduceFolder().produceFolder(rq).getResponseMessage().getFolder();
			Folder f1 = component.getProduceFolder().produceFolder(rq).getResponseMessage().getFolder();
			Folder f2 = component.getProduceFolder().produceFolder(rq).getResponseMessage().getFolder();
			Folder f3 = component.getProduceFolder().produceFolder(rq).getResponseMessage().getFolder();
			Folder f4 = component.getProduceFolder().produceFolder(rq).getResponseMessage().getFolder();
			Folder f5 = component.getProduceFolder().produceFolder(rq).getResponseMessage().getFolder();
			Folder f6 = component.getProduceFolder().produceFolder(rq).getResponseMessage().getFolder();
			
			root.setName("Dictionary");
			root.setRoot(Boolean.TRUE);
			f1.setName("System 1");
			f2.setName("System 2");
			f3.setName("System 3");
			f4.setName("SubSystem 1");
			f5.setName("SubSystem 2");
			f6.setName("SubSystem 3");
			root.getSubFolderList().add(f1);
			root.getSubFolderList().add(f2);
			root.getSubFolderList().add(f3);
			f1.getSubFolderList().add(f4);
			f2.getSubFolderList().add(f5);
			f3.getSubFolderList().add(f6);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testMaintainFolder() {
		
		ServiceRequest<FolderMsg> rq = new ServiceRequest<FolderMsg>(getServiceContext());
		FolderMsg msg = new FolderMsg();
		rq.setRequestMessage(msg);
		msg.setFolder(root);
		try {
			Folder folder = component.getMaintainFolder().maintainFolder(rq).getResponseMessage().getFolder();
			assertNotNull(folder);
			assertNotNull(folder.getId());
			assertNotNull(folder.getDatatypeState() == DatatypeState.PERSISTENT);
			assertTrue(folder.getSubFolderList().size() == 3);
			assertTrue(folder.getSubFolderList().get(0).getSubFolderList().size() == 1);
			assertTrue(folder.getSubFolderList().get(0).getSubFolderList().size() == 1);
			assertTrue(folder.getSubFolderList().get(0).getSubFolderList().size() == 1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
}
