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
package org.nabucco.testautomation.script.facade.datatype.visitor;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.visitor.DatatypeVisitor;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Assertion;
import org.nabucco.testautomation.script.facade.datatype.dictionary.BreakLoop;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Condition;
import org.nabucco.testautomation.script.facade.datatype.dictionary.EmbeddedTestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Execution;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Foreach;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Lock;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Logger;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Loop;
import org.nabucco.testautomation.script.facade.datatype.dictionary.PropertyAction;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TextMessage;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;

import org.nabucco.testautomation.facade.datatype.property.base.Property;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyContainer;

/**
 * TestScriptVisitor
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class TestScriptVisitor extends DatatypeVisitor {

    @Override
    public void visit(Datatype datatype) throws VisitorException {

        if (datatype instanceof TestScript) {
            this.visit((TestScript) datatype);
        } else if (datatype instanceof TestScriptElement) {
            this.visit((TestScriptElement) datatype);
        } else if (datatype instanceof TestScriptElementContainer) {
            this.visit((TestScriptElementContainer) datatype);
        } else if (datatype instanceof Property) {
        	this.visit((Property) datatype);
        } else if (datatype instanceof PropertyContainer) {
        	this.visit((PropertyContainer) datatype);
        }
        super.visit(datatype);
    }

    /**
     * Visit {@link TestScript} instances.
     * 
     * @param element
     *            the element to visit
     */
    protected void visit(TestScript element) throws VisitorException {
    }

    /**
     * Visit {@link TestScriptElement} instances.
     * 
     * @param element
     *            the element to visit
     */
    protected void visit(TestScriptElement element) throws VisitorException {
    	
    	switch (element.getType()) {
    	case ACTION:
    		this.visit((Action) element);
    		break;
    	case ASSERTION:
    		this.visit((Assertion) element);
    		break;
    	case BREAK_LOOP:
    		this.visit((BreakLoop) element);
    		break;
    	case CONDITION:
    		this.visit((Condition) element);
    		break;
    	case EXECUTION:
    		this.visit((Execution) element);
    		break;
    	case FOREACH:
    		this.visit((Foreach) element);
    		break;
    	case LOCK:
    		this.visit((Lock) element);
    		break;
    	case LOGGER:
    		this.visit((Logger) element);
    		break;
    	case LOOP:
    		this.visit((Loop) element);
    		break;
    	case PROPERTY_ACTION:
    		this.visit((PropertyAction) element);
    		break;
    	case SCRIPT:
    		this.visit((TestScript) element);
    		break;
    	case TEXT_MESSAGE:
    		this.visit((TextMessage) element);
    		break;
    	case EMBEDDED_SCRIPT:
    		this.visit((EmbeddedTestScript) element);
    		break;
    	}
    }

    /**
     * Visit {@link TestScriptElementContainer} instances.
     * 
     * @param element
     *            the element to visit
     */
    protected void visit(TestScriptElementContainer element) throws VisitorException {
    }
    
    /**
     * Visit {@link Action} instances.
     * 
     * @param element
     *            the element to visit
     */
    protected void visit(Action element) throws VisitorException {
    }
    
    /**
     * Visit {@link Assertion} instances.
     * 
     * @param element
     *            the element to visit
     */
    protected void visit(Assertion element) throws VisitorException {
    }
    
    /**
     * Visit {@link BreakLoop} instances.
     * 
     * @param element
     *            the element to visit
     */
    protected void visit(BreakLoop element) throws VisitorException {
    }
    
    /**
     * Visit {@link Condition} instances.
     * 
     * @param element
     *            the element to visit
     */
    protected void visit(Condition element) throws VisitorException {
    }
    
    /**
     * Visit {@link Execution} instances.
     * 
     * @param element
     *            the element to visit
     */
    protected void visit(Execution element) throws VisitorException {
    }
    
    /**
     * Visit {@link Foreach} instances.
     * 
     * @param element
     *            the element to visit
     */
    protected void visit(Foreach element) throws VisitorException {
    }
    
    /**
     * Visit {@link Lock} instances.
     * 
     * @param element
     *            the element to visit
     */
    protected void visit(Lock element) throws VisitorException {
    }
    
    /**
     * Visit {@link Logger} instances.
     * 
     * @param element
     *            the element to visit
     */
    protected void visit(Logger element) throws VisitorException {
    }
    
    /**
     * Visit {@link Loop} instances.
     * 
     * @param element
     *            the element to visit
     */
    protected void visit(Loop element) throws VisitorException {
    }
    
    /**
     * Visit {@link PropertyAction} instances.
     * 
     * @param element
     *            the element to visit
     */
    protected void visit(PropertyAction element) throws VisitorException {
    }
    
    /**
     * Visit {@link TextMessage} instances.
     * 
     * @param element
     *            the element to visit
     */
    protected void visit(TextMessage element) throws VisitorException {
    }
    
    /**
     * Visit {@link Property} instances.
     * 
     * @param element
     *            the element to visit
     */
    protected void visit(Property element) throws VisitorException {
    }
    
    /**
     * Visit {@link PropertyContainer} instances.
     * 
     * @param element
     *            the element to visit
     */
    protected void visit(PropertyContainer element) throws VisitorException {
    }

    /**
     * Visit {@link EmbeddedTestScript} instances.
     * 
     * @param element
     *            the element to visit
     */
    protected void visit(EmbeddedTestScript element) throws VisitorException {
    }
    
}
