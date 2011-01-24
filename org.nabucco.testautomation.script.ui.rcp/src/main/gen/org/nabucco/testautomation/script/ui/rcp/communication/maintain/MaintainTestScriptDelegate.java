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
     * @param rq the TestScriptMsg.
     * @return the TestScriptMsg.
     * @throws ClientException
     */
    public TestScriptMsg maintainTestScript(TestScriptMsg rq) throws ClientException {
        ServiceRequest<TestScriptMsg> request = new ServiceRequest<TestScriptMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<TestScriptMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.maintainTestScript(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(MaintainTestScriptDelegate.class, "Service: ",
                                "MaintainTestScript.maintainTestScript", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: MaintainTestScript.maintainTestScript");
    }
}
