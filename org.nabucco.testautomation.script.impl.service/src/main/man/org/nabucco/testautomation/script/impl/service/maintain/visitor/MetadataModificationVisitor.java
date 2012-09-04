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
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;

/**
 * MetadataModificationVisitor
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public final class MetadataModificationVisitor extends DatatypeVisitor {

    private final Metadata rootMetadata;

    /**
     * Creates a new {@link MetadataModificationVisitor} instance.
     * 
     * @param metadata
     *            the metadata
     */
    public MetadataModificationVisitor(Metadata metadata) {
        this.rootMetadata = metadata;
    }

    @Override
    public void visit(Datatype datatype) throws VisitorException {

        if (datatype instanceof Metadata) {
            this.visit((Metadata) datatype);
        } else if (datatype instanceof MetadataLabel) {
            this.visit((MetadataLabel) datatype);
        } else if (datatype instanceof PropertyComposite) {
            this.visit((PropertyComposite) datatype);
            return;
        }

        if (this.rootMetadata.getDatatypeState() == DatatypeState.PERSISTENT) {
            super.visit(datatype);
        }
    }

    private void visit(Metadata metadata) throws VisitorException {

        if (metadata != null
                && (metadata.getDatatypeState() == DatatypeState.INITIALIZED
                        || metadata.getDatatypeState() == DatatypeState.MODIFIED || metadata.getDatatypeState() == DatatypeState.DELETED)) {
            rootMetadata.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    private void visit(MetadataLabel metadataLabel) throws VisitorException {

        if (metadataLabel != null
                && (metadataLabel.getDatatypeState() == DatatypeState.INITIALIZED
                        || metadataLabel.getDatatypeState() == DatatypeState.MODIFIED || metadataLabel
                        .getDatatypeState() == DatatypeState.DELETED)) {
            rootMetadata.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    private void visit(PropertyComposite property) throws VisitorException {

        if (property != null) {
            PropertyModificationVisitor visitor = new PropertyModificationVisitor(property);
            property.accept(visitor);

            if (property.getDatatypeState() == DatatypeState.INITIALIZED
                    || property.getDatatypeState() == DatatypeState.MODIFIED
                    || property.getDatatypeState() == DatatypeState.DELETED) {
                this.rootMetadata.setDatatypeState(DatatypeState.MODIFIED);
            }
        }
    }

}
