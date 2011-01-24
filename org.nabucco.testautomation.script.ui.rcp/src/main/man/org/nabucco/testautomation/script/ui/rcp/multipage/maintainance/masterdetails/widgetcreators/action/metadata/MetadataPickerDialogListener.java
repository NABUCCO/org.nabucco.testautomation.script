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

import java.util.Iterator;
import java.util.Set;

import org.eclipse.swt.events.TypedEvent;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.PickerDialogSelectionListener;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.action.MetadataPickerActionComboMiniModel;


/**
 * MetadataPickerDialogListener
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MetadataPickerDialogListener implements PickerDialogSelectionListener {

    public static final String ID = "";

    private MetadataPickerActionComboMiniModel model;

    /**
     * Creates a new {@link MetadataPickerDialogListener} instance.
     * 
     * @param model
     *            the mini model
     */
    public MetadataPickerDialogListener(MetadataPickerActionComboMiniModel model) {
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
        Set<Metadata> metadataSet = (Set<Metadata>) event.data;
        Iterator<Metadata> iterator = metadataSet.iterator();
		if(iterator.hasNext()){
			this.model.setMetadata(iterator.next());
		}
    }
}
