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
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComponent;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;

/**
 * Function<p/>A custom implemented function<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2011-09-15
 */
public class Function extends TestScriptComponent implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final TestScriptElementType TYPE_DEFAULT = TestScriptElementType.FUNCTION;

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;" };

    public static final String SCRIPT = "script";

    private Script script;

    private Long scriptRefId;

    /** Constructs a new Function instance. */
    public Function() {
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
     * @param clone the Function.
     */
    protected void cloneObject(Function clone) {
        super.cloneObject(clone);
        if ((this.getScript() != null)) {
            clone.setScript(this.getScript().cloneObject());
        }
        if ((this.getScriptRefId() != null)) {
            clone.setScriptRefId(this.getScriptRefId());
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
        propertyMap.put(SCRIPT, PropertyDescriptorSupport.createDatatype(SCRIPT, Script.class, 7,
                PROPERTY_CONSTRAINTS[0], false, PropertyAssociationType.COMPONENT));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties
                .add(super.createProperty(Function.getPropertyDescriptor(SCRIPT), this.getScript(), this.scriptRefId));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(SCRIPT) && (property.getType() == Script.class))) {
            this.setScript(((Script) property.getInstance()));
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
        final Function other = ((Function) obj);
        if ((this.script == null)) {
            if ((other.script != null))
                return false;
        } else if ((!this.script.equals(other.script)))
            return false;
        if ((this.scriptRefId == null)) {
            if ((other.scriptRefId != null))
                return false;
        } else if ((!this.scriptRefId.equals(other.scriptRefId)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.script == null) ? 0 : this.script.hashCode()));
        result = ((PRIME * result) + ((this.scriptRefId == null) ? 0 : this.scriptRefId.hashCode()));
        return result;
    }

    @Override
    public Function cloneObject() {
        Function clone = new Function();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Missing description at method setScript.
     *
     * @param script the Script.
     */
    public void setScript(Script script) {
        this.script = script;
        if ((script != null)) {
            this.setScriptRefId(script.getId());
        } else {
            this.setScriptRefId(null);
        }
    }

    /**
     * Missing description at method getScript.
     *
     * @return the Script.
     */
    public Script getScript() {
        return this.script;
    }

    /**
     * Getter for the ScriptRefId.
     *
     * @return the Long.
     */
    public Long getScriptRefId() {
        return this.scriptRefId;
    }

    /**
     * Setter for the ScriptRefId.
     *
     * @param scriptRefId the Long.
     */
    public void setScriptRefId(Long scriptRefId) {
        this.scriptRefId = scriptRefId;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(Function.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(Function.class).getAllProperties();
    }
}
