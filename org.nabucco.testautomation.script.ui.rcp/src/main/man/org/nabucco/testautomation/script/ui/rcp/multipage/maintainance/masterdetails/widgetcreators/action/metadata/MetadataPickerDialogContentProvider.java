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

import java.util.ArrayList;
import java.util.List;

import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollection;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.tree.TreePickerDialogContentProvider;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.message.MetadataListMsg;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.MetadataSearchMsg;
import org.nabucco.testautomation.script.ui.rcp.communication.ScriptComponentServiceDelegateFactory;
import org.nabucco.testautomation.script.ui.rcp.communication.search.SearchMetadataDelegate;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.action.MetadataPickerActionComboMiniModel;


/**
 * MetadataPickerDialogContentProvider
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MetadataPickerDialogContentProvider extends TreePickerDialogContentProvider<Metadata> {

    @Override
    public boolean hasChildren(Object element) {
        if (element instanceof Metadata) {
            List<Metadata> children = ((Metadata) element).getChildren();
            if (this.isLazy(children)) {
                return true;
            }
            return children.size() > 0;

        }
        return false;
    }

    @Override
    public Metadata[] getChildren(Object element) {
        if (element instanceof Metadata) {
            Metadata metadata = (Metadata) element;
            List<Metadata> children = this.loadChildren(metadata).getChildren();

            if (!this.isLazy(children)) {
                return children.toArray(new Metadata[children.size()]);
            }
        }
        return new Metadata[0];
    }

    @Override
    public Metadata[] getElements(Object element) {
        if (element instanceof MetadataPickerActionComboMiniModel) {
            List<Metadata> roots = this.loadMetdataRoots();
            return roots.toArray(new Metadata[roots.size()]);
        }

        if (element instanceof List<?>) {

            @SuppressWarnings("unchecked")
            List<Metadata> elementList = (List<Metadata>) element;

            if (!this.isLazy(elementList)) {
                return elementList.toArray(new Metadata[elementList.size()]);
            }
        }
        return new Metadata[0];
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
     * Load the metadata children.
     * 
     * @param metadata
     *            the metadata to load
     * 
     * @return the loaded metadata object
     */
    private Metadata loadChildren(Metadata metadata) {
        try {
            SearchMetadataDelegate searchMetadata = ScriptComponentServiceDelegateFactory
                    .getInstance().getSearchMetadata();

            MetadataMsg rq = new MetadataMsg();
            rq.setMetadata(metadata);
            MetadataMsg rs = searchMetadata.getChildren(rq);

            return rs.getMetadata();

        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }

        return metadata;
    }

    /**
     * Loads all metadata roots from the server.
     * 
     * @return the loaded metadata roots
     */
    private List<Metadata> loadMetdataRoots() {

        try {
            SearchMetadataDelegate searchMetadata = ScriptComponentServiceDelegateFactory
                    .getInstance().getSearchMetadata();

            MetadataSearchMsg rq = new MetadataSearchMsg();
            MetadataListMsg rs = searchMetadata.searchMetadata(rq);

            return rs.getMetadataList();

        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }

        return new ArrayList<Metadata>();
    }

}
