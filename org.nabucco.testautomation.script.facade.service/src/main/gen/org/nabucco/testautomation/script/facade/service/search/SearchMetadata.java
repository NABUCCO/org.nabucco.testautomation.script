/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.service.search;

import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.testautomation.script.facade.message.MetadataListMsg;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.MetadataSearchMsg;

/**
 * SearchMetadata<p/>Metadata search service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-20
 */
public interface SearchMetadata extends Service {

    /**
     * Missing description at method searchMetadata.
     *
     * @param rq the ServiceRequest<MetadataSearchMsg>.
     * @return the ServiceResponse<MetadataListMsg>.
     * @throws SearchException
     */
    ServiceResponse<MetadataListMsg> searchMetadata(ServiceRequest<MetadataSearchMsg> rq)
            throws SearchException;

    /**
     * Missing description at method getMetadata.
     *
     * @param rq the ServiceRequest<MetadataSearchMsg>.
     * @return the ServiceResponse<MetadataMsg>.
     * @throws SearchException
     */
    ServiceResponse<MetadataMsg> getMetadata(ServiceRequest<MetadataSearchMsg> rq)
            throws SearchException;

    /**
     * Missing description at method getChildren.
     *
     * @param rq the ServiceRequest<MetadataMsg>.
     * @return the ServiceResponse<MetadataMsg>.
     * @throws SearchException
     */
    ServiceResponse<MetadataMsg> getChildren(ServiceRequest<MetadataMsg> rq) throws SearchException;

    /**
     * Missing description at method getParents.
     *
     * @param rq the ServiceRequest<MetadataMsg>.
     * @return the ServiceResponse<MetadataMsg>.
     * @throws SearchException
     */
    ServiceResponse<MetadataMsg> getParents(ServiceRequest<MetadataMsg> rq) throws SearchException;
}
