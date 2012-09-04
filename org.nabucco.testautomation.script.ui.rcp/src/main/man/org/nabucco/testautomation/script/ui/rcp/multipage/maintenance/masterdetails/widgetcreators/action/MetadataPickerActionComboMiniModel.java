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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.masterdetails.widgetcreators.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.model.MiniViewModel;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerCombo;
import org.nabucco.framework.plugin.base.logging.NabuccoLogMessage;
import org.nabucco.framework.plugin.base.model.ViewModel;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineActionCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineOperationCode;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.masterdetails.widgetcreators.action.action.ObjectListContentProvider;


/**
 * TestScriptContainerMiniModel
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MetadataPickerActionComboMiniModel extends MiniViewModel {

	private Action action;

	private ElementPickerCombo actionCombo;

	private ObjectListContentProvider actionComboContentProvider;

	private String masterBlockId;

	public static final String PROPERTY_METADATA = "metadata";

	public static final String PROPERTY_METADATA_NAME = "metadataName";

	public static final String PROPERTY_ACTION = "action";

	public static final String PROPERTY_ACTION_DESCRIPTION = "actionDescription";
	
	private TableViewer propertiesList;

	/**
	 * Creates a new {@link TestScriptContainerTableMiniModel} instance.
	 * 
	 * @param externalViewModel
	 *            the external view model
	 * @param datatype
	 *            the parent datatype
	 */
	public MetadataPickerActionComboMiniModel(ViewModel externalViewModel, Action datatype, String masterBlockId) {
		super(externalViewModel, datatype);

		if (datatype == null) {
			throw new IllegalArgumentException(
					"Cannot create MetadataPickerMiniModel for Action [null].");
		}

		this.action = datatype;
		this.masterBlockId = masterBlockId;
		super.setInitialized();
	}
	
	public void setPropertiesListViewer(TableViewer tableViewer){
		this.propertiesList = tableViewer;
	}

	/**
	 * Getter for the metadata.
	 * 
	 * @return Returns the metadata.
	 */
	public Metadata getMetadata() {
		return this.action.getMetadata();
	}

	/**
	 * Getter for the metadata.
	 * 
	 * @return Returns the metadata.
	 */
	public String getMetadataName() {
		
		Metadata metadata = this.getMetadata();
		
		if (metadata != null && metadata.getName() != null) {
			return metadata.getIdentificationKey().getValue() + " - " + this.getMetadata().getName().getValue();
		}
		return "";
	}

	/**
	 * Set the metadata in the model.
	 * 
	 * @param newValue
	 *            the metadata to set
	 */
	public void setMetadata(Metadata newValue) {
		Object oldValue = this.getMetadata();

		this.action.setMetadata(newValue);

		super.updateProperty(PROPERTY_METADATA_NAME, oldValue, newValue);
		super.updateProperty(PROPERTY_ACTION, oldValue, newValue);
		super.updateProperty(PROPERTY_ACTION_DESCRIPTION, oldValue, newValue);
		// update action property
		List<SubEngineActionCode> availableActionCodes;
		if (newValue.getOperation() != null && newValue.getOperation().getActionList() != null) {
			availableActionCodes = newValue.getOperation().getActionList();
		} else {
			Activator
			.getDefault()
			.logInfo(
					new NabuccoLogMessage(
							this.getClass(),
							"Metadata ('"
							+ newValue.getName()
							+ ")' without operation set found. Cannot create action dropdownbox."));

			availableActionCodes = new ArrayList<SubEngineActionCode>();
			SubEngineActionCode dummyErrorCode = new SubEngineActionCode();
			dummyErrorCode.setDatatypeState(DatatypeState.TRANSIENT);
			String errorMessage = I18N.i18n(masterBlockId + "." + "MetadataWithoutOperation");
			dummyErrorCode.setDescription(errorMessage);
			dummyErrorCode.setName(errorMessage);
			availableActionCodes.add(dummyErrorCode);
			SubEngineOperationCode subEngineOperationCode = new SubEngineOperationCode();
			subEngineOperationCode.setDatatypeState(DatatypeState.TRANSIENT);
			subEngineOperationCode.getActionList().add(dummyErrorCode);
			newValue.setOperation(subEngineOperationCode);
		}
		this.actionComboContentProvider.inputChanged(null, null, availableActionCodes);
		this.actionCombo.inputChanged();
		if (availableActionCodes != null && availableActionCodes.size() > 0) {
			this.actionCombo.setEnabled(true);
			this.actionCombo.getCombo().select(0);
		}
	}

	/**
	 * Set the SubEngineActionCode by its string representation.
	 * 
	 * @param value
	 *            the string representation
	 */
	public void setAction(String value) {
		SubEngineActionCode oldValue = this.action.getActionCode();
		SubEngineActionCode newValue = null;

		Object[] allElements = this.actionComboContentProvider.getElements(null);

		for (Object element : allElements) {
			if (element instanceof SubEngineActionCode) {
				SubEngineActionCode actionCode = (SubEngineActionCode) element;
				if (actionCode.getName().getValue().equals(value)) {
					newValue = actionCode;
				}
			}
		}
		this.action.setActionCode(newValue);
		super.updateProperty(PROPERTY_ACTION, oldValue, newValue);
		super.updateProperty(PROPERTY_ACTION_DESCRIPTION, oldValue, newValue);
		this.propertiesList.setInput(newValue.getParameterList());
	}

	/**
	 * Getter for the SubEngineActionCode string representation.
	 * 
	 * @return the string value
	 */
	public String getAction() {
		if (this.action != null && this.action.getActionCode() != null) {
			return this.action.getActionCode().getName().toString();
		}
		return "";
	}

	public void setActionCombo(ElementPickerCombo combo) {
		this.actionCombo = combo;
	}

	public void setActionComboContentProvider(ObjectListContentProvider contentProvider) {
		this.actionComboContentProvider = contentProvider;
	}

}
