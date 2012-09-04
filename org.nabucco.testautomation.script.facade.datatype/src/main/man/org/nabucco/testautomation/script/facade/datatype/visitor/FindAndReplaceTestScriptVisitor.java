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
package org.nabucco.testautomation.script.facade.datatype.visitor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.testautomation.property.facade.datatype.base.Property;
import org.nabucco.testautomation.property.facade.datatype.visitor.FindAndReplacePropertyVisitor;
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

/**
 * FindAndReplaceTestScriptVisitor
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class FindAndReplaceTestScriptVisitor extends TestScriptVisitor {

	private int depthCounter = 0;
	
    private int matches = 0;

    private String findPattern;

    private Pattern pattern;

    private String replacement;

    private boolean checkName, checkReferences, checkProperties, checkMessage, regex;

	private FindAndReplacePropertyVisitor propertyVisitor;

    public FindAndReplaceTestScriptVisitor(String findPattern, String replacement, boolean checkName,
            boolean checkReferences, boolean checkProperties, boolean checkMessage, boolean regex) {
        this.findPattern = findPattern;
        this.replacement = replacement;
        this.checkName = checkName;
        this.checkReferences = checkReferences;
        this.checkProperties = checkProperties;
        this.checkMessage = checkMessage;
        this.regex = regex;
        this.pattern = Pattern.compile(findPattern);
        if(this.checkProperties) {
        	propertyVisitor = new FindAndReplacePropertyVisitor(findPattern, replacement, checkName, true, checkReferences, regex);
        }
    }
    
    /* (non-Javadoc)
     * @see org.nabucco.testautomation.script.facade.datatype.visitor.TestScriptVisitor#visit(org.nabucco.framework.base.facade.datatype.Datatype)
     */
    @Override
    public void visit(Datatype datatype) throws VisitorException {
    	if(!(datatype instanceof EmbeddedTestScript)) {
    		super.visit(datatype);
    	}
    }

    /**
     * Return the number of matches
     * 
     * @return the number of matches
     */
    public int getMatches() {
        return this.matches + propertyVisitor.getMatches();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void visit(TestScript element) throws VisitorException {

    	depthCounter++;
    	
    	if (depthCounter < 2) {
	        if (this.checkName && element.getName() != null) {
	            String result = findAndReplace(element.getName().getValue());
	
	            if (result != null) {
	                element.getName().setValue(result);
	
	                if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
	                    element.setDatatypeState(DatatypeState.MODIFIED);
	                }
	            }
	        }
	
	        super.visit(element);
    	}
        depthCounter++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void visit(Action element) throws VisitorException {

        if (this.checkName && element.getName() != null) {
            String result = findAndReplace(element.getName().getValue());

            if (result != null) {
                element.getName().setValue(result);

                if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
                    element.setDatatypeState(DatatypeState.MODIFIED);
                }
            }
        }

        super.visit(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void visit(Assertion element) throws VisitorException {

        if (this.checkName && element.getName() != null) {
            String result = findAndReplace(element.getName().getValue());

            if (result != null) {
                element.getName().setValue(result);

                if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
                    element.setDatatypeState(DatatypeState.MODIFIED);
                }
            }
        }

        if (this.checkMessage && element.getMessage() != null) {
            String result = findAndReplace(element.getMessage().getValue());

            if (result != null) {
                element.getMessage().setValue(result);

                if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
                    element.setDatatypeState(DatatypeState.MODIFIED);
                }
            }
        }

        super.visit(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void visit(BreakLoop element) throws VisitorException {

        if (this.checkName && element.getName() != null) {
            String result = findAndReplace(element.getName().getValue());

            if (result != null) {
                element.getName().setValue(result);

                if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
                    element.setDatatypeState(DatatypeState.MODIFIED);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void visit(Condition element) throws VisitorException {

        if (this.checkName && element.getName() != null) {
            String result = findAndReplace(element.getName().getValue());

            if (result != null) {
                element.getName().setValue(result);

                if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
                    element.setDatatypeState(DatatypeState.MODIFIED);
                }
            }
        }

        if (this.checkReferences) {

            if (element.getPropertyRef() != null) {
                String result = findAndReplace(element.getPropertyRef().getValue());

                if (result != null) {
                    element.getPropertyRef().setValue(result);

                    if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
                        element.setDatatypeState(DatatypeState.MODIFIED);
                    }
                }
            }

            if (element.getValueRef() != null) {
                String result = findAndReplace(element.getValueRef().getValue());

                if (result != null) {
                    element.getValueRef().setValue(result);

                    if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
                        element.setDatatypeState(DatatypeState.MODIFIED);
                    }
                }
            }
        }

        super.visit(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void visit(Execution element) throws VisitorException {

        if (this.checkName && element.getName() != null) {
            String result = findAndReplace(element.getName().getValue());

            if (result != null) {
                element.getName().setValue(result);

                if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
                    element.setDatatypeState(DatatypeState.MODIFIED);
                }
            }
        }

        super.visit(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void visit(Foreach element) throws VisitorException {

        if (this.checkName && element.getName() != null) {
            String result = findAndReplace(element.getName().getValue());

            if (result != null) {
                element.getName().setValue(result);

                if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
                    element.setDatatypeState(DatatypeState.MODIFIED);
                }
            }
        }

        if (this.checkReferences && element.getIterableRef() != null) {

            String result = findAndReplace(element.getIterableRef().getValue());

            if (result != null) {
                element.getIterableRef().setValue(result);

                if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
                    element.setDatatypeState(DatatypeState.MODIFIED);
                }
            }
        }

        super.visit(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void visit(Logger element) throws VisitorException {

        if (this.checkName && element.getName() != null) {
            String result = findAndReplace(element.getName().getValue());

            if (result != null) {
                element.getName().setValue(result);

                if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
                    element.setDatatypeState(DatatypeState.MODIFIED);
                }
            }
        }

        super.visit(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void visit(Loop element) throws VisitorException {

        if (this.checkName && element.getName() != null) {
            String result = findAndReplace(element.getName().getValue());

            if (result != null) {
                element.getName().setValue(result);

                if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
                    element.setDatatypeState(DatatypeState.MODIFIED);
                }
            }
        }

        super.visit(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void visit(PropertyAction element) throws VisitorException {

        if (this.checkName && element.getName() != null) {
            String result = findAndReplace(element.getName().getValue());

            if (result != null) {
                element.getName().setValue(result);

                if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
                    element.setDatatypeState(DatatypeState.MODIFIED);
                }
            }
        }

        if (this.checkReferences) {

            if (element.getPropertyRef() != null) {
                String result = findAndReplace(element.getPropertyRef().getValue());

                if (result != null) {
                    element.getPropertyRef().setValue(result);

                    if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
                        element.setDatatypeState(DatatypeState.MODIFIED);
                    }
                }
            }

            if (element.getTarget() != null) {
                String result = findAndReplace(element.getTarget().getValue());

                if (result != null) {
                    element.getTarget().setValue(result);

                    if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
                        element.setDatatypeState(DatatypeState.MODIFIED);
                    }
                }
            }
        }

        super.visit(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void visit(TextMessage element) throws VisitorException {

        if (this.checkName && element.getName() != null) {
            String result = findAndReplace(element.getName().getValue());

            if (result != null) {
                element.getName().setValue(result);

                if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
                    element.setDatatypeState(DatatypeState.MODIFIED);
                }
            }
        }

        if (this.checkMessage && element.getText() != null) {
            String result = findAndReplace(element.getText().getValue());

            if (result != null) {
                element.getText().setValue(result);

                if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
                    element.setDatatypeState(DatatypeState.MODIFIED);
                }
            }
        }

        if (this.checkReferences && element.getPropertyRef() != null) {

            String result = findAndReplace(element.getPropertyRef().getValue());

            if (result != null) {
                element.getPropertyRef().setValue(result);

                if (element.getDatatypeState() == DatatypeState.PERSISTENT) {
                    element.setDatatypeState(DatatypeState.MODIFIED);
                }
            }
        }

        super.visit(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void visit(Property element) throws VisitorException {
        if (this.checkProperties) {
        	propertyVisitor.visit(element);
        }
    }

    /**
     * Return a new String with replaced values or null, if no matches were found.
     * 
     * @param value
     * @return
     */
    private String findAndReplace(String value) {

        String result = null;

        if (this.regex) {
            Matcher matcher = this.pattern.matcher(value);
            result = matcher.replaceAll(this.replacement);
        } else {
            result = value.replace(this.findPattern, this.replacement);
        }

        if (!value.equals(result)) {
            this.matches++;
            return result;
        } else {
            return null;
        }
    }

}
