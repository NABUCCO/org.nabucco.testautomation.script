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

import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.model.MiniViewModel;
import org.nabucco.framework.plugin.base.model.ViewModel;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;


/**
 * TestScriptContainerMiniModel
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class FolderPickerActionComboMiniModel extends MiniViewModel {

	private TestScript testScript;

    public static final String PROPERTY_FOLDER = "folder";

	public static final String PROPERTY_FOLDER_NAME = "folderName";

    /**
     * Creates a new {@link TestScriptContainerTableMiniModel} instance.
     * 
     * @param externalViewModel
     *            the external view model
     * @param datatype
     *            the parent datatype
     */
    public FolderPickerActionComboMiniModel(ViewModel externalViewModel, TestScript testScript) {
        super(externalViewModel, testScript);

        if (testScript == null) {
            throw new IllegalArgumentException(
                    "Cannot create FolderPickerMiniModel for TestScript [null].");
        }

        this.testScript = testScript;
        super.setInitialized();
    }

    /**
     * Getter for the folder.
     * 
     * @return Returns the folder.
     */
    public Folder getFolder() {
        return this.testScript.getFolder();
    }

    /**
     * Getter for the folder.
     * 
     * @return Returns the folder.
     */
    public String getFolderName() {
        if (this.getFolder() != null && this.getFolder().getName() != null) {
            return this.getFolder().getName().getValue();
        }
        return "";
    }

    /**
     * Set the folder in the model.
     * 
     * @param newValue
     *            the folder to set
     */
    public void setFolder(Folder newValue) {
        Object oldValue = this.getFolder();

        this.testScript.setFolder(newValue);

        super.updateProperty(PROPERTY_FOLDER, oldValue, newValue);
        super.updateProperty(PROPERTY_FOLDER_NAME, oldValue, newValue);
    }
    
}
