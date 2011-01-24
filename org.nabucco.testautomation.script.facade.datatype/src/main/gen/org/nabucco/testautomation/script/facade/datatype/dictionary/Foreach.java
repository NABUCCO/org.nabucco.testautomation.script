/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyReference;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;

/**
 * Foreach<p/>A Foreach-loop<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-08
 */
public class Foreach extends TestScriptComposite implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "iterableRef", "elementName" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m1,1;", "l0,n;m1,1;" };

    private PropertyReference iterableRef;

    private Name elementName;

    /** Constructs a new Foreach instance. */
    public Foreach() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
        type = TestScriptElementType.FOREACH;
    }

    /**
     * CloneObject.
     *
     * @param clone the Foreach.
     */
    protected void cloneObject(Foreach clone) {
        super.cloneObject(clone);
        if ((this.getIterableRef() != null)) {
            clone.setIterableRef(this.getIterableRef().cloneObject());
        }
        if ((this.getElementName() != null)) {
            clone.setElementName(this.getElementName().cloneObject());
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
        properties.add(new BasetypeProperty<PropertyReference>(PROPERTY_NAMES[0],
                PropertyReference.class, PROPERTY_CONSTRAINTS[0], this.iterableRef));
        properties.add(new BasetypeProperty<Name>(PROPERTY_NAMES[1], Name.class,
                PROPERTY_CONSTRAINTS[1], this.elementName));
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
        final Foreach other = ((Foreach) obj);
        if ((this.iterableRef == null)) {
            if ((other.iterableRef != null))
                return false;
        } else if ((!this.iterableRef.equals(other.iterableRef)))
            return false;
        if ((this.elementName == null)) {
            if ((other.elementName != null))
                return false;
        } else if ((!this.elementName.equals(other.elementName)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.iterableRef == null) ? 0 : this.iterableRef.hashCode()));
        result = ((PRIME * result) + ((this.elementName == null) ? 0 : this.elementName.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<Foreach>\n");
        appendable.append(super.toString());
        appendable.append((("<iterableRef>" + this.iterableRef) + "</iterableRef>\n"));
        appendable.append((("<elementName>" + this.elementName) + "</elementName>\n"));
        appendable.append("</Foreach>\n");
        return appendable.toString();
    }

    @Override
    public Foreach cloneObject() {
        Foreach clone = new Foreach();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Missing description at method getIterableRef.
     *
     * @return the PropertyReference.
     */
    public PropertyReference getIterableRef() {
        return this.iterableRef;
    }

    /**
     * Missing description at method setIterableRef.
     *
     * @param iterableRef the PropertyReference.
     */
    public void setIterableRef(PropertyReference iterableRef) {
        this.iterableRef = iterableRef;
    }

    /**
     * Missing description at method setIterableRef.
     *
     * @param iterableRef the String.
     */
    public void setIterableRef(String iterableRef) {
        if ((this.iterableRef == null)) {
            this.iterableRef = new PropertyReference();
        }
        this.iterableRef.setValue(iterableRef);
    }

    /**
     * Missing description at method getElementName.
     *
     * @return the Name.
     */
    public Name getElementName() {
        return this.elementName;
    }

    /**
     * Missing description at method setElementName.
     *
     * @param elementName the Name.
     */
    public void setElementName(Name elementName) {
        this.elementName = elementName;
    }

    /**
     * Missing description at method setElementName.
     *
     * @param elementName the String.
     */
    public void setElementName(String elementName) {
        if ((this.elementName == null)) {
            this.elementName = new Name();
        }
        this.elementName.setValue(elementName);
    }
}
