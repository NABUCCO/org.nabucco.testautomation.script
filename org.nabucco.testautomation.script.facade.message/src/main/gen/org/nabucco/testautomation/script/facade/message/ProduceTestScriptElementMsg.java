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
package org.nabucco.testautomation.script.facade.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;", "m1,1;", "m1,1;", "l0,n;u0,n;m0,1;" };

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
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.put(TESTSCRIPTELEMENTTYPE, PropertyDescriptorSupport.createEnumeration(TESTSCRIPTELEMENTTYPE,
                TestScriptElementType.class, 0, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(TESTSCRIPTELEMENT, PropertyDescriptorSupport.createDatatype(TESTSCRIPTELEMENT,
                TestScriptElement.class, 1, PROPERTY_CONSTRAINTS[1], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(TESTSCRIPTELEMENTCONTAINER, PropertyDescriptorSupport.createDatatype(
                TESTSCRIPTELEMENTCONTAINER, TestScriptElementContainer.class, 2, PROPERTY_CONSTRAINTS[2], false,
                PropertyAssociationType.COMPOSITION));
        propertyMap.put(IMPORTELEMENT,
                PropertyDescriptorSupport.createBasetype(IMPORTELEMENT, Flag.class, 3, PROPERTY_CONSTRAINTS[3], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    /** Init. */
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(ProduceTestScriptElementMsg.getPropertyDescriptor(TESTSCRIPTELEMENTTYPE),
                this.getTestScriptElementType()));
        properties.add(super.createProperty(ProduceTestScriptElementMsg.getPropertyDescriptor(TESTSCRIPTELEMENT),
                this.getTestScriptElement()));
        properties.add(super.createProperty(
                ProduceTestScriptElementMsg.getPropertyDescriptor(TESTSCRIPTELEMENTCONTAINER),
                this.getTestScriptElementContainer()));
        properties.add(super.createProperty(ProduceTestScriptElementMsg.getPropertyDescriptor(IMPORTELEMENT),
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
        result = ((PRIME * result) + ((this.testScriptElementType == null) ? 0 : this.testScriptElementType.hashCode()));
        result = ((PRIME * result) + ((this.testScriptElement == null) ? 0 : this.testScriptElement.hashCode()));
        result = ((PRIME * result) + ((this.testScriptElementContainer == null) ? 0 : this.testScriptElementContainer
                .hashCode()));
        result = ((PRIME * result) + ((this.importElement == null) ? 0 : this.importElement.hashCode()));
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
        return PropertyCache.getInstance().retrieve(ProduceTestScriptElementMsg.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(ProduceTestScriptElementMsg.class).getAllProperties();
    }
}
