/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.web.communication.search;

import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
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
     * @throws SearchException
     */
    public TestScriptListMsg searchTestScript(TestScriptSearchMsg rq) throws SearchException {
        ServiceRequest<TestScriptSearchMsg> request = new ServiceRequest<TestScriptSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<TestScriptListMsg> rs;
        if ((service != null)) {
            rs = service.searchTestScript(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchTestScript.searchTestScript");
        }
        return rs.getResponseMessage();
    }

    /**
     * SearchTestScript.
     *
     * @param subject the Subject.
     * @param rq the TestScriptSearchMsg.
     * @return the TestScriptListMsg.
     * @throws SearchException
     */
    public TestScriptListMsg searchTestScript(TestScriptSearchMsg rq, Subject subject)
            throws SearchException {
        ServiceRequest<TestScriptSearchMsg> request = new ServiceRequest<TestScriptSearchMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<TestScriptListMsg> rs;
        if ((service != null)) {
            rs = service.searchTestScript(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchTestScript.searchTestScript");
        }
        return rs.getResponseMessage();
    }

    /**
     * Getter for the TestScript.
     *
     * @param rq the TestScriptSearchMsg.
     * @return the TestScriptMsg.
     * @throws SearchException
     */
    public TestScriptMsg getTestScript(TestScriptSearchMsg rq) throws SearchException {
        ServiceRequest<TestScriptSearchMsg> request = new ServiceRequest<TestScriptSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<TestScriptMsg> rs;
        if ((service != null)) {
            rs = service.getTestScript(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchTestScript.getTestScript");
        }
        return rs.getResponseMessage();
    }

    /**
     * Getter for the TestScript.
     *
     * @param subject the Subject.
     * @param rq the TestScriptSearchMsg.
     * @return the TestScriptMsg.
     * @throws SearchException
     */
    public TestScriptMsg getTestScript(TestScriptSearchMsg rq, Subject subject)
            throws SearchException {
        ServiceRequest<TestScriptSearchMsg> request = new ServiceRequest<TestScriptSearchMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<TestScriptMsg> rs;
        if ((service != null)) {
            rs = service.getTestScript(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchTestScript.getTestScript");
        }
        return rs.getResponseMessage();
    }
}
