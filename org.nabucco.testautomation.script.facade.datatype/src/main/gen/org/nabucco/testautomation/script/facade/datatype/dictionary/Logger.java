/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;
import org.nabucco.testautomation.script.facade.datatype.dictionary.type.LoggerLevelType;

/**
 * Logger<p/>A Logger<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-08
 */
public class Logger extends TestScriptComposite implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;" };

    public static final String LEVEL = "level";

    private LoggerLevelType level;

    /** Constructs a new Logger instance. */
    public Logger() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
        type = TestScriptElementType.LOGGER;
    }

    /**
     * CloneObject.
     *
     * @param clone the Logger.
     */
    protected void cloneObject(Logger clone) {
        super.cloneObject(clone);
        clone.setLevel(this.getLevel());
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
        propertyMap.put(LEVEL, PropertyDescriptorSupport.createEnumeration(LEVEL,
                LoggerLevelType.class, 8, PROPERTY_CONSTRAINTS[0], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty> getProperties() {
        List<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(Logger.getPropertyDescriptor(LEVEL), this.level, null));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(LEVEL) && (property.getType() == LoggerLevelType.class))) {
            this.setLevel(((LoggerLevelType) property.getInstance()));
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
        final Logger other = ((Logger) obj);
        if ((this.level == null)) {
            if ((other.level != null))
                return false;
        } else if ((!this.level.equals(other.level)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.level == null) ? 0 : this.level.hashCode()));
        return result;
    }

    @Override
    public Logger cloneObject() {
        Logger clone = new Logger();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Missing description at method getLevel.
     *
     * @return the LoggerLevelType.
     */
    public LoggerLevelType getLevel() {
        return this.level;
    }

    /**
     * Missing description at method setLevel.
     *
     * @param level the LoggerLevelType.
     */
    public void setLevel(LoggerLevelType level) {
        this.level = level;
    }

    /**
     * Missing description at method setLevel.
     *
     * @param level the String.
     */
    public void setLevel(String level) {
        if ((level == null)) {
            this.level = null;
        } else {
            this.level = LoggerLevelType.valueOf(level);
        }
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(Logger.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(Logger.class).getAllProperties();
    }
}
