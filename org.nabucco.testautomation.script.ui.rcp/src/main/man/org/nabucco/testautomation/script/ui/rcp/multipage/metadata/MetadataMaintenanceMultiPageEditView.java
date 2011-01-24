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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Map;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailBlock;
import org.nabucco.framework.plugin.base.component.multipage.view.MultiPageEditView;
import org.nabucco.framework.plugin.base.component.multipage.xml.XMLEditorPage;
import org.nabucco.framework.plugin.base.component.multipage.xml.example.XmlDefaultPage;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.MetadataMaintenanceMasterDetailBlock;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataCopyPasteHandler;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataDragAndDropHandler;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataMaintenanceMultiPageEditViewModel;


/**
 * MetadataMaintainanceMultiplePageEditView
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class MetadataMaintenanceMultiPageEditView extends
MultiPageEditView<MetadataMaintenanceMultiPageEditViewModel> {

	public static final String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.metadata.MetadataMaintenanceMultiPageEditView";

	public static final String TITLE = ID + ".title";

	public static final String TAB_TITLE = ID + ".tabTitle";

	/**
	 * Creates a new {@link MetadataMaintenanceMultiPageEditView} instance.
	 */
	public MetadataMaintenanceMultiPageEditView() {
		super.model = new MetadataMaintenanceMultiPageEditViewModel();
		Metadata metadata = new Metadata();
		metadata.setName("");
		model.setMetadata(metadata);
		model.setDragAndDropHandler(new MetadataDragAndDropHandler());
		model.setCopyPasteHandler(new MetadataCopyPasteHandler());
	}

	private MetadataMaintenanceMasterDetailBlock masterDetailBlock;

	@Override
	protected String getManagedFormTitle() {
		return I18N.i18n(TITLE, getValues());
	}

	@Override
	public MasterDetailBlock<MetadataMaintenanceMultiPageEditViewModel> getMasterDetailsBlock() {
		if (masterDetailBlock == null) {
			masterDetailBlock = new MetadataMaintenanceMasterDetailBlock(
					super.model, super.getMessageManager(), this);
		}
		return masterDetailBlock;
	}

	@Override
	public XMLEditorPage<MetadataMaintenanceMultiPageEditViewModel> getXMLPage() {
		return new XmlDefaultPage<MetadataMaintenanceMultiPageEditViewModel>();
	}

	public Map<String, Serializable> getValues() {
		return model.getValues();
	}

	@Override
	public String getNewPartName() {
		return I18N.i18n(TAB_TITLE, getValues());
	}

	@Override
	public void postOpen() {
		// Activate or deactivate Reload Button
		refreshReloadButtonState();
		super.model.addPropertyChangeListener("datatype",
				new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				refreshReloadButtonState();
			}
		});
	}

	private void refreshReloadButtonState() {
		// TODO Develop general concept in plugin.base
		final ToolBar toolBar = ((ToolBarManager) getToolBarManager())
		.getControl();
		ToolItem reloadItem = null;

		for (ToolItem item : toolBar.getItems()) {
			if (item.getToolTipText().equals("Reload")) {
				reloadItem = item;
			}
		}

		if (reloadItem != null) {
			if (super.getModel().getMetadata().getDatatypeState() == DatatypeState.INITIALIZED) {
				reloadItem.setEnabled(false);
			} else {
				reloadItem.setEnabled(true);
			}
		}
	}

}