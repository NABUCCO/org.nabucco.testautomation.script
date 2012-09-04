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
package org.nabucco.testautomation.script.ui.rcp.command.folder;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.command.AbstractSaveCommandHandlerImpl;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.ui.rcp.multipage.folder.model.FolderMaintenanceEditViewBusinessModel;
import org.nabucco.testautomation.script.ui.rcp.multipage.folder.model.FolderMaintenanceMultiPageEditViewModel;

/**
 * MaintainFolderStructureHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class MaintainFolderStructureHandlerImpl extends AbstractSaveCommandHandlerImpl<FolderMaintenanceEditViewBusinessModel, FolderMaintenanceMultiPageEditViewModel> implements
		MaintainFolderStructureHandler {

	@Override
	public void maintainFolderStructure() {
		super.run();
	}

	@Override
	protected void saveModel(
			FolderMaintenanceMultiPageEditViewModel viewModel,
			FolderMaintenanceEditViewBusinessModel businessModel)
			throws ClientException {
		
            Folder response = businessModel.save(viewModel.getRoot());
            viewModel.setRootFolder(response);
		
	}

	@Override
	public String getBusinessModelId() {
		return FolderMaintenanceEditViewBusinessModel.ID;
	}

}
