/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary.type;

import org.nabucco.framework.base.facade.datatype.Enumeration;

/**
 * OperatorType<p/>Enumeration for all possible operator types.<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public enum OperatorType implements Enumeration {
    /** logical and */
    AND("A"),
    /** logical or */
    OR("O"),
    /** no operator */
    NONE("N"), ;

    private String id;

    /**
     * Constructs a new OperatorType instance.
     *
     * @param id the String.
     */
    OperatorType(String id) {
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
