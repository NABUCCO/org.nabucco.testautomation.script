/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary.base;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.ListProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;

/**
 * Folder<p/>A Folder containing TestScripts and/or other folders<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-07
 */
public class Folder extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "name", "root", "subFolderList",
            "testScriptList" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m1,1;", "l0,n;m1,1;", "m0,n;",
            "m0,n;" };

    private Name name;

    private Flag root;

    private List<Folder> subFolderList;

    private List<TestScript> testScriptList;

    /** Constructs a new Folder instance. */
    public Folder() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the Folder.
     */
    protected void cloneObject(Folder clone) {
        super.cloneObject(clone);
        if ((this.getName() != null)) {
            clone.setName(this.getName().cloneObject());
        }
        if ((this.getRoot() != null)) {
            clone.setRoot(this.getRoot().cloneObject());
        }
        if ((this.subFolderList instanceof NabuccoList<?>)) {
            clone.subFolderList = ((NabuccoList<Folder>) this.subFolderList).cloneCollection();
        }
        if ((this.testScriptList instanceof NabuccoList<?>)) {
            clone.testScriptList = ((NabuccoList<TestScript>) this.testScriptList)
                    .cloneCollection();
        }
    }

    /**
     * Getter for the SubFolderListJPA.
     *
     * @return the List<Folder>.
     */
    List<Folder> getSubFolderListJPA() {
        if ((this.subFolderList == null)) {
            this.subFolderList = new NabuccoList<Folder>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoList<Folder>) this.subFolderList).getDelegate();
    }

    /**
     * Setter for the SubFolderListJPA.
     *
     * @param subFolderList the List<Folder>.
     */
    void setSubFolderListJPA(List<Folder> subFolderList) {
        if ((this.subFolderList == null)) {
            this.subFolderList = new NabuccoList<Folder>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoList<Folder>) this.subFolderList).setDelegate(subFolderList);
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
        properties.add(new BasetypeProperty<Flag>(PROPERTY_NAMES[1], Flag.class,
                PROPERTY_CONSTRAINTS[1], this.root));
        properties.add(new ListProperty<Folder>(PROPERTY_NAMES[2], Folder.class,
                PROPERTY_CONSTRAINTS[2], this.subFolderList));
        properties.add(new ListProperty<TestScript>(PROPERTY_NAMES[3], TestScript.class,
                PROPERTY_CONSTRAINTS[3], this.testScriptList));
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
        final Folder other = ((Folder) obj);
        if ((this.name == null)) {
            if ((other.name != null))
                return false;
        } else if ((!this.name.equals(other.name)))
            return false;
        if ((this.root == null)) {
            if ((other.root != null))
                return false;
        } else if ((!this.root.equals(other.root)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((PRIME * result) + ((this.root == null) ? 0 : this.root.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<Folder>\n");
        appendable.append(super.toString());
        appendable.append((("<name>" + this.name) + "</name>\n"));
        appendable.append((("<root>" + this.root) + "</root>\n"));
        appendable.append("</Folder>\n");
        return appendable.toString();
    }

    @Override
    public Folder cloneObject() {
        Folder clone = new Folder();
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
     * Missing description at method getRoot.
     *
     * @return the Flag.
     */
    public Flag getRoot() {
        return this.root;
    }

    /**
     * Missing description at method setRoot.
     *
     * @param root the Flag.
     */
    public void setRoot(Flag root) {
        this.root = root;
    }

    /**
     * Missing description at method setRoot.
     *
     * @param root the Boolean.
     */
    public void setRoot(Boolean root) {
        if ((this.root == null)) {
            this.root = new Flag();
        }
        this.root.setValue(root);
    }

    /**
     * Missing description at method getSubFolderList.
     *
     * @return the List<Folder>.
     */
    public List<Folder> getSubFolderList() {
        if ((this.subFolderList == null)) {
            this.subFolderList = new NabuccoList<Folder>(NabuccoCollectionState.INITIALIZED);
        }
        return this.subFolderList;
    }

    /**
     * Missing description at method getTestScriptList.
     *
     * @return the List<TestScript>.
     */
    public List<TestScript> getTestScriptList() {
        if ((this.testScriptList == null)) {
            this.testScriptList = new NabuccoList<TestScript>(NabuccoCollectionState.INITIALIZED);
        }
        return this.testScriptList;
    }
}
