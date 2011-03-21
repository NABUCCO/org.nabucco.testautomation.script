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

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.impl.service.SubEngineCodeSupport;
import org.nabucco.testautomation.script.impl.service.cache.SubEngineCodeCache;


/**
 * GetParentsServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class GetParentsServiceHandlerImpl extends
		GetParentsServiceHandler {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected MetadataMsg getParents(MetadataMsg msg) throws SearchException {

		Metadata metadata = msg.getMetadata();

		if (metadata == null || metadata.getId() == null) {
			throw new SearchException("No Metadata for resolving parents");
		}
		
		resolveParents(metadata);
		
		this.getEntityManager().clear();
		
		if (!SubEngineCodeCache.getInstance().isInitialized()) {
			SubEngineCodeSupport.getInstance().initCache(this.getContext());
		}
		SubEngineCodeSupport.getInstance().resolveSubEngineCodeShallow(metadata);
		
		MetadataMsg rs = new MetadataMsg();
		rs.setMetadata(metadata);
		return rs;
	}
	
	private void resolveParents(Metadata metadata) throws SearchException {
		
		Metadata parent = null;
		
		while ((parent = findParent(metadata)) != null) {
			metadata.setParent(parent);
			metadata = parent;
		}
	}
	
	private Metadata findParent(Metadata metadata) throws SearchException {
		
		Query query = super.getEntityManager().createQuery("FROM Metadata m WHERE :child MEMBER OF m.childrenJPA");
		query.setParameter("child", metadata);
		
		try {
			Metadata parent = (Metadata) query.getSingleResult();
			return parent;
		} catch (NoResultException ex) {
			return null;
		} catch (NonUniqueResultException ex) {
			throw new SearchException("More than one parent found for Metadata [" 
					+ metadata.getId() + "] '" 
					+ metadata.getIdentificationKey() + "'");
		}
	}
	
}
