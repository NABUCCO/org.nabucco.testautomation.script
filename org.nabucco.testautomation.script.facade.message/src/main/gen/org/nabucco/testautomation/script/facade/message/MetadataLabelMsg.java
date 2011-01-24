/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.message;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;

/**
 * MetadataLabelMsg<p/>Message for transporting a MetadataLabel<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-20
 */
public class MetadataLabelMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "metadataLabel" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;" };

    private MetadataLabel metadataLabel;

    /** Constructs a new MetadataLabelMsg instance. */
    public MetadataLabelMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new DatatypeProperty<MetadataLabel>(PROPERTY_NAMES[0], MetadataLabel.class,
                PROPERTY_CONSTRAINTS[0], this.metadataLabel));
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
        final MetadataLabelMsg other = ((MetadataLabelMsg) obj);
        if ((this.metadataLabel == null)) {
            if ((other.metadataLabel != null))
                return false;
        } else if ((!this.metadataLabel.equals(other.metadataLabel)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.metadataLabel == null) ? 0 : this.metadataLabel
                .hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<MetadataLabelMsg>\n");
        appendable.append(super.toString());
        appendable.append((("<metadataLabel>" + this.metadataLabel) + "</metadataLabel>\n"));
        appendable.append("</MetadataLabelMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getMetadataLabel.
     *
     * @return the MetadataLabel.
     */
    public MetadataLabel getMetadataLabel() {
        return this.metadataLabel;
    }

    /**
     * Missing description at method setMetadataLabel.
     *
     * @param metadataLabel the MetadataLabel.
     */
    public void setMetadataLabel(MetadataLabel metadataLabel) {
        this.metadataLabel = metadataLabel;
    }
}
