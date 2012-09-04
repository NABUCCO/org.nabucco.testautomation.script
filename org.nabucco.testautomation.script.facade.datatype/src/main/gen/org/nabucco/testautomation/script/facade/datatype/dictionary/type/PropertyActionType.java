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
    SET("SE"),
    /** Determines the size of a PropertyList or of a XPathProperty */
    SIZE_OF("SO"),
    /** Concatenates the values of StringProperties */
    CONCAT("CC"),
    /** Adds the values of numeric properties */
    ADD("AD"),
    /** Subtracts the values of numeric properties */
    SUBTRACT("SU"),
    /** Calculates the length of the string representation of the value */
    LENGTH("LE"),
    /** Multiplies the values of numeric properties */
    MULTIPLY("MU"),
    /** Divides the values of numeric properties */
    DIVIDE("DI"),
    /** Calculates the modulo of numeric properties */
    MODULO("MO"),
    /** Removes whitespaces at the beginning and the end of text properties */
    TRIM("TR"),
    /** Returns a substring of text properties */
    SUBSTRING("SS"),
    /** Returns a part of text properties */
    SPLIT("SP"), ;

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
     * @return the PropertyActionType.
     */
    public static PropertyActionType valueOfId(String literalId) {
        for (PropertyActionType enumeration : PropertyActionType.values()) {
            if (enumeration.getId().equalsIgnoreCase(literalId)) {
                return enumeration;
            }
        }
        return null;
    }
}
