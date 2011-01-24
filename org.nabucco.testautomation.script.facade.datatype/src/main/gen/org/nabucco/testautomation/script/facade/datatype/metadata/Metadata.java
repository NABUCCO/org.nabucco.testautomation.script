/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.metadata;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.ListProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineOperationCode;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;

/**
 * Metadata<p/>The Metadata-class<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public class Metadata extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "name", "description", "subEngine",
            "operation", "labelList", "children", "propertyList", "parent" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m1,1;", "l0,n;m0,1;", "m0,1;",
            "m0,1;", "m0,n;", "m0,n;", "m0,1;", "m0,1;" };

    private Name name;

    private Description description;

    private SubEngineCode subEngine;

    private SubEngineOperationCode operation;

    private List<MetadataLabel> labelList;

    private List<Metadata> children;

    private PropertyList propertyList;

    private Long propertyListRefId;

    private Metadata parent;

    /** Constructs a new Metadata instance. */
    public Metadata() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the Metadata.
     */
    protected void cloneObject(Metadata clone) {
        super.cloneObject(clone);
        if ((this.getName() != null)) {
            clone.setName(this.getName().cloneObject());
        }
        if ((this.getDescription() != null)) {
            clone.setDescription(this.getDescription().cloneObject());
        }
        if ((this.getSubEngine() != null)) {
            clone.setSubEngine(this.getSubEngine().cloneObject());
        }
        if ((this.getOperation() != null)) {
            clone.setOperation(this.getOperation().cloneObject());
        }
        if ((this.labelList instanceof NabuccoList<?>)) {
            clone.labelList = ((NabuccoList<MetadataLabel>) this.labelList).cloneCollection();
        }
        if ((this.children instanceof NabuccoList<?>)) {
            clone.children = ((NabuccoList<Metadata>) this.children).cloneCollection();
        }
        if ((this.getPropertyList() != null)) {
            clone.setPropertyList(this.getPropertyList().cloneObject());
        }
        if ((this.getPropertyListRefId() != null)) {
            clone.setPropertyListRefId(this.getPropertyListRefId());
        }
        if ((this.getParent() != null)) {
            clone.setParent(this.getParent().cloneObject());
        }
    }

    /**
     * Getter for the LabelListJPA.
     *
     * @return the List<MetadataLabel>.
     */
    List<MetadataLabel> getLabelListJPA() {
        if ((this.labelList == null)) {
            this.labelList = new NabuccoList<MetadataLabel>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoList<MetadataLabel>) this.labelList).getDelegate();
    }

    /**
     * Setter for the LabelListJPA.
     *
     * @param labelList the List<MetadataLabel>.
     */
    void setLabelListJPA(List<MetadataLabel> labelList) {
        if ((this.labelList == null)) {
            this.labelList = new NabuccoList<MetadataLabel>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoList<MetadataLabel>) this.labelList).setDelegate(labelList);
    }

    /**
     * Getter for the ChildrenJPA.
     *
     * @return the List<Metadata>.
     */
    List<Metadata> getChildrenJPA() {
        if ((this.children == null)) {
            this.children = new NabuccoList<Metadata>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoList<Metadata>) this.children).getDelegate();
    }

    /**
     * Setter for the ChildrenJPA.
     *
     * @param children the List<Metadata>.
     */
    void setChildrenJPA(List<Metadata> children) {
        if ((this.children == null)) {
            this.children = new NabuccoList<Metadata>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoList<Metadata>) this.children).setDelegate(children);
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
        properties.add(new BasetypeProperty<Description>(PROPERTY_NAMES[1], Description.class,
                PROPERTY_CONSTRAINTS[1], this.description));
        properties.add(new DatatypeProperty<SubEngineCode>(PROPERTY_NAMES[2], SubEngineCode.class,
                PROPERTY_CONSTRAINTS[2], this.subEngine));
        properties.add(new DatatypeProperty<SubEngineOperationCode>(PROPERTY_NAMES[3],
                SubEngineOperationCode.class, PROPERTY_CONSTRAINTS[3], this.operation));
        properties.add(new ListProperty<MetadataLabel>(PROPERTY_NAMES[4], MetadataLabel.class,
                PROPERTY_CONSTRAINTS[4], this.labelList));
        properties.add(new ListProperty<Metadata>(PROPERTY_NAMES[5], Metadata.class,
                PROPERTY_CONSTRAINTS[5], this.children));
        properties.add(new DatatypeProperty<PropertyList>(PROPERTY_NAMES[6], PropertyList.class,
                PROPERTY_CONSTRAINTS[6], this.propertyList));
        properties.add(new DatatypeProperty<Metadata>(PROPERTY_NAMES[7], Metadata.class,
                PROPERTY_CONSTRAINTS[7], this.parent));
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
        final Metadata other = ((Metadata) obj);
        if ((this.name == null)) {
            if ((other.name != null))
                return false;
        } else if ((!this.name.equals(other.name)))
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
        if ((this.operation == null)) {
            if ((other.operation != null))
                return false;
        } else if ((!this.operation.equals(other.operation)))
            return false;
        if ((this.propertyList == null)) {
            if ((other.propertyList != null))
                return false;
        } else if ((!this.propertyList.equals(other.propertyList)))
            return false;
        if ((this.propertyListRefId == null)) {
            if ((other.propertyListRefId != null))
                return false;
        } else if ((!this.propertyListRefId.equals(other.propertyListRefId)))
            return false;
        if ((this.parent == null)) {
            if ((other.parent != null))
                return false;
        } else if ((!this.parent.equals(other.parent)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((PRIME * result) + ((this.description == null) ? 0 : this.description.hashCode()));
        result = ((PRIME * result) + ((this.subEngine == null) ? 0 : this.subEngine.hashCode()));
        result = ((PRIME * result) + ((this.operation == null) ? 0 : this.operation.hashCode()));
        result = ((PRIME * result) + ((this.propertyList == null) ? 0 : this.propertyList
                .hashCode()));
        result = ((PRIME * result) + ((this.propertyListRefId == null) ? 0 : this.propertyListRefId
                .hashCode()));
        result = ((PRIME * result) + ((this.parent == null) ? 0 : this.parent.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<Metadata>\n");
        appendable.append(super.toString());
        appendable.append((("<name>" + this.name) + "</name>\n"));
        appendable.append((("<description>" + this.description) + "</description>\n"));
        appendable.append((("<subEngine>" + this.subEngine) + "</subEngine>\n"));
        appendable.append((("<operation>" + this.operation) + "</operation>\n"));
        appendable.append((("<propertyList>" + this.propertyList) + "</propertyList>\n"));
        appendable
                .append((("<propertyListRefId>" + this.propertyListRefId) + "</propertyListRefId>\n"));
        appendable.append((("<parent>" + this.parent) + "</parent>\n"));
        appendable.append("</Metadata>\n");
        return appendable.toString();
    }

    @Override
    public Metadata cloneObject() {
        Metadata clone = new Metadata();
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
     * Missing description at method setSubEngine.
     *
     * @param subEngine the SubEngineCode.
     */
    public void setSubEngine(SubEngineCode subEngine) {
        this.subEngine = subEngine;
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
     * Missing description at method setOperation.
     *
     * @param operation the SubEngineOperationCode.
     */
    public void setOperation(SubEngineOperationCode operation) {
        this.operation = operation;
    }

    /**
     * Missing description at method getOperation.
     *
     * @return the SubEngineOperationCode.
     */
    public SubEngineOperationCode getOperation() {
        return this.operation;
    }

    /**
     * Missing description at method getLabelList.
     *
     * @return the List<MetadataLabel>.
     */
    public List<MetadataLabel> getLabelList() {
        if ((this.labelList == null)) {
            this.labelList = new NabuccoList<MetadataLabel>(NabuccoCollectionState.INITIALIZED);
        }
        return this.labelList;
    }

    /**
     * Missing description at method getChildren.
     *
     * @return the List<Metadata>.
     */
    public List<Metadata> getChildren() {
        if ((this.children == null)) {
            this.children = new NabuccoList<Metadata>(NabuccoCollectionState.INITIALIZED);
        }
        return this.children;
    }

    /**
     * Missing description at method setPropertyList.
     *
     * @param propertyList the PropertyList.
     */
    public void setPropertyList(PropertyList propertyList) {
        this.propertyList = propertyList;
        if ((propertyList != null)) {
            this.setPropertyListRefId(propertyList.getId());
        } else {
            this.setPropertyListRefId(null);
        }
    }

    /**
     * Missing description at method getPropertyList.
     *
     * @return the PropertyList.
     */
    public PropertyList getPropertyList() {
        return this.propertyList;
    }

    /**
     * Getter for the PropertyListRefId.
     *
     * @return the Long.
     */
    public Long getPropertyListRefId() {
        return this.propertyListRefId;
    }

    /**
     * Setter for the PropertyListRefId.
     *
     * @param propertyListRefId the Long.
     */
    public void setPropertyListRefId(Long propertyListRefId) {
        this.propertyListRefId = propertyListRefId;
    }

    /**
     * Missing description at method setParent.
     *
     * @param parent the Metadata.
     */
    public void setParent(Metadata parent) {
        this.parent = parent;
    }

    /**
     * Missing description at method getParent.
     *
     * @return the Metadata.
     */
    public Metadata getParent() {
        return this.parent;
    }
}
