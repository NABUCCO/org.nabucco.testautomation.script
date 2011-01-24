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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.testscript.folder;

import java.lang.reflect.Method;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.widget.AbstractBaseTypeWidgetCreator;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.widget.BaseTypeWidgetFactory;
import org.nabucco.framework.plugin.base.component.newpicker.composite.element.ElementPickerComposite;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.tree.TreePickerDialog;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.tree.TreePickerDialogLabel;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.tree.TreePickerDialogParameter;
import org.nabucco.framework.plugin.base.model.ViewModel;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;


/**
 * MetadataPickerActionComboWidgetCreator
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class FolderPickerActionComboWidgetCreator extends
		AbstractBaseTypeWidgetCreator<Folder> {

	private static final String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.script.maintainance.masterdetails.widgetcreators.metadata.FolderPicker";

	private static final String TITLE = ID + ".dialogTitle";

	private static final String SHELL_TITLE = ID + ".shellTitle";

	private static final String MESSAGE = ID + ".dialogMessage";

	private static final String TREE_TITLE = ID + ".treeTitle";

	/**
	 * Creates a new {@link FolderPickerActionComboWidgetCreator} instance.
	 * 
	 * @param nft
	 *            the form toolkit
	 */
	public FolderPickerActionComboWidgetCreator(NabuccoFormToolkit nft) {
		super(nft);
	}

	public Control createWidget(Composite parent, Folder folder,
			Method method, Object dataObject, boolean readOnly,
			ViewModel viewModel, NabuccoMessageManager messageManager,
			String propertyName, GridData data, String masterBlockId) {

		Control result = null;

		if (dataObject instanceof TestScript) {
			TestScript testScript = (TestScript) dataObject;
			FolderPickerActionComboMiniModel model = new FolderPickerActionComboMiniModel(
					viewModel, testScript);
			BaseTypeWidgetFactory widgetFactory = new BaseTypeWidgetFactory(
					super.getFormToolkit());

			// Layout Folder-Picker and Label
			Label label = widgetFactory.createLabel(parent, masterBlockId + "."
					+ FolderPickerActionComboMiniModel.PROPERTY_FOLDER);
			label.setToolTipText(label.getText());
			label.setLayoutData(data);
			result = this.layoutPicker(parent, testScript, viewModel,
					model);

		}
		return result;
	}

	/**
	 * Layout the script table.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param model
	 * 
	 * @return the layouted table
	 */
	private Control layoutPicker(Composite parent, TestScript testScript,
			ViewModel viewModel, FolderPickerActionComboMiniModel model) {

		ElementPickerComposite<TreePickerDialog> picker = this.createPicker(
				parent, model);

		DataBindingContext bindingContext = new DataBindingContext();
		IObservableValue uiElement = SWTObservables.observeText(
				picker.getTextField(), SWT.Modify);
		IObservableValue modelElement = BeansObservables.observeValue(model,
				FolderPickerActionComboMiniModel.PROPERTY_FOLDER_NAME);

		bindingContext.bindValue(uiElement, modelElement, null, null);

		return picker;
	}

	/**
	 * Creates the picker composite.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param model
	 *            the mini model
	 * 
	 * @return the layouted composite
	 */
	private ElementPickerComposite<TreePickerDialog> createPicker(
			Composite parent, FolderPickerActionComboMiniModel model) {

		TreePickerDialogLabel label = new TreePickerDialogLabel(TITLE, MESSAGE,
				SHELL_TITLE, "Tree", TREE_TITLE);

		TreePickerDialogParameter parameter = new TreePickerDialogParameter(
				label, model);
		parameter.setContentProvider(new FolderPickerDialogContentProvider());
		parameter.setLabelProvider(new FolderPickerDialogLabelProvider());

		TreePickerDialog dialog = new FolderTreePickerDialog(parent.getShell(),
				parameter);
		

		dialog.addSelectionListener(FolderPickerDialogListener.ID,
				new FolderPickerDialogListener(model));

		return new ElementPickerComposite<TreePickerDialog>(parent, SWT.NONE,
				dialog);
	}

	@Override
	protected Control createWidget(Composite parent, Folder specialized,
			Method method, Object object, boolean readOnly,
			ViewModel externalViewModel, NabuccoMessageManager messageManager,
			String propertyName) {
		throw new UnsupportedOperationException();
	}

}
