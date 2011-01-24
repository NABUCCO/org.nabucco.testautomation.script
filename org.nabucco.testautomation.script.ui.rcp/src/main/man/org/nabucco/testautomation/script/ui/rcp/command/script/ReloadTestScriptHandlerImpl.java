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

import org.nabucco.framework.plugin.base.command.refresh.AbstractRefreshViewHandler;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.ui.rcp.command.script.ReloadTestScriptHandler;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.ScriptMaintainanceMultiplePageEditView;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.ScriptMaintainanceMultiplePageEditViewModel;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.ScriptMaintenanceEditViewBusinessModel;


/**
 * ReloadTestScriptHandlerImpl
 * 
 * @author Marco Sussek, PRODYNA AG
 */
public class ReloadTestScriptHandlerImpl
		extends
		AbstractRefreshViewHandler<ScriptMaintainanceMultiplePageEditViewModel, ScriptMaintainanceMultiplePageEditView>
		implements ReloadTestScriptHandler {

	@Override
	public void reloadTestScript() {
		run();
	}

	@Override
	protected String getEditViewId() {
		return ScriptMaintainanceMultiplePageEditView.ID;
	}

	@Override
	protected void updateModel(ScriptMaintainanceMultiplePageEditViewModel model) {
		if (model.getTestScript() != null
				&& model.getTestScript().getId() != null) {
			final TestScript testScript = new ScriptMaintenanceEditViewBusinessModel()
					.readTestScript(model.getTestScript());
			model.setTestScript(testScript);
			model.setDirty(false);
		}
	}

}