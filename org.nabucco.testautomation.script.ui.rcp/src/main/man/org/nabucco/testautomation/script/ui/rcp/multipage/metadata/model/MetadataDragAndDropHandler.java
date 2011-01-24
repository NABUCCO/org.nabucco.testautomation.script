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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model;

import java.util.Arrays;
import java.util.List;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.draganddrop.AbstractDragAndDropHandler;
import org.nabucco.testautomation.script.facade.datatype.code.CodeParameter;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;

import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.facade.datatype.property.base.Property;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyContainer;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyType;
import org.nabucco.testautomation.facade.message.ProducePropertyMsg;
import org.nabucco.testautomation.facade.message.PropertyMsg;
import org.nabucco.testautomation.ui.rcp.communication.TestautomationComponentServiceDelegateFactory;

public class MetadataDragAndDropHandler extends AbstractDragAndDropHandler
implements
org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.draganddrop.DragAndDropHandler {

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

	
	
	
	@Override
	public boolean validateDrop(MasterDetailTreeNode data,
			MasterDetailTreeNode target, int location) {

		// Movement to same node instance makes no sense
		Datatype targetDatatype = target.getDatatype();
		Datatype movedDatatype = data.getDatatype();
		if(movedDatatype == targetDatatype){
			return false;
		} 
		if(location == 1 || location == 2){ 
			MasterDetailTreeNode parentNode = target.getParent();  
			if(parentNode == null){
				return false;
			}
			Datatype targetParentDatatype = parentNode.getDatatype();
			if(targetParentDatatype instanceof PropertyList && movedDatatype instanceof Property){
				return verifyHierarchy(data, target);
			}
			return false;
		} else if(location == 3){
			// It is only allowed to move into composites
			if(targetDatatype instanceof Metadata){
				Metadata targetMetadata = (Metadata) targetDatatype;
				if(movedDatatype instanceof Metadata){
						return verifyHierarchy(data, target);
				} else if(movedDatatype instanceof MetadataLabel){
						return verifyHierarchy(data, target);
				} else if(movedDatatype instanceof PropertyList){
					if(targetMetadata.getPropertyList() == null){
						if(Arrays.asList(DataModelManager.getPossibleChildrenTypes(targetDatatype)).contains(movedDatatype.getClass())){
							return verifyHierarchy(data, target);
						}
					}
					return false;
				}
				return false;
			} if(targetDatatype instanceof MetadataLabel){
				MetadataLabel targetMetadataLabel = (MetadataLabel) targetDatatype;
				if(movedDatatype instanceof PropertyList){
					if(targetMetadataLabel.getPropertyList() == null){
						if(Arrays.asList(DataModelManager.getPossibleChildrenTypes(targetDatatype)).contains(movedDatatype.getClass())){
							return verifyHierarchy(data, target);
						}
					}
					return false;
				}
				return false;
			} else if(targetDatatype instanceof PropertyList && movedDatatype instanceof Property){
				return verifyHierarchy(data, target);
			}
		}
		return false; 
	}
	
	@Override
	public boolean postDrop(MasterDetailTreeNode movedNode, MasterDetailTreeNode targetNode, int location) {
		// Perform changes in data model
		if(location == 1 || location == 2){ 
			Datatype targetParentDatatype = targetNode.getParent().getDatatype();
			if(targetParentDatatype instanceof PropertyList){
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
			if(targetDatatype instanceof Metadata){
				Metadata targetMetadata = (Metadata) targetDatatype;
				Datatype movedDatatype = movedNode.getDatatype();
				if(movedDatatype instanceof Metadata){
					Metadata movedMetadata = (Metadata) movedDatatype;
					targetMetadata.getChildren().add(movedMetadata);
					if(targetMetadata.getDatatypeState() == DatatypeState.PERSISTENT){
						targetMetadata.setDatatypeState(DatatypeState.MODIFIED);
					}
				} else if(movedDatatype instanceof MetadataLabel){
					MetadataLabel movedMetadataLabel = (MetadataLabel) movedDatatype;
					targetMetadata.getLabelList().add(movedMetadataLabel);
					if(targetMetadata.getDatatypeState() == DatatypeState.PERSISTENT){
						targetMetadata.setDatatypeState(DatatypeState.MODIFIED);
					}
				}
			} else if(targetDatatype instanceof MetadataLabel){
				MetadataLabel targetMetadataLabel = (MetadataLabel) targetDatatype;
				Datatype movedDatatype = movedNode.getDatatype();
				if(movedDatatype instanceof PropertyList){
					PropertyList movedList = (PropertyList) movedDatatype;
					targetMetadataLabel.setPropertyList(movedList);
					if(targetMetadataLabel.getDatatypeState() == DatatypeState.PERSISTENT){
						targetMetadataLabel.setDatatypeState(DatatypeState.MODIFIED);
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
		if(parentDatatype instanceof Metadata){
			Metadata parentMetadata = (Metadata) parentDatatype;
			if(droppedDatatype instanceof Metadata){
				int indexOf = parentNode.getChildren().indexOf(movedNode);
				int numberOfLabels = parentMetadata.getLabelList().size();
				parentMetadata.getChildren().remove(indexOf - numberOfLabels);
				if(parentMetadata.getDatatypeState() == DatatypeState.PERSISTENT){
					parentMetadata.setDatatypeState(DatatypeState.MODIFIED);
				}
				// this must stand after the "indexOf" call
				parentNode.getChildren().remove(movedNode);
				return;
			} else if(droppedDatatype instanceof MetadataLabel){
				int indexOf = parentNode.getChildren().indexOf(movedNode);
				(parentMetadata).getLabelList().remove(indexOf);
				if(parentMetadata.getDatatypeState() == DatatypeState.PERSISTENT){
					parentMetadata.setDatatypeState(DatatypeState.MODIFIED);
				}
				// this must stand after the "indexOf" call
				parentNode.getChildren().remove(movedNode);
				return;
			}
		} else if(parentDatatype instanceof MetadataLabel){
			MetadataLabel parentMetadataLabel = (MetadataLabel) parentDatatype;
			if(droppedDatatype instanceof PropertyList){
				parentMetadataLabel.setPropertyList(null);
				parentNode.getChildren().remove(0);
				if(parentMetadataLabel.getDatatypeState() == DatatypeState.PERSISTENT){
					parentMetadataLabel.setDatatypeState(DatatypeState.MODIFIED);
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
			} else if(newNode.getDatatype() instanceof MetadataLabel){
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
	

}