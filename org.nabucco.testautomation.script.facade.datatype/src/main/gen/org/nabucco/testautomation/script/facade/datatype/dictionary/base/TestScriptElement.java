/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary.base;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.EnumProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;

/**
 * TestScriptElement<p/>Abstract superclass of all testscript elements<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public abstract class TestScriptElement extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "name", "propertyList", "type" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m1,1;", "m0,1;", "m1,1;" };

    private Name name;

    private PropertyList propertyList;

    private Long propertyListRefId;

    protected TestScriptElementType type;

    /** Constructs a new TestScriptElement instance. */
    public TestScriptElement() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the TestScriptElement.
     */
    protected void cloneObject(TestScriptElement clone) {
        super.cloneObject(clone);
        if ((this.getName() != null)) {
            clone.setName(this.getName().cloneObject());
        }
        if ((this.getPropertyList() != null)) {
            clone.setPropertyList(this.getPropertyList().cloneObject());
        }
        if ((this.getPropertyListRefId() != null)) {
            clone.setPropertyListRefId(this.getPropertyListRefId());
        }
        clone.setType(this.getType());
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
        properties.add(new DatatypeProperty<PropertyList>(PROPERTY_NAMES[1], PropertyList.class,
                PROPERTY_CONSTRAINTS[1], this.propertyList));
        properties.add(new EnumProperty<TestScriptElementType>(PROPERTY_NAMES[2],
                TestScriptElementType.class, PROPERTY_CONSTRAINTS[2], this.type));
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
        final TestScriptElement other = ((TestScriptElement) obj);
        if ((this.name == null)) {
            if ((other.name != null))
                return false;
        } else if ((!this.name.equals(other.name)))
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
        if ((this.type == null)) {
            if ((other.type != null))
                return false;
        } else if ((!this.type.equals(other.type)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((PRIME * result) + ((this.propertyList == null) ? 0 : this.propertyList
                .hashCode()));
        result = ((PRIME * result) + ((this.propertyListRefId == null) ? 0 : this.propertyListRefId
                .hashCode()));
        result = ((PRIME * result) + ((this.type == null) ? 0 : this.type.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<TestScriptElement>\n");
        appendable.append(super.toString());
        appendable.append((("<name>" + this.name) + "</name>\n"));
        appendable.append((("<propertyList>" + this.propertyList) + "</propertyList>\n"));
        appendable
                .append((("<propertyListRefId>" + this.propertyListRefId) + "</propertyListRefId>\n"));
        appendable.append((("<type>" + this.type) + "</type>\n"));
        appendable.append("</TestScriptElement>\n");
        return appendable.toString();
    }

    @Override
    public abstract TestScriptElement cloneObject();

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
     * Missing description at method getType.
     *
     * @return the TestScriptElementType.
     */
    public TestScriptElementType getType() {
        return this.type;
    }

    /**
     * Missing description at method setType.
     *
     * @param type the TestScriptElementType.
     */
    public void setType(TestScriptElementType type) {
        this.type = type;
    }

    /**
     * Missing description at method setType.
     *
     * @param type the String.
     */
    public void setType(String type) {
        if ((type == null)) {
            this.type = null;
        } else {
            this.type = TestScriptElementType.valueOf(type);
        }
    }
}
