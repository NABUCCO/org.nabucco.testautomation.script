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

import java.util.List;

import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.AbstractOpenEditViewHandler;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.browser.metadata.MetadataEditViewBrowserElement;
import org.nabucco.testautomation.script.ui.rcp.command.metadata.LoadAndOpenMetadataHandler;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.MetadataMaintenanceMultiPageEditView;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataMaintenanceEditViewBusinessModel;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataMaintenanceMultiPageEditViewModel;


/**
 * LoadAndOpenMetadataHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class LoadAndOpenMetadataHandlerImpl
        extends
        AbstractOpenEditViewHandler<MetadataMaintenanceMultiPageEditViewModel, MetadataMaintenanceMultiPageEditView>
        implements LoadAndOpenMetadataHandler {

    @Override
    public void loadAndOpenMetadata() {
        run();
    }

    @Override
    protected String getEditViewId() {
        return MetadataMaintenanceMultiPageEditView.ID;
    }

    @Override
    protected void updateModel(MetadataMaintenanceMultiPageEditViewModel model) {
        Metadata selection = getSelection();
        if (selection != null) {
            final MetadataMaintenanceEditViewBusinessModel businessModel = new MetadataMaintenanceEditViewBusinessModel();
            selection = businessModel.readMetadata(selection);
            model.setMetadata(selection);
        }

    }

    private Metadata getSelection() {
        Metadata result = null;
        // get browser view
        final List<BrowserElement> selected = Activator.getDefault().getModel().getBrowserModel()
                .getSelected();
        final BrowserElement firstElement = selected.get(0);
        result = ((MetadataEditViewBrowserElement) firstElement).getViewModel().getMetadata();

        return result;
    }
}
