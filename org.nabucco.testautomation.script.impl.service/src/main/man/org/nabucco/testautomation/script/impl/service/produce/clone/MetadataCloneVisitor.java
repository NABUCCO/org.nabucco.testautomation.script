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

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Version;
import org.nabucco.testautomation.property.facade.datatype.base.Property;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyContainer;
import org.nabucco.testautomation.property.facade.datatype.base.TestAutomationDatatype;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;
import org.nabucco.testautomation.script.facade.datatype.visitor.MetadataVisitor;

/**
 * MetadataCloneVisitor
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class MetadataCloneVisitor extends MetadataVisitor {

    @Override
    protected void visit(Metadata metadata) {
        resetDatatype(metadata);
        super.visit(metadata);
    }

    @Override
    protected void visit(MetadataLabel label) {
        resetDatatype(label);
        super.visit(label);
    }

    @Override
    protected void visit(Property property) {
        resetDatatype(property);
        super.visit(property);
    }

    @Override
    protected void visit(PropertyContainer container) {
        resetDatatype(container);
        super.visit(container);
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
