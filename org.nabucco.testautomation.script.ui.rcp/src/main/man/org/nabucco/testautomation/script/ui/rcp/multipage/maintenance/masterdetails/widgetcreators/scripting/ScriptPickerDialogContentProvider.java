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

import java.util.List;

import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollection;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.dynamiccode.facade.datatype.DynamicCodeCode;
import org.nabucco.framework.common.dynamiccode.facade.message.search.DynamicCodeCodeSearchMsg;
import org.nabucco.framework.common.dynamiccode.ui.rcp.communication.DynamicCodeComponentServiceDelegateFactory;
import org.nabucco.framework.common.dynamiccode.ui.rcp.communication.search.SearchDynamicCodeDelegate;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.tree.TreePickerDialogContentProvider;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptType;
import org.nabucco.framework.support.scripting.facade.message.ScriptListMsg;
import org.nabucco.framework.support.scripting.facade.message.search.ScriptSearchRq;
import org.nabucco.framework.support.scripting.ui.rcp.communication.ScriptingComponentServiceDelegateFactory;
import org.nabucco.framework.support.scripting.ui.rcp.communication.search.ScriptSearchServiceDelegate;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;

/**
 * ScriptPickerDialogContentProvider
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ScriptPickerDialogContentProvider extends TreePickerDialogContentProvider<Script> {

    private TestScriptElementType type;

    public ScriptPickerDialogContentProvider(TestScriptElementType type) {
        this.type = type;
    }

    @Override
    public boolean hasChildren(Object element) {
        return false;
    }

    @Override
    public Script[] getChildren(Object element) {
        return new Script[0];
    }

    @Override
    public Script[] getElements(Object element) {

        if (element instanceof ScriptPickerActionComboMiniModel) {
            List<Script> scriptList = this.loadScripts();
            return scriptList.toArray(new Script[scriptList.size()]);
        }

        if (element instanceof List<?>) {

            @SuppressWarnings("unchecked")
            List<Folder> elementList = (List<Folder>) element;

            if (!this.isLazy(elementList)) {
                return elementList.toArray(new Script[elementList.size()]);
            }
        }
        return new Script[0];
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
     * Loads all scripts from the server.
     * 
     * @return the loaded scripts
     */
    private NabuccoList<Script> loadScripts() {
        try {
            ScriptSearchServiceDelegate searchDelegate = ScriptingComponentServiceDelegateFactory.getInstance()
            .getScriptSearchService();
            ScriptSearchRq msg = new ScriptSearchRq();

            if (this.type != null) {
                SearchDynamicCodeDelegate dynamicCodeDelegate = DynamicCodeComponentServiceDelegateFactory
                        .getInstance().getSearchDynamicCode();
                DynamicCodeCodeSearchMsg rq = new DynamicCodeCodeSearchMsg();
                rq.setName(new Name(this.type.toString()));
                NabuccoList<DynamicCodeCode> codeList = dynamicCodeDelegate.searchDynamicCodeCode(rq).getCodeList();
                
                if (!codeList.isEmpty()) {
                    msg.setContextType(codeList.first());
                }
            }
            
            msg.setType(ScriptType.JAVA);
            msg.setOwner(Activator.getDefault().getModel().getSecurityModel().getSubject().getOwner());
            ScriptListMsg rs = searchDelegate.searchScripts(msg);
            return rs.getScriptList();
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }

        return null;
    }

}
