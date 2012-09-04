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
import org.nabucco.framework.base.facade.datatype.Duration;
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
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

    private static final TestScriptElementType TYPE_DEFAULT = TestScriptElementType.ACTION;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;u0,n;m1,1;", "l0,n;u0,n;m0,1;", "m0,1;", "m0,1;" };

    public static final String TRACE = "trace";

    public static final String DELAY = "delay";

    public static final String METADATA = "metadata";

    public static final String ACTIONCODE = "actionCode";

    private Flag trace;

    private Duration delay;

    private Metadata metadata;

    private SubEngineActionCode actionCode;

    /** Constructs a new Action instance. */
    public Action() {
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
     * @param clone the Action.
     */
    protected void cloneObject(Action clone) {
        super.cloneObject(clone);
        if ((this.getTrace() != null)) {
            clone.setTrace(this.getTrace().cloneObject());
        }
        if ((this.getDelay() != null)) {
            clone.setDelay(this.getDelay().cloneObject());
        }
        if ((this.getMetadata() != null)) {
            clone.setMetadata(this.getMetadata().cloneObject());
        }
        if ((this.getActionCode() != null)) {
            clone.setActionCode(this.getActionCode().cloneObject());
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
        propertyMap.putAll(PropertyCache.getInstance().retrieve(TestScriptComponent.class).getPropertyMap());
        propertyMap.put(TRACE,
                PropertyDescriptorSupport.createBasetype(TRACE, Flag.class, 7, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(DELAY,
                PropertyDescriptorSupport.createBasetype(DELAY, Duration.class, 8, PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(METADATA, PropertyDescriptorSupport.createDatatype(METADATA, Metadata.class, 9,
                PROPERTY_CONSTRAINTS[2], false, PropertyAssociationType.AGGREGATION));
        propertyMap.put(ACTIONCODE, PropertyDescriptorSupport.createDatatype(ACTIONCODE, SubEngineActionCode.class, 10,
                PROPERTY_CONSTRAINTS[3], false, PropertyAssociationType.AGGREGATION));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(Action.getPropertyDescriptor(TRACE), this.trace, null));
        properties.add(super.createProperty(Action.getPropertyDescriptor(DELAY), this.delay, null));
        properties.add(super.createProperty(Action.getPropertyDescriptor(METADATA), this.getMetadata(), null));
        properties.add(super.createProperty(Action.getPropertyDescriptor(ACTIONCODE), this.getActionCode(), null));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(TRACE) && (property.getType() == Flag.class))) {
            this.setTrace(((Flag) property.getInstance()));
            return true;
        } else if ((property.getName().equals(DELAY) && (property.getType() == Duration.class))) {
            this.setDelay(((Duration) property.getInstance()));
            return true;
        } else if ((property.getName().equals(METADATA) && (property.getType() == Metadata.class))) {
            this.setMetadata(((Metadata) property.getInstance()));
            return true;
        } else if ((property.getName().equals(ACTIONCODE) && (property.getType() == SubEngineActionCode.class))) {
            this.setActionCode(((SubEngineActionCode) property.getInstance()));
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
        final Action other = ((Action) obj);
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
        if ((this.actionCode == null)) {
            if ((other.actionCode != null))
                return false;
        } else if ((!this.actionCode.equals(other.actionCode)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.trace == null) ? 0 : this.trace.hashCode()));
        result = ((PRIME * result) + ((this.delay == null) ? 0 : this.delay.hashCode()));
        result = ((PRIME * result) + ((this.metadata == null) ? 0 : this.metadata.hashCode()));
        result = ((PRIME * result) + ((this.actionCode == null) ? 0 : this.actionCode.hashCode()));
        return result;
    }

    @Override
    public Action cloneObject() {
        Action clone = new Action();
        this.cloneObject(clone);
        return clone;
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
            if ((trace == null)) {
                return;
            }
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
            if ((delay == null)) {
                return;
            }
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
     * Missing description at method setActionCode.
     *
     * @param actionCode the SubEngineActionCode.
     */
    public void setActionCode(SubEngineActionCode actionCode) {
        this.actionCode = actionCode;
    }

    /**
     * Missing description at method getActionCode.
     *
     * @return the SubEngineActionCode.
     */
    public SubEngineActionCode getActionCode() {
        return this.actionCode;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(Action.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(Action.class).getAllProperties();
    }
}
