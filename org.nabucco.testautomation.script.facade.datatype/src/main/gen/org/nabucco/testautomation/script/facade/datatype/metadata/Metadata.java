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
package org.nabucco.testautomation.script.facade.datatype.metadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoListImpl;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.testautomation.property.facade.datatype.PropertyList;
import org.nabucco.testautomation.property.facade.datatype.base.TestAutomationDatatype;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineOperationCode;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;

/**
 * Metadata<p/>The Metadata-class<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public class Metadata extends TestAutomationDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,255;u0,n;m1,1;", "l0,255;u0,n;m0,1;", "m0,1;", "m0,1;",
            "m0,n;", "m0,n;", "m0,1;", "m0,1;" };

    public static final String NAME = "name";

    public static final String DESCRIPTION = "description";

    public static final String SUBENGINE = "subEngine";

    public static final String OPERATION = "operation";

    public static final String LABELLIST = "labelList";

    public static final String CHILDREN = "children";

    public static final String PROPERTYLIST = "propertyList";

    public static final String PARENT = "parent";

    private Name name;

    private Description description;

    private SubEngineCode subEngine;

    private SubEngineOperationCode operation;

    private NabuccoList<MetadataLabel> labelList;

    private NabuccoList<Metadata> children;

    private PropertyList propertyList;

    private Metadata parent;

    /** Constructs a new Metadata instance. */
    public Metadata() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the Metadata.
     */
    protected void cloneObject(Metadata clone) {
        super.cloneObject(clone);
        if ((this.getName() != null)) {
            clone.setName(this.getName().cloneObject());
        }
        if ((this.getDescription() != null)) {
            clone.setDescription(this.getDescription().cloneObject());
        }
        if ((this.getSubEngine() != null)) {
            clone.setSubEngine(this.getSubEngine().cloneObject());
        }
        if ((this.getOperation() != null)) {
            clone.setOperation(this.getOperation().cloneObject());
        }
        if ((this.labelList != null)) {
            clone.labelList = this.labelList.cloneCollection();
        }
        if ((this.children != null)) {
            clone.children = this.children.cloneCollection();
        }
        if ((this.getPropertyList() != null)) {
            clone.setPropertyList(this.getPropertyList().cloneObject());
        }
        if ((this.getParent() != null)) {
            clone.setParent(this.getParent().cloneObject());
        }
    }

    /**
     * Getter for the LabelListJPA.
     *
     * @return the List<MetadataLabel>.
     */
    List<MetadataLabel> getLabelListJPA() {
        if ((this.labelList == null)) {
            this.labelList = new NabuccoListImpl<MetadataLabel>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoListImpl<MetadataLabel>) this.labelList).getDelegate();
    }

    /**
     * Setter for the LabelListJPA.
     *
     * @param labelList the List<MetadataLabel>.
     */
    void setLabelListJPA(List<MetadataLabel> labelList) {
        if ((this.labelList == null)) {
            this.labelList = new NabuccoListImpl<MetadataLabel>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoListImpl<MetadataLabel>) this.labelList).setDelegate(labelList);
    }

    /**
     * Getter for the ChildrenJPA.
     *
     * @return the List<Metadata>.
     */
    List<Metadata> getChildrenJPA() {
        if ((this.children == null)) {
            this.children = new NabuccoListImpl<Metadata>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoListImpl<Metadata>) this.children).getDelegate();
    }

    /**
     * Setter for the ChildrenJPA.
     *
     * @param children the List<Metadata>.
     */
    void setChildrenJPA(List<Metadata> children) {
        if ((this.children == null)) {
            this.children = new NabuccoListImpl<Metadata>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoListImpl<Metadata>) this.children).setDelegate(children);
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(TestAutomationDatatype.class).getPropertyMap());
        propertyMap.put(NAME,
                PropertyDescriptorSupport.createBasetype(NAME, Name.class, 4, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(DESCRIPTION, PropertyDescriptorSupport.createBasetype(DESCRIPTION, Description.class, 5,
                PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(SUBENGINE, PropertyDescriptorSupport.createDatatype(SUBENGINE, SubEngineCode.class, 6,
                PROPERTY_CONSTRAINTS[2], false, PropertyAssociationType.AGGREGATION));
        propertyMap.put(OPERATION, PropertyDescriptorSupport.createDatatype(OPERATION, SubEngineOperationCode.class, 7,
                PROPERTY_CONSTRAINTS[3], false, PropertyAssociationType.AGGREGATION));
        propertyMap.put(LABELLIST, PropertyDescriptorSupport.createCollection(LABELLIST, MetadataLabel.class, 8,
                PROPERTY_CONSTRAINTS[4], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(CHILDREN, PropertyDescriptorSupport.createCollection(CHILDREN, Metadata.class, 9,
                PROPERTY_CONSTRAINTS[5], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(PROPERTYLIST, PropertyDescriptorSupport.createDatatype(PROPERTYLIST, PropertyList.class, 10,
                PROPERTY_CONSTRAINTS[6], false, PropertyAssociationType.COMPONENT));
        propertyMap.put(PARENT, PropertyDescriptorSupport.createDatatype(PARENT, Metadata.class, 11,
                PROPERTY_CONSTRAINTS[7], false, PropertyAssociationType.COMPOSITION));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(Metadata.getPropertyDescriptor(NAME), this.name, null));
        properties.add(super.createProperty(Metadata.getPropertyDescriptor(DESCRIPTION), this.description, null));
        properties.add(super.createProperty(Metadata.getPropertyDescriptor(SUBENGINE), this.getSubEngine(), null));
        properties.add(super.createProperty(Metadata.getPropertyDescriptor(OPERATION), this.getOperation(), null));
        properties.add(super.createProperty(Metadata.getPropertyDescriptor(LABELLIST), this.labelList, null));
        properties.add(super.createProperty(Metadata.getPropertyDescriptor(CHILDREN), this.children, null));
        properties
                .add(super.createProperty(Metadata.getPropertyDescriptor(PROPERTYLIST), this.getPropertyList(), null));
        properties.add(super.createProperty(Metadata.getPropertyDescriptor(PARENT), this.getParent(), null));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(NAME) && (property.getType() == Name.class))) {
            this.setName(((Name) property.getInstance()));
            return true;
        } else if ((property.getName().equals(DESCRIPTION) && (property.getType() == Description.class))) {
            this.setDescription(((Description) property.getInstance()));
            return true;
        } else if ((property.getName().equals(SUBENGINE) && (property.getType() == SubEngineCode.class))) {
            this.setSubEngine(((SubEngineCode) property.getInstance()));
            return true;
        } else if ((property.getName().equals(OPERATION) && (property.getType() == SubEngineOperationCode.class))) {
            this.setOperation(((SubEngineOperationCode) property.getInstance()));
            return true;
        } else if ((property.getName().equals(LABELLIST) && (property.getType() == MetadataLabel.class))) {
            this.labelList = ((NabuccoList<MetadataLabel>) property.getInstance());
            return true;
        } else if ((property.getName().equals(CHILDREN) && (property.getType() == Metadata.class))) {
            this.children = ((NabuccoList<Metadata>) property.getInstance());
            return true;
        } else if ((property.getName().equals(PROPERTYLIST) && (property.getType() == PropertyList.class))) {
            this.setPropertyList(((PropertyList) property.getInstance()));
            return true;
        } else if ((property.getName().equals(PARENT) && (property.getType() == Metadata.class))) {
            this.setParent(((Metadata) property.getInstance()));
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
        final Metadata other = ((Metadata) obj);
        if ((this.name == null)) {
            if ((other.name != null))
                return false;
        } else if ((!this.name.equals(other.name)))
            return false;
        if ((this.description == null)) {
            if ((other.description != null))
                return false;
        } else if ((!this.description.equals(other.description)))
            return false;
        if ((this.subEngine == null)) {
            if ((other.subEngine != null))
                return false;
        } else if ((!this.subEngine.equals(other.subEngine)))
            return false;
        if ((this.operation == null)) {
            if ((other.operation != null))
                return false;
        } else if ((!this.operation.equals(other.operation)))
            return false;
        if ((this.propertyList == null)) {
            if ((other.propertyList != null))
                return false;
        } else if ((!this.propertyList.equals(other.propertyList)))
            return false;
        if ((this.parent == null)) {
            if ((other.parent != null))
                return false;
        } else if ((!this.parent.equals(other.parent)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((PRIME * result) + ((this.description == null) ? 0 : this.description.hashCode()));
        result = ((PRIME * result) + ((this.subEngine == null) ? 0 : this.subEngine.hashCode()));
        result = ((PRIME * result) + ((this.operation == null) ? 0 : this.operation.hashCode()));
        result = ((PRIME * result) + ((this.propertyList == null) ? 0 : this.propertyList.hashCode()));
        result = ((PRIME * result) + ((this.parent == null) ? 0 : this.parent.hashCode()));
        return result;
    }

    @Override
    public Metadata cloneObject() {
        Metadata clone = new Metadata();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Missing description at method getName.
     *
     * @return the Name.
     */
    public Name getName() {
        return this.name;
    }

    /**
     * Missing description at method setName.
     *
     * @param name the Name.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Missing description at method setName.
     *
     * @param name the String.
     */
    public void setName(String name) {
        if ((this.name == null)) {
            if ((name == null)) {
                return;
            }
            this.name = new Name();
        }
        this.name.setValue(name);
    }

    /**
     * Missing description at method getDescription.
     *
     * @return the Description.
     */
    public Description getDescription() {
        return this.description;
    }

    /**
     * Missing description at method setDescription.
     *
     * @param description the Description.
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * Missing description at method setDescription.
     *
     * @param description the String.
     */
    public void setDescription(String description) {
        if ((this.description == null)) {
            if ((description == null)) {
                return;
            }
            this.description = new Description();
        }
        this.description.setValue(description);
    }

    /**
     * Missing description at method setSubEngine.
     *
     * @param subEngine the SubEngineCode.
     */
    public void setSubEngine(SubEngineCode subEngine) {
        this.subEngine = subEngine;
    }

    /**
     * Missing description at method getSubEngine.
     *
     * @return the SubEngineCode.
     */
    public SubEngineCode getSubEngine() {
        return this.subEngine;
    }

    /**
     * Missing description at method setOperation.
     *
     * @param operation the SubEngineOperationCode.
     */
    public void setOperation(SubEngineOperationCode operation) {
        this.operation = operation;
    }

    /**
     * Missing description at method getOperation.
     *
     * @return the SubEngineOperationCode.
     */
    public SubEngineOperationCode getOperation() {
        return this.operation;
    }

    /**
     * Missing description at method getLabelList.
     *
     * @return the NabuccoList<MetadataLabel>.
     */
    public NabuccoList<MetadataLabel> getLabelList() {
        if ((this.labelList == null)) {
            this.labelList = new NabuccoListImpl<MetadataLabel>(NabuccoCollectionState.INITIALIZED);
        }
        return this.labelList;
    }

    /**
     * Missing description at method getChildren.
     *
     * @return the NabuccoList<Metadata>.
     */
    public NabuccoList<Metadata> getChildren() {
        if ((this.children == null)) {
            this.children = new NabuccoListImpl<Metadata>(NabuccoCollectionState.INITIALIZED);
        }
        return this.children;
    }

    /**
     * Missing description at method setPropertyList.
     *
     * @param propertyList the PropertyList.
     */
    public void setPropertyList(PropertyList propertyList) {
        this.propertyList = propertyList;
    }

    /**
     * Missing description at method getPropertyList.
     *
     * @return the PropertyList.
     */
    public PropertyList getPropertyList() {
        return this.propertyList;
    }

    /**
     * Missing description at method setParent.
     *
     * @param parent the Metadata.
     */
    public void setParent(Metadata parent) {
        this.parent = parent;
    }

    /**
     * Missing description at method getParent.
     *
     * @return the Metadata.
     */
    public Metadata getParent() {
        return this.parent;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(Metadata.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(Metadata.class).getAllProperties();
    }
}
