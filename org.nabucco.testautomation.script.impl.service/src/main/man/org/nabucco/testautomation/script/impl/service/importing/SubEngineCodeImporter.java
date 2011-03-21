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
import org.nabucco.framework.base.facade.datatype.Key;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.importing.ImportContext;
import org.nabucco.framework.base.facade.datatype.importing.ImportContextEntry;
import org.nabucco.framework.base.facade.exception.importing.ImportException;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.testautomation.facade.service.importing.BasicImporter;
import org.nabucco.testautomation.script.facade.datatype.code.CodeParameter;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineActionCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineOperationCode;
import org.nabucco.testautomation.script.impl.service.cache.SubEngineCodeCache;


/**
 * SubEngineCodeImporter
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class SubEngineCodeImporter extends BasicImporter {

	/**
	 * 
	 * @param em
	 */
	public SubEngineCodeImporter(EntityManager em, ImportContext importContext) {
		super(em, importContext);
	}
	
	/**
	 * 
	 * @param datatypes
	 * @return
	 * @throws ImportException
	 */
	public List<ImportContextEntry> importAll(List<Datatype> datatypes)
			throws ImportException {

		List<ImportContextEntry> importedElements = new ArrayList<ImportContextEntry>();

		for (Datatype current : datatypes) {
			
			if (current instanceof SubEngineCode) {
				importedElements.addAll(mergeSubEngineCodes((SubEngineCode) current));
			}
		}
		
		// reset SubEngineCodeCache
		SubEngineCodeCache.getInstance().clear();
		
		return importedElements;
	}
	
	/**
	 * 
	 * @param subEngineCode
	 * @return
	 * @throws ImportException
	 */
	private List<ImportContextEntry> mergeSubEngineCodes(SubEngineCode subEngineCode) throws ImportException {
		
		try {
			List<ImportContextEntry> importedEntries = new ArrayList<ImportContextEntry>();
			SubEngineCode existingSubEngineCode = findSubEngineCode(subEngineCode.getCode());
			
			// SubEngineCode already exists
			if (existingSubEngineCode != null)  {
				importedEntries.addAll(mergeSubEngineOperationCodes(subEngineCode.getOperationList(), existingSubEngineCode.getOperationList()));
				super.modify(existingSubEngineCode);
				ImportContextEntry entry = new ImportContextEntry();
				entry.setOldId(subEngineCode.getId());
				entry.setTypeName(subEngineCode.getClass().getName());
				entry.setNewId(existingSubEngineCode.getId());
				importedEntries.add(entry);
			} else {
				subEngineCode = maintain(subEngineCode, importedEntries);
			}
			return importedEntries;
		} catch (PersistenceException ex) {
			throw new ImportException("Error updating existing SubEngineCode", ex);
		}
	}
	
	/**
	 * 
	 * @param importedOperations
	 * @param existingOperations
	 * @return
	 * @throws ImportException
	 */
	private List<ImportContextEntry> mergeSubEngineOperationCodes(
			List<SubEngineOperationCode> importedOperations,
			List<SubEngineOperationCode> existingOperations)
			throws ImportException {

		try {
			List<ImportContextEntry> importedEntries = new ArrayList<ImportContextEntry>();
			
			for (SubEngineOperationCode importedOperation : importedOperations) {
				
				SubEngineOperationCode existingOperation = findSubEngineOperationCode(importedOperation.getCode(), existingOperations);
				
				// SubEngineOperationCode already exists
				if (existingOperation != null)  {
					importedEntries.addAll(mergeSubEngineActionCodes(importedOperation.getActionList(), existingOperation.getActionList()));
					importedEntries.addAll(mergeCodeParameters(importedOperation.getParameterList(), existingOperation.getParameterList()));
					super.modify(existingOperation);
					ImportContextEntry entry = new ImportContextEntry();
					entry.setOldId(importedOperation.getId());
					entry.setTypeName(importedOperation.getClass().getName());
					entry.setNewId(existingOperation.getId());
					importedEntries.add(entry);
				} else {
					importedOperation = maintain(importedOperation, importedEntries);
					existingOperations.add(importedOperation);
				}
			}
			return importedEntries;
		} catch (PersistenceException ex) {
			throw new ImportException("Error updating existing SubEngineOperationCode", ex);
		}
	}
	
	/**
	 * 
	 * @param importedActions
	 * @param existingActions
	 * @return
	 * @throws ImportException
	 */
	private List<ImportContextEntry> mergeSubEngineActionCodes(
			List<SubEngineActionCode> importedActions,
			List<SubEngineActionCode> existingActions)
			throws ImportException {
		
		try {
			List<ImportContextEntry> importedEntries = new ArrayList<ImportContextEntry>();
			
			for (SubEngineActionCode importedAction : importedActions) {
				
				SubEngineActionCode existingAction = findSubEngineActionCode(importedAction.getCode(), existingActions);
				
				// SubEngineAcionCode already exists
				if (existingAction != null)  {
					importedEntries.addAll(mergeCodeParameters(importedAction.getParameterList(), existingAction.getParameterList()));
					super.modify(existingAction);
					ImportContextEntry entry = new ImportContextEntry();
					entry.setOldId(importedAction.getId());
					entry.setTypeName(importedAction.getClass().getName());
					entry.setNewId(existingAction.getId());
					importedEntries.add(entry);
				} else {
					importedAction = maintain(importedAction, importedEntries);
					existingActions.add(importedAction);
				}
			}
			return importedEntries;
		} catch (PersistenceException ex) {
			throw new ImportException("Error updating existing SubEngineActionCode", ex);
		}
	}

	/**
	 * 
	 * @param importedParams
	 * @param existingParams
	 * @return
	 * @throws ImportException
	 */
	private List<ImportContextEntry> mergeCodeParameters(
			List<CodeParameter> importedParams,
			List<CodeParameter> existingParams)
			throws ImportException {
		
		List<ImportContextEntry> importedEntries = new ArrayList<ImportContextEntry>();
		
		for (CodeParameter importedParam : importedParams) {
			
			CodeParameter existingParam = findCodeParameter(importedParam, existingParams);
			
			// CodeParameter not exists
			if (existingParam == null)  {
				importedParam = maintain(importedParam, importedEntries);
				existingParams.add(importedParam);
			}
		}
		return importedEntries;
	}
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	private SubEngineCode findSubEngineCode(Key code) {
		
		Query query = this.getEntityManager().createQuery("FROM SubEngineCode c WHERE c.code = :code");
		query.setParameter("code", code);
		
		@SuppressWarnings("unchecked")
		List<SubEngineCode> resultList = query.getResultList();
		
		if (resultList.isEmpty()) {
			return null;
		} else {
			return resultList.get(0);
		}
	}
	
	/**
	 * 
	 * @param code
	 * @param existingOperations
	 * @return
	 */
	private SubEngineOperationCode findSubEngineOperationCode(Key code, List<SubEngineOperationCode> existingOperations) {
		
		for (SubEngineOperationCode existingOperation : existingOperations) {
			if (existingOperation.getCode().equals(code)) {
				return existingOperation;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param code
	 * @param existingActions
	 * @return
	 */
	private SubEngineActionCode findSubEngineActionCode(Key code, List<SubEngineActionCode> existingActions) {
		
		for (SubEngineActionCode existingAction : existingActions) {
			if (existingAction.getCode().equals(code)) {
				return existingAction;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param importedParam
	 * @param existingParams
	 * @return
	 */
	private CodeParameter findCodeParameter(CodeParameter importedParam,
			List<CodeParameter> existingParams) {
		
		for (CodeParameter existingParam : existingParams) {
			if (existingParam.getName().equals(importedParam.getName())
					&& existingParam.getType()== importedParam.getType()) {
				return existingParam;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param entity
	 * @param importedEntries
	 * @return
	 * @throws ImportException
	 */
	private SubEngineCode maintain(SubEngineCode entity, List<ImportContextEntry> importedEntries) throws ImportException {
		
		try {
			for (int i = 0; i < entity.getOperationList().size(); i++) {
				SubEngineOperationCode operationCode = entity.getOperationList().get(i);
				operationCode = maintain(operationCode, importedEntries);
				entity.getOperationList().set(i, operationCode);
			}
			
			ImportContextEntry entry = new ImportContextEntry();
			entry.setOldId(entity.getId());
			entry.setTypeName(entity.getClass().getName());
			entity = (SubEngineCode) super.create(entity);
			entry.setNewId(entity.getId());
			importedEntries.add(entry);
			return entity;
		} catch (PersistenceException ex) {
			throw new ImportException("Error maintaining SubEngineCode: " + entity.getCode().getValue(), ex);
		}
	}

	/**
	 * 
	 * @param entity
	 * @param importedEntries
	 * @return
	 * @throws ImportException
	 */
	private SubEngineOperationCode maintain(SubEngineOperationCode entity, List<ImportContextEntry> importedEntries) throws ImportException {
		
		try {
			for (int i = 0; i < entity.getActionList().size(); i++) {
				SubEngineActionCode actionCode = entity.getActionList().get(i);
				actionCode = maintain(actionCode, importedEntries);
				entity.getActionList().set(i, actionCode);
			}
			
			for (int i = 0; i < entity.getParameterList().size(); i++) {
				CodeParameter param = entity.getParameterList().get(i);
				param = maintain(param, importedEntries);
				entity.getParameterList().set(i, param);
			}
		
			ImportContextEntry entry = new ImportContextEntry();
			entry.setOldId(entity.getId());
			entry.setTypeName(entity.getClass().getName());
			entity = (SubEngineOperationCode) super.create(entity);
			entry.setNewId(entity.getId());
			importedEntries.add(entry);
			return entity;
		} catch (PersistenceException ex) {
			throw new ImportException("Error maintaining SubEngineOperationCode: " + entity.getCode().getValue(), ex);
		}
	}
	
	/**
	 * 
	 * @param entity
	 * @param importedEntries
	 * @return
	 * @throws ImportException
	 */
	private SubEngineActionCode maintain(SubEngineActionCode entity, List<ImportContextEntry> importedEntries) throws ImportException {
		
		try {
			for (int i = 0; i < entity.getParameterList().size(); i++) {
				CodeParameter param = entity.getParameterList().get(i);
				param = maintain(param, importedEntries);
				entity.getParameterList().set(i, param);
			}
		
			ImportContextEntry entry = new ImportContextEntry();
			entry.setOldId(entity.getId());
			entry.setTypeName(entity.getClass().getName());
			entity = (SubEngineActionCode) super.create(entity);
			entry.setNewId(entity.getId());
			importedEntries.add(entry);
			return entity;
		} catch (PersistenceException ex) {
			throw new ImportException("Error maintaining SubEngineActionCode: " + entity.getCode().getValue(), ex);
		}
	}
	
	/**
	 * 
	 * @param entity
	 * @param importedEntries
	 * @return
	 * @throws ImportException
	 */
	private CodeParameter maintain(CodeParameter entity, List<ImportContextEntry> importedEntries) throws ImportException {
		
		try {
			ImportContextEntry entry = new ImportContextEntry();
			entry.setOldId(entity.getId());
			entry.setTypeName(entity.getClass().getName());
			entity = (CodeParameter) super.create(entity);
			entry.setNewId(entity.getId());
			importedEntries.add(entry);
			return entity;
		} catch (PersistenceException ex) {
			throw new ImportException("Error maintaining CodeParameter: " + entity.getName().getValue(), ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void deleteAll(Owner arg0) throws ImportException {
		// SubEngineCodes are merged and not deleted !
	}
	
}
