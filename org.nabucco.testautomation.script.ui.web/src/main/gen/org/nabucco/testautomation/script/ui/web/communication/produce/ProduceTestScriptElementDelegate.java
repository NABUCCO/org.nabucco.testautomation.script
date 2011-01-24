/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.web.communication.produce;

import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.testautomation.script.facade.message.ProduceTestScriptElementMsg;
import org.nabucco.testautomation.script.facade.service.produce.ProduceTestScriptElement;

/**
 * ProduceTestScriptElementDelegate<p/>TestScript produce service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-13
 */
public class ProduceTestScriptElementDelegate extends ServiceDelegateSupport {

    private ProduceTestScriptElement service;

    /**
     * Constructs a new ProduceTestScriptElementDelegate instance.
     *
     * @param service the ProduceTestScriptElement.
     */
    public ProduceTestScriptElementDelegate(ProduceTestScriptElement service) {
        super();
        this.service = service;
    }

    /**
     * ProduceTestScriptElement.
     *
     * @param rq the ProduceTestScriptElementMsg.
     * @return the ProduceTestScriptElementMsg.
     * @throws ProduceException
     */
    public ProduceTestScriptElementMsg produceTestScriptElement(ProduceTestScriptElementMsg rq)
            throws ProduceException {
        ServiceRequest<ProduceTestScriptElementMsg> request = new ServiceRequest<ProduceTestScriptElementMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<ProduceTestScriptElementMsg> rs;
        if ((service != null)) {
            rs = service.produceTestScriptElement(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceTestScriptElement.produceTestScriptElement");
        }
        return rs.getResponseMessage();
    }

    /**
     * ProduceTestScriptElement.
     *
     * @param subject the Subject.
     * @param rq the ProduceTestScriptElementMsg.
     * @return the ProduceTestScriptElementMsg.
     * @throws ProduceException
     */
    public ProduceTestScriptElementMsg produceTestScriptElement(ProduceTestScriptElementMsg rq,
            Subject subject) throws ProduceException {
        ServiceRequest<ProduceTestScriptElementMsg> request = new ServiceRequest<ProduceTestScriptElementMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<ProduceTestScriptElementMsg> rs;
        if ((service != null)) {
            rs = service.produceTestScriptElement(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceTestScriptElement.produceTestScriptElement");
        }
        return rs.getResponseMessage();
    }

    /**
     * ProduceTestScriptElementClone.
     *
     * @param rq the ProduceTestScriptElementMsg.
     * @return the ProduceTestScriptElementMsg.
     * @throws ProduceException
     */
    public ProduceTestScriptElementMsg produceTestScriptElementClone(ProduceTestScriptElementMsg rq)
            throws ProduceException {
        ServiceRequest<ProduceTestScriptElementMsg> request = new ServiceRequest<ProduceTestScriptElementMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<ProduceTestScriptElementMsg> rs;
        if ((service != null)) {
            rs = service.produceTestScriptElementClone(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceTestScriptElement.produceTestScriptElementClone");
        }
        return rs.getResponseMessage();
    }

    /**
     * ProduceTestScriptElementClone.
     *
     * @param subject the Subject.
     * @param rq the ProduceTestScriptElementMsg.
     * @return the ProduceTestScriptElementMsg.
     * @throws ProduceException
     */
    public ProduceTestScriptElementMsg produceTestScriptElementClone(
            ProduceTestScriptElementMsg rq, Subject subject) throws ProduceException {
        ServiceRequest<ProduceTestScriptElementMsg> request = new ServiceRequest<ProduceTestScriptElementMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<ProduceTestScriptElementMsg> rs;
        if ((service != null)) {
            rs = service.produceTestScriptElementClone(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceTestScriptElement.produceTestScriptElementClone");
        }
        return rs.getResponseMessage();
    }
}
