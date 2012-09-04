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
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.widget.AbstractBaseTypeWidgetCreator;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.widget.BaseTypeWidgetFactory;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.listener.validation.DatatypeModifyListener;
import org.nabucco.framework.plugin.base.layout.ImageProvider;
import org.nabucco.framework.plugin.base.model.ViewModel;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.testautomation.property.facade.datatype.FileProperty;
import org.nabucco.testautomation.property.ui.rcp.images.PropertyImageRegistry;

/**
 * NameContentCombinationWidgetCreator
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

	private boolean readOnly;
	
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
			BaseTypeWidgetFactory widgetFactory, String masterBlockId, boolean readOnly) throws ClientException {

		Control[] result = new Control[2];
		
		this.parent = parent;
		this.data = data;
		this.fileProperty = fileProperty;
		this.masterBlockId = masterBlockId;
		this.externalViewModel = externalViewModel;
		this.widgetFactory = widgetFactory;
		this.readOnly = readOnly;
		
		NameContentCombinationMiniModel model = new NameContentCombinationMiniModel(externalViewModel, fileProperty);
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
		
		// Create TextInput
		Text propertyNameTextInput = formToolkit.createTextInput(parent, this.readOnly);
		
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.widthHint = 100;
		propertyNameTextInput.setLayoutData(gridData);
		
		Label label2 = widgetFactory.createLabel(parent, masterBlockId + "." + NameContentCombinationMiniModel.FILE_NAME);
		label2.setToolTipText(label2.getText());
		label2.setLayoutData(data);

		// Create Composite for FileChooser
		FormLayout layout = new FormLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		Composite composite = formToolkit.createComposite(parent, layout);

		Text fileNameTextInput = formToolkit.createTextInput(composite, readOnly);
		
		FormData textArea2 = new FormData();
		textArea2.left = new FormAttachment(0, 0);
		textArea2.right = new FormAttachment(100, -24);
		textArea2.top = new FormAttachment(0, 0);
		textArea2.bottom = new FormAttachment(100, 0);
		fileNameTextInput.setLayoutData(textArea2);
		
		if (fileProperty != null && fileProperty.getName() != null) {
			propertyNameTextInput.setText(fileProperty.getName().getValue());
		}
		
		if (fileProperty != null && fileProperty.getFilename() != null) {
			fileNameTextInput.setText(fileProperty.getFilename().getValue());
		}

		DataBindingContext bindingContext1 = new DataBindingContext();
		DataBindingContext bindingContext2 = new DataBindingContext();
		IObservableValue uiElement1 = SWTObservables.observeText(propertyNameTextInput, SWT.Modify);
		IObservableValue uiElement2 = SWTObservables.observeText(fileNameTextInput, SWT.Modify);
		IObservableValue modelElement1 = BeansObservables.observeValue(model, NameContentCombinationMiniModel.PROPERTY_NAME);
		IObservableValue modelElement2 = BeansObservables.observeValue(model, NameContentCombinationMiniModel.FILE_NAME);
		bindingContext1.bindValue(uiElement1, modelElement1, null, null);
		bindingContext2.bindValue(uiElement2, modelElement2, null, null);
		propertyNameTextInput.addModifyListener(new DatatypeModifyListener(this.externalViewModel));
		fileNameTextInput.addModifyListener(new DatatypeModifyListener(this.externalViewModel));

		// Create Button
		Button button = formToolkit.createFlatButton(composite, ImageProvider.createImage("icons/fileupload.png"));
		button.setEnabled(!this.readOnly);
		FormData area = new FormData();
		area.left = new FormAttachment(fileNameTextInput, 2, SWT.RIGHT);
		area.right = new FormAttachment(100, 0);
		area.top = new FormAttachment(0, 0);
		area.bottom = new FormAttachment(100, 0);
		area.height = 18;
		button.setLayoutData(area);
		button.setBackground(null);
		button.addSelectionListener(new OpenFileChooserDialogListener(fileProperty,
				fileNameTextInput, propertyNameTextInput, model));

		return composite;
	}

	private Control layoutFileContentArea(NameContentCombinationMiniModel model) {

		NabuccoFormToolkit formToolkit = super.getFormToolkit();
		
		Label label = widgetFactory.createLabel(parent, masterBlockId + "."
				+ NameContentCombinationMiniModel.PROPERTY_CONTENT);
		label.setToolTipText(label.getText());
		label.setLayoutData(data);

		// Create Composite for Documentation
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.horizontalSpacing = 1;
		Composite composite = formToolkit.createComposite(parent, layout);

		Text result = super.getFormToolkit().createTextarea(composite, false);
		GridData areaLayoutData = new GridData();
		areaLayoutData.grabExcessHorizontalSpace = true;
		areaLayoutData.grabExcessVerticalSpace = false;;
		areaLayoutData.minimumHeight = 120;
		areaLayoutData.minimumWidth = 120;
		areaLayoutData.heightHint = 120;
		areaLayoutData.horizontalAlignment = SWT.FILL;
		result.setLayoutData(areaLayoutData);
		
		if (fileProperty != null && fileProperty.getContent() != null){
			result.setText(fileProperty.getContent().getValue());
		}
		
		DataBindingContext bindingContext = new DataBindingContext();
		IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
		IObservableValue modelElement = BeansObservables.observeValue(model,
				NameContentCombinationMiniModel.PROPERTY_CONTENT);

		result.addModifyListener(new DatatypeModifyListener(this.externalViewModel));
		bindingContext.bindValue(uiElement, modelElement, null, null);

		// Create Button
		Button button = formToolkit.createFlatButton(composite, 
				ImageProvider.createImage(PropertyImageRegistry.ICON_DETAILS_DIALOG.getId()));
		GridData buttonLayoutData = new GridData();
		buttonLayoutData.verticalAlignment = SWT.TOP;
		buttonLayoutData.grabExcessHorizontalSpace = false;
		buttonLayoutData.grabExcessVerticalSpace = false;
		buttonLayoutData.heightHint = 20;
		buttonLayoutData.widthHint = 20;
		
		button.setLayoutData(buttonLayoutData);
		button.setBackground(null);
		button.addSelectionListener(new OpenFileContentDialogListener(fileProperty, model));

		return composite;
		
		
		
	}

	@Override
	protected Control createWidget(Composite parent, Name specialized,
			Method method, Object object, boolean readOnly, ViewModel externalViewModel,
			NabuccoMessageManager messageManager, String propertyName) {
		throw new UnsupportedOperationException();
	}
	
}
