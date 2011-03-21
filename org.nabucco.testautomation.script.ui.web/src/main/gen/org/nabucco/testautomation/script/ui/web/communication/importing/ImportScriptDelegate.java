/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.web.communication.importing;

import org.nabucco.framework.base.facade.datatype.session.NabuccoSession;
import org.nabucco.framework.base.facade.exception.importing.ImportException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.message.importing.ImportRq;
import org.nabucco.framework.base.facade.message.importing.ImportRs;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
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
     * @param session the NabuccoSession.
     * @param rq the ImportRq.
     * @return the ImportRs.
     * @throws ImportException
     */
    public ImportRs importScript(ImportRq rq, NabuccoSession session) throws ImportException {
        ServiceRequest<ImportRq> request = new ServiceRequest<ImportRq>(
                super.createServiceContext(session));
        request.setRequestMessage(rq);
        ServiceResponse<ImportRs> rs;
        if ((service != null)) {
            rs = service.importScript(request);
        } else {
            throw new ImportException("Cannot execute service operation: ImportScript.importScript");
        }
        return rs.getResponseMessage();
    }
}
