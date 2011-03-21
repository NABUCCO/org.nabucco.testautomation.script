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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.widgetcreators.fileproperty;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Shell;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.testautomation.facade.datatype.property.FileProperty;

/**
 * OpenFileContentDialogListener
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class OpenFileContentDialogListener implements SelectionListener {
	
	private NameContentCombinationMiniModel model;
	

	public OpenFileContentDialogListener(FileProperty fileProperty, NameContentCombinationMiniModel model) {
		this.model = model;
	}


	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// nothing to do
	}   

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		Shell shell = new Shell(Activator.getDefault().getWorkbench().getDisplay(), SWT.DIALOG_TRIM
				| SWT.APPLICATION_MODAL | SWT.MAX | SWT.RESIZE);
		FileContentDialog fileContentDialog = new FileContentDialog(shell, model, false);
//		textAreaDialog.setTitle(I18N.i18n("org.nabucco.testautomation.ui.rcp.multipage.detail.textarea.TextAreaDialog.shellTitle"));
//		textAreaDialog.setIcon(ImageProvider
//				.createImage(TestautomationImageRegistry.ICON_DETAILS_DIALOG.getId()));
		fileContentDialog.open();
	}
}
