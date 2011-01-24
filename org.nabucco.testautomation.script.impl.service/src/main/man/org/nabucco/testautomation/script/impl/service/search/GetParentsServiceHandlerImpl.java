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

import javax.persistence.Query;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.impl.service.SubEngineCodeSupport;
import org.nabucco.testautomation.script.impl.service.cache.SubEngineCodeCache;
import org.nabucco.testautomation.script.impl.service.search.GetParentsServiceHandler;


/**
 * GetChildrenServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class GetParentsServiceHandlerImpl extends
		GetParentsServiceHandler {
	
	private static final long serialVersionUID = 1L;
	
	private Query query = super.getEntityManager().createNativeQuery("SELECT metadata_id FROM metadata WHERE id = :id");
	
	@Override
	protected MetadataMsg getParents(MetadataMsg msg) throws SearchException {

		Metadata metadata = msg.getMetadata();

		if (metadata == null || metadata.getId() == null) {
			throw new SearchException("Invalid Metadata in getChildren()");
		}
		
		metadata = resolve(metadata);
		
		this.getEntityManager().clear();
		
		if (!SubEngineCodeCache.getInstance().isInitialized()) {
			SubEngineCodeSupport.getInstance().initCache(this.getEntityManager());
		}
		SubEngineCodeSupport.getInstance().resolveSubEngineCodeShallow(metadata);
		
		MetadataMsg rs = new MetadataMsg();
		rs.setMetadata(metadata);
		return rs;
	}
	
	private Metadata resolve(Metadata metadata) throws SearchException {
		metadata = find(metadata.getId());
		
		query.setParameter("id", metadata.getId());
		Long parentId = (Long) query.getSingleResult();
		
		if (parentId != null) {
			Metadata parent = find(parentId);
			metadata.setParent(parent);
			resolve(parent);
		}	
		return metadata;
	}
	
	private Metadata find(Long id) {
		Metadata metadata = super.getEntityManager().find(Metadata.class, id);
		metadata.setDatatypeState(DatatypeState.PERSISTENT);
		return metadata;
	}
	
}
