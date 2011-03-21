/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.web.communication.maintain;

import org.nabucco.framework.base.facade.datatype.session.NabuccoSession;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainTestScript;

/**
 * MaintainTestScriptDelegate<p/>TestScript maintenance service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-09
 */
public class MaintainTestScriptDelegate extends ServiceDelegateSupport {

    private MaintainTestScript service;

    /**
     * Constructs a new MaintainTestScriptDelegate instance.
     *
     * @param service the MaintainTestScript.
     */
    public MaintainTestScriptDelegate(MaintainTestScript service) {
        super();
        this.service = service;
    }

    /**
     * MaintainTestScript.
     *
     * @param session the NabuccoSession.
     * @param rq the TestScriptMsg.
     * @return the TestScriptMsg.
     * @throws MaintainException
     */
    public TestScriptMsg maintainTestScript(TestScriptMsg rq, NabuccoSession session)
            throws MaintainException {
        ServiceRequest<TestScriptMsg> request = new ServiceRequest<TestScriptMsg>(
                super.createServiceContext(session));
        request.setRequestMessage(rq);
        ServiceResponse<TestScriptMsg> rs;
        if ((service != null)) {
            rs = service.maintainTestScript(request);
        } else {
            throw new MaintainException(
                    "Cannot execute service operation: MaintainTestScript.maintainTestScript");
        }
        return rs.getResponseMessage();
    }
}
