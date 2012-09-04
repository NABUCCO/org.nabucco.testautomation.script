/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.nabucco.testautomation.script.ui.rcp.browser.script;

import org.nabucco.framework.plugin.base.command.CommandHandler;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;
import org.nabucco.testautomation.script.ui.rcp.list.script.model.TestScriptListViewModel;

/**
 * TestScriptListViewBrowserElementHandler
 *
 * @author Undefined
 */
public interface TestScriptListViewBrowserElementHandler extends CommandHandler {

    /**
     * CreateChildren.
     *
     * @param element the TestScriptListViewBrowserElement.
     * @param viewModel the TestScriptListViewModel.
     */
    void createChildren(final TestScriptListViewModel viewModel, final TestScriptListViewBrowserElement element);

    /**
     * RemoveChild.
     *
     * @param element the TestScriptListViewBrowserElement.
     * @param toBeRemoved the BrowserElement.
     */
    void removeChild(final BrowserElement toBeRemoved, final TestScriptListViewBrowserElement element);
}
