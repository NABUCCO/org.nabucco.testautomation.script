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
package org.nabucco.testautomation.script.impl.service.maintain;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionAccessor;
import org.nabucco.framework.base.facade.datatype.visitor.DatatypeVisitor;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceExceptionMapper;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceHelper;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.impl.service.FolderSupport;
import org.nabucco.testautomation.script.impl.service.ScriptSupport;
import org.nabucco.testautomation.script.impl.service.maintain.MaintainFolderServiceHandler;
import org.nabucco.testautomation.script.impl.service.maintain.visitor.FolderModificationVisitor;


/**
 * MaintainFolderServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class MaintainFolderServiceHandlerImpl extends
		MaintainFolderServiceHandler {

	private static final long serialVersionUID = 1L;
	
	private PersistenceHelper persistenceHelper;
	
	@Override
	public FolderMsg maintainFolder(FolderMsg msg) throws MaintainException {

		Folder folder = msg.getFolder();

		try {
			// initialize PersistenceHelper
			this.persistenceHelper = new PersistenceHelper(super.getEntityManager());
			
			if (folder.getDatatypeState() == DatatypeState.PERSISTENT) {
	            DatatypeVisitor visitor = new FolderModificationVisitor(
	            		folder);
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
				folder = null;
				break;
			case TRANSIENT:
				break;
			case PERSISTENT:
				break;
			default:
				throw new MaintainException("Datatype state '"
						+ folder.getDatatypeState()
						+ "' is not valid for Folder.");
			}
		} catch (Exception ex) {
			throw new MaintainException("Error maintaining Folder",
					PersistenceExceptionMapper.resolve(ex,
							Folder.class.getName(), folder.getId()));
		}
		
		this.persistenceHelper.flush();
        this.persistenceHelper = null;

		if (folder != null) {
			try {
				FolderSupport.getInstance().loadFolder(folder, super.getEntityManager());
				
				// Detach Entity
				this.getEntityManager().clear();
				
				// Sort
				FolderSupport.getInstance().sort(folder);
			} catch (Exception e) {
				super.getLogger().error(e, "Could not load '"
							+ folder.getId() + "'");
			}
		}
		
		msg.setFolder(folder);
		return msg;
	}
	

	private Folder create(Folder entity) throws PersistenceException {
		
		// Create children
		List<Folder> children = entity.getSubFolderList();
		
		for (int i = 0; i < children.size(); i++) {
			Folder createdChild = create(children.get(i));
			children.set(i, createdChild);
		}
		
		// Create Folder
		List<TestScript> testScriptList = entity.getTestScriptList();
		entity = this.persistenceHelper.persist(entity);
		
		// Update TestScripts
		for (TestScript testScript : testScriptList) {
			testScript = this.persistenceHelper.find(TestScript.class, testScript);
			testScript.setFolder(entity);
			testScript.setDatatypeState(DatatypeState.MODIFIED);
			
			try {
				ScriptSupport.getInstance().maintainTestScript(testScript, super.getContext());
			} catch (Exception e) {
				super.getLogger().error(
						e,
						"Could not set Folder for TestScript '"
								+ testScript.getId() + "'");
			}
		}
		return entity;
	}

	private Folder update(Folder entity) throws PersistenceException {
		
		// Has to be created before the folder is updated, because an update
		// clears the assignedList.
		List<TestScript> assignedTestScriptList = NabuccoCollectionAccessor
				.getInstance().getAssignedList(entity.getTestScriptList());

		// Create children
		List<Folder> children = entity.getSubFolderList();
		List<Folder> removed = NabuccoCollectionAccessor.getInstance()
				.getUnassignedList(children);

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

		// Create or update Folder
		entity = this.persistenceHelper.persist(entity);
		
		// Update assigned TestScripts
		for (TestScript testScript : assignedTestScriptList) { 
			testScript = this.persistenceHelper.find(TestScript.class, testScript);
			testScript.setFolder(entity);
			testScript = this.persistenceHelper.persist(testScript);
		}
		
		return entity;
	}

	private void delete(Folder entity) throws PersistenceException {

		if (entity.getId() == null) {
			throw new IllegalArgumentException("Folder is not persistent.");
		}
		
		Flag isRoot = entity.getRoot();
		
		if (isRoot != null && isRoot.getValue() != null
				&& isRoot.getValue().booleanValue()) {
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
				testScript = this.persistenceHelper.find(TestScript.class, testScript);
				testScript.setFolder(rootFolder);
				testScript = this.persistenceHelper.persist(testScript);
			}
		} catch(PersistenceException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new PersistenceException(ex);
		}
		
		// Delete folder
		this.persistenceHelper.persist(entity);
	}

}
