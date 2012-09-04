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
package org.nabucco.testautomation.script.ui.rcp.search.script.model;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.Key;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchModel;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.message.TestScriptListMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptSearchMsg;
import org.nabucco.testautomation.script.ui.rcp.browser.script.TestScriptListViewBrowserElement;
import org.nabucco.testautomation.script.ui.rcp.communication.ScriptComponentServiceDelegateFactory;
import org.nabucco.testautomation.script.ui.rcp.communication.search.SearchScriptDelegate;

/**
 * Does the search for TestScript.
 * 
 * @author Stefan Huettenrauch, PRODYNA AG
 */
public class TestScriptSearchBusinessModel implements NabuccoComponentSearchModel {

    public static final String ID = "org.nabucco.testautomation.script.ui.search.script.model.TestScriptSearchBusinessModel";

    @Override
    public TestScriptListViewBrowserElement search(NabuccoComponentSearchParameter parameter) {

        TestScriptListViewBrowserElement result = null;

        if (parameter instanceof TestScriptSearchViewModel) {
            TestScriptSearchViewModel testScriptSearchViewModel = (TestScriptSearchViewModel) parameter;
            TestScriptSearchMsg rq = createTestScriptSearchMsg(testScriptSearchViewModel);
            result = new TestScriptListViewBrowserElement(search(rq).toArray(new TestScript[0]));

        }
        return result;
    }

    private List<TestScript> search(final TestScriptSearchMsg rq) {

        List<TestScript> result = null;

        try {
            SearchScriptDelegate searchDelegate = ScriptComponentServiceDelegateFactory.getInstance().getSearchScript();
            TestScriptListMsg response = searchDelegate.searchTestScript(rq);
            result = response.getTestScriptList();
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
        return result;
    }

    private TestScriptSearchMsg createTestScriptSearchMsg(TestScriptSearchViewModel searchViewModel) {

        TestScriptSearchMsg result = new TestScriptSearchMsg();
        result.setName(getName(searchViewModel));
        result.setTestScriptKey(getElementKey(searchViewModel));

        return result;
    }

    private Key getElementKey(final TestScriptSearchViewModel searchViewModel) {

        Key result = new Key();
        String key = searchViewModel.getTestScriptIdentificationKey();

        result.setValue((key == null || key.length() == 0) ? null : key);
        return result;
    }

    private Name getName(TestScriptSearchViewModel searchViewModel) {

        Name result = new Name();
        String name = searchViewModel.getTestScriptName();

        result.setValue((name == null || name.length() == 0) ? null : name);
        return result;
    }

}
