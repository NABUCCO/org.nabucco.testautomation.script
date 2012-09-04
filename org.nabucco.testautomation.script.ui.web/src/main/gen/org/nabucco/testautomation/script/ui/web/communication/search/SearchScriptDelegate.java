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
package org.nabucco.testautomation.script.ui.web.communication.search;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.datatype.session.NabuccoSession;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
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
     * @param session the NabuccoSession.
     * @param message the MetadataSearchMsg.
     * @return the MetadataListMsg.
     * @throws SearchException
     */
    public MetadataListMsg searchMetadata(MetadataSearchMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws SearchException {
        ServiceRequest<MetadataSearchMsg> request = new ServiceRequest<MetadataSearchMsg>(super.createServiceContext(
                session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<MetadataListMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new SearchException("Cannot execute service operation: SearchScript.searchMetadata");
    }

    /**
     * SearchSubEngineCode.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the SubEngineCodeSearchMsg.
     * @return the SubEngineCodeListMsg.
     * @throws SearchException
     */
    public SubEngineCodeListMsg searchSubEngineCode(SubEngineCodeSearchMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws SearchException {
        ServiceRequest<SubEngineCodeSearchMsg> request = new ServiceRequest<SubEngineCodeSearchMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<SubEngineCodeListMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new SearchException("Cannot execute service operation: SearchScript.searchSubEngineCode");
    }

    /**
     * SearchTestScript.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the TestScriptSearchMsg.
     * @return the TestScriptListMsg.
     * @throws SearchException
     */
    public TestScriptListMsg searchTestScript(TestScriptSearchMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws SearchException {
        ServiceRequest<TestScriptSearchMsg> request = new ServiceRequest<TestScriptSearchMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<TestScriptListMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new SearchException("Cannot execute service operation: SearchScript.searchTestScript");
    }

    /**
     * QuickSearch.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the QuickSearchMsg.
     * @return the QuickSearchRs.
     * @throws SearchException
     */
    public QuickSearchRs quickSearch(QuickSearchMsg message, NabuccoSession session, ServiceSubContext... subContexts)
            throws SearchException {
        ServiceRequest<QuickSearchMsg> request = new ServiceRequest<QuickSearchMsg>(super.createServiceContext(session,
                subContexts));
        request.setRequestMessage(message);
        ServiceResponse<QuickSearchRs> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new SearchException("Cannot execute service operation: SearchScript.quickSearch");
    }

    /**
     * Getter for the RootFolder.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the FolderSearchMsg.
     * @return the FolderMsg.
     * @throws SearchException
     */
    public FolderMsg getRootFolder(FolderSearchMsg message, NabuccoSession session, ServiceSubContext... subContexts)
            throws SearchException {
        ServiceRequest<FolderSearchMsg> request = new ServiceRequest<FolderSearchMsg>(super.createServiceContext(
                session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<FolderMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new SearchException("Cannot execute service operation: SearchScript.getRootFolder");
    }

    /**
     * Getter for the FolderStructure.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the FolderSearchMsg.
     * @return the FolderListMsg.
     * @throws SearchException
     */
    public FolderListMsg getFolderStructure(FolderSearchMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws SearchException {
        ServiceRequest<FolderSearchMsg> request = new ServiceRequest<FolderSearchMsg>(super.createServiceContext(
                session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<FolderListMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new SearchException("Cannot execute service operation: SearchScript.getFolderStructure");
    }
}
