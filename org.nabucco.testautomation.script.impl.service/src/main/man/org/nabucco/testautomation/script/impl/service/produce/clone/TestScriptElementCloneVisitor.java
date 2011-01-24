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
package org.nabucco.testautomation.script.impl.service.produce.clone;

import java.util.UUID;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Assertion;
import org.nabucco.testautomation.script.facade.datatype.dictionary.BreakLoop;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Condition;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Execution;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Foreach;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Lock;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Logger;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Loop;
import org.nabucco.testautomation.script.facade.datatype.dictionary.PropertyAction;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TextMessage;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;
import org.nabucco.testautomation.script.facade.datatype.visitor.TestScriptVisitor;

import org.nabucco.testautomation.facade.datatype.property.base.Property;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyContainer;

/**
 * TestScriptElementCloneVisitor
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class TestScriptElementCloneVisitor extends TestScriptVisitor {

	@Override 
	protected void visit(TestScriptElement element) {
        resetDatatype(element);
        super.visit(element);
    }
	
	@Override 
	protected void visit(TestScriptElementContainer element) {
        resetDatatype(element);
        super.visit(element);
    }
	
	@Override 
	protected void visit(Action element) {
		Long id = Math.abs(UUID.randomUUID().getMostSignificantBits());
		element.setActionId(id);
		resetDatatype(element);
        super.visit(element);
	}
	
	@Override 
	protected void visit(Assertion element) {
		resetDatatype(element);
        super.visit(element);
    }
    
	@Override 
    protected void visit(BreakLoop element) {
		resetDatatype(element);
        super.visit(element);
    }
    
	@Override 
    protected void visit(Condition element) {
		resetDatatype(element);
        super.visit(element);
    }
    
	@Override 
    protected void visit(Execution element) {
		resetDatatype(element);
        super.visit(element);
    }
    
	@Override 
    protected void visit(Foreach element) {
		resetDatatype(element);
        super.visit(element);
    }
    
	@Override 
    protected void visit(Lock element) {
		resetDatatype(element);
        super.visit(element);
    }
    
	@Override 
    protected void visit(Logger element) {
		resetDatatype(element);
        super.visit(element);
    }
    
	@Override 
    protected void visit(Loop element) {
		resetDatatype(element);
        super.visit(element);
    }
    
	@Override 
    protected void visit(PropertyAction element) {
		resetDatatype(element);
        super.visit(element);
    }
    
	@Override 
    protected void visit(TextMessage element) {
		resetDatatype(element);
        super.visit(element);
    }
	
	@Override 
	protected void visit(Property property) {
        resetDatatype(property);
        super.visit(property);
    }
	
	@Override 
    protected void visit(PropertyContainer property) {
        resetDatatype(property);
        super.visit(property);
    }
    
    private void resetDatatype(NabuccoDatatype datatype) {
        datatype.setDatatypeState(DatatypeState.INITIALIZED);
        datatype.setVersion(null);
        datatype.setId(null);
    }
	
}
