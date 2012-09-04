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
package org.nabucco.testautomation.script.impl.service.resolve;

import java.util.ArrayList;
import java.util.List;

import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.base.impl.service.maintain.PersistenceCleaner;
import org.nabucco.testautomation.property.facade.datatype.PropertyList;
import org.nabucco.testautomation.script.facade.datatype.comparator.TestScriptElementSorter;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Assertion;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Function;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptSearchMsg;
import org.nabucco.testautomation.script.impl.service.PropertySupport;
import org.nabucco.testautomation.script.impl.service.ScriptSupport;
import org.nabucco.testautomation.script.impl.service.SubEngineCodeSupport;
import org.nabucco.testautomation.script.impl.service.cache.SubEngineCodeCache;

/**
 * ResolveTestScriptServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ResolveTestScriptServiceHandlerImpl extends ResolveTestScriptServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final TestScriptElementSorter elementSorter = new TestScriptElementSorter();

    private List<Action> actionList = new ArrayList<Action>();

    @Override
    protected TestScriptMsg resolveTestScript(TestScriptSearchMsg msg) throws ResolveException {

        if (msg.getIdentifier() == null || msg.getIdentifier().getValue() == null) {
            throw new ResolveException("Mandatory Identifier is null");
        }

        TestScript testScript;
        try {
            String queryString = "FROM TestScript s WHERE s.id = :id";
            NabuccoQuery<TestScript> query = super.getPersistenceManager().createQuery(queryString);
            query.setParameter("id", msg.getIdentifier().getValue());

            testScript = (TestScript) query.getSingleResult();

            if (testScript == null) {
                throw new ResolveException("TestScript with id '" + msg.getIdentifier().getValue() + "' not found");
            }
        } catch (PersistenceException e) {
            throw new ResolveException(e);
        }

        // load all children
        this.actionList.clear();
        this.resolve(testScript);

        try {
            // Detach Entity
            this.getPersistenceManager().clear();
        } catch (PersistenceException e) {
            getLogger().error("Could not detach entities", e);
        }

        if (!SubEngineCodeCache.getInstance().isInitialized()) {
            SubEngineCodeSupport.getInstance().initCache(this.getContext());
        }

        // load actions
        for (Action action : this.actionList) {
            this.resolveSubEngineCodes(action);
        }
        this.actionList.clear();

        try {
            testScript.accept(new PersistenceCleaner());
        } catch (VisitorException e) {
            throw new ResolveException(e);
        }

        // Sort
        elementSorter.sort(testScript);

        TestScriptMsg rs = new TestScriptMsg();
        rs.setTestScript(testScript);
        return rs;
    }

    private void resolve(TestScriptElement testScriptElement) throws ResolveException {

        try {
            PropertySupport.getInstance().resolveProperties(testScriptElement, super.getContext());
        } catch (Exception e) {
            super.getLogger().error(e,
                    "Could not resolve Properties for TestScriptElement '" + testScriptElement.getId() + "'");
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
                this.actionList.add(action);

                // Resolving MetadataLabels must be done before detaching Entities
                Metadata metadata = action.getMetadata();

                if (metadata != null) {

                    for (MetadataLabel label : metadata.getLabelList()) {
                        try {
                            PropertyList propertyList = PropertySupport.getInstance().getPropertyList(
                                    label.getPropertyListRefId(), super.getContext());
                            label.setPropertyList(propertyList);
                        } catch (Exception e) {
                            super.getLogger().error(
                                    e,
                                    "Could not resolve PropertyList for MetadataLabel of Action '"
                                            + action.getId() + "'");
                        }
                    }
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

    /**
     * Resolves SubEngineCode and SubEngineActionCode. This must be done after detaching the Entity
     * in order to avoid unnecessary updates.
     * 
     * @param action
     */
    private void resolveSubEngineCodes(Action action) {

        Metadata metadata = action.getMetadata();

        if (metadata != null) {
            try {
                SubEngineCodeSupport.getInstance().resolveSubEngineCodeShallow(metadata);
            } catch (ResolveException e) {
                super.getLogger().error(e,
                        "Could not resolve SubEngineCodes for Metadata of Action '" + action.getId() + "'");
            }
        }
        if (action.getActionCode() != null) {
            action.setActionCode(SubEngineCodeCache.getInstance().getActionCode(action.getActionCode().getId()));
        }
    }

}
