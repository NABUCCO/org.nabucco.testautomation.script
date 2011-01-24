/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.message;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;

/**
 * TestScriptMsg<p/>Message for persisting a TestScript<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-09
 */
public class TestScriptMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "testScript" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;" };

    private TestScript testScript;

    /** Constructs a new TestScriptMsg instance. */
    public TestScriptMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new DatatypeProperty<TestScript>(PROPERTY_NAMES[0], TestScript.class,
                PROPERTY_CONSTRAINTS[0], this.testScript));
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
        final TestScriptMsg other = ((TestScriptMsg) obj);
        if ((this.testScript == null)) {
            if ((other.testScript != null))
                return false;
        } else if ((!this.testScript.equals(other.testScript)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.testScript == null) ? 0 : this.testScript.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<TestScriptMsg>\n");
        appendable.append(super.toString());
        appendable.append((("<testScript>" + this.testScript) + "</testScript>\n"));
        appendable.append("</TestScriptMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getTestScript.
     *
     * @return the TestScript.
     */
    public TestScript getTestScript() {
        return this.testScript;
    }

    /**
     * Missing description at method setTestScript.
     *
     * @param testScript the TestScript.
     */
    public void setTestScript(TestScript testScript) {
        this.testScript = testScript;
    }
}
