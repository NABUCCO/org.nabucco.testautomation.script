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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintainance;

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
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.ScriptMaintainanceMasterDetailBlock;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.ScriptMaintainanceMultiplePageEditViewModel;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.TestScriptCopyPasteHandler;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.TestScriptDragAndDropHandler;


/**
 * ScriptMaintainanceMultiplePageEditView
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class ScriptMaintainanceMultiplePageEditView extends
MultiPageEditView<ScriptMaintainanceMultiplePageEditViewModel> {

	public static final String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.ScriptMaintainanceMultiplePageEditView";

	public static final String TITLE = ID + ".title";

	public static final String TAB_TITLE = ID + ".tabTitle";

	private ScriptMaintainanceMasterDetailBlock masterDetailBlock;

	/**
	 * Creates a new {@link ScriptMaintainanceMultiplePageEditView} instance.
	 */
	public ScriptMaintainanceMultiplePageEditView() {
		super.model = new ScriptMaintainanceMultiplePageEditViewModel();
		TestScript testScript = new TestScript();
		testScript.setName("");
		model.setTestScript(testScript);
		super.model.setDragAndDropHandler(new TestScriptDragAndDropHandler());
		super.model.setCopyPasteHandler(new TestScriptCopyPasteHandler());
	}

	@Override
	protected String getManagedFormTitle() {
		return I18N.i18n(TITLE, getValues());
	}

	@Override
	public MasterDetailBlock<ScriptMaintainanceMultiplePageEditViewModel> getMasterDetailsBlock() {
		if (this.masterDetailBlock == null) {
			this.masterDetailBlock = new ScriptMaintainanceMasterDetailBlock(
					super.model, super.getMessageManager(), this);
		}
		return this.masterDetailBlock;
	}

	@Override
	public XMLEditorPage<ScriptMaintainanceMultiplePageEditViewModel> getXMLPage() {
		return new XmlDefaultPage<ScriptMaintainanceMultiplePageEditViewModel>();
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
			if (item.getToolTipText() != null && item.getToolTipText().equals("Reload")) {
				reloadItem = item;
			}
		}

		if (reloadItem != null) {
			if (super.getModel().getTestScript().getDatatypeState() == DatatypeState.INITIALIZED) {
				reloadItem.setEnabled(false);
			} else {
				reloadItem.setEnabled(true);
			}
		}
	}

}