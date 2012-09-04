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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.masterdetails.widgetcreators.action;

import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.testautomation.property.facade.datatype.PropertyList;
import org.nabucco.testautomation.property.facade.datatype.base.Property;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyContainer;
import org.nabucco.testautomation.property.facade.message.ProducePropertyMsg;
import org.nabucco.testautomation.property.facade.message.PropertyMsg;
import org.nabucco.testautomation.property.ui.rcp.communication.PropertyComponentServiceDelegateFactory;
import org.nabucco.testautomation.property.ui.rcp.model.property.DataModelManager;
import org.nabucco.testautomation.script.facade.datatype.code.CodeParameter;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.ScriptMaintenanceMultiplePageEditView;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.ScriptElementFactory;

/**
 * CodeParameterListDoubleClickListener
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class CodeParameterListDoubleClickListener implements IDoubleClickListener {

	private static final String ACTION_PARAMS = "ActionParams";
	
	private TableViewer listViewer;

	public CodeParameterListDoubleClickListener(TableViewer listViewer){
		this.listViewer = listViewer;
	}

	@Override
	public void doubleClick(DoubleClickEvent arg0) {
		ScriptMaintenanceMultiplePageEditView view = (ScriptMaintenanceMultiplePageEditView) Activator.getDefault().getView(
				ScriptMaintenanceMultiplePageEditView.ID);
		TreeViewer treeViewer = view.getMasterDetailsBlock().getTreeViewer();
		MasterDetailTreeNode actionNode = (MasterDetailTreeNode) ((StructuredSelection) treeViewer.getSelection())
		.getFirstElement();

		CodeParameter codeParameter = (CodeParameter) ((StructuredSelection) listViewer.getSelection()).getFirstElement();

		if (actionNode.getDatatype() instanceof Action) {
			Action action = (Action) actionNode.getDatatype();
			List<MasterDetailTreeNode> actionChildrenNodeList = actionNode.getChildren();
			boolean foundPropertList = false;
			for (MasterDetailTreeNode actionNodeChild : actionChildrenNodeList) {
				if(actionNodeChild.getDatatype() instanceof PropertyList){
					addProperty(actionNodeChild, codeParameter);
					foundPropertList = true;
					break;
				}						
			}
			if(!foundPropertList){
				PropertyList propertyList = (PropertyList) ((PropertyContainer) ScriptElementFactory.create(PropertyList.class)).getProperty();
				propertyList.setName(ACTION_PARAMS);
				action.setPropertyList(propertyList);
				
				if (action.getDatatypeState() == DatatypeState.PERSISTENT) {
					action.setDatatypeState(DatatypeState.MODIFIED);
				}

				MasterDetailTreeNode newNode = new MasterDetailTreeNode(propertyList, actionNode);
				newNode.setViewModel(actionNode.getViewModel());
				actionNode.getChildren().add(newNode);
				addProperty(newNode, codeParameter);
			}
		}
		treeViewer.refresh();
		treeViewer.expandToLevel(actionNode, 2);
	}

	private void addProperty(MasterDetailTreeNode propertyListNode, CodeParameter codeParameter) {
		
		if (propertyListNode == null || codeParameter == null) {
			return;
		}
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
			PropertyMsg rs = PropertyComponentServiceDelegateFactory.getInstance().getProduceProperty().produceProperty(rq);
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
