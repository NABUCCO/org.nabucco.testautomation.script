/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
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

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;", "m1,1;", "m1,1;", "l0,n;m0,1;" };

    public static final String TESTSCRIPTELEMENTTYPE = "testScriptElementType";

    public static final String TESTSCRIPTELEMENT = "testScriptElement";

    public static final String TESTSCRIPTELEMENTCONTAINER = "testScriptElementContainer";

    public static final String IMPORTELEMENT = "importElement";

    private TestScriptElementType testScriptElementType;

    private TestScriptElement testScriptElement;

    private TestScriptElementContainer testScriptElementContainer;

    private Flag importElement;

    /** Constructs a new ProduceTestScriptElementMsg instance. */
    public ProduceTestScriptElementMsg() {
        super();
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.put(TESTSCRIPTELEMENTTYPE, PropertyDescriptorSupport.createEnumeration(
                TESTSCRIPTELEMENTTYPE, TestScriptElementType.class, 0, PROPERTY_CONSTRAINTS[0],
                false));
        propertyMap.put(TESTSCRIPTELEMENT, PropertyDescriptorSupport.createDatatype(
                TESTSCRIPTELEMENT, TestScriptElement.class, 1, PROPERTY_CONSTRAINTS[1], false,
                PropertyAssociationType.COMPOSITION));
        propertyMap.put(TESTSCRIPTELEMENTCONTAINER, PropertyDescriptorSupport.createDatatype(
                TESTSCRIPTELEMENTCONTAINER, TestScriptElementContainer.class, 2,
                PROPERTY_CONSTRAINTS[2], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(IMPORTELEMENT, PropertyDescriptorSupport.createBasetype(IMPORTELEMENT,
                Flag.class, 3, PROPERTY_CONSTRAINTS[3], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public List<NabuccoProperty> getProperties() {
        List<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(
                ProduceTestScriptElementMsg.getPropertyDescriptor(TESTSCRIPTELEMENTTYPE),
                this.testScriptElementType));
        properties.add(super.createProperty(
                ProduceTestScriptElementMsg.getPropertyDescriptor(TESTSCRIPTELEMENT),
                this.testScriptElement));
        properties.add(super.createProperty(
                ProduceTestScriptElementMsg.getPropertyDescriptor(TESTSCRIPTELEMENTCONTAINER),
                this.testScriptElementContainer));
        properties.add(super.createProperty(
                ProduceTestScriptElementMsg.getPropertyDescriptor(IMPORTELEMENT),
                this.importElement));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(TESTSCRIPTELEMENTTYPE) && (property.getType() == TestScriptElementType.class))) {
            this.setTestScriptElementType(((TestScriptElementType) property.getInstance()));
            return true;
        } else if ((property.getName().equals(TESTSCRIPTELEMENT) && (property.getType() == TestScriptElement.class))) {
            this.setTestScriptElement(((TestScriptElement) property.getInstance()));
            return true;
        } else if ((property.getName().equals(TESTSCRIPTELEMENTCONTAINER) && (property.getType() == TestScriptElementContainer.class))) {
            this.setTestScriptElementContainer(((TestScriptElementContainer) property.getInstance()));
            return true;
        } else if ((property.getName().equals(IMPORTELEMENT) && (property.getType() == Flag.class))) {
            this.setImportElement(((Flag) property.getInstance()));
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
        if ((this.importElement == null)) {
            if ((other.importElement != null))
                return false;
        } else if ((!this.importElement.equals(other.importElement)))
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
        result = ((PRIME * result) + ((this.importElement == null) ? 0 : this.importElement
                .hashCode()));
        return result;
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

    /**
     * Missing description at method getImportElement.
     *
     * @return the Flag.
     */
    public Flag getImportElement() {
        return this.importElement;
    }

    /**
     * Missing description at method setImportElement.
     *
     * @param importElement the Flag.
     */
    public void setImportElement(Flag importElement) {
        this.importElement = importElement;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(ProduceTestScriptElementMsg.class)
                .getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(ProduceTestScriptElementMsg.class)
                .getAllProperties();
    }
}
