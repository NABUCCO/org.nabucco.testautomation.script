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
package org.nabucco.testautomation.script.impl.service.search;

import java.util.List;

import javax.persistence.Query;

import org.nabucco.framework.base.facade.component.NabuccoInstance;
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.validation.constraint.element.ConstraintFactory;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
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
public class GetFolderStructureServiceHandlerImpl extends GetFolderStructureServiceHandler{

	private static final long serialVersionUID = 1L;

	@Override
	protected FolderListMsg getFolderStructure(FolderSearchMsg msg)
			throws SearchException {

		String statement = "FROM Folder f WHERE f.root = :root";
		
		if (msg.getOwner() != null && msg.getOwner().getValue() != null) {
			statement += " AND f.owner = :owner";
		}

		Query query = super.getEntityManager().createQuery(statement);
		query.setParameter("root", new Flag(Boolean.TRUE));

		if (msg.getOwner() != null && msg.getOwner().getValue() != null) {
			query.setParameter("owner", msg.getOwner());
		}
		
		@SuppressWarnings("unchecked")
		List<Folder> folderList = query.getResultList();

		// No RootFolder exists in database
		if (folderList.isEmpty()) {
			throw new SearchException("No RootFolder found");
		}
		
		for (Folder folder : folderList) {
			try {
				FolderSupport.getInstance().loadFolder(folder, super.getEntityManager());
			} catch (Exception e) {
				super.getLogger().error(e, "Could not load '"
							+ folder.getId() + "'");
			}
		}
	
		// Detach Entity
		this.getEntityManager().clear();
		
		// Sort
		for (Folder folder : folderList) {
			try {
				folder.accept(new PersistenceCleaner());
			} catch (VisitorException e) {
				throw new SearchException(e);
			}
			FolderSupport.getInstance().sort(folder);
		
			// Check owner and set Editable-Constraint
			if (!folder.getOwner().equals(NabuccoInstance.getInstance().getOwner())) {
				try {
					folder.addConstraint(ConstraintFactory.getInstance()
							.createEditableConstraint(false), true);
				} catch (VisitorException ex) {
					throw new SearchException(ex);
				}
			}
		}
		
		FolderListMsg rs = new FolderListMsg();
		rs.getFolderList().addAll(folderList);
		return rs;
	}
	
}
