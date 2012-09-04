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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.masterdetails.widgetcreators.scripting;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.tree.TreePickerDialog;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.tree.TreePickerDialogParameter;

/**
 * ScriptTreePickerDialog
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ScriptTreePickerDialog extends TreePickerDialog {

    public static final String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.masterdetails.widgetcreators.scripting.ScriptTreePickerDialog";

    public ScriptTreePickerDialog(Shell parentShell, TreePickerDialogParameter parameter) {
        super(parentShell, parameter);
        this.setTreeStyle(SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI | SWT.BORDER);
    }

}
