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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.action.metadata;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.tree.TreePickerDialog;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.tree.TreePickerDialogParameter;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.command.metadata.OpenMetadataHandlerImpl;


public class MetadataTreePickerDialog extends TreePickerDialog{



	private static final String EDIT = "editMetadata";
	public static final String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.action.metadata.MetadataTreePickerDialog";

	public MetadataTreePickerDialog(Shell parentShell,
			TreePickerDialogParameter parameter) {
		super(parentShell, parameter);
		setInitialExpandLevel(1);
	}
	

	@Override
	protected void createButtonsForButtonBar(Composite parent) {

		// increment the number of columns in the button bar
		((GridLayout) parent.getLayout()).numColumns++;
		Button button = new Button(parent, SWT.PUSH);
		button.setText(I18N.i18n(ID + "." + EDIT));
		button.setFont(JFaceResources.getDialogFont());
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				openMetadataForEdit();
			}
		});
		setButtonLayoutData(button);
		
		super.createButtonsForButtonBar(parent);
	}


	private void openMetadataForEdit(){
		StructuredSelection selection = (StructuredSelection) this.treeViewer.getSelection();
		if(selection != null){
			Metadata selectedMetadata = (Metadata) selection.getFirstElement();
			OpenMetadataHandlerImpl openMetadataHandlerImpl = new OpenMetadataHandlerImpl(selectedMetadata.getId());
			openMetadataHandlerImpl.run();
		}
		this.treeViewer.setSelection(null);
		this.close();
}













}
