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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.test.RuntimeTestSupport;
import org.nabucco.testautomation.script.facade.component.ScriptComponent;
import org.nabucco.testautomation.script.facade.component.ScriptComponentLocator;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Condition;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Logger;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TextMessage;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;
import org.nabucco.testautomation.script.facade.datatype.dictionary.type.ConditionType;
import org.nabucco.testautomation.script.facade.datatype.dictionary.type.LoggerLevelType;
import org.nabucco.testautomation.script.facade.datatype.dictionary.type.OperatorType;
import org.nabucco.testautomation.script.facade.message.ProduceTestScriptElementMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptListMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptSearchMsg;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainTestScript;
import org.nabucco.testautomation.script.facade.service.produce.ProduceTestScriptElement;
import org.nabucco.testautomation.script.facade.service.search.SearchTestScript;

import org.nabucco.testautomation.facade.component.TestautomationComponent;
import org.nabucco.testautomation.facade.component.TestautomationComponentLocator;
import org.nabucco.testautomation.facade.datatype.property.BooleanProperty;
import org.nabucco.testautomation.facade.datatype.property.IntegerProperty;
import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.facade.datatype.property.StringProperty;
import org.nabucco.testautomation.facade.datatype.property.base.Property;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyComposite;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyContainer;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyType;
import org.nabucco.testautomation.facade.message.ProducePropertyMsg;
import org.nabucco.testautomation.facade.message.PropertyMsg;
import org.nabucco.testautomation.facade.service.maintain.MaintainProperty;
import org.nabucco.testautomation.facade.service.produce.ProduceProperty;

public class TestScriptTest extends RuntimeTestSupport {

	private MaintainTestScript maintain;
	
	private SearchTestScript search;
	
	private ProduceTestScriptElement produce;
	
	private MaintainProperty maintainProperty;
	
	private ProduceProperty produceProperty;

	@Before
	public void setUp() throws Exception {
		ScriptComponent scriptComponent = super.getComponent(ScriptComponentLocator
				.getInstance());
		maintain = scriptComponent.getMaintainTestScript();
		search = scriptComponent.getSearchTestScript();
		produce = scriptComponent.getProduceTestScriptElement();
		TestautomationComponent propertyComponent = super.getComponent(TestautomationComponentLocator.getInstance());
		maintainProperty = propertyComponent.getMaintainProperty();
		produceProperty = propertyComponent.getProduceProperty();
	}
	
	private TestScriptElementContainer wrap(TestScriptElement element, int orderIndex) {
		TestScriptElementContainer container = new TestScriptElementContainer();
		container.setDatatypeState(DatatypeState.INITIALIZED);
		container.setOrderIndex(orderIndex);
		container.setElement(element);
		return container;
	}

