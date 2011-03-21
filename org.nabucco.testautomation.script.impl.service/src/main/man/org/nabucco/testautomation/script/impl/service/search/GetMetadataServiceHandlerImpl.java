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

import org.nabucco.framework.base.facade.component.NabuccoInstance;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.validation.constraint.element.ConstraintFactory;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceCleaner;
import org.nabucco.testautomation.script.facade.datatype.comparator.MetadataSorter;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.MetadataSearchMsg;
import org.nabucco.testautomation.script.impl.service.DynamicCodeSupport;
import org.nabucco.testautomation.script.impl.service.PropertySupport;
import org.nabucco.testautomation.script.impl.service.SubEngineCodeSupport;
import org.nabucco.testautomation.script.impl.service.cache.SubEngineCodeCache;


/**
 * GetMetadataServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class GetMetadataServiceHandlerImpl extends
		GetMetadataServiceHandler {
	
	private static final long serialVersionUID = 1L;
	
	private static final MetadataSorter metadataSorter = new MetadataSorter();
	
	@Override
	protected MetadataMsg getMetadata(MetadataSearchMsg msg)
			throws SearchException {

		if (msg.getIdentifier() == null || msg.getIdentifier().getValue() == null) {
			throw new SearchException("Mandatory Identifier is null");
		}
		
		Metadata metadata = getMetadata(msg.getIdentifier().getValue());

		if (metadata == null) {
			throw new SearchException("Metadata with id '" + msg.getIdentifier().getValue() + "' not found");
		}
		
		resolveMetadata(metadata);
		
		// Detach Entity
		this.getEntityManager().clear();
		try {
			metadata.accept(new PersistenceCleaner());
		} catch (VisitorException e) {
			throw new SearchException(e);
		}
		
		// Sort
		metadataSorter.sort(metadata);
		
		if (!SubEngineCodeCache.getInstance().isInitialized()) {
			SubEngineCodeSupport.getInstance().initCache(this.getContext());
		}
		SubEngineCodeSupport.getInstance().resolveSubEngineCodeDeep(metadata);

		// Check owner and set Editable-Constraint
		if (!metadata.getOwner().equals(NabuccoInstance.getInstance().getOwner())) {
			try {
				metadata.addConstraint(ConstraintFactory.getInstance()
						.createEditableConstraint(false), true);
			} catch (VisitorException ex) {
				throw new SearchException(ex);
			}
		}
		
		MetadataMsg rs = new MetadataMsg();
		rs.setMetadata(metadata);
		return rs;
	}
	
	private Metadata getMetadata(Long id) {
		String queryString = "select m from Metadata m where m.id = :id";
		Query query = super.getEntityManager().createQuery(
				queryString);
		query.setParameter("id", id);
		Metadata result = (Metadata) query.getSingleResult();
		return result;
	}
	
	private void resolveMetadata(Metadata metadata) throws SearchException {
		
		// Resolve MetadataLabels
		resolveMetadataLabels(metadata);
		
		for (Metadata child : metadata.getChildren()) {
			child.setParent(metadata);
			resolveMetadata(child);
		}
		metadata.setDatatypeState(DatatypeState.PERSISTENT);
	}
	
	private void resolveMetadataLabels(Metadata metadata) throws SearchException {
		
		for (MetadataLabel label : metadata.getLabelList()) {
			label.setDatatypeState(DatatypeState.PERSISTENT);
			
			try {
				PropertySupport.getInstance().resolveProperties(label,
						getContext());
			} catch (Exception e) {
				super.getLogger().error(
						e,
						"Could not resolve PropertyList for MetadataLabel '"
								+ label.getId() + "'");
			}
			try {
				DynamicCodeSupport.getInstance().resolveDynamicCodes(label,
						getContext());
			} catch (Exception e) {
				super.getLogger().error(
						e,
						"Could not resolve DynamicCodes for MetadataLabel '"
								+ label.getId() + "'");
			}
		}
	}
	
}
