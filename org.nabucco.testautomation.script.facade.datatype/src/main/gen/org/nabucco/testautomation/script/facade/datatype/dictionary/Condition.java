/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.EnumProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.testautomation.facade.datatype.base.Value;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyReference;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;
import org.nabucco.testautomation.script.facade.datatype.dictionary.type.ConditionType;
import org.nabucco.testautomation.script.facade.datatype.dictionary.type.OperatorType;

/**
 * Condition<p/>A condition<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public class Condition extends TestScriptComposite implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "propertyRef", "value", "valueRef",
            "conditionType", "operator", "isBreakCondition" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m0,1;", "l0,n;m0,1;",
            "l0,n;m0,1;", "m0,1;", "m0,1;", "l0,n;m1,1;" };

    private PropertyReference propertyRef;

    private Value value;

    private PropertyReference valueRef;

    private ConditionType conditionType;

    private OperatorType operator;

    private Flag isBreakCondition;

    /** Constructs a new Condition instance. */
    public Condition() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
        type = TestScriptElementType.CONDITION;
    }

    /**
     * CloneObject.
     *
     * @param clone the Condition.
     */
    protected void cloneObject(Condition clone) {
        super.cloneObject(clone);
        if ((this.getPropertyRef() != null)) {
            clone.setPropertyRef(this.getPropertyRef().cloneObject());
        }
        if ((this.getValue() != null)) {
            clone.setValue(this.getValue().cloneObject());
        }
        if ((this.getValueRef() != null)) {
            clone.setValueRef(this.getValueRef().cloneObject());
        }
        clone.setConditionType(this.getConditionType());
        clone.setOperator(this.getOperator());
        clone.setType(this.getType());
        if ((this.getIsBreakCondition() != null)) {
            clone.setIsBreakCondition(this.getIsBreakCondition().cloneObject());
        }
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
        properties.add(new BasetypeProperty<Value>(PROPERTY_NAMES[1], Value.class,
                PROPERTY_CONSTRAINTS[1], this.value));
        properties.add(new BasetypeProperty<PropertyReference>(PROPERTY_NAMES[2],
                PropertyReference.class, PROPERTY_CONSTRAINTS[2], this.valueRef));
        properties.add(new EnumProperty<ConditionType>(PROPERTY_NAMES[3], ConditionType.class,
                PROPERTY_CONSTRAINTS[3], this.conditionType));
        properties.add(new EnumProperty<OperatorType>(PROPERTY_NAMES[4], OperatorType.class,
                PROPERTY_CONSTRAINTS[4], this.operator));
        properties.add(new BasetypeProperty<Flag>(PROPERTY_NAMES[5], Flag.class,
                PROPERTY_CONSTRAINTS[5], this.isBreakCondition));
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
        final Condition other = ((Condition) obj);
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
        if ((this.valueRef == null)) {
            if ((other.valueRef != null))
                return false;
        } else if ((!this.valueRef.equals(other.valueRef)))
            return false;
        if ((this.conditionType == null)) {
            if ((other.conditionType != null))
                return false;
        } else if ((!this.conditionType.equals(other.conditionType)))
            return false;
        if ((this.operator == null)) {
            if ((other.operator != null))
                return false;
        } else if ((!this.operator.equals(other.operator)))
            return false;
        if ((this.isBreakCondition == null)) {
            if ((other.isBreakCondition != null))
                return false;
        } else if ((!this.isBreakCondition.equals(other.isBreakCondition)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.propertyRef == null) ? 0 : this.propertyRef.hashCode()));
        result = ((PRIME * result) + ((this.value == null) ? 0 : this.value.hashCode()));
        result = ((PRIME * result) + ((this.valueRef == null) ? 0 : this.valueRef.hashCode()));
        result = ((PRIME * result) + ((this.conditionType == null) ? 0 : this.conditionType
                .hashCode()));
        result = ((PRIME * result) + ((this.operator == null) ? 0 : this.operator.hashCode()));
        result = ((PRIME * result) + ((this.isBreakCondition == null) ? 0 : this.isBreakCondition
                .hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<Condition>\n");
        appendable.append(super.toString());
        appendable.append((("<propertyRef>" + this.propertyRef) + "</propertyRef>\n"));
        appendable.append((("<value>" + this.value) + "</value>\n"));
        appendable.append((("<valueRef>" + this.valueRef) + "</valueRef>\n"));
        appendable.append((("<conditionType>" + this.conditionType) + "</conditionType>\n"));
        appendable.append((("<operator>" + this.operator) + "</operator>\n"));
        appendable
                .append((("<isBreakCondition>" + this.isBreakCondition) + "</isBreakCondition>\n"));
        appendable.append("</Condition>\n");
        return appendable.toString();
    }

    @Override
    public Condition cloneObject() {
        Condition clone = new Condition();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Missing description at method getPropertyRef.
     *
     * @return the PropertyReference.
     */
    public PropertyReference getPropertyRef() {
        return this.propertyRef;
    }

    /**
     * Missing description at method setPropertyRef.
     *
     * @param propertyRef the PropertyReference.
     */
    public void setPropertyRef(PropertyReference propertyRef) {
        this.propertyRef = propertyRef;
    }

    /**
     * Missing description at method setPropertyRef.
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
     * Missing description at method getValue.
     *
     * @return the Value.
     */
    public Value getValue() {
        return this.value;
    }

    /**
     * Missing description at method setValue.
     *
     * @param value the Value.
     */
    public void setValue(Value value) {
        this.value = value;
    }

    /**
     * Missing description at method setValue.
     *
     * @param value the String.
     */
    public void setValue(String value) {
        if ((this.value == null)) {
            this.value = new Value();
        }
        this.value.setValue(value);
    }

    /**
     * Missing description at method getValueRef.
     *
     * @return the PropertyReference.
     */
    public PropertyReference getValueRef() {
        return this.valueRef;
    }

    /**
     * Missing description at method setValueRef.
     *
     * @param valueRef the PropertyReference.
     */
    public void setValueRef(PropertyReference valueRef) {
        this.valueRef = valueRef;
    }

    /**
     * Missing description at method setValueRef.
     *
     * @param valueRef the String.
     */
    public void setValueRef(String valueRef) {
        if ((this.valueRef == null)) {
            this.valueRef = new PropertyReference();
        }
        this.valueRef.setValue(valueRef);
    }

    /**
     * Missing description at method getConditionType.
     *
     * @return the ConditionType.
     */
    public ConditionType getConditionType() {
        return this.conditionType;
    }

    /**
     * Missing description at method setConditionType.
     *
     * @param conditionType the ConditionType.
     */
    public void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }

    /**
     * Missing description at method setConditionType.
     *
     * @param conditionType the String.
     */
    public void setConditionType(String conditionType) {
        if ((conditionType == null)) {
            this.conditionType = null;
        } else {
            this.conditionType = ConditionType.valueOf(conditionType);
        }
    }

    /**
     * Missing description at method getOperator.
     *
     * @return the OperatorType.
     */
    public OperatorType getOperator() {
        return this.operator;
    }

    /**
     * Missing description at method setOperator.
     *
     * @param operator the OperatorType.
     */
    public void setOperator(OperatorType operator) {
        this.operator = operator;
    }

    /**
     * Missing description at method setOperator.
     *
     * @param operator the String.
     */
    public void setOperator(String operator) {
        if ((operator == null)) {
            this.operator = null;
        } else {
            this.operator = OperatorType.valueOf(operator);
        }
    }

    /**
     * Missing description at method getIsBreakCondition.
     *
     * @return the Flag.
     */
    public Flag getIsBreakCondition() {
        return this.isBreakCondition;
    }

    /**
     * Missing description at method setIsBreakCondition.
     *
     * @param isBreakCondition the Flag.
     */
    public void setIsBreakCondition(Flag isBreakCondition) {
        this.isBreakCondition = isBreakCondition;
    }

    /**
     * Missing description at method setIsBreakCondition.
     *
     * @param isBreakCondition the Boolean.
     */
    public void setIsBreakCondition(Boolean isBreakCondition) {
        if ((this.isBreakCondition == null)) {
            this.isBreakCondition = new Flag();
        }
        this.isBreakCondition.setValue(isBreakCondition);
    }
}
