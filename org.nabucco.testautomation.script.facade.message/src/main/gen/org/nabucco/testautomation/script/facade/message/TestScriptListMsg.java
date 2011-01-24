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
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;

/**
 * TestScriptListMsg<p/>Message containing a list of TestScripts<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-13
 */
public class TestScriptListMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "testScriptList" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,n;" };

    private List<TestScript> testScriptList;

    /** Constructs a new TestScriptListMsg instance. */
    public TestScriptListMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new ListProperty<TestScript>(PROPERTY_NAMES[0], TestScript.class,
                PROPERTY_CONSTRAINTS[0], this.testScriptList));
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
        final TestScriptListMsg other = ((TestScriptListMsg) obj);
        if ((this.testScriptList == null)) {
            if ((other.testScriptList != null))
                return false;
        } else if ((!this.testScriptList.equals(other.testScriptList)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.testScriptList == null) ? 0 : this.testScriptList
                .hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<TestScriptListMsg>\n");
        appendable.append(super.toString());
        appendable.append((("<testScriptList>" + this.testScriptList) + "</testScriptList>\n"));
        appendable.append("</TestScriptListMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getTestScriptList.
     *
     * @return the List<TestScript>.
     */
    public List<TestScript> getTestScriptList() {
        if ((this.testScriptList == null)) {
            this.testScriptList = new ArrayList<TestScript>();
        }
        return this.testScriptList;
    }
}
