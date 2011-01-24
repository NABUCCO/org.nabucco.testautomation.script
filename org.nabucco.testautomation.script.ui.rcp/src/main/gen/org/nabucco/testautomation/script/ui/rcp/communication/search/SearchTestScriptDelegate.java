/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.communication.search;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.framework.plugin.base.logging.NabuccoLogMessage;
import org.nabucco.testautomation.script.facade.message.TestScriptListMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptSearchMsg;
import org.nabucco.testautomation.script.facade.service.search.SearchTestScript;

/**
 * SearchTestScriptDelegate<p/>TestScript search service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-13
 */
public class SearchTestScriptDelegate extends ServiceDelegateSupport {

    private SearchTestScript service;

    /**
     * Constructs a new SearchTestScriptDelegate instance.
     *
     * @param service the SearchTestScript.
     */
    public SearchTestScriptDelegate(SearchTestScript service) {
        super();
        this.service = service;
    }

    /**
     * SearchTestScript.
     *
     * @param rq the TestScriptSearchMsg.
     * @return the TestScriptListMsg.
     * @throws ClientException
     */
    public TestScriptListMsg searchTestScript(TestScriptSearchMsg rq) throws ClientException {
        ServiceRequest<TestScriptSearchMsg> request = new ServiceRequest<TestScriptSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<TestScriptListMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.searchTestScript(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(SearchTestScriptDelegate.class, "Service: ",
                                "SearchTestScript.searchTestScript", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: SearchTestScript.searchTestScript");
    }

    /**
     * Getter for the TestScript.
     *
     * @param rq the TestScriptSearchMsg.
     * @return the TestScriptMsg.
     * @throws ClientException
     */
    public TestScriptMsg getTestScript(TestScriptSearchMsg rq) throws ClientException {
        ServiceRequest<TestScriptSearchMsg> request = new ServiceRequest<TestScriptSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<TestScriptMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.getTestScript(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(SearchTestScriptDelegate.class, "Service: ",
                                "SearchTestScript.getTestScript", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: SearchTestScript.getTestScript");
    }
}
