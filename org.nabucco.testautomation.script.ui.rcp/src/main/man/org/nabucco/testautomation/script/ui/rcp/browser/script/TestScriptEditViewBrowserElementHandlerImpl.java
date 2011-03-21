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

import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.ScriptMaintainanceMultiplePageEditViewModel;

/**
 * TestScriptEditViewBrowserElementHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class TestScriptEditViewBrowserElementHandlerImpl implements
        TestScriptEditViewBrowserElementHandler {

    @Override
    public ScriptMaintainanceMultiplePageEditViewModel loadFull(
            ScriptMaintainanceMultiplePageEditViewModel testScript) {
        return testScript;
    }

    @Override
    public void createChildren(ScriptMaintainanceMultiplePageEditViewModel viewModel,
            TestScriptEditViewBrowserElement element) {
        // do nothing. TestScriptElement won´t be displayed in the BrowserView
    }

}
