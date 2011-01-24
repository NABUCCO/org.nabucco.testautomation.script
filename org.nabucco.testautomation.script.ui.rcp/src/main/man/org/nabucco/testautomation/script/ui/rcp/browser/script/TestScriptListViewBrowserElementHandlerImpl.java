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
package org.nabucco.testautomation.script.ui.rcp.browser.script;

import org.nabucco.framework.plugin.base.model.browser.BrowserElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.ui.rcp.browser.script.TestScriptListViewBrowserElement;
import org.nabucco.testautomation.script.ui.rcp.browser.script.TestScriptListViewBrowserElementHandler;
import org.nabucco.testautomation.script.ui.rcp.list.script.model.TestScriptListViewModel;


/**
 * TestScriptListViewBrowserElementHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class TestScriptListViewBrowserElementHandlerImpl implements
        TestScriptListViewBrowserElementHandler {

    @Override
    public void createChildren(TestScriptListViewModel viewModel,
            TestScriptListViewBrowserElement element) {
        TestScript[] elements = viewModel.getElements();
        for (TestScript testScript : elements) {
            element.addBrowserElement(new TestScriptEditViewBrowserElement(testScript));
        }
    }

    @Override
    public void removeChild(BrowserElement toBeRemoved, TestScriptListViewBrowserElement element) {
        // do nothing
    }

}
