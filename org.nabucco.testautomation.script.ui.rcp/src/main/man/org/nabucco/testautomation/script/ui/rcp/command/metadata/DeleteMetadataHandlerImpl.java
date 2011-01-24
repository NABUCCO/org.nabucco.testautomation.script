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
package org.nabucco.testautomation.script.ui.rcp.command.metadata;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.close.AbstractDeleteDatatypeHandler;
import org.nabucco.framework.plugin.base.model.BusinessModel;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.command.metadata.DeleteMetadataHandler;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.MetadataMaintenanceMultiPageEditView;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataMaintenanceEditViewBusinessModel;


/**
 * DeleteMetadataHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class DeleteMetadataHandlerImpl extends
        AbstractDeleteDatatypeHandler<MetadataMaintenanceMultiPageEditView> implements
        DeleteMetadataHandler {

    @Override
    public void deleteMetadata() {
        super.run();
    }

    @Override
    public String getId() {
        return MetadataMaintenanceMultiPageEditView.ID;
    }

    @Override
    protected boolean preClose(MetadataMaintenanceMultiPageEditView view) {

        Metadata metadata = view.getModel().getMetadata();

        metadata.setDatatypeState(DatatypeState.DELETED);

        BusinessModel businessModel = Activator.getDefault().getModel()
                .getBusinessModel(MetadataMaintenanceEditViewBusinessModel.ID);

        try {
            if (businessModel instanceof MetadataMaintenanceEditViewBusinessModel) {
                ((MetadataMaintenanceEditViewBusinessModel) businessModel).save(metadata);
            }
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
            return true;
        }

        return super.preClose(view);
    }
}
