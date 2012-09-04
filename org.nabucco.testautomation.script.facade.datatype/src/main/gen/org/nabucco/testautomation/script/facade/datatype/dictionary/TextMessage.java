/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.nabucco.testautomation.script.facade.datatype.dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyReference;
import org.nabucco.testautomation.property.facade.datatype.base.Text;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComponent;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;

/**
 * TextMessage<p/>A log message<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public class TextMessage extends TestScriptComponent implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final TestScriptElementType TYPE_DEFAULT = TestScriptElementType.TEXT_MESSAGE;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;u0,n;m0,1;", "l0,n;u0,n;m0,1;" };

    public static final String TEXT = "text";

    public static final String PROPERTYREF = "propertyRef";

    private Text text;

    private PropertyReference propertyRef;

    /** Constructs a new TextMessage instance. */
    public TextMessage() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
        type = TYPE_DEFAULT;
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

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(TestScriptComponent.class).getPropertyMap());
        propertyMap.put(TEXT,
                PropertyDescriptorSupport.createBasetype(TEXT, Text.class, 7, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(PROPERTYREF, PropertyDescriptorSupport.createBasetype(PROPERTYREF, PropertyReference.class, 8,
                PROPERTY_CONSTRAINTS[1], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(TextMessage.getPropertyDescriptor(TEXT), this.text, null));
        properties.add(super.createProperty(TextMessage.getPropertyDescriptor(PROPERTYREF), this.propertyRef, null));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(TEXT) && (property.getType() == Text.class))) {
            this.setText(((Text) property.getInstance()));
            return true;
        } else if ((property.getName().equals(PROPERTYREF) && (property.getType() == PropertyReference.class))) {
            this.setPropertyRef(((PropertyReference) property.getInstance()));
            return true;
        }
        return false;
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
            if ((text == null)) {
                return;
            }
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
            if ((propertyRef == null)) {
                return;
            }
            this.propertyRef = new PropertyReference();
        }
        this.propertyRef.setValue(propertyRef);
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(TextMessage.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(TextMessage.class).getAllProperties();
    }
}