	@Test
	public void testCreateTestScript() throws Exception {

		String name = "testScriptName";
		String description = "This is a runtime-test";
		String text1 = "First text message";
		String text2 = "Second text message";
		
		TestScript testScript = (TestScript) produceTestScriptElement(TestScriptElementType.SCRIPT);
		Condition condition = (Condition) produceTestScriptElement(TestScriptElementType.CONDITION);
		condition.setConditionType(ConditionType.EQUALS);
		condition.setOperator(OperatorType.AND);
		testScript.setPropertyList(createPropertyList());
		
		Logger logger = (Logger) produceTestScriptElement(TestScriptElementType.LOGGER);
		logger.setLevel(LoggerLevelType.INFO);
		TextMessage msg1 = (TextMessage) produceTestScriptElement(TestScriptElementType.TEXT_MESSAGE);
		TextMessage msg2 = (TextMessage) produceTestScriptElement(TestScriptElementType.TEXT_MESSAGE);
		msg1.setText(text1);
		msg2.setText(text2);
		logger.getTestScriptElementList().add(wrap(msg1, 0));
		logger.getTestScriptElementList().add(wrap(msg2, 1));
		condition.getTestScriptElementList().add(wrap(logger, 0));
		testScript.getTestScriptElementList().add(wrap(condition, 0));
		testScript.setName(name);
		testScript.setDescription(description);
		
		ServiceRequest<TestScriptMsg> rq = createTestScriptRequest(testScript);
		ServiceResponse<TestScriptMsg> rs = maintain.maintainTestScript(rq);
		
		TestScriptMsg rsMsg = rs.getResponseMessage();
		testScript = rsMsg.getTestScript();

		assertNotNull("TestScript is null", testScript);
		assertNotNull("Id is null", testScript.getId());
		assertNotNull("Name is null", testScript.getName());
		assertNotNull("Description is null", testScript.getDescription());
		assertEquals(name, testScript.getName().getValue());
		assertEquals(description, testScript.getDescription().getValue());
		
		assertTrue(testScript.getPropertyList() != null);
		assertTrue(testScript.getTestScriptElementList().size() == 1);
		assertTrue(testScript.getTestScriptElementList().get(0).getElement().getType() == TestScriptElementType.CONDITION);
		Condition c = (Condition) testScript.getTestScriptElementList().get(0).getElement();
		assertTrue(c.getTestScriptElementList().size() == 1);
		assertTrue(c.getTestScriptElementList().get(0).getElement().getType() == TestScriptElementType.LOGGER);
		Logger l = (Logger) c.getTestScriptElementList().get(0).getElement();
		assertTrue(l.getTestScriptElementList().size() == 2);
		assertTrue(l.getTestScriptElementList().get(0).getElement().getType() == TestScriptElementType.TEXT_MESSAGE);
		assertTrue(l.getTestScriptElementList().get(1).getElement().getType() == TestScriptElementType.TEXT_MESSAGE);
		
		// Search persisted TestScript
		ServiceRequest<TestScriptSearchMsg> searchRq = createGetTestScriptRequest(testScript.getId());
		ServiceResponse<TestScriptMsg> searchRs = search.getTestScript(searchRq);
		
		TestScriptMsg searchMsg = searchRs.getResponseMessage();
		testScript = searchMsg.getTestScript();
		assertNotNull("Name is null", testScript.getName());
		assertNotNull("Description is null", testScript.getDescription());
		assertEquals(name, testScript.getName().getValue());
		assertEquals(description, testScript.getDescription().getValue());
		
		assertTrue(testScript.getPropertyList() != null);
		assertTrue(testScript.getTestScriptElementList().size() == 1);
		assertTrue(testScript.getTestScriptElementList().get(0).getElement().getType() == TestScriptElementType.CONDITION);
		c = (Condition) testScript.getTestScriptElementList().get(0).getElement();
		assertTrue(c.getTestScriptElementList().size() == 1);
		assertTrue(c.getTestScriptElementList().get(0).getElement().getType() == TestScriptElementType.LOGGER);
		l = (Logger) c.getTestScriptElementList().get(0).getElement();
		assertTrue(l.getTestScriptElementList().size() == 2);
		assertTrue(l.getTestScriptElementList().get(0).getElement().getType() == TestScriptElementType.TEXT_MESSAGE);
		assertTrue(l.getTestScriptElementList().get(1).getElement().getType() == TestScriptElementType.TEXT_MESSAGE);
	}
	
	private PropertyList createPropertyList() throws Exception {
		
		String name = "ActionProperties";
		PropertyList propertyList = (PropertyList) produceProperty(PropertyType.LIST);
		propertyList.setName(name);
		propertyList.setDescription("My PropertyList");

		StringProperty prop1 = (StringProperty) produceProperty(PropertyType.STRING);
		prop1.setName("ListProperty 1");
		prop1.setValue("PropertyList-Test 1");
		IntegerProperty prop2 = (IntegerProperty) produceProperty(PropertyType.INTEGER);
		prop2.setName("ListProperty 2");
		prop2.setValue(4711);
		BooleanProperty prop3 = (BooleanProperty) produceProperty(PropertyType.BOOLEAN);
		prop3.setName("ListProperty 3");
		prop3.setValue(Boolean.TRUE);
		
		PropertyList subList = (PropertyList) produceProperty(PropertyType.LIST);
		subList.setName("SubList Properties");
		subList.setDescription("A SubList with properties");
		StringProperty subProp1 = (StringProperty) produceProperty(PropertyType.STRING);
		subProp1.setName("SubListProperty 1");
		subProp1.setValue("SubPropertyList-Test 1");
		StringProperty subProp2 = (StringProperty) produceProperty(PropertyType.STRING);
		subProp2.setName("SubListProperty 2");
		subProp2.setValue("SubPropertyList-Test 2");
		add(subProp1,subList);
		add(subProp2,subList);
		
		add(prop1,propertyList);
		add(prop2,propertyList);
		add(prop3,propertyList);
		add(subList,propertyList);

		ServiceRequest<PropertyMsg> rq = createPropertyRequest(propertyList);
		ServiceResponse<PropertyMsg> rs = maintainProperty.maintainProperty(rq);
		PropertyMsg rsMsg = rs.getResponseMessage();
		propertyList = (PropertyList) rsMsg.getProperty();
		return propertyList;
	}
	
