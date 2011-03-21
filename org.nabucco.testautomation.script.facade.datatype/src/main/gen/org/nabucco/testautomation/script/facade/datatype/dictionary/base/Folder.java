/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Flag;
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
import org.nabucco.testautomation.facade.datatype.base.ExportDatatype;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;

/**
 * Folder<p/>A Folder containing TestScripts and/or other folders<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-07
 */
public class Folder extends ExportDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,255;m1,1;", "l0,n;m1,1;", "m0,n;",
            "m0,n;" };

    public static final String NAME = "name";

    public static final String ROOT = "root";

    public static final String SUBFOLDERLIST = "subFolderList";

    public static final String TESTSCRIPTLIST = "testScriptList";

    private Name name;

    private Flag root;

    private NabuccoList<Folder> subFolderList;

    private NabuccoList<TestScript> testScriptList;

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
        if ((this.subFolderList != null)) {
            clone.subFolderList = this.subFolderList.cloneCollection();
        }
        if ((this.testScriptList != null)) {
            clone.testScriptList = this.testScriptList.cloneCollection();
        }
    }

    /**
     * Getter for the SubFolderListJPA.
     *
     * @return the List<Folder>.
     */
    List<Folder> getSubFolderListJPA() {
        if ((this.subFolderList == null)) {
            this.subFolderList = new NabuccoListImpl<Folder>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoListImpl<Folder>) this.subFolderList).getDelegate();
    }

    /**
     * Setter for the SubFolderListJPA.
     *
     * @param subFolderList the List<Folder>.
     */
    void setSubFolderListJPA(List<Folder> subFolderList) {
        if ((this.subFolderList == null)) {
            this.subFolderList = new NabuccoListImpl<Folder>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoListImpl<Folder>) this.subFolderList).setDelegate(subFolderList);
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(ExportDatatype.class)
                .getPropertyMap());
        propertyMap.put(NAME, PropertyDescriptorSupport.createBasetype(NAME, Name.class, 4,
                PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(ROOT, PropertyDescriptorSupport.createBasetype(ROOT, Flag.class, 5,
                PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(SUBFOLDERLIST, PropertyDescriptorSupport.createCollection(SUBFOLDERLIST,
                Folder.class, 6, PROPERTY_CONSTRAINTS[2], false,
                PropertyAssociationType.COMPOSITION));
        propertyMap.put(TESTSCRIPTLIST, PropertyDescriptorSupport.createCollection(TESTSCRIPTLIST,
                TestScript.class, 7, PROPERTY_CONSTRAINTS[3], false,
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
        properties.add(super.createProperty(Folder.getPropertyDescriptor(NAME), this.name, null));
        properties.add(super.createProperty(Folder.getPropertyDescriptor(ROOT), this.root, null));
        properties.add(super.createProperty(Folder.getPropertyDescriptor(SUBFOLDERLIST),
                this.subFolderList, null));
        properties.add(super.createProperty(Folder.getPropertyDescriptor(TESTSCRIPTLIST),
                this.testScriptList, null));
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
        } else if ((property.getName().equals(ROOT) && (property.getType() == Flag.class))) {
            this.setRoot(((Flag) property.getInstance()));
            return true;
        } else if ((property.getName().equals(SUBFOLDERLIST) && (property.getType() == Folder.class))) {
            this.subFolderList = ((NabuccoList<Folder>) property.getInstance());
            return true;
        } else if ((property.getName().equals(TESTSCRIPTLIST) && (property.getType() == TestScript.class))) {
            this.testScriptList = ((NabuccoList<TestScript>) property.getInstance());
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
            if ((name == null)) {
                return;
            }
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
            if ((root == null)) {
                return;
            }
            this.root = new Flag();
        }
        this.root.setValue(root);
    }

    /**
     * Missing description at method getSubFolderList.
     *
     * @return the NabuccoList<Folder>.
     */
    public NabuccoList<Folder> getSubFolderList() {
        if ((this.subFolderList == null)) {
            this.subFolderList = new NabuccoListImpl<Folder>(NabuccoCollectionState.INITIALIZED);
        }
        return this.subFolderList;
    }

    /**
     * Missing description at method getTestScriptList.
     *
     * @return the NabuccoList<TestScript>.
     */
    public NabuccoList<TestScript> getTestScriptList() {
        if ((this.testScriptList == null)) {
            this.testScriptList = new NabuccoListImpl<TestScript>(
                    NabuccoCollectionState.INITIALIZED);
        }
        return this.testScriptList;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(Folder.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(Folder.class).getAllProperties();
    }
}
