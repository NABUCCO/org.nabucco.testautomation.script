/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.service.search;

import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.testautomation.script.facade.message.TestScriptListMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptSearchMsg;

/**
 * SearchTestScript<p/>TestScript search service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-13
 */
public interface SearchTestScript extends Service {

    /**
     * Missing description at method searchTestScript.
     *
     * @param rq the ServiceRequest<TestScriptSearchMsg>.
     * @return the ServiceResponse<TestScriptListMsg>.
     * @throws SearchException
     */
    ServiceResponse<TestScriptListMsg> searchTestScript(ServiceRequest<TestScriptSearchMsg> rq)
            throws SearchException;

    /**
     * Missing description at method getTestScript.
     *
     * @param rq the ServiceRequest<TestScriptSearchMsg>.
     * @return the ServiceResponse<TestScriptMsg>.
     * @throws SearchException
     */
    ServiceResponse<TestScriptMsg> getTestScript(ServiceRequest<TestScriptSearchMsg> rq)
            throws SearchException;
}
