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
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.datatype.Key;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;

/**
 * MetadataSearchMsg<p/>Message for searching Metadata<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-20
 */
public class MetadataSearchMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;u0,n;m1,1;", "l0,255;u0,n;m1,1;", "l0,n;u0,n;m0,1;",
            "l0,255;u0,n;m1,1;", "m1,1;" };

    public static final String IDENTIFIER = "identifier";

    public static final String NAME = "name";

    public static final String METADATAKEY = "metadataKey";

    public static final String DESCRIPTION = "description";

    public static final String SUBENGINE = "subEngine";

    private Identifier identifier;

    private Name name;

    private Key metadataKey;

    private Description description;

    private SubEngineCode subEngine;

    /** Constructs a new MetadataSearchMsg instance. */
    public MetadataSearchMsg() {
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
        propertyMap.put(IDENTIFIER, PropertyDescriptorSupport.createBasetype(IDENTIFIER, Identifier.class, 0,
                PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(NAME,
                PropertyDescriptorSupport.createBasetype(NAME, Name.class, 1, PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(METADATAKEY,
                PropertyDescriptorSupport.createBasetype(METADATAKEY, Key.class, 2, PROPERTY_CONSTRAINTS[2], false));
        propertyMap.put(DESCRIPTION, PropertyDescriptorSupport.createBasetype(DESCRIPTION, Description.class, 3,
                PROPERTY_CONSTRAINTS[3], false));
        propertyMap.put(SUBENGINE, PropertyDescriptorSupport.createDatatype(SUBENGINE, SubEngineCode.class, 4,
                PROPERTY_CONSTRAINTS[4], false, PropertyAssociationType.COMPOSITION));
        return new NabuccoPropertyContainer(propertyMap);
    }

    /** Init. */
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(MetadataSearchMsg.getPropertyDescriptor(IDENTIFIER), this.identifier));
        properties.add(super.createProperty(MetadataSearchMsg.getPropertyDescriptor(NAME), this.name));
        properties.add(super.createProperty(MetadataSearchMsg.getPropertyDescriptor(METADATAKEY), this.metadataKey));
        properties.add(super.createProperty(MetadataSearchMsg.getPropertyDescriptor(DESCRIPTION), this.description));
        properties.add(super.createProperty(MetadataSearchMsg.getPropertyDescriptor(SUBENGINE), this.getSubEngine()));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(IDENTIFIER) && (property.getType() == Identifier.class))) {
            this.setIdentifier(((Identifier) property.getInstance()));
            return true;
        } else if ((property.getName().equals(NAME) && (property.getType() == Name.class))) {
            this.setName(((Name) property.getInstance()));
            return true;
        } else if ((property.getName().equals(METADATAKEY) && (property.getType() == Key.class))) {
            this.setMetadataKey(((Key) property.getInstance()));
            return true;
        } else if ((property.getName().equals(DESCRIPTION) && (property.getType() == Description.class))) {
            this.setDescription(((Description) property.getInstance()));
            return true;
        } else if ((property.getName().equals(SUBENGINE) && (property.getType() == SubEngineCode.class))) {
            this.setSubEngine(((SubEngineCode) property.getInstance()));
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
        final MetadataSearchMsg other = ((MetadataSearchMsg) obj);
        if ((this.identifier == null)) {
            if ((other.identifier != null))
                return false;
        } else if ((!this.identifier.equals(other.identifier)))
            return false;
        if ((this.name == null)) {
            if ((other.name != null))
                return false;
        } else if ((!this.name.equals(other.name)))
            return false;
        if ((this.metadataKey == null)) {
            if ((other.metadataKey != null))
                return false;
        } else if ((!this.metadataKey.equals(other.metadataKey)))
            return false;
        if ((this.description == null)) {
            if ((other.description != null))
                return false;
        } else if ((!this.description.equals(other.description)))
            return false;
        if ((this.subEngine == null)) {
            if ((other.subEngine != null))
                return false;
        } else if ((!this.subEngine.equals(other.subEngine)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.identifier == null) ? 0 : this.identifier.hashCode()));
        result = ((PRIME * result) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((PRIME * result) + ((this.metadataKey == null) ? 0 : this.metadataKey.hashCode()));
        result = ((PRIME * result) + ((this.description == null) ? 0 : this.description.hashCode()));
        result = ((PRIME * result) + ((this.subEngine == null) ? 0 : this.subEngine.hashCode()));
        return result;
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getIdentifier.
     *
     * @return the Identifier.
     */
    public Identifier getIdentifier() {
        return this.identifier;
    }

    /**
     * Missing description at method setIdentifier.
     *
     * @param identifier the Identifier.
     */
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    /**
     * Missing description at method getName.
     *
     * @return the Name.
     */
    public Name getName() {
        return this.name;
    }

    /**
     * Missing description at method setName.
     *
     * @param name the Name.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Missing description at method getMetadataKey.
     *
     * @return the Key.
     */
    public Key getMetadataKey() {
        return this.metadataKey;
    }

    /**
     * Missing description at method setMetadataKey.
     *
     * @param metadataKey the Key.
     */
    public void setMetadataKey(Key metadataKey) {
        this.metadataKey = metadataKey;
    }

    /**
     * Missing description at method getDescription.
     *
     * @return the Description.
     */
    public Description getDescription() {
        return this.description;
    }

    /**
     * Missing description at method setDescription.
     *
     * @param description the Description.
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * Missing description at method getSubEngine.
     *
     * @return the SubEngineCode.
     */
    public SubEngineCode getSubEngine() {
        return this.subEngine;
    }

    /**
     * Missing description at method setSubEngine.
     *
     * @param subEngine the SubEngineCode.
     */
    public void setSubEngine(SubEngineCode subEngine) {
        this.subEngine = subEngine;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(MetadataSearchMsg.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(MetadataSearchMsg.class).getAllProperties();
    }
}
