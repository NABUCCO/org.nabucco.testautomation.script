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
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;

/**
 * MetadataListMsg<p/>Message containing a list of Metadata<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-20
 */
public class MetadataListMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "metadataList" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,n;" };

    private List<Metadata> metadataList;

    /** Constructs a new MetadataListMsg instance. */
    public MetadataListMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new ListProperty<Metadata>(PROPERTY_NAMES[0], Metadata.class,
                PROPERTY_CONSTRAINTS[0], this.metadataList));
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
        final MetadataListMsg other = ((MetadataListMsg) obj);
        if ((this.metadataList == null)) {
            if ((other.metadataList != null))
                return false;
        } else if ((!this.metadataList.equals(other.metadataList)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.metadataList == null) ? 0 : this.metadataList
                .hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<MetadataListMsg>\n");
        appendable.append(super.toString());
        appendable.append((("<metadataList>" + this.metadataList) + "</metadataList>\n"));
        appendable.append("</MetadataListMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getMetadataList.
     *
     * @return the List<Metadata>.
     */
    public List<Metadata> getMetadataList() {
        if ((this.metadataList == null)) {
            this.metadataList = new ArrayList<Metadata>();
        }
        return this.metadataList;
    }
}
