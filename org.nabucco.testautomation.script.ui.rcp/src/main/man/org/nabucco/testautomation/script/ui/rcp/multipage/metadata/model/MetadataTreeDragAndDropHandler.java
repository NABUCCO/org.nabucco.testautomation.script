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
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.draganddrop.AbstractDragAndDropHandler;
import org.nabucco.testautomation.property.facade.datatype.PropertyList;
import org.nabucco.testautomation.property.facade.datatype.base.Property;
import org.nabucco.testautomation.property.ui.rcp.multipage.metadata.model.PropertyDragAndDropHandler;
import org.nabucco.testautomation.property.ui.rcp.util.LoggingUtility;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;


public class MetadataTreeDragAndDropHandler extends AbstractDragAndDropHandler {
    
    private static enum AllowedDataType {
        Metadata, MetadataLabel, Property, PropertyList
    }
    
    static int start = 0;
    
    int count = start++;
    
    @Override
    public void dragFinished(MasterDetailTreeNode movedNode) {
       MasterDetailTreeNode root = null;
       MasterDetailTreeNode temp = movedNode;
       while(root == null) {
           if(temp.getParent() == null) {
               root = temp;
               break;
           }
           temp = temp.getParent();
       }
//       printTree("", root);
    }
    
//    private void printTree(String pre, MasterDetailTreeNode node) {
//        String[] split = node.getDatatype().getClass().getName().split("\\.");
//        System.out.println(pre + split[split.length - 1] + " -> " + node.getDatatype().getDatatypeState());
//        for(MasterDetailTreeNode child: node.getChildren()) {
//            if(child.getDatatype() instanceof PropertyList) {
//                printPropertyTree(pre + "\t", (PropertyComposite)child.getDatatype());
//            } else {
//                printTree(pre + "\t", child);
//            }
//        }
//    }
    
//    private void printPropertyTree(String pre, Property prop) {
//        String[] split = prop.getClass().getName().split("\\.");
//        System.out.println(pre + split[split.length - 1] + " -> " + prop.getDatatypeState());
//        if(prop instanceof PropertyComposite) {
//            PropertyComposite pc = (PropertyComposite) prop;
//            pre += "\t";
//            for(PropertyContainer child: pc.getPropertyList()) {
//                String[] pcs = child.getClass().getName().split("\\.");
//                System.out.println(pre + pcs[pcs.length - 1] + "[" + child.getOrderIndex() + "] -> " + child.getDatatypeState());
//                printPropertyTree(pre + "\t", child.getProperty());
//            }
//        }
//    }
    
    @Override
    public boolean performUiDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
        switch(checkDataType(data)) {
            case Metadata:
                return new MetadataDragAndDropHandler().performUiDrop(data, target, location);
            case MetadataLabel:
                return new MetadataLabelDragAndDropHandler().performUiDrop(data, target, location);
            case PropertyList:
                return new MetadataTreePropertyListDragAndDropHandler().performUiDrop(data, target, location);
            case Property:
                return new PropertyDragAndDropHandler().performUiDrop(data, target, location);
        }
        
