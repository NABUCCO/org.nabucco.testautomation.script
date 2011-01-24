/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.communication.maintain;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.framework.plugin.base.logging.NabuccoLogMessage;
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
     * @param rq the SubEngineCodeMsg.
     * @return the SubEngineCodeMsg.
     * @throws ClientException
     */
    public SubEngineCodeMsg maintainSubEngineCode(SubEngineCodeMsg rq) throws ClientException {
        ServiceRequest<SubEngineCodeMsg> request = new ServiceRequest<SubEngineCodeMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<SubEngineCodeMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.maintainSubEngineCode(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(MaintainSubEngineCodeDelegate.class, "Service: ",
                                "MaintainSubEngineCode.maintainSubEngineCode", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: MaintainSubEngineCode.maintainSubEngineCode");
    }
}
