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

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.testautomation.property.facade.datatype.PropertyList;
import org.nabucco.testautomation.property.facade.datatype.base.Property;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyComposite;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyContainer;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyType;
import org.nabucco.testautomation.property.facade.message.ProducePropertyMsg;
import org.nabucco.testautomation.property.ui.rcp.communication.PropertyComponentServiceDelegateFactory;
import org.nabucco.testautomation.property.ui.rcp.multipage.metadata.model.PropertyCompositeDragAndDropHandler;
import org.nabucco.testautomation.property.ui.rcp.multipage.metadata.model.PropertyDragAndDropHandler;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;


public class MetadataTreePropertyListDragAndDropHandler extends PropertyCompositeDragAndDropHandler {
    
    @Override
    public void dragFinished(MasterDetailTreeNode movedNode) {
        // TODO Auto-generated method stub
    }
    
    @Override
    public boolean postDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
        MasterDetailTreeNode oldParent = data.getParent();
        Datatype oldParentDatatype = oldParent.getDatatype();
        int oldIndex = oldParent.getChildren().indexOf(data);
        PropertyContainer container = null;
        if(oldParentDatatype instanceof PropertyComposite) {
            container = PropertyDragAndDropHandler.removeProperty((PropertyComposite)oldParentDatatype, (Property)data.getDatatype());
        } else if(oldParentDatatype instanceof MetadataLabel) {
            MetadataLabel oldParentMetadataLabel = (MetadataLabel) oldParentDatatype;
            oldParentMetadataLabel.setPropertyList(null);
            if (oldParentDatatype.getDatatypeState() == DatatypeState.PERSISTENT) {
                oldParentDatatype.setDatatypeState(DatatypeState.MODIFIED);
            }
        }
        
        MasterDetailTreeNode newParent = null;
        switch (location) {
            case DropBefore:
            case DropAfter:
                newParent = target.getParent();
                break;
            case DropOnto:
                newParent = target;
                break;
        }
        Datatype newParentDatatype = newParent.getDatatype();

        if(newParentDatatype instanceof MetadataLabel) {
            if(container != null) {
                container.setProperty((Property)null);
                container.setDatatypeState(DatatypeState.DELETED);
            }
            MetadataLabel newParentMetadataLabel = (MetadataLabel) newParentDatatype;
            newParentMetadataLabel.setPropertyList((PropertyList) data.getDatatype());
            if (newParentDatatype.getDatatypeState() == DatatypeState.PERSISTENT) {
                newParentDatatype.setDatatypeState(DatatypeState.MODIFIED);
            }
        } else if(newParentDatatype instanceof PropertyComposite) {
            if(container == null) {
                try {
                    ProducePropertyMsg rq = new ProducePropertyMsg();
                    rq.setPropertyType(PropertyType.LIST);
                    container = PropertyComponentServiceDelegateFactory.getInstance().getProduceProperty().produceProperty(rq).getPropertyContainer();
                } catch (ClientException e) {
                    Activator.getDefault().logError("Cannot produce PropertyContainer");
                }
                container.setProperty((Property) data.getDatatype());
            }
            insertion(container, oldIndex, oldParentDatatype, target, location);
        }
        
        // return value is useless at the moment
        return false;
    }
    
    @Override
    public boolean postExternalDatatypeDrop(Datatype data, MasterDetailTreeNode targetNode, int location) {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public boolean validateDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {    
        // Einf�gen als Geschwisterelement von target
        if (location == 1 || location == 2) {
            MasterDetailTreeNode parent = target.getParent();
            // If DataType in parent of target is not of type Metadata or null, we won't insert data 
            if(parent == null || !(parent.getDatatype() instanceof PropertyList)) {
                return false;
            }
            // Parent of target is not null and of type PropertyList so we can insert
            return true;
        }
        // Einf�gen als Kind von target 
        else if(location == 3) {
            // If DataType in target is of type PropertyList, we can insert data 
            if(target.getDatatype() instanceof PropertyList) {
                return true;
            }
            // If DataType in target is of type PropertyList, we can insert data
            // but only if there isn't already a PropertyList
            if(target.getDatatype() instanceof MetadataLabel && target.getChildren().size() == 0) {
                return true;
            }
        }
        // Hierher sollte das Programm nie kommen
        return false;
    }
    
    @Override
    public boolean validateExternalDrop(MasterDetailTreeNode target, int location) {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public boolean performUiDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
//        MasterDetailTreeNode newNode;
        if(location == DropBefore || location == DropAfter) {
           // If its dropped before or after target, we can do a standard insert
           return super.performUiDrop(data, target, location);
        } else if (location == DropOnto) {
            if(target.getDatatype() instanceof Property) {
                // If its dropped before or after target, we can do a standard insert (as last element)
                return super.performUiDrop(data, target, location);
            } else {
                // If its dropped onto target and target is not a Property, we have to insert as first element
                MasterDetailTreeNode oldParent = data.getParent();
                oldParent.getChildren().remove(data);
                data.setParent(target);
                data.setViewModel(target.getViewModel());
                target.getChildren().add(0, data);
            }
        } else {
            return false;
        }
//        List<MasterDetailTreeNode> childrenOfMovedNode = data.getChildren();
//        newNode.getChildren().addAll(childrenOfMovedNode);
//        for (MasterDetailTreeNode childOfMovedNode : childrenOfMovedNode) {
//            childOfMovedNode.setParent(newNode);
//        }
        return true;
    }

}
