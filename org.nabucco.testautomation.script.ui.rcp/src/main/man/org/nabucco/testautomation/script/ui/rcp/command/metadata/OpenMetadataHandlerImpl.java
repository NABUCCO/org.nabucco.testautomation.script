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

import org.nabucco.framework.plugin.base.command.AbstractOpenEditViewHandler;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.MetadataMaintenanceMultiPageEditView;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataMaintenanceEditViewBusinessModel;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataMaintenanceMultiPageEditViewModel;


/**
 * OpenTestScriptHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class OpenMetadataHandlerImpl
		extends
		AbstractOpenEditViewHandler<MetadataMaintenanceMultiPageEditViewModel, MetadataMaintenanceMultiPageEditView> {

	private Long metadataId;

	public OpenMetadataHandlerImpl(Long metadataId) {
		this.metadataId = metadataId;
	}

	@Override
	protected String getEditViewId() {
		return MetadataMaintenanceMultiPageEditView.ID;
	}

	@Override
	protected void updateModel(MetadataMaintenanceMultiPageEditViewModel model) {
		if (metadataId != null) {
			final MetadataMaintenanceEditViewBusinessModel businessModel = new MetadataMaintenanceEditViewBusinessModel();
			Metadata metadata = new Metadata();
			metadata.setId(metadataId);
			metadata = businessModel.readMetadata(metadata);
			model.setMetadata(metadata);
		}
	}

}
