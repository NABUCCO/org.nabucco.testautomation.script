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

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.close.AbstractDeleteDatatypeHandler;
import org.nabucco.framework.plugin.base.model.BusinessModel;
import org.nabucco.testautomation.property.facade.service.history.HistoryService;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.ScriptMaintenanceMultiplePageEditView;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.ScriptMaintenanceMultiplePageEditViewModel;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.ScriptMaintenanceEditViewBusinessModel;

/**
 * DeleteTestScriptHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class DeleteTestScriptHandlerImpl extends AbstractDeleteDatatypeHandler<ScriptMaintenanceMultiplePageEditView>
        implements DeleteTestScriptHandler {

    private static final String DIALOG_TITLE = "org.nabucco.testautomation.delete.dialog.info.title";

    private static final String DIALOG_MESSAGE = "org.nabucco.testautomation.delete.dialog.info.message";

    @Override
    public void deleteTestScript() {
        String viewId = this.getId();

        ScriptMaintenanceMultiplePageEditViewModel model = super.getView(viewId).getModel();

        if (model.isDirty()) {
            Shell shell = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
            MessageDialog.openWarning(shell, I18N.i18n(DIALOG_TITLE), I18N.i18n(DIALOG_MESSAGE));
            return;
        }
        super.run();
    }

    @Override
    public String getId() {
        return ScriptMaintenanceMultiplePageEditView.ID;
    }

    @Override
    protected boolean preClose(ScriptMaintenanceMultiplePageEditView view) throws ClientException {

        TestScript script = view.getModel().getTestScript();

        if (script.getDatatypeState() != DatatypeState.INITIALIZED) {
            script.setDatatypeState(DatatypeState.DELETED);
        }

        BusinessModel businessModel = Activator.getDefault().getModel()
                .getBusinessModel(ScriptMaintenanceEditViewBusinessModel.ID);

        if (businessModel instanceof ScriptMaintenanceEditViewBusinessModel) {
            ((ScriptMaintenanceEditViewBusinessModel) businessModel).save(script);
        }
        
        // Remove HistoryEntry
        HistoryService.getInstance().removeHistoryEntry(script.getIdentificationKey());

        return super.preClose(view);
    }

}
