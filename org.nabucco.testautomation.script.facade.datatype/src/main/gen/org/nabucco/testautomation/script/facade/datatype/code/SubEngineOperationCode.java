/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.nabucco.testautomation.script.facade.datatype.code.CodeParameter;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineActionCode;

/**
 * SubEngineOperationCode<p/>A SubEngineOperationCode<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-10-07
 */
public class SubEngineOperationCode extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,255;m1,1;", "l0,n;m1,1;", "m0,n;",
            "l0,255;m0,1;", "m0,n;" };

    public static final String NAME = "name";

    public static final String CODE = "code";

    public static final String ACTIONLIST = "actionList";

    public static final String DESCRIPTION = "description";

    public static final String PARAMETERLIST = "parameterList";

    private Name name;

    private Key code;

    private NabuccoList<SubEngineActionCode> actionList;

    private Description description;

    private NabuccoList<CodeParameter> parameterList;

    /** Constructs a new SubEngineOperationCode instance. */
    public SubEngineOperationCode() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the SubEngineOperationCode.
     */
    protected void cloneObject(SubEngineOperationCode clone) {
        super.cloneObject(clone);
        if ((this.getName() != null)) {
            clone.setName(this.getName().cloneObject());
        }
        if ((this.getCode() != null)) {
            clone.setCode(this.getCode().cloneObject());
        }
        if ((this.actionList != null)) {
            clone.actionList = this.actionList.cloneCollection();
        }
        if ((this.getDescription() != null)) {
            clone.setDescription(this.getDescription().cloneObject());
        }
        if ((this.parameterList != null)) {
            clone.parameterList = this.parameterList.cloneCollection();
        }
    }

    /**
     * Getter for the ActionListJPA.
     *
     * @return the List<SubEngineActionCode>.
     */
    List<SubEngineActionCode> getActionListJPA() {
        if ((this.actionList == null)) {
            this.actionList = new NabuccoListImpl<SubEngineActionCode>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoListImpl<SubEngineActionCode>) this.actionList).getDelegate();
    }

    /**
     * Setter for the ActionListJPA.
     *
     * @param actionList the List<SubEngineActionCode>.
     */
    void setActionListJPA(List<SubEngineActionCode> actionList) {
        if ((this.actionList == null)) {
            this.actionList = new NabuccoListImpl<SubEngineActionCode>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoListImpl<SubEngineActionCode>) this.actionList).setDelegate(actionList);
    }

    /**
     * Getter for the ParameterListJPA.
     *
     * @return the List<CodeParameter>.
     */
    List<CodeParameter> getParameterListJPA() {
        if ((this.parameterList == null)) {
            this.parameterList = new NabuccoListImpl<CodeParameter>(NabuccoCollectionState.EAGER);
        }
        return ((NabuccoListImpl<CodeParameter>) this.parameterList).getDelegate();
    }

    /**
     * Setter for the ParameterListJPA.
     *
     * @param parameterList the List<CodeParameter>.
     */
    void setParameterListJPA(List<CodeParameter> parameterList) {
        if ((this.parameterList == null)) {
            this.parameterList = new NabuccoListImpl<CodeParameter>(NabuccoCollectionState.EAGER);
        }
        ((NabuccoListImpl<CodeParameter>) this.parameterList).setDelegate(parameterList);
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(NabuccoDatatype.class)
                .getPropertyMap());
        propertyMap.put(NAME, PropertyDescriptorSupport.createBasetype(NAME, Name.class, 2,
                PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(CODE, PropertyDescriptorSupport.createBasetype(CODE, Key.class, 3,
                PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(ACTIONLIST, PropertyDescriptorSupport.createCollection(ACTIONLIST,
                SubEngineActionCode.class, 4, PROPERTY_CONSTRAINTS[2], false,
                PropertyAssociationType.COMPOSITION));
        propertyMap.put(DESCRIPTION, PropertyDescriptorSupport.createBasetype(DESCRIPTION,
                Description.class, 5, PROPERTY_CONSTRAINTS[3], false));
        propertyMap.put(PARAMETERLIST, PropertyDescriptorSupport.createCollection(PARAMETERLIST,
                CodeParameter.class, 6, PROPERTY_CONSTRAINTS[4], false,
                PropertyAssociationType.COMPOSITION));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty> getProperties() {
        List<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(SubEngineOperationCode.getPropertyDescriptor(NAME),
                this.name, null));
        properties.add(super.createProperty(SubEngineOperationCode.getPropertyDescriptor(CODE),
                this.code, null));
        properties.add(super.createProperty(
                SubEngineOperationCode.getPropertyDescriptor(ACTIONLIST), this.actionList, null));
        properties.add(super.createProperty(
                SubEngineOperationCode.getPropertyDescriptor(DESCRIPTION), this.description, null));
        properties.add(super.createProperty(
                SubEngineOperationCode.getPropertyDescriptor(PARAMETERLIST), this.parameterList,
                null));
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
        } else if ((property.getName().equals(ACTIONLIST) && (property.getType() == SubEngineActionCode.class))) {
            this.actionList = ((NabuccoList<SubEngineActionCode>) property.getInstance());
            return true;
        } else if ((property.getName().equals(DESCRIPTION) && (property.getType() == Description.class))) {
            this.setDescription(((Description) property.getInstance()));
            return true;
        } else if ((property.getName().equals(PARAMETERLIST) && (property.getType() == CodeParameter.class))) {
            this.parameterList = ((NabuccoList<CodeParameter>) property.getInstance());
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
        final SubEngineOperationCode other = ((SubEngineOperationCode) obj);
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
    public SubEngineOperationCode cloneObject() {
        SubEngineOperationCode clone = new SubEngineOperationCode();
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
     * Missing description at method getActionList.
     *
     * @return the NabuccoList<SubEngineActionCode>.
     */
    public NabuccoList<SubEngineActionCode> getActionList() {
        if ((this.actionList == null)) {
            this.actionList = new NabuccoListImpl<SubEngineActionCode>(
                    NabuccoCollectionState.INITIALIZED);
        }
        return this.actionList;
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
     * Missing description at method getParameterList.
     *
     * @return the NabuccoList<CodeParameter>.
     */
    public NabuccoList<CodeParameter> getParameterList() {
        if ((this.parameterList == null)) {
            this.parameterList = new NabuccoListImpl<CodeParameter>(
                    NabuccoCollectionState.INITIALIZED);
        }
        return this.parameterList;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(SubEngineOperationCode.class)
                .getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(SubEngineOperationCode.class)
                .getAllProperties();
    }
}
