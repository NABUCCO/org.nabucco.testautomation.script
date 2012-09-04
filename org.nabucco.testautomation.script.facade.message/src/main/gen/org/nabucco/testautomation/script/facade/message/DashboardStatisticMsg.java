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
package org.nabucco.testautomation.script.facade.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Number;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;

/**
 * DashboardStatisticMsg<p/>Message for transportation statistics<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2011-12-22
 */
public class DashboardStatisticMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;u0,n;m1,1;", "l0,n;u0,n;m1,1;" };

    public static final String NUMTESTSCRIPTS = "numTestScripts";

    public static final String NUMMETADATA = "numMetadata";

    private Number numTestScripts;

    private Number numMetadata;

    /** Constructs a new DashboardStatisticMsg instance. */
    public DashboardStatisticMsg() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.put(NUMTESTSCRIPTS, PropertyDescriptorSupport.createBasetype(NUMTESTSCRIPTS, Number.class, 0,
                PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(NUMMETADATA,
                PropertyDescriptorSupport.createBasetype(NUMMETADATA, Number.class, 1, PROPERTY_CONSTRAINTS[1], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    /** Init. */
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(DashboardStatisticMsg.getPropertyDescriptor(NUMTESTSCRIPTS),
                this.numTestScripts));
        properties
                .add(super.createProperty(DashboardStatisticMsg.getPropertyDescriptor(NUMMETADATA), this.numMetadata));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(NUMTESTSCRIPTS) && (property.getType() == Number.class))) {
            this.setNumTestScripts(((Number) property.getInstance()));
            return true;
        } else if ((property.getName().equals(NUMMETADATA) && (property.getType() == Number.class))) {
            this.setNumMetadata(((Number) property.getInstance()));
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
        final DashboardStatisticMsg other = ((DashboardStatisticMsg) obj);
        if ((this.numTestScripts == null)) {
            if ((other.numTestScripts != null))
                return false;
        } else if ((!this.numTestScripts.equals(other.numTestScripts)))
            return false;
        if ((this.numMetadata == null)) {
            if ((other.numMetadata != null))
                return false;
        } else if ((!this.numMetadata.equals(other.numMetadata)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.numTestScripts == null) ? 0 : this.numTestScripts.hashCode()));
        result = ((PRIME * result) + ((this.numMetadata == null) ? 0 : this.numMetadata.hashCode()));
        return result;
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getNumTestScripts.
     *
     * @return the Number.
     */
    public Number getNumTestScripts() {
        return this.numTestScripts;
    }

    /**
     * Missing description at method setNumTestScripts.
     *
     * @param numTestScripts the Number.
     */
    public void setNumTestScripts(Number numTestScripts) {
        this.numTestScripts = numTestScripts;
    }

    /**
     * Missing description at method getNumMetadata.
     *
     * @return the Number.
     */
    public Number getNumMetadata() {
        return this.numMetadata;
    }

    /**
     * Missing description at method setNumMetadata.
     *
     * @param numMetadata the Number.
     */
    public void setNumMetadata(Number numMetadata) {
        this.numMetadata = numMetadata;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(DashboardStatisticMsg.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(DashboardStatisticMsg.class).getAllProperties();
    }
}
