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

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.action.MetadataPickerActionComboMiniModel;


/**
 * Allows general enumeration handler to update its model.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class SubEngineActionCodeComboBoxHandler implements SelectionListener {

    private MetadataPickerActionComboMiniModel model;

    /**
     * Creates a new {@link SubEngineActionCodeComboBoxHandler} instance.
     * 
     * @param model
     *            contains enumeration which will be updated
     */
    public SubEngineActionCodeComboBoxHandler(final MetadataPickerActionComboMiniModel model) {
        this.model = model;
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent selectionEvent) {
        // Nothing to do here
    }

    @Override
    public void widgetSelected(SelectionEvent event) {
        if ((event.widget instanceof Combo)) {
            Combo combo = ((Combo) event.widget);
            model.setAction(combo.getText());
        }

    }

}
