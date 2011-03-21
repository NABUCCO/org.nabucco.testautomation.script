/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.testautomation.facade.datatype.base.BooleanValue;
import org.nabucco.testautomation.facade.datatype.base.Text;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyReference;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComponent;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;

/**
 * Assertion<p/>A log message<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public class Assertion extends TestScriptComponent implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m0,1;", "l0,n;m0,1;",
            "l0,n;m0,1;", "l0,255;m0,1;" };

    public static final String PROPERTYREF = "propertyRef";

    public static final String MESSAGE = "message";

    public static final String FAIL = "fail";

    public static final String CLASSNAME = "className";

    private PropertyReference propertyRef;

    private Text message;

    private BooleanValue fail;

    private Name className;

    /** Constructs a new Assertion instance. */
    public Assertion() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
        type = TestScriptElementType.ASSERTION;
    }

    /**
     * CloneObject.
     *
     * @param clone the Assertion.
     */
    protected void cloneObject(Assertion clone) {
        super.cloneObject(clone);
        if ((this.getPropertyRef() != null)) {
            clone.setPropertyRef(this.getPropertyRef().cloneObject());
        }
        if ((this.getMessage() != null)) {
            clone.setMessage(this.getMessage().cloneObject());
        }
        if ((this.getFail() != null)) {
            clone.setFail(this.getFail().cloneObject());
        }
        if ((this.getClassName() != null)) {
            clone.setClassName(this.getClassName().cloneObject());
        }
        clone.setType(this.getType());
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(TestScriptComponent.class)
                .getPropertyMap());
        propertyMap.put(PROPERTYREF, PropertyDescriptorSupport.createBasetype(PROPERTYREF,
                PropertyReference.class, 7, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(MESSAGE, PropertyDescriptorSupport.createBasetype(MESSAGE, Text.class, 8,
                PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(FAIL, PropertyDescriptorSupport.createBasetype(FAIL, BooleanValue.class, 9,
                PROPERTY_CONSTRAINTS[2], false));
        propertyMap.put(CLASSNAME, PropertyDescriptorSupport.createBasetype(CLASSNAME, Name.class,
                10, PROPERTY_CONSTRAINTS[3], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty> getProperties() {
        List<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(Assertion.getPropertyDescriptor(PROPERTYREF),
                this.propertyRef, null));
        properties.add(super.createProperty(Assertion.getPropertyDescriptor(MESSAGE), this.message,
                null));
        properties
                .add(super.createProperty(Assertion.getPropertyDescriptor(FAIL), this.fail, null));
        properties.add(super.createProperty(Assertion.getPropertyDescriptor(CLASSNAME),
                this.className, null));
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
        } else if ((property.getName().equals(MESSAGE) && (property.getType() == Text.class))) {
            this.setMessage(((Text) property.getInstance()));
            return true;
        } else if ((property.getName().equals(FAIL) && (property.getType() == BooleanValue.class))) {
            this.setFail(((BooleanValue) property.getInstance()));
            return true;
        } else if ((property.getName().equals(CLASSNAME) && (property.getType() == Name.class))) {
            this.setClassName(((Name) property.getInstance()));
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
        final Assertion other = ((Assertion) obj);
        if ((this.propertyRef == null)) {
            if ((other.propertyRef != null))
                return false;
        } else if ((!this.propertyRef.equals(other.propertyRef)))
            return false;
        if ((this.message == null)) {
            if ((other.message != null))
                return false;
        } else if ((!this.message.equals(other.message)))
            return false;
        if ((this.fail == null)) {
            if ((other.fail != null))
                return false;
        } else if ((!this.fail.equals(other.fail)))
            return false;
        if ((this.className == null)) {
            if ((other.className != null))
                return false;
        } else if ((!this.className.equals(other.className)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.propertyRef == null) ? 0 : this.propertyRef.hashCode()));
        result = ((PRIME * result) + ((this.message == null) ? 0 : this.message.hashCode()));
        result = ((PRIME * result) + ((this.fail == null) ? 0 : this.fail.hashCode()));
        result = ((PRIME * result) + ((this.className == null) ? 0 : this.className.hashCode()));
        return result;
    }

    @Override
    public Assertion cloneObject() {
        Assertion clone = new Assertion();
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
     * Missing description at method getMessage.
     *
     * @return the Text.
     */
    public Text getMessage() {
        return this.message;
    }

    /**
     * Missing description at method setMessage.
     *
     * @param message the Text.
     */
    public void setMessage(Text message) {
        this.message = message;
    }

    /**
     * Missing description at method setMessage.
     *
     * @param message the String.
     */
    public void setMessage(String message) {
        if ((this.message == null)) {
            if ((message == null)) {
                return;
            }
            this.message = new Text();
        }
        this.message.setValue(message);
    }

    /**
     * Missing description at method getFail.
     *
     * @return the BooleanValue.
     */
    public BooleanValue getFail() {
        return this.fail;
    }

    /**
     * Missing description at method setFail.
     *
     * @param fail the BooleanValue.
     */
    public void setFail(BooleanValue fail) {
        this.fail = fail;
    }

    /**
     * Missing description at method setFail.
     *
     * @param fail the Boolean.
     */
    public void setFail(Boolean fail) {
        if ((this.fail == null)) {
            if ((fail == null)) {
                return;
            }
            this.fail = new BooleanValue();
        }
        this.fail.setValue(fail);
    }

    /**
     * Missing description at method getClassName.
     *
     * @return the Name.
     */
    public Name getClassName() {
        return this.className;
    }

    /**
     * Missing description at method setClassName.
     *
     * @param className the Name.
     */
    public void setClassName(Name className) {
        this.className = className;
    }

    /**
     * Missing description at method setClassName.
     *
     * @param className the String.
     */
    public void setClassName(String className) {
        if ((this.className == null)) {
            if ((className == null)) {
                return;
            }
            this.className = new Name();
        }
        this.className.setValue(className);
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(Assertion.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(Assertion.class).getAllProperties();
    }
}
