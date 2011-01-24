/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary.type;

import org.nabucco.framework.base.facade.datatype.Enumeration;

/**
 * PropertyActionType<p/>Enumeration for all possible property action types.<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-09-06
 */
public enum PropertyActionType implements Enumeration {
    /** Copies the value of a property into a existing or new property */
    COPY("CO"),
    /** Sets the value of a property to null */
    CLEAR("CL"),
    /** Deletes an existing property */
    DELETE("DE"),
    /** Sets or overrides the value of an existing property */
    SET("SE"), ;

    private String id;

    /**
     * Constructs a new PropertyActionType instance.
     *
     * @param id the String.
     */
    PropertyActionType(String id) {
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
