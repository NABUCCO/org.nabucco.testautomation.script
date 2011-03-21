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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Enumeration;
import org.nabucco.framework.base.facade.datatype.NBoolean;
import org.nabucco.framework.base.facade.datatype.NDate;
import org.nabucco.framework.base.facade.datatype.NDouble;
import org.nabucco.framework.base.facade.datatype.NFloat;
import org.nabucco.framework.base.facade.datatype.NInteger;
import org.nabucco.framework.base.facade.datatype.NLong;
import org.nabucco.framework.base.facade.datatype.NString;
import org.nabucco.framework.base.facade.datatype.NText;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.initializer.EnumInitializer;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.initializer.Initializer;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.initializer.NBooleanInitializer;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.initializer.NDateInitializer;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.initializer.NDoubleInitializer;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.initializer.NFloatInitializer;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.initializer.NIntegerInitializer;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.initializer.NLongInitializer;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.initializer.NStringInitializer;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.initializer.NTextInitializer;
import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.facade.datatype.property.base.Property;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyContainer;
import org.nabucco.testautomation.facade.message.ProducePropertyMsg;
import org.nabucco.testautomation.facade.message.PropertyMsg;
import org.nabucco.testautomation.script.facade.datatype.code.CodeParameter;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.MetadataMaintenanceMultiPageEditView;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataElementFactory;
import org.nabucco.testautomation.ui.rcp.communication.TestautomationComponentServiceDelegateFactory;
import org.nabucco.testautomation.ui.rcp.model.property.DataModelManager;

/**
 * CodeParameterListDoubleClickListener
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class CodeParameterListDoubleClickListener implements IDoubleClickListener {

	private static final String METADATA_PROPS = "MetadataProps";

	private Map<Class<?>, Initializer<?>> initializerMap;
	
	private TableViewer tableViewer;

	private MetadataMaintenanceMultiPageEditView view;
	
	private TreeViewer treeViewer;
	
	public CodeParameterListDoubleClickListener(TableViewer listViewer){
		this.tableViewer = listViewer;
		this.view = (MetadataMaintenanceMultiPageEditView) Activator.getDefault().getView(
				MetadataMaintenanceMultiPageEditView.ID);
		treeViewer = view.getMasterDetailsBlock().getTreeViewer();
		
        this.initializerMap = new HashMap<Class<?>, Initializer<?>>();
        this.initializerMap.put(Enumeration.class, new EnumInitializer());
        this.initializerMap.put(NString.class, new NStringInitializer());
        this.initializerMap.put(NText.class, new NTextInitializer());
        this.initializerMap.put(NBoolean.class, new NBooleanInitializer());
        this.initializerMap.put(NInteger.class, new NIntegerInitializer());
        this.initializerMap.put(NFloat.class, new NFloatInitializer());
        this.initializerMap.put(NDouble.class, new NDoubleInitializer());
        this.initializerMap.put(NLong.class, new NLongInitializer());
        this.initializerMap.put(NDate.class, new NDateInitializer());
		
	}

	@Override
	public void doubleClick(DoubleClickEvent arg0) {
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
						propertyList.setName(METADATA_PROPS);
						metadataLabel.setPropertyList(propertyList);
						
						if (metadataLabel.getDatatypeState() == DatatypeState.PERSISTENT) {
							metadataLabel.setDatatypeState(DatatypeState.MODIFIED);
						}
						
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
			PropertyMsg rs = TestautomationComponentServiceDelegateFactory.getInstance().getProduceProperty().produceProperty(rq);
			PropertyContainer newPropertyContainer = rs.getPropertyContainer();
			
			// Perform business model change
			newPropertyContainer.setOrderIndex(propertyList.getPropertyList().size());
			propertyList.getPropertyList().add(newPropertyContainer);
			DataModelManager.normalizeOrderIndicies(propertyList, false);
			if(propertyList.getDatatypeState() == DatatypeState.PERSISTENT){
				propertyList.setDatatypeState(DatatypeState.MODIFIED);
			}
			
			// Perform ui change
			MasterDetailTreeNode newNode = new MasterDetailTreeNode(newPropertyContainer.getProperty(), propertyListNode);
			newNode.setViewModel(propertyListNode.getViewModel());
			propertyListNode.getChildren().add(newNode);
			view.getModel().setDirty(true);
		} catch (ClientException e) {
			Activator.getDefault().logError(e);
		}
	}


}
