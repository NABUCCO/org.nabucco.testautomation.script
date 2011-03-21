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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model;

import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.logging.Loggable;
import org.nabucco.framework.plugin.base.model.BusinessModel;
import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;
import org.nabucco.testautomation.script.facade.message.ProduceTestScriptElementMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptSearchMsg;
import org.nabucco.testautomation.script.ui.rcp.communication.ScriptComponentServiceDelegateFactory;
import org.nabucco.testautomation.script.ui.rcp.communication.maintain.MaintainTestScriptDelegate;
import org.nabucco.testautomation.script.ui.rcp.communication.produce.ProduceTestScriptElementDelegate;
import org.nabucco.testautomation.script.ui.rcp.communication.search.SearchTestScriptDelegate;

/**
 * ScriptMaintenanceEditViewBusinessModel
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class ScriptMaintenanceEditViewBusinessModel implements BusinessModel, Loggable {

    public static final String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.ScriptMaintenanceEditViewBusinessModel";

    /**
     * Saves a TestScript .
     * 
     * @param testScript
     *            the TestScript
     * @return the TestScript
     * 
     * @throws ClientException
     *             if the save was unsuccessful
     */
    public TestScript save(final TestScript testScript) throws ClientException {
        ScriptComponentServiceDelegateFactory scriptComponentServiceDelegateFactory = ScriptComponentServiceDelegateFactory
                .getInstance();

        MaintainTestScriptDelegate maintainTestScriptDelegate = scriptComponentServiceDelegateFactory
                .getMaintainTestScript();

        TestScriptMsg rq = new TestScriptMsg();
        DataModelManager.normalizeOrderIndicies(testScript, true);
        rq.setTestScript(testScript);

        TestScriptMsg rs = maintainTestScriptDelegate.maintainTestScript(rq);

        if (rs != null) {
        	return rs.getTestScript();
        }
        return testScript;
    }

    @Override
    public String getID() {
        return ScriptMaintenanceEditViewBusinessModel.ID;
    }

    public TestScript readTestScript(TestScript testScript) {
        try {
            ScriptComponentServiceDelegateFactory scriptComponentServiceDelegateFactory = ScriptComponentServiceDelegateFactory
                    .getInstance();
            SearchTestScriptDelegate searchTestScriptDelegate = scriptComponentServiceDelegateFactory
                    .getSearchTestScript();

            TestScriptSearchMsg rq = new TestScriptSearchMsg();
            rq.setIdentifier(new Identifier(testScript.getId()));
            TestScriptMsg response = searchTestScriptDelegate.getTestScript(rq);
            if (response != null) {
                TestScript result = response.getTestScript();
                DataModelManager.normalizeOrderIndicies(result, true);
				return result;
            }
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
        return testScript;
    }

    public PropertyList readPropertyList(PropertyList propertyList) {
        return propertyList; // no load necessary today.
    }

	public TestScript importDatatype(TestScript testScript) {
		try {
			ScriptComponentServiceDelegateFactory scriptComponentServiceDelegateFactory = ScriptComponentServiceDelegateFactory
			.getInstance();
			ProduceTestScriptElementDelegate produceTestScriptElementDelegate = scriptComponentServiceDelegateFactory.getProduceTestScriptElement();

			ProduceTestScriptElementMsg rq = new ProduceTestScriptElementMsg();
			rq.setTestScriptElement(testScript);
			rq.setTestScriptElementType(TestScriptElementType.SCRIPT);
			rq.setImportElement(new Flag(Boolean.TRUE));
			ProduceTestScriptElementMsg response = produceTestScriptElementDelegate.produceTestScriptElementClone(rq);
			if (response != null) {
				return (TestScript) response.getTestScriptElement();
			}
		} catch (ClientException e) {
			Activator.getDefault().logError(e);
		}
		return testScript;
	}

}
