/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Duration;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;

/**
 * Lock<p/>A Foreach-loop<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-08
 */
public class Lock extends TestScriptComposite implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,255;m1,1;", "l0,n;m0,1;" };

    public static final String BLOCKNAME = "blockName";

    public static final String TIMEOUT = "timeout";

    private Name blockName;

    private Duration timeout;

    /** Constructs a new Lock instance. */
    public Lock() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
        type = TestScriptElementType.LOCK;
    }

    /**
     * CloneObject.
     *
     * @param clone the Lock.
     */
    protected void cloneObject(Lock clone) {
        super.cloneObject(clone);
        if ((this.getBlockName() != null)) {
            clone.setBlockName(this.getBlockName().cloneObject());
        }
        if ((this.getTimeout() != null)) {
            clone.setTimeout(this.getTimeout().cloneObject());
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
        propertyMap.putAll(PropertyCache.getInstance().retrieve(TestScriptComposite.class)
                .getPropertyMap());
        propertyMap.put(BLOCKNAME, PropertyDescriptorSupport.createBasetype(BLOCKNAME, Name.class,
                8, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(TIMEOUT, PropertyDescriptorSupport.createBasetype(TIMEOUT, Duration.class,
                9, PROPERTY_CONSTRAINTS[1], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty> getProperties() {
        List<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(Lock.getPropertyDescriptor(BLOCKNAME), this.blockName,
                null));
        properties
                .add(super.createProperty(Lock.getPropertyDescriptor(TIMEOUT), this.timeout, null));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(BLOCKNAME) && (property.getType() == Name.class))) {
            this.setBlockName(((Name) property.getInstance()));
            return true;
        } else if ((property.getName().equals(TIMEOUT) && (property.getType() == Duration.class))) {
            this.setTimeout(((Duration) property.getInstance()));
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
        final Lock other = ((Lock) obj);
        if ((this.blockName == null)) {
            if ((other.blockName != null))
                return false;
        } else if ((!this.blockName.equals(other.blockName)))
            return false;
        if ((this.timeout == null)) {
            if ((other.timeout != null))
                return false;
        } else if ((!this.timeout.equals(other.timeout)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.blockName == null) ? 0 : this.blockName.hashCode()));
        result = ((PRIME * result) + ((this.timeout == null) ? 0 : this.timeout.hashCode()));
        return result;
    }

    @Override
    public Lock cloneObject() {
        Lock clone = new Lock();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Missing description at method getBlockName.
     *
     * @return the Name.
     */
    public Name getBlockName() {
        return this.blockName;
    }

    /**
     * Missing description at method setBlockName.
     *
     * @param blockName the Name.
     */
    public void setBlockName(Name blockName) {
        this.blockName = blockName;
    }

    /**
     * Missing description at method setBlockName.
     *
     * @param blockName the String.
     */
    public void setBlockName(String blockName) {
        if ((this.blockName == null)) {
            if ((blockName == null)) {
                return;
            }
            this.blockName = new Name();
        }
        this.blockName.setValue(blockName);
    }

    /**
     * Missing description at method getTimeout.
     *
     * @return the Duration.
     */
    public Duration getTimeout() {
        return this.timeout;
    }

    /**
     * Missing description at method setTimeout.
     *
     * @param timeout the Duration.
     */
    public void setTimeout(Duration timeout) {
        this.timeout = timeout;
    }

    /**
     * Missing description at method setTimeout.
     *
     * @param timeout the Long.
     */
    public void setTimeout(Long timeout) {
        if ((this.timeout == null)) {
            if ((timeout == null)) {
                return;
            }
            this.timeout = new Duration();
        }
        this.timeout.setValue(timeout);
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(Lock.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(Lock.class).getAllProperties();
    }
}
