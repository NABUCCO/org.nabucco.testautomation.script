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
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;

/**
 * SubEngineCodeListMsg<p/>Message containing a list of SubEngineCodes<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-20
 */
public class SubEngineCodeListMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "subEngineCodeList" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,n;" };

    private List<SubEngineCode> subEngineCodeList;

    /** Constructs a new SubEngineCodeListMsg instance. */
    public SubEngineCodeListMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new ListProperty<SubEngineCode>(PROPERTY_NAMES[0], SubEngineCode.class,
                PROPERTY_CONSTRAINTS[0], this.subEngineCodeList));
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
        final SubEngineCodeListMsg other = ((SubEngineCodeListMsg) obj);
        if ((this.subEngineCodeList == null)) {
            if ((other.subEngineCodeList != null))
                return false;
        } else if ((!this.subEngineCodeList.equals(other.subEngineCodeList)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.subEngineCodeList == null) ? 0 : this.subEngineCodeList
                .hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<SubEngineCodeListMsg>\n");
        appendable.append(super.toString());
        appendable
                .append((("<subEngineCodeList>" + this.subEngineCodeList) + "</subEngineCodeList>\n"));
        appendable.append("</SubEngineCodeListMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getSubEngineCodeList.
     *
     * @return the List<SubEngineCode>.
     */
    public List<SubEngineCode> getSubEngineCodeList() {
        if ((this.subEngineCodeList == null)) {
            this.subEngineCodeList = new ArrayList<SubEngineCode>();
        }
        return this.subEngineCodeList;
    }
}
