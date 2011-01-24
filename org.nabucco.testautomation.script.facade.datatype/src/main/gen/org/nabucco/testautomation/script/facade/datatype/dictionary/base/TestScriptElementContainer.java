/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary.base;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Order;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;

/**
 * TestScriptElementContainer<p/>Container holding a TestScriptElement and its order index<p/>
 *
 * @version 1.0
 * @author sschmidt, PRODYNA AG, 2010-10-03
 */
public class TestScriptElementContainer extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "element", "orderIndex" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;", "l0,n;m1,1;" };

    private TestScriptElement element;

    private Order orderIndex;

    /** Constructs a new TestScriptElementContainer instance. */
    public TestScriptElementContainer() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the TestScriptElementContainer.
     */
    protected void cloneObject(TestScriptElementContainer clone) {
        super.cloneObject(clone);
        if ((this.getElement() != null)) {
            clone.setElement(this.getElement().cloneObject());
        }
        if ((this.getOrderIndex() != null)) {
            clone.setOrderIndex(this.getOrderIndex().cloneObject());
        }
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new DatatypeProperty<TestScriptElement>(PROPERTY_NAMES[0],
                TestScriptElement.class, PROPERTY_CONSTRAINTS[0], this.element));
        properties.add(new BasetypeProperty<Order>(PROPERTY_NAMES[1], Order.class,
                PROPERTY_CONSTRAINTS[1], this.orderIndex));
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
        final TestScriptElementContainer other = ((TestScriptElementContainer) obj);
        if ((this.element == null)) {
            if ((other.element != null))
                return false;
        } else if ((!this.element.equals(other.element)))
            return false;
        if ((this.orderIndex == null)) {
            if ((other.orderIndex != null))
                return false;
        } else if ((!this.orderIndex.equals(other.orderIndex)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.element == null) ? 0 : this.element.hashCode()));
        result = ((PRIME * result) + ((this.orderIndex == null) ? 0 : this.orderIndex.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<TestScriptElementContainer>\n");
        appendable.append(super.toString());
        appendable.append((("<element>" + this.element) + "</element>\n"));
        appendable.append((("<orderIndex>" + this.orderIndex) + "</orderIndex>\n"));
        appendable.append("</TestScriptElementContainer>\n");
        return appendable.toString();
    }

    @Override
    public TestScriptElementContainer cloneObject() {
        TestScriptElementContainer clone = new TestScriptElementContainer();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Missing description at method setElement.
     *
     * @param element the TestScriptElement.
     */
    public void setElement(TestScriptElement element) {
        this.element = element;
    }

    /**
     * Missing description at method getElement.
     *
     * @return the TestScriptElement.
     */
    public TestScriptElement getElement() {
        return this.element;
    }

    /**
     * Missing description at method getOrderIndex.
     *
     * @return the Order.
     */
    public Order getOrderIndex() {
        return this.orderIndex;
    }

    /**
     * Missing description at method setOrderIndex.
     *
     * @param orderIndex the Order.
     */
    public void setOrderIndex(Order orderIndex) {
        this.orderIndex = orderIndex;
    }

    /**
     * Missing description at method setOrderIndex.
     *
     * @param orderIndex the Integer.
     */
    public void setOrderIndex(Integer orderIndex) {
        if ((this.orderIndex == null)) {
            this.orderIndex = new Order();
        }
        this.orderIndex.setValue(orderIndex);
    }
}
