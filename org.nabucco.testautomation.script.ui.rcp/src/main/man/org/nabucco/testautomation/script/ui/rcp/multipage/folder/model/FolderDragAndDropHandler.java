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
package org.nabucco.testautomation.script.ui.rcp.multipage.folder.model;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.draganddrop.AbstractDragAndDropHandler;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;


public class FolderDragAndDropHandler extends AbstractDragAndDropHandler
implements
org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.draganddrop.DragAndDropHandler {

	@Override
	public boolean validateDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
//		if(location != 3){
//			return false;
//		}
//		// Movement to same node instance makes no sense
//		if(data.getDatatype() == target.getDatatype()){
//			return false;
//		}
//		// It is only allowed to move into folders
//		if(!(target.getDatatype() instanceof Folder)){
//			return false;
//		}
//		if(data.getDatatype() instanceof Folder){
//			// All folders expect the root folder are allowed to be moved
//			if(data.getParent() != null){
//				// if in the hierarchy they are not higher than the target node
//				if(verifyHierarchy(data, target)){
//					return true;
//				}
//			}
//			// It is always allowed to move a TestScript to another Folder
//		} else if(data.getDatatype() instanceof TestScript){
//			return true;
//		}
//		return false;
	    
	    
	    
	    MasterDetailTreeNode parent;
        
        switch(location) {
            case DropBefore:
//                parent = target.getParent();
//                if(parent == null) {
//                    return false;
//                }
//                if(!DataModelManager.canBeChildOf(data.getDatatype(), parent.getDatatype())) {
//                    return false;
//                }
//                if(target.getIndex() != 0 && parent.getChildren().get(target.getIndex() - 1).getDatatype() instanceof TestScript) {
//                    return false;
//                }
                return false;
            case DropAfter:
//                parent = target.getParent();
//                if(parent == null) {
//                    return false;
//                }
//                if(!DataModelManager.canBeChildOf(data.getDatatype(), parent.getDatatype())) {
//                    return false;
//                }
//                if(target.getDatatype() instanceof TestScript) {
//                    return false;
//                }
                return false;
            case DropOnto:
                parent = target;
                if(!DataModelManager.canBeChildOf(data.getDatatype(), parent.getDatatype())) {
                    return false;
                }
                return true;
        }
        
        return false;
	}

//	private boolean verifyHierarchy(MasterDetailTreeNode nodeToMove,
//			MasterDetailTreeNode targetNode) {
//		
//		while(targetNode.getParent() != null){
//			targetNode = targetNode.getParent();
//			if(targetNode == nodeToMove){
//				return false;
//			}
//		}
//		return true;
//	}

	@Override
	public boolean postDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
//		// Perform changes in Tree
//		this.performUiDrop(movedNode, targetNode, location);
//		// Perform changes in data model
//		if(targetNode.getDatatype() instanceof Folder){
//			Folder targetFolder = (Folder) targetNode.getDatatype();
//			Datatype movedDatatype = movedNode.getDatatype();
//			if(movedDatatype instanceof Folder){
//				Folder droppedFolder = (Folder) movedDatatype;
//				targetFolder.getSubFolderList().add(droppedFolder);
//				if(targetFolder.getDatatypeState() == DatatypeState.PERSISTENT){
//					targetFolder.setDatatypeState(DatatypeState.MODIFIED);
//				}
//				return true;
//			} else if(movedDatatype instanceof TestScript){
//				TestScript testScript = (TestScript) movedDatatype;
//				targetFolder.getTestScriptList().add(testScript);
//				testScript.setDatatypeState(DatatypeState.MODIFIED);
//				if(targetFolder.getDatatypeState() == DatatypeState.PERSISTENT){
//					targetFolder.setDatatypeState(DatatypeState.MODIFIED);
//				}
//				return true;
//			}
//		}
//		Activator.getDefault().logDebug("Fatal Error. Target node of dragged Element is no Folder!");
//		return false;
	    Folder oldParent = (Folder)data.getParent().getDatatype();
        Folder newParent = null;
        if(location == DropAfter || location == DropBefore) {
            newParent = (Folder)target.getParent().getDatatype();
        } else if(location == DropOnto) {
            newParent = (Folder)target.getDatatype();
        }
        oldParent.getSubFolderList().remove(data.getDatatype());
        newParent.getSubFolderList().add((Folder)data.getDatatype());
        
        if(oldParent.getDatatypeState() != DatatypeState.INITIALIZED && oldParent.getDatatypeState() == DatatypeState.PERSISTENT) {
            oldParent.setDatatypeState(DatatypeState.MODIFIED);
        }
        if(newParent.getDatatypeState() != DatatypeState.INITIALIZED && newParent.getDatatypeState() == DatatypeState.PERSISTENT) {
            newParent.setDatatypeState(DatatypeState.MODIFIED);
        }
        // wird im Moment ignoriert
        return false;
	}
	
	@Override
    public boolean performUiDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
	    return super.performUiDrop(data, target, location);
	}
	
	
