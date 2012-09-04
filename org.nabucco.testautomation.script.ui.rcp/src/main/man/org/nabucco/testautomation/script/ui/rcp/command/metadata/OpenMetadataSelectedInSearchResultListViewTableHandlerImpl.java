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

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.command.AbstractOpenCorrespondingEditViewHandlerImpl;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.command.metadata.OpenMetadataSelectedInSearchResultListViewTableHandler;
import org.nabucco.testautomation.script.ui.rcp.list.metadata.view.MetadataListView;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.MetadataMaintenanceMultiPageEditView;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataMaintenanceEditViewBusinessModel;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataMaintenanceMultiPageEditViewModel;

/**
 * OpenMetadataSelectedInSearchResultListViewTableHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class OpenMetadataSelectedInSearchResultListViewTableHandlerImpl
		extends
		AbstractOpenCorrespondingEditViewHandlerImpl<MetadataMaintenanceMultiPageEditViewModel, Metadata>
		implements OpenMetadataSelectedInSearchResultListViewTableHandler {

	@Override
	public void openMetadataSelectedInSearchResultListViewTable() {
		run();
	}

	@Override
	protected String getListViewId() {
		return MetadataListView.ID;
	}

	@Override
	protected String getEditorViewId() {
		return MetadataMaintenanceMultiPageEditView.ID;
	}

	@Override
	protected void openCorrespondingEditView(
			MetadataMaintenanceMultiPageEditViewModel model, Metadata selected) throws ClientException {

		// load full TestConfiguration
		MetadataMaintenanceEditViewBusinessModel businessModel = new MetadataMaintenanceEditViewBusinessModel();
		Metadata fullMetadata = businessModel.readMetadata(selected);
		model.setMetadata(fullMetadata);
	}

}
