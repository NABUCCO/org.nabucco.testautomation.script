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
package org.nabucco.testautomation.script.ui.rcp.multipage.folder;

import java.io.Serializable;
import java.util.Map;

import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailBlock;
import org.nabucco.framework.plugin.base.component.multipage.view.MultiPageEditView;
import org.nabucco.framework.plugin.base.component.multipage.xml.XMLEditorPage;
import org.nabucco.framework.plugin.base.component.multipage.xml.example.XmlDefaultPage;
import org.nabucco.testautomation.script.ui.rcp.multipage.folder.masterdetail.FolderMaintenanceMasterDetailBlock;
import org.nabucco.testautomation.script.ui.rcp.multipage.folder.model.FolderMaintenanceMultiPageEditViewModel;
import org.nabucco.testautomation.script.ui.rcp.multipage.folder.model.FolderTreeDragAndDropHandler;


/**
 * FolderMaintenanceMultiPageEditView
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class FolderMaintenanceMultiPageEditView extends
        MultiPageEditView<FolderMaintenanceMultiPageEditViewModel> {

    public static final String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.folder.FolderMaintenanceMultiPageEditView";

    public static final String TITLE = ID + ".title";

    public static final String TAB_TITLE = ID + ".tabTitle";

    /**
     * Creates a new {@link FolderMaintenanceMultiPageEditView} instance.
     */
    public FolderMaintenanceMultiPageEditView() throws ClientException {
        super.model = new FolderMaintenanceMultiPageEditViewModel();
        super.model.setDragAndDropHandler(new FolderTreeDragAndDropHandler());
    }

    private FolderMaintenanceMasterDetailBlock masterDetailBlock;

    @Override
    protected String getManagedFormTitle() {
        return I18N.i18n(TITLE, getValues());
    }

    @Override
    public MasterDetailBlock<FolderMaintenanceMultiPageEditViewModel> getMasterDetailsBlock() {
        if (masterDetailBlock == null) {
            masterDetailBlock = new FolderMaintenanceMasterDetailBlock(super.model,
                    super.getMessageManager(), this);
        }
        return masterDetailBlock;
    }

    @Override
    public XMLEditorPage<FolderMaintenanceMultiPageEditViewModel> getXMLPage() {
        return new XmlDefaultPage<FolderMaintenanceMultiPageEditViewModel>();
    }

    public Map<String, Serializable> getValues() {
        return model.getValues();
    }

    @Override
    public String getNewPartName() {
        return I18N.i18n(TAB_TITLE, getValues());
    }
    
    @Override
    public String getId() {
        return ID;
    }

}
