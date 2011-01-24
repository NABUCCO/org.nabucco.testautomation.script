/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.search.script.model;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
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
public class TestScriptSearchViewModel extends NabuccoComponentSearchViewModel<TestScript>
        implements NabuccoComponentSearchParameter {

    public static final String ID = "org.nabucco.testautomation.script.ui.search.script.TestScriptSearchViewModel";

    private TestScript testScript;

    public static final String PROPERTY_TESTSCRIPT_NAME = "testScriptName";

    public static final String PROPERTY_TESTSCRIPT_DESCRIPTION = "testScriptDescription";

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
        if (((!oldVal.equals(newName)) && testScript.getDatatypeState().equals(
                DatatypeState.PERSISTENT))) {
            testScript.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the TestScriptName.
     *
     * @return the String.
     */
    public String getTestScriptName() {
        if ((((testScript == null) || (testScript.getName() == null)) || (testScript.getName()
                .getValue() == null))) {
            return "";
        }
        return testScript.getName().getValue();
    }

    /**
     * Setter for the TestScriptDescription.
     *
     * @param newDescription the String.
     */
    public void setTestScriptDescription(String newDescription) {
        if (((testScript != null) && (testScript.getDescription() == null))) {
            Description description = new Description();
            testScript.setDescription(description);
        }
        String oldVal = testScript.getDescription().getValue();
        testScript.getDescription().setValue(newDescription);
        this.updateProperty(PROPERTY_TESTSCRIPT_DESCRIPTION, oldVal, newDescription);
        if (((!oldVal.equals(newDescription)) && testScript.getDatatypeState().equals(
                DatatypeState.PERSISTENT))) {
            testScript.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the TestScriptDescription.
     *
     * @return the String.
     */
    public String getTestScriptDescription() {
        if ((((testScript == null) || (testScript.getDescription() == null)) || (testScript
                .getDescription().getValue() == null))) {
            return "";
        }
        return testScript.getDescription().getValue();
    }

    @Override
    public String getId() {
        return TestScriptSearchViewModel.ID;
    }
}
