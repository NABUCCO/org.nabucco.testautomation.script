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

import java.util.List;

import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollection;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.tree.TreePickerDialogContentProvider;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.message.FolderListMsg;
import org.nabucco.testautomation.script.facade.message.FolderSearchMsg;
import org.nabucco.testautomation.script.ui.rcp.communication.ScriptComponentServiceDelegateFactory;
import org.nabucco.testautomation.script.ui.rcp.communication.search.SearchScriptDelegate;


/**
 * MetadataPickerDialogContentProvider
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class FolderPickerDialogContentProvider extends TreePickerDialogContentProvider<Folder> {

	
    @Override
    public boolean hasChildren(Object element) {
        if (element instanceof Folder) {
            List<Folder> children = ((Folder) element).getSubFolderList();
            if (this.isLazy(children)) {
                return true;
            }
            return children.size() > 0;

        }
        return false;
    }

    @Override
    public Folder[] getChildren(Object element) {
        if (element instanceof Folder) {
        	Folder folder = (Folder) element;
            List<Folder> children =folder.getSubFolderList();

            if (!this.isLazy(children)) {
                return children.toArray(new Folder[children.size()]);
            }
        }
        return new Folder[0];
    }

    @Override
    public Folder[] getElements(Object element) {
    	
        if (element instanceof FolderPickerActionComboMiniModel) {
            List<Folder> rootFolderList = this.loadRootFolder();
			return rootFolderList.toArray(new Folder[rootFolderList.size()]);
        }

        if (element instanceof List<?>) {

            @SuppressWarnings("unchecked")
            List<Folder> elementList = (List<Folder>) element;

            if (!this.isLazy(elementList)) {
                return elementList.toArray(new Folder[elementList.size()]);
            }
        }
        return new Folder[0];
    }

    /**
     * Checks whether a collection is lazy or not.
     * 
     * @param collection
     *            the collection to validate
     * 
     * @return <b>true</b> if the collection is lazy, <b>false</b> if not
     */
    private boolean isLazy(List<?> collection) {
        if (collection instanceof NabuccoCollection<?>) {
            NabuccoCollectionState state = ((NabuccoCollection<?>) collection).getState();
            if (state == NabuccoCollectionState.LAZY) {
                return true;
            }
        }
        return false;
    }

    /**
     * Loads all folder roots from the server.
     * 
     * @return the loaded folder roots
     */
    private List<Folder> loadRootFolder() {
        try {
        	SearchScriptDelegate searchFolderDelegate = ScriptComponentServiceDelegateFactory
            	.getInstance().getSearchScript();

            FolderSearchMsg msg = new FolderSearchMsg();
			FolderListMsg rs = searchFolderDelegate.getFolderStructure(msg);
            return rs.getFolderList();
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }

		return null;
    }

}
