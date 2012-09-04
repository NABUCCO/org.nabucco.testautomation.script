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
package org.nabucco.testautomation.script.facade.datatype.dictionary.type;

import java.util.Collections;
import java.util.Set;
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
    CONTAINS("V"),
    /** starts with */
    NOT_STARTS_WITH("W"),
    /** ends with */
    NOT_ENDS_WITH("X"),
    /** contains */
    NOT_CONTAINS("Y"), ;

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
    public Set<NabuccoProperty> getProperties() {
        return Collections.emptySet();
    }

    /**
     * ValueOfId.
     *
     * @param literalId the String.
     * @return the ConditionType.
     */
    public static ConditionType valueOfId(String literalId) {
        for (ConditionType enumeration : ConditionType.values()) {
            if (enumeration.getId().equalsIgnoreCase(literalId)) {
                return enumeration;
            }
        }
        return null;
    }
}
