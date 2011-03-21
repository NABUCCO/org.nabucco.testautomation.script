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
package org.nabucco.testautomation.script.impl.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionAccessor;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.testautomation.script.facade.component.ScriptComponent;
import org.nabucco.testautomation.script.facade.component.ScriptComponentLocator;
import org.nabucco.testautomation.script.facade.datatype.comparator.FolderSorter;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.message.FolderSearchMsg;
import org.nabucco.testautomation.script.facade.service.search.SearchFolder;


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

	public synchronized Folder getRootFolder(ServiceMessageContext ctx, Owner owner) throws SearchException {
		
		if (this.rootFolder == null) {
		
			try {
				ScriptComponent scriptComponent = ScriptComponentLocator.getInstance().getComponent();
				SearchFolder search = scriptComponent.getSearchFolder();
				ServiceRequest<FolderSearchMsg> rq = new ServiceRequest<FolderSearchMsg>(ctx);
				FolderSearchMsg msg = new FolderSearchMsg();
				msg.setOwner(owner);
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

	public Folder loadFolder(Folder folder, EntityManager em) {
		
		// Load TestScripts
		loadTestScripts(folder, em);
		folder.setDatatypeState(DatatypeState.PERSISTENT);

		// Load subFolders
		List<Folder> subFolderList = folder.getSubFolderList();
		
		for (int i = 0; i < subFolderList.size(); i++) {
			subFolderList.set(i, loadFolder(subFolderList.get(i), em));
		}
		
		return folder;
	}

	private void loadTestScripts(Folder folder, EntityManager em) {
		
		Query query = em.createQuery("select t from TestScript t where folder = :folder order by t.name");
		query.setParameter("folder", folder);
		
		@SuppressWarnings("unchecked")
		List<TestScript> scripts = (List<TestScript>) query.getResultList();

		List<TestScript> testScriptList = folder.getTestScriptList();
		testScriptList.clear();
		testScriptList.addAll(scripts);
		NabuccoCollectionAccessor.getInstance().clearAssignments(testScriptList);
	}

}