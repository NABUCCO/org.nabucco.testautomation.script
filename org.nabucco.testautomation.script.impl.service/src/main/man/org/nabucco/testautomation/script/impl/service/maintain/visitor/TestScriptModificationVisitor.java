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
package org.nabucco.testautomation.script.impl.service.maintain.visitor;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.visitor.DatatypeVisitor;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyComposite;
import org.nabucco.testautomation.property.facade.datatype.visitor.PropertyModificationVisitor;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;

/**
 * TestScriptModificationVisitor
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public final class TestScriptModificationVisitor extends DatatypeVisitor {

    private final TestScript testScript;

    /**
     * Creates a new {@link TestScriptModificationVisitor} instance.
     * 
     * @param testScript
     *            the testScript
     */
    public TestScriptModificationVisitor(TestScript testScript) {
        this.testScript = testScript;
    }

    @Override
    public void visit(Datatype datatype) throws VisitorException {

        if (datatype instanceof TestScriptElement) {
            this.visit((TestScriptElement) datatype);
        } else if (datatype instanceof TestScriptElementContainer) {
            this.visit((TestScriptElementContainer) datatype);
        } else if (datatype instanceof PropertyComposite) {
            this.visit((PropertyComposite) datatype);
            return;
        }

        if (this.testScript.getDatatypeState() == DatatypeState.PERSISTENT) {
            super.visit(datatype);
        }
    }

    private void visit(TestScriptElement element) throws VisitorException {

        if (element.getDatatypeState() == DatatypeState.INITIALIZED
                || element.getDatatypeState() == DatatypeState.MODIFIED
                || element.getDatatypeState() == DatatypeState.DELETED) {
            this.testScript.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    private void visit(TestScriptElementContainer element) throws VisitorException {

        if (element.getDatatypeState() == DatatypeState.INITIALIZED
                || element.getDatatypeState() == DatatypeState.MODIFIED
                || element.getDatatypeState() == DatatypeState.DELETED) {
            this.testScript.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    private void visit(PropertyComposite property) throws VisitorException {

        if (property.getDatatypeState() == DatatypeState.PERSISTENT) {
            PropertyModificationVisitor visitor = new PropertyModificationVisitor(property);
            property.accept(visitor);

            if (property.getDatatypeState() == DatatypeState.INITIALIZED
                    || property.getDatatypeState() == DatatypeState.MODIFIED
                    || property.getDatatypeState() == DatatypeState.DELETED) {
                this.testScript.setDatatypeState(DatatypeState.MODIFIED);
            }
        } else {
            this.testScript.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

}
