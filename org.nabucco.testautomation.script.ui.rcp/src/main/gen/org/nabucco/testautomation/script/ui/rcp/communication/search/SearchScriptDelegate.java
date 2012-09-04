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
package org.nabucco.testautomation.script.ui.rcp.communication.search;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.testautomation.property.facade.message.QuickSearchMsg;
import org.nabucco.testautomation.property.facade.message.QuickSearchRs;
import org.nabucco.testautomation.script.facade.message.FolderListMsg;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.facade.message.FolderSearchMsg;
import org.nabucco.testautomation.script.facade.message.MetadataListMsg;
import org.nabucco.testautomation.script.facade.message.MetadataSearchMsg;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeListMsg;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeSearchMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptListMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptSearchMsg;
import org.nabucco.testautomation.script.facade.service.search.SearchScript;

/**
 * SearchScriptDelegate<p/>Script search service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-13
 */
public class SearchScriptDelegate extends ServiceDelegateSupport {

    private SearchScript service;

    /**
     * Constructs a new SearchScriptDelegate instance.
     *
     * @param service the SearchScript.
     */
    public SearchScriptDelegate(SearchScript service) {
        super();
        this.service = service;
    }

    /**
     * SearchMetadata.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the MetadataSearchMsg.
     * @return the MetadataListMsg.
     * @throws ClientException
     */
    public MetadataListMsg searchMetadata(MetadataSearchMsg message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<MetadataSearchMsg> request = new ServiceRequest<MetadataSearchMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<MetadataListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.searchMetadata(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(SearchScript.class, "searchMetadata", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: SearchScript.searchMetadata");
    }

    /**
     * SearchSubEngineCode.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the SubEngineCodeSearchMsg.
     * @return the SubEngineCodeListMsg.
     * @throws ClientException
     */
    public SubEngineCodeListMsg searchSubEngineCode(SubEngineCodeSearchMsg message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<SubEngineCodeSearchMsg> request = new ServiceRequest<SubEngineCodeSearchMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<SubEngineCodeListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.searchSubEngineCode(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(SearchScript.class, "searchSubEngineCode", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: SearchScript.searchSubEngineCode");
    }

    /**
     * SearchTestScript.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the TestScriptSearchMsg.
     * @return the TestScriptListMsg.
     * @throws ClientException
     */
    public TestScriptListMsg searchTestScript(TestScriptSearchMsg message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<TestScriptSearchMsg> request = new ServiceRequest<TestScriptSearchMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<TestScriptListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.searchTestScript(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(SearchScript.class, "searchTestScript", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: SearchScript.searchTestScript");
    }

    /**
     * QuickSearch.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the QuickSearchMsg.
     * @return the QuickSearchRs.
     * @throws ClientException
     */
    public QuickSearchRs quickSearch(QuickSearchMsg message, ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<QuickSearchMsg> request = new ServiceRequest<QuickSearchMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<QuickSearchRs> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.quickSearch(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(SearchScript.class, "quickSearch", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: SearchScript.quickSearch");
    }

    /**
     * Getter for the RootFolder.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the FolderSearchMsg.
     * @return the FolderMsg.
     * @throws ClientException
     */
    public FolderMsg getRootFolder(FolderSearchMsg message, ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<FolderSearchMsg> request = new ServiceRequest<FolderSearchMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<FolderMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.getRootFolder(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(SearchScript.class, "getRootFolder", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: SearchScript.getRootFolder");
    }

    /**
     * Getter for the FolderStructure.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the FolderSearchMsg.
     * @return the FolderListMsg.
     * @throws ClientException
     */
    public FolderListMsg getFolderStructure(FolderSearchMsg message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<FolderSearchMsg> request = new ServiceRequest<FolderSearchMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<FolderListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.getFolderStructure(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(SearchScript.class, "getFolderStructure", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: SearchScript.getFolderStructure");
    }
}
