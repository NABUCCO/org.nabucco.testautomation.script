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

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.command.NabuccoAbstractSaveCommandHandlerImpl;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.command.metadata.MaintainMetadataHandler;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataMaintenanceEditViewBusinessModel;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataMaintenanceMultiPageEditViewModel;


/**
 * MaintainMetadataHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class MaintainMetadataHandlerImpl
        extends
        NabuccoAbstractSaveCommandHandlerImpl<MetadataMaintenanceEditViewBusinessModel, MetadataMaintenanceMultiPageEditViewModel>
        implements MaintainMetadataHandler {

    @Override
    public void maintainMetadata() {
        run();
    }

    @Override
    public String getBusinessModelId() {
        return MetadataMaintenanceEditViewBusinessModel.ID;
    }

    @Override
    protected void saveModel(MetadataMaintenanceMultiPageEditViewModel viewModel,
            MetadataMaintenanceEditViewBusinessModel businessModel) throws ClientException {
            Metadata response = businessModel.save(viewModel.getMetadata());
            viewModel.setMetadata(response);
    }

}
