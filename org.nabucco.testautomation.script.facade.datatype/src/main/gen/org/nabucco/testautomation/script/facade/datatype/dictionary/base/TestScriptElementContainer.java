/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Order;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;

/**
 * TestScriptElementContainer<p/>Container holding a TestScriptElement and its order index<p/>
 *
 * @version 1.0
 * @author sschmidt, PRODYNA AG, 2010-10-03
 */
public class TestScriptElementContainer extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;", "l0,n;m1,1;" };

    public static final String ELEMENT = "element";

    public static final String ORDERINDEX = "orderIndex";

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

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(NabuccoDatatype.class)
                .getPropertyMap());
        propertyMap.put(ELEMENT, PropertyDescriptorSupport.createDatatype(ELEMENT,
                TestScriptElement.class, 2, PROPERTY_CONSTRAINTS[0], false,
                PropertyAssociationType.AGGREGATION));
        propertyMap.put(ORDERINDEX, PropertyDescriptorSupport.createBasetype(ORDERINDEX,
                Order.class, 3, PROPERTY_CONSTRAINTS[1], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty> getProperties() {
        List<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(
                TestScriptElementContainer.getPropertyDescriptor(ELEMENT), this.element, null));
        properties
                .add(super.createProperty(
                        TestScriptElementContainer.getPropertyDescriptor(ORDERINDEX),
                        this.orderIndex, null));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(ELEMENT) && (property.getType() == TestScriptElement.class))) {
            this.setElement(((TestScriptElement) property.getInstance()));
            return true;
        } else if ((property.getName().equals(ORDERINDEX) && (property.getType() == Order.class))) {
            this.setOrderIndex(((Order) property.getInstance()));
            return true;
        }
        return false;
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
            if ((orderIndex == null)) {
                return;
            }
            this.orderIndex = new Order();
        }
        this.orderIndex.setValue(orderIndex);
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(TestScriptElementContainer.class)
                .getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(TestScriptElementContainer.class)
                .getAllProperties();
    }
}
