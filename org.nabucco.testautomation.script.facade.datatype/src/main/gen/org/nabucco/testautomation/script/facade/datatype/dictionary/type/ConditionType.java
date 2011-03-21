/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary.type;

import java.util.Collections;
import java.util.List;
import org.nabucco.framework.base.facade.datatype.Enumeration;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.visitor.Visitor;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;

/**
 * ConditionType<p/>Enumeration for all possible condition types.<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public enum ConditionType implements Enumeration {
    /** true */
    TRUE("T"),
    /** false */
    FALSE("F"),
    /** greater than */
    GT("G"),
    /** lower than */
    LT("L"),
    /** lower than or equals */
    LTE("A"),
    /** greater than or equals */
    GTE("B"),
    /** equals */
    EQUALS("E"),
    /** not equals */
    NOT_EQUALS("N"),
    /** is null */
    IS_NULL("I"),
    /** not null */
    NOT_NULL("O"),
    /** is empty */
    IS_EMPTY("P"),
    /** not empty */
    NOT_EMPTY("Q"),
    /** starts with */
    STARTS_WITH("S"),
    /** ends with */
    ENDS_WITH("U"),
    /** contains */
    CONTAINS("V"), ;

    private String id;

    /**
     * Constructs a new ConditionType instance.
     *
     * @param id the String.
     */
    ConditionType(String id) {
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

    @Override
    public void accept(Visitor visitor) throws VisitorException {
    }

    @Override
    public List<NabuccoProperty> getProperties() {
        return Collections.emptyList();
    }
}
