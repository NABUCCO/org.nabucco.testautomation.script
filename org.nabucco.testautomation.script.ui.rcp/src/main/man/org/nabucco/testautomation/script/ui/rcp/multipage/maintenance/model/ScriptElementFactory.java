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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.testautomation.property.facade.datatype.BooleanProperty;
import org.nabucco.testautomation.property.facade.datatype.DateProperty;
import org.nabucco.testautomation.property.facade.datatype.FileProperty;
import org.nabucco.testautomation.property.facade.datatype.NumericProperty;
import org.nabucco.testautomation.property.facade.datatype.PropertyList;
import org.nabucco.testautomation.property.facade.datatype.SqlProperty;
import org.nabucco.testautomation.property.facade.datatype.TextProperty;
import org.nabucco.testautomation.property.facade.datatype.XPathProperty;
import org.nabucco.testautomation.property.facade.datatype.XmlProperty;
import org.nabucco.testautomation.property.facade.datatype.base.Property;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyContainer;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyType;
import org.nabucco.testautomation.property.facade.message.ProducePropertyMsg;
import org.nabucco.testautomation.property.facade.message.PropertyMsg;
import org.nabucco.testautomation.property.ui.rcp.communication.PropertyComponentServiceDelegateFactory;
import org.nabucco.testautomation.property.ui.rcp.communication.produce.ProducePropertyDelegate;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Assertion;
import org.nabucco.testautomation.script.facade.datatype.dictionary.BreakLoop;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Condition;
import org.nabucco.testautomation.script.facade.datatype.dictionary.EmbeddedTestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Execution;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Foreach;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Function;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Logger;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Loop;
import org.nabucco.testautomation.script.facade.datatype.dictionary.PropertyAction;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TextMessage;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;
import org.nabucco.testautomation.script.facade.message.ProduceTestScriptElementMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptListMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptSearchMsg;
import org.nabucco.testautomation.script.ui.rcp.communication.ScriptComponentServiceDelegateFactory;
import org.nabucco.testautomation.script.ui.rcp.communication.produce.ProduceScriptDelegate;
import org.nabucco.testautomation.script.ui.rcp.communication.search.SearchScriptDelegate;

/**
 * ScriptElementFactory
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class ScriptElementFactory {

    private static Map<Class<? extends Datatype>, Datatype> cache = new HashMap<Class<? extends Datatype>, Datatype>();

    private static Map<Class<? extends TestScriptElement>, TestScriptElementType> typeMap = new HashMap<Class<? extends TestScriptElement>, TestScriptElementType>();

    private static Map<Class<? extends Property>, PropertyType> propertyTypeMap = new HashMap<Class<? extends Property>, PropertyType>();

    static {
        typeMap.put(Action.class, TestScriptElementType.ACTION);
        typeMap.put(Assertion.class, TestScriptElementType.ASSERTION);
        typeMap.put(BreakLoop.class, TestScriptElementType.BREAK_LOOP);
        typeMap.put(Condition.class, TestScriptElementType.CONDITION);
        typeMap.put(Execution.class, TestScriptElementType.EXECUTION);
        typeMap.put(Foreach.class, TestScriptElementType.FOREACH);
        typeMap.put(Function.class, TestScriptElementType.FUNCTION);
        typeMap.put(Logger.class, TestScriptElementType.LOGGER);
        typeMap.put(Loop.class, TestScriptElementType.LOOP);
        typeMap.put(PropertyAction.class, TestScriptElementType.PROPERTY_ACTION);
        typeMap.put(TestScript.class, TestScriptElementType.SCRIPT);
        typeMap.put(TextMessage.class, TestScriptElementType.TEXT_MESSAGE);
        typeMap.put(EmbeddedTestScript.class, TestScriptElementType.EMBEDDED_SCRIPT);

        propertyTypeMap.put(PropertyList.class, PropertyType.LIST);
        propertyTypeMap.put(BooleanProperty.class, PropertyType.BOOLEAN);
        propertyTypeMap.put(DateProperty.class, PropertyType.DATE);
        propertyTypeMap.put(NumericProperty.class, PropertyType.NUMERIC);
        propertyTypeMap.put(TextProperty.class, PropertyType.TEXT);
        propertyTypeMap.put(XmlProperty.class, PropertyType.XML);
        propertyTypeMap.put(XPathProperty.class, PropertyType.XPATH);
        propertyTypeMap.put(SqlProperty.class, PropertyType.SQL);
        propertyTypeMap.put(FileProperty.class, PropertyType.FILE);
    }

    public static Datatype create(Class<? extends Datatype> className) {

        Datatype result = null;
        Datatype cachedElement = cache.get(className);

        if (cachedElement != null) {
            return cachedElement.cloneObject();
        } else {
            try {
                if (typeMap.containsKey(className)) {
                    ScriptComponentServiceDelegateFactory scriptComponentServiceDelegateFactory = ScriptComponentServiceDelegateFactory
                            .getInstance();

                    ProduceScriptDelegate produceTestScriptDelegate = scriptComponentServiceDelegateFactory
                            .getProduceScript();

                    ProduceTestScriptElementMsg rq = new ProduceTestScriptElementMsg();
                    rq.setTestScriptElementType(typeMap.get(className));

                    ProduceTestScriptElementMsg response = produceTestScriptDelegate.produceTestScriptElement(rq);
                    TestScriptElementContainer testScriptElementContainer = response.getTestScriptElementContainer();
                    result = testScriptElementContainer;
                } else if (propertyTypeMap.keySet().contains(className)) {
                    PropertyComponentServiceDelegateFactory testautomationComponentServiceDelegateFactory = PropertyComponentServiceDelegateFactory
                            .getInstance();
                    ProducePropertyDelegate produceProperty = testautomationComponentServiceDelegateFactory
                            .getProduceProperty();

                    ProducePropertyMsg producePropertyMsg = new ProducePropertyMsg();
                    producePropertyMsg.setPropertyType(propertyTypeMap.get(className));
                    PropertyMsg response = produceProperty.produceProperty(producePropertyMsg);
                    PropertyContainer propertyContainer = response.getPropertyContainer();
                    result = propertyContainer;
                }
            } catch (ClientException e) {
                Activator.getDefault().logError(e);
            }

            if (result != null) {
                cache.put(className, result.cloneObject());
            }
            return result;
        }
    }

    public static TestScriptElementContainer clone(TestScriptElement element) {
        ScriptComponentServiceDelegateFactory componentServiceDelegateFactory = ScriptComponentServiceDelegateFactory
                .getInstance();
        ProduceScriptDelegate produceTestScriptElementDelegate;
        try {
            produceTestScriptElementDelegate = componentServiceDelegateFactory.getProduceScript();
            ProduceTestScriptElementMsg rq = new ProduceTestScriptElementMsg();
            rq.setTestScriptElement(element);
            TestScriptElementContainer testScriptElementClone = produceTestScriptElementDelegate
                    .produceTestScriptElementClone(rq).getTestScriptElementContainer();
            return testScriptElementClone;
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
        return null;
    }

    public static TestScript[] getExistingTestScripts() {
        try {
            SearchScriptDelegate searchTestScriptDelegate = ScriptComponentServiceDelegateFactory.getInstance()
                    .getSearchScript();

            TestScriptSearchMsg rq = new TestScriptSearchMsg();

            TestScriptListMsg rs = searchTestScriptDelegate.searchTestScript(rq);
            List<TestScript> testScriptList = rs.getTestScriptList();
            return testScriptList.toArray(new TestScript[testScriptList.size()]);
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
        return new TestScript[] {};
    }

}
