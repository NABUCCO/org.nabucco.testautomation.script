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
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.datatype.Key;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;

/**
 * TestScriptSearchMsg<p/>Message for searching TestScripts<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-09
 */
public class TestScriptSearchMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;u0,n;m0,1;", "l0,255;u0,n;m0,1;", "l0,n;u0,n;m0,1;",
            "l0,255;u0,n;m0,1;", "l0,n;u0,n;m0,1;", "l0,n;u0,n;m0,1;" };

    public static final String IDENTIFIER = "identifier";

    public static final String NAME = "name";

    public static final String TESTSCRIPTKEY = "testScriptKey";

    public static final String DESCRIPTION = "description";

    public static final String FOLDERID = "folderId";

    public static final String HASFOLDER = "hasFolder";

    private Identifier identifier;

    private Name name;

    private Key testScriptKey;

    private Description description;

    private Identifier folderId;

    /** leave as null if it doesn't matter if the script has a folder or not, true if it must belong to a folder, false if it must not belong to a folder */
    private Flag hasFolder;

    /** Constructs a new TestScriptSearchMsg instance. */
    public TestScriptSearchMsg() {
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
        propertyMap.put(TESTSCRIPTKEY,
                PropertyDescriptorSupport.createBasetype(TESTSCRIPTKEY, Key.class, 2, PROPERTY_CONSTRAINTS[2], false));
        propertyMap.put(DESCRIPTION, PropertyDescriptorSupport.createBasetype(DESCRIPTION, Description.class, 3,
                PROPERTY_CONSTRAINTS[3], false));
        propertyMap
                .put(FOLDERID, PropertyDescriptorSupport.createBasetype(FOLDERID, Identifier.class, 4,
                        PROPERTY_CONSTRAINTS[4], false));
        propertyMap.put(HASFOLDER,
                PropertyDescriptorSupport.createBasetype(HASFOLDER, Flag.class, 5, PROPERTY_CONSTRAINTS[5], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    /** Init. */
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(TestScriptSearchMsg.getPropertyDescriptor(IDENTIFIER), this.identifier));
        properties.add(super.createProperty(TestScriptSearchMsg.getPropertyDescriptor(NAME), this.name));
        properties.add(super.createProperty(TestScriptSearchMsg.getPropertyDescriptor(TESTSCRIPTKEY),
                this.testScriptKey));
        properties.add(super.createProperty(TestScriptSearchMsg.getPropertyDescriptor(DESCRIPTION), this.description));
        properties.add(super.createProperty(TestScriptSearchMsg.getPropertyDescriptor(FOLDERID), this.folderId));
        properties.add(super.createProperty(TestScriptSearchMsg.getPropertyDescriptor(HASFOLDER), this.hasFolder));
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
        } else if ((property.getName().equals(TESTSCRIPTKEY) && (property.getType() == Key.class))) {
            this.setTestScriptKey(((Key) property.getInstance()));
            return true;
        } else if ((property.getName().equals(DESCRIPTION) && (property.getType() == Description.class))) {
            this.setDescription(((Description) property.getInstance()));
            return true;
        } else if ((property.getName().equals(FOLDERID) && (property.getType() == Identifier.class))) {
            this.setFolderId(((Identifier) property.getInstance()));
            return true;
        } else if ((property.getName().equals(HASFOLDER) && (property.getType() == Flag.class))) {
            this.setHasFolder(((Flag) property.getInstance()));
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
        final TestScriptSearchMsg other = ((TestScriptSearchMsg) obj);
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
        if ((this.testScriptKey == null)) {
            if ((other.testScriptKey != null))
                return false;
        } else if ((!this.testScriptKey.equals(other.testScriptKey)))
            return false;
        if ((this.description == null)) {
            if ((other.description != null))
                return false;
        } else if ((!this.description.equals(other.description)))
            return false;
        if ((this.folderId == null)) {
            if ((other.folderId != null))
                return false;
        } else if ((!this.folderId.equals(other.folderId)))
            return false;
        if ((this.hasFolder == null)) {
            if ((other.hasFolder != null))
                return false;
        } else if ((!this.hasFolder.equals(other.hasFolder)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.identifier == null) ? 0 : this.identifier.hashCode()));
        result = ((PRIME * result) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((PRIME * result) + ((this.testScriptKey == null) ? 0 : this.testScriptKey.hashCode()));
        result = ((PRIME * result) + ((this.description == null) ? 0 : this.description.hashCode()));
        result = ((PRIME * result) + ((this.folderId == null) ? 0 : this.folderId.hashCode()));
        result = ((PRIME * result) + ((this.hasFolder == null) ? 0 : this.hasFolder.hashCode()));
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
     * Missing description at method getTestScriptKey.
     *
     * @return the Key.
     */
    public Key getTestScriptKey() {
        return this.testScriptKey;
    }

    /**
     * Missing description at method setTestScriptKey.
     *
     * @param testScriptKey the Key.
     */
    public void setTestScriptKey(Key testScriptKey) {
        this.testScriptKey = testScriptKey;
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
     * Missing description at method getFolderId.
     *
     * @return the Identifier.
     */
    public Identifier getFolderId() {
        return this.folderId;
    }

    /**
     * Missing description at method setFolderId.
     *
     * @param folderId the Identifier.
     */
    public void setFolderId(Identifier folderId) {
        this.folderId = folderId;
    }

    /**
     * leave as null if it doesn't matter if the script has a folder or not, true if it must belong to a folder, false if it must not belong to a folder
     *
     * @return the Flag.
     */
    public Flag getHasFolder() {
        return this.hasFolder;
    }

    /**
     * leave as null if it doesn't matter if the script has a folder or not, true if it must belong to a folder, false if it must not belong to a folder
     *
     * @param hasFolder the Flag.
     */
    public void setHasFolder(Flag hasFolder) {
        this.hasFolder = hasFolder;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(TestScriptSearchMsg.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(TestScriptSearchMsg.class).getAllProperties();
    }
}
