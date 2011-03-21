/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.web.communication.maintain;

import org.nabucco.framework.base.facade.datatype.session.NabuccoSession;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeMsg;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainSubEngineCode;

/**
 * MaintainSubEngineCodeDelegate<p/>SubEngineCode maintenance service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-09
 */
public class MaintainSubEngineCodeDelegate extends ServiceDelegateSupport {

    private MaintainSubEngineCode service;

    /**
     * Constructs a new MaintainSubEngineCodeDelegate instance.
     *
     * @param service the MaintainSubEngineCode.
     */
    public MaintainSubEngineCodeDelegate(MaintainSubEngineCode service) {
        super();
        this.service = service;
    }

    /**
     * MaintainSubEngineCode.
     *
     * @param session the NabuccoSession.
     * @param rq the SubEngineCodeMsg.
     * @return the SubEngineCodeMsg.
     * @throws MaintainException
     */
    public SubEngineCodeMsg maintainSubEngineCode(SubEngineCodeMsg rq, NabuccoSession session)
            throws MaintainException {
        ServiceRequest<SubEngineCodeMsg> request = new ServiceRequest<SubEngineCodeMsg>(
                super.createServiceContext(session));
        request.setRequestMessage(rq);
        ServiceResponse<SubEngineCodeMsg> rs;
        if ((service != null)) {
            rs = service.maintainSubEngineCode(request);
        } else {
            throw new MaintainException(
                    "Cannot execute service operation: MaintainSubEngineCode.maintainSubEngineCode");
        }
        return rs.getResponseMessage();
    }
}
