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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.draganddrop.AbstractDragAndDropHandler;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;

/**
 * MetadataLabelDragAndDropHandler
 * 
 * @author Florian Schmidt, PRODYNA AG
 */
public class MetadataLabelDragAndDropHandler extends AbstractDragAndDropHandler {

    @Override
    public void dragFinished(MasterDetailTreeNode arg0) {
    }

    @Override
    public boolean postDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
        Metadata oldParentMetadata = (Metadata) data.getParent().getDatatype();
        if (oldParentMetadata.getDatatypeState() == DatatypeState.PERSISTENT) {
            oldParentMetadata.setDatatypeState(DatatypeState.MODIFIED);
        }

        Metadata newParentMetadata = null;
        if (location == DropBefore || location == DropAfter) {
            newParentMetadata = (Metadata) target.getParent().getDatatype();
        } else if (location == DropOnto) {
            newParentMetadata = (Metadata) target.getDatatype();
        }

        if (newParentMetadata.getDatatypeState() == DatatypeState.PERSISTENT) {
            newParentMetadata.setDatatypeState(DatatypeState.MODIFIED);
        }

        // Perform actual drop of metadata
        oldParentMetadata.getChildren().remove(data.getDatatype());
        newParentMetadata.getChildren().add((Metadata) data.getDatatype());

        // return value is useless at the moment
        return false;
    }

    @Override
    public boolean postExternalDatatypeDrop(Datatype arg0, MasterDetailTreeNode arg1, int arg2) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean validateDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
        // Einf�gen als Geschwisterelement von target
        if (location == DropBefore || location == DropAfter) {

            MasterDetailTreeNode parent = target.getParent();
            // If DataType in parent of target is not of type Metadata or null, we won't insert data
            if (!(parent.getDatatype() instanceof Metadata)) {
                return false;
            }

            // MetadataLabel kann immer vor einem MetadataLabel kommen
            if ((target.getDatatype() instanceof MetadataLabel) && location == DropBefore) {
                return true;
            }

            // MetadataLabel kann nur vor einem nicht MetadataLabel kommen, wenn bisher vor target
            // ein MetadataLabel kam
            if (location == DropBefore) {
                List<MasterDetailTreeNode> children = target.getParent().getChildren();
                int targetIndex = children.indexOf(target);
                if (targetIndex > 0 && !(children.get(targetIndex - 1).getDatatype() instanceof MetadataLabel)) {
                    return false;
                }
            }

            // MetadataLabel kann nur nach einem anderen MetadataLabel kommen
            if ((!(target.getDatatype() instanceof MetadataLabel)) && location == DropAfter) {
                return false;
            }

            // Parent of target is not null and of type Metadata so we can insert
            return true;
        }
        // Einf�gen als Kind von target
        else if (location == DropOnto) {
            // If DataType in target is not of type Metadata, we won't insert data
            if (!(target.getDatatype() instanceof Metadata)) {
                return false;
            }

            return true;
        }

        // Hierher sollte das Programm nie kommen
        return false;
    }

    @Override
    public boolean validateExternalDrop(MasterDetailTreeNode arg0, int arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean performUiDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
        // MasterDetailTreeNode newNode;
        if (location == DropBefore || location == DropAfter) {
            // If its dropped before or after target, we can do a standard insert
            return super.performUiDrop(data, target, location);
        } else if (location == DropOnto) {
            // If its dropped onto target(target is always metadata), we have to insert as first
            // element
            MasterDetailTreeNode oldParent = data.getParent();
            oldParent.getChildren().remove(data);
            data.setParent(target);
            data.setViewModel(target.getViewModel());
            target.getChildren().add(0, data);
        } else {
            return false;
        }
        // List<MasterDetailTreeNode> childrenOfMovedNode = data.getChildren();
        // newNode.getChildren().addAll(childrenOfMovedNode);
        // for (MasterDetailTreeNode childOfMovedNode : childrenOfMovedNode) {
        // childOfMovedNode.setParent(newNode);
        // }
        return true;
    }

}
