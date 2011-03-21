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

import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceCleaner;
import org.nabucco.testautomation.script.facade.datatype.comparator.MetadataSorter;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.impl.service.SubEngineCodeSupport;
import org.nabucco.testautomation.script.impl.service.cache.SubEngineCodeCache;


/**
 * GetChildrenServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class GetChildrenServiceHandlerImpl extends
		GetChildrenServiceHandler {
	
	private static final long serialVersionUID = 1L;
	
	private static final MetadataSorter metadataSorter = new MetadataSorter();
	
	@Override
	protected MetadataMsg getChildren(MetadataMsg msg) throws SearchException {

		Metadata metadata = msg.getMetadata();

		if (metadata == null || metadata.getId() == null) {
			throw new SearchException("Invalid Metadata in getChildren()");
		}
		
		Metadata parent = metadata.getParent();
		metadata = resolveChildren(metadata);
		metadata.setParent(parent);

		this.getEntityManager().clear();
		try {
			metadata.accept(new PersistenceCleaner());
		} catch (VisitorException e) {
			throw new SearchException(e);
		}
		
		metadataSorter.sort(metadata.getChildren());
		
		if (!SubEngineCodeCache.getInstance().isInitialized()) {
			SubEngineCodeSupport.getInstance().initCache(this.getContext());
		}
		
		SubEngineCodeSupport.getInstance().resolveSubEngineCodeShallow(metadata);
		
		for (Metadata child : metadata.getChildren()) {
			SubEngineCodeSupport.getInstance().resolveSubEngineCodeShallow(child);
		}
		
		MetadataMsg rs = new MetadataMsg();
		rs.setMetadata(metadata);
		return rs;
	}
	
	private Metadata resolveChildren(Metadata metadata) throws SearchException {
		metadata = super.getEntityManager().find(Metadata.class, metadata.getId());
		List<Metadata> children = metadata.getChildren();

		for (Metadata child : children) {
			child.setParent(metadata);
		}
		return metadata;
	}
	
}
