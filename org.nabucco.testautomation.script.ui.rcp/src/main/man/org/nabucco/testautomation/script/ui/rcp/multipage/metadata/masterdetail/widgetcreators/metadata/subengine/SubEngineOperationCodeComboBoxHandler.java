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

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;

/**
 * Allows general enumeration handler to update its model.
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class SubEngineOperationCodeComboBoxHandler implements SelectionListener {

    private SubEngineComboPairMiniModel model;

    /**
     * Creates a new {@link SubEngineOperationCodeComboBoxHandler} instance.
     * 
     * @param model
     *            contains enumeration which will be updated
     */
    public SubEngineOperationCodeComboBoxHandler(final SubEngineComboPairMiniModel model) {
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
            model.setSubEngineOperation(combo.getText());
        }

    }

}
