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
package org.nabucco.testautomation.script.ui.rcp.communication.resolve;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.MetadataSearchMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptSearchMsg;
import org.nabucco.testautomation.script.facade.service.resolve.ResolveScript;

/**
 * ResolveScriptDelegate<p/>Script resolve service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-13
 */
public class ResolveScriptDelegate extends ServiceDelegateSupport {

    private ResolveScript service;

    /**
     * Constructs a new ResolveScriptDelegate instance.
     *
     * @param service the ResolveScript.
     */
    public ResolveScriptDelegate(ResolveScript service) {
        super();
        this.service = service;
    }

    /**
     * ResolveTestScript.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the TestScriptSearchMsg.
     * @return the TestScriptMsg.
     * @throws ClientException
     */
    public TestScriptMsg resolveTestScript(TestScriptSearchMsg message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<TestScriptSearchMsg> request = new ServiceRequest<TestScriptSearchMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<TestScriptMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveTestScript(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveScript.class, "resolveTestScript", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ResolveScript.resolveTestScript");
    }

    /**
     * ResolveMetadata.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the MetadataSearchMsg.
     * @return the MetadataMsg.
     * @throws ClientException
     */
    public MetadataMsg resolveMetadata(MetadataSearchMsg message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<MetadataSearchMsg> request = new ServiceRequest<MetadataSearchMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<MetadataMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveMetadata(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveScript.class, "resolveMetadata", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ResolveScript.resolveMetadata");
    }

    /**
     * ResolveChildren.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the MetadataMsg.
     * @return the MetadataMsg.
     * @throws ClientException
     */
    public MetadataMsg resolveChildren(MetadataMsg message, ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<MetadataMsg> request = new ServiceRequest<MetadataMsg>(super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<MetadataMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveChildren(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveScript.class, "resolveChildren", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ResolveScript.resolveChildren");
    }

    /**
     * ResolveParents.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the MetadataMsg.
     * @return the MetadataMsg.
     * @throws ClientException
     */
    public MetadataMsg resolveParents(MetadataMsg message, ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<MetadataMsg> request = new ServiceRequest<MetadataMsg>(super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<MetadataMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveParents(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveScript.class, "resolveParents", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ResolveScript.resolveParents");
    }
}
