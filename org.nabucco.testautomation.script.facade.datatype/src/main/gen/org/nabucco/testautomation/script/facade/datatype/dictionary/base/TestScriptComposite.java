/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary.base;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.property.ListProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;

/**
 * TestScriptComposite<p/>Abstract superclass of all testscript composits<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public abstract class TestScriptComposite extends TestScriptElement implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "testScriptElementList" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,n;" };

    private List<TestScriptElementContainer> testScriptElementList;

    /** Constructs a new TestScriptComposite instance. */
    public TestScriptComposite() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the TestScriptComposite.
     */
    protected void cloneObject(TestScriptComposite clone) {
        super.cloneObject(clone);
        if ((this.testScriptElementList instanceof NabuccoList<?>)) {
            clone.testScriptElementList = ((NabuccoList<TestScriptElementContainer>) this.testScriptElementList)
                    .cloneCollection();
        }
    }

    /**
     * Getter for the TestScriptElementListJPA.
     *
     * @return the List<TestScriptElementContainer>.
     */
    List<TestScriptElementContainer> getTestScriptElementListJPA() {
        if ((this.testScriptElementList == null)) {
            this.testScriptElementList = new NabuccoList<TestScriptElementContainer>(
                    NabuccoCollectionState.LAZY);
        }
        return ((NabuccoList<TestScriptElementContainer>) this.testScriptElementList).getDelegate();
    }

    /**
     * Setter for the TestScriptElementListJPA.
     *
     * @param testScriptElementList the List<TestScriptElementContainer>.
     */
    void setTestScriptElementListJPA(List<TestScriptElementContainer> testScriptElementList) {
        if ((this.testScriptElementList == null)) {
            this.testScriptElementList = new NabuccoList<TestScriptElementContainer>(
                    NabuccoCollectionState.LAZY);
        }
        ((NabuccoList<TestScriptElementContainer>) this.testScriptElementList)
                .setDelegate(testScriptElementList);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new ListProperty<TestScriptElementContainer>(PROPERTY_NAMES[0],
                TestScriptElementContainer.class, PROPERTY_CONSTRAINTS[0],
                this.testScriptElementList));
        return properties;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append(super.toString());
        return appendable.toString();
    }

    @Override
    public abstract TestScriptComposite cloneObject();

    /**
     * Missing description at method getTestScriptElementList.
     *
     * @return the List<TestScriptElementContainer>.
     */
    public List<TestScriptElementContainer> getTestScriptElementList() {
        if ((this.testScriptElementList == null)) {
            this.testScriptElementList = new NabuccoList<TestScriptElementContainer>(
                    NabuccoCollectionState.INITIALIZED);
        }
        return this.testScriptElementList;
    }
}
