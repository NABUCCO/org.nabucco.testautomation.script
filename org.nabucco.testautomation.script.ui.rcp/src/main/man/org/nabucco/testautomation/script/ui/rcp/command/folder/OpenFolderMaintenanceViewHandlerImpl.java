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
package org.nabucco.testautomation.script.ui.rcp.command.folder;

import org.nabucco.framework.plugin.base.command.AbstractOpenViewHandler;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.ui.rcp.command.folder.OpenFolderMaintenanceViewHandler;
import org.nabucco.testautomation.script.ui.rcp.multipage.folder.FolderMaintenanceMultiPageEditView;
import org.nabucco.testautomation.script.ui.rcp.multipage.folder.model.FolderElementFactory;
import org.nabucco.testautomation.script.ui.rcp.multipage.folder.model.FolderMaintenanceMultiPageEditViewModel;

/**
 * OpenFolderMaintenanceViewHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class OpenFolderMaintenanceViewHandlerImpl extends
		AbstractOpenViewHandler<FolderMaintenanceMultiPageEditView> implements
		OpenFolderMaintenanceViewHandler {

	@Override
	public void openFolderMaintenanceView() {
		super.openView(FolderMaintenanceMultiPageEditView.ID);
	}

	@Override
	protected void preOpen(FolderMaintenanceMultiPageEditView view) {
		Folder rootFolder = FolderElementFactory.getFolderStructure().get(0); // FIXME add more than one root folder
		FolderMaintenanceMultiPageEditViewModel model = getView(
				FolderMaintenanceMultiPageEditView.ID).getModel();
		model.setRootFolder(rootFolder);
		super.preOpen(view);
	}

}
