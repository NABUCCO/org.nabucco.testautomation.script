/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Duration;
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineActionCode;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComponent;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;

/**
 * Action<p/>A test execution<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public class Action extends TestScriptComponent implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "actionId", "trace", "delay", "metadata",
            "action" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m1,1;", "l0,n;m1,1;",
            "l0,n;m0,1;", "m0,1;", "m0,1;" };

    private Identifier actionId;

    private Flag trace;

    private Duration delay;

    private Metadata metadata;

    private SubEngineActionCode action;

    /** Constructs a new Action instance. */
    public Action() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
        type = TestScriptElementType.ACTION;
    }

    /**
     * CloneObject.
     *
     * @param clone the Action.
     */
    protected void cloneObject(Action clone) {
        super.cloneObject(clone);
        if ((this.getActionId() != null)) {
            clone.setActionId(this.getActionId().cloneObject());
        }
        if ((this.getTrace() != null)) {
            clone.setTrace(this.getTrace().cloneObject());
        }
        if ((this.getDelay() != null)) {
            clone.setDelay(this.getDelay().cloneObject());
        }
        if ((this.getMetadata() != null)) {
            clone.setMetadata(this.getMetadata().cloneObject());
        }
        if ((this.getAction() != null)) {
            clone.setAction(this.getAction().cloneObject());
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
        properties.add(new BasetypeProperty<Identifier>(PROPERTY_NAMES[0], Identifier.class,
                PROPERTY_CONSTRAINTS[0], this.actionId));
        properties.add(new BasetypeProperty<Flag>(PROPERTY_NAMES[1], Flag.class,
                PROPERTY_CONSTRAINTS[1], this.trace));
        properties.add(new BasetypeProperty<Duration>(PROPERTY_NAMES[2], Duration.class,
                PROPERTY_CONSTRAINTS[2], this.delay));
        properties.add(new DatatypeProperty<Metadata>(PROPERTY_NAMES[3], Metadata.class,
                PROPERTY_CONSTRAINTS[3], this.metadata));
        properties.add(new DatatypeProperty<SubEngineActionCode>(PROPERTY_NAMES[4],
                SubEngineActionCode.class, PROPERTY_CONSTRAINTS[4], this.action));
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
        final Action other = ((Action) obj);
        if ((this.actionId == null)) {
            if ((other.actionId != null))
                return false;
        } else if ((!this.actionId.equals(other.actionId)))
            return false;
        if ((this.trace == null)) {
            if ((other.trace != null))
                return false;
        } else if ((!this.trace.equals(other.trace)))
            return false;
        if ((this.delay == null)) {
            if ((other.delay != null))
                return false;
        } else if ((!this.delay.equals(other.delay)))
            return false;
        if ((this.metadata == null)) {
            if ((other.metadata != null))
                return false;
        } else if ((!this.metadata.equals(other.metadata)))
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
        result = ((PRIME * result) + ((this.actionId == null) ? 0 : this.actionId.hashCode()));
        result = ((PRIME * result) + ((this.trace == null) ? 0 : this.trace.hashCode()));
        result = ((PRIME * result) + ((this.delay == null) ? 0 : this.delay.hashCode()));
        result = ((PRIME * result) + ((this.metadata == null) ? 0 : this.metadata.hashCode()));
        result = ((PRIME * result) + ((this.action == null) ? 0 : this.action.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<Action>\n");
        appendable.append(super.toString());
        appendable.append((("<actionId>" + this.actionId) + "</actionId>\n"));
        appendable.append((("<trace>" + this.trace) + "</trace>\n"));
        appendable.append((("<delay>" + this.delay) + "</delay>\n"));
        appendable.append((("<metadata>" + this.metadata) + "</metadata>\n"));
        appendable.append((("<action>" + this.action) + "</action>\n"));
        appendable.append("</Action>\n");
        return appendable.toString();
    }

    @Override
    public Action cloneObject() {
        Action clone = new Action();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Missing description at method getActionId.
     *
     * @return the Identifier.
     */
    public Identifier getActionId() {
        return this.actionId;
    }

    /**
     * Missing description at method setActionId.
     *
     * @param actionId the Identifier.
     */
    public void setActionId(Identifier actionId) {
        this.actionId = actionId;
    }

    /**
     * Missing description at method setActionId.
     *
     * @param actionId the Long.
     */
    public void setActionId(Long actionId) {
        if ((this.actionId == null)) {
            this.actionId = new Identifier();
        }
        this.actionId.setValue(actionId);
    }

    /**
     * Missing description at method getTrace.
     *
     * @return the Flag.
     */
    public Flag getTrace() {
        return this.trace;
    }

    /**
     * Missing description at method setTrace.
     *
     * @param trace the Flag.
     */
    public void setTrace(Flag trace) {
        this.trace = trace;
    }

    /**
     * Missing description at method setTrace.
     *
     * @param trace the Boolean.
     */
    public void setTrace(Boolean trace) {
        if ((this.trace == null)) {
            this.trace = new Flag();
        }
        this.trace.setValue(trace);
    }

    /**
     * Missing description at method getDelay.
     *
     * @return the Duration.
     */
    public Duration getDelay() {
        return this.delay;
    }

    /**
     * Missing description at method setDelay.
     *
     * @param delay the Duration.
     */
    public void setDelay(Duration delay) {
        this.delay = delay;
    }

    /**
     * Missing description at method setDelay.
     *
     * @param delay the Long.
     */
    public void setDelay(Long delay) {
        if ((this.delay == null)) {
            this.delay = new Duration();
        }
        this.delay.setValue(delay);
    }

    /**
     * Missing description at method setMetadata.
     *
     * @param metadata the Metadata.
     */
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    /**
     * Missing description at method getMetadata.
     *
     * @return the Metadata.
     */
    public Metadata getMetadata() {
        return this.metadata;
    }

    /**
     * Missing description at method setAction.
     *
     * @param action the SubEngineActionCode.
     */
    public void setAction(SubEngineActionCode action) {
        this.action = action;
    }

    /**
     * Missing description at method getAction.
     *
     * @return the SubEngineActionCode.
     */
    public SubEngineActionCode getAction() {
        return this.action;
    }
}
