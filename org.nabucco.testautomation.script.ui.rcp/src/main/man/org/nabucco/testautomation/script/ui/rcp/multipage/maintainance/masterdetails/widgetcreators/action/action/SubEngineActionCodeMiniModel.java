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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.action.action;

import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.GeneralDetailPageViewModel;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.model.MiniViewModel;
import org.nabucco.framework.plugin.base.component.multipage.model.MultiPageEditViewModel;
import org.nabucco.framework.plugin.base.model.ViewModel;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineActionCode;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;


/**
 * SubEngineActionCodeMiniModel
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class SubEngineActionCodeMiniModel extends MiniViewModel {

    public static final String PROPERTY_VALUE_STRING = "action";

    private ObjectListContentProvider contentProvider;

    private Action action;

    /**
     * Creates a new {@link SubEngineActionCodeMiniModel} instance.
     * 
     * @param value
     *            the initial value
     * @param contentProvider
     *            provides Contents for comboBox
     * @param method
     *            will be called to set the value
     * @param object
     *            object where enumerations is part of
     * @param externalViewModel
     *            holds datatypes and must be informed for changes
     */
    public SubEngineActionCodeMiniModel(ObjectListContentProvider contentProvider,
            ViewModel externalViewModel, Action action) {
        super(externalViewModel, action);

        this.contentProvider = contentProvider;

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
     * Set the SubEngineActionCode by its string representation.
     * 
     * @param value
     *            the string representation
     */
    public void setAction(String value) {
        SubEngineActionCode oldValue = this.action.getAction();
        SubEngineActionCode newValue = null;

        Object[] allElements = this.contentProvider.getElements(null);

        for (Object element : allElements) {
            if (element instanceof SubEngineActionCode) {
                SubEngineActionCode actionCode = (SubEngineActionCode) element;
                if (actionCode.getName().getValue().equals(value)) {
                    newValue = actionCode;
                }
            }
        }
        this.action.setAction(newValue);
        super.updateProperty(PROPERTY_VALUE_STRING, oldValue, newValue);
    }

    /**
     * Getter for the SubEngineActionCode string representation.
     * 
     * @return the string value
     */
    public String getAction() {
        if (this.action != null && this.action.getAction() != null) {
            return this.action.getAction().getName().toString();
        }
        return "";
    }

}
