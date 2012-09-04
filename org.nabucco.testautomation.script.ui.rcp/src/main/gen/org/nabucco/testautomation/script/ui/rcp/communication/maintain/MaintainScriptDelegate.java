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
package org.nabucco.testautomation.script.ui.rcp.communication.maintain;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainScript;

/**
 * MaintainScriptDelegate<p/>Folder maintenance service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-09
 */
public class MaintainScriptDelegate extends ServiceDelegateSupport {

    private MaintainScript service;

    /**
     * Constructs a new MaintainScriptDelegate instance.
     *
     * @param service the MaintainScript.
     */
    public MaintainScriptDelegate(MaintainScript service) {
        super();
        this.service = service;
    }

    /**
     * MaintainFolder.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the FolderMsg.
     * @return the FolderMsg.
     * @throws ClientException
     */
    public FolderMsg maintainFolder(FolderMsg message, ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<FolderMsg> request = new ServiceRequest<FolderMsg>(super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<FolderMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.maintainFolder(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainScript.class, "maintainFolder", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainScript.maintainFolder");
    }

    /**
     * MaintainMetadata.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the MetadataMsg.
     * @return the MetadataMsg.
     * @throws ClientException
     */
    public MetadataMsg maintainMetadata(MetadataMsg message, ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<MetadataMsg> request = new ServiceRequest<MetadataMsg>(super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<MetadataMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.maintainMetadata(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainScript.class, "maintainMetadata", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainScript.maintainMetadata");
    }

    /**
     * MaintainSubEngineCode.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the SubEngineCodeMsg.
     * @return the SubEngineCodeMsg.
     * @throws ClientException
     */
    public SubEngineCodeMsg maintainSubEngineCode(SubEngineCodeMsg message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<SubEngineCodeMsg> request = new ServiceRequest<SubEngineCodeMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<SubEngineCodeMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.maintainSubEngineCode(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainScript.class, "maintainSubEngineCode", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainScript.maintainSubEngineCode");
    }

    /**
     * MaintainTestScript.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the TestScriptMsg.
     * @return the TestScriptMsg.
     * @throws ClientException
     */
    public TestScriptMsg maintainTestScript(TestScriptMsg message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<TestScriptMsg> request = new ServiceRequest<TestScriptMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<TestScriptMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.maintainTestScript(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainScript.class, "maintainTestScript", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainScript.maintainTestScript");
    }
}
