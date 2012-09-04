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
package org.nabucco.testautomation.script.ui.rcp.multipage.folder.model;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.logging.Loggable;
import org.nabucco.framework.plugin.base.model.BusinessModel;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.ui.rcp.communication.ScriptComponentServiceDelegateFactory;
import org.nabucco.testautomation.script.ui.rcp.communication.maintain.MaintainScriptDelegate;


public class FolderMaintenanceEditViewBusinessModel implements BusinessModel, Loggable {

    public static String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.folder.model.FolderMaintenanceEditViewBusinessModel";

    @Override
    public String getID() {
        return FolderMaintenanceEditViewBusinessModel.ID;
    }

    /**
     * Saves a Folder .
     * 
     * @param folder
     *            the Folder
     * @return the Folder
     * 
     * @throws ClientException
     *             if the save was unsuccesful
     */
    public Folder save(final Folder folder) throws ClientException {
        ScriptComponentServiceDelegateFactory scriptComponentServiceDelegateFactory = ScriptComponentServiceDelegateFactory
                .getInstance();

        MaintainScriptDelegate maintainFolderDelegate = scriptComponentServiceDelegateFactory
                .getMaintainScript();

        FolderMsg rq = new FolderMsg();
        rq.setFolder(folder);
        FolderMsg response = maintainFolderDelegate.maintainFolder(rq);

        if (response != null) {
            return response.getFolder();
        }

        return folder;
    }

//    public Folder readFolder(Folder folder) {
//        try {
//            ScriptComponentServiceDelegateFactory scriptComponentServiceDelegateFactory = ScriptComponentServiceDelegateFactory
//                    .getInstance();
//            SearchFolderDelegate searchFolderDelegate = scriptComponentServiceDelegateFactory
//                    .getSearchFolder();
//
//            FolderSearchMsg rq = new FolderSearchMsg();
//            rq.setIdentifier(new Identifier(folder.getId()));
//            FolderMsg response = searchFolderDelegate.getFolder(rq);
//            if (response != null) {
//                return response.getFolder();
//            }
//        } catch (ClientException e) {
//            Activator.getDefault().logError(e);
//        }
//        return folder;
//    }

}
