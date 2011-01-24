/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Duration;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;

/**
 * Lock<p/>A Foreach-loop<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-08
 */
public class Lock extends TestScriptComposite implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "blockName", "timeout" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m1,1;", "l0,n;m0,1;" };

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

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new BasetypeProperty<Name>(PROPERTY_NAMES[0], Name.class,
                PROPERTY_CONSTRAINTS[0], this.blockName));
        properties.add(new BasetypeProperty<Duration>(PROPERTY_NAMES[1], Duration.class,
                PROPERTY_CONSTRAINTS[1], this.timeout));
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
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<Lock>\n");
        appendable.append(super.toString());
        appendable.append((("<blockName>" + this.blockName) + "</blockName>\n"));
        appendable.append((("<timeout>" + this.timeout) + "</timeout>\n"));
        appendable.append("</Lock>\n");
        return appendable.toString();
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
            this.timeout = new Duration();
        }
        this.timeout.setValue(timeout);
    }
}
