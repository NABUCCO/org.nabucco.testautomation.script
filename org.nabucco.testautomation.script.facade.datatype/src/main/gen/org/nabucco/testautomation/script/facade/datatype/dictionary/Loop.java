/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Duration;
import org.nabucco.framework.base.facade.datatype.Number;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;

/**
 * Loop<p/>A basic loop<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-08
 */
public class Loop extends TestScriptComposite implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "maxIterations", "maxDuration", "wait" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m0,1;", "l0,n;m0,1;", "l0,n;m0,1;" };

    private Number maxIterations;

    private Duration maxDuration;

    private Duration wait;

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
        clone.setType(this.getType());
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new BasetypeProperty<Number>(PROPERTY_NAMES[0], Number.class,
                PROPERTY_CONSTRAINTS[0], this.maxIterations));
        properties.add(new BasetypeProperty<Duration>(PROPERTY_NAMES[1], Duration.class,
                PROPERTY_CONSTRAINTS[1], this.maxDuration));
        properties.add(new BasetypeProperty<Duration>(PROPERTY_NAMES[2], Duration.class,
                PROPERTY_CONSTRAINTS[2], this.wait));
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
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<Loop>\n");
        appendable.append(super.toString());
        appendable.append((("<maxIterations>" + this.maxIterations) + "</maxIterations>\n"));
        appendable.append((("<maxDuration>" + this.maxDuration) + "</maxDuration>\n"));
        appendable.append((("<wait>" + this.wait) + "</wait>\n"));
        appendable.append("</Loop>\n");
        return appendable.toString();
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
            this.wait = new Duration();
        }
        this.wait.setValue(wait);
    }
}
