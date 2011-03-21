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

/**
 * OpenFileChooserDialogListener
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class OpenFileChooserDialogListener implements SelectionListener {

	private static final String DOT = ".";

	private FileProperty fileProperty;
	
	private Text fileNameTextInput;

	private Text propertyNameTextInput;
	
	private NameContentCombinationMiniModel model;

	public OpenFileChooserDialogListener(FileProperty fileProperty,
			Text fileNameTextInput, Text propertyNameTextInput, NameContentCombinationMiniModel model) {
		this.fileProperty = fileProperty;
		this.fileNameTextInput = fileNameTextInput;
		this.propertyNameTextInput = propertyNameTextInput;
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
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(f);
					byte[] b = new byte[(int) f.length()];
					fis.read(b);
					filename = f.getName();
					fileProperty.setFilename(filename);
					
					if (fileProperty.getName() == null
							|| fileProperty.getName().getValue() == null
							|| fileProperty.getName().getValue().equals("")) {
						String propertyName = parseFilename(filename);
						fileProperty.setName(propertyName);
						propertyNameTextInput.setText(propertyName);
					}
					fileNameTextInput.setText(filename); 
					String content = new String(b);
					model.setContent(content);
				} catch (FileNotFoundException e) {
					Activator.getDefault().logError(e);
				} catch (IOException e) {
					Activator.getDefault().logError(e);
				} finally {
					try {
						if (fis != null) {
							fis.close();
						}
					} catch (IOException e) {
						Activator.getDefault().logError(e);
					}
				}
			}
		}
	}
	
	/**
	 * '.' are not allowed in Property-Names. This operation
	 * removes all '.' from the given filename.
	 * 
	 * @param filename the filename to parse
	 * @return a legal name for a Property
	 */
	private String parseFilename(String filename) {
		
		StringBuffer b = new StringBuffer(filename);
		
		if (b.indexOf(DOT) > -1) {
			b.setCharAt(b.lastIndexOf(DOT), '[');
			b.append(']');
		}
		while (b.indexOf(DOT) != -1) {
			b.setCharAt(b.lastIndexOf(DOT), ':');
		}
		return b.toString();
	}
	
}
