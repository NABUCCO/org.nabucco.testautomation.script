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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.masterdetails;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.testautomation.script.facade.datatype.dictionary.EmbeddedTestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.ui.rcp.command.script.OpenTestScriptHandlerImpl;


/**
 * ScriptMaintenanceMasterDetailBlock
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class ScriptMaintenanceMultiplePageMasterTreeDoubleClickListener implements IDoubleClickListener {

	
	private TreeViewer treeViewer;

	public ScriptMaintenanceMultiplePageMasterTreeDoubleClickListener(TreeViewer treeViewer) {
		this.treeViewer = treeViewer;
	}

	@Override
	public void doubleClick(DoubleClickEvent arg0) {
		MasterDetailTreeNode node = (MasterDetailTreeNode) ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
		
		if(node != null && node.getDatatype() != null && node.getDatatype() instanceof EmbeddedTestScript){
			TestScript testScript = ((EmbeddedTestScript) node.getDatatype()).getTestScript();
			OpenTestScriptHandlerImpl openTestScriptHandlerImpl = new OpenTestScriptHandlerImpl(testScript.getId());
			openTestScriptHandlerImpl.run();
		}
	}

}
