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
package org.nabucco.testautomation.script.impl.service;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionAccessor;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.testautomation.script.facade.component.ScriptComponent;
import org.nabucco.testautomation.script.facade.component.ScriptComponentLocator;
import org.nabucco.testautomation.script.facade.datatype.comparator.FolderSorter;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.message.FolderSearchMsg;
import org.nabucco.testautomation.script.facade.service.search.SearchScript;

/**
 * FolderSupport
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class FolderSupport {

    private static final FolderSorter folderSorter = new FolderSorter();

    private static FolderSupport instance;

    private Folder rootFolder;

    private FolderSupport() {
    }

    public static synchronized FolderSupport getInstance() {

        if (instance == null) {
            instance = new FolderSupport();
        }
        return instance;
    }

    public void sort(Folder folder) {

        List<Folder> children = folder.getSubFolderList();
        folderSorter.sort(children);

        for (Folder child : children) {
            sort(child);
        }
    }

    public synchronized Folder getRootFolder(ServiceMessageContext ctx) throws SearchException {

        if (this.rootFolder == null) {

            try {
                ScriptComponent scriptComponent = ScriptComponentLocator.getInstance().getComponent();
                SearchScript search = scriptComponent.getSearchScript();
                ServiceRequest<FolderSearchMsg> rq = new ServiceRequest<FolderSearchMsg>(ctx);
                FolderSearchMsg msg = new FolderSearchMsg();
                rq.setRequestMessage(msg);
                this.rootFolder = search.getRootFolder(rq).getResponseMessage().getFolder();
            } catch (SearchException ex) {
                throw ex;
            } catch (Exception ex) {
                throw new SearchException(ex);
            }
        }
        return this.rootFolder;
    }

    public Folder resolveFolder(Folder folder, PersistenceManager pm) throws SearchException {

        // Resolve TestScripts
        resolveTestScripts(folder, pm);

        // Resolve subFolders
        List<Folder> subFolderList = folder.getSubFolderList();

        for (int i = 0; i < subFolderList.size(); i++) {
            subFolderList.set(i, resolveFolder(subFolderList.get(i), pm));
        }

        return folder;
    }

    private void resolveTestScripts(Folder folder, PersistenceManager pm) throws SearchException {

        try {
            NabuccoQuery<TestScript> query = pm.createQuery("FROM TestScript t WHERE folder = :folder ORDER BY t.name");
            query.setParameter("folder", folder);

            List<TestScript> scripts = (List<TestScript>) query.getResultList();
            List<TestScript> testScriptList = folder.getTestScriptList();
            testScriptList.clear();
            testScriptList.addAll(scripts);
            NabuccoCollectionAccessor.getInstance().clearAssignments(testScriptList);
        } catch (PersistenceException e) {
            throw new SearchException(e);
        }
    }

}
