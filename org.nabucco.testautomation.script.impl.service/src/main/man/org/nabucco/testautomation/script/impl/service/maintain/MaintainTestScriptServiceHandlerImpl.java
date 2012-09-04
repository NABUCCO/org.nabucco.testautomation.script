/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.testautomation.script.impl.service.maintain;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionAccessor;
import org.nabucco.framework.base.facade.datatype.visitor.DatatypeVisitor;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceExceptionMapper;
import org.nabucco.testautomation.property.facade.datatype.PropertyList;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyUsageType;
import org.nabucco.testautomation.property.facade.datatype.visitor.PropertyModificationVisitor;
import org.nabucco.testautomation.script.facade.datatype.comparator.TestScriptElementSorter;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Assertion;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Function;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;
import org.nabucco.testautomation.script.facade.exception.IntegrityCheckException;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;
import org.nabucco.testautomation.script.impl.service.FolderSupport;
import org.nabucco.testautomation.script.impl.service.PropertySupport;
import org.nabucco.testautomation.script.impl.service.ScriptSupport;
import org.nabucco.testautomation.script.impl.service.SubEngineCodeSupport;
import org.nabucco.testautomation.script.impl.service.cache.SubEngineCodeCache;
import org.nabucco.testautomation.script.impl.service.maintain.support.ScriptMaintainSupport;
import org.nabucco.testautomation.script.impl.service.maintain.visitor.TestScriptModificationVisitor;

