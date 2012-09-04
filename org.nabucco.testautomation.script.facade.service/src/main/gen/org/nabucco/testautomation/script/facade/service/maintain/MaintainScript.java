/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.nabucco.testautomation.script.facade.service.maintain;

import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;

/**
 * MaintainScript<p/>Folder maintenance service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-09
 */
public interface MaintainScript extends Service {

    /**
     * Missing description at method maintainFolder.
     *
     * @param rq the ServiceRequest<FolderMsg>.
     * @return the ServiceResponse<FolderMsg>.
     * @throws MaintainException
     */
    ServiceResponse<FolderMsg> maintainFolder(ServiceRequest<FolderMsg> rq) throws MaintainException;

    /**
     * Missing description at method maintainMetadata.
     *
     * @param rq the ServiceRequest<MetadataMsg>.
     * @return the ServiceResponse<MetadataMsg>.
     * @throws MaintainException
     */
    ServiceResponse<MetadataMsg> maintainMetadata(ServiceRequest<MetadataMsg> rq) throws MaintainException;

    /**
     * Missing description at method maintainSubEngineCode.
     *
     * @param rq the ServiceRequest<SubEngineCodeMsg>.
     * @return the ServiceResponse<SubEngineCodeMsg>.
     * @throws MaintainException
     */
    ServiceResponse<SubEngineCodeMsg> maintainSubEngineCode(ServiceRequest<SubEngineCodeMsg> rq)
            throws MaintainException;

    /**
     * Missing description at method maintainTestScript.
     *
     * @param rq the ServiceRequest<TestScriptMsg>.
     * @return the ServiceResponse<TestScriptMsg>.
     * @throws MaintainException
     */
    ServiceResponse<TestScriptMsg> maintainTestScript(ServiceRequest<TestScriptMsg> rq) throws MaintainException;
}
