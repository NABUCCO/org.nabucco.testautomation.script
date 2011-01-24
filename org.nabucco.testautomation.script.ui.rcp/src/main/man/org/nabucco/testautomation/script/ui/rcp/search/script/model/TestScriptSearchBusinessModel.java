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
package org.nabucco.testautomation.script.ui.rcp.search.script.model;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.Description;
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
import org.nabucco.testautomation.script.ui.rcp.communication.search.SearchTestScriptDelegate;
import org.nabucco.testautomation.script.ui.rcp.search.script.model.TestScriptSearchViewModel;


/**
 * Does the search for TestScript.
 * 
 * @author Stefan Huettenrauch, PRODYNA AG
 */
public class TestScriptSearchBusinessModel implements NabuccoComponentSearchModel {

    public static final String ID = "org.nabucco.testautomation.script.ui.search.script.model.TestScriptSearchBusinessModel";

    @Override
    public TestScriptListViewBrowserElement search(NabuccoComponentSearchParameter searchViewModel) {
        TestScriptListViewBrowserElement result = null;
        if (searchViewModel instanceof TestScriptSearchViewModel) {
            TestScriptSearchViewModel testScriptSearchViewModel = (TestScriptSearchViewModel) searchViewModel;
            TestScriptSearchMsg rq = createTestScriptSearchMsg(testScriptSearchViewModel);
            result = new TestScriptListViewBrowserElement(search(rq).toArray(new TestScript[0]));

        }
        return result;
    }

    private List<TestScript> search(final TestScriptSearchMsg rq) {
        List<TestScript> result = null;
        try {
            SearchTestScriptDelegate searchDelegate = ScriptComponentServiceDelegateFactory
                    .getInstance().getSearchTestScript();
            TestScriptListMsg response = searchDelegate.searchTestScript(rq);
            result = response.getTestScriptList();
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
        return result;
    }

    private TestScriptSearchMsg createTestScriptSearchMsg(TestScriptSearchViewModel searchViewModel) {
        TestScriptSearchMsg result = new TestScriptSearchMsg();

        result.setName(getNameFromModel(searchViewModel));
        result.setDescription(getDescriptionFromModel(searchViewModel));

        return result;
    }

    private Description getDescriptionFromModel(final TestScriptSearchViewModel searchViewModel) {
        Description result = new Description();
        String description = searchViewModel.getTestScriptDescription();

        result.setValue((description == null || description.length() == 0) ? null : description);
        return result;
    }

    private Name getNameFromModel(TestScriptSearchViewModel searchViewModel) {
        Name result = new Name();
        String name = searchViewModel.getTestScriptName();

        result.setValue((name == null || name.length() == 0) ? null : name);
        return result;
    }

}