        // Diese Stelle sollte nie erreicht werden
        return false;
        
    }

    @Override
    public boolean postDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
        LoggingUtility.logDrop(data, target, location);
        switch(checkDataType(data)) {
            case Metadata:
                new MetadataDragAndDropHandler().postDrop(data, target, location);
                break;
            case MetadataLabel:
                new MetadataLabelDragAndDropHandler().postDrop(data, target, location);
                break;
            case PropertyList:
                new MetadataTreePropertyListDragAndDropHandler().postDrop(data, target, location);
                break;
            case Property:
                new PropertyDragAndDropHandler().postDrop(data, target, location);
                break;
        }
        
        return this.performUiDrop(data, target, location);
    }

    @Override
    public boolean postExternalDatatypeDrop(Datatype data, MasterDetailTreeNode target, int location) {
//        if(data == null || target == null) {
//            return false;
//        }
//        
//        switch(checkDataType(data)) {
//            case Metadata:
//                return new MetadataDragAndDropHandler().postExternalDatatypeDrop(data, target, location);
//            case MetadataLabel:
//                return new MetadataLabelDragAndDropHandler().postExternalDatatypeDrop(data, target, location);
//            case Property:
//                return new PropertyDragAndDropHandler().postExternalDatatypeDrop(data, target, location);
//        }
//        
//        // Diese Stelle sollte nie erreicht werden
//        return false;
        
//        if (data instanceof CodeParameter && target.getDatatype() instanceof PropertyList) {
//            CodeParameter codeParameter = (CodeParameter) data;
//            ProducePropertyMsg rq = new ProducePropertyMsg();
//            rq.setName(codeParameter.getName());
//            rq.setPropertyType(codeParameter.getType());
//            rq.setValue(codeParameter.getDefaultValue());
//            try {
//                PropertyMsg rs = PropertyComponentServiceDelegateFactory.getInstance().getProduceProperty()
//                        .produceProperty(rq);
//                PropertyContainer newPropertyContainer = rs.getPropertyContainer();
//
//                // Perform business model change
//                PropertyList targetList = (PropertyList) target.getDatatype();
//                newPropertyContainer.setOrderIndex(targetList.getPropertyList().size());
//                targetList.getPropertyList().add(newPropertyContainer);
//                org.nabucco.testautomation.property.ui.rcp.model.property.DataModelManager.normalizeOrderIndicies(
//                        targetList, false);
//
//                // Perform ui change
//                MasterDetailTreeNode newNode = new MasterDetailTreeNode(newPropertyContainer.getProperty(), target);
//                newNode.setViewModel(target.getViewModel());
//                target.getChildren().add(newNode);
//            } catch (ClientException e) {
//                Activator.getDefault().logError(e);
//            }
//
//        } else if (data instanceof CodeParameter && target.getDatatype() instanceof Property) {
//            CodeParameter codeParameter = (CodeParameter) data;
//            ProducePropertyMsg rq = new ProducePropertyMsg();
//            rq.setName(codeParameter.getName());
//            rq.setPropertyType(codeParameter.getType());
//            rq.setValue(codeParameter.getDefaultValue());
//            try {
//                PropertyMsg rs = PropertyComponentServiceDelegateFactory.getInstance().getProduceProperty()
//                        .produceProperty(rq);
//                PropertyContainer newPropertyContainer = rs.getPropertyContainer();
//
//                // Perform business model change
//                PropertyList targetList = (PropertyList) target.getParent().getDatatype();
//                newPropertyContainer.setOrderIndex(targetList.getPropertyList().size());
//                targetList.getPropertyList().add(newPropertyContainer);
//                org.nabucco.testautomation.property.ui.rcp.model.property.DataModelManager.normalizeOrderIndicies(
//                        targetList, false);
//
//                // Perform ui change
//                MasterDetailTreeNode newNode = new MasterDetailTreeNode(newPropertyContainer.getProperty(),
//                        target.getParent());
//                newNode.setViewModel(target.getViewModel());
//                target.getParent().getChildren().add(newNode);
//            } catch (ClientException e) {
//                Activator.getDefault().logError(e);
//            }
//
//        }
        return false;
    }

    @Override
    public boolean validateDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
        // Check if Tree is editable and check standard operation errors
        if(!super.validateDrop(data, target, location)) {
            return false;
        }
        
        switch(checkDataType(data)) {
            case Metadata:
                return new MetadataDragAndDropHandler().validateDrop(data, target, location);
            case MetadataLabel:
                return new MetadataLabelDragAndDropHandler().validateDrop(data, target, location);
            case PropertyList:
                return new MetadataTreePropertyListDragAndDropHandler().validateDrop(data, target, location);
            case Property:
                return new PropertyDragAndDropHandler().validateDrop(data, target, location);
        }
        
        // Diese Stelle sollte nie erreicht werden
        return false;
    }

    @Override
    public boolean validateExternalDrop(MasterDetailTreeNode target, int location) {
//        if (target.getDatatype() instanceof PropertyList && location == 3) {
//            return true;
//        } else if (target.getDatatype() instanceof Property) {
//            return true;
//        }
        return false;
//        if(target == null) {
//            return false;
//        }
//        
//        switch(checkDataType(target)) {
//        case Metadata:
//            return new MetadataDragAndDropHandler().validateExternalDrop(target, location);
//        case MetadataLabel:
//            return new MetadataLabelDragAndDropHandler().validateExternalDrop(target, location);
//        case Property:
//            return new PropertyDragAndDropHandler().validateExternalDrop(target, location);
//        }
//    
//        // Diese Stelle sollte nie erreicht werden
//        return false;
    }
        
    static AllowedDataType checkDataType(Datatype datatype) {
        if(datatype instanceof Metadata) {
            return AllowedDataType.Metadata;
        }
        if(datatype instanceof MetadataLabel) {
            return AllowedDataType.MetadataLabel;
        }
        if(datatype instanceof PropertyList) {
            return AllowedDataType.PropertyList;
        }
        if(datatype instanceof Property) {
            return AllowedDataType.Property;
        }
        // Hierher sollte das Programm nie kommen
        return null;
    }
    
    static AllowedDataType checkDataType(MasterDetailTreeNode node) {
        return checkDataType(node.getDatatype());
        
    }

}
