/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.EnumProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.testautomation.facade.datatype.base.StringValue;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyReference;
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

    private static final String[] PROPERTY_NAMES = { "propertyRef", "value", "target", "action" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m0,1;", "l0,n;m0,1;",
            "l0,n;m0,1;", "m1,1;" };

    /** the reference to a property in the context (Needed for: COPY, CLEAR, DELETE, SET) */
    private PropertyReference propertyRef;

    /** The value to set into the referenced Property (Needed for: SET) */
    private StringValue value;

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
        type = TestScriptElementType.PROPERTY_ACTION;
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

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new BasetypeProperty<PropertyReference>(PROPERTY_NAMES[0],
                PropertyReference.class, PROPERTY_CONSTRAINTS[0], this.propertyRef));
        properties.add(new BasetypeProperty<StringValue>(PROPERTY_NAMES[1], StringValue.class,
                PROPERTY_CONSTRAINTS[1], this.value));
        properties.add(new BasetypeProperty<PropertyReference>(PROPERTY_NAMES[2],
                PropertyReference.class, PROPERTY_CONSTRAINTS[2], this.target));
        properties.add(new EnumProperty<PropertyActionType>(PROPERTY_NAMES[3],
                PropertyActionType.class, PROPERTY_CONSTRAINTS[3], this.action));
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
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<PropertyAction>\n");
        appendable.append(super.toString());
        appendable.append((("<propertyRef>" + this.propertyRef) + "</propertyRef>\n"));
        appendable.append((("<value>" + this.value) + "</value>\n"));
        appendable.append((("<target>" + this.target) + "</target>\n"));
        appendable.append((("<action>" + this.action) + "</action>\n"));
        appendable.append("</PropertyAction>\n");
        return appendable.toString();
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
            this.propertyRef = new PropertyReference();
        }
        this.propertyRef.setValue(propertyRef);
    }

    /**
     * The value to set into the referenced Property (Needed for: SET)
     *
     * @return the StringValue.
     */
    public StringValue getValue() {
        return this.value;
    }

    /**
     * The value to set into the referenced Property (Needed for: SET)
     *
     * @param value the StringValue.
     */
    public void setValue(StringValue value) {
        this.value = value;
    }

    /**
     * The value to set into the referenced Property (Needed for: SET)
     *
     * @param value the String.
     */
    public void setValue(String value) {
        if ((this.value == null)) {
            this.value = new StringValue();
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
}