	private void add(Property prop, PropertyComposite to) {
		PropertyContainer container = new PropertyContainer();
		container.setDatatypeState(DatatypeState.INITIALIZED);
		container.setOrderIndex(to.getPropertyList().size());
		container.setProperty(prop);
		to.getPropertyList().add(container);
	}
	
	private ServiceRequest<PropertyMsg> createPropertyRequest(Property property) {
		
		ServiceRequest<PropertyMsg> rq = new ServiceRequest<PropertyMsg>(
				getServiceContext());
		PropertyMsg msg = new PropertyMsg();
		msg.setProperty(property);
		rq.setRequestMessage(msg);
		return rq;
	}
	
	private Property produceProperty(PropertyType type) throws Exception {

		ServiceRequest<ProducePropertyMsg> rq = new ServiceRequest<ProducePropertyMsg>(
				getServiceContext());
		ProducePropertyMsg msg = new ProducePropertyMsg();
		msg.setPropertyType(type);
		rq.setRequestMessage(msg);
		ServiceResponse<PropertyMsg> rs = produceProperty.produceProperty(rq);
		return rs.getResponseMessage().getProperty();
	}

	@Test
	public void testSearchTestScript() throws Exception {

		String name = "testScriptName";
		String description = "This is a runtime-test";
		
		// Search by name
		ServiceRequest<TestScriptSearchMsg> rq = createSearchTestScriptRequest(name, "%runtime%");
		ServiceResponse<TestScriptListMsg> rs = search.searchTestScript(rq);
		
		TestScriptListMsg rsMsg = rs.getResponseMessage();
		List<TestScript> testScriptList = rsMsg.getTestScriptList();

		assertNotNull("TestScriptList is null", testScriptList);
		assertTrue(testScriptList.size() > 0);
		TestScript testScript = testScriptList.get(0);
		assertNotNull("Name is null", testScript.getName());
		assertNotNull("Description is null", testScript.getDescription());
		assertEquals(name, testScript.getName().getValue());
		assertEquals(description, testScript.getDescription().getValue());
	}
	
	@Test
	public void testGetTestScript() throws Exception {

		Long id = 8L;
		ServiceRequest<TestScriptSearchMsg> rq = createGetTestScriptRequest(id);
		ServiceResponse<TestScriptMsg> rs = search.getTestScript(rq);
		
		TestScriptMsg rsMsg = rs.getResponseMessage();
		TestScript testScript = rsMsg.getTestScript();

		assertNotNull("TestScriptList is null", testScript);
		assertNotNull("Name is null", testScript.getName());
		assertNotNull("Description is null", testScript.getDescription());
	}
	
	@Test
	public void testTestUpdateTestScript() throws Exception {
		
		String name = "testScriptName";
		String description = "My updated description";
		String updatedValue = "My updated value";
		
		// Search by name
		ServiceRequest<TestScriptSearchMsg> rq = createSearchTestScriptRequest(name, null);
		ServiceResponse<TestScriptListMsg> rs = search.searchTestScript(rq);
		
		TestScriptListMsg rsMsg = rs.getResponseMessage();
		List<TestScript> testScriptList = rsMsg.getTestScriptList();

		assertNotNull("TestScriptList is null", testScriptList);
		assertTrue(testScriptList.size() > 0);
		TestScript testScript = testScriptList.get(0);
		assertNotNull("Name is null", testScript.getName());
		assertNotNull("Description is null", testScript.getDescription());
		assertEquals(name, testScript.getName().getValue());
		
		assertTrue("testScript.getElementList() != 1", testScript.getTestScriptElementList().size() == 1);
		assertTrue("TestScriptElement is not a Condition", testScript.getTestScriptElementList().get(0).getElement() instanceof Condition);
		Condition condition = (Condition) testScript.getTestScriptElementList().get(0).getElement();
		condition.setValue(updatedValue);
		condition.setDatatypeState(DatatypeState.MODIFIED);
		testScript.setDescription(description);
		testScript.setDatatypeState(DatatypeState.MODIFIED);
		
		ServiceRequest<TestScriptMsg> updateRq = createTestScriptRequest(testScript);
		ServiceResponse<TestScriptMsg> updateRs = maintain.maintainTestScript(updateRq);
		
		testScript = updateRs.getResponseMessage().getTestScript();
		assertNotNull("TestScript is null", testScript);
		assertNotNull("Name is null", testScript.getName());
		assertNotNull("Description is null", testScript.getDescription());
		assertEquals(name, testScript.getName().getValue());
		assertEquals(description, testScript.getDescription().getValue());
		
		assertTrue("testScript.getElementList() != 1", testScript.getTestScriptElementList().size() == 1);
		assertTrue("TestScriptElement is not a Condition", testScript.getTestScriptElementList().get(0).getElement() instanceof Condition);
		condition = (Condition) testScript.getTestScriptElementList().get(0).getElement();
		assertNotNull("Condition is null", condition);
		assertEquals(updatedValue, condition.getValue().getValue());
	}
	
