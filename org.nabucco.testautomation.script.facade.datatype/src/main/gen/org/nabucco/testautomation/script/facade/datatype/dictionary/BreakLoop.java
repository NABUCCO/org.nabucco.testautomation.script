/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;

/**
 * BreakLoop<p/>A condition to break a loop<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public class BreakLoop extends TestScriptComposite implements Datatype {

    private static final long serialVersionUID = 1L;

    /** Constructs a new BreakLoop instance. */
    public BreakLoop() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
        type = TestScriptElementType.BREAK_LOOP;
    }

    /**
     * CloneObject.
     *
     * @param clone the BreakLoop.
     */
    protected void cloneObject(BreakLoop clone) {
        super.cloneObject(clone);
        clone.setType(this.getType());
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
    public BreakLoop cloneObject() {
        BreakLoop clone = new BreakLoop();
        this.cloneObject(clone);
        return clone;
    }
}
