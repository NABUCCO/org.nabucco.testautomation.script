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

import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.file.TextFileContent;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.GeneralDetailPageViewModel;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.model.MiniViewModel;
import org.nabucco.framework.plugin.base.component.multipage.model.MultiPageEditViewModel;
import org.nabucco.framework.plugin.base.model.ViewModel;

import org.nabucco.testautomation.facade.datatype.property.FileProperty;

/**
 * SubEngineCodeMiniModel
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class NameContentCombinationMiniModel extends MiniViewModel {

	public static final String PROPERTY_NAME = "name";

	public static final String PROPERTY_CONTENT = "content";

	private FileProperty fileProperty;

	/**
	 * Creates a new {@link NameContentCombinationMiniModel} instance.
	 * 
	 * @param parent
	 * 
	 * @param contentProvider
	 *            provides Contents for comboBox
	 * @param externalViewModel
	 *            holds datatypes and must be informed for changes
	 * @param fileProperty
	 *            the fileProperty that contains the SubEngineCode
	 */
	public NameContentCombinationMiniModel(Composite parent, ViewModel externalViewModel, FileProperty fileProperty) {
		super(externalViewModel, fileProperty);

		this.fileProperty = fileProperty;

		if (externalViewModel instanceof GeneralDetailPageViewModel) {
			GeneralDetailPageViewModel generalDetailPageViewModel = (GeneralDetailPageViewModel) externalViewModel;
			if (generalDetailPageViewModel.getExternalViewModel() instanceof MultiPageEditViewModel) {
				MultiPageEditViewModel multiPageEditViewModel = (MultiPageEditViewModel) generalDetailPageViewModel
				.getExternalViewModel();
				this.addPropertyChangeListener(multiPageEditViewModel.getPropertyDatatype(),
						multiPageEditViewModel);
			}
		}
		super.setInitialized();
	}

	/**
	 * @param value
	 *            the string representation
	 */
	public void setName(String value) {
		Name oldNameValue = this.fileProperty.getName();
		this.fileProperty.setName(value);

		super.updateProperty(PROPERTY_NAME, oldNameValue, fileProperty.getName());
	}

	/**
	 * Getter for the Name string representation.
	 * 
	 * @return the string value
	 */
	public String getName() {
		if (this.fileProperty != null && fileProperty.getName() != null) {
			return this.fileProperty.getName().getValue();
		}
		return "";
	}


	public FileProperty getFileProperty() {
		return this.fileProperty;
	}

	/**
	 * Getter for the SubEngineActionCode.Description string representation.
	 * 
	 * @return the string value
	 */
	public String getContent() {
		if (this.fileProperty != null && this.fileProperty.getContent() != null) {
			return this.fileProperty.getContent().getValue();
		}
		return "";
	}

	/**
	 * Getter for the SubEngineActionCode.Description string representation.
	 * 
	 * @return the string value
	 */
	public void setContent(String string) {
		if (this.fileProperty != null){
			TextFileContent oldValue = fileProperty.getContent();
			if(this.fileProperty.getContent() != null) {
				this.fileProperty.getContent().setValue(string);
			} else {
				this.fileProperty.setContent(new TextFileContent(string));
			}
			super.updateProperty(PROPERTY_CONTENT, oldValue, string);
		}
	}

}
