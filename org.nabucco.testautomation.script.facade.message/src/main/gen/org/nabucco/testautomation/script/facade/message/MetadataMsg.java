/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.message;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;

/**
 * MetadataMsg<p/>Message for persisting a Metadata<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-20
 */
public class MetadataMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "metadata" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;" };

    private Metadata metadata;

    /** Constructs a new MetadataMsg instance. */
    public MetadataMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new DatatypeProperty<Metadata>(PROPERTY_NAMES[0], Metadata.class,
                PROPERTY_CONSTRAINTS[0], this.metadata));
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
        final MetadataMsg other = ((MetadataMsg) obj);
        if ((this.metadata == null)) {
            if ((other.metadata != null))
                return false;
        } else if ((!this.metadata.equals(other.metadata)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.metadata == null) ? 0 : this.metadata.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<MetadataMsg>\n");
        appendable.append(super.toString());
        appendable.append((("<metadata>" + this.metadata) + "</metadata>\n"));
        appendable.append("</MetadataMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getMetadata.
     *
     * @return the Metadata.
     */
    public Metadata getMetadata() {
        return this.metadata;
    }

    /**
     * Missing description at method setMetadata.
     *
     * @param metadata the Metadata.
     */
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
