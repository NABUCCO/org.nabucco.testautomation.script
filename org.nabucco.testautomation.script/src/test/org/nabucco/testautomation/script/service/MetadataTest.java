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

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.test.RuntimeTestSupport;
import org.nabucco.testautomation.script.facade.component.ScriptComponent;
import org.nabucco.testautomation.script.facade.component.ScriptComponentLocator;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.MetadataSearchMsg;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainMetadata;
import org.nabucco.testautomation.script.facade.service.produce.ProduceMetadata;
import org.nabucco.testautomation.script.facade.service.search.SearchMetadata;


public class MetadataTest extends RuntimeTestSupport {

	private MaintainMetadata maintainMetadata;
	
	private SearchMetadata searchMetadata;
	
	private ProduceMetadata produceMetadata;

	@Before
	public void setUp() throws Exception {
		ScriptComponent component = super.getComponent(ScriptComponentLocator
				.getInstance());
		maintainMetadata = component.getMaintainMetadata();
		searchMetadata = component.getSearchMetadata();
		produceMetadata = component.getProduceMetadata();
	}
	
	@Test
	public void testSearchMetadata() throws Exception {
		
		ServiceRequest<MetadataSearchMsg> rq = new ServiceRequest<MetadataSearchMsg>(getServiceContext());
		MetadataSearchMsg msg = new MetadataSearchMsg();
		SubEngineCode code = new SubEngineCode();
		code.setCode("PROCESS");
		code.setId(2L);
		code.setVersion(204L);
		code.setName("Process");
		msg.setSubEngine(code);
		rq.setRequestMessage(msg);
		List<Metadata> result = searchMetadata.searchMetadata(rq).getResponseMessage().getMetadataList();
		
		for (Metadata metadata : result) {
			System.out.println(metadata.getName().getValue());
		}
	}
	
	@Test
	public void testGetMetadata() throws Exception {
		
		ServiceRequest<MetadataSearchMsg> rq = new ServiceRequest<MetadataSearchMsg>(getServiceContext());
		MetadataSearchMsg msg = new MetadataSearchMsg();
		msg.setIdentifier(new Identifier(5L));
		rq.setRequestMessage(msg);
		Metadata metadata = searchMetadata.getMetadata(rq).getResponseMessage().getMetadata();
		System.out.println(metadata.toString());
	}
	
	@Test
	public void testGetParents() throws Exception {
		
		Metadata m = new Metadata();
		m.setId(1L);
		ServiceRequest<MetadataMsg> rq = new ServiceRequest<MetadataMsg>(getServiceContext());
		MetadataMsg msg = new MetadataMsg();
		msg.setMetadata(m);
		rq.setRequestMessage(msg);
		Metadata metadata = searchMetadata.getParents(rq).getResponseMessage().getMetadata();
		System.out.println(metadata.toString());
	}


	@Test
	public void testMetadata() throws Exception {

		String operation = "someOperation";
		
		Metadata root = produceMetadata();
		root.setName("root");
//		root.setEngineType(SubEngineType.WEB);
//		root.setOperation(operation);
		
		Metadata m1 = produceMetadata();
		m1.setName("m1");
//		m1.setEngineType(SubEngineType.WEB);
//		m1.setOperation(operation);
		
		Metadata m2 = produceMetadata();
		m2.setName("m2");
//		m2.setEngineType(SubEngineType.WEB);
//		m2.setOperation(operation);
		
		Metadata m3 = produceMetadata();
		m3.setName("m3");
//		m3.setEngineType(SubEngineType.WEB);
//		m3.setOperation(operation);
		
		root.getChildren().add(m1);
		m1.getChildren().add(m2);
		m2.getChildren().add(m3);
		
		ServiceRequest<MetadataMsg> rq = createMetadataRequest(root);
		ServiceResponse<MetadataMsg> rs = maintainMetadata.maintainMetadata(rq);
		MetadataMsg rsMsg = rs.getResponseMessage();
		root = (Metadata) rsMsg.getMetadata();

		assertNotNull("Metadata is null", root);
		assertNotNull("Id is null", root.getId());
//		assertNotNull("Operation is null", root.getOperation());
//		assertNotNull("SubEngineType is null", root.getEngineType());
//		assertEquals(operation, root.getOperation().getValue());
		
//		ServiceResponse<PropertyListMsg> searchRs = searchProperty.searchProperty(createSearchPropertyRequest(property.getId(), null));
//		List<Property> resultList = searchRs.getResponseMessage().getPropertyList();
//		
//		assertTrue("ResultList.size != 1", resultList.size() == 1);
//		property = (StringProperty) resultList.get(0);
//		assertNotNull("Id is null", property.getId());
//		assertNotNull("Name is null", property.getName());
//		assertNotNull("Value is null", property.getValue());
//		assertEquals(name, property.getName().getValue());
//		assertEquals(value, property.getValue().getValue());
//		
//		searchRs = searchProperty.searchProperty(createSearchPropertyRequest(null, property.getName().getValue()));
//		resultList = searchRs.getResponseMessage().getPropertyList();
//		
//		assertTrue("ResultList.size != 1", resultList.size() == 1);
//		property = (StringProperty) resultList.get(0);
//		assertNotNull("Id is null", property.getId());
//		assertNotNull("Name is null", property.getName());
//		assertNotNull("Value is null", property.getValue());
//		assertEquals(name, property.getName().getValue());
//		assertEquals(value, property.getValue().getValue());
//		
//		property.setDatatypeState(DatatypeState.DELETED);
//		rq.getRequestMessage().setProperty(property);
//		maintainProperty.maintainProperty(rq);
//		
//		searchRs = searchProperty.searchProperty(createSearchPropertyRequest(property.getId(), null));
//		resultList = searchRs.getResponseMessage().getPropertyList();
//		
//		assertTrue("ResultList.size != 0", resultList.size() == 0);
	}
	
	private Metadata produceMetadata() throws Exception {

		ServiceRequest<EmptyServiceMessage> rq = new ServiceRequest<EmptyServiceMessage>(
				getServiceContext());
		EmptyServiceMessage msg = new EmptyServiceMessage();
		rq.setRequestMessage(msg);
		ServiceResponse<MetadataMsg> rs = produceMetadata.produceMetadata(rq);
		return rs.getResponseMessage().getMetadata();
	}

	private ServiceRequest<MetadataMsg> createMetadataRequest(
			Metadata ref) {

		ServiceRequest<MetadataMsg> rq = new ServiceRequest<MetadataMsg>(
				getServiceContext());
		MetadataMsg msg = new MetadataMsg();
		msg.setMetadata(ref);
		rq.setRequestMessage(msg);
		return rq;
	}
	
}
