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
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.importing.ImportContext;
import org.nabucco.framework.base.facade.datatype.importing.ImportContextEntry;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.exception.importing.ImportException;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.facade.service.importing.BasicImporter;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineActionCode;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;

/**
 * TestScriptImporter
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class TestScriptImporter extends BasicImporter {

	private final NabuccoLogger logger = NabuccoLoggingFactory.getInstance()
			.getLogger(TestScriptImporter.class);
	
	private Folder root;
	
	/**
	 * 
	 * @param em
	 * @param importContext
	 */
	protected TestScriptImporter(EntityManager em, ImportContext importContext) {
		super(em, importContext);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Owner getOwner() {
		return super.getOwner();
	}

	/**
	 * 
	 * @param datatypes
	 * @return
	 * @throws ImportException
	 */
	public List<ImportContextEntry> importAll(List<Datatype> datatypes)
			throws ImportException {

		// Import TestScripts
		List<ImportContextEntry> importedElements = new ArrayList<ImportContextEntry>();
		List<TestScriptElementContainer> containerList = new ArrayList<TestScriptElementContainer>();
		
		for (Datatype current : datatypes) {
			
			if (current instanceof TestScript) {
				importedElements.add(maintain((TestScript) current));
			} else if (current instanceof Action) {
				importedElements.add(maintain((Action) current));
			} else if (current instanceof TestScriptElement) {
				importedElements.add(maintain((TestScriptElement) current));
			} else if (current instanceof Folder) {
				importedElements.add(maintain((Folder) current));
			} else if (current instanceof TestScriptElementContainer) {
				containerList.add((TestScriptElementContainer) current);
			}
		}
		
		for (TestScriptElementContainer current : containerList) {
			importedElements.add(maintain((NabuccoDatatype) current));
		}
		
		return importedElements;
	}
	
	private ImportContextEntry maintain(TestScript entity)
			throws ImportException {
		
		// Update PropertyList of TestScript
		if (entity.getPropertyListRefId() != null) {
			entity.setPropertyListRefId(this.getNewRefId(entity.getPropertyListRefId(), PropertyList.class.getName()));
		}
		
		if (entity.getFolder() == null) {
			entity.setFolder(root);
		}
		
		ImportContextEntry entry = super.maintain((NabuccoDatatype) entity);
		logger.info("TestScript '", entity.getIdentificationKey().getValue(), "' imported");
		return entry;
	}

	private ImportContextEntry maintain(Folder entity)
			throws ImportException {
		
		if (entity.getRoot() != null && entity.getRoot().getValue() != null
				&& entity.getRoot().getValue().booleanValue()) {
			this.root = entity;
		}
		
		ImportContextEntry entry = super.maintain((NabuccoDatatype) entity);
		logger.info("Folder '", entity.getIdentificationKey().getValue(), "' imported");
		return entry;
	}
	
	private ImportContextEntry maintain(Action entity)
			throws ImportException {
		
		// Update SubEngineActionCode and PropertyList of Action
		SubEngineActionCode actionCode = entity.getActionCode();
		
		if (actionCode != null) {
			Long newId = this.getNewRefId(actionCode.getId(), actionCode.getClass().getName());
			actionCode = getEntityManager().find(SubEngineActionCode.class,	newId);
			entity.setActionCode(actionCode);
		}
		
		// Update PropertyList
		if (entity.getPropertyListRefId() != null) {
			entity.setPropertyListRefId(this.getNewRefId(entity.getPropertyListRefId(), PropertyList.class.getName()));
		}
		
		return super.maintain((NabuccoDatatype) entity);
	}
	
	/**
	 * 
	 * @param owner
	 * @throws ImportException
	 */
	@SuppressWarnings("unchecked")
	public void deleteAll(Owner owner) throws ImportException {
		
		// Find all TestScripts for the importing owner
		Query query = this.getEntityManager().createQuery("FROM TestScript s WHERE s.owner = :owner");
		query.setParameter("owner", owner);
		
		List<TestScript> testScriptList = query.getResultList();
		
		for (TestScript testScript : testScriptList) {
			delete(testScript);
			logger.info("TestScript '", testScript.getIdentificationKey().getValue(), "' deleted");
		}

		query = this.getEntityManager().createQuery("FROM TestScriptElementContainer c WHERE c.element.owner = :owner");
		query.setParameter("owner", owner);
		
		List<TestScriptElementContainer> containerList = query.getResultList();
		
		for (TestScriptElementContainer container : containerList) {
			delete(container);
			logger.info("Remaining TestScriptElementContainer [" + container.getId() + "] deleted");
		}

		List<TestScriptElement> elementList = new ArrayList<TestScriptElement>();
		query = this.getEntityManager().createQuery("FROM TestScriptElement e WHERE e.owner = :owner");
		query.setParameter("owner", owner);
		elementList.addAll(query.getResultList());
		
		for (TestScriptElement element : elementList) {
			delete(element);
			logger.info("Remaining TestScriptElement [" + element.getId() + "] deleted");
		}
		
		// Find all root folder for the importing owner
		query = this.getEntityManager().createQuery("FROM Folder f WHERE f.root = :root AND f.owner = :owner");
		query.setParameter("root", new Flag(Boolean.TRUE));
		query.setParameter("owner", owner);
		
		List<Folder> folderList = query.getResultList();
		
		for (Folder folder : folderList) {
			delete(folder);
			logger.info("Folder '", folder.getIdentificationKey().getValue(), "' deleted");
		}
	}
	
	/**
	 * 
	 * @param entity
	 * @throws ImportException
	 */
	private void delete(Folder entity) throws ImportException {
		
		// Delete children first
		for (Folder subfolder : entity.getSubFolderList()) {
			delete(subfolder);
		}
		
		try {
			// Delete folder
			super.delete(entity);
		} catch (PersistenceException ex) {
			throw new ImportException(ex);
		}
	}
	
	/**
	 * 
	 * @param entity
	 * @throws ImportException
	 */
	private void delete(TestScript entity) throws ImportException {
		
		// Delete children
		for (TestScriptElementContainer child : entity.getTestScriptElementList()) {
			delete(child);
		}
		
		// Delete TestScript
		try {
			super.delete(entity);
		} catch (PersistenceException ex) {
			throw new ImportException(ex);
		}
	}
	
	/**
	 * 
	 * @param entity
	 * @throws ImportException
	 */
	private void delete(TestScriptElementContainer entity) throws ImportException {
			
		TestScriptElement element = entity.getElement();
			
		try {
			super.delete(entity);
			delete(element);
		} catch (PersistenceException ex) {
			throw new ImportException(ex);
		}
	}
	
	/**
	 * 
	 * @param entity
	 * @throws ImportException
	 */
	private void delete(TestScriptElement entity) throws ImportException {
		
		try {
			if (entity instanceof TestScriptComposite) {
				
				for (TestScriptElementContainer child : ((TestScriptComposite) entity).getTestScriptElementList()) {
					delete(child);
				}
			}
			
			super.delete(entity);
		} catch (PersistenceException ex) {
			throw new ImportException(ex);
		}
	}
	
}
