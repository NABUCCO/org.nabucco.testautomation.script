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
package org.nabucco.testautomation.script.facade.datatype.dictionary.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.testautomation.property.facade.datatype.PropertyList;
import org.nabucco.testautomation.property.facade.datatype.base.TestAutomationDatatype;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;

/**
 * TestScriptElement<p/>Abstract superclass of all testscript elements<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public abstract class TestScriptElement extends TestAutomationDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;", "l0,255;u0,n;m1,1;", "m0,1;" };

    public static final String TYPE = "type";

    public static final String NAME = "name";

    public static final String PROPERTYLIST = "propertyList";

    protected TestScriptElementType type;

    private Name name;

    private PropertyList propertyList;

    private Long propertyListRefId;

    /** Constructs a new TestScriptElement instance. */
    public TestScriptElement() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the TestScriptElement.
     */
    protected void cloneObject(TestScriptElement clone) {
        super.cloneObject(clone);
        clone.setType(this.getType());
        if ((this.getName() != null)) {
            clone.setName(this.getName().cloneObject());
        }
        if ((this.getPropertyList() != null)) {
            clone.setPropertyList(this.getPropertyList().cloneObject());
        }
        if ((this.getPropertyListRefId() != null)) {
            clone.setPropertyListRefId(this.getPropertyListRefId());
        }
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(TestAutomationDatatype.class).getPropertyMap());
        propertyMap.put(TYPE, PropertyDescriptorSupport.createEnumeration(TYPE, TestScriptElementType.class, 4,
                PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(NAME,
                PropertyDescriptorSupport.createBasetype(NAME, Name.class, 5, PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(PROPERTYLIST, PropertyDescriptorSupport.createDatatype(PROPERTYLIST, PropertyList.class, 6,
                PROPERTY_CONSTRAINTS[2], false, PropertyAssociationType.COMPONENT));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(TestScriptElement.getPropertyDescriptor(TYPE), this.getType(), null));
        properties.add(super.createProperty(TestScriptElement.getPropertyDescriptor(NAME), this.name, null));
        properties.add(super.createProperty(TestScriptElement.getPropertyDescriptor(PROPERTYLIST),
                this.getPropertyList(), this.propertyListRefId));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(TYPE) && (property.getType() == TestScriptElementType.class))) {
            this.setType(((TestScriptElementType) property.getInstance()));
            return true;
        } else if ((property.getName().equals(NAME) && (property.getType() == Name.class))) {
            this.setName(((Name) property.getInstance()));
            return true;
        } else if ((property.getName().equals(PROPERTYLIST) && (property.getType() == PropertyList.class))) {
            this.setPropertyList(((PropertyList) property.getInstance()));
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
        final TestScriptElement other = ((TestScriptElement) obj);
        if ((this.type == null)) {
            if ((other.type != null))
                return false;
        } else if ((!this.type.equals(other.type)))
            return false;
        if ((this.name == null)) {
            if ((other.name != null))
                return false;
        } else if ((!this.name.equals(other.name)))
            return false;
        if ((this.propertyList == null)) {
            if ((other.propertyList != null))
                return false;
        } else if ((!this.propertyList.equals(other.propertyList)))
            return false;
        if ((this.propertyListRefId == null)) {
            if ((other.propertyListRefId != null))
                return false;
        } else if ((!this.propertyListRefId.equals(other.propertyListRefId)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.type == null) ? 0 : this.type.hashCode()));
        result = ((PRIME * result) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((PRIME * result) + ((this.propertyList == null) ? 0 : this.propertyList.hashCode()));
        result = ((PRIME * result) + ((this.propertyListRefId == null) ? 0 : this.propertyListRefId.hashCode()));
        return result;
    }

    @Override
    public abstract TestScriptElement cloneObject();

    /**
     * Missing description at method getType.
     *
     * @return the TestScriptElementType.
     */
    public TestScriptElementType getType() {
        return this.type;
    }

    /**
     * Missing description at method setType.
     *
     * @param type the TestScriptElementType.
     */
    public void setType(TestScriptElementType type) {
        this.type = type;
    }

    /**
     * Missing description at method setType.
     *
     * @param type the String.
     */
    public void setType(String type) {
        if ((type == null)) {
            this.type = null;
        } else {
            this.type = TestScriptElementType.valueOf(type);
        }
    }

    /**
     * Missing description at method getName.
     *
     * @return the Name.
     */
    public Name getName() {
        return this.name;
    }

    /**
     * Missing description at method setName.
     *
     * @param name the Name.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Missing description at method setName.
     *
     * @param name the String.
     */
    public void setName(String name) {
        if ((this.name == null)) {
            if ((name == null)) {
                return;
            }
            this.name = new Name();
        }
        this.name.setValue(name);
    }

    /**
     * Missing description at method setPropertyList.
     *
     * @param propertyList the PropertyList.
     */
    public void setPropertyList(PropertyList propertyList) {
        this.propertyList = propertyList;
        if ((propertyList != null)) {
            this.setPropertyListRefId(propertyList.getId());
        } else {
            this.setPropertyListRefId(null);
        }
    }

    /**
     * Missing description at method getPropertyList.
     *
     * @return the PropertyList.
     */
    public PropertyList getPropertyList() {
        return this.propertyList;
    }

    /**
     * Getter for the PropertyListRefId.
     *
     * @return the Long.
     */
    public Long getPropertyListRefId() {
        return this.propertyListRefId;
    }

    /**
     * Setter for the PropertyListRefId.
     *
     * @param propertyListRefId the Long.
     */
    public void setPropertyListRefId(Long propertyListRefId) {
        this.propertyListRefId = propertyListRefId;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(TestScriptElement.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(TestScriptElement.class).getAllProperties();
    }
}
