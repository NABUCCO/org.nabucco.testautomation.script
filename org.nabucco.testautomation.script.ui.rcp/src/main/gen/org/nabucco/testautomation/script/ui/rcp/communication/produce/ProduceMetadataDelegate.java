/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.communication.produce;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.framework.plugin.base.logging.NabuccoLogMessage;
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
     * @throws ClientException
     */
    public MetadataMsg produceMetadata(EmptyServiceMessage rq) throws ClientException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<MetadataMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.produceMetadata(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ProduceMetadataDelegate.class, "Service: ",
                                "ProduceMetadata.produceMetadata", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ProduceMetadata.produceMetadata");
    }

    /**
     * ProduceMetadataClone.
     *
     * @param rq the MetadataMsg.
     * @return the MetadataMsg.
     * @throws ClientException
     */
    public MetadataMsg produceMetadataClone(MetadataMsg rq) throws ClientException {
        ServiceRequest<MetadataMsg> request = new ServiceRequest<MetadataMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<MetadataMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.produceMetadataClone(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ProduceMetadataDelegate.class, "Service: ",
                                "ProduceMetadata.produceMetadataClone", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ProduceMetadata.produceMetadataClone");
    }

    /**
     * ProduceMetadataLabel.
     *
     * @param rq the EmptyServiceMessage.
     * @return the MetadataLabelMsg.
     * @throws ClientException
     */
    public MetadataLabelMsg produceMetadataLabel(EmptyServiceMessage rq) throws ClientException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<MetadataLabelMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.produceMetadataLabel(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ProduceMetadataDelegate.class, "Service: ",
                                "ProduceMetadata.produceMetadataLabel", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ProduceMetadata.produceMetadataLabel");
    }

    /**
     * ProduceMetadataLabelClone.
     *
     * @param rq the MetadataLabelMsg.
     * @return the MetadataLabelMsg.
     * @throws ClientException
     */
    public MetadataLabelMsg produceMetadataLabelClone(MetadataLabelMsg rq) throws ClientException {
        ServiceRequest<MetadataLabelMsg> request = new ServiceRequest<MetadataLabelMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<MetadataLabelMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.produceMetadataLabelClone(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ProduceMetadataDelegate.class, "Service: ",
                                "ProduceMetadata.produceMetadataLabelClone", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ProduceMetadata.produceMetadataLabelClone");
    }
}
