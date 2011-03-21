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

import java.io.Serializable;
import java.util.Map;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.model.browser.DatatypeBrowserElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.ScriptMaintainanceMultiplePageEditViewModel;

/**
 * TestScriptEditViewBrowserElement
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class TestScriptEditViewBrowserElement extends DatatypeBrowserElement implements
        NabuccoInjectionReciever {

    private TestScriptEditViewBrowserElementHandler browserHandler;

    private ScriptMaintainanceMultiplePageEditViewModel viewModel;

    /**
     * Constructs a new TestScriptEditViewBrowserElement instance.
     * 
     * @param datatype
     *            the TestScript.
     */
    public TestScriptEditViewBrowserElement(final TestScript datatype) {
        super();
        NabuccoInjector instance = NabuccoInjector
                .getInstance(TestScriptEditViewBrowserElement.class);
        browserHandler = instance.inject(TestScriptEditViewBrowserElementHandler.class);
        viewModel = new ScriptMaintainanceMultiplePageEditViewModel(datatype);
    }

    @Override
    protected void createChildren() {
        this.clearChildren();
        browserHandler.createChildren(viewModel, this);
    }

    @Override
    protected void fillDatatype() {
        viewModel = browserHandler.loadFull(viewModel);
    }

    /**
     * Getter for the ViewModel.
     * 
     * @return the TestScriptEditViewModel.
     */
    public ScriptMaintainanceMultiplePageEditViewModel getViewModel() {
        return this.viewModel;
    }

    @Override
    public Map<String, Serializable> getValues() {
        return this.viewModel.getValues();
    }

    /**
     * Setter for the ViewModel.
     * 
     * @param viewModel
     *            the TestScriptEditViewModel.
     */
    public void setViewModel(ScriptMaintainanceMultiplePageEditViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
