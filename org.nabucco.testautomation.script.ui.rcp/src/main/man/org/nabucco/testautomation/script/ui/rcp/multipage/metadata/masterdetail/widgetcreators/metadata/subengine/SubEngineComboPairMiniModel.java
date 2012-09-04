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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.widgetcreators.metadata.subengine;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.GeneralDetailPageViewModel;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.model.MiniViewModel;
import org.nabucco.framework.plugin.base.component.multipage.model.MultiPageEditViewModel;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerCombo;
import org.nabucco.framework.plugin.base.model.ViewModel;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineOperationCode;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.widgetcreators.ObjectListContentProvider;


/**
 * SubEngineCodeMiniModel
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class SubEngineComboPairMiniModel extends MiniViewModel {

    public static final String PROPERTY_VALUE_SUB_ENGINE = "subEngine";

    public static final String PROPERTY_VALUE_SUB_ENGINE_OPERATION = "subEngineOperation";

	public static final String PROPERTY_ACTION_DESCRIPTION = "actionDescription";

    private ElementPickerCombo subEngineOperationCodeCombo;

    private ObjectListContentProvider subEngineOperationContentProvider;

    private ObjectListContentProvider subEngineContentProvider;

    private Metadata metadata;

	private TableViewer propertiesList;

    /**
     * Creates a new {@link SubEngineComboPairMiniModel} instance.
     * 
     * @param parent
     * @param propertiesList 
     * 
     * @param contentProvider
     *            provides Contents for comboBox
     * @param externalViewModel
     *            holds datatypes and must be informed for changes
     * @param metadata
     *            the metadata that contains the SubEngineCode
     */
    public SubEngineComboPairMiniModel(Composite parent,
            ObjectListContentProvider subEngineContentProvider,
            ObjectListContentProvider subEngineOperationContentProvider,
            ElementPickerCombo subEngineOperationCodeCombo, TableViewer propertiesList, ViewModel externalViewModel,
            Metadata metadata) throws ClientException {
        super(externalViewModel, metadata);

        this.subEngineContentProvider = subEngineContentProvider;
        this.subEngineOperationCodeCombo = subEngineOperationCodeCombo;
        this.subEngineOperationContentProvider = subEngineOperationContentProvider;
        this.metadata = metadata;
        this.propertiesList = propertiesList;

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
     * Set the SubEngineCode by its string representation.
     * 
     * @param value
     *            the string representation
     */
    public void setSubEngine(String value) {
        SubEngineCode oldValue = this.metadata.getSubEngine();
        SubEngineCode newValue = null;

        Object[] allElements = this.subEngineContentProvider.getElements(null);

        for (Object element : allElements) {
            if (element instanceof SubEngineCode) {
                SubEngineCode actionCode = (SubEngineCode) element;
                if (actionCode.getName().getValue().equals(value)) {
                    newValue = actionCode;
                    this.metadata.setSubEngine(newValue);
                }
            }
        }
        super.updateProperty(PROPERTY_VALUE_SUB_ENGINE, oldValue, newValue);
        // update SubEngineOperationCode
        super.updateProperty(PROPERTY_VALUE_SUB_ENGINE_OPERATION, this.metadata, null);
        List<SubEngineOperationCode> operationList;
        if (newValue != null && newValue.getOperationList() != null) {
            operationList = newValue.getOperationList();
        } else {
            operationList = new ArrayList<SubEngineOperationCode>();
        }
        this.subEngineOperationContentProvider.inputChanged(null, null, operationList);
        subEngineOperationCodeCombo.inputChanged();
        if (operationList != null && operationList.size() > 0) {
            subEngineOperationCodeCombo.setEnabled(true);
        }
    }

    /**
     * Getter for the SubEngineCode string representation.
     * 
     * @return the string value
     */
    public String getSubEngine() {
        if (this.metadata != null && metadata.getSubEngine() != null) {
            return this.metadata.getSubEngine().getName().getValue();
        }
        return "";
    }

    /**
     * Set the SubEngineCode by its string representation.
     * 
     * @param value
     *            the string representation
     */
    public void setSubEngineOperation(String value) {
        SubEngineOperationCode oldValue = this.metadata.getOperation();
        SubEngineOperationCode newValue = null;

        Object[] allElements = this.subEngineOperationContentProvider.getElements(null);

        for (Object element : allElements) {
            if (element instanceof SubEngineOperationCode) {
                SubEngineOperationCode actionCode = (SubEngineOperationCode) element;
                if (actionCode.getName().getValue().equals(value)) {
                    newValue = actionCode;
                    this.metadata.setOperation(newValue);
                }
            }
        }
        super.updateProperty(PROPERTY_VALUE_SUB_ENGINE, oldValue, newValue);
        this.propertiesList.setInput(metadata.getOperation().getParameterList());
    }

    /**
     * Getter for the SubEngineCode string representation.
     * 
     * @return the string value
     */
    public String getSubEngineOperation() {
        if (this.metadata != null && metadata.getOperation() != null) {
            return this.metadata.getOperation().getName().getValue();
        }
        return "";
    }

    public Metadata getMetadata() {
        return this.metadata;
    }
    

}
