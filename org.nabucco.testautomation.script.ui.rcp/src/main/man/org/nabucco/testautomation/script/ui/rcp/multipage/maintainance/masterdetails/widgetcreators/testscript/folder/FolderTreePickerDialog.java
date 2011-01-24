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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.testscript.folder;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.tree.TreePickerDialog;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.tree.TreePickerDialogParameter;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.ui.rcp.communication.ScriptComponentServiceDelegateFactory;
import org.nabucco.testautomation.script.ui.rcp.communication.maintain.MaintainFolderDelegate;
import org.nabucco.testautomation.script.ui.rcp.communication.produce.ProduceFolderDelegate;


public class FolderTreePickerDialog extends TreePickerDialog{



	private static final String DELETE = "deleteFolder";
	public static final String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.testscript.folder.FolderTreePickerDialog";
	private static final String NEW = "newFolder";
	private Shell shell;

	public FolderTreePickerDialog(Shell parentShell,
			TreePickerDialogParameter parameter) {
		super(parentShell, parameter);
		this.shell = parentShell;
		this.setTreeStyle(SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI | SWT.BORDER);
	}


	@Override
	protected void createButtonsForButtonBar(Composite parent) {

		// increment the number of columns in the button bar
		((GridLayout) parent.getLayout()).numColumns++;
		Button button = new Button(parent, SWT.PUSH);
		button.setText(I18N.i18n(ID + "." + NEW));
		button.setFont(JFaceResources.getDialogFont());
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				addFolder();
			}
		});
		setButtonLayoutData(button);

		// increment the number of columns in the button bar
		((GridLayout) parent.getLayout()).numColumns++;
		button = new Button(parent, SWT.PUSH);
		button.setText(I18N.i18n(ID + "." + DELETE));
		button.setFont(JFaceResources.getDialogFont());
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				deleteFolder();
			}
		});
		setButtonLayoutData(button);

		super.createButtonsForButtonBar(parent);
	}



	private void addFolder(){
		NewFolderDialog dialog = new NewFolderDialog(this.shell, this.dialogArea);
		String folderName = dialog.open();
		StructuredSelection selection = (StructuredSelection) this.treeViewer.getSelection();
		Folder parentFolder;
		if(selection != null && selection.getFirstElement() != null){
			parentFolder = (Folder) selection.getFirstElement();
		} else {
			parentFolder = ((FolderPickerDialogContentProvider) this.treeViewer.getContentProvider()).getRoot();
		}
		if(parentFolder == null){
			Activator.getDefault().logError("No parent folder or root folder found.");
			return;
		}
		try {
			ProduceFolderDelegate produceFolder = ScriptComponentServiceDelegateFactory.getInstance().getProduceFolder();
			FolderMsg response = produceFolder.produceFolder(new EmptyServiceMessage());
			Folder folder = response.getFolder();
			folder.setName(folderName);
			folder.setRoot(false);
			MaintainFolderDelegate maintainFolder = ScriptComponentServiceDelegateFactory.getInstance().getMaintainFolder();
			FolderMsg rq = new FolderMsg();
			rq.setFolder(folder);
			response = maintainFolder.maintainFolder(rq);
			folder = response.getFolder(); // TODO check if these server calls can be reduced to one

			parentFolder.getSubFolderList().add(folder);
			parentFolder.setDatatypeState(DatatypeState.MODIFIED);
			rq.setFolder(parentFolder);
			maintainFolder.maintainFolder(rq);
			this.treeViewer.refresh();
		} catch (ClientException e) {
			Activator.getDefault().logError(e);
		}

	}

	private void deleteFolder(){
		try {
			StructuredSelection selection = (StructuredSelection) this.treeViewer.getSelection();
			if(selection != null){
				Folder selectedFolder = (Folder) selection.getFirstElement();
				if(selectedFolder.getRoot() == null || selectedFolder.getRoot().getValue() == false){
					MaintainFolderDelegate maintainFolder = ScriptComponentServiceDelegateFactory.getInstance().getMaintainFolder();
					selectedFolder.setDatatypeState(DatatypeState.DELETED);
					FolderMsg rq = new FolderMsg();
					rq.setFolder(selectedFolder);
					maintainFolder.maintainFolder(rq);
				}
			}
			this.treeViewer.refresh();
		} catch (ClientException e) {
			Activator.getDefault().logError(e);
		}		
	}
















}
