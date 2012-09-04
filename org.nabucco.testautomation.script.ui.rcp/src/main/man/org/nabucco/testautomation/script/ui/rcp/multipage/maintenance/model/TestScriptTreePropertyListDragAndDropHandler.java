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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model;

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
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;


public class TestScriptTreePropertyListDragAndDropHandler extends PropertyCompositeDragAndDropHandler {
    
    @Override
    public boolean validateDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
        MasterDetailTreeNode parent = null;
        // Einf�gen als Geschwisterelement von target
        switch(location) {
            case DropBefore:
                parent = target.getParent();
                if(!DataModelManager.canBeChildOf(data.getDatatype(), parent.getDatatype())) {
                    return false;
                }
                // Target has to be first element if parent is no PropertyComposite
                if((!(parent.getDatatype() instanceof PropertyComposite)) && parent.getChildren().get(0) != target) {
                    return false;
                }
                return true;

            case DropAfter:
                parent = target.getParent();
                if(!(parent.getDatatype() instanceof PropertyComposite)) {
                    return false;
                }
                return true;
            // Einf�gen als Kind von target 
            case DropOnto:
                if(!DataModelManager.canBeChildOf(data.getDatatype(), target.getDatatype())) {
                    return false;
                }
                return true;
        }
        
        // Hierher sollte das Programm nie kommen
        return false;
    }
    
    @Override
    public boolean postDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
        MasterDetailTreeNode oldParent = data.getParent();
        Datatype oldParentDatatype = oldParent.getDatatype();
        int oldIndex = oldParent.getChildren().indexOf(data);
        PropertyContainer container = null;
        if(oldParentDatatype instanceof PropertyComposite) {
            container = PropertyDragAndDropHandler.removeProperty((PropertyComposite)oldParentDatatype, (Property)data.getDatatype());
        } else if(oldParentDatatype instanceof TestScriptComposite) {
            TestScriptComposite oldParentTestScriptComposite = (TestScriptComposite) oldParentDatatype;
            oldParentTestScriptComposite.setPropertyList(null);
            if (oldParentDatatype.getDatatypeState() == DatatypeState.PERSISTENT) {
                oldParentDatatype.setDatatypeState(DatatypeState.MODIFIED);
            }
        } else if(oldParentDatatype instanceof TestScriptElement) {
            TestScriptElement oldParentTestScriptElement = (TestScriptElement) oldParentDatatype;
            oldParentTestScriptElement.setPropertyList(null);
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

        if(newParentDatatype instanceof TestScriptComposite) {
            if(container != null) {
                container.setProperty((Property)null);
                container.setDatatypeState(DatatypeState.DELETED);
            }
            TestScriptComposite newParentTestScriptComposite = (TestScriptComposite) newParentDatatype;
            newParentTestScriptComposite.setPropertyList((PropertyList) data.getDatatype());
            if (newParentDatatype.getDatatypeState() == DatatypeState.PERSISTENT) {
                newParentDatatype.setDatatypeState(DatatypeState.MODIFIED);
            }
        } else if(newParentDatatype instanceof TestScriptElement) {
            if(container != null) {
                container.setProperty((Property)null);
                container.setDatatypeState(DatatypeState.DELETED);
            }
            TestScriptElement newParentTestScriptElement = (TestScriptElement) newParentDatatype;
            newParentTestScriptElement.setPropertyList((PropertyList) data.getDatatype());
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
    public boolean performUiDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
        if(location == DropBefore || location == DropAfter) {
            // If its dropped before or after target, we can do a standard insert
            return super.performUiDrop(data, target, location);
         } else if (location == DropOnto) {
             if(target.getDatatype() instanceof Property) {
                 // If target is a Property, we can do a standard insert
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
         return true;
    }
}
