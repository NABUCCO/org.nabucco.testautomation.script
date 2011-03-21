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
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.copypaste.AbstractCopyPasteHandler;
import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.facade.datatype.property.base.Property;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyContainer;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.ScriptMaintainanceMasterDetailTreeNodeCreator;
import org.nabucco.testautomation.ui.rcp.model.property.TestautomationElementFactory;

/**
 * TestScriptCopyPasteHandler
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class TestScriptCopyPasteHandler extends AbstractCopyPasteHandler {

	private ScriptMaintainanceMasterDetailTreeNodeCreator treeNodeCreator;

	public TestScriptCopyPasteHandler(){
		this.treeNodeCreator = new ScriptMaintainanceMasterDetailTreeNodeCreator();
	}
	
	@Override
	public void paste(MasterDetailTreeNode targetNode,
			Datatype copiedDatatype) {
		
		if(!super.validate(targetNode, copiedDatatype)){
			return;
		}

		Datatype targetDatatype = targetNode.getDatatype();
		
		// Copy & Paste of TestScripts
		if (copiedDatatype instanceof TestScript && targetDatatype instanceof TestScript) {
			copyAndPasteTestScript((TestScript) copiedDatatype, (TestScript) targetDatatype, targetNode);
			return;
		}
		
		// Copy & Paste of TestScriptElements and Properties
		Class<? extends Datatype>[] possibleChildren = DataModelManager.getPossibleChildrenTypes(targetNode.getDatatype());
		
		if(Arrays.asList(possibleChildren).contains(copiedDatatype.getClass())){
			Datatype clone;
			Integer index = null;
			
			if(targetDatatype instanceof TestScriptElement){
				
				if(copiedDatatype instanceof TestScriptElement){
					TestScriptElement copiedTestScriptElement = (TestScriptElement) copiedDatatype;
					TestScriptElementContainer testScriptElementContainer = ScriptElementFactory.clone(copiedTestScriptElement);	
					clone = testScriptElementContainer.getElement();
					DataModelManager.addTestScriptElementChild(targetDatatype, testScriptElementContainer);	
				} else if(copiedDatatype instanceof PropertyList){
					PropertyList copiedPropertyList = (PropertyList) copiedDatatype;
					PropertyContainer propertyContainer = TestautomationElementFactory.clone(copiedPropertyList);	
					clone = propertyContainer.getProperty();
					DataModelManager.addTestScriptElementChild(targetDatatype, propertyContainer.getProperty());
					index = 0;
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
			
			if (index != null) {
				targetNode.getChildren().add(index, newNode);
			} else {
				targetNode.getChildren().add(newNode);
			}
			newNode.setViewModel(targetNode.getViewModel());

			// Update target DatatypeState
			if(targetDatatype.getDatatypeState() == DatatypeState.PERSISTENT){
				targetDatatype.setDatatypeState(DatatypeState.MODIFIED);
			}
		}
	}
	
	/**
	 * Executes the Copy&Paste of one TestScript to another. Pasting of a TestScript is 
	 * only possible, if the target TestScript has DatatypeState INITIALIZED.
	 * 
	 * @param sourceScript the copied TestScript
	 * @param targetScript the target TestScript
	 * @param targetNode the target TreeNode
	 */
	private void copyAndPasteTestScript(TestScript sourceScript, TestScript targetScript, MasterDetailTreeNode targetNode) {
		
		// Coloning of TestScripts is only allowed, if target TestScript is initialized
		if (targetScript.getDatatypeState() == DatatypeState.INITIALIZED) {

			// Clone copied TestScript
			TestScript clone = (TestScript) ScriptElementFactory.clone(sourceScript).getElement();
			ScriptMaintainanceMultiplePageEditViewModel model = (ScriptMaintainanceMultiplePageEditViewModel) targetNode.getViewModel();
			model.setTestScript((TestScript) clone);
			
			// Update target TreeNode
			targetNode.setDatatype(clone);
			targetNode.getChildren().clear();
			
			for (TestScriptElementContainer child : ((TestScript) clone).getTestScriptElementList()) {
				MasterDetailTreeNode newNode = treeNodeCreator.createNodeTyped(child.getElement(), targetNode, null);
				targetNode.getChildren().add(newNode);
				newNode.setViewModel(targetNode.getViewModel());
			}
		}
	}


}
