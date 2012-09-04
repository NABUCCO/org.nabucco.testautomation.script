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
package org.nabucco.testautomation.script.facade.datatype.dictionary.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoListImpl;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;

/**
 * TestScriptComposite<p/>Abstract superclass of all testscript composits<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public abstract class TestScriptComposite extends TestScriptElement implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,n;" };

    public static final String TESTSCRIPTELEMENTLIST = "testScriptElementList";

    private NabuccoList<TestScriptElementContainer> testScriptElementList;

    /** Constructs a new TestScriptComposite instance. */
    public TestScriptComposite() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the TestScriptComposite.
     */
    protected void cloneObject(TestScriptComposite clone) {
        super.cloneObject(clone);
        if ((this.testScriptElementList != null)) {
            clone.testScriptElementList = this.testScriptElementList.cloneCollection();
        }
    }

    /**
     * Getter for the TestScriptElementListJPA.
     *
     * @return the List<TestScriptElementContainer>.
     */
    List<TestScriptElementContainer> getTestScriptElementListJPA() {
        if ((this.testScriptElementList == null)) {
            this.testScriptElementList = new NabuccoListImpl<TestScriptElementContainer>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoListImpl<TestScriptElementContainer>) this.testScriptElementList).getDelegate();
    }

    /**
     * Setter for the TestScriptElementListJPA.
     *
     * @param testScriptElementList the List<TestScriptElementContainer>.
     */
    void setTestScriptElementListJPA(List<TestScriptElementContainer> testScriptElementList) {
        if ((this.testScriptElementList == null)) {
            this.testScriptElementList = new NabuccoListImpl<TestScriptElementContainer>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoListImpl<TestScriptElementContainer>) this.testScriptElementList).setDelegate(testScriptElementList);
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(TestScriptElement.class).getPropertyMap());
        propertyMap.put(TESTSCRIPTELEMENTLIST, PropertyDescriptorSupport.createCollection(TESTSCRIPTELEMENTLIST,
                TestScriptElementContainer.class, 7, PROPERTY_CONSTRAINTS[0], false,
                PropertyAssociationType.COMPOSITION));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(TestScriptComposite.getPropertyDescriptor(TESTSCRIPTELEMENTLIST),
                this.testScriptElementList, null));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(TESTSCRIPTELEMENTLIST) && (property.getType() == TestScriptElementContainer.class))) {
            this.testScriptElementList = ((NabuccoList<TestScriptElementContainer>) property.getInstance());
            return true;
        }
        return false;
    }

    @Override
    public abstract TestScriptComposite cloneObject();

    /**
     * Missing description at method getTestScriptElementList.
     *
     * @return the NabuccoList<TestScriptElementContainer>.
     */
    public NabuccoList<TestScriptElementContainer> getTestScriptElementList() {
        if ((this.testScriptElementList == null)) {
            this.testScriptElementList = new NabuccoListImpl<TestScriptElementContainer>(
                    NabuccoCollectionState.INITIALIZED);
        }
        return this.testScriptElementList;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(TestScriptComposite.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(TestScriptComposite.class).getAllProperties();
    }
}
