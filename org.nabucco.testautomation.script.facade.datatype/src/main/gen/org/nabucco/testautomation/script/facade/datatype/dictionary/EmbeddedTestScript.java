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
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Key;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComponent;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;

/**
 * EmbeddedTestScript<p/>A wrapper that is used to reuse a TestScript in another TestScript<p/>
 *
 * @author Markus Jorroch, PRODYNA AG, 2010-04-07
 */
public class EmbeddedTestScript extends TestScriptComponent implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final TestScriptElementType TYPE_DEFAULT = TestScriptElementType.EMBEDDED_SCRIPT;

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,1;", "l0,n;u0,n;m0,1;", "l0,255;u0,n;m0,1;",
            "l0,255;u0,n;m0,1;" };

    public static final String TESTSCRIPT = "testScript";

    public static final String TESTSCRIPTKEY = "testScriptKey";

    public static final String DESCRIPTION = "description";

    public static final String FOLDER = "folder";

    private TestScript testScript;

    private Key testScriptKey;

    private Description description;

    private Name folder;

    /** Constructs a new EmbeddedTestScript instance. */
    public EmbeddedTestScript() {
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
     * @param clone the EmbeddedTestScript.
     */
    protected void cloneObject(EmbeddedTestScript clone) {
        super.cloneObject(clone);
        if ((this.getTestScript() != null)) {
            clone.setTestScript(this.getTestScript().cloneObject());
        }
        if ((this.getTestScriptKey() != null)) {
            clone.setTestScriptKey(this.getTestScriptKey().cloneObject());
        }
        if ((this.getDescription() != null)) {
            clone.setDescription(this.getDescription().cloneObject());
        }
        if ((this.getFolder() != null)) {
            clone.setFolder(this.getFolder().cloneObject());
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
        propertyMap.put(TESTSCRIPT, PropertyDescriptorSupport.createDatatype(TESTSCRIPT, TestScript.class, 7,
                PROPERTY_CONSTRAINTS[0], false, PropertyAssociationType.AGGREGATION));
        propertyMap.put(TESTSCRIPTKEY,
                PropertyDescriptorSupport.createBasetype(TESTSCRIPTKEY, Key.class, 8, PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(DESCRIPTION, PropertyDescriptorSupport.createBasetype(DESCRIPTION, Description.class, 9,
                PROPERTY_CONSTRAINTS[2], false));
        propertyMap.put(FOLDER,
                PropertyDescriptorSupport.createBasetype(FOLDER, Name.class, 10, PROPERTY_CONSTRAINTS[3], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(EmbeddedTestScript.getPropertyDescriptor(TESTSCRIPT), this.getTestScript(),
                null));
        properties.add(super.createProperty(EmbeddedTestScript.getPropertyDescriptor(TESTSCRIPTKEY),
                this.testScriptKey, null));
        properties.add(super.createProperty(EmbeddedTestScript.getPropertyDescriptor(DESCRIPTION), this.description,
                null));
        properties.add(super.createProperty(EmbeddedTestScript.getPropertyDescriptor(FOLDER), this.folder, null));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(TESTSCRIPT) && (property.getType() == TestScript.class))) {
            this.setTestScript(((TestScript) property.getInstance()));
            return true;
        } else if ((property.getName().equals(TESTSCRIPTKEY) && (property.getType() == Key.class))) {
            this.setTestScriptKey(((Key) property.getInstance()));
            return true;
        } else if ((property.getName().equals(DESCRIPTION) && (property.getType() == Description.class))) {
            this.setDescription(((Description) property.getInstance()));
            return true;
        } else if ((property.getName().equals(FOLDER) && (property.getType() == Name.class))) {
            this.setFolder(((Name) property.getInstance()));
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
        final EmbeddedTestScript other = ((EmbeddedTestScript) obj);
        if ((this.testScript == null)) {
            if ((other.testScript != null))
                return false;
        } else if ((!this.testScript.equals(other.testScript)))
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
        if ((this.folder == null)) {
            if ((other.folder != null))
                return false;
        } else if ((!this.folder.equals(other.folder)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.testScript == null) ? 0 : this.testScript.hashCode()));
        result = ((PRIME * result) + ((this.testScriptKey == null) ? 0 : this.testScriptKey.hashCode()));
        result = ((PRIME * result) + ((this.description == null) ? 0 : this.description.hashCode()));
        result = ((PRIME * result) + ((this.folder == null) ? 0 : this.folder.hashCode()));
        return result;
    }

    @Override
    public EmbeddedTestScript cloneObject() {
        EmbeddedTestScript clone = new EmbeddedTestScript();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Missing description at method setTestScript.
     *
     * @param testScript the TestScript.
     */
    public void setTestScript(TestScript testScript) {
        this.testScript = testScript;
    }

    /**
     * Missing description at method getTestScript.
     *
     * @return the TestScript.
     */
    public TestScript getTestScript() {
        return this.testScript;
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
     * Missing description at method setTestScriptKey.
     *
     * @param testScriptKey the String.
     */
    public void setTestScriptKey(String testScriptKey) {
        if ((this.testScriptKey == null)) {
            if ((testScriptKey == null)) {
                return;
            }
            this.testScriptKey = new Key();
        }
        this.testScriptKey.setValue(testScriptKey);
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
     * Missing description at method setDescription.
     *
     * @param description the String.
     */
    public void setDescription(String description) {
        if ((this.description == null)) {
            if ((description == null)) {
                return;
            }
            this.description = new Description();
        }
        this.description.setValue(description);
    }

    /**
     * Missing description at method getFolder.
     *
     * @return the Name.
     */
    public Name getFolder() {
        return this.folder;
    }

    /**
     * Missing description at method setFolder.
     *
     * @param folder the Name.
     */
    public void setFolder(Name folder) {
        this.folder = folder;
    }

    /**
     * Missing description at method setFolder.
     *
     * @param folder the String.
     */
    public void setFolder(String folder) {
        if ((this.folder == null)) {
            if ((folder == null)) {
                return;
            }
            this.folder = new Name();
        }
        this.folder.setValue(folder);
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(EmbeddedTestScript.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(EmbeddedTestScript.class).getAllProperties();
    }
}