//	@Override
//	public void dragFinished(MasterDetailTreeNode movedNode) {
//		MasterDetailTreeNode parentNode = movedNode.getParent();
//		Datatype droppedDatatype = movedNode.getDatatype();
//		Datatype parentDatatype = parentNode.getDatatype();
//		parentNode.getChildren().remove(movedNode);
//		if(parentDatatype instanceof Folder){
//			Folder parentFolder = (Folder) parentDatatype;
//			if(droppedDatatype instanceof Folder){
//				Folder droppedFolder = (Folder) droppedDatatype;
//				parentFolder.getSubFolderList().remove(droppedFolder);
//				if(parentFolder.getDatatypeState() == DatatypeState.PERSISTENT){
//					parentFolder.setDatatypeState(DatatypeState.MODIFIED);
//				}
//				return;
//			} else if(droppedDatatype instanceof TestScript){
//				TestScript testScript = (TestScript) droppedDatatype;
//				parentFolder.getTestScriptList().remove(testScript);
//				if(parentFolder.getDatatypeState() == DatatypeState.PERSISTENT){
//					parentFolder.setDatatypeState(DatatypeState.MODIFIED);
//				}
//				return;
//			}
//		}
//		Activator.getDefault().logDebug("Fatal Error. Parent node of dragged Element is no Folder!");
//	}
	
//	protected static void insertion(TestScriptElementContainer container, int oldIndex, Datatype oldParentDatatype, MasterDetailTreeNode target, int location) {
//        MasterDetailTreeNode newParent = null;
//        Datatype newParentDatatype = null;
//        int newIndex = 0;
//        switch (location) {
//            case DropBefore:
//                newParent = target.getParent();
//                newParentDatatype = newParent.getDatatype();
//                newIndex = newParent.getChildren().indexOf(target);
//                break;
//            case DropAfter:
//                newParent = target.getParent();
//                newParentDatatype = newParent.getDatatype();
//                newIndex = newParent.getChildren().indexOf(target) + 1;
//                break;
//            case DropOnto:
//                newParent = target;
//                newParentDatatype = newParent.getDatatype();
//                newIndex = newParent.getChildren().size();
//                break;
//        }
//        
//        // Wenn parent eine PropertyList enth�lt, muss vom index 1 abgezogen werden
//        if(newParent.getChildren().size() > 0 && newParent.getChildren().get(0).getDatatype() instanceof PropertyList) {
//            newIndex--;
//        }
//        
//        // Wenn es in der gleichen Liste nach unten verschoben wird muss der neue Einf�gepunkt eins nach oben verschoben werden,
//        // da durch das entfernen alle folgenden Elemente eins nach oben rutschen.
//        if(newParentDatatype == oldParentDatatype && newIndex > oldIndex) {
//            newIndex--;
//        }
//        // Setzen des neuen Elternelements auf modified
//        // Setzen der alten Folgegeschwisterelemente auf modified
//        insertProperty((TestScriptComposite)newParentDatatype, container, newIndex);
//    }
	
}