	@Test
	public void testDeleteTestScript() throws Exception {
		
		String name = "MyTestScript";
		
		// Search by name
		ServiceRequest<TestScriptSearchMsg> rq = createSearchTestScriptRequest(name, null);
		ServiceResponse<TestScriptListMsg> rs = search.searchTestScript(rq);
		
		TestScriptListMsg rsMsg = rs.getResponseMessage();
		List<TestScript> testScriptList = rsMsg.getTestScriptList();

		assertTrue(testScriptList.size() > 0);
		
		for (TestScript testScript : testScriptList) {
			assertNotNull("Name is null", testScript.getName());
			assertNotNull("Description is null", testScript.getDescription());
			assertEquals(name, testScript.getName().getValue());
			
			// load deep 
			testScript = search.getTestScript(createGetTestScriptRequest(testScript.getId())).getResponseMessage().getTestScript();
			
			// delete
			testScript.setDatatypeState(DatatypeState.DELETED);
			ServiceRequest<TestScriptMsg> updateRq = createTestScriptRequest(testScript);
			maintain.maintainTestScript(updateRq);
		}
		
		rq = createSearchTestScriptRequest(name, null);
		rs = search.searchTestScript(rq);
		
		assertEquals(0, rs.getResponseMessage().getTestScriptList().size());
	}
	
	private TestScriptElement produceTestScriptElement(TestScriptElementType type) throws Exception {

		ServiceRequest<ProduceTestScriptElementMsg> rq = new ServiceRequest<ProduceTestScriptElementMsg>(
				getServiceContext());
		ProduceTestScriptElementMsg msg = new ProduceTestScriptElementMsg();
		msg.setTestScriptElementType(type);
		rq.setRequestMessage(msg);
		ServiceResponse<ProduceTestScriptElementMsg> rs = produce.produceTestScriptElement(rq);
		return rs.getResponseMessage().getTestScriptElement();
	}
	
	private ServiceRequest<TestScriptMsg> createTestScriptRequest(
			TestScript script) {

		ServiceRequest<TestScriptMsg> rq = new ServiceRequest<TestScriptMsg>(
				getServiceContext());
		TestScriptMsg msg = new TestScriptMsg();
		msg.setTestScript(script);
		rq.setRequestMessage(msg);
		return rq;
	}
	
	private ServiceRequest<TestScriptSearchMsg> createSearchTestScriptRequest(String name, String description) {
		
		ServiceRequest<TestScriptSearchMsg> rq = new ServiceRequest<TestScriptSearchMsg>(getServiceContext());
		TestScriptSearchMsg msg = new TestScriptSearchMsg();
		
		msg.setFolderId(new Identifier(2L));
		
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
		rq.setRequestMessage(msg);
		return rq;
	}
	
	private ServiceRequest<TestScriptSearchMsg> createGetTestScriptRequest(Long id) {
		
		ServiceRequest<TestScriptSearchMsg> rq = new ServiceRequest<TestScriptSearchMsg>(getServiceContext());
		TestScriptSearchMsg msg = new TestScriptSearchMsg();
		Identifier identifier = new Identifier();
		identifier.setValue(id);
		msg.setIdentifier(identifier);
		rq.setRequestMessage(msg);
		return rq;
	}
	
}
