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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.nabucco.framework.plugin.base.Activator;

import org.nabucco.testautomation.facade.datatype.property.FileProperty;

public class OpenFileDialogListener implements SelectionListener {

	private FileProperty fileProperty;
	private Text fileNameTextInput;
	private NameContentCombinationMiniModel model;

	public OpenFileDialogListener(FileProperty fileProperty,
			Text fileNameTextInput, NameContentCombinationMiniModel model) {
		this.fileProperty = fileProperty;
		this.fileNameTextInput = fileNameTextInput;
		this.model = model;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// nothing to do
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		FileDialog fd = new FileDialog(Activator.getDefault().getWorkbench()
				.getDisplay().getActiveShell(), SWT.OPEN);
		String filename = fd.open();
		if (filename != null) {
			File f = new File(filename);
			if (f.exists()) {
				FileInputStream fis;
				try {
					fis = new FileInputStream(f);
					byte[] b = new byte[(int) f.length()];
					fis.read(b);
					fileProperty.setName(f.getName());
					fileNameTextInput.setText(f.getName());
					String content = new String(b);
					model.setContent(content);
				} catch (FileNotFoundException e) {
					Activator.getDefault().logError(e);
				} catch (IOException e) {
					Activator.getDefault().logError(e);
				}
			}
		}
	}
}
