/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.communication.search;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.framework.plugin.base.logging.NabuccoLogMessage;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeListMsg;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeSearchMsg;
import org.nabucco.testautomation.script.facade.service.search.SearchSubEngineCode;

/**
 * SearchSubEngineCodeDelegate<p/>SubEngineCode search service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-13
 */
public class SearchSubEngineCodeDelegate extends ServiceDelegateSupport {

    private SearchSubEngineCode service;

    /**
     * Constructs a new SearchSubEngineCodeDelegate instance.
     *
     * @param service the SearchSubEngineCode.
     */
    public SearchSubEngineCodeDelegate(SearchSubEngineCode service) {
        super();
        this.service = service;
    }

    /**
     * SearchSubEngineCode.
     *
     * @param rq the SubEngineCodeSearchMsg.
     * @return the SubEngineCodeListMsg.
     * @throws ClientException
     */
    public SubEngineCodeListMsg searchSubEngineCode(SubEngineCodeSearchMsg rq)
            throws ClientException {
        ServiceRequest<SubEngineCodeSearchMsg> request = new ServiceRequest<SubEngineCodeSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<SubEngineCodeListMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.searchSubEngineCode(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(SearchSubEngineCodeDelegate.class, "Service: ",
                                "SearchSubEngineCode.searchSubEngineCode", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: SearchSubEngineCode.searchSubEngineCode");
    }
}