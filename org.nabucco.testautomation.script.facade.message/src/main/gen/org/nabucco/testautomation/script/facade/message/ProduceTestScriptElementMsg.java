/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.message;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.EnumProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;

/**
 * ProduceTestScriptElementMsg<p/>Message for producing any TestScriptElement<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-26
 */
public class ProduceTestScriptElementMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "testScriptElementType", "testScriptElement",
            "testScriptElementContainer" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;", "m1,1;", "m1,1;" };

    private TestScriptElementType testScriptElementType;

    private TestScriptElement testScriptElement;

    private TestScriptElementContainer testScriptElementContainer;

    /** Constructs a new ProduceTestScriptElementMsg instance. */
    public ProduceTestScriptElementMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new EnumProperty<TestScriptElementType>(PROPERTY_NAMES[0],
                TestScriptElementType.class, PROPERTY_CONSTRAINTS[0], this.testScriptElementType));
        properties.add(new DatatypeProperty<TestScriptElement>(PROPERTY_NAMES[1],
                TestScriptElement.class, PROPERTY_CONSTRAINTS[1], this.testScriptElement));
        properties.add(new DatatypeProperty<TestScriptElementContainer>(PROPERTY_NAMES[2],
                TestScriptElementContainer.class, PROPERTY_CONSTRAINTS[2],
                this.testScriptElementContainer));
        return properties;
    }

    @Override
    public boolean equals(Object obj) {
        if ((this == obj)) {
            return true;
        }
        if ((obj == null)) {
            return false;
        }
        if ((this.getClass() != obj.getClass())) {
            return false;
        }
        if ((!super.equals(obj))) {
            return false;
        }
        final ProduceTestScriptElementMsg other = ((ProduceTestScriptElementMsg) obj);
        if ((this.testScriptElementType == null)) {
            if ((other.testScriptElementType != null))
                return false;
        } else if ((!this.testScriptElementType.equals(other.testScriptElementType)))
            return false;
        if ((this.testScriptElement == null)) {
            if ((other.testScriptElement != null))
                return false;
        } else if ((!this.testScriptElement.equals(other.testScriptElement)))
            return false;
        if ((this.testScriptElementContainer == null)) {
            if ((other.testScriptElementContainer != null))
                return false;
        } else if ((!this.testScriptElementContainer.equals(other.testScriptElementContainer)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.testScriptElementType == null) ? 0
                : this.testScriptElementType.hashCode()));
        result = ((PRIME * result) + ((this.testScriptElement == null) ? 0 : this.testScriptElement
                .hashCode()));
        result = ((PRIME * result) + ((this.testScriptElementContainer == null) ? 0
                : this.testScriptElementContainer.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<ProduceTestScriptElementMsg>\n");
        appendable.append(super.toString());
        appendable
                .append((("<testScriptElementType>" + this.testScriptElementType) + "</testScriptElementType>\n"));
        appendable
                .append((("<testScriptElement>" + this.testScriptElement) + "</testScriptElement>\n"));
        appendable
                .append((("<testScriptElementContainer>" + this.testScriptElementContainer) + "</testScriptElementContainer>\n"));
        appendable.append("</ProduceTestScriptElementMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getTestScriptElementType.
     *
     * @return the TestScriptElementType.
     */
    public TestScriptElementType getTestScriptElementType() {
        return this.testScriptElementType;
    }

    /**
     * Missing description at method setTestScriptElementType.
     *
     * @param testScriptElementType the TestScriptElementType.
     */
    public void setTestScriptElementType(TestScriptElementType testScriptElementType) {
        this.testScriptElementType = testScriptElementType;
    }

    /**
     * Missing description at method getTestScriptElement.
     *
     * @return the TestScriptElement.
     */
    public TestScriptElement getTestScriptElement() {
        return this.testScriptElement;
    }

    /**
     * Missing description at method setTestScriptElement.
     *
     * @param testScriptElement the TestScriptElement.
     */
    public void setTestScriptElement(TestScriptElement testScriptElement) {
        this.testScriptElement = testScriptElement;
    }

    /**
     * Missing description at method getTestScriptElementContainer.
     *
     * @return the TestScriptElementContainer.
     */
    public TestScriptElementContainer getTestScriptElementContainer() {
        return this.testScriptElementContainer;
    }

    /**
     * Missing description at method setTestScriptElementContainer.
     *
     * @param testScriptElementContainer the TestScriptElementContainer.
     */
    public void setTestScriptElementContainer(TestScriptElementContainer testScriptElementContainer) {
        this.testScriptElementContainer = testScriptElementContainer;
    }
}
