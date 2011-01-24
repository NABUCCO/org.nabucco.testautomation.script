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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model;

import java.util.Arrays;
import java.util.List;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.draganddrop.AbstractDragAndDropHandler;
import org.nabucco.testautomation.script.facade.datatype.code.CodeParameter;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;

import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.facade.datatype.property.base.Property;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyContainer;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyType;
import org.nabucco.testautomation.facade.message.ProducePropertyMsg;
import org.nabucco.testautomation.facade.message.PropertyMsg;
import org.nabucco.testautomation.ui.rcp.communication.TestautomationComponentServiceDelegateFactory;

public class TestScriptDragAndDropHandler extends AbstractDragAndDropHandler
implements
org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.draganddrop.DragAndDropHandler {

	@Override
	public boolean validateDrop(MasterDetailTreeNode data,
			MasterDetailTreeNode target, int location) {

		// Movement to same node instance makes no sense
		Datatype targetDatatype = target.getDatatype();
		Datatype movedDatatype = data.getDatatype();
		if(movedDatatype == targetDatatype){
			return false;
		} if(location == 1 || location == 2){ 
			MasterDetailTreeNode parentNode = target.getParent();  
			if(parentNode == null){
				return false;
			}
			if(movedDatatype instanceof PropertyList && location == 2 && targetDatatype instanceof TestScriptElement){
				// PropertyList has to be the first element in Composites
				return false;
			}
			Datatype targetParentDatatype = parentNode.getDatatype();
			if(targetParentDatatype instanceof TestScriptElement){
				TestScriptElement targetParentTestScriptElement = (TestScriptElement) targetParentDatatype;
				if(movedDatatype instanceof TestScriptElement){
					if(targetParentTestScriptElement.getPropertyList() == null || location == 2){
						if(Arrays.asList(DataModelManager.getPossibleChildrenTypes(targetParentDatatype)).contains(movedDatatype.getClass())){
							return verifyHierarchy(data, target);
						}
					} else {
						int targetIndex = parentNode.getChildren().indexOf(target);
						if(targetIndex == 0){
							// it is not allowed to insert to position 0, when a propertyList exists. 
							return false;
						}
					}
				} else if(movedDatatype instanceof PropertyList){
					// No before/after inserts for PropertyLists allowed if target parent is a TesScriptElement
					return false;
				}
				return false;
			} else if(targetParentDatatype instanceof TestScriptElement && movedDatatype instanceof PropertyList){
				// No before/after inserts for PropertyLists allowed if target parent is a TesScriptElement
				return false;
			} else if(targetParentDatatype instanceof PropertyList && movedDatatype instanceof Property){
				return verifyHierarchy(data, target);
			}
		} else if(location == 3){
			// It is only allowed to move into composites
			if(targetDatatype instanceof TestScriptComposite){
				TestScriptComposite targetComposite = (TestScriptComposite) targetDatatype;
				if(movedDatatype instanceof TestScriptElement){
					if(Arrays.asList(DataModelManager.getPossibleChildrenTypes(targetDatatype)).contains(movedDatatype.getClass())){
						return verifyHierarchy(data, target);
					}
				} else if(movedDatatype instanceof PropertyList){
					if(targetComposite.getPropertyList() == null){
						if(Arrays.asList(DataModelManager.getPossibleChildrenTypes(targetDatatype)).contains(movedDatatype.getClass())){
							return verifyHierarchy(data, target);
						}
					}
					return false;
				}
				return false;
			} else if(targetDatatype instanceof TestScriptElement & movedDatatype instanceof PropertyList){
				if(((TestScriptElement)targetDatatype).getPropertyList() == null){
					return verifyHierarchy(data, target);
				}
			} else if(targetDatatype instanceof PropertyList && movedDatatype instanceof Property){
				return verifyHierarchy(data, target);
			}
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
		// Perform changes in data model
		if(location == 1 || location == 2){ 
			Datatype targetParentDatatype = targetNode.getParent().getDatatype();

			if(targetParentDatatype instanceof TestScriptComposite){
				TestScriptComposite targetComposite = (TestScriptComposite) targetParentDatatype;
				Datatype movedDatatype = movedNode.getDatatype();
				if(movedDatatype instanceof TestScriptElement){
					// Find container in old parent datatype
					int indexOfMovedNodeInOldParent = movedNode.getParent().getChildren().indexOf(movedNode);

					// handle PropertyList case
					if(((TestScriptElement)movedNode.getParent().getDatatype()).getPropertyList() != null){
						indexOfMovedNodeInOldParent--;
					}

					TestScriptElementContainer testScriptElementContainerToMove = ((TestScriptComposite) movedNode.getParent().getDatatype()).getTestScriptElementList().get(indexOfMovedNodeInOldParent);
					// Find index to insert to
					int indexToInsertTo = targetNode.getParent().getChildren().indexOf(targetNode);
					if(location == 2){
						indexToInsertTo++;
					}
					// Insert
					targetComposite.getTestScriptElementList().add(indexToInsertTo, testScriptElementContainerToMove);
					// Update order index of inserted container
					DataModelManager.normalizeOrderIndicies(targetComposite, false);
					// Update datatype state
					if(targetComposite.getDatatypeState() == DatatypeState.PERSISTENT){
						targetComposite.setDatatypeState(DatatypeState.MODIFIED);
					}
				} else if(movedDatatype instanceof PropertyList){
					// No before/after inserts for PropertyLists allowed if target parent is a TesScriptElement
					return false;
				}
			} else if(targetParentDatatype instanceof TestScriptElement){
				// No before/after inserts for PropertyLists allowed if target parent is a TesScriptElement
				return false;
			} else if(targetParentDatatype instanceof PropertyList){
				PropertyList targetPropertyList = (PropertyList) targetParentDatatype;
				int indexOfMovedNodeInOldParent = movedNode.getParent().getChildren().indexOf(movedNode);
				PropertyContainer propertyContainer = ((PropertyList) movedNode.getParent().getDatatype()).getPropertyList().get(indexOfMovedNodeInOldParent);
				// Find index to insert to
				int indexToInsertTo = targetNode.getParent().getChildren().indexOf(targetNode);
				if(location == 2){
					indexToInsertTo++;
				}
				// Insert
				targetPropertyList.getPropertyList().add(indexToInsertTo, propertyContainer);
				org.nabucco.testautomation.ui.rcp.model.property.DataModelManager.normalizeOrderIndicies(targetPropertyList, false);
				if(targetPropertyList.getDatatypeState() == DatatypeState.PERSISTENT){
					targetPropertyList.setDatatypeState(DatatypeState.MODIFIED);
				}
			}
		}

		Datatype targetDatatype = targetNode.getDatatype();
		if(location == 3){

			if(targetDatatype instanceof TestScriptComposite){
				TestScriptComposite targetComposite = (TestScriptComposite) targetDatatype;
				Datatype movedDatatype = movedNode.getDatatype();
				if(movedDatatype instanceof TestScriptElement){
					// Find container in old parent datatype
					int indexOfMovedNodeInOldParent = movedNode.getParent().getChildren().indexOf(movedNode);

					// handle PropertyList case
					if(((TestScriptElement)movedNode.getParent().getDatatype()).getPropertyList() != null){
						indexOfMovedNodeInOldParent--;
					}

					TestScriptElementContainer testScriptElementContainer = ((TestScriptComposite) movedNode.getParent().getDatatype()).getTestScriptElementList().get(indexOfMovedNodeInOldParent);
					// Insert
					targetComposite.getTestScriptElementList().add(testScriptElementContainer);
					// Update order index of inserted container
					DataModelManager.normalizeOrderIndicies(targetComposite, false);
					// Update datatype state
					if(targetComposite.getDatatypeState() == DatatypeState.PERSISTENT){
						targetComposite.setDatatypeState(DatatypeState.MODIFIED);
					}
				} else if(movedDatatype instanceof PropertyList){
					PropertyList movedList = (PropertyList) movedDatatype;
					targetComposite.setPropertyList(movedList);
					if(targetComposite.getDatatypeState() == DatatypeState.PERSISTENT){
						targetComposite.setDatatypeState(DatatypeState.MODIFIED);
					}
				}
			} else if(targetDatatype instanceof TestScriptElement){
				TestScriptElement targetElement = (TestScriptElement) targetDatatype;
				Datatype movedDatatype = movedNode.getDatatype();
				if(movedDatatype instanceof PropertyList){
					PropertyList movedList = (PropertyList) movedDatatype;
					targetElement.setPropertyList(movedList);
					if(targetElement.getDatatypeState() == DatatypeState.PERSISTENT){
						targetElement.setDatatypeState(DatatypeState.MODIFIED);
					}
				}
			} else if(targetDatatype instanceof PropertyList){
				PropertyList targetPropertyList = (PropertyList) targetDatatype;
				int indexOfMovedNodeInOldParent = movedNode.getParent().getChildren().indexOf(movedNode);
				PropertyContainer propertyContainer; 

				if(movedNode.getParent().getDatatype() instanceof TestScriptElement){
					ProducePropertyMsg rq = new ProducePropertyMsg();
					rq.setPropertyType(PropertyType.LIST);
					try {
						propertyContainer = TestautomationComponentServiceDelegateFactory.getInstance().getProduceProperty().produceProperty(rq).getPropertyContainer();
					} catch (ClientException e) {
						Activator.getDefault().logError("Cannot produce PropertyContainer"); 
						return false;
					}
					propertyContainer.setProperty((Property) movedNode.getDatatype());
				} else {
					propertyContainer = ((PropertyList) movedNode.getParent().getDatatype()).getPropertyList().get(indexOfMovedNodeInOldParent);
				}
				targetPropertyList.getPropertyList().add(propertyContainer);
				org.nabucco.testautomation.ui.rcp.model.property.DataModelManager.normalizeOrderIndicies(targetPropertyList, false);
				if(targetPropertyList.getDatatypeState() == DatatypeState.PERSISTENT){
					targetPropertyList.setDatatypeState(DatatypeState.MODIFIED);
				}
			}
		}
		// Perform changes in Tree - We have to do this last, because changes in the tree could influence the indices needed for proper datamodel change
		return this.performUiDrop(movedNode, targetNode, location);
	}

	@Override
	public void dragFinished(MasterDetailTreeNode movedNode) {
		MasterDetailTreeNode parentNode = movedNode.getParent();
		Datatype droppedDatatype = movedNode.getDatatype();
		Datatype parentDatatype = parentNode.getDatatype();
		if(parentDatatype instanceof TestScriptElement){
			TestScriptElement parentTestScriptElement = (TestScriptElement) parentDatatype;
			if(droppedDatatype instanceof TestScriptElement){
				int indexOf = parentNode.getChildren().indexOf(movedNode);

				// handle PropertyList case
				if(((TestScriptElement)movedNode.getParent().getDatatype()).getPropertyList() != null){
					indexOf--;
				}

				((TestScriptComposite) parentTestScriptElement).getTestScriptElementList().remove(indexOf);
				if(parentTestScriptElement.getDatatypeState() == DatatypeState.PERSISTENT){
					parentTestScriptElement.setDatatypeState(DatatypeState.MODIFIED);
				}
				// this must stand after the "indexOf" call
				parentNode.getChildren().remove(movedNode);
				// Normalize order indices
				DataModelManager.normalizeOrderIndicies((TestScriptComposite) parentTestScriptElement, false);
				return;
			} else if(droppedDatatype instanceof PropertyList){
				parentTestScriptElement.setPropertyList(null);
				parentNode.getChildren().remove(0);
				if(parentTestScriptElement.getDatatypeState() == DatatypeState.PERSISTENT){
					parentTestScriptElement.setDatatypeState(DatatypeState.MODIFIED);
				} 
				return;
			}
		} else if(parentDatatype instanceof PropertyList){
			PropertyList parentPropertyList = (PropertyList) parentDatatype;
			int indexOf = parentNode.getChildren().indexOf(movedNode);
			parentPropertyList.getPropertyList().remove(indexOf);
			// this must stand after the "indexOf" call
			parentNode.getChildren().remove(movedNode);
			// Normalize order indices
			org.nabucco.testautomation.ui.rcp.model.property.DataModelManager.normalizeOrderIndicies(parentPropertyList, false);
			if(parentPropertyList.getDatatypeState() == DatatypeState.PERSISTENT){
				parentPropertyList.setDatatypeState(DatatypeState.MODIFIED);
			}
			return;
		}
		Activator.getDefault().logDebug("Fatal Error. Parent node of dragged Element is no TestScriptElement or PropertyList!");
	}

	@Override
	public boolean performUiDrop(MasterDetailTreeNode movedNode, MasterDetailTreeNode targetNode, int location) {
		MasterDetailTreeNode newNode;
		if(location == 1 || location == 2) {
			newNode = new MasterDetailTreeNode(movedNode.getDatatype(), targetNode.getParent());
			newNode.setViewModel(targetNode.getViewModel());

			// Find index to insert to
			int indexToInsertTo = targetNode.getParent().getChildren().indexOf(targetNode);
			if(location == 2){
				indexToInsertTo++;
			}
			targetNode.getParent().getChildren().add(indexToInsertTo, newNode);
		} else if(location == 3){ 
			newNode = new MasterDetailTreeNode(movedNode.getDatatype(), targetNode);
			newNode.setViewModel(targetNode.getViewModel());
			if(movedNode.getDatatype() instanceof PropertyList){
				targetNode.getChildren().add(0, newNode);
			} else {
				targetNode.getChildren().add(newNode);
			}
		} else {
			return false;
		}
		List<MasterDetailTreeNode> childrenOfMovedNode = movedNode.getChildren();
		newNode.getChildren().addAll(childrenOfMovedNode);
		for (MasterDetailTreeNode childOfMovedNode : childrenOfMovedNode) {
			childOfMovedNode.setParent(newNode);
		}
		return true;
	}

	@Override
	public boolean validateExternalDrop(MasterDetailTreeNode target, int location) {
		if(target.getDatatype() instanceof PropertyList && location == 3){
			return true;
		} else if(target.getDatatype() instanceof Property){
			return true;
		}
		return false;
	}

	@Override
	public boolean postExternalDatatypeDrop(Datatype data, MasterDetailTreeNode targetNode, int location) {
		if(data instanceof CodeParameter && targetNode.getDatatype() instanceof PropertyList){
			CodeParameter codeParameter = (CodeParameter) data;
			ProducePropertyMsg rq = new ProducePropertyMsg();
			rq.setName(codeParameter.getName());
			rq.setPropertyType(codeParameter.getType());
			rq.setValue(codeParameter.getDefaultValue());
			try {
				PropertyMsg rs = TestautomationComponentServiceDelegateFactory.getInstance().getProduceProperty().produceProperty(rq);
				PropertyContainer newPropertyContainer = rs.getPropertyContainer();

				// Perform business model change
				PropertyList targetList = (PropertyList) targetNode.getDatatype();
				newPropertyContainer.setOrderIndex(targetList.getPropertyList().size());
				targetList.getPropertyList().add(newPropertyContainer);
				org.nabucco.testautomation.ui.rcp.model.property.DataModelManager.normalizeOrderIndicies(targetList, false);
				
				// Perform ui change
				MasterDetailTreeNode newNode = new MasterDetailTreeNode(newPropertyContainer.getProperty(), targetNode);
				newNode.setViewModel(targetNode.getViewModel());
				targetNode.getChildren().add(newNode);
			} catch (ClientException e) {
				Activator.getDefault().logError(e);
			}
			
		} else if(data instanceof CodeParameter && targetNode.getDatatype() instanceof Property){
			CodeParameter codeParameter = (CodeParameter) data;
			ProducePropertyMsg rq = new ProducePropertyMsg();
			rq.setName(codeParameter.getName());
			rq.setPropertyType(codeParameter.getType());
			rq.setValue(codeParameter.getDefaultValue());
			try {
				PropertyMsg rs = TestautomationComponentServiceDelegateFactory.getInstance().getProduceProperty().produceProperty(rq);
				PropertyContainer newPropertyContainer = rs.getPropertyContainer();

				// Perform business model change
				PropertyList targetList = (PropertyList) targetNode.getParent().getDatatype();
				newPropertyContainer.setOrderIndex(targetList.getPropertyList().size());
				targetList.getPropertyList().add(newPropertyContainer);
				org.nabucco.testautomation.ui.rcp.model.property.DataModelManager.normalizeOrderIndicies(targetList, false);
				
				// Perform ui change
				MasterDetailTreeNode newNode = new MasterDetailTreeNode(newPropertyContainer.getProperty(), targetNode.getParent());
				newNode.setViewModel(targetNode.getViewModel());
				targetNode.getParent().getChildren().add(newNode);
			} catch (ClientException e) {
				Activator.getDefault().logError(e);
			}
			
		}
		return false;
	}
		

}