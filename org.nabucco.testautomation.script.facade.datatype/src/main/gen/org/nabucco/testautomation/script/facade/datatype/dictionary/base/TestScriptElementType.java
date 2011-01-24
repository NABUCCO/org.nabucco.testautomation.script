/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary.base;

import org.nabucco.framework.base.facade.datatype.Enumeration;

/**
 * TestScriptElementType<p/>Enumeration for all possible script element types.<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public enum TestScriptElementType implements Enumeration {
    ACTION("A"),
    BREAK_LOOP("B"),
    CONDITION("C"),
    EXECUTION("E"),
    FOREACH("F"),
    SCRIPT("G"),
    LOOP("H"),
    ASSERTION("I"),
    LOCK("K"),
    LOGGER("L"),
    TEXT_MESSAGE("M"),
    PROPERTY_ACTION("P"), ;

    private String id;

    /**
     * Constructs a new TestScriptElementType instance.
     *
     * @param id the String.
     */
    TestScriptElementType(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getOrdinal() {
        return this.ordinal();
    }

    @Override
    public Enumeration cloneObject() {
        return this;
    }
}
