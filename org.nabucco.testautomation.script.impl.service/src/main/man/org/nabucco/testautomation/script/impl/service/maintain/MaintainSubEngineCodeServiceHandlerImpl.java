/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.testautomation.script.impl.service.maintain;

import java.util.List;

import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceExceptionMapper;
import org.nabucco.testautomation.script.facade.datatype.code.CodeParameter;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineActionCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineOperationCode;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeMsg;
import org.nabucco.testautomation.script.impl.service.maintain.support.ScriptMaintainSupport;

/**
 * MaintainSubEngineCodeServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class MaintainSubEngineCodeServiceHandlerImpl extends MaintainSubEngineCodeServiceHandler {

    private static final long serialVersionUID = 1L;

    private ScriptMaintainSupport support;

    @Override
    public SubEngineCodeMsg maintainSubEngineCode(SubEngineCodeMsg msg) throws MaintainException {

        SubEngineCode subEngineCode = msg.getSubEngineCode();

        try {
            // initialize PersistenceHelper
            this.support = new ScriptMaintainSupport(super.getPersistenceManager());

            switch (subEngineCode.getDatatypeState()) {

            case CONSTRUCTED:
                throw new MaintainException("SubEngineCode is not initialized.");
            case INITIALIZED:
                subEngineCode = this.create(subEngineCode);
                break;
            case MODIFIED:
                // subEngineCode = this.update(subEngineCode);
                break;
            case DELETED:
                // this.delete(subEngineCode);
                // subEngineCode = null;
                break;
            case TRANSIENT:
                break;
            case PERSISTENT:
                break;
            default:
                throw new MaintainException("Datatype state '"
                        + subEngineCode.getDatatypeState() + "' is not valid for Folder.");
            }

            this.getPersistenceManager().flush();
            this.support = null;

        } catch (Exception ex) {
            throw new MaintainException("Error maintaining SubEngineCode", PersistenceExceptionMapper.resolve(ex,
                    Folder.class.getName(), subEngineCode.getId()));
        }

        // resolve
        resolve(subEngineCode);

        getLogger().info(
                "SubEngineCode '"
                        + subEngineCode.getName() + "' [" + subEngineCode.getId() + "] successfully maintained");
        msg.setSubEngineCode(subEngineCode);
        return msg;
    }

    protected void resolve(SubEngineCode subEngineCode) {
        subEngineCode.getOperationList().size();
    }

    protected SubEngineCode create(SubEngineCode entity) throws MaintainException {

        // Create operations
        List<SubEngineOperationCode> operations = entity.getOperationList();

        for (int i = 0; i < operations.size(); i++) {
            SubEngineOperationCode operation = create(operations.get(i));
            operations.set(i, operation);
        }

        // Create SubEngineCode
        return this.support.maintain(entity);
    }

    protected SubEngineOperationCode create(SubEngineOperationCode entity) throws MaintainException {

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
        return this.support.maintain(entity);
    }

    protected SubEngineActionCode create(SubEngineActionCode entity) throws MaintainException {

        List<CodeParameter> params = entity.getParameterList();

        for (int i = 0; i < params.size(); i++) {
            CodeParameter param = create(params.get(i));
            params.set(i, param);
        }

        // Create SubEngineActionCode
        return this.support.maintain(entity);
    }

    protected CodeParameter create(CodeParameter entity) throws MaintainException {

        // Create CodeParameter
        return this.support.maintain(entity);
    }

}
