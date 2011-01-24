/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.service.maintain;

import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;

/**
 * MaintainTestScript<p/>TestScript maintenance service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-09
 */
public interface MaintainTestScript extends Service {

    /**
     * Missing description at method maintainTestScript.
     *
     * @param rq the ServiceRequest<TestScriptMsg>.
     * @return the ServiceResponse<TestScriptMsg>.
     * @throws MaintainException
     */
    ServiceResponse<TestScriptMsg> maintainTestScript(ServiceRequest<TestScriptMsg> rq)
            throws MaintainException;
}
