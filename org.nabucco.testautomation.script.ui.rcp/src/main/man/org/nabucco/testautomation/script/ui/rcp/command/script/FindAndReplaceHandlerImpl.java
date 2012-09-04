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
package org.nabucco.testautomation.script.ui.rcp.command.script;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.testautomation.property.facade.datatype.base.Property;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.ScriptMaintenanceMultiplePageEditViewModel;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.wizard.find.FindAndReplaceWizard;

/**
 * FindAndReplaceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class FindAndReplaceHandlerImpl implements FindAndReplaceHandler {
	
	private FindAndReplaceWizard wizard;
	private MasterDetailTreeNode node;
	
	@Override
	public void findAndReplace() {
		
		ScriptMaintenanceMultiplePageEditViewModel model = (ScriptMaintenanceMultiplePageEditViewModel) Activator.getDefault().getModel().getCurrentView().getModel();
		int matches = 0;
		if(node != null) {
			Datatype datatype = node.getDatatype();
			matches = findAndReplace(datatype);
		}
		if (matches > 0) {
			model.setDirty(true);
		}
	}
	
	private int findAndReplace(Datatype datatype) {
		if(datatype != null) {
			
			if(datatype instanceof TestScriptElement) {
				TestScriptElement element = (TestScriptElement) datatype;
				wizard = new FindAndReplaceWizard(element);
			} else if(datatype instanceof Property) {
				Property property = (Property) datatype;
				wizard = new FindAndReplaceWizard(property);
			}
			return startWizard();
		}
		return 0;
	}

    private int startWizard() {
	    wizard.init(Activator.getDefault().getWorkbench(), StructuredSelection.EMPTY);
	    WizardDialog wizardDialog = new WizardDialog(Activator.getDefault().getWorkbench()
	    		.getActiveWorkbenchWindow().getShell(), wizard);
	    // wizardDialog.setPageSize(250, 200);
	    int returnCode = wizardDialog.open();
	    
	    if (returnCode == WizardDialog.OK) {
	    	int matches = wizard.getMatches();
	    	
	    	Shell shell = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
	    	MessageDialog.openInformation(shell, I18N.i18n(FindAndReplaceWizard.ID + ".info.title"),
	    			I18N.i18n(FindAndReplaceWizard.ID + ".info.message") + matches);
	    	
	    	return matches;
	    }
	    return 0;
    }

	/* (non-Javadoc)
	 * @see org.nabucco.testautomation.script.ui.rcp.command.script.FindAndReplaceHandler#setNode(org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode)
	 */
	@Override
	public void setNode(MasterDetailTreeNode node) {
		this.node = node;
	}

}
