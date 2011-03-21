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
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.copypaste.AbstractCopyPasteHandler;
import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.facade.datatype.property.base.Property;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyContainer;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.MetadataMaintenanceMasterDetailTreeNodeCreator;
import org.nabucco.testautomation.ui.rcp.model.property.TestautomationElementFactory;

/**
 * MetadataCopyPasteHandler
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class MetadataCopyPasteHandler extends AbstractCopyPasteHandler {

	private MetadataMaintenanceMasterDetailTreeNodeCreator treeNodeCreator;

	public MetadataCopyPasteHandler(){
		this.treeNodeCreator = new MetadataMaintenanceMasterDetailTreeNodeCreator();
	}

	@Override
	public void paste(MasterDetailTreeNode targetNode,
			Datatype copiedDatatype) {
		
		if(!super.validate(targetNode, copiedDatatype)){
			return;
		}

		Class<? extends Datatype>[] possibleChildren = DataModelManager.getPossibleChildrenTypes(targetNode.getDatatype());
		if(Arrays.asList(possibleChildren).contains(copiedDatatype.getClass())){
			Datatype targetDatatype = targetNode.getDatatype();
			Datatype clone;
			if(targetDatatype instanceof Metadata){
				if(copiedDatatype instanceof Metadata){
					Metadata copiedMetadata = (Metadata) copiedDatatype;
					copiedMetadata = (Metadata) MetadataElementFactory.clone(copiedMetadata);
					copiedMetadata.setParent((Metadata) targetDatatype);
					clone = copiedMetadata;
					((Metadata) targetDatatype).getChildren().add(copiedMetadata);
				} else if(copiedDatatype instanceof MetadataLabel){
					MetadataLabel copiedMetadataLabel = (MetadataLabel) copiedDatatype;
					clone = MetadataElementFactory.clone(copiedMetadataLabel);	
					((Metadata) targetDatatype).getLabelList().add((MetadataLabel) clone);
				} else {
					return;
				}
			} else if(targetDatatype instanceof MetadataLabel){
				if(copiedDatatype instanceof PropertyList){
					MetadataLabel targetMetadataLabel = (MetadataLabel) targetDatatype;
					if(targetMetadataLabel.getPropertyList() == null){
						PropertyList copiedPropertyList = (PropertyList) copiedDatatype;
						PropertyContainer propertyContainer = TestautomationElementFactory.clone(copiedPropertyList);	
						clone = propertyContainer.getProperty();
						targetMetadataLabel.setPropertyList((PropertyList) clone);
					} else {
						return;
					}
				} else {
					return;
				}
			} else if(targetDatatype instanceof PropertyList && copiedDatatype instanceof Property){
				Property copiedProperty = (Property) copiedDatatype;
				PropertyContainer propertyContainer = TestautomationElementFactory.clone(copiedProperty);
				clone = propertyContainer.getProperty();
				List<PropertyContainer> targetPropertyList = ((PropertyList) targetDatatype).getPropertyList();
				propertyContainer.setOrderIndex(targetPropertyList.size());
				targetPropertyList.add(propertyContainer);
			} else {
				return;
			}

			// Update target TreeNode
			MasterDetailTreeNode newNode = treeNodeCreator.createNodeTyped(clone, targetNode, null);
			targetNode.getChildren().add(newNode);
			newNode.setViewModel(targetNode.getViewModel());
			// Update target DatatypeState
			if(targetDatatype.getDatatypeState() == DatatypeState.PERSISTENT){
				targetDatatype.setDatatypeState(DatatypeState.MODIFIED);
			}
		}

	}

}
