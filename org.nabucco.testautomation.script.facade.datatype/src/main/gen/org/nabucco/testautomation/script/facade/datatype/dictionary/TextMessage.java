/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.testautomation.facade.datatype.base.Text;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyReference;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComponent;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;

/**
 * TextMessage<p/>A log message<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public class TextMessage extends TestScriptComponent implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "text", "propertyRef" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m1,1;", "l0,n;m0,1;" };

    private Text text;

    private PropertyReference propertyRef;

    /** Constructs a new TextMessage instance. */
    public TextMessage() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
        type = TestScriptElementType.TEXT_MESSAGE;
    }

    /**
     * CloneObject.
     *
     * @param clone the TextMessage.
     */
    protected void cloneObject(TextMessage clone) {
        super.cloneObject(clone);
        if ((this.getText() != null)) {
            clone.setText(this.getText().cloneObject());
        }
        if ((this.getPropertyRef() != null)) {
            clone.setPropertyRef(this.getPropertyRef().cloneObject());
        }
        clone.setType(this.getType());
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new BasetypeProperty<Text>(PROPERTY_NAMES[0], Text.class,
                PROPERTY_CONSTRAINTS[0], this.text));
        properties.add(new BasetypeProperty<PropertyReference>(PROPERTY_NAMES[1],
                PropertyReference.class, PROPERTY_CONSTRAINTS[1], this.propertyRef));
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
        final TextMessage other = ((TextMessage) obj);
        if ((this.text == null)) {
            if ((other.text != null))
                return false;
        } else if ((!this.text.equals(other.text)))
            return false;
        if ((this.propertyRef == null)) {
            if ((other.propertyRef != null))
                return false;
        } else if ((!this.propertyRef.equals(other.propertyRef)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.text == null) ? 0 : this.text.hashCode()));
        result = ((PRIME * result) + ((this.propertyRef == null) ? 0 : this.propertyRef.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<TextMessage>\n");
        appendable.append(super.toString());
        appendable.append((("<text>" + this.text) + "</text>\n"));
        appendable.append((("<propertyRef>" + this.propertyRef) + "</propertyRef>\n"));
        appendable.append("</TextMessage>\n");
        return appendable.toString();
    }

    @Override
    public TextMessage cloneObject() {
        TextMessage clone = new TextMessage();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Missing description at method getText.
     *
     * @return the Text.
     */
    public Text getText() {
        return this.text;
    }

    /**
     * Missing description at method setText.
     *
     * @param text the Text.
     */
    public void setText(Text text) {
        this.text = text;
    }

    /**
     * Missing description at method setText.
     *
     * @param text the String.
     */
    public void setText(String text) {
        if ((this.text == null)) {
            this.text = new Text();
        }
        this.text.setValue(text);
    }

    /**
     * Missing description at method getPropertyRef.
     *
     * @return the PropertyReference.
     */
    public PropertyReference getPropertyRef() {
        return this.propertyRef;
    }

    /**
     * Missing description at method setPropertyRef.
     *
     * @param propertyRef the PropertyReference.
     */
    public void setPropertyRef(PropertyReference propertyRef) {
        this.propertyRef = propertyRef;
    }

    /**
     * Missing description at method setPropertyRef.
     *
     * @param propertyRef the String.
     */
    public void setPropertyRef(String propertyRef) {
        if ((this.propertyRef == null)) {
            this.propertyRef = new PropertyReference();
        }
        this.propertyRef.setValue(propertyRef);
    }
}
