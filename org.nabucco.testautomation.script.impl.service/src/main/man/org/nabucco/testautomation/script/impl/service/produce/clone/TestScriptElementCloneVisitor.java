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
package org.nabucco.testautomation.script.impl.service.produce.clone;

import java.util.Map;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Version;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.testautomation.property.facade.datatype.base.Property;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyContainer;
import org.nabucco.testautomation.property.facade.datatype.base.TestAutomationDatatype;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineActionCode;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Assertion;
import org.nabucco.testautomation.script.facade.datatype.dictionary.BreakLoop;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Condition;
import org.nabucco.testautomation.script.facade.datatype.dictionary.EmbeddedTestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Execution;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Foreach;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Logger;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Loop;
import org.nabucco.testautomation.script.facade.datatype.dictionary.PropertyAction;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TextMessage;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.visitor.TestScriptVisitor;

/**
 * TestScriptElementCloneVisitor
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class TestScriptElementCloneVisitor extends TestScriptVisitor {

    private Map<Long, SubEngineActionCode> actionCodeMap;

    private Map<Long, Metadata> metadataMap;

    private Map<Long, TestScript> scriptMap;

    public TestScriptElementCloneVisitor(Map<Long, SubEngineActionCode> subEngineCodeMap,
            Map<Long, Metadata> metadataMap, Map<Long, TestScript> scriptMap) {
        this.actionCodeMap = subEngineCodeMap;
        this.metadataMap = metadataMap;
        this.scriptMap = scriptMap;
    }

    @Override
    protected void visit(TestScriptElementContainer element) throws VisitorException {
        resetDatatype(element);
        super.visit(element);
    }

    @Override
    protected void visit(Action element) throws VisitorException {
        element.setActionCode(this.actionCodeMap.get(element.getId()));
        element.setMetadata(this.metadataMap.get(element.getId()));
        resetDatatype(element);
        super.visit(element);
    }

    @Override
    protected void visit(Assertion element) throws VisitorException {
        resetDatatype(element);
        super.visit(element);
    }

    @Override
    protected void visit(BreakLoop element) throws VisitorException {
        resetDatatype(element);
        super.visit(element);
    }

    @Override
    protected void visit(Condition element) throws VisitorException {
        resetDatatype(element);
        super.visit(element);
    }

    @Override
    protected void visit(Execution element) throws VisitorException {
        resetDatatype(element);
        super.visit(element);
    }

    @Override
    protected void visit(Foreach element) throws VisitorException {
        resetDatatype(element);
        super.visit(element);
    }

    @Override
    protected void visit(Logger element) throws VisitorException {
        resetDatatype(element);
        super.visit(element);
    }

    @Override
    protected void visit(Loop element) throws VisitorException {
        resetDatatype(element);
        super.visit(element);
    }

    @Override
    protected void visit(PropertyAction element) throws VisitorException {
        resetDatatype(element);
        super.visit(element);
    }

    @Override
    protected void visit(TestScript element) throws VisitorException {
        resetDatatype(element);
        super.visit(element);
    }

    @Override
    protected void visit(TextMessage element) throws VisitorException {
        resetDatatype(element);
        super.visit(element);
    }

    @Override
    protected void visit(Property property) throws VisitorException {
        resetDatatype(property);
        super.visit(property);
    }

    @Override
    protected void visit(PropertyContainer property) throws VisitorException {
        resetDatatype(property);
        super.visit(property);
    }

    @Override
    protected void visit(EmbeddedTestScript element) throws VisitorException {
        element.setTestScript(this.scriptMap.get(element.getId()));
        resetDatatype(element);
        super.visit(element);
    }

    @Override
    public void visit(Datatype datatype) throws VisitorException {

        if (datatype instanceof EmbeddedTestScript) {
            // Only visit EmbeddedTestScript and not the contained TestScript
            this.visit((EmbeddedTestScript) datatype);
        } else {
            super.visit(datatype);
        }
    }

    private void resetDatatype(NabuccoDatatype datatype) {
        datatype.setDatatypeState(DatatypeState.INITIALIZED);
        datatype.setVersion((Version) null);
        datatype.setId((Identifier) null);

        if (datatype instanceof TestAutomationDatatype) {
            TestAutomationDatatype exportDatatype = (TestAutomationDatatype) datatype;
            exportDatatype.setIdentificationKey("");
        }
    }

}
