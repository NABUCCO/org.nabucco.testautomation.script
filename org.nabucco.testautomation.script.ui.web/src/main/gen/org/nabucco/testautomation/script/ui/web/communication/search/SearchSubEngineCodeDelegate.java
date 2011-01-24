/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.web.communication.search;

import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
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
     * @throws SearchException
     */
    public SubEngineCodeListMsg searchSubEngineCode(SubEngineCodeSearchMsg rq)
            throws SearchException {
        ServiceRequest<SubEngineCodeSearchMsg> request = new ServiceRequest<SubEngineCodeSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<SubEngineCodeListMsg> rs;
        if ((service != null)) {
            rs = service.searchSubEngineCode(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchSubEngineCode.searchSubEngineCode");
        }
        return rs.getResponseMessage();
    }

    /**
     * SearchSubEngineCode.
     *
     * @param subject the Subject.
     * @param rq the SubEngineCodeSearchMsg.
     * @return the SubEngineCodeListMsg.
     * @throws SearchException
     */
    public SubEngineCodeListMsg searchSubEngineCode(SubEngineCodeSearchMsg rq, Subject subject)
            throws SearchException {
        ServiceRequest<SubEngineCodeSearchMsg> request = new ServiceRequest<SubEngineCodeSearchMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<SubEngineCodeListMsg> rs;
        if ((service != null)) {
            rs = service.searchSubEngineCode(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchSubEngineCode.searchSubEngineCode");
        }
        return rs.getResponseMessage();
    }
}
