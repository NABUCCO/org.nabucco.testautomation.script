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
package org.nabucco.testautomation.script.impl.service.search;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.base.impl.service.maintain.PersistenceCleaner;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.message.FolderListMsg;
import org.nabucco.testautomation.script.facade.message.FolderSearchMsg;
import org.nabucco.testautomation.script.impl.service.FolderSupport;

/**
 * GetFolderStructureServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class GetFolderStructureServiceHandlerImpl extends GetFolderStructureServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected FolderListMsg getFolderStructure(FolderSearchMsg msg) throws SearchException {

        String statement = "FROM Folder f WHERE f.root = :root";

        try {
            NabuccoQuery<Folder> query = super.getPersistenceManager().createQuery(statement);
            query.setParameter("root", new Flag(Boolean.TRUE));

            List<Folder> folderList = query.getResultList();

            // No RootFolder exists in database
            if (folderList.isEmpty()) {
                throw new SearchException("No RootFolder found");
            }

            for (Folder folder : folderList) {
                try {
                    FolderSupport.getInstance().resolveFolder(folder, super.getPersistenceManager());
                } catch (Exception e) {
                    super.getLogger().error(e, "Could not load '" + folder.getId() + "'");
                }
            }

            // Detach Entity
            this.getPersistenceManager().clear();

            // Sort
            for (Folder folder : folderList) {

                try {
                    folder.accept(new PersistenceCleaner());
                } catch (VisitorException e) {
                    throw new SearchException(e);
                }

                FolderSupport.getInstance().sort(folder);
            }

            FolderListMsg rs = new FolderListMsg();
            rs.getFolderList().addAll(folderList);
            return rs;
        } catch (PersistenceException e) {
            throw new SearchException("Error while getting folder structure", e);
        }
    }

}
