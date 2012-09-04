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

import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.command.AbstractSaveCommandHandlerImpl;
import org.nabucco.testautomation.property.facade.service.history.HistoryEntry;
import org.nabucco.testautomation.property.facade.service.history.HistoryEntryType;
import org.nabucco.testautomation.property.facade.service.history.HistoryService;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.ScriptMaintenanceMultiplePageEditViewModel;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.ScriptMaintenanceEditViewBusinessModel;

/**
 * SaveTestScriptHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class SaveTestScriptHandlerImpl
        extends
        AbstractSaveCommandHandlerImpl<ScriptMaintenanceEditViewBusinessModel, ScriptMaintenanceMultiplePageEditViewModel>
        implements SaveTestScriptHandler {

    @Override
    public void saveTestScript() {
        run();
    }

    @Override
    public String getBusinessModelId() {
        return ScriptMaintenanceEditViewBusinessModel.ID;
    }

    @Override
    protected void saveModel(ScriptMaintenanceMultiplePageEditViewModel viewModel,
            ScriptMaintenanceEditViewBusinessModel businessModel) throws ClientException {
        TestScript response = businessModel.save(viewModel.getTestScript());
        viewModel.setTestScript(response);
        
        HistoryEntry entry = new HistoryEntry(HistoryEntryType.TEST_SCRIPT, new Identifier(response.getId()),
                response.getIdentificationKey(), response.getName(), response.getDescription());
        HistoryService.getInstance().addHistoryEntry(entry);

    }
}
