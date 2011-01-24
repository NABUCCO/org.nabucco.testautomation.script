/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.message;

import java.util.ArrayList;
import java.util.List;
import org.nabucco.framework.base.facade.datatype.property.ListProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;

/**
 * FolderListMsg<p/>Message for transporting a list of folders<p/>
 *
 * @version 1.0
 * @author Jens Wurm, PRODYNA AG, 2010-11-04
 */
public class FolderListMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "folderList" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,n;" };

    private List<Folder> folderList;

    /** Constructs a new FolderListMsg instance. */
    public FolderListMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new ListProperty<Folder>(PROPERTY_NAMES[0], Folder.class,
                PROPERTY_CONSTRAINTS[0], this.folderList));
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
        final FolderListMsg other = ((FolderListMsg) obj);
        if ((this.folderList == null)) {
            if ((other.folderList != null))
                return false;
        } else if ((!this.folderList.equals(other.folderList)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.folderList == null) ? 0 : this.folderList.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<FolderListMsg>\n");
        appendable.append(super.toString());
        appendable.append((("<folderList>" + this.folderList) + "</folderList>\n"));
        appendable.append("</FolderListMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getFolderList.
     *
     * @return the List<Folder>.
     */
    public List<Folder> getFolderList() {
        if ((this.folderList == null)) {
            this.folderList = new ArrayList<Folder>();
        }
        return this.folderList;
    }
}
