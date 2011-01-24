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

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.nabucco.framework.base.facade.datatype.utils.I18N;

public class NewFolderDialog {

	private static final String ID = FolderTreePickerDialog.ID + "." + "NewFolderDialog";
	
	private String folderName;
	
	 private Shell parentShell;

	private Control dialogArea;


	public NewFolderDialog(Shell shell, Control dialogArea) {
		this.parentShell = shell;
		this.dialogArea = dialogArea; 
	}

	public String open() {
		    final Shell shell =
		      new Shell(parentShell, SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL);
		    shell.setLocation(dialogArea.toDisplay(80, 80));
		    shell.setText(I18N.i18n(ID + ".shellTitle"));

		    shell.setLayout(new GridLayout(2, true));

		    Label label = new Label(shell, SWT.NULL);
		    label.setText(I18N.i18n(ID + ".message"));

		    final Text text = new Text(shell, SWT.SINGLE | SWT.BORDER);

		    final Button buttonOK = new Button(shell, SWT.PUSH);
		    buttonOK.setText(I18N.i18n(ID + ".okButton"));
		    buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		    Button buttonCancel = new Button(shell, SWT.PUSH);
		    buttonCancel.setText(I18N.i18n(ID + ".cancelButton"));

		    buttonOK.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event event) {
		        shell.dispose();
		      }
		    });

		    text.addListener(SWT.Modify, new Listener() {
			      public void handleEvent(Event event) {
			          folderName = text.getText();
			          if(folderName != null && folderName.length() > 0){
			        	  buttonOK.setEnabled(true);
			          } else {
			        	  buttonOK.setEnabled(false);
			          }
			      }
			    });
		    
		    buttonCancel.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event event) {
		    	  folderName = null;
		        shell.dispose();
		      }
		    });
		    
		    shell.addListener(SWT.Traverse, new Listener() {
		      public void handleEvent(Event event) {
		        if(event.detail == SWT.TRAVERSE_ESCAPE)
		          event.doit = false;
		      }
		    });

		    text.setText("");
		    shell.pack();
		    shell.open();

		    Display display = parentShell.getDisplay();
		    while (!shell.isDisposed()) {
		      if (!display.readAndDispatch())
		        display.sleep();
		    }

		    return folderName;
		  }
	
}
