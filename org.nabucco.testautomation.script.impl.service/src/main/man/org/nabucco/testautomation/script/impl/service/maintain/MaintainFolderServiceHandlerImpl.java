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
package org.nabucco.testautomation.script.impl.service.maintain;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionAccessor;
import org.nabucco.framework.base.facade.datatype.visitor.DatatypeVisitor;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceExceptionMapper;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.impl.service.FolderSupport;
import org.nabucco.testautomation.script.impl.service.ScriptSupport;
import org.nabucco.testautomation.script.impl.service.maintain.support.ScriptMaintainSupport;
import org.nabucco.testautomation.script.impl.service.maintain.visitor.FolderModificationVisitor;

/**
 * MaintainFolderServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class MaintainFolderServiceHandlerImpl extends MaintainFolderServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String PREFIX = "FLD-";

    private ScriptMaintainSupport support;

    @Override
    public FolderMsg maintainFolder(FolderMsg msg) throws MaintainException {

        Folder folder = msg.getFolder();

        try {
            // initialize PersistenceHelper
            this.support = new ScriptMaintainSupport(super.getPersistenceManager());

            if (folder.getDatatypeState() == DatatypeState.PERSISTENT) {
                DatatypeVisitor visitor = new FolderModificationVisitor(folder);
                folder.accept(visitor);
            }

            switch (folder.getDatatypeState()) {

            case CONSTRUCTED:
                throw new MaintainException("Folder is not initialized.");
            case INITIALIZED:
                folder = this.create(folder);
                break;
            case MODIFIED:
                folder = this.update(folder);
                break;
            case DELETED:
                this.delete(folder);
                getLogger().info("Folder '" + folder.getName() + "' [" + folder.getId() + "] deleted");
                folder = null;
                break;
            case TRANSIENT:
                break;
            case PERSISTENT:
                break;
            default:
                throw new MaintainException("Datatype state '"
                        + folder.getDatatypeState() + "' is not valid for Folder.");
            }

            this.getPersistenceManager().flush();
            this.support = null;

        } catch (Exception ex) {
            throw new MaintainException("Error maintaining Folder", PersistenceExceptionMapper.resolve(ex,
                    Folder.class.getName(), folder.getId()));
        }

        if (folder != null) {
            try {
                FolderSupport.getInstance().resolveFolder(folder, super.getPersistenceManager());

                // Detach Entity
                this.getPersistenceManager().clear();

                // Sort
                FolderSupport.getInstance().sort(folder);
            } catch (Exception e) {
                super.getLogger().error(e, "Could not load '" + folder.getId() + "'");
            }
            getLogger().info("Folder '" + folder.getName() + "' [" + folder.getId() + "] successfully maintained");
        }

        msg.setFolder(folder);
        return msg;
    }

    private Folder create(Folder entity) throws MaintainException {

        // Create children
        List<Folder> children = entity.getSubFolderList();

        for (int i = 0; i < children.size(); i++) {
            Folder createdChild = create(children.get(i));
            children.set(i, createdChild);
        }

        // Create Folder
        List<TestScript> testScriptList = entity.getTestScriptList();
        entity = this.support.maintain(entity);
        entity.setIdentificationKey(PREFIX + entity.getId());
        entity.setDatatypeState(DatatypeState.MODIFIED);
        entity = this.support.maintain(entity);

        // Update TestScripts
        for (TestScript testScript : testScriptList) {
            try {
                testScript = this.getPersistenceManager().find(TestScript.class, testScript.getId());
                testScript.setFolder(entity);
                testScript.setDatatypeState(DatatypeState.MODIFIED);

                ScriptSupport.getInstance().maintainTestScript(testScript, super.getContext());
            } catch (Exception e) {
                super.getLogger().error(e, "Could not set Folder for TestScript '" + testScript.getId() + "'");
            }
        }
        return entity;
    }

    private Folder update(Folder entity) throws MaintainException {

        // Has to be created before the folder is updated, because an update
        // clears the assignedList.
        List<TestScript> assignedTestScriptList = NabuccoCollectionAccessor.getInstance().getAssignedList(
                entity.getTestScriptList());

        // Create children
        List<Folder> children = entity.getSubFolderList();
        List<Folder> removed = NabuccoCollectionAccessor.getInstance().getUnassignedList(children);

        for (int i = 0; i < children.size(); i++) {
            Folder createdChild = update(children.get(i));
            children.set(i, createdChild);
        }

        // Delete removed folder
        for (Folder folder : removed) {
            // Unassigning folders does not automatically trigger a delete. The
            // Folder could be moved to another parent.
            if (folder.getDatatypeState() == DatatypeState.DELETED) {
                delete(folder);
            }
        }

        // Generate FolderKey
        if (entity.getDatatypeState() == DatatypeState.INITIALIZED) {
            entity = this.support.maintain(entity);
            entity.setIdentificationKey(PREFIX + entity.getId());
            entity.setDatatypeState(DatatypeState.MODIFIED);
        }

        // Create or update Folder
        entity = this.support.maintain(entity);

        // Update assigned TestScripts
        for (TestScript testScript : assignedTestScriptList) {
            try {
                testScript = this.getPersistenceManager().find(TestScript.class, testScript.getId());
                testScript.setFolder(entity);
                testScript = this.support.maintain(testScript);
            } catch (PersistenceException e) {
                throw new MaintainException(e);
            }
        }

        return entity;
    }

    private void delete(Folder entity) throws MaintainException {

        if (entity.getId() == null) {
            throw new IllegalArgumentException("Folder is not persistent.");
        }

        Flag isRoot = entity.getRoot();

        if (isRoot != null && isRoot.getValue() != null && isRoot.getValue().booleanValue()) {
            // Root-Folder cannot be deleted
            return;
        }

        // Delete children
        List<Folder> children = entity.getSubFolderList();

        for (int i = 0; i < children.size(); i++) {
            delete(children.get(i));
        }

        // Move TestScripts to root folder
        List<TestScript> testScriptList = entity.getTestScriptList();

        try {
            Folder rootFolder = FolderSupport.getInstance().getRootFolder(super.getContext());

            for (TestScript testScript : testScriptList) {
                testScript = this.getPersistenceManager().find(TestScript.class, testScript.getId());
                testScript.setFolder(rootFolder);
                testScript = this.support.maintain(testScript);
            }
        } catch (Exception ex) {
            throw new MaintainException(ex);
        }

        // Delete folder
        this.support.maintain(entity);
    }

}
