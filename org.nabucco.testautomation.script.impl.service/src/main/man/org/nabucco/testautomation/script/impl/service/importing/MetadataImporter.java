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
package org.nabucco.testautomation.script.impl.service.importing;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.importing.ImportContainer;
import org.nabucco.framework.base.facade.datatype.importing.ImportContext;
import org.nabucco.framework.base.facade.datatype.importing.ImportContextEntry;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.datatype.serialization.SerializationException;
import org.nabucco.framework.base.facade.exception.importing.ImportException;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.common.dynamiccode.facade.datatype.DynamicCodeCode;
import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.facade.service.importing.BasicImporter;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineOperationCode;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;


/**
 * MetadataImporter
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class MetadataImporter extends BasicImporter {

	private final NabuccoLogger logger = NabuccoLoggingFactory.getInstance()
			.getLogger(MetadataImporter.class);

	/**
	 * 
	 * @param em
	 * @param importContext
	 */
	protected MetadataImporter(EntityManager em, ImportContext importContext) {
		super(em, importContext);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Datatype> deserialize(ImportContainer importContainer) throws SerializationException {
		return super.deserialize(importContainer);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ImportContextEntry> importAll(List<Datatype> datatypes)
			throws ImportException {

		// Import Metadata
		List<ImportContextEntry> importedElements = new ArrayList<ImportContextEntry>();

		for (Datatype current : datatypes) {
			
			if (current instanceof Metadata) {
				importedElements.add(maintain((Metadata) current));
			} else if (current instanceof MetadataLabel) {
				importedElements.add(maintain((MetadataLabel) current));
			}
		}
		
		return importedElements;
	}
	
	/**
	 * 
	 * @param entity
	 * @param importedEntries
	 * @return
	 * @throws ImportException
	 */
	private ImportContextEntry maintain(Metadata entity) throws ImportException {
	
		// Update SubEngineCode 
		SubEngineCode subEngine = entity.getSubEngine();
		
		if (subEngine != null) {
			Query query = getEntityManager().createQuery("FROM SubEngineCode c WHERE c.id = :id");
			query.setParameter("id", this.getNewRefId(subEngine.getId(), subEngine.getClass().getName()));
			subEngine = (SubEngineCode) query.getSingleResult();
			entity.setSubEngine(subEngine);
		}
		
		// Update SubEngineOperationCode
		SubEngineOperationCode operation = entity.getOperation();
		
		if (operation != null) {
			Query query = getEntityManager().createQuery("FROM SubEngineOperationCode c WHERE c.id = :id");
			query.setParameter("id", this.getNewRefId(operation.getId(), operation.getClass().getName()));
			operation = (SubEngineOperationCode) query.getSingleResult();
			entity.setOperation(operation);
		}
		
		Query query = getEntityManager().createNativeQuery("SELECT id FROM scpt_metadata m WHERE m.identification_key = :key AND m.owner = :owner");
		query.setParameter("key", entity.getIdentificationKey());
		query.setParameter("owner", entity.getOwner());
		
		@SuppressWarnings("unchecked")
		List<Long> existing = query.getResultList();
		ImportContextEntry entry = null;
		
		if (existing.size() == 0) {
			entry = maintain((NabuccoDatatype) entity);
			logger.info("Metadata '", entity.getIdentificationKey().getValue(), "' imported");
		} else if (existing.size() == 1) {
			entity.setId((Long) existing.get(0));
			entry = modify(entity);
			logger.info("Metadata '", entity.getIdentificationKey().getValue(), "' merged to existing Metadata");
		} else {
			logger.error("More than one Metadata with key '", entity.getIdentificationKey().getValue(), "' found !");
			return entry;
		}		
		return entry;
	}
	
	/**
	 * Copies the attributes from a source Metadata to a target Metadata.
	 * The children are merged, if a child from source does not exist in target,
	 * it is added.
	 * 
	 * @param source the Metadata to copy from
	 * @param target the Metadata to copy to
	 * @return the ImportEntry
	 * @throws ImportException thrown, if an error occurs during the merge 
	 */
//	private ImportContextEntry merge(Metadata source, Metadata target) throws ImportException {
//		
//		ImportContextEntry result = new ImportContextEntry();
//		result.setOldId(source.getId());
//		result.setTypeName(source.getClass().getName());
//
//		// Copy attributes
//		target.setDescription(source.getDescription());
//		target.setName(source.getName());
//		target.setOperation(source.getOperation());
//		target.setSubEngine(source.getSubEngine());
//		List<Metadata> addChildren = new ArrayList<Metadata>();
//		
//		// Merge children
//		for (Metadata child : source.getChildren()) {
//			for (Metadata existingChild : target.getChildren()) {
//				// Child already exists in target
//				if (child.getIdentificationKey().equals(existingChild.getIdentificationKey())) {
//					continue;
//				}
//				
//				// Add child to target
//				addChildren.add(child);
//			}
//		}
//		target.getChildren().addAll(addChildren);
//		
//		try {
//			target = (Metadata) super.modify(target);
//		} catch (PersistenceException ex) {
//			throw new ImportException("Unable to update "
//					+ target.getClass().getSimpleName(), ex);
//		}
//		
//		source.setId(target.getId());
//		result.setNewId(source.getId());
//		return result;
//	}

	private ImportContextEntry modify(Metadata entity) throws ImportException {
		
		ImportContextEntry result = new ImportContextEntry();
		result.setOldId(entity.getId());
		result.setTypeName(entity.getClass().getName());
		
		try {
			entity = (Metadata) super.modify(entity);
		} catch (PersistenceException ex) {
			throw new ImportException("Unable to update "
					+ entity.getClass().getSimpleName(), ex);
		}
		
		result.setNewId(entity.getId());
		return result;
	}

	/**
	 * 
	 * @param entity
	 * @param importedEntries
	 * @return
	 * @throws ImportException
	 */
	private ImportContextEntry maintain(MetadataLabel entity) throws ImportException {

		// Update PropertyList
		if (entity.getPropertyListRefId() != null) {
			entity.setPropertyListRefId(getNewRefId(
					entity.getPropertyListRefId(), PropertyList.class.getName()));
		}
		
		// Update BrandType
		if (entity.getBrandTypeRefId() != null) {
			entity.setBrandTypeRefId(getNewRefId(entity.getBrandTypeRefId(), DynamicCodeCode.class.getName()));
		}
		
		// Update EnvirnomentType
		if (entity.getEnvironmentTypeRefId() != null) {
			entity.setEnvironmentTypeRefId(getNewRefId(entity.getEnvironmentTypeRefId(), DynamicCodeCode.class.getName()));
		}
		
		// Update ReleaseType
		if (entity.getReleaseTypeRefId() != null) {
			entity.setReleaseTypeRefId(getNewRefId(entity.getReleaseTypeRefId(), DynamicCodeCode.class.getName()));
		}
			
		return maintain((NabuccoDatatype) entity);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll(Owner owner) throws ImportException {
		
		// Find all root-metadata for the importing owner
		Query query = this.getEntityManager().createQuery("FROM Metadata m WHERE metadata_children_id IS NULL AND m.owner = :owner");
		query.setParameter("owner", owner);
		
		@SuppressWarnings("unchecked")
		List<Metadata> metadataList = query.getResultList();
		
		for (Metadata metadata : metadataList) {
			
			if (delete(metadata)) {
				logger.info("Metadata '", metadata.getIdentificationKey().getValue(), "' completely deleted");
			} else {
				logger.info("Metadata '", metadata.getIdentificationKey().getValue(), "' NOT completely deleted");
			}
		}
	}
	
	/**
	 * 
	 * @param metadata
	 * @throws ImportException
	 */
	private boolean delete(Metadata metadata) throws ImportException {
		
		// Delete children
		for (Metadata child : metadata.getChildren()) {
			
			if(!delete(child)) {
				return false;
			}
		}
		
		try {
			// Check if Metadata is still referenced
			if (isMetadataInUse(metadata)) {
				super.modify(metadata);
				return false;
			}
		
			// Delete MetadataLabels
			for (MetadataLabel label : metadata.getLabelList()) {
				super.delete(label);
			}
			
			// Delete Metadata
			super.delete(metadata);
			return true;
		} catch (PersistenceException ex) {
			throw new ImportException(ex);
		}		
	}
	
	private boolean isMetadataInUse(Metadata metadata) {
		
		Query query = getEntityManager().createQuery("FROM Action a WHERE a.metadata = :metadata");
		query.setParameter("metadata", metadata);
		
		@SuppressWarnings("unchecked")
		List<Action> resultList = query.getResultList();
		
		boolean inUse = resultList.size() > 0;
		
		if (inUse) {
			logger.info("Metadata '", metadata.getIdentificationKey().getValue(), "' ["
					+ metadata.getId() + "] is in use of " + resultList.size()
					+ " Actions an is NOT deleted");
		}
		
		return inUse;
	}
	
}
