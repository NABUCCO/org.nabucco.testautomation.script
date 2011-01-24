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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.widgetcreators.fileproperty;

import java.lang.reflect.Method;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.widget.AbstractBaseTypeWidgetCreator;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.widget.BaseTypeWidgetFactory;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.listener.validation.DatatypeModifyListener;
import org.nabucco.framework.plugin.base.layout.ImageProvider;
import org.nabucco.framework.plugin.base.model.ViewModel;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;

import org.nabucco.testautomation.facade.datatype.property.FileProperty;

/**
 * WidgetCreatorForSubEngineActionCode
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class NameContentCombinationWidgetCreator extends
AbstractBaseTypeWidgetCreator<Name> {

	private GridData data;
	private BaseTypeWidgetFactory widgetFactory;
	private Composite parent;
	private FileProperty fileProperty;
	private String masterBlockId;
	private ViewModel externalViewModel;
	
	
	/**
	 * Creates a new {@link NameContentCombinationWidgetCreator} instance.
	 * 
	 * @param nft
	 *            the nabucco form toolkit
	 */
	public NameContentCombinationWidgetCreator(NabuccoFormToolkit nft) {
		super(nft);
	}

	/**
	 * 
	 * @param data 
	 * @param externalViewModel
	 * @param data
	 * @param masterBlockId
	 * @param widgetFactory2
	 */
	public Control[] createWidget(Composite parent, GridData data, FileProperty fileProperty, ViewModel externalViewModel,
			BaseTypeWidgetFactory widgetFactory, String masterBlockId) {

		Control[] result = new Control[2];
		
		this.parent = parent;
		this.data = data;
		this.fileProperty = fileProperty;
		this.masterBlockId = masterBlockId;
		this.externalViewModel = externalViewModel;
		this.widgetFactory = widgetFactory;
		
		NameContentCombinationMiniModel model = new NameContentCombinationMiniModel(parent, externalViewModel, fileProperty);
		result[0] = this.layoutFileChooser(model);
		result[1] = this.layoutFileContentArea(model);
		
		return result;
	}

	private Control layoutFileChooser(NameContentCombinationMiniModel model) {
		NabuccoFormToolkit formToolkit = super.getFormToolkit();
		// Create Label
		Label label = widgetFactory.createLabel(parent, masterBlockId + "." + NameContentCombinationMiniModel.PROPERTY_NAME);
		label.setToolTipText(label.getText());
		label.setLayoutData(data);

		// Create Composite for FileChooser
		FormLayout layout = new FormLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		Composite composite = formToolkit.createComposite(parent, layout);

		// Create TextInput
		Text fileNameTextInput = formToolkit.createTextInput(composite, false);
		fileNameTextInput.setText(fileProperty.getName().getValue());
		FormData textArea = new FormData();
		textArea.left = new FormAttachment(0, 0);
		textArea.right = new FormAttachment(100, -24);
		textArea.top = new FormAttachment(0, 0);
		textArea.bottom = new FormAttachment(100, 0);
		fileNameTextInput.setLayoutData(textArea);

		DataBindingContext bindingContext = new DataBindingContext();
		IObservableValue uiElement = SWTObservables.observeText(fileNameTextInput, SWT.Modify);
		IObservableValue modelElement = BeansObservables.observeValue(model, NameContentCombinationMiniModel.PROPERTY_NAME);
		fileNameTextInput.addModifyListener(new DatatypeModifyListener(this.externalViewModel));
		bindingContext.bindValue(uiElement, modelElement, null, null);
		
		

		// Create Button
		Button button = formToolkit.createFlatButton(composite, ImageProvider.createImage("icons/fileupload.png"));
		FormData area = new FormData();
		area.left = new FormAttachment(fileNameTextInput, 2, SWT.RIGHT);
		area.right = new FormAttachment(100, 0);
		area.top = new FormAttachment(0, 0);
		area.bottom = new FormAttachment(100, 0);
		area.height = 18;
		button.setLayoutData(area);
		button.setBackground(null);
		button.addSelectionListener(new OpenFileDialogListener(fileProperty,
				fileNameTextInput, model));


		return composite;
	}

	private Control layoutFileContentArea(NameContentCombinationMiniModel model) {

		Label label = widgetFactory.createLabel(parent, masterBlockId + "."
				+ NameContentCombinationMiniModel.PROPERTY_CONTENT);
		label.setToolTipText(label.getText());
		label.setLayoutData(data);

		Text result = super.getFormToolkit().createTextarea(parent, false);
		if(fileProperty != null && fileProperty.getContent() != null){
			result.setText(fileProperty.getContent().getValue());
		}
		
		DataBindingContext bindingContext = new DataBindingContext();
		IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
		IObservableValue modelElement = BeansObservables.observeValue(model,
				NameContentCombinationMiniModel.PROPERTY_CONTENT);

		result.addModifyListener(new DatatypeModifyListener(this.externalViewModel));
		bindingContext.bindValue(uiElement, modelElement, null, null);

		return result;
	}


	@Override
	protected Control createWidget(Composite parent, Name specialized,
			Method method, Object object, boolean readOnly, ViewModel externalViewModel,
			NabuccoMessageManager messageManager, String propertyName) {
		throw new UnsupportedOperationException();
	}
}
