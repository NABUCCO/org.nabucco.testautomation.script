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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nabucco.framework.base.facade.datatype.code.CodePath;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollection;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.dynamiccode.facade.datatype.DynamicCodeCode;
import org.nabucco.framework.common.dynamiccode.facade.message.DynamicCodeCodeListMsg;
import org.nabucco.framework.common.dynamiccode.facade.message.search.CodePathSearchMsg;
import org.nabucco.framework.common.dynamiccode.ui.rcp.communication.DynamicCodeComponentServiceDelegateFactory;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.message.MetadataListMsg;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.MetadataSearchMsg;
import org.nabucco.testautomation.script.ui.rcp.communication.ScriptComponentServiceDelegateFactory;
import org.nabucco.testautomation.script.ui.rcp.communication.search.SearchMetadataDelegate;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.action.MetadataPickerActionComboMiniModel;
import org.nabucco.testautomation.ui.rcp.base.dialog.OwnerSelectionTreePickerDialogContentProvider;

/**
 * MetadataPickerDialogContentProvider
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class MetadataPickerDialogContentProvider extends
        OwnerSelectionTreePickerDialogContentProvider<Metadata> {

    private List<Metadata> metadataRoots;

    private Map<Long, List<Metadata>> childrenCache = new HashMap<Long, List<Metadata>>();

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
	public void setSelectedOwner(String selectedOwner) {
    	this.metadataRoots = null;
		super.setSelectedOwner(selectedOwner);
	}

	@Override
    public Metadata[] getChildren(Object element) {
        if (element instanceof Metadata) {
            Metadata metadata = (Metadata) element;

            if (this.childrenCache.containsKey(metadata.getId())) {
                List<Metadata> children = this.childrenCache.get(metadata.getId());
                return children.toArray(new Metadata[children.size()]);
            }

            List<Metadata> children = this.loadChildren(metadata).getChildren();

            if (!this.isLazy(children)) {
                this.childrenCache.put(metadata.getId(), children);
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

        if (this.metadataRoots == null) {

            try {
                SearchMetadataDelegate searchMetadata = ScriptComponentServiceDelegateFactory
                        .getInstance().getSearchMetadata();

                MetadataSearchMsg rq = new MetadataSearchMsg();
                rq.setOwner(this.selectedOwner);
                MetadataListMsg rs = searchMetadata.searchMetadata(rq);

                this.metadataRoots = rs.getMetadataList();

            } catch (ClientException e) {
                Activator.getDefault().logError(e);
            }
        }

        return this.metadataRoots;
    }

    @Override
    public String[] getOwners() {
        CodePathSearchMsg rq = new CodePathSearchMsg();
        rq.setCodePath(new CodePath("nabucco.owner"));
        try {
            DynamicCodeCodeListMsg codeListMsg = DynamicCodeComponentServiceDelegateFactory
                    .getInstance().getSearchDynamicCode().searchByCodePath(rq);
            NabuccoList<DynamicCodeCode> codeList = codeListMsg.getCodeList();
            this.availableOwners.clear();
            this.availableOwners.add("");
            for (DynamicCodeCode dynamicCodeCode : codeList) {
                this.availableOwners.add(dynamicCodeCode.getName().getValue());
            }
            return this.availableOwners.toArray(new String[this.availableOwners.size()]);
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
        return null;
    }

    @Override
    public void dispose() {
        super.dispose();
        
        this.metadataRoots = null;
        this.childrenCache.clear();
    }
    
}
