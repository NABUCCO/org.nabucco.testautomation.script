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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.widgetcreators.metadata.subengine;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
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
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerCombo;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerComboParameter;
import org.nabucco.framework.plugin.base.layout.ImageProvider;
import org.nabucco.framework.plugin.base.model.ViewModel;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.testautomation.script.facade.datatype.code.CodeParameter;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineOperationCode;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.images.ScriptImageRegistry;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.widgetcreators.ObjectListContentProvider;

import org.nabucco.testautomation.facade.datatype.property.base.PropertyType;

/**
 * WidgetCreatorForSubEngineActionCode
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class WidgetCreatorForSubEngineComboPair extends AbstractBaseTypeWidgetCreator<List<SubEngineCode>> {

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
	private Metadata metadata;
	private List<SubEngineCode> subEngineCodeList;

	/**
	 * Creates a new {@link WidgetCreatorForSubEngineComboPair} instance.
	 * 
	 * @param nft
	 *            the nabucco form toolkit
	 */
	public WidgetCreatorForSubEngineComboPair(Composite parent, BaseTypeWidgetFactory widgetFactory, GridData data, NabuccoFormToolkit nft,
			ViewModel externalViewModel, String masterBlockId, Metadata metadata, List<SubEngineCode> subEngineCodeList) {
		super(nft);
		this.parent = parent;
		this.widgetFactory = widgetFactory;
		this.data = data;
		this.externalViewModel = externalViewModel;
		this.masterBlockId = masterBlockId;
		this.metadata = metadata;
		this.subEngineCodeList = subEngineCodeList;
	}

	/**
	 * 
	 * @param externalViewModel
	 * @param data
	 * @param masterBlockId
	 * @param widgetFactory2
	 */
	public Control[] createWidget() {

		Control[] result = new Control[3];


		// Create Content Providers
		ObjectListContentProvider subEngineCodeContentProvider = new ObjectListContentProvider(subEngineCodeList);
		ElementPickerComboParameter subEngineCodeParameter = new ElementPickerComboParameter(subEngineCodeContentProvider, new SubEngineCodeLabelProvider());

		List<SubEngineOperationCode> subEngineOperationCodeList;
		if (metadata.getSubEngine() != null) {
			subEngineOperationCodeList = metadata.getSubEngine().getOperationList();
		} else {
			subEngineOperationCodeList = new ArrayList<SubEngineOperationCode>();
		}
		ObjectListContentProvider subEngineOperationCodeContentProvider = new ObjectListContentProvider(subEngineOperationCodeList);
		ElementPickerComboParameter subEngineOperationCodeParameter = new ElementPickerComboParameter(subEngineOperationCodeContentProvider,
				new SubEngineOperationCodeLabelProvider());

		// Create Labels and Widgets

		ElementPickerCombo subEngineCodeCombo = layoutSubEngineCodeCombo(subEngineCodeParameter);
		result[0] = subEngineCodeCombo;

		ElementPickerCombo subEngineOperationCodeCombo = layoutSubEngineOperationCodeCombo(subEngineOperationCodeParameter);
		result[1] = subEngineOperationCodeCombo;

		TableViewer propertiesList = layoutPropertiesControl();
		result[2] = propertiesList.getControl(); 


		// Create MiniModel
		SubEngineComboPairMiniModel model = new SubEngineComboPairMiniModel(parent, subEngineCodeContentProvider, subEngineOperationCodeContentProvider,
				subEngineOperationCodeCombo, propertiesList, externalViewModel, metadata);

		// Bind
		DataBindingContext bindingContext = new DataBindingContext();
		IObservableValue uiElement = SWTObservables.observeSelection(subEngineCodeCombo.getCombo());
		IObservableValue modelElement = BeansObservables.observeValue(model, SubEngineComboPairMiniModel.PROPERTY_VALUE_SUB_ENGINE);
		bindingContext.bindValue(uiElement, modelElement, null, null);

		bindingContext = new DataBindingContext();
		uiElement = SWTObservables.observeSelection(subEngineOperationCodeCombo.getCombo());
		modelElement = BeansObservables.observeValue(model, SubEngineComboPairMiniModel.PROPERTY_VALUE_SUB_ENGINE_OPERATION);
		bindingContext.bindValue(uiElement, modelElement, null, null);

		// Add listeners
		subEngineCodeCombo.addSelectionListener(new SubEngineCodeComboBoxHandler(model));
		subEngineOperationCodeCombo.addSelectionListener(new SubEngineOperationCodeComboBoxHandler(model));
		return result;
	}



	private ElementPickerCombo layoutSubEngineCodeCombo(ElementPickerComboParameter subEngineCodeParameter) {
		Label label = widgetFactory.createLabel(parent, masterBlockId + "." + SubEngineComboPairMiniModel.PROPERTY_VALUE_SUB_ENGINE);
		label.setToolTipText(label.getText());
		label.setLayoutData(data);
		ElementPickerCombo subEngineCodeCombo = super.getFormToolkit().createElementPickerCombo(parent, subEngineCodeParameter, false, false);
		return subEngineCodeCombo;
	}

	private ElementPickerCombo layoutSubEngineOperationCodeCombo(ElementPickerComboParameter subEngineOperationCodeParameter) {
		Label label = widgetFactory.createLabel(parent, masterBlockId + "." + SubEngineComboPairMiniModel.PROPERTY_VALUE_SUB_ENGINE_OPERATION);
		label.setToolTipText(label.getText());
		label.setLayoutData(data);
		boolean readOnly = false;
		if (metadata.getSubEngine() == null) {
			readOnly = true;
		}
		ElementPickerCombo subEngineOperationCodeCombo = super.getFormToolkit().createElementPickerCombo(parent, subEngineOperationCodeParameter, readOnly,
				false);
		return subEngineOperationCodeCombo;
	}

	private TableViewer layoutPropertiesControl() {
		Label label = widgetFactory.createLabel(parent, masterBlockId + "." + SubEngineComboPairMiniModel.PROPERTY_ACTION_DESCRIPTION);
		label.setToolTipText(label.getText());
		label.setLayoutData(data);

		TableViewer tableViewer = new TableViewer(parent);
		new FormToolkit(parent.getDisplay()).adapt(tableViewer.getControl(), true, true);
		tableViewer.getControl().setLayoutData(data);

		int ops = DND.DROP_COPY  | DND.DROP_MOVE;
		Transfer[] transfers = new Transfer[] { MasterTreeExternalDatatypeTransfer.getInstance()};
		tableViewer.addDragSupport(ops, transfers, new SubEngineOperationParamterDragListener(tableViewer));

		tableViewer.addDoubleClickListener(new CodeParameterListDoubleClickListener(tableViewer));

		List<CodeParameter> parameterList; 
		if (metadata.getOperation() != null && metadata.getOperation().getParameterList() != null) {
			parameterList = metadata.getOperation().getParameterList();
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
					case STRING:
						imagePath = ICON_PROPERTY_STRING;
					case LONG:
					case DOUBLE:
					case INTEGER:
						imagePath = ICON_PROPERTY_NUMERIC;
					case XML:
						imagePath = ICON_PROPERTY_XML;
					case SQL:
						imagePath = ScriptImageRegistry.ICON_SQL.getId();
					case FILE:
						imagePath = ScriptImageRegistry.ICON_FILE.getId();
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
	protected Control createWidget(Composite parent, List<SubEngineCode> specialized, Method method, Object object, boolean readOnly,
			ViewModel externalViewModel, NabuccoMessageManager messageManager, String propertyName) {
		throw new UnsupportedOperationException();
	}
}
