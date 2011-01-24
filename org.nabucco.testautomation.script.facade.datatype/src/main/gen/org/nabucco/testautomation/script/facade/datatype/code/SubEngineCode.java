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
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineOperationCode;

/**
 * SubEngineCode<p/>A SubEngineCode<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-10-07
 */
public class SubEngineCode extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "name", "code", "operationList", "description" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m1,1;", "l0,n;m1,1;", "m0,n;",
            "l0,n;m0,1;" };

    private Name name;

    private Key code;

    private List<SubEngineOperationCode> operationList;

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
        if ((this.operationList instanceof NabuccoList<?>)) {
            clone.operationList = ((NabuccoList<SubEngineOperationCode>) this.operationList)
                    .cloneCollection();
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
            this.operationList = new NabuccoList<SubEngineOperationCode>(
                    NabuccoCollectionState.LAZY);
        }
        return ((NabuccoList<SubEngineOperationCode>) this.operationList).getDelegate();
    }

    /**
     * Setter for the OperationListJPA.
     *
     * @param operationList the List<SubEngineOperationCode>.
     */
    void setOperationListJPA(List<SubEngineOperationCode> operationList) {
        if ((this.operationList == null)) {
            this.operationList = new NabuccoList<SubEngineOperationCode>(
                    NabuccoCollectionState.LAZY);
        }
        ((NabuccoList<SubEngineOperationCode>) this.operationList).setDelegate(operationList);
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
        properties.add(new ListProperty<SubEngineOperationCode>(PROPERTY_NAMES[2],
                SubEngineOperationCode.class, PROPERTY_CONSTRAINTS[2], this.operationList));
        properties.add(new BasetypeProperty<Description>(PROPERTY_NAMES[3], Description.class,
                PROPERTY_CONSTRAINTS[3], this.description));
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
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<SubEngineCode>\n");
        appendable.append(super.toString());
        appendable.append((("<name>" + this.name) + "</name>\n"));
        appendable.append((("<code>" + this.code) + "</code>\n"));
        appendable.append((("<description>" + this.description) + "</description>\n"));
        appendable.append("</SubEngineCode>\n");
        return appendable.toString();
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
     * Missing description at method getOperationList.
     *
     * @return the List<SubEngineOperationCode>.
     */
    public List<SubEngineOperationCode> getOperationList() {
        if ((this.operationList == null)) {
            this.operationList = new NabuccoList<SubEngineOperationCode>(
                    NabuccoCollectionState.INITIALIZED);
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
            this.description = new Description();
        }
        this.description.setValue(description);
    }
}
