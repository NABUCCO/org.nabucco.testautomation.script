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
package org.nabucco.testautomation.script.ui.rcp.command.metadata;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.close.AbstractDeleteDatatypeHandler;
import org.nabucco.framework.plugin.base.model.BusinessModel;
import org.nabucco.testautomation.property.facade.service.history.HistoryService;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.command.metadata.DeleteMetadataHandler;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.MetadataMaintenanceMultiPageEditView;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataMaintenanceEditViewBusinessModel;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataMaintenanceMultiPageEditViewModel;

/**
 * DeleteMetadataHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class DeleteMetadataHandlerImpl extends AbstractDeleteDatatypeHandler<MetadataMaintenanceMultiPageEditView>
        implements DeleteMetadataHandler {

    private static final String DIALOG_TITLE = "org.nabucco.testautomation.delete.dialog.info.title";

    private static final String DIALOG_MESSAGE = "org.nabucco.testautomation.delete.dialog.info.message";

    @Override
    public void deleteMetadata() {
        String viewId = this.getId();

        MetadataMaintenanceMultiPageEditViewModel model = super.getView(viewId).getModel();

        if (model.isDirty()) {
            Shell shell = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
            MessageDialog.openWarning(shell, I18N.i18n(DIALOG_TITLE), I18N.i18n(DIALOG_MESSAGE));
            return;
        }

        super.run();
    }

    @Override
    public String getId() {
        return MetadataMaintenanceMultiPageEditView.ID;
    }

    @Override
    protected boolean preClose(MetadataMaintenanceMultiPageEditView view) throws ClientException {

        MetadataMaintenanceMultiPageEditViewModel model = view.getModel();

        Metadata metadata = model.getMetadata();

        metadata.setDatatypeState(DatatypeState.DELETED);

        BusinessModel businessModel = Activator.getDefault().getModel()
                .getBusinessModel(MetadataMaintenanceEditViewBusinessModel.ID);
        if (businessModel instanceof MetadataMaintenanceEditViewBusinessModel) {
            ((MetadataMaintenanceEditViewBusinessModel) businessModel).save(metadata);
        }
        
        // Remove HistoryEntry
        HistoryService.getInstance().removeHistoryEntry(metadata.getIdentificationKey());

        return super.preClose(view);
    }
}
