/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.web.communication.produce;

import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.testautomation.script.facade.message.MetadataLabelMsg;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.service.produce.ProduceMetadata;

/**
 * ProduceMetadataDelegate<p/>Metadata produce service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-20
 */
public class ProduceMetadataDelegate extends ServiceDelegateSupport {

    private ProduceMetadata service;

    /**
     * Constructs a new ProduceMetadataDelegate instance.
     *
     * @param service the ProduceMetadata.
     */
    public ProduceMetadataDelegate(ProduceMetadata service) {
        super();
        this.service = service;
    }

    /**
     * ProduceMetadata.
     *
     * @param rq the EmptyServiceMessage.
     * @return the MetadataMsg.
     * @throws ProduceException
     */
    public MetadataMsg produceMetadata(EmptyServiceMessage rq) throws ProduceException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<MetadataMsg> rs;
        if ((service != null)) {
            rs = service.produceMetadata(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceMetadata.produceMetadata");
        }
        return rs.getResponseMessage();
    }

    /**
     * ProduceMetadata.
     *
     * @param subject the Subject.
     * @param rq the EmptyServiceMessage.
     * @return the MetadataMsg.
     * @throws ProduceException
     */
    public MetadataMsg produceMetadata(EmptyServiceMessage rq, Subject subject)
            throws ProduceException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<MetadataMsg> rs;
        if ((service != null)) {
            rs = service.produceMetadata(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceMetadata.produceMetadata");
        }
        return rs.getResponseMessage();
    }

    /**
     * ProduceMetadataClone.
     *
     * @param rq the MetadataMsg.
     * @return the MetadataMsg.
     * @throws ProduceException
     */
    public MetadataMsg produceMetadataClone(MetadataMsg rq) throws ProduceException {
        ServiceRequest<MetadataMsg> request = new ServiceRequest<MetadataMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<MetadataMsg> rs;
        if ((service != null)) {
            rs = service.produceMetadataClone(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceMetadata.produceMetadataClone");
        }
        return rs.getResponseMessage();
    }

    /**
     * ProduceMetadataClone.
     *
     * @param subject the Subject.
     * @param rq the MetadataMsg.
     * @return the MetadataMsg.
     * @throws ProduceException
     */
    public MetadataMsg produceMetadataClone(MetadataMsg rq, Subject subject)
            throws ProduceException {
        ServiceRequest<MetadataMsg> request = new ServiceRequest<MetadataMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<MetadataMsg> rs;
        if ((service != null)) {
            rs = service.produceMetadataClone(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceMetadata.produceMetadataClone");
        }
        return rs.getResponseMessage();
    }

    /**
     * ProduceMetadataLabel.
     *
     * @param rq the EmptyServiceMessage.
     * @return the MetadataLabelMsg.
     * @throws ProduceException
     */
    public MetadataLabelMsg produceMetadataLabel(EmptyServiceMessage rq) throws ProduceException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<MetadataLabelMsg> rs;
        if ((service != null)) {
            rs = service.produceMetadataLabel(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceMetadata.produceMetadataLabel");
        }
        return rs.getResponseMessage();
    }

    /**
     * ProduceMetadataLabel.
     *
     * @param subject the Subject.
     * @param rq the EmptyServiceMessage.
     * @return the MetadataLabelMsg.
     * @throws ProduceException
     */
    public MetadataLabelMsg produceMetadataLabel(EmptyServiceMessage rq, Subject subject)
            throws ProduceException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<MetadataLabelMsg> rs;
        if ((service != null)) {
            rs = service.produceMetadataLabel(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceMetadata.produceMetadataLabel");
        }
        return rs.getResponseMessage();
    }

    /**
     * ProduceMetadataLabelClone.
     *
     * @param rq the MetadataLabelMsg.
     * @return the MetadataLabelMsg.
     * @throws ProduceException
     */
    public MetadataLabelMsg produceMetadataLabelClone(MetadataLabelMsg rq) throws ProduceException {
        ServiceRequest<MetadataLabelMsg> request = new ServiceRequest<MetadataLabelMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<MetadataLabelMsg> rs;
        if ((service != null)) {
            rs = service.produceMetadataLabelClone(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceMetadata.produceMetadataLabelClone");
        }
        return rs.getResponseMessage();
    }

    /**
     * ProduceMetadataLabelClone.
     *
     * @param subject the Subject.
     * @param rq the MetadataLabelMsg.
     * @return the MetadataLabelMsg.
     * @throws ProduceException
     */
    public MetadataLabelMsg produceMetadataLabelClone(MetadataLabelMsg rq, Subject subject)
            throws ProduceException {
        ServiceRequest<MetadataLabelMsg> request = new ServiceRequest<MetadataLabelMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<MetadataLabelMsg> rs;
        if ((service != null)) {
            rs = service.produceMetadataLabelClone(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceMetadata.produceMetadataLabelClone");
        }
        return rs.getResponseMessage();
    }
}
