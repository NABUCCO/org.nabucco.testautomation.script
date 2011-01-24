/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.code;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Key;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.ListProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.testautomation.script.facade.datatype.code.CodeParameter;

/**
 * SubEngineActionCode<p/>A SubEngineActionCode<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-10-07
 */
public class SubEngineActionCode extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "name", "code", "description", "parameterList" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m1,1;", "l0,n;m1,1;",
            "l0,n;m0,1;", "m0,n;" };

    private Name name;

    private Key code;

    private Description description;

    private List<CodeParameter> parameterList;

    /** Constructs a new SubEngineActionCode instance. */
    public SubEngineActionCode() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the SubEngineActionCode.
     */
    protected void cloneObject(SubEngineActionCode clone) {
        super.cloneObject(clone);
        if ((this.getName() != null)) {
            clone.setName(this.getName().cloneObject());
        }
        if ((this.getCode() != null)) {
            clone.setCode(this.getCode().cloneObject());
        }
        if ((this.getDescription() != null)) {
            clone.setDescription(this.getDescription().cloneObject());
        }
        if ((this.parameterList instanceof NabuccoList<?>)) {
            clone.parameterList = ((NabuccoList<CodeParameter>) this.parameterList)
                    .cloneCollection();
        }
    }

    /**
     * Getter for the ParameterListJPA.
     *
     * @return the List<CodeParameter>.
     */
    List<CodeParameter> getParameterListJPA() {
        if ((this.parameterList == null)) {
            this.parameterList = new NabuccoList<CodeParameter>(NabuccoCollectionState.EAGER);
        }
        return ((NabuccoList<CodeParameter>) this.parameterList).getDelegate();
    }

    /**
     * Setter for the ParameterListJPA.
     *
     * @param parameterList the List<CodeParameter>.
     */
    void setParameterListJPA(List<CodeParameter> parameterList) {
        if ((this.parameterList == null)) {
            this.parameterList = new NabuccoList<CodeParameter>(NabuccoCollectionState.EAGER);
        }
        ((NabuccoList<CodeParameter>) this.parameterList).setDelegate(parameterList);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new BasetypeProperty<Name>(PROPERTY_NAMES[0], Name.class,
                PROPERTY_CONSTRAINTS[0], this.name));
        properties.add(new BasetypeProperty<Key>(PROPERTY_NAMES[1], Key.class,
                PROPERTY_CONSTRAINTS[1], this.code));
        properties.add(new BasetypeProperty<Description>(PROPERTY_NAMES[2], Description.class,
                PROPERTY_CONSTRAINTS[2], this.description));
        properties.add(new ListProperty<CodeParameter>(PROPERTY_NAMES[3], CodeParameter.class,
                PROPERTY_CONSTRAINTS[3], this.parameterList));
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
        final SubEngineActionCode other = ((SubEngineActionCode) obj);
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
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<SubEngineActionCode>\n");
        appendable.append(super.toString());
        appendable.append((("<name>" + this.name) + "</name>\n"));
        appendable.append((("<code>" + this.code) + "</code>\n"));
        appendable.append((("<description>" + this.description) + "</description>\n"));
        appendable.append("</SubEngineActionCode>\n");
        return appendable.toString();
    }

    @Override
    public SubEngineActionCode cloneObject() {
        SubEngineActionCode clone = new SubEngineActionCode();
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
            this.code = new Key();
        }
        this.code.setValue(code);
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
            this.description = new Description();
        }
        this.description.setValue(description);
    }

    /**
     * Missing description at method getParameterList.
     *
     * @return the List<CodeParameter>.
     */
    public List<CodeParameter> getParameterList() {
        if ((this.parameterList == null)) {
            this.parameterList = new NabuccoList<CodeParameter>(NabuccoCollectionState.INITIALIZED);
        }
        return this.parameterList;
    }
}
