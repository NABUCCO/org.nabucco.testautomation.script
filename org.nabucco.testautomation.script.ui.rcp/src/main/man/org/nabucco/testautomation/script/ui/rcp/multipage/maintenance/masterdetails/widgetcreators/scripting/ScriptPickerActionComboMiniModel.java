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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.masterdetails.widgetcreators.scripting;

import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.model.MiniViewModel;
import org.nabucco.framework.plugin.base.model.ViewModel;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Assertion;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Function;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;

/**
 * ScriptPickerActionComboMiniModel
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ScriptPickerActionComboMiniModel extends MiniViewModel {

    private TestScriptElement testScriptElement;

    public static final String SCRIPT = "script";

    public static final String SCRIPT_NAME = "scriptName";

    /**
     * Creates a new {@link ScriptPickerActionComboMiniModel} instance.
     * 
     * @param externalViewModel
     *            the external view model
     * @param datatype
     *            the parent datatype
     */
    public ScriptPickerActionComboMiniModel(ViewModel externalViewModel, TestScriptElement testScriptElement) {
        super(externalViewModel, testScriptElement);

        if (testScriptElement == null) {
            throw new IllegalArgumentException(
                    "Cannot create ScriptPickerActionComboMiniModel for TestScriptElement [null].");
        }

        this.testScriptElement = testScriptElement;
        super.setInitialized();
    }

    /**
     * Getter for the {@link Script}.
     * 
     * @return Returns the script.
     */
    public Script getScript() {

        switch (this.testScriptElement.getType()) {
        case ASSERTION:
            return ((Assertion) this.testScriptElement).getAssertionScript();
        case FUNCTION:
            return ((Function) this.testScriptElement).getScript();
        }
        return null;
    }

    /**
     * Getter for the script name.
     * 
     * @return Returns the name of the script.
     */
    public String getScriptName() {

        Script script = getScript();

        if (script != null && script.getName() != null) {
            return script.getName().getValue();
        }
        return "";
    }

    /**
     * Sets the script into the model.
     * 
     * @param newValue
     *            the script to set
     */
    public void setScript(Script newValue) {
        Object oldValue = this.getScript();

        switch (this.testScriptElement.getType()) {
        case ASSERTION:
            ((Assertion) this.testScriptElement).setAssertionScript(newValue);
            break;
        case FUNCTION:
            ((Function) this.testScriptElement).setScript(newValue);
            break;
        }

        super.updateProperty(SCRIPT, oldValue, newValue);
        super.updateProperty(SCRIPT_NAME, oldValue, newValue);
    }

}
