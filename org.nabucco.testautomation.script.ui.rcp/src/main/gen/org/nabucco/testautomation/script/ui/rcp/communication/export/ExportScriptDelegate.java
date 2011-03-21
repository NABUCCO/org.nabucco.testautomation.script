/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.communication.export;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.message.exporting.ExportRs;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.framework.plugin.base.logging.NabuccoLogMessage;
import org.nabucco.testautomation.script.facade.service.export.ExportScript;

/**
 * ExportScriptDelegate<p/>Export Service for Script<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2011-02-04
 */
public class ExportScriptDelegate extends ServiceDelegateSupport {

    private ExportScript service;

    /**
     * Constructs a new ExportScriptDelegate instance.
     *
     * @param service the ExportScript.
     */
    public ExportScriptDelegate(ExportScript service) {
        super();
        this.service = service;
    }

    /**
     * Export.
     *
     * @param rq the EmptyServiceMessage.
     * @return the ExportRs.
     * @throws ClientException
     */
    public ExportRs export(EmptyServiceMessage rq) throws ClientException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<ExportRs> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.export(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ExportScriptDelegate.class, "Service: ",
                                "ExportScript.export", " Time: ", String.valueOf((end - start)),
                                "ms."));
            }
        }
        throw new ClientException("Cannot execute service operation: ExportScript.export");
    }
}
