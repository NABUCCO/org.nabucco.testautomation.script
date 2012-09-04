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
package org.nabucco.testautomation.script.facade.datatype.dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyReference;
import org.nabucco.testautomation.property.facade.datatype.base.Text;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComponent;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;
import org.nabucco.testautomation.script.facade.datatype.dictionary.type.PropertyActionType;

/**
 * PropertyAction<p/>An action performing operations on properties<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-09-06
 */
public class PropertyAction extends TestScriptComponent implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final TestScriptElementType TYPE_DEFAULT = TestScriptElementType.PROPERTY_ACTION;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;u0,n;m0,1;", "l0,n;u0,n;m0,1;", "l0,n;u0,n;m0,1;",
            "m1,1;" };

    public static final String PROPERTYREF = "propertyRef";

    public static final String VALUE = "value";

    public static final String TARGET = "target";

    public static final String ACTION = "action";

    /** the reference to a property in the context (Needed for: COPY, CLEAR, DELETE, SET) */
    private PropertyReference propertyRef;

    /** The value to set into the referenced Property (Needed for: SET) */
    private Text value;

    /** the reference to the target property in the context (Needed for: COPY) */
    private PropertyReference target;

    /** The type of action to perform on a property */
    protected PropertyActionType action;

    /** Constructs a new PropertyAction instance. */
    public PropertyAction() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
        type = TYPE_DEFAULT;
    }

    /**
     * CloneObject.
     *
     * @param clone the PropertyAction.
     */
    protected void cloneObject(PropertyAction clone) {
        super.cloneObject(clone);
        if ((this.getPropertyRef() != null)) {
            clone.setPropertyRef(this.getPropertyRef().cloneObject());
        }
        if ((this.getValue() != null)) {
            clone.setValue(this.getValue().cloneObject());
        }
        if ((this.getTarget() != null)) {
            clone.setTarget(this.getTarget().cloneObject());
        }
        clone.setAction(this.getAction());
        clone.setType(this.getType());
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(TestScriptComponent.class).getPropertyMap());
        propertyMap.put(PROPERTYREF, PropertyDescriptorSupport.createBasetype(PROPERTYREF, PropertyReference.class, 7,
                PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(VALUE,
                PropertyDescriptorSupport.createBasetype(VALUE, Text.class, 8, PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(TARGET, PropertyDescriptorSupport.createBasetype(TARGET, PropertyReference.class, 9,
                PROPERTY_CONSTRAINTS[2], false));
        propertyMap.put(ACTION, PropertyDescriptorSupport.createEnumeration(ACTION, PropertyActionType.class, 10,
                PROPERTY_CONSTRAINTS[3], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(PropertyAction.getPropertyDescriptor(PROPERTYREF), this.propertyRef, null));
        properties.add(super.createProperty(PropertyAction.getPropertyDescriptor(VALUE), this.value, null));
        properties.add(super.createProperty(PropertyAction.getPropertyDescriptor(TARGET), this.target, null));
        properties.add(super.createProperty(PropertyAction.getPropertyDescriptor(ACTION), this.getAction(), null));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(PROPERTYREF) && (property.getType() == PropertyReference.class))) {
            this.setPropertyRef(((PropertyReference) property.getInstance()));
            return true;
        } else if ((property.getName().equals(VALUE) && (property.getType() == Text.class))) {
            this.setValue(((Text) property.getInstance()));
            return true;
        } else if ((property.getName().equals(TARGET) && (property.getType() == PropertyReference.class))) {
            this.setTarget(((PropertyReference) property.getInstance()));
            return true;
        } else if ((property.getName().equals(ACTION) && (property.getType() == PropertyActionType.class))) {
            this.setAction(((PropertyActionType) property.getInstance()));
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
        final PropertyAction other = ((PropertyAction) obj);
        if ((this.propertyRef == null)) {
            if ((other.propertyRef != null))
                return false;
        } else if ((!this.propertyRef.equals(other.propertyRef)))
            return false;
        if ((this.value == null)) {
            if ((other.value != null))
                return false;
        } else if ((!this.value.equals(other.value)))
            return false;
        if ((this.target == null)) {
            if ((other.target != null))
                return false;
        } else if ((!this.target.equals(other.target)))
            return false;
        if ((this.action == null)) {
            if ((other.action != null))
                return false;
        } else if ((!this.action.equals(other.action)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.propertyRef == null) ? 0 : this.propertyRef.hashCode()));
        result = ((PRIME * result) + ((this.value == null) ? 0 : this.value.hashCode()));
        result = ((PRIME * result) + ((this.target == null) ? 0 : this.target.hashCode()));
        result = ((PRIME * result) + ((this.action == null) ? 0 : this.action.hashCode()));
        return result;
    }

    @Override
    public PropertyAction cloneObject() {
        PropertyAction clone = new PropertyAction();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * the reference to a property in the context (Needed for: COPY, CLEAR, DELETE, SET)
     *
     * @return the PropertyReference.
     */
    public PropertyReference getPropertyRef() {
        return this.propertyRef;
    }

    /**
     * the reference to a property in the context (Needed for: COPY, CLEAR, DELETE, SET)
     *
     * @param propertyRef the PropertyReference.
     */
    public void setPropertyRef(PropertyReference propertyRef) {
        this.propertyRef = propertyRef;
    }

    /**
     * the reference to a property in the context (Needed for: COPY, CLEAR, DELETE, SET)
     *
     * @param propertyRef the String.
     */
    public void setPropertyRef(String propertyRef) {
        if ((this.propertyRef == null)) {
            if ((propertyRef == null)) {
                return;
            }
            this.propertyRef = new PropertyReference();
        }
        this.propertyRef.setValue(propertyRef);
    }

    /**
     * The value to set into the referenced Property (Needed for: SET)
     *
     * @return the Text.
     */
    public Text getValue() {
        return this.value;
    }

    /**
     * The value to set into the referenced Property (Needed for: SET)
     *
     * @param value the Text.
     */
    public void setValue(Text value) {
        this.value = value;
    }

    /**
     * The value to set into the referenced Property (Needed for: SET)
     *
     * @param value the String.
     */
    public void setValue(String value) {
        if ((this.value == null)) {
            if ((value == null)) {
                return;
            }
            this.value = new Text();
        }
        this.value.setValue(value);
    }

    /**
     * the reference to the target property in the context (Needed for: COPY)
     *
     * @return the PropertyReference.
     */
    public PropertyReference getTarget() {
        return this.target;
    }

    /**
     * the reference to the target property in the context (Needed for: COPY)
     *
     * @param target the PropertyReference.
     */
    public void setTarget(PropertyReference target) {
        this.target = target;
    }

    /**
     * the reference to the target property in the context (Needed for: COPY)
     *
     * @param target the String.
     */
    public void setTarget(String target) {
        if ((this.target == null)) {
            if ((target == null)) {
                return;
            }
            this.target = new PropertyReference();
        }
        this.target.setValue(target);
    }

    /**
     * The type of action to perform on a property
     *
     * @return the PropertyActionType.
     */
    public PropertyActionType getAction() {
        return this.action;
    }

    /**
     * The type of action to perform on a property
     *
     * @param action the PropertyActionType.
     */
    public void setAction(PropertyActionType action) {
        this.action = action;
    }

    /**
     * The type of action to perform on a property
     *
     * @param action the String.
     */
    public void setAction(String action) {
        if ((action == null)) {
            this.action = null;
        } else {
            this.action = PropertyActionType.valueOf(action);
        }
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(PropertyAction.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(PropertyAction.class).getAllProperties();
    }
}