/**
 * MaintainTestScriptServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class MaintainTestScriptServiceHandlerImpl extends MaintainTestScriptServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String PREFIX = "TSC-";

    private static final String ACTION_PREFIX = "ACT-";

    private static final TestScriptElementSorter elementSorter = new TestScriptElementSorter();

    private ScriptMaintainSupport support;

    @Override
    public TestScriptMsg maintainTestScript(TestScriptMsg msg) throws MaintainException {

        TestScript testScript = msg.getTestScript();

        try {
            // initialize PersistenceHelper
            this.support = new ScriptMaintainSupport(super.getPersistenceManager());

            if (testScript.getDatatypeState() == DatatypeState.PERSISTENT) {
                DatatypeVisitor visitor = new TestScriptModificationVisitor(testScript);
                testScript.accept(visitor);
            }

            switch (testScript.getDatatypeState()) {

            case CONSTRUCTED:
                throw new MaintainException("TestScript is not initialized.");
            case INITIALIZED:
                testScript = this.create(testScript);
                break;
            case MODIFIED:
                testScript = this.update(testScript);
                break;
            case DELETED:
                this.delete(testScript);
                getLogger().info("TestScript '" + testScript.getName() + "' [" + testScript.getId() + "] deleted");
                testScript = null;
                break;
            case TRANSIENT:
                break;
            case PERSISTENT:
                break;
            default:
                throw new MaintainException("Datatype state '"
                        + testScript.getDatatypeState() + "' is not valid for TestScript.");
            }

            this.getPersistenceManager().flush();
            this.support = null;

        } catch (Exception ex) {
            throw new MaintainException("Error maintaining TestScript", PersistenceExceptionMapper.resolve(ex,
                    TestScript.class.getName(), testScript.getId()));
        }

        if (testScript != null) {

            if (!SubEngineCodeCache.getInstance().isInitialized()) {
                SubEngineCodeSupport.getInstance().initCache(this.getContext());
            }

            try {
                resolve(testScript);

                // Detach Entity
                this.getPersistenceManager().clear();

                // Sort
                elementSorter.sort(testScript);
            } catch (PersistenceException e) {
                getLogger().error(e, "Could not detach entities for sorting");
            } catch (ResolveException e) {
                getLogger().error(e, "Could not resolve TestScript after maintaining");
            }
            getLogger().info(
                    "TestScript '" + testScript.getName() + "' [" + testScript.getId() + "] successfully maintained");
        }

        msg.setTestScript(testScript);
        return msg;
    }

    private void resolve(TestScriptElement testScriptElement) throws ResolveException {

        try {
            PropertySupport.getInstance().resolveProperties(testScriptElement, super.getContext());
        } catch (Exception e) {
            super.getLogger().error(
                    e,
                    "Could not resolve Properties for TestScriptElement '"
                            + testScriptElement.getIdentificationKey().getValue() + "'");
        }

        if (testScriptElement instanceof TestScriptComposite) {
            List<TestScriptElementContainer> testScriptElementList = ((TestScriptComposite) testScriptElement)
                    .getTestScriptElementList();

            for (TestScriptElementContainer testScriptElementContainer : testScriptElementList) {
                TestScriptElement element = testScriptElementContainer.getElement();
                resolve(element);
            }
        } else {
            switch (testScriptElement.getType()) {
            case ACTION: {
                Action action = (Action) testScriptElement;

                if (action.getMetadata() != null) {
                    try {
                        SubEngineCodeSupport.getInstance().resolveSubEngineCodeShallow(action.getMetadata());
                    } catch (ResolveException e) {
                        super.getLogger().error(
                                e,
                                "Could not resolve SubEngineCodes for Metadata of Action '"
                                        + testScriptElement.getIdentificationKey().getValue() + "'");
                    }
                }
                if (action.getActionCode() != null) {
                    action.setActionCode(SubEngineCodeCache.getInstance().getActionCode(action.getActionCode().getId()));
                }
                break;
            }
            case ASSERTION: {
                Assertion assertion = (Assertion) testScriptElement;

                if (assertion.getAssertionScriptRefId() != null) {
                    assertion.setAssertionScript(ScriptSupport.getInstance().resolveScript(
                            assertion.getAssertionScriptRefId(), getContext()));
                }
                break;
            }
            case FUNCTION: {
                Function function = (Function) testScriptElement;

                if (function.getScriptRefId() != null) {
                    function.setScript(ScriptSupport.getInstance().resolveScript(function.getScriptRefId(),
                            getContext()));
                }
                break;
            }
            }
        }
    }

    private TestScript create(TestScript testScript) throws MaintainException {

        // if no folder selected, add script to root folder
        if (testScript.getFolder() == null) {
            try {
                testScript.setFolder(FolderSupport.getInstance().getRootFolder(super.getContext()));
            } catch (Exception e) {
                super.getLogger().error(e, "Could not set Root-Folder for TestScript '",
                        testScript.getName(), "'");
            }
        }

        List<TestScriptElementContainer> elements = testScript.getTestScriptElementList();

        for (int i = 0; i < elements.size(); i++) {
            TestScriptElementContainer updatedElement = create(elements.get(i));
            elements.set(i, updatedElement);
        }

        testScript = (TestScript) create((TestScriptElement) testScript);
        testScript.setIdentificationKey(PREFIX + testScript.getId());
        testScript.setDatatypeState(DatatypeState.MODIFIED);
        testScript = this.support.maintain(testScript);
        return testScript;
    }

    private TestScriptElementContainer create(TestScriptElementContainer entity) throws MaintainException {

        TestScriptElement element = entity.getElement();

        if (element instanceof TestScriptComposite) {
            List<TestScriptElementContainer> testScriptElementList = ((TestScriptComposite) element)
                    .getTestScriptElementList();

            for (int i = 0; i < testScriptElementList.size(); i++) {
                TestScriptElementContainer updatedElement = create(testScriptElementList.get(i));
                testScriptElementList.set(i, updatedElement);
            }
        }

        // Create TestScriptElement
        element = create(element);
        entity.setElement(element);

        // Create TestScriptElementContainer
        entity = this.support.maintain(entity);
        entity.setElement(element);
        return entity;
    }

    private TestScriptElement create(TestScriptElement entity) throws MaintainException {

        PropertyList propertyList = entity.getPropertyList();

        if (propertyList != null) {
            propertyList = update(propertyList);
            entity.setPropertyList(propertyList);
        }

        entity = this.support.maintain(entity);

        // Generate ActionId
        if (entity instanceof Action) {
            entity.setIdentificationKey(ACTION_PREFIX + entity.getId());
            entity.setDatatypeState(DatatypeState.MODIFIED);
            entity = this.support.maintain(entity);
        }

        return entity;
    }

    private TestScript update(TestScript testScript) throws MaintainException {

        if (testScript.getFolder() == null) {
            try {
                testScript.setFolder(FolderSupport.getInstance().getRootFolder(super.getContext()));
            } catch (Exception e) {
                super.getLogger().error(e, "Could not set Rootfolder for TestScript '" + testScript.getId() + "'");
            }
        }

        List<TestScriptElementContainer> elements = testScript.getTestScriptElementList();
        List<TestScriptElementContainer> deletedContainer = NabuccoCollectionAccessor.getInstance().getUnassignedList(
                elements);

        // Create or update children
        for (int i = 0; i < elements.size(); i++) {
            TestScriptElementContainer updatedElement = update(elements.get(i));
            elements.set(i, updatedElement);
        }

        // Delete removed TestScriptElementContainer
        for (TestScriptElementContainer testScriptElementContainer : deletedContainer) {
            if (testScriptElementContainer.getDatatypeState() == DatatypeState.DELETED) {
                delete(testScriptElementContainer);
            }
        }

        // Update TestScript
        testScript = (TestScript) update((TestScriptElement) testScript);
        return testScript;
    }

    private TestScriptElementContainer update(TestScriptElementContainer entity) throws MaintainException {

        TestScriptElement element = entity.getElement();

        if (element instanceof TestScriptComposite) {
            List<TestScriptElementContainer> testScriptElementList = ((TestScriptComposite) element)
                    .getTestScriptElementList();
            List<TestScriptElementContainer> deletedContainer = NabuccoCollectionAccessor.getInstance()
                    .getUnassignedList(testScriptElementList);

            // Create or update children
            for (int i = 0; i < testScriptElementList.size(); i++) {
                TestScriptElementContainer updatedElement = update(testScriptElementList.get(i));
                testScriptElementList.set(i, updatedElement);
            }

            // Delete removed TestScriptElementContainer
            for (TestScriptElementContainer testScriptElementContainer : deletedContainer) {
                if (testScriptElementContainer.getDatatypeState() == DatatypeState.DELETED) {
                    delete(testScriptElementContainer);
                }
            }
        }

        // Update TestScriptElement
        element = update(element);
        entity.setElement(element);

        // Update TestScriptElementContainer
        entity = this.support.maintain(entity);
        return entity;
    }

    private TestScriptElement update(TestScriptElement entity) throws MaintainException {

        PropertyList propertyList = entity.getPropertyList();

        if (propertyList != null) {
            propertyList = update(propertyList);
            entity.setPropertyList(propertyList);
        }

        // Generate ActionId
        if (entity instanceof Action && entity.getDatatypeState() == DatatypeState.INITIALIZED) {
            entity = this.support.maintain(entity);
            entity.setIdentificationKey(ACTION_PREFIX + entity.getId());
            entity.setDatatypeState(DatatypeState.MODIFIED);
        }

        entity = this.support.maintain(entity);
        return entity;
    }

    private PropertyList update(PropertyList propertyList) {

        if (propertyList.getDatatypeState() == DatatypeState.PERSISTENT) {
            try {
                PropertyModificationVisitor visitor = new PropertyModificationVisitor(propertyList);
                propertyList.accept(visitor);
            } catch (VisitorException e) {
                super.getLogger().error(e, "Could not check PropertyList '", propertyList.getName().getValue(),
                        "' (ID:" + propertyList.getId() + ") for modification");
            }
        }

        if (propertyList.getDatatypeState() != DatatypeState.PERSISTENT) {
            try {
                propertyList.setUsageType(PropertyUsageType.TEST_SCRIPT_ELEMENT_PARAM);
                propertyList = PropertySupport.getInstance().maintainPropertyList(propertyList, super.getContext());
            } catch (Exception e) {
                super.getLogger().error(e, "Could not maintain PropertyList '", propertyList.getName().getValue(),
                        "' (ID:" + propertyList.getId() + ")");
            }
        }
        return propertyList;
    }

    private void delete(TestScript script) throws MaintainException {

        // if(script.getDatatypeState() != DatatypeState.DELETED && script.getDatatypeState() !=
        // DatatypeState.INITIALIZED) {
        // return;
        // }

        // Check, if TestScript is not referenced by any TestConfigElement
        integrityCheck(script.getId());

        // Delete children
        List<TestScriptElementContainer> testScriptElementList = script.getTestScriptElementList();

        for (TestScriptElementContainer testScriptElementContainer : testScriptElementList) {
            if (testScriptElementContainer.getDatatypeState() != DatatypeState.INITIALIZED) {
                testScriptElementContainer.setDatatypeState(DatatypeState.DELETED);
            }
            delete(testScriptElementContainer);
        }

        PropertyList propertyList = script.getPropertyList();

        // Delete Properties
        if (propertyList != null) {
            if (propertyList.getDatatypeState() != DatatypeState.INITIALIZED) {
                propertyList.setDatatypeState(DatatypeState.DELETED);
                PropertySupport.getInstance().maintainPropertyList(propertyList, getContext());
            }
            script.setPropertyList(null);
        }

        // Delete TestScript
        if (script.getDatatypeState() != DatatypeState.INITIALIZED) {
            script.setDatatypeState(DatatypeState.DELETED);
            this.support.maintain(script);
        }
    }

    private void delete(TestScriptElementContainer entity) throws MaintainException {

        // Do not delete TestScriptElementContainer that were moved by Drag&Drop
        if (entity.getDatatypeState() != DatatypeState.DELETED
                && entity.getDatatypeState() != DatatypeState.INITIALIZED) {
            return;
        }

        TestScriptElement element = entity.getElement();

        // TestScripts must not be deleted, only the TestScriptElementContainer
        if (element.getType() == TestScriptElementType.SCRIPT) {
            this.support.maintain(entity);
            return;
        }

        // Delete children
        if (element instanceof TestScriptComposite) {
            TestScriptComposite composite = (TestScriptComposite) element;

            for (TestScriptElementContainer child : composite.getTestScriptElementList()) {
                if (child.getDatatypeState() != DatatypeState.INITIALIZED) {
                    child.setDatatypeState(DatatypeState.DELETED);
                }
                delete(child);
            }
        }

        // Delete TestScriptElementContainer
        if (entity.getDatatypeState() != DatatypeState.INITIALIZED) {
            this.support.maintain(entity);
        }

        PropertyList propertyList = element.getPropertyList();

        // Delete PropertyList
        if (propertyList != null) {
            if (propertyList.getDatatypeState() != DatatypeState.INITIALIZED) {
                propertyList.setDatatypeState(DatatypeState.DELETED);
                PropertySupport.getInstance().maintainPropertyList(propertyList, getContext());
            }
            element.setPropertyList(null);
        }

        // Delete TestScriptElement
        if (element.getDatatypeState() != DatatypeState.INITIALIZED) {
            element.setDatatypeState(DatatypeState.DELETED);
            this.support.maintain(element);
        }
    }

    private void integrityCheck(Long testScriptId) throws MaintainException {

        List<Long> testConfigElementIdList = ReferenceSupport.getInstance().findReferencingTestConfigElements(
                super.getPersistenceManager(), testScriptId);

        if (!testConfigElementIdList.isEmpty()) {
            StringBuffer buf = new StringBuffer();
            buf.append("[");

            for (Long refId : testConfigElementIdList) {
                buf.append(refId);
                buf.append(" ");
            }
            buf.append("]");

            throw new IntegrityCheckException("Cannot delete TestScript ("
                    + testScriptId + "). It is referenced by " + testConfigElementIdList.size()
                    + " TestConfigElements " + buf.toString());
        }
    }

}
