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
package org.nabucco.testautomation.script.ui.rcp.command.script;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.command.NabuccoAbstractImportDatatypeCommandHandlerImpl;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.ScriptMaintainanceMultiplePageEditViewModel;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.ScriptMaintenanceEditViewBusinessModel;

/**
 * ImportDatatypeHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class ImportDatatypeHandlerImpl
		extends	NabuccoAbstractImportDatatypeCommandHandlerImpl<ScriptMaintenanceEditViewBusinessModel, ScriptMaintainanceMultiplePageEditViewModel>
		implements ImportDatatypeHandler {

	@Override
	public void importDatatype() {
		run();
	}
	
    @Override
    public String getBusinessModelId() {
        return ScriptMaintenanceEditViewBusinessModel.ID;
    }

    @Override
    protected void importDatatype(ScriptMaintainanceMultiplePageEditViewModel viewModel,
    		ScriptMaintenanceEditViewBusinessModel businessModel) throws ClientException {
    	TestScript response = businessModel.importDatatype(viewModel.getTestScript());
        viewModel.setTestScript(response);
    }
	
}