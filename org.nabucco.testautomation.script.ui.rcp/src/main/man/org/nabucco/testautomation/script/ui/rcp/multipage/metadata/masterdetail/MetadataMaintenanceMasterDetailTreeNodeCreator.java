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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNodeCreatorForAllDatatypes;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNodeCreatorImpl;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;

import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyContainer;

/**
 * MetadataMaintenanceMasterDetailTreeNodeCreator
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class MetadataMaintenanceMasterDetailTreeNodeCreator extends
        MasterDetailTreeNodeCreatorImpl<Datatype> {

    /**
     * {@inheritDoc}
     */
    @Override
    public MasterDetailTreeNode createNodeTyped(final Datatype dataType,
            final MasterDetailTreeNode parent,
            final MasterDetailTreeNodeCreatorForAllDatatypes builder) {
        MasterDetailTreeNode result = new MasterDetailTreeNode(dataType, parent);

        if (dataType instanceof Metadata) {
            Metadata metadataParent = (Metadata) dataType;
            for (MetadataLabel metadataLabel : metadataParent.getLabelList()) {
                MasterDetailTreeNode child = MasterDetailTreeNodeCreatorForAllDatatypes
                        .getInstance().create(metadataLabel, result);
                result.getChildren().add(child);
            }
            for (Metadata metadata : metadataParent.getChildren()) {
                MasterDetailTreeNode child = MasterDetailTreeNodeCreatorForAllDatatypes
                        .getInstance().create(metadata, result);
                result.getChildren().add(child);
            }
        } else if (dataType instanceof MetadataLabel) {
            MetadataLabel metadataLabel = (MetadataLabel) dataType;
            if (metadataLabel.getPropertyList() != null) {
                MasterDetailTreeNode child = MasterDetailTreeNodeCreatorForAllDatatypes
                        .getInstance().create(metadataLabel.getPropertyList(), result);
                result.getChildren().add(0, child);
            }
        } else if (dataType instanceof PropertyList) {
            PropertyList propertyList = (PropertyList) dataType;

            for (PropertyContainer prop : propertyList.getPropertyList()) {
                MasterDetailTreeNode child = MasterDetailTreeNodeCreatorForAllDatatypes
                        .getInstance().create(prop.getProperty(), result);
                result.getChildren().add(child);
            }
        }
        return result;
    }

}
