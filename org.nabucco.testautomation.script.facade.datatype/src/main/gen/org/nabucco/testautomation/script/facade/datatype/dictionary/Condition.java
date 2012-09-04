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
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyReference;
import org.nabucco.testautomation.property.facade.datatype.base.Value;
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

    private static final TestScriptElementType TYPE_DEFAULT = TestScriptElementType.CONDITION;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;u0,n;m0,1;", "l0,n;u0,n;m0,1;", "l0,n;u0,n;m0,1;",
            "m0,1;", "m0,1;", "l0,n;u0,n;m1,1;" };

    public static final String PROPERTYREF = "propertyRef";

    public static final String VALUE = "value";

    public static final String VALUEREF = "valueRef";

    public static final String CONDITIONTYPE = "conditionType";

    public static final String OPERATOR = "operator";

    public static final String ISBREAKCONDITION = "isBreakCondition";

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
        type = TYPE_DEFAULT;
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

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(TestScriptComposite.class).getPropertyMap());
        propertyMap.put(PROPERTYREF, PropertyDescriptorSupport.createBasetype(PROPERTYREF, PropertyReference.class, 8,
                PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(VALUE,
                PropertyDescriptorSupport.createBasetype(VALUE, Value.class, 9, PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(VALUEREF, PropertyDescriptorSupport.createBasetype(VALUEREF, PropertyReference.class, 10,
                PROPERTY_CONSTRAINTS[2], false));
        propertyMap.put(CONDITIONTYPE, PropertyDescriptorSupport.createEnumeration(CONDITIONTYPE, ConditionType.class,
                11, PROPERTY_CONSTRAINTS[3], false));
        propertyMap.put(OPERATOR, PropertyDescriptorSupport.createEnumeration(OPERATOR, OperatorType.class, 12,
                PROPERTY_CONSTRAINTS[4], false));
        propertyMap.put(ISBREAKCONDITION, PropertyDescriptorSupport.createBasetype(ISBREAKCONDITION, Flag.class, 13,
                PROPERTY_CONSTRAINTS[5], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(Condition.getPropertyDescriptor(PROPERTYREF), this.propertyRef, null));
        properties.add(super.createProperty(Condition.getPropertyDescriptor(VALUE), this.value, null));
        properties.add(super.createProperty(Condition.getPropertyDescriptor(VALUEREF), this.valueRef, null));
        properties.add(super.createProperty(Condition.getPropertyDescriptor(CONDITIONTYPE), this.getConditionType(),
                null));
        properties.add(super.createProperty(Condition.getPropertyDescriptor(OPERATOR), this.getOperator(), null));
        properties.add(super.createProperty(Condition.getPropertyDescriptor(ISBREAKCONDITION), this.isBreakCondition,
                null));
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
        } else if ((property.getName().equals(VALUE) && (property.getType() == Value.class))) {
            this.setValue(((Value) property.getInstance()));
            return true;
        } else if ((property.getName().equals(VALUEREF) && (property.getType() == PropertyReference.class))) {
            this.setValueRef(((PropertyReference) property.getInstance()));
            return true;
        } else if ((property.getName().equals(CONDITIONTYPE) && (property.getType() == ConditionType.class))) {
            this.setConditionType(((ConditionType) property.getInstance()));
            return true;
        } else if ((property.getName().equals(OPERATOR) && (property.getType() == OperatorType.class))) {
            this.setOperator(((OperatorType) property.getInstance()));
            return true;
        } else if ((property.getName().equals(ISBREAKCONDITION) && (property.getType() == Flag.class))) {
            this.setIsBreakCondition(((Flag) property.getInstance()));
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
        result = ((PRIME * result) + ((this.conditionType == null) ? 0 : this.conditionType.hashCode()));
        result = ((PRIME * result) + ((this.operator == null) ? 0 : this.operator.hashCode()));
        result = ((PRIME * result) + ((this.isBreakCondition == null) ? 0 : this.isBreakCondition.hashCode()));
        return result;
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
            if ((propertyRef == null)) {
                return;
            }
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
            if ((value == null)) {
                return;
            }
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
            if ((valueRef == null)) {
                return;
            }
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
            if ((isBreakCondition == null)) {
                return;
            }
            this.isBreakCondition = new Flag();
        }
        this.isBreakCondition.setValue(isBreakCondition);
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(Condition.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(Condition.class).getAllProperties();
    }
}
