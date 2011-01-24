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
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainMetadata;

/**
 * MaintainMetadataDelegate<p/>Metadata maintenance service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-19
 */
public class MaintainMetadataDelegate extends ServiceDelegateSupport {

    private MaintainMetadata service;

    /**
     * Constructs a new MaintainMetadataDelegate instance.
     *
     * @param service the MaintainMetadata.
     */
    public MaintainMetadataDelegate(MaintainMetadata service) {
        super();
        this.service = service;
    }

    /**
     * MaintainMetadata.
     *
     * @param rq the MetadataMsg.
     * @return the MetadataMsg.
     * @throws ClientException
     */
    public MetadataMsg maintainMetadata(MetadataMsg rq) throws ClientException {
        ServiceRequest<MetadataMsg> request = new ServiceRequest<MetadataMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<MetadataMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.maintainMetadata(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(MaintainMetadataDelegate.class, "Service: ",
                                "MaintainMetadata.maintainMetadata", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: MaintainMetadata.maintainMetadata");
    }
}
