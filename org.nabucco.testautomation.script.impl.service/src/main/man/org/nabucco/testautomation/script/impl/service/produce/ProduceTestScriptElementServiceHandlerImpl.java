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
package org.nabucco.testautomation.script.impl.service.produce;

import java.util.UUID;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Assertion;
import org.nabucco.testautomation.script.facade.datatype.dictionary.BreakLoop;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Condition;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Execution;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Foreach;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Lock;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Logger;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Loop;
import org.nabucco.testautomation.script.facade.datatype.dictionary.PropertyAction;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TextMessage;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;
import org.nabucco.testautomation.script.facade.datatype.dictionary.type.LoggerLevelType;
import org.nabucco.testautomation.script.facade.datatype.dictionary.type.OperatorType;
import org.nabucco.testautomation.script.facade.message.ProduceTestScriptElementMsg;
import org.nabucco.testautomation.script.impl.service.PropertySupport;
import org.nabucco.testautomation.script.impl.service.produce.ProduceTestScriptElementServiceHandler;

import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyUsageType;

/**
 * ProduceTestScriptElementServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ProduceTestScriptElementServiceHandlerImpl extends ProduceTestScriptElementServiceHandler {

	private static final long serialVersionUID = 1L;

	@Override
	public ProduceTestScriptElementMsg produceTestScriptElement(
			ProduceTestScriptElementMsg msg) throws ProduceException {
		
		if (msg.getTestScriptElementType() == null) {
			msg.setTestScriptElement(null);
			return msg;
		}
		
		TestScriptElement testScriptElement = null;
		
		switch (msg.getTestScriptElementType()) {
		case ACTION:
			testScriptElement = produceAction();
			break;
		case ASSERTION:
			testScriptElement = produceAssertion();
			break;
		case BREAK_LOOP:
			testScriptElement = produceBreakLoop();
			break;
		case CONDITION:
			testScriptElement = produceCondition();
			break;
		case EXECUTION:
			testScriptElement = produceExecution();
			break;
		case FOREACH:
			testScriptElement = produceForeach();
			break;
		case LOCK:
			testScriptElement = produceLock();
			break;
		case LOGGER:
			testScriptElement = produceLogger();
			break;
		case LOOP:
			testScriptElement = produceLoop();
			break;
		case SCRIPT:
			testScriptElement = produceTestScript();
			break;
		case TEXT_MESSAGE:
			testScriptElement = produceTextMessage();
			break;
		case PROPERTY_ACTION:
			testScriptElement = producePropertyAction();
			break;
		}
		
		if (testScriptElement != null) {
			testScriptElement.setDatatypeState(DatatypeState.INITIALIZED);
			msg.setTestScriptElement(testScriptElement);
			
			TestScriptElementContainer container = new TestScriptElementContainer();
			container.setDatatypeState(DatatypeState.INITIALIZED);
			container.setElement(testScriptElement);
			msg.setTestScriptElementContainer(container);
		}
		return msg;
	}
	
	private PropertyAction producePropertyAction() {
		PropertyAction action = new PropertyAction();
		action.setName("PropertyAction");
		action.setDatatypeState(DatatypeState.INITIALIZED);
		return action;
	}

	private TextMessage produceTextMessage() {
		TextMessage msg = new TextMessage();
		msg.setName("TextMessage");
		msg.setDatatypeState(DatatypeState.INITIALIZED);
		return msg;
	}

	private TestScript produceTestScript() {
		TestScript script = new TestScript();
		script.setName("TestScript");
		script.setDatatypeState(DatatypeState.INITIALIZED);
		return script;
	}

	private Loop produceLoop() {
		Loop loop = new Loop();
		loop.setName("Loop");
		loop.setDatatypeState(DatatypeState.INITIALIZED);
		return loop;
	}

	private Lock produceLock() {
		Lock lock = new Lock();
		lock.setName("Lock");
		lock.setDatatypeState(DatatypeState.INITIALIZED);
		return lock;
	}

	private Foreach produceForeach() {
		Foreach foreach = new Foreach();
		foreach.setName("Foreach");
		foreach.setDatatypeState(DatatypeState.INITIALIZED);
		return foreach;
	}

	private Condition produceCondition() {
		Condition condition = new Condition();
		condition.setName("Condition");
		condition.setDatatypeState(DatatypeState.INITIALIZED);
		condition.setIsBreakCondition(new Flag(Boolean.FALSE));
		condition.setOperator(OperatorType.NONE);
		return condition;
	}

	private BreakLoop produceBreakLoop() {
		BreakLoop breakLoop = new BreakLoop();
		breakLoop.setName("BreakLoop");
		breakLoop.setDatatypeState(DatatypeState.INITIALIZED);
		return breakLoop;
	}

	private Assertion produceAssertion() {
		Assertion assertion = new Assertion();
		assertion.setName("Assertion");
		assertion.setDatatypeState(DatatypeState.INITIALIZED);
		return assertion;
	}

	private Execution produceExecution() {
		Execution execution = new Execution();
		execution.setName("Execution");
		execution.setDatatypeState(DatatypeState.INITIALIZED);
		Action action = produceAction();
		add(action, execution);
		return execution;
	}

	private Action produceAction() {
		Action action = new Action();
		Long id = Math.abs(UUID.randomUUID().getMostSignificantBits());
		action.setActionId(id);
		action.setName("Action");
		action.setTrace(Boolean.FALSE);
		action.setDatatypeState(DatatypeState.INITIALIZED);
		try {
			PropertyList propertyList = PropertySupport.getInstance().producePropertyList(getContext());
			propertyList.setName("ActionParams");
			propertyList.setUsageType(PropertyUsageType.TEST_SCRIPT_ELEMENT_PARAM);
			action.setPropertyList(propertyList);
		} catch (Exception e) {
			// No handling -> return action without ProperyList
		}
		return action;
	}
	
	private Logger produceLogger() {
		Logger logger = new Logger();
		logger.setLevel(LoggerLevelType.INFO);
		logger.setName("Logger");
		logger.setDatatypeState(DatatypeState.INITIALIZED);
		TextMessage msg = produceTextMessage();
		add(msg, logger);
		return logger;
	}
	
	private void add(TestScriptElement element, TestScriptComposite to) {
		TestScriptElementContainer container = new TestScriptElementContainer();
		container.setDatatypeState(DatatypeState.INITIALIZED);
		container.setElement(element);
		container.setOrderIndex(to.getTestScriptElementList().size());
		to.getTestScriptElementList().add(container);
	}
	
}
