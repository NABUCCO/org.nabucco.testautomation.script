/*
* Copyright 2010 PRODYNA AG
*
* Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.opensource.org/licenses/eclipse-1.0.php or
* http://www.nabucco-source.org/nabucco-license.html
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
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;

import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.facade.datatype.visitor.PropertyModificationVisitor;

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

        if (datatype.getDatatypeState() == DatatypeState.INITIALIZED
                || datatype.getDatatypeState() == DatatypeState.MODIFIED
                || datatype.getDatatypeState() == DatatypeState.DELETED) {
        	this.testScript.setDatatypeState(DatatypeState.MODIFIED);
        } else if (datatype instanceof TestScriptElement) {
        	TestScriptElement element = (TestScriptElement) datatype;
        	PropertyList propertyList = element.getPropertyList();
        	
			if (propertyList != null) {
				PropertyModificationVisitor visitor = new PropertyModificationVisitor(propertyList);
				propertyList.accept(visitor);

				if (propertyList.getDatatypeState() == DatatypeState.INITIALIZED
		                || propertyList.getDatatypeState() == DatatypeState.MODIFIED
		                || propertyList.getDatatypeState() == DatatypeState.DELETED) {
					this.testScript.setDatatypeState(DatatypeState.MODIFIED);
					return;
				}
			}
			super.visit(datatype);
        } else {
            super.visit(datatype);
        }
    }
}