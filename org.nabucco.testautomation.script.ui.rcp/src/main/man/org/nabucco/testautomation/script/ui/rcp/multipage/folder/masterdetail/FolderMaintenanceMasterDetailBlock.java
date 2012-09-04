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
package org.nabucco.testautomation.script.ui.rcp.multipage.folder.masterdetail;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.ui.forms.DetailsPart;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailBlock;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailBlockLayouter;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.testautomation.property.ui.rcp.multipage.detail.PropertyDetailPageView;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.ui.rcp.multipage.folder.FolderMaintenanceMultiPageEditView;
import org.nabucco.testautomation.script.ui.rcp.multipage.folder.model.FolderMaintenanceMultiPageEditViewModel;

/**
 * FolderMaintenanceMasterDetailBlock
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class FolderMaintenanceMasterDetailBlock extends
        MasterDetailBlock<FolderMaintenanceMultiPageEditViewModel> implements
        Layoutable<FolderMaintenanceMultiPageEditViewModel> {

    public static String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.folder.masterdetail.FolderMaintenanceMasterDetailBlock";

    public FolderMaintenanceMasterDetailBlock(FolderMaintenanceMultiPageEditViewModel model,
            NabuccoMessageManager messageManager, FolderMaintenanceMultiPageEditView view) {
        super(messageManager, view, model);
    }

    @Override
    protected void registerPages(DetailsPart arg0) {
    	Map<Class<? extends Datatype>, Set<String>> typeToInvisiblePropertiesMap = this.getTypeToInvisiblePropertiesMap();
    	
    	Set<String> readOnlyProperties = new HashSet<String>();
    	Set<String> invisibleProperties = new HashSet<String>();
    	
    	/*
    	 * Folder
    	 */
    	readOnlyProperties.add("owner");
    	readOnlyProperties.add("identificationKey");
    	invisibleProperties.add("id");
    	invisibleProperties.add("version");
    	invisibleProperties.add("root");
    	invisibleProperties.add("type");
    	
        detailsPart.registerPage(Folder.class, new PropertyDetailPageView<FolderMaintenanceMultiPageEditViewModel>(this, getManagedForm(),
                getManagedFormViewPart(), nabuccoMessageManager, ID, ID + "Folder",
                invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(Folder.class, invisibleProperties);
        
        readOnlyProperties = new HashSet<String>();
        readOnlyProperties.add("owner");
        readOnlyProperties.add("name");
        readOnlyProperties.add("identificationKey");
        readOnlyProperties.add("description");

        detailsPart.registerPage(TestScript.class, new PropertyDetailPageView<FolderMaintenanceMultiPageEditViewModel>(this, getManagedForm(),
                getManagedFormViewPart(), nabuccoMessageManager, ID + ".TestScript", ID + "TestScript",
                invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(TestScript.class, invisibleProperties);

    }

    /**
     * {@inheritDoc}
     * 
     * @return
     */
    protected MasterDetailBlockLayouter<FolderMaintenanceMultiPageEditViewModel> getLayouter() {
        return new FolderMaintenanceMasterDetailBlockLayouter();
    }

}
