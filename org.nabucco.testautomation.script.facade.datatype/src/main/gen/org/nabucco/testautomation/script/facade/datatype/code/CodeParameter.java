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
package org.nabucco.testautomation.script.facade.datatype.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyType;
import org.nabucco.testautomation.property.facade.datatype.base.Value;

/**
 * CodeParameter<p/>Parameter information for an Operation or Action<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-12-15
 */
public class CodeParameter extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,255;u0,n;m1,1;", "l0,255;u0,n;m0,1;", "m1,1;",
            "l0,n;u0,n;m0,1;", "l0,n;u0,n;m1,1;" };

    public static final String NAME = "name";

    public static final String DESCRIPTION = "description";

    public static final String TYPE = "type";

    public static final String DEFAULTVALUE = "defaultValue";

    public static final String MANDATORY = "mandatory";

    private Name name;

    private Description description;

    private PropertyType type;

    private Value defaultValue;

    private Flag mandatory;

    /** Constructs a new CodeParameter instance. */
    public CodeParameter() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the CodeParameter.
     */
    protected void cloneObject(CodeParameter clone) {
        super.cloneObject(clone);
        if ((this.getName() != null)) {
            clone.setName(this.getName().cloneObject());
        }
        if ((this.getDescription() != null)) {
            clone.setDescription(this.getDescription().cloneObject());
        }
        clone.setType(this.getType());
        if ((this.getDefaultValue() != null)) {
            clone.setDefaultValue(this.getDefaultValue().cloneObject());
        }
        if ((this.getMandatory() != null)) {
            clone.setMandatory(this.getMandatory().cloneObject());
        }
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(NabuccoDatatype.class).getPropertyMap());
        propertyMap.put(NAME,
                PropertyDescriptorSupport.createBasetype(NAME, Name.class, 3, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(DESCRIPTION, PropertyDescriptorSupport.createBasetype(DESCRIPTION, Description.class, 4,
                PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(TYPE, PropertyDescriptorSupport.createEnumeration(TYPE, PropertyType.class, 5,
                PROPERTY_CONSTRAINTS[2], false));
        propertyMap.put(DEFAULTVALUE,
                PropertyDescriptorSupport.createBasetype(DEFAULTVALUE, Value.class, 6, PROPERTY_CONSTRAINTS[3], false));
        propertyMap.put(MANDATORY,
                PropertyDescriptorSupport.createBasetype(MANDATORY, Flag.class, 7, PROPERTY_CONSTRAINTS[4], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(CodeParameter.getPropertyDescriptor(NAME), this.name, null));
        properties.add(super.createProperty(CodeParameter.getPropertyDescriptor(DESCRIPTION), this.description, null));
        properties.add(super.createProperty(CodeParameter.getPropertyDescriptor(TYPE), this.getType(), null));
        properties
                .add(super.createProperty(CodeParameter.getPropertyDescriptor(DEFAULTVALUE), this.defaultValue, null));
        properties.add(super.createProperty(CodeParameter.getPropertyDescriptor(MANDATORY), this.mandatory, null));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(NAME) && (property.getType() == Name.class))) {
            this.setName(((Name) property.getInstance()));
            return true;
        } else if ((property.getName().equals(DESCRIPTION) && (property.getType() == Description.class))) {
            this.setDescription(((Description) property.getInstance()));
            return true;
        } else if ((property.getName().equals(TYPE) && (property.getType() == PropertyType.class))) {
            this.setType(((PropertyType) property.getInstance()));
            return true;
        } else if ((property.getName().equals(DEFAULTVALUE) && (property.getType() == Value.class))) {
            this.setDefaultValue(((Value) property.getInstance()));
            return true;
        } else if ((property.getName().equals(MANDATORY) && (property.getType() == Flag.class))) {
            this.setMandatory(((Flag) property.getInstance()));
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
        final CodeParameter other = ((CodeParameter) obj);
        if ((this.name == null)) {
            if ((other.name != null))
                return false;
        } else if ((!this.name.equals(other.name)))
            return false;
        if ((this.description == null)) {
            if ((other.description != null))
                return false;
        } else if ((!this.description.equals(other.description)))
            return false;
        if ((this.type == null)) {
            if ((other.type != null))
                return false;
        } else if ((!this.type.equals(other.type)))
            return false;
        if ((this.defaultValue == null)) {
            if ((other.defaultValue != null))
                return false;
        } else if ((!this.defaultValue.equals(other.defaultValue)))
            return false;
        if ((this.mandatory == null)) {
            if ((other.mandatory != null))
                return false;
        } else if ((!this.mandatory.equals(other.mandatory)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((PRIME * result) + ((this.description == null) ? 0 : this.description.hashCode()));
        result = ((PRIME * result) + ((this.type == null) ? 0 : this.type.hashCode()));
        result = ((PRIME * result) + ((this.defaultValue == null) ? 0 : this.defaultValue.hashCode()));
        result = ((PRIME * result) + ((this.mandatory == null) ? 0 : this.mandatory.hashCode()));
        return result;
    }

    @Override
    public CodeParameter cloneObject() {
        CodeParameter clone = new CodeParameter();
        this.cloneObject(clone);
        return clone;
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
     * Missing description at method getDescription.
     *
     * @return the Description.
     */
    public Description getDescription() {
        return this.description;
    }

    /**
     * Missing description at method setDescription.
     *
     * @param description the Description.
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * Missing description at method setDescription.
     *
     * @param description the String.
     */
    public void setDescription(String description) {
        if ((this.description == null)) {
            if ((description == null)) {
                return;
            }
            this.description = new Description();
        }
        this.description.setValue(description);
    }

    /**
     * Missing description at method getType.
     *
     * @return the PropertyType.
     */
    public PropertyType getType() {
        return this.type;
    }

    /**
     * Missing description at method setType.
     *
     * @param type the PropertyType.
     */
    public void setType(PropertyType type) {
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
            this.type = PropertyType.valueOf(type);
        }
    }

    /**
     * Missing description at method getDefaultValue.
     *
     * @return the Value.
     */
    public Value getDefaultValue() {
        return this.defaultValue;
    }

    /**
     * Missing description at method setDefaultValue.
     *
     * @param defaultValue the Value.
     */
    public void setDefaultValue(Value defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Missing description at method setDefaultValue.
     *
     * @param defaultValue the String.
     */
    public void setDefaultValue(String defaultValue) {
        if ((this.defaultValue == null)) {
            if ((defaultValue == null)) {
                return;
            }
            this.defaultValue = new Value();
        }
        this.defaultValue.setValue(defaultValue);
    }

    /**
     * Missing description at method getMandatory.
     *
     * @return the Flag.
     */
    public Flag getMandatory() {
        return this.mandatory;
    }

    /**
     * Missing description at method setMandatory.
     *
     * @param mandatory the Flag.
     */
    public void setMandatory(Flag mandatory) {
        this.mandatory = mandatory;
    }

    /**
     * Missing description at method setMandatory.
     *
     * @param mandatory the Boolean.
     */
    public void setMandatory(Boolean mandatory) {
        if ((this.mandatory == null)) {
            if ((mandatory == null)) {
                return;
            }
            this.mandatory = new Flag();
        }
        this.mandatory.setValue(mandatory);
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(CodeParameter.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(CodeParameter.class).getAllProperties();
    }
}
