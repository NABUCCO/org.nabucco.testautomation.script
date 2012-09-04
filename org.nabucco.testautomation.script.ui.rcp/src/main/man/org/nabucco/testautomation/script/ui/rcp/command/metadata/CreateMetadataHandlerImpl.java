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

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.create.AbstractCreateViewHandler;
import org.nabucco.testautomation.script.ui.rcp.command.metadata.CreateMetadataHandler;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.MetadataMaintenanceMultiPageEditView;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataMaintenanceMultiPageEditViewModel;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataMaintenanceMultiPageEditViewModelHandler;


/**
 * CreateMetadataHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class CreateMetadataHandlerImpl
        extends
        AbstractCreateViewHandler<MetadataMaintenanceMultiPageEditViewModel, MetadataMaintenanceMultiPageEditView>
        implements CreateMetadataHandler {

    private MetadataMaintenanceMultiPageEditViewModelHandler handler = NabuccoInjector.getInstance(
            MetadataMaintenanceMultiPageEditViewModel.class).inject(
            MetadataMaintenanceMultiPageEditViewModelHandler.class);

    @Override
    public void createMetadata() {
        super.run();
    }

    @Override
    protected String getEditViewId() {
        return MetadataMaintenanceMultiPageEditView.ID;
    }

    @Override
    protected void updateModel(MetadataMaintenanceMultiPageEditViewModel model) {
        model.setMetadata(handler.createDefaultDatatype());
    }

}
