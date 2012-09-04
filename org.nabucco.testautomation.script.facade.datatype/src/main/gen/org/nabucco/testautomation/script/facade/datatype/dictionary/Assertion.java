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
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.testautomation.property.facade.datatype.base.Text;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComponent;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;

/**
 * Assertion<p/>An assertion<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public class Assertion extends TestScriptComponent implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final TestScriptElementType TYPE_DEFAULT = TestScriptElementType.ASSERTION;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;u0,n;m0,1;", "l0,n;u0,n;m0,1;", "m0,1;" };

    public static final String MESSAGE = "message";

    public static final String FAIL = "fail";

    public static final String ASSERTIONSCRIPT = "assertionScript";

    private Text message;

    private Flag fail;

    private Script assertionScript;

    private Long assertionScriptRefId;

    /** Constructs a new Assertion instance. */
    public Assertion() {
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
     * @param clone the Assertion.
     */
    protected void cloneObject(Assertion clone) {
        super.cloneObject(clone);
        if ((this.getMessage() != null)) {
            clone.setMessage(this.getMessage().cloneObject());
        }
        if ((this.getFail() != null)) {
            clone.setFail(this.getFail().cloneObject());
        }
        if ((this.getAssertionScript() != null)) {
            clone.setAssertionScript(this.getAssertionScript().cloneObject());
        }
        if ((this.getAssertionScriptRefId() != null)) {
            clone.setAssertionScriptRefId(this.getAssertionScriptRefId());
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
        propertyMap.put(MESSAGE,
                PropertyDescriptorSupport.createBasetype(MESSAGE, Text.class, 7, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(FAIL,
                PropertyDescriptorSupport.createBasetype(FAIL, Flag.class, 8, PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(ASSERTIONSCRIPT, PropertyDescriptorSupport.createDatatype(ASSERTIONSCRIPT, Script.class, 9,
                PROPERTY_CONSTRAINTS[2], false, PropertyAssociationType.COMPONENT));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(Assertion.getPropertyDescriptor(MESSAGE), this.message, null));
        properties.add(super.createProperty(Assertion.getPropertyDescriptor(FAIL), this.fail, null));
        properties.add(super.createProperty(Assertion.getPropertyDescriptor(ASSERTIONSCRIPT),
                this.getAssertionScript(), this.assertionScriptRefId));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(MESSAGE) && (property.getType() == Text.class))) {
            this.setMessage(((Text) property.getInstance()));
            return true;
        } else if ((property.getName().equals(FAIL) && (property.getType() == Flag.class))) {
            this.setFail(((Flag) property.getInstance()));
            return true;
        } else if ((property.getName().equals(ASSERTIONSCRIPT) && (property.getType() == Script.class))) {
            this.setAssertionScript(((Script) property.getInstance()));
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
        final Assertion other = ((Assertion) obj);
        if ((this.message == null)) {
            if ((other.message != null))
                return false;
        } else if ((!this.message.equals(other.message)))
            return false;
        if ((this.fail == null)) {
            if ((other.fail != null))
                return false;
        } else if ((!this.fail.equals(other.fail)))
            return false;
        if ((this.assertionScript == null)) {
            if ((other.assertionScript != null))
                return false;
        } else if ((!this.assertionScript.equals(other.assertionScript)))
            return false;
        if ((this.assertionScriptRefId == null)) {
            if ((other.assertionScriptRefId != null))
                return false;
        } else if ((!this.assertionScriptRefId.equals(other.assertionScriptRefId)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.message == null) ? 0 : this.message.hashCode()));
        result = ((PRIME * result) + ((this.fail == null) ? 0 : this.fail.hashCode()));
        result = ((PRIME * result) + ((this.assertionScript == null) ? 0 : this.assertionScript.hashCode()));
        result = ((PRIME * result) + ((this.assertionScriptRefId == null) ? 0 : this.assertionScriptRefId.hashCode()));
        return result;
    }

    @Override
    public Assertion cloneObject() {
        Assertion clone = new Assertion();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Missing description at method getMessage.
     *
     * @return the Text.
     */
    public Text getMessage() {
        return this.message;
    }

    /**
     * Missing description at method setMessage.
     *
     * @param message the Text.
     */
    public void setMessage(Text message) {
        this.message = message;
    }

    /**
     * Missing description at method setMessage.
     *
     * @param message the String.
     */
    public void setMessage(String message) {
        if ((this.message == null)) {
            if ((message == null)) {
                return;
            }
            this.message = new Text();
        }
        this.message.setValue(message);
    }

    /**
     * Missing description at method getFail.
     *
     * @return the Flag.
     */
    public Flag getFail() {
        return this.fail;
    }

    /**
     * Missing description at method setFail.
     *
     * @param fail the Flag.
     */
    public void setFail(Flag fail) {
        this.fail = fail;
    }

    /**
     * Missing description at method setFail.
     *
     * @param fail the Boolean.
     */
    public void setFail(Boolean fail) {
        if ((this.fail == null)) {
            if ((fail == null)) {
                return;
            }
            this.fail = new Flag();
        }
        this.fail.setValue(fail);
    }

    /**
     * Missing description at method setAssertionScript.
     *
     * @param assertionScript the Script.
     */
    public void setAssertionScript(Script assertionScript) {
        this.assertionScript = assertionScript;
        if ((assertionScript != null)) {
            this.setAssertionScriptRefId(assertionScript.getId());
        } else {
            this.setAssertionScriptRefId(null);
        }
    }

    /**
     * Missing description at method getAssertionScript.
     *
     * @return the Script.
     */
    public Script getAssertionScript() {
        return this.assertionScript;
    }

    /**
     * Getter for the AssertionScriptRefId.
     *
     * @return the Long.
     */
    public Long getAssertionScriptRefId() {
        return this.assertionScriptRefId;
    }

    /**
     * Setter for the AssertionScriptRefId.
     *
     * @param assertionScriptRefId the Long.
     */
    public void setAssertionScriptRefId(Long assertionScriptRefId) {
        this.assertionScriptRefId = assertionScriptRefId;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(Assertion.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(Assertion.class).getAllProperties();
    }
}
