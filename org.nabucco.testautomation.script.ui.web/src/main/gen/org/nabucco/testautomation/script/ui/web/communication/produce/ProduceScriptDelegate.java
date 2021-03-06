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
package org.nabucco.testautomation.script.ui.web.communication.produce;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.datatype.session.NabuccoSession;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.facade.message.MetadataLabelMsg;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.ProduceTestScriptElementMsg;
import org.nabucco.testautomation.script.facade.service.produce.ProduceScript;

/**
 * ProduceScriptDelegate<p/>Folder produce service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-20
 */
public class ProduceScriptDelegate extends ServiceDelegateSupport {

    private ProduceScript service;

    /**
     * Constructs a new ProduceScriptDelegate instance.
     *
     * @param service the ProduceScript.
     */
    public ProduceScriptDelegate(ProduceScript service) {
        super();
        this.service = service;
    }

    /**
     * ProduceFolder.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the EmptyServiceMessage.
     * @return the FolderMsg.
     * @throws ProduceException
     */
    public FolderMsg produceFolder(EmptyServiceMessage message, NabuccoSession session,
            ServiceSubContext... subContexts) throws ProduceException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<FolderMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.produceFolder(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ProduceScript.class, "produceFolder", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ProduceException("Cannot execute service operation: ProduceScript.produceFolder");
    }

    /**
     * ProduceMetadata.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the EmptyServiceMessage.
     * @return the MetadataMsg.
     * @throws ProduceException
     */
    public MetadataMsg produceMetadata(EmptyServiceMessage message, NabuccoSession session,
            ServiceSubContext... subContexts) throws ProduceException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<MetadataMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.produceMetadata(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ProduceScript.class, "produceMetadata", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ProduceException("Cannot execute service operation: ProduceScript.produceMetadata");
    }

    /**
     * ProduceMetadataClone.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the MetadataMsg.
     * @return the MetadataMsg.
     * @throws ProduceException
     */
    public MetadataMsg produceMetadataClone(MetadataMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws ProduceException {
        ServiceRequest<MetadataMsg> request = new ServiceRequest<MetadataMsg>(super.createServiceContext(session,
                subContexts));
        request.setRequestMessage(message);
        ServiceResponse<MetadataMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.produceMetadataClone(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ProduceScript.class, "produceMetadataClone", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ProduceException("Cannot execute service operation: ProduceScript.produceMetadataClone");
    }

    /**
     * ProduceMetadataLabel.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the EmptyServiceMessage.
     * @return the MetadataLabelMsg.
     * @throws ProduceException
     */
    public MetadataLabelMsg produceMetadataLabel(EmptyServiceMessage message, NabuccoSession session,
            ServiceSubContext... subContexts) throws ProduceException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<MetadataLabelMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.produceMetadataLabel(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ProduceScript.class, "produceMetadataLabel", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ProduceException("Cannot execute service operation: ProduceScript.produceMetadataLabel");
    }

    /**
     * ProduceMetadataLabelClone.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the MetadataLabelMsg.
     * @return the MetadataLabelMsg.
     * @throws ProduceException
     */
    public MetadataLabelMsg produceMetadataLabelClone(MetadataLabelMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws ProduceException {
        ServiceRequest<MetadataLabelMsg> request = new ServiceRequest<MetadataLabelMsg>(super.createServiceContext(
                session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<MetadataLabelMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.produceMetadataLabelClone(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ProduceScript.class, "produceMetadataLabelClone", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ProduceException("Cannot execute service operation: ProduceScript.produceMetadataLabelClone");
    }

    /**
     * ProduceTestScriptElement.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the ProduceTestScriptElementMsg.
     * @return the ProduceTestScriptElementMsg.
     * @throws ProduceException
     */
    public ProduceTestScriptElementMsg produceTestScriptElement(ProduceTestScriptElementMsg message,
            NabuccoSession session, ServiceSubContext... subContexts) throws ProduceException {
        ServiceRequest<ProduceTestScriptElementMsg> request = new ServiceRequest<ProduceTestScriptElementMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ProduceTestScriptElementMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.produceTestScriptElement(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ProduceScript.class, "produceTestScriptElement", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ProduceException("Cannot execute service operation: ProduceScript.produceTestScriptElement");
    }

    /**
     * ProduceTestScriptElementClone.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the ProduceTestScriptElementMsg.
     * @return the ProduceTestScriptElementMsg.
     * @throws ProduceException
     */
    public ProduceTestScriptElementMsg produceTestScriptElementClone(ProduceTestScriptElementMsg message,
            NabuccoSession session, ServiceSubContext... subContexts) throws ProduceException {
        ServiceRequest<ProduceTestScriptElementMsg> request = new ServiceRequest<ProduceTestScriptElementMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ProduceTestScriptElementMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.produceTestScriptElementClone(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ProduceScript.class, "produceTestScriptElementClone", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ProduceException("Cannot execute service operation: ProduceScript.produceTestScriptElementClone");
    }
}
