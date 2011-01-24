/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.message;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;

/**
 * TestScriptSearchMsg<p/>Message for searching TestScripts<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-09
 */
public class TestScriptSearchMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "identifier", "name", "testScriptKey",
            "description", "folderId", "hasFolder" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m1,1;", "l0,n;m1,1;",
            "l0,n;m0,1;", "l0,n;m1,1;", "l0,n;m1,1;", "l0,n;m0,1;" };

    private Identifier identifier;

    private Name name;

    private Name testScriptKey;

    private Description description;

    private Identifier folderId;

    /** leave as null if it doesn't matter if the script has a folder or not, true if it must belong to a folder, false if it must not belong to a folder */
    private Flag hasFolder;

    /** Constructs a new TestScriptSearchMsg instance. */
    public TestScriptSearchMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new BasetypeProperty<Identifier>(PROPERTY_NAMES[0], Identifier.class,
                PROPERTY_CONSTRAINTS[0], this.identifier));
        properties.add(new BasetypeProperty<Name>(PROPERTY_NAMES[1], Name.class,
                PROPERTY_CONSTRAINTS[1], this.name));
        properties.add(new BasetypeProperty<Name>(PROPERTY_NAMES[2], Name.class,
                PROPERTY_CONSTRAINTS[2], this.testScriptKey));
        properties.add(new BasetypeProperty<Description>(PROPERTY_NAMES[3], Description.class,
                PROPERTY_CONSTRAINTS[3], this.description));
        properties.add(new BasetypeProperty<Identifier>(PROPERTY_NAMES[4], Identifier.class,
                PROPERTY_CONSTRAINTS[4], this.folderId));
        properties.add(new BasetypeProperty<Flag>(PROPERTY_NAMES[5], Flag.class,
                PROPERTY_CONSTRAINTS[5], this.hasFolder));
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
        final TestScriptSearchMsg other = ((TestScriptSearchMsg) obj);
        if ((this.identifier == null)) {
            if ((other.identifier != null))
                return false;
        } else if ((!this.identifier.equals(other.identifier)))
            return false;
        if ((this.name == null)) {
            if ((other.name != null))
                return false;
        } else if ((!this.name.equals(other.name)))
            return false;
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
        if ((this.folderId == null)) {
            if ((other.folderId != null))
                return false;
        } else if ((!this.folderId.equals(other.folderId)))
            return false;
        if ((this.hasFolder == null)) {
            if ((other.hasFolder != null))
                return false;
        } else if ((!this.hasFolder.equals(other.hasFolder)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.identifier == null) ? 0 : this.identifier.hashCode()));
        result = ((PRIME * result) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((PRIME * result) + ((this.testScriptKey == null) ? 0 : this.testScriptKey
                .hashCode()));
        result = ((PRIME * result) + ((this.description == null) ? 0 : this.description.hashCode()));
        result = ((PRIME * result) + ((this.folderId == null) ? 0 : this.folderId.hashCode()));
        result = ((PRIME * result) + ((this.hasFolder == null) ? 0 : this.hasFolder.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<TestScriptSearchMsg>\n");
        appendable.append(super.toString());
        appendable.append((("<identifier>" + this.identifier) + "</identifier>\n"));
        appendable.append((("<name>" + this.name) + "</name>\n"));
        appendable.append((("<testScriptKey>" + this.testScriptKey) + "</testScriptKey>\n"));
        appendable.append((("<description>" + this.description) + "</description>\n"));
        appendable.append((("<folderId>" + this.folderId) + "</folderId>\n"));
        appendable.append((("<hasFolder>" + this.hasFolder) + "</hasFolder>\n"));
        appendable.append("</TestScriptSearchMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getIdentifier.
     *
     * @return the Identifier.
     */
    public Identifier getIdentifier() {
        return this.identifier;
    }

    /**
     * Missing description at method setIdentifier.
     *
     * @param identifier the Identifier.
     */
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
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
     * Missing description at method getTestScriptKey.
     *
     * @return the Name.
     */
    public Name getTestScriptKey() {
        return this.testScriptKey;
    }

    /**
     * Missing description at method setTestScriptKey.
     *
     * @param testScriptKey the Name.
     */
    public void setTestScriptKey(Name testScriptKey) {
        this.testScriptKey = testScriptKey;
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
     * Missing description at method getFolderId.
     *
     * @return the Identifier.
     */
    public Identifier getFolderId() {
        return this.folderId;
    }

    /**
     * Missing description at method setFolderId.
     *
     * @param folderId the Identifier.
     */
    public void setFolderId(Identifier folderId) {
        this.folderId = folderId;
    }

    /**
     * leave as null if it doesn't matter if the script has a folder or not, true if it must belong to a folder, false if it must not belong to a folder
     *
     * @return the Flag.
     */
    public Flag getHasFolder() {
        return this.hasFolder;
    }

    /**
     * leave as null if it doesn't matter if the script has a folder or not, true if it must belong to a folder, false if it must not belong to a folder
     *
     * @param hasFolder the Flag.
     */
    public void setHasFolder(Flag hasFolder) {
        this.hasFolder = hasFolder;
    }
}
