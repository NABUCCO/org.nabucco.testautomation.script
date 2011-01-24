/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Key;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;

/**
 * TestScript<p/>A TestScript<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public class TestScript extends TestScriptComposite implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "testScriptKey", "description", "folder" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,16;m0,1;", "l0,n;m0,1;", "m0,1;" };

    private Key testScriptKey;

    private Description description;

    private Folder folder;

    /** Constructs a new TestScript instance. */
    public TestScript() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
        type = TestScriptElementType.SCRIPT;
    }

    /**
     * CloneObject.
     *
     * @param clone the TestScript.
     */
    protected void cloneObject(TestScript clone) {
        super.cloneObject(clone);
        if ((this.getTestScriptKey() != null)) {
            clone.setTestScriptKey(this.getTestScriptKey().cloneObject());
        }
        if ((this.getDescription() != null)) {
            clone.setDescription(this.getDescription().cloneObject());
        }
        if ((this.getFolder() != null)) {
            clone.setFolder(this.getFolder().cloneObject());
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
        properties.add(new BasetypeProperty<Key>(PROPERTY_NAMES[0], Key.class,
                PROPERTY_CONSTRAINTS[0], this.testScriptKey));
        properties.add(new BasetypeProperty<Description>(PROPERTY_NAMES[1], Description.class,
                PROPERTY_CONSTRAINTS[1], this.description));
        properties.add(new DatatypeProperty<Folder>(PROPERTY_NAMES[2], Folder.class,
                PROPERTY_CONSTRAINTS[2], this.folder));
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
        final TestScript other = ((TestScript) obj);
        if ((this.testScriptKey == null)) {
            if ((other.testScriptKey != null))
                return false;
        } else if ((!this.testScriptKey.equals(other.testScriptKey)))
            return false;
        if ((this.description == null)) {
            if ((other.description != null))
                return false;
        } else if ((!this.description.equals(other.description)))
            return false;
        if ((this.folder == null)) {
            if ((other.folder != null))
                return false;
        } else if ((!this.folder.equals(other.folder)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.testScriptKey == null) ? 0 : this.testScriptKey
                .hashCode()));
        result = ((PRIME * result) + ((this.description == null) ? 0 : this.description.hashCode()));
        result = ((PRIME * result) + ((this.folder == null) ? 0 : this.folder.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<TestScript>\n");
        appendable.append(super.toString());
        appendable.append((("<testScriptKey>" + this.testScriptKey) + "</testScriptKey>\n"));
        appendable.append((("<description>" + this.description) + "</description>\n"));
        appendable.append((("<folder>" + this.folder) + "</folder>\n"));
        appendable.append("</TestScript>\n");
        return appendable.toString();
    }

    @Override
    public TestScript cloneObject() {
        TestScript clone = new TestScript();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Missing description at method getTestScriptKey.
     *
     * @return the Key.
     */
    public Key getTestScriptKey() {
        return this.testScriptKey;
    }

    /**
     * Missing description at method setTestScriptKey.
     *
     * @param testScriptKey the Key.
     */
    public void setTestScriptKey(Key testScriptKey) {
        this.testScriptKey = testScriptKey;
    }

    /**
     * Missing description at method setTestScriptKey.
     *
     * @param testScriptKey the String.
     */
    public void setTestScriptKey(String testScriptKey) {
        if ((this.testScriptKey == null)) {
            this.testScriptKey = new Key();
        }
        this.testScriptKey.setValue(testScriptKey);
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
     * Missing description at method setFolder.
     *
     * @param folder the Folder.
     */
    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    /**
     * Missing description at method getFolder.
     *
     * @return the Folder.
     */
    public Folder getFolder() {
        return this.folder;
    }
}
