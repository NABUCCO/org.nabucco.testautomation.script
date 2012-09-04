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

import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.draganddrop.AbstractDragAndDropHandler;


public class TestScriptCompositeDragAndDropHandler extends AbstractDragAndDropHandler {
    
    @Override
    public boolean validateDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
//        MasterDetailTreeNode parent;
//        // Einf�gen als Geschwisterelement von target
//        switch(location) {
//            case DropBefore:
//                parent = target.getParent();
//                
//                if(!DataModelManager.canBeChildOf(data.getDatatype(), parent.getDatatype())) {
//                    return false;
//                }
//                
//                if(target.getDatatype() instanceof PropertyList) {
//                    return false;
//                }
//                
//                 
//                return true;
//            case DropAfter:
//                parent = target.getParent();
//                
//                if(!DataModelManager.canBeChildOf(data.getDatatype(), parent.getDatatype())) {
//                    return false;
//                }
//                 
//                return true;
//                
//            // Einf�gen als Kind von target 
//            case DropOnto:
//                parent = target;
//                if(!DataModelManager.canBeChildOf(data.getDatatype(), parent.getDatatype())) {
//                    return false;
//                }
//                
//                return true;
//        }
//        
//        // Hierher sollte das Programm nie kommen
//        return false;
        return new TestScriptElementDragAndDropHandler().validateDrop(data, target, location);
    }
    
    @Override
    public boolean postDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
        return new TestScriptElementDragAndDropHandler().postDrop(data, target, location);
    }
    
    @Override
    public boolean performUiDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
        return super.performUiDrop(data, target, location);
    }

}
