/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary.type;

import org.nabucco.framework.base.facade.datatype.Enumeration;

/**
 * LoggerLevelType<p/>Enumeration for all possible logger levels.<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public enum LoggerLevelType implements Enumeration {
    /** LoggerLevel warning */
    WARN("W"),
    /** LoggerLevel info */
    INFO("I"),
    /** LoggerLevel fatal */
    FATAL("F"),
    /** LoggerLevel debug */
    DEBUG("D"),
    /** LoggerLevel error */
    ERROR("E"), ;

    private String id;

    /**
     * Constructs a new LoggerLevelType instance.
     *
     * @param id the String.
     */
    LoggerLevelType(String id) {
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
