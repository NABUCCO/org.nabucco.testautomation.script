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
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.draganddrop.AbstractDragAndDropHandler;
import org.nabucco.testautomation.property.facade.datatype.PropertyList;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;


public class TestScriptElementDragAndDropHandler extends AbstractDragAndDropHandler {
    
    @Override
    public boolean validateDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
        MasterDetailTreeNode parent;
        // Einf�gen als Geschwisterelement von target
        switch(location) {
            case DropBefore:
                parent = target.getParent();
                
                if(!DataModelManager.canBeChildOf(data.getDatatype(), parent.getDatatype())) {
                    return false;
                }
                
                if(target.getDatatype() instanceof PropertyList) {
                    return false;
                }
                
                 
                return true;
            case DropAfter:
                parent = target.getParent();
                
                if(!DataModelManager.canBeChildOf(data.getDatatype(), parent.getDatatype())) {
                    return false;
                }
                 
                return true;
                
            // Einf�gen als Kind von target 
            case DropOnto:
                parent = target;
                if(!DataModelManager.canBeChildOf(data.getDatatype(), parent.getDatatype())) {
                    return false;
                }
                
                return true;
        }
        
        // Hierher sollte das Programm nie kommen
        return false;
    }
    
    @Override
    public boolean postDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {

        // Setzen des alten Elternelements auf modified
        // Setzen der alten Folgegeschwisterelemente auf modified
        Datatype oldParentDatatype = data.getParent().getDatatype();
        int oldIndex = data.getParent().getChildren().indexOf(data);
        TestScriptElementContainer container = removeProperty((TestScriptComposite)oldParentDatatype, (TestScriptElement)data.getDatatype());

        insertion(container, oldIndex, oldParentDatatype, target, location);
        
        // return value is useless at the moment
        return false;
    }
    
    public static TestScriptElementContainer removeProperty(TestScriptComposite pc, TestScriptElement prop) {
        NabuccoList<TestScriptElementContainer> list = pc.getTestScriptElementList();
        TestScriptElementContainer propcont = null;
        for(int i = 0; i < list.size(); i++) {
            TestScriptElementContainer temp = list.get(i);
            if(propcont == null) {
                if(temp.getElement() == prop) {
                    propcont = temp;
                }
            } else {
                temp.setOrderIndex(temp.getOrderIndex().getValue() - 1);
                if (temp.getDatatypeState() == DatatypeState.PERSISTENT) {
                    temp.setDatatypeState(DatatypeState.MODIFIED);
                }
            }
        }
        if(propcont != null) {
            list.remove(propcont);
            if(pc.getDatatypeState() == DatatypeState.PERSISTENT) {
                pc.setDatatypeState(DatatypeState.MODIFIED);
            }
        }
//        if(propcont != null && propcont.getDatatypeState() == DatatypeState.PERSISTENT) {
//            propcont.setDatatypeState(DatatypeState.MODIFIED);
//        }
        return propcont;
    }
    
    protected static void insertion(TestScriptElementContainer container, int oldIndex, Datatype oldParentDatatype, MasterDetailTreeNode target, int location) {
        MasterDetailTreeNode newParent = null;
        Datatype newParentDatatype = null;
        int newIndex = 0;
        switch (location) {
            case DropBefore:
                newParent = target.getParent();
                newParentDatatype = newParent.getDatatype();
                newIndex = newParent.getChildren().indexOf(target);
                break;
            case DropAfter:
                newParent = target.getParent();
                newParentDatatype = newParent.getDatatype();
                newIndex = newParent.getChildren().indexOf(target) + 1;
                break;
            case DropOnto:
                newParent = target;
                newParentDatatype = newParent.getDatatype();
                newIndex = newParent.getChildren().size();
                break;
        }
        
        // Wenn parent eine PropertyList enth�lt, muss vom index 1 abgezogen werden
        if(newParent.getChildren().size() > 0 && newParent.getChildren().get(0).getDatatype() instanceof PropertyList) {
            newIndex--;
        }
        
        // Wenn es in der gleichen Liste nach unten verschoben wird muss der neue Einf�gepunkt eins nach oben verschoben werden,
        // da durch das entfernen alle folgenden Elemente eins nach oben rutschen.
        if(newParentDatatype == oldParentDatatype && newIndex > oldIndex) {
            newIndex--;
        }
        // Setzen des neuen Elternelements auf modified
        // Setzen der alten Folgegeschwisterelemente auf modified
        insertProperty((TestScriptComposite)newParentDatatype, container, newIndex);
    }
    
    public static void insertProperty(TestScriptComposite pc, TestScriptElementContainer container, int index) {
        NabuccoList<TestScriptElementContainer> list = pc.getTestScriptElementList();
        list.add(index, container);
        for(int i = index; i < list.size(); i++) {
            TestScriptElementContainer cont = list.get(i);
            cont.setOrderIndex(i);
            if (cont.getDatatypeState() == DatatypeState.PERSISTENT) {
                cont.setDatatypeState(DatatypeState.MODIFIED);
            }
        }
        if(pc.getDatatypeState() == DatatypeState.PERSISTENT) {
            pc.setDatatypeState(DatatypeState.MODIFIED);
        }
    }
    
    @Override
    public boolean performUiDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
        return super.performUiDrop(data, target, location);
    }
    
}
