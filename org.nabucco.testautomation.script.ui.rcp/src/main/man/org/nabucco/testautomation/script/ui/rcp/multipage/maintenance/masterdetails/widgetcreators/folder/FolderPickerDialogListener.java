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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.masterdetails.widgetcreators.folder;

import java.util.Set;

import org.eclipse.swt.events.TypedEvent;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.PickerDialogSelectionListener;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;


/**
 * FolderPickerDialogListener
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class FolderPickerDialogListener implements PickerDialogSelectionListener {

    public static final String ID = "";

    private FolderPickerActionComboMiniModel model;

    /**
     * Creates a new {@link FolderPickerDialogListener} instance.
     * 
     * @param model
     *            the mini model
     */
    public FolderPickerDialogListener(FolderPickerActionComboMiniModel model) {
        if (model == null) {
            throw new IllegalArgumentException(
                    "Cannot create MetadataPickerDialogListener for model [null].");
        }
        this.model = model;
    }

    @Override
    public void elementSelected(TypedEvent event) {
        if (event == null || event.data == null) {
            return;
        }

        if (!(event.data instanceof Set<?>)) {
            return;
        }

        @SuppressWarnings("unchecked")
        Set<Folder> folderSet = (Set<Folder>) event.data;
        this.model.setFolder(folderSet.iterator().next());
    }
}
