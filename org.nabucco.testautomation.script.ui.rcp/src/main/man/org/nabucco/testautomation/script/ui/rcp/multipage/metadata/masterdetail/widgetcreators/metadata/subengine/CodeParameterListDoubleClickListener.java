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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.widgetcreators.metadata.subengine;

import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.testautomation.script.facade.datatype.code.CodeParameter;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.MetadataMaintenanceMultiPageEditView;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataElementFactory;

import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.facade.datatype.property.base.Property;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyContainer;
import org.nabucco.testautomation.facade.message.ProducePropertyMsg;
import org.nabucco.testautomation.facade.message.PropertyMsg;
import org.nabucco.testautomation.ui.rcp.communication.TestautomationComponentServiceDelegateFactory;
import org.nabucco.testautomation.ui.rcp.model.property.DataModelManager;

public class CodeParameterListDoubleClickListener implements IDoubleClickListener {


	private TableViewer tableViewer;

	public CodeParameterListDoubleClickListener(TableViewer listViewer){
		this.tableViewer = listViewer;
	}

	@Override
	public void doubleClick(DoubleClickEvent arg0) {
		MetadataMaintenanceMultiPageEditView view = (MetadataMaintenanceMultiPageEditView) Activator.getDefault().getView(
				MetadataMaintenanceMultiPageEditView.ID);
		TreeViewer treeViewer = view.getMasterDetailsBlock().getTreeViewer();
		MasterDetailTreeNode metadataNode = (MasterDetailTreeNode) ((StructuredSelection) treeViewer.getSelection())
		.getFirstElement();

		CodeParameter codeParameter = (CodeParameter) ((StructuredSelection) tableViewer.getSelection()).getFirstElement();

		if (metadataNode.getDatatype() instanceof Metadata) {
			List<MasterDetailTreeNode> labelNodeList = metadataNode.getChildren();
			for (MasterDetailTreeNode labelNode : labelNodeList) {
				if(labelNode.getDatatype() instanceof MetadataLabel){
					MetadataLabel metadataLabel = (MetadataLabel) labelNode.getDatatype();
					List<MasterDetailTreeNode> labelNodeChildren = labelNode.getChildren();
					boolean foundPropertList = false;
					for (MasterDetailTreeNode labelNodeChild : labelNodeChildren) {
						if(labelNodeChild.getDatatype() instanceof PropertyList){
							addProperty(labelNodeChild, codeParameter);
							foundPropertList = true;
							break;
						}						
					}
					if(!foundPropertList){
						PropertyList propertyList = (PropertyList) ((PropertyContainer) MetadataElementFactory.create(PropertyList.class)).getProperty();
						metadataLabel.setPropertyList(propertyList);
						
						MasterDetailTreeNode newNode = new MasterDetailTreeNode(propertyList, labelNode);
						newNode.setViewModel(labelNode.getViewModel());
						labelNode.getChildren().add(newNode);
						addProperty(newNode, codeParameter);
					}
				}
			}
		}
		treeViewer.refresh();
		treeViewer.expandToLevel(metadataNode, 3);
	}

	private void addProperty(MasterDetailTreeNode propertyListNode, CodeParameter codeParameter) {
		PropertyList propertyList = (PropertyList) propertyListNode.getDatatype();
		List<PropertyContainer> properties = propertyList.getPropertyList();
		for (PropertyContainer propertyContainer : properties) {
			Property property = propertyContainer.getProperty();
			if(property.getType() == codeParameter.getType() && property.getName().getValue().equals(codeParameter.getName().getValue())){
				return;
			}
		}
		ProducePropertyMsg rq = new ProducePropertyMsg();
		rq.setName(codeParameter.getName());
		rq.setPropertyType(codeParameter.getType());
		rq.setValue(codeParameter.getDefaultValue());
		try {
			PropertyMsg rs = TestautomationComponentServiceDelegateFactory.getInstance().getProduceProperty().produceProperty(rq);
			PropertyContainer newPropertyContainer = rs.getPropertyContainer();
			
			// Perform business model change
			newPropertyContainer.setOrderIndex(propertyList.getPropertyList().size());
			propertyList.getPropertyList().add(newPropertyContainer);
			DataModelManager.normalizeOrderIndicies(propertyList, false);
			
			// Perform ui change
			MasterDetailTreeNode newNode = new MasterDetailTreeNode(newPropertyContainer.getProperty(), propertyListNode);
			newNode.setViewModel(propertyListNode.getViewModel());
			propertyListNode.getChildren().add(newNode);
		} catch (ClientException e) {
			Activator.getDefault().logError(e);
		}
	}


}
