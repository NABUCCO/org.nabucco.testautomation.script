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
import org.nabucco.framework.base.facade.datatype.Number;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;

/**
 * Loop<p/>A basic loop<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-08
 */
public class Loop extends TestScriptComposite implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m0,1;", "l0,n;m0,1;",
            "l0,n;m0,1;", "l0,255;m0,1;" };

    public static final String MAXITERATIONS = "maxIterations";

    public static final String MAXDURATION = "maxDuration";

    public static final String WAIT = "wait";

    public static final String INDEXNAME = "indexName";

    private Number maxIterations;

    private Duration maxDuration;

    private Duration wait;

    private Name indexName;

    /** Constructs a new Loop instance. */
    public Loop() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
        type = TestScriptElementType.LOOP;
    }

    /**
     * CloneObject.
     *
     * @param clone the Loop.
     */
    protected void cloneObject(Loop clone) {
        super.cloneObject(clone);
        if ((this.getMaxIterations() != null)) {
            clone.setMaxIterations(this.getMaxIterations().cloneObject());
        }
        if ((this.getMaxDuration() != null)) {
            clone.setMaxDuration(this.getMaxDuration().cloneObject());
        }
        if ((this.getWait() != null)) {
            clone.setWait(this.getWait().cloneObject());
        }
        if ((this.getIndexName() != null)) {
            clone.setIndexName(this.getIndexName().cloneObject());
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
        propertyMap.put(MAXITERATIONS, PropertyDescriptorSupport.createBasetype(MAXITERATIONS,
                Number.class, 8, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(MAXDURATION, PropertyDescriptorSupport.createBasetype(MAXDURATION,
                Duration.class, 9, PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(WAIT, PropertyDescriptorSupport.createBasetype(WAIT, Duration.class, 10,
                PROPERTY_CONSTRAINTS[2], false));
        propertyMap.put(INDEXNAME, PropertyDescriptorSupport.createBasetype(INDEXNAME, Name.class,
                11, PROPERTY_CONSTRAINTS[3], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty> getProperties() {
        List<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(Loop.getPropertyDescriptor(MAXITERATIONS),
                this.maxIterations, null));
        properties.add(super.createProperty(Loop.getPropertyDescriptor(MAXDURATION),
                this.maxDuration, null));
        properties.add(super.createProperty(Loop.getPropertyDescriptor(WAIT), this.wait, null));
        properties.add(super.createProperty(Loop.getPropertyDescriptor(INDEXNAME), this.indexName,
                null));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(MAXITERATIONS) && (property.getType() == Number.class))) {
            this.setMaxIterations(((Number) property.getInstance()));
            return true;
        } else if ((property.getName().equals(MAXDURATION) && (property.getType() == Duration.class))) {
            this.setMaxDuration(((Duration) property.getInstance()));
            return true;
        } else if ((property.getName().equals(WAIT) && (property.getType() == Duration.class))) {
            this.setWait(((Duration) property.getInstance()));
            return true;
        } else if ((property.getName().equals(INDEXNAME) && (property.getType() == Name.class))) {
            this.setIndexName(((Name) property.getInstance()));
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
        final Loop other = ((Loop) obj);
        if ((this.maxIterations == null)) {
            if ((other.maxIterations != null))
                return false;
        } else if ((!this.maxIterations.equals(other.maxIterations)))
            return false;
        if ((this.maxDuration == null)) {
            if ((other.maxDuration != null))
                return false;
        } else if ((!this.maxDuration.equals(other.maxDuration)))
            return false;
        if ((this.wait == null)) {
            if ((other.wait != null))
                return false;
        } else if ((!this.wait.equals(other.wait)))
            return false;
        if ((this.indexName == null)) {
            if ((other.indexName != null))
                return false;
        } else if ((!this.indexName.equals(other.indexName)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.maxIterations == null) ? 0 : this.maxIterations
                .hashCode()));
        result = ((PRIME * result) + ((this.maxDuration == null) ? 0 : this.maxDuration.hashCode()));
        result = ((PRIME * result) + ((this.wait == null) ? 0 : this.wait.hashCode()));
        result = ((PRIME * result) + ((this.indexName == null) ? 0 : this.indexName.hashCode()));
        return result;
    }

    @Override
    public Loop cloneObject() {
        Loop clone = new Loop();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Missing description at method getMaxIterations.
     *
     * @return the Number.
     */
    public Number getMaxIterations() {
        return this.maxIterations;
    }

    /**
     * Missing description at method setMaxIterations.
     *
     * @param maxIterations the Number.
     */
    public void setMaxIterations(Number maxIterations) {
        this.maxIterations = maxIterations;
    }

    /**
     * Missing description at method setMaxIterations.
     *
     * @param maxIterations the Integer.
     */
    public void setMaxIterations(Integer maxIterations) {
        if ((this.maxIterations == null)) {
            if ((maxIterations == null)) {
                return;
            }
            this.maxIterations = new Number();
        }
        this.maxIterations.setValue(maxIterations);
    }

    /**
     * Missing description at method getMaxDuration.
     *
     * @return the Duration.
     */
    public Duration getMaxDuration() {
        return this.maxDuration;
    }

    /**
     * Missing description at method setMaxDuration.
     *
     * @param maxDuration the Duration.
     */
    public void setMaxDuration(Duration maxDuration) {
        this.maxDuration = maxDuration;
    }

    /**
     * Missing description at method setMaxDuration.
     *
     * @param maxDuration the Long.
     */
    public void setMaxDuration(Long maxDuration) {
        if ((this.maxDuration == null)) {
            if ((maxDuration == null)) {
                return;
            }
            this.maxDuration = new Duration();
        }
        this.maxDuration.setValue(maxDuration);
    }

    /**
     * Missing description at method getWait.
     *
     * @return the Duration.
     */
    public Duration getWait() {
        return this.wait;
    }

    /**
     * Missing description at method setWait.
     *
     * @param wait the Duration.
     */
    public void setWait(Duration wait) {
        this.wait = wait;
    }

    /**
     * Missing description at method setWait.
     *
     * @param wait the Long.
     */
    public void setWait(Long wait) {
        if ((this.wait == null)) {
            if ((wait == null)) {
                return;
            }
            this.wait = new Duration();
        }
        this.wait.setValue(wait);
    }

    /**
     * Missing description at method getIndexName.
     *
     * @return the Name.
     */
    public Name getIndexName() {
        return this.indexName;
    }

    /**
     * Missing description at method setIndexName.
     *
     * @param indexName the Name.
     */
    public void setIndexName(Name indexName) {
        this.indexName = indexName;
    }

    /**
     * Missing description at method setIndexName.
     *
     * @param indexName the String.
     */
    public void setIndexName(String indexName) {
        if ((this.indexName == null)) {
            if ((indexName == null)) {
                return;
            }
            this.indexName = new Name();
        }
        this.indexName.setValue(indexName);
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(Loop.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(Loop.class).getAllProperties();
    }
}
