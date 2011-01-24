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
package org.nabucco.testautomation.script.ui.rcp.multipage.folder.model;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.draganddrop.AbstractDragAndDropHandler;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;


public class FolderDragAndDropHandler extends AbstractDragAndDropHandler
implements
org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.draganddrop.DragAndDropHandler {

	@Override
	public boolean validateDrop(MasterDetailTreeNode data,
			MasterDetailTreeNode target, int location) {
		if(location != 3){
			return false;
		}
		// Movement to same node instance makes no sense
		if(data.getDatatype() == target.getDatatype()){
			return false;
		}
		// It is only allowed to move into folders
		if(!(target.getDatatype() instanceof Folder)){
			return false;
		}
		if(data.getDatatype() instanceof Folder){
			// All folders expect the root folder are allowed to be moved
			if(data.getParent() != null){
				// if in the hierarchy they are not higher than the target node
				if(verifyHierarchy(data, target)){
					return true;
				}
			}
			// It is always allowed to move a TestScript to another Folder
		} else if(data.getDatatype() instanceof TestScript){
			return true;
		}
		return false;
	}

	private boolean verifyHierarchy(MasterDetailTreeNode nodeToMove,
			MasterDetailTreeNode targetNode) {
		
		while(targetNode.getParent() != null){
			targetNode = targetNode.getParent();
			if(targetNode == nodeToMove){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean postDrop(MasterDetailTreeNode movedNode, MasterDetailTreeNode targetNode, int location) {
		// Perform changes in Tree
		this.performUiDrop(movedNode, targetNode, location);
		// Perform changes in data model
		if(targetNode.getDatatype() instanceof Folder){
			Folder targetFolder = (Folder) targetNode.getDatatype();
			Datatype movedDatatype = movedNode.getDatatype();
			if(movedDatatype instanceof Folder){
				Folder droppedFolder = (Folder) movedDatatype;
				targetFolder.getSubFolderList().add(droppedFolder);
				if(targetFolder.getDatatypeState() == DatatypeState.PERSISTENT){
					targetFolder.setDatatypeState(DatatypeState.MODIFIED);
				}
				return true;
			} else if(movedDatatype instanceof TestScript){
				TestScript testScript = (TestScript) movedDatatype;
				targetFolder.getTestScriptList().add(testScript);
				testScript.setDatatypeState(DatatypeState.MODIFIED);
				if(targetFolder.getDatatypeState() == DatatypeState.PERSISTENT){
					targetFolder.setDatatypeState(DatatypeState.MODIFIED);
				}
				return true;
			}
		}
		Activator.getDefault().logDebug("Fatal Error. Target node of dragged Element is no Folder!");
		return false;
	}

	@Override
	public void dragFinished(MasterDetailTreeNode movedNode) {
		MasterDetailTreeNode parentNode = movedNode.getParent();
		Datatype droppedDatatype = movedNode.getDatatype();
		Datatype parentDatatype = parentNode.getDatatype();
		parentNode.getChildren().remove(movedNode);
		if(parentDatatype instanceof Folder){
			Folder parentFolder = (Folder) parentDatatype;
			if(droppedDatatype instanceof Folder){
				Folder droppedFolder = (Folder) droppedDatatype;
				parentFolder.getSubFolderList().remove(droppedFolder);
				if(parentFolder.getDatatypeState() == DatatypeState.PERSISTENT){
					parentFolder.setDatatypeState(DatatypeState.MODIFIED);
				}
				return;
			} else if(droppedDatatype instanceof TestScript){
				TestScript testScript = (TestScript) droppedDatatype;
				parentFolder.getTestScriptList().remove(testScript);
				if(parentFolder.getDatatypeState() == DatatypeState.PERSISTENT){
					parentFolder.setDatatypeState(DatatypeState.MODIFIED);
				}
				return;
			}
		}
		Activator.getDefault().logDebug("Fatal Error. Parent node of dragged Element is no Folder!");
	}

}
