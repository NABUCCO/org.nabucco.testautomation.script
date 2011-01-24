/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.message;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;

/**
 * SubEngineCodeMsg<p/>Message for persisting a SubEngineCode<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-20
 */
public class SubEngineCodeMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "subEngineCode" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;" };

    private SubEngineCode subEngineCode;

    /** Constructs a new SubEngineCodeMsg instance. */
    public SubEngineCodeMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new DatatypeProperty<SubEngineCode>(PROPERTY_NAMES[0], SubEngineCode.class,
                PROPERTY_CONSTRAINTS[0], this.subEngineCode));
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
        final SubEngineCodeMsg other = ((SubEngineCodeMsg) obj);
        if ((this.subEngineCode == null)) {
            if ((other.subEngineCode != null))
                return false;
        } else if ((!this.subEngineCode.equals(other.subEngineCode)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.subEngineCode == null) ? 0 : this.subEngineCode
                .hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<SubEngineCodeMsg>\n");
        appendable.append(super.toString());
        appendable.append((("<subEngineCode>" + this.subEngineCode) + "</subEngineCode>\n"));
        appendable.append("</SubEngineCodeMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getSubEngineCode.
     *
     * @return the SubEngineCode.
     */
    public SubEngineCode getSubEngineCode() {
        return this.subEngineCode;
    }

    /**
     * Missing description at method setSubEngineCode.
     *
     * @param subEngineCode the SubEngineCode.
     */
    public void setSubEngineCode(SubEngineCode subEngineCode) {
        this.subEngineCode = subEngineCode;
    }
}
