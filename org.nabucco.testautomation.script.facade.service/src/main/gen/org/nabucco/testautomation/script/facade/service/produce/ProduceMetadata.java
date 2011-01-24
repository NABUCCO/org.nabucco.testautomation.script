/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.service.produce;

import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.testautomation.script.facade.message.MetadataLabelMsg;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;

/**
 * ProduceMetadata<p/>Metadata produce service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-20
 */
public interface ProduceMetadata extends Service {

    /**
     * Missing description at method produceMetadata.
     *
     * @param rq the ServiceRequest<EmptyServiceMessage>.
     * @return the ServiceResponse<MetadataMsg>.
     * @throws ProduceException
     */
    ServiceResponse<MetadataMsg> produceMetadata(ServiceRequest<EmptyServiceMessage> rq)
            throws ProduceException;

    /**
     * Missing description at method produceMetadataClone.
     *
     * @param rq the ServiceRequest<MetadataMsg>.
     * @return the ServiceResponse<MetadataMsg>.
     * @throws ProduceException
     */
    ServiceResponse<MetadataMsg> produceMetadataClone(ServiceRequest<MetadataMsg> rq)
            throws ProduceException;

    /**
     * Missing description at method produceMetadataLabel.
     *
     * @param rq the ServiceRequest<EmptyServiceMessage>.
     * @return the ServiceResponse<MetadataLabelMsg>.
     * @throws ProduceException
     */
    ServiceResponse<MetadataLabelMsg> produceMetadataLabel(ServiceRequest<EmptyServiceMessage> rq)
            throws ProduceException;

    /**
     * Missing description at method produceMetadataLabelClone.
     *
     * @param rq the ServiceRequest<MetadataLabelMsg>.
     * @return the ServiceResponse<MetadataLabelMsg>.
     * @throws ProduceException
     */
    ServiceResponse<MetadataLabelMsg> produceMetadataLabelClone(ServiceRequest<MetadataLabelMsg> rq)
            throws ProduceException;
}
