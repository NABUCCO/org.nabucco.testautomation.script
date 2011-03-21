/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.web.communication.maintain;

import org.nabucco.framework.base.facade.datatype.session.NabuccoSession;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
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
     * @param session the NabuccoSession.
     * @param rq the MetadataMsg.
     * @return the MetadataMsg.
     * @throws MaintainException
     */
    public MetadataMsg maintainMetadata(MetadataMsg rq, NabuccoSession session)
            throws MaintainException {
        ServiceRequest<MetadataMsg> request = new ServiceRequest<MetadataMsg>(
                super.createServiceContext(session));
        request.setRequestMessage(rq);
        ServiceResponse<MetadataMsg> rs;
        if ((service != null)) {
            rs = service.maintainMetadata(request);
        } else {
            throw new MaintainException(
                    "Cannot execute service operation: MaintainMetadata.maintainMetadata");
        }
        return rs.getResponseMessage();
    }
}
