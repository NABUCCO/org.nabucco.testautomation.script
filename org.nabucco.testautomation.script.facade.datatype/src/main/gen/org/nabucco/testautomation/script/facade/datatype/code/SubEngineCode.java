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
package org.nabucco.testautomation.script.facade.datatype.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Key;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoListImpl;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineOperationCode;

/**
 * SubEngineCode<p/>A SubEngineCode<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-10-07
 */
public class SubEngineCode extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,255;u0,n;m1,1;", "l0,n;u0,n;m1,1;", "m0,n;",
            "l0,255;u0,n;m0,1;" };

    public static final String NAME = "name";

    public static final String CODE = "code";

    public static final String OPERATIONLIST = "operationList";

    public static final String DESCRIPTION = "description";

    private Name name;

    private Key code;

    private NabuccoList<SubEngineOperationCode> operationList;

    private Description description;

    /** Constructs a new SubEngineCode instance. */
    public SubEngineCode() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the SubEngineCode.
     */
    protected void cloneObject(SubEngineCode clone) {
        super.cloneObject(clone);
        if ((this.getName() != null)) {
            clone.setName(this.getName().cloneObject());
        }
        if ((this.getCode() != null)) {
            clone.setCode(this.getCode().cloneObject());
        }
        if ((this.operationList != null)) {
            clone.operationList = this.operationList.cloneCollection();
        }
        if ((this.getDescription() != null)) {
            clone.setDescription(this.getDescription().cloneObject());
        }
    }

    /**
     * Getter for the OperationListJPA.
     *
     * @return the List<SubEngineOperationCode>.
     */
    List<SubEngineOperationCode> getOperationListJPA() {
        if ((this.operationList == null)) {
            this.operationList = new NabuccoListImpl<SubEngineOperationCode>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoListImpl<SubEngineOperationCode>) this.operationList).getDelegate();
    }

    /**
     * Setter for the OperationListJPA.
     *
     * @param operationList the List<SubEngineOperationCode>.
     */
    void setOperationListJPA(List<SubEngineOperationCode> operationList) {
        if ((this.operationList == null)) {
            this.operationList = new NabuccoListImpl<SubEngineOperationCode>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoListImpl<SubEngineOperationCode>) this.operationList).setDelegate(operationList);
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(NabuccoDatatype.class).getPropertyMap());
        propertyMap.put(NAME,
                PropertyDescriptorSupport.createBasetype(NAME, Name.class, 3, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(CODE,
                PropertyDescriptorSupport.createBasetype(CODE, Key.class, 4, PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(OPERATIONLIST, PropertyDescriptorSupport.createCollection(OPERATIONLIST,
                SubEngineOperationCode.class, 5, PROPERTY_CONSTRAINTS[2], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(DESCRIPTION, PropertyDescriptorSupport.createBasetype(DESCRIPTION, Description.class, 6,
                PROPERTY_CONSTRAINTS[3], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(SubEngineCode.getPropertyDescriptor(NAME), this.name, null));
        properties.add(super.createProperty(SubEngineCode.getPropertyDescriptor(CODE), this.code, null));
        properties.add(super.createProperty(SubEngineCode.getPropertyDescriptor(OPERATIONLIST), this.operationList,
                null));
        properties.add(super.createProperty(SubEngineCode.getPropertyDescriptor(DESCRIPTION), this.description, null));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(NAME) && (property.getType() == Name.class))) {
            this.setName(((Name) property.getInstance()));
            return true;
        } else if ((property.getName().equals(CODE) && (property.getType() == Key.class))) {
            this.setCode(((Key) property.getInstance()));
            return true;
        } else if ((property.getName().equals(OPERATIONLIST) && (property.getType() == SubEngineOperationCode.class))) {
            this.operationList = ((NabuccoList<SubEngineOperationCode>) property.getInstance());
            return true;
        } else if ((property.getName().equals(DESCRIPTION) && (property.getType() == Description.class))) {
            this.setDescription(((Description) property.getInstance()));
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
        final SubEngineCode other = ((SubEngineCode) obj);
        if ((this.name == null)) {
            if ((other.name != null))
                return false;
        } else if ((!this.name.equals(other.name)))
            return false;
        if ((this.code == null)) {
            if ((other.code != null))
                return false;
        } else if ((!this.code.equals(other.code)))
            return false;
        if ((this.description == null)) {
            if ((other.description != null))
                return false;
        } else if ((!this.description.equals(other.description)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((PRIME * result) + ((this.code == null) ? 0 : this.code.hashCode()));
        result = ((PRIME * result) + ((this.description == null) ? 0 : this.description.hashCode()));
        return result;
    }

    @Override
    public SubEngineCode cloneObject() {
        SubEngineCode clone = new SubEngineCode();
        this.cloneObject(clone);
        return clone;
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
     * Missing description at method setName.
     *
     * @param name the String.
     */
    public void setName(String name) {
        if ((this.name == null)) {
            if ((name == null)) {
                return;
            }
            this.name = new Name();
        }
        this.name.setValue(name);
    }

    /**
     * Missing description at method getCode.
     *
     * @return the Key.
     */
    public Key getCode() {
        return this.code;
    }

    /**
     * Missing description at method setCode.
     *
     * @param code the Key.
     */
    public void setCode(Key code) {
        this.code = code;
    }

    /**
     * Missing description at method setCode.
     *
     * @param code the String.
     */
    public void setCode(String code) {
        if ((this.code == null)) {
            if ((code == null)) {
                return;
            }
            this.code = new Key();
        }
        this.code.setValue(code);
    }

    /**
     * Missing description at method getOperationList.
     *
     * @return the NabuccoList<SubEngineOperationCode>.
     */
    public NabuccoList<SubEngineOperationCode> getOperationList() {
        if ((this.operationList == null)) {
            this.operationList = new NabuccoListImpl<SubEngineOperationCode>(NabuccoCollectionState.INITIALIZED);
        }
        return this.operationList;
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
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(SubEngineCode.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(SubEngineCode.class).getAllProperties();
    }
}
