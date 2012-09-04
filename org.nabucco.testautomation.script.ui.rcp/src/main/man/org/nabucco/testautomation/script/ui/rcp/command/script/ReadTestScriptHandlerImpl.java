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
package org.nabucco.testautomation.script.ui.rcp.command.script;

import java.util.List;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.AbstractOpenEditViewHandler;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.ui.rcp.browser.script.TestScriptEditViewBrowserElement;
import org.nabucco.testautomation.script.ui.rcp.command.script.ReadTestScriptHandler;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.ScriptMaintenanceMultiplePageEditView;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.ScriptMaintenanceMultiplePageEditViewModel;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.ScriptMaintenanceEditViewBusinessModel;


/**
 * ReadTestScriptHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class ReadTestScriptHandlerImpl
        extends
        AbstractOpenEditViewHandler<ScriptMaintenanceMultiplePageEditViewModel, ScriptMaintenanceMultiplePageEditView>
        implements ReadTestScriptHandler {

    @Override
    public void readTestScript() {
        run();
    }

    @Override
    public void run() {
        super.openView(getEditViewId());
    }

    @Override
    protected String getEditViewId() {
        return ScriptMaintenanceMultiplePageEditView.ID;
    }

    @Override
    protected void updateModel(ScriptMaintenanceMultiplePageEditViewModel model) throws ClientException {
        TestScript selection = getSelection();
        if (selection != null) {
            final ScriptMaintenanceEditViewBusinessModel businessModel = new ScriptMaintenanceEditViewBusinessModel();
            selection = businessModel.readTestScript(selection);
            model.setTestScript(selection);
        }

    }

    private TestScript getSelection() {
        TestScript result = null;
        // get browser view
        final List<BrowserElement> selected = Activator.getDefault().getModel().getBrowserModel()
                .getSelected();
        final BrowserElement firstElement = selected.get(0);
        result = ((TestScriptEditViewBrowserElement) firstElement).getViewModel().getTestScript();

        return result;
    }
}
