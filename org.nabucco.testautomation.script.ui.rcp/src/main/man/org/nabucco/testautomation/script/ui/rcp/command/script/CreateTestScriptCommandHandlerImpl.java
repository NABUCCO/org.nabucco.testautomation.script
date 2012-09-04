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

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.create.AbstractCreateViewHandler;
import org.nabucco.testautomation.script.ui.rcp.command.script.CreateTestScriptCommandHandler;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.ScriptMaintenanceMultiplePageEditView;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.ScriptMaintenanceMultiplePageEditViewModel;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.ScriptMaintenanceMultiplePageEditViewModelHandler;


/**
 * CreateTestScriptCommandHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class CreateTestScriptCommandHandlerImpl
        extends
        AbstractCreateViewHandler<ScriptMaintenanceMultiplePageEditViewModel, ScriptMaintenanceMultiplePageEditView>
        implements CreateTestScriptCommandHandler {

    ScriptMaintenanceMultiplePageEditViewModelHandler handler = NabuccoInjector.getInstance(
            ScriptMaintenanceMultiplePageEditViewModel.class).inject(
            ScriptMaintenanceMultiplePageEditViewModelHandler.class);

    @Override
    public void createTestScriptCommand() {
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
    protected void updateModel(ScriptMaintenanceMultiplePageEditViewModel model) {
        model.setTestScript(handler.createDefaultDatatype());
    }
}
