/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary.base;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;

/**
 * TestScriptComponent<p/>Abstract superclass of all testscript components<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public abstract class TestScriptComponent extends TestScriptElement implements Datatype {

    private static final long serialVersionUID = 1L;

    /** Constructs a new TestScriptComponent instance. */
    public TestScriptComponent() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the TestScriptComponent.
     */
    protected void cloneObject(TestScriptComponent clone) {
        super.cloneObject(clone);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        return properties;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append(super.toString());
        return appendable.toString();
    }

    @Override
    public abstract TestScriptComponent cloneObject();
}
