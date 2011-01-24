/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.service.maintain;

import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;

/**
 * MaintainMetadata<p/>Metadata maintenance service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-19
 */
public interface MaintainMetadata extends Service {

    /**
     * Missing description at method maintainMetadata.
     *
     * @param rq the ServiceRequest<MetadataMsg>.
     * @return the ServiceResponse<MetadataMsg>.
     * @throws MaintainException
     */
    ServiceResponse<MetadataMsg> maintainMetadata(ServiceRequest<MetadataMsg> rq)
            throws MaintainException;
}
