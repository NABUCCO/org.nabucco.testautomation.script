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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.action;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.widget.AbstractBaseTypeWidgetCreator;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.widget.BaseTypeWidgetFactory;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.MasterTreeExternalDatatypeTransfer;
import org.nabucco.framework.plugin.base.component.newpicker.composite.element.ElementPickerComposite;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.tree.TreePickerDialog;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.tree.TreePickerDialogLabel;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.tree.TreePickerDialogParameter;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerCombo;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerComboParameter;
import org.nabucco.framework.plugin.base.layout.ImageProvider;
import org.nabucco.framework.plugin.base.model.ViewModel;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.testautomation.script.facade.datatype.code.CodeParameter;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineActionCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineOperationCode;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.images.ScriptImageRegistry;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.action.action.ObjectListContentProvider;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.action.action.SubEngineActionCodeComboBoxHandler;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.action.action.SubEngineActionCodeLabelProvider;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.action.metadata.MetadataPickerDialogContentProvider;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.action.metadata.MetadataPickerDialogLabelProvider;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.action.metadata.MetadataPickerDialogListener;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.action.metadata.MetadataTreePickerDialog;

import org.nabucco.testautomation.facade.datatype.property.base.PropertyType;

/**
 * MetadataPickerActionComboWidgetCreator
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MetadataPickerActionComboWidgetCreator extends
AbstractBaseTypeWidgetCreator<Metadata> {

	private static final String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.script.maintainance.masterdetails.widgetcreators.metadata.MetadataPicker";

	private static final String TITLE = ID + ".dialogTitle";

	private static final String SHELL_TITLE = ID + ".shellTitle";

	private static final String MESSAGE = ID + ".dialogMessage";

	private static final String TREE_TITLE = ID + ".treeTitle";
	
	private static final String ICON_PROPERTY = "icons/text.png";

	private static final String ICON_PROPERTY_LIST = "icons/browser_list.png";

	private static final String ICON_PROPERTY_STRING = "icons/text.png";

	private static final String ICON_PROPERTY_NUMERIC = "icons/calculator.png";

	private static final String ICON_PROPERTY_XML = "icons/xml.png";

	private Composite parent;
	private BaseTypeWidgetFactory widgetFactory;
	private GridData data;
	private ViewModel externalViewModel;
	private String masterBlockId;
	private Action action;

	/**
	 * Creates a new {@link MetadataPickerActionComboWidgetCreator} instance.
	 * 
	 * @param nft
	 *            the form toolkit
	 */
	public MetadataPickerActionComboWidgetCreator(Composite parent, BaseTypeWidgetFactory widgetFactory, GridData data, NabuccoFormToolkit nft,
			ViewModel externalViewModel, String masterBlockId, Metadata metadata, Action action) {
		super(nft);
		this.parent = parent;
		this.widgetFactory = widgetFactory;
		this.data = data;
		this.externalViewModel = externalViewModel;
		this.masterBlockId = masterBlockId;
		this.action = action;
	}

	public Control[] createWidgets() {

		Control[] result = new Control[3];

		MetadataPickerActionComboMiniModel model = new MetadataPickerActionComboMiniModel(
				externalViewModel, action, masterBlockId);

		BaseTypeWidgetFactory widgetFactory = new BaseTypeWidgetFactory(
				super.getFormToolkit());

		// Layout Metadata-Picker and Label
		Label label = widgetFactory.createLabel(parent, masterBlockId + "."
				+ MetadataPickerActionComboMiniModel.PROPERTY_METADATA);
		label.setToolTipText(label.getText());
		label.setLayoutData(data);
		result[0] = this.layoutPicker(model);

		// Layout Action-Combo and Label
		label = widgetFactory.createLabel(parent, masterBlockId + "."
				+ MetadataPickerActionComboMiniModel.PROPERTY_ACTION);
		label.setToolTipText(label.getText());
		label.setLayoutData(data);
		result[1] = this.layoutActionCombo(model);

		TableViewer propertiesControl = layoutPropertiesControl();
		model.setPropertiesListViewer(propertiesControl);
		result[2] = propertiesControl.getControl();



		return result;
	}


	private Control layoutActionCombo(MetadataPickerActionComboMiniModel model) {

		// Create ActionCombo ContentProvider
		List<SubEngineActionCode> availableActionCodes;
		Metadata metadata = action.getMetadata();
		boolean readOnly = false;
		if (metadata == null) {
			readOnly = true;
			availableActionCodes = new ArrayList<SubEngineActionCode>();
		} else {
			SubEngineOperationCode subEngineOperationCode = metadata
			.getOperation();
			availableActionCodes = subEngineOperationCode.getActionList();
		}
		ObjectListContentProvider contentProvider = new ObjectListContentProvider(
				availableActionCodes);
		ElementPickerComboParameter parameter = new ElementPickerComboParameter(
				contentProvider, new SubEngineActionCodeLabelProvider());

		// Layout ActionCombo
		ElementPickerCombo combo = super.getFormToolkit()
		.createElementPickerCombo(parent, parameter, readOnly, false);

		// Set Combo and ContentProvier to MiniModel
		model.setActionCombo(combo);
		model.setActionComboContentProvider(contentProvider);

		// Bind ActionCombo
		DataBindingContext bindingContext = new DataBindingContext();
		IObservableValue uiElement = SWTObservables.observeSelection(combo
				.getCombo());
		IObservableValue modelElement = BeansObservables.observeValue(model,
				MetadataPickerActionComboMiniModel.PROPERTY_ACTION);
		bindingContext.bindValue(uiElement, modelElement, null, null);

		combo.addSelectionListener(new SubEngineActionCodeComboBoxHandler(model));
		return combo;
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
	private Control layoutPicker(MetadataPickerActionComboMiniModel model) {

		ElementPickerComposite<TreePickerDialog> picker = this.createPicker(
				parent, model);

		DataBindingContext bindingContext = new DataBindingContext();
		IObservableValue uiElement = SWTObservables.observeText(
				picker.getTextField(), SWT.Modify);
		IObservableValue modelElement = BeansObservables.observeValue(model,
				MetadataPickerActionComboMiniModel.PROPERTY_METADATA_NAME);

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
			Composite parent, MetadataPickerActionComboMiniModel model) {

		TreePickerDialogLabel label = new TreePickerDialogLabel(TITLE, MESSAGE,
				SHELL_TITLE, "Tree", TREE_TITLE);

		TreePickerDialogParameter parameter = new TreePickerDialogParameter(
				label, model);
		parameter.setContentProvider(new MetadataPickerDialogContentProvider());
		parameter.setLabelProvider(new MetadataPickerDialogLabelProvider());

		TreePickerDialog dialog = new MetadataTreePickerDialog(parent.getShell(),
				parameter);

		dialog.addSelectionListener(MetadataPickerDialogListener.ID,
				new MetadataPickerDialogListener(model));

		return new ElementPickerComposite<TreePickerDialog>(parent, SWT.NONE,
				dialog);
	}


	private TableViewer layoutPropertiesControl() {
		Label label = widgetFactory.createLabel(parent, masterBlockId + "." + MetadataPickerActionComboMiniModel.PROPERTY_ACTION_DESCRIPTION);
		label.setToolTipText(label.getText());
		label.setLayoutData(data);

		TableViewer tableViewer = new TableViewer(parent);
		new FormToolkit(parent.getDisplay()).adapt(tableViewer.getControl(), true, true);
		tableViewer.getControl().setLayoutData(data);

		int ops = DND.DROP_COPY  | DND.DROP_MOVE;
		Transfer[] transfers = new Transfer[] { MasterTreeExternalDatatypeTransfer.getInstance()};
		tableViewer.addDragSupport(ops, transfers, new ActionCodeParamterDragListener(tableViewer));

		tableViewer.addDoubleClickListener(new CodeParameterListDoubleClickListener(tableViewer));

		List<CodeParameter> parameterList; 
		if (action != null && action.getAction() != null && action.getAction().getParameterList() != null) {
			parameterList = action.getAction().getParameterList();
		} else {
			parameterList = new ArrayList<CodeParameter>();
		}
		tableViewer.setContentProvider(new ObjectListContentProvider(parameterList));
		tableViewer.setInput(parameterList);

		tableViewer.setLabelProvider(new LabelProvider() {
			public Image getImage(Object element) {
				if(element instanceof CodeParameter){
					CodeParameter codeParameter = (CodeParameter) element;
					PropertyType propertyType = codeParameter.getType();
					String imagePath = null;
					
					switch (propertyType) {
					case LIST:
						imagePath = ICON_PROPERTY_LIST;
						break;
					case STRING:
						imagePath = ICON_PROPERTY_STRING;
						break;
					case LONG:
					case DOUBLE:
					case INTEGER:
						imagePath = ICON_PROPERTY_NUMERIC;
						break;
					case XML:
						imagePath = ICON_PROPERTY_XML;
						break;
					case SQL:
						imagePath = ScriptImageRegistry.ICON_SQL.getId();
						break;
					case FILE:
						imagePath = ScriptImageRegistry.ICON_FILE.getId();
						break;
					default:
						imagePath = ICON_PROPERTY;
					}
					return ImageProvider.createImage(imagePath);
				}
				return null;
			}

			public String getText(Object element) {
				return ((CodeParameter) element).getName().getValue();
			}
		});
		return tableViewer;
	}

	@Override
	protected Control createWidget(Composite parent, Metadata specialized,
			Method method, Object object, boolean readOnly,
			ViewModel externalViewModel, NabuccoMessageManager messageManager,
			String propertyName) {
		throw new UnsupportedOperationException();
	}

}
