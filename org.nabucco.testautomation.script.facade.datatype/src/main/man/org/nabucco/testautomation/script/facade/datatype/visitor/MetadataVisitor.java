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

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.visitor.DatatypeVisitor;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.testautomation.property.facade.datatype.base.Property;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyContainer;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;

/**
 * MetadataVisitor
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class MetadataVisitor extends DatatypeVisitor {

	@Override
    public void visit(Datatype datatype) throws VisitorException {

        if (datatype instanceof Metadata) {
            this.visit((Metadata) datatype);
        } else if (datatype instanceof MetadataLabel) {
            this.visit((MetadataLabel) datatype);
        } else if (datatype instanceof Property) {
        	this.visit((Property) datatype);
        } else if (datatype instanceof PropertyContainer) {
        	this.visit((PropertyContainer) datatype);
        }

        super.visit(datatype);
    }
	
	/**
     * Visit {@link Metadata} instances.
     * 
     * @param metadata
     *            the element to visit
     */
    protected void visit(Metadata metadata) {
    }
    
    /**
     * Visit {@link MetadataLabel} instances.
     * 
     * @param label
     *            the element to visit
     */
    protected void visit(MetadataLabel label) {
    }
    
    /**
     * Visit {@link Property} instances.
     * 
     * @param label
     *            the element to visit
     */
    protected void visit(Property property) {
    }
    
    /**
     * Visit {@link PropertyContainer} instances.
     * 
     * @param label
     *            the element to visit
     */
    protected void visit(PropertyContainer container) {
    }
	
}
