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
package org.nabucco.testautomation.script.impl.service.maintain;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceExceptionMapper;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceHelper;
import org.nabucco.testautomation.script.facade.datatype.code.CodeParameter;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineActionCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineOperationCode;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeMsg;
import org.nabucco.testautomation.script.impl.service.maintain.MaintainSubEngineCodeServiceHandler;


/**
 * MaintainSubEngineCodeServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class MaintainSubEngineCodeServiceHandlerImpl extends
		MaintainSubEngineCodeServiceHandler {

	private static final long serialVersionUID = 1L;
	
	private PersistenceHelper persistenceHelper;
	
	@Override
	public SubEngineCodeMsg maintainSubEngineCode(SubEngineCodeMsg msg) throws MaintainException {

		SubEngineCode subEngineCode = msg.getSubEngineCode();

		try {
			// initialize PersistenceHelper
			this.persistenceHelper = new PersistenceHelper(super.getEntityManager());
			
			switch (subEngineCode.getDatatypeState()) {

			case CONSTRUCTED:
				throw new MaintainException("SubEngineCode is not initialized.");
			case INITIALIZED:
				subEngineCode = this.create(subEngineCode);
				break;
			case MODIFIED:
				//subEngineCode = this.update(subEngineCode);
				break;
			case DELETED:
				//this.delete(subEngineCode);
				//subEngineCode = null;
				break;
			case TRANSIENT:
				break;
			case PERSISTENT:
				break;
			default:
				throw new MaintainException("Datatype state '"
						+ subEngineCode.getDatatypeState()
						+ "' is not valid for Folder.");
			}
		} catch (Exception ex) {
			throw new MaintainException("Error maintaining SubEngineCode",
					PersistenceExceptionMapper.resolve(ex,
							Folder.class.getName(), subEngineCode.getId()));
		}
		
		this.persistenceHelper.flush();
        this.persistenceHelper = null;

		load(subEngineCode);
		msg.setSubEngineCode(subEngineCode);
		return msg;
	}
	
	protected void load(SubEngineCode subEngineCode) {
		subEngineCode.setDatatypeState(DatatypeState.PERSISTENT);
		
		for (SubEngineOperationCode operation : subEngineCode.getOperationList()) {
			operation.setDatatypeState(DatatypeState.PERSISTENT);
		}
	}
	
	protected SubEngineCode create(SubEngineCode entity) throws PersistenceException {
		
		// Create operations
		List<SubEngineOperationCode> operations = entity.getOperationList();
		
		for (int i = 0; i < operations.size(); i++) {
			SubEngineOperationCode operation = create(operations.get(i));
			operations.set(i, operation);
		}
		
		// Create SubEngineCode
		return this.persistenceHelper.persist(entity);
	}
	
	protected SubEngineOperationCode create(SubEngineOperationCode entity) throws PersistenceException {
		
		// Create actions
		List<SubEngineActionCode> actions = entity.getActionList();
		
		for (int i = 0; i < actions.size(); i++) {
			SubEngineActionCode action = create(actions.get(i));
			actions.set(i, action);
		}
		
		List<CodeParameter> params = entity.getParameterList();
		
		for (int i = 0; i < params.size(); i++) {
			CodeParameter param = create(params.get(i));
			params.set(i, param);
		}
		
		// Create SubEngineOperationCode
		return this.persistenceHelper.persist(entity);
	}

	protected SubEngineActionCode create(SubEngineActionCode entity) throws PersistenceException {
	
		List<CodeParameter> params = entity.getParameterList();
		
		for (int i = 0; i < params.size(); i++) {
			CodeParameter param = create(params.get(i));
			params.set(i, param);
		}
		
		// Create SubEngineActionCode
		return this.persistenceHelper.persist(entity);
	}
	
	protected CodeParameter create(CodeParameter entity) throws PersistenceException {
		
		// Create CodeParameter
		return this.persistenceHelper.persist(entity);
	}

}
