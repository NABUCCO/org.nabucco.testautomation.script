/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.communication.importing;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.message.importing.ImportRq;
import org.nabucco.framework.base.facade.message.importing.ImportRs;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.framework.plugin.base.logging.NabuccoLogMessage;
import org.nabucco.testautomation.script.facade.service.importing.ImportScript;

/**
 * ImportScriptDelegate<p/>Service to import Script<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2011-02-14
 */
public class ImportScriptDelegate extends ServiceDelegateSupport {

    private ImportScript service;

    /**
     * Constructs a new ImportScriptDelegate instance.
     *
     * @param service the ImportScript.
     */
    public ImportScriptDelegate(ImportScript service) {
        super();
        this.service = service;
    }

    /**
     * ImportScript.
     *
     * @param rq the ImportRq.
     * @return the ImportRs.
     * @throws ClientException
     */
    public ImportRs importScript(ImportRq rq) throws ClientException {
        ServiceRequest<ImportRq> request = new ServiceRequest<ImportRq>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<ImportRs> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.importScript(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ImportScriptDelegate.class, "Service: ",
                                "ImportScript.importScript", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException("Cannot execute service operation: ImportScript.importScript");
    }
}
