/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.message;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;

/**
 * FolderMsg<p/>Message for persisting a Folder nad its Subfolder<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-20
 */
public class FolderMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "folder" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;" };

    private Folder folder;

    /** Constructs a new FolderMsg instance. */
    public FolderMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new DatatypeProperty<Folder>(PROPERTY_NAMES[0], Folder.class,
                PROPERTY_CONSTRAINTS[0], this.folder));
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
        final FolderMsg other = ((FolderMsg) obj);
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
        result = ((PRIME * result) + ((this.folder == null) ? 0 : this.folder.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<FolderMsg>\n");
        appendable.append(super.toString());
        appendable.append((("<folder>" + this.folder) + "</folder>\n"));
        appendable.append("</FolderMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getFolder.
     *
     * @return the Folder.
     */
    public Folder getFolder() {
        return this.folder;
    }

    /**
     * Missing description at method setFolder.
     *
     * @param folder the Folder.
     */
    public void setFolder(Folder folder) {
        this.folder = folder;
    }
}
