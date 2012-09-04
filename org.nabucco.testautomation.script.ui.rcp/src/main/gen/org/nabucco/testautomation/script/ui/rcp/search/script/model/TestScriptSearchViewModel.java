/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.nabucco.testautomation.script.ui.rcp.search.script.model;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Key;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchViewModel;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;

/**
 * TestScriptSearchViewModel<p/>Search view for datatype TestScript<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-13
 */
public class TestScriptSearchViewModel extends NabuccoComponentSearchViewModel<TestScript> implements
        NabuccoComponentSearchParameter {

    public static final String ID = "org.nabucco.testautomation.script.ui.search.script.TestScriptSearchViewModel";

    private TestScript testScript;

    public static final String PROPERTY_TESTSCRIPT_NAME = "testScriptName";

    public static final String PROPERTY_TESTSCRIPT_IDENTIFICATIONKEY = "testScriptIdentificationKey";

    public static String TITLE = (ID + "Title");

    /**
     * Constructs a new TestScriptSearchViewModel instance.
     *
     * @param viewId the String.
     */
    public TestScriptSearchViewModel(String viewId) {
        super();
        correspondingListView = viewId;
        this.testScript = new TestScript();
    }

    @Override
    public String getSearchModelId() {
        return searchModelId;
    }

    @Override
    public NabuccoComponentSearchParameter getSearchParameter() {
        return this;
    }

    /**
     * Getter for the TestScript.
     *
     * @return the TestScript.
     */
    public TestScript getTestScript() {
        return this.testScript;
    }

    /**
     * Setter for the TestScriptName.
     *
     * @param newName the String.
     */
    public void setTestScriptName(String newName) {
        if (((testScript != null) && (testScript.getName() == null))) {
            Name name = new Name();
            testScript.setName(name);
        }
        String oldVal = testScript.getName().getValue();
        testScript.getName().setValue(newName);
        this.updateProperty(PROPERTY_TESTSCRIPT_NAME, oldVal, newName);
        if (((!oldVal.equals(newName)) && testScript.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            testScript.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the TestScriptName.
     *
     * @return the String.
     */
    public String getTestScriptName() {
        if ((((testScript == null) || (testScript.getName() == null)) || (testScript.getName().getValue() == null))) {
            return "";
        }
        return testScript.getName().getValue();
    }

    /**
     * Setter for the TestScriptIdentificationKey.
     *
     * @param newIdentificationKey the String.
     */
    public void setTestScriptIdentificationKey(String newIdentificationKey) {
        if (((testScript != null) && (testScript.getIdentificationKey() == null))) {
            Key identificationKey = new Key();
            testScript.setIdentificationKey(identificationKey);
        }
        String oldVal = testScript.getIdentificationKey().getValue();
        testScript.getIdentificationKey().setValue(newIdentificationKey);
        this.updateProperty(PROPERTY_TESTSCRIPT_IDENTIFICATIONKEY, oldVal, newIdentificationKey);
        if (((!oldVal.equals(newIdentificationKey)) && testScript.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            testScript.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the TestScriptIdentificationKey.
     *
     * @return the String.
     */
    public String getTestScriptIdentificationKey() {
        if ((((testScript == null) || (testScript.getIdentificationKey() == null)) || (testScript
                .getIdentificationKey().getValue() == null))) {
            return "";
        }
        return testScript.getIdentificationKey().getValue();
    }

    @Override
    public String getId() {
        return TestScriptSearchViewModel.ID;
    }
}
