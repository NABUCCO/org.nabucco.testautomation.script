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

import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.impl.service.search.GetRootFolderServiceHandler;

/**
 * GetRootFolderServiceHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class GetRootFolderServiceHandlerImpl extends GetRootFolderServiceHandler {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected FolderMsg getRootFolder(EmptyServiceMessage msg)
			throws SearchException {
		
        Query query = super.getEntityManager().createQuery("select f from Folder f where f.root = :root");
		query.setParameter("root", new Flag(Boolean.TRUE));
        
		@SuppressWarnings("unchecked")
		List<Folder> folderList = query.getResultList();

		// No RootFolder exists in database
		if (folderList.isEmpty()) {
			throw new SearchException("No RootFolder found");
		}
		
		// More than one RootFolder exists in database
		if (folderList.size() > 1) {
			this.getLogger().warning("More than one RootFolder found in database !");
		}
		
		FolderMsg rs = new FolderMsg();
		rs.setFolder(folderList.get(0));
		return rs;
	}
	

}
