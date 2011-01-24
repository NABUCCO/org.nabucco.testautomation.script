/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.web.communication.search;

import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.testautomation.script.facade.message.MetadataListMsg;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.MetadataSearchMsg;
import org.nabucco.testautomation.script.facade.service.search.SearchMetadata;

/**
 * SearchMetadataDelegate<p/>Metadata search service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-20
 */
public class SearchMetadataDelegate extends ServiceDelegateSupport {

    private SearchMetadata service;

    /**
     * Constructs a new SearchMetadataDelegate instance.
     *
     * @param service the SearchMetadata.
     */
    public SearchMetadataDelegate(SearchMetadata service) {
        super();
        this.service = service;
    }

    /**
     * SearchMetadata.
     *
     * @param rq the MetadataSearchMsg.
     * @return the MetadataListMsg.
     * @throws SearchException
     */
    public MetadataListMsg searchMetadata(MetadataSearchMsg rq) throws SearchException {
        ServiceRequest<MetadataSearchMsg> request = new ServiceRequest<MetadataSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<MetadataListMsg> rs;
        if ((service != null)) {
            rs = service.searchMetadata(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchMetadata.searchMetadata");
        }
        return rs.getResponseMessage();
    }

    /**
     * SearchMetadata.
     *
     * @param subject the Subject.
     * @param rq the MetadataSearchMsg.
     * @return the MetadataListMsg.
     * @throws SearchException
     */
    public MetadataListMsg searchMetadata(MetadataSearchMsg rq, Subject subject)
            throws SearchException {
        ServiceRequest<MetadataSearchMsg> request = new ServiceRequest<MetadataSearchMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<MetadataListMsg> rs;
        if ((service != null)) {
            rs = service.searchMetadata(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchMetadata.searchMetadata");
        }
        return rs.getResponseMessage();
    }

    /**
     * Getter for the Metadata.
     *
     * @param rq the MetadataSearchMsg.
     * @return the MetadataMsg.
     * @throws SearchException
     */
    public MetadataMsg getMetadata(MetadataSearchMsg rq) throws SearchException {
        ServiceRequest<MetadataSearchMsg> request = new ServiceRequest<MetadataSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<MetadataMsg> rs;
        if ((service != null)) {
            rs = service.getMetadata(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchMetadata.getMetadata");
        }
        return rs.getResponseMessage();
    }

    /**
     * Getter for the Metadata.
     *
     * @param subject the Subject.
     * @param rq the MetadataSearchMsg.
     * @return the MetadataMsg.
     * @throws SearchException
     */
    public MetadataMsg getMetadata(MetadataSearchMsg rq, Subject subject) throws SearchException {
        ServiceRequest<MetadataSearchMsg> request = new ServiceRequest<MetadataSearchMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<MetadataMsg> rs;
        if ((service != null)) {
            rs = service.getMetadata(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchMetadata.getMetadata");
        }
        return rs.getResponseMessage();
    }

    /**
     * Getter for the Children.
     *
     * @param rq the MetadataMsg.
     * @return the MetadataMsg.
     * @throws SearchException
     */
    public MetadataMsg getChildren(MetadataMsg rq) throws SearchException {
        ServiceRequest<MetadataMsg> request = new ServiceRequest<MetadataMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<MetadataMsg> rs;
        if ((service != null)) {
            rs = service.getChildren(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchMetadata.getChildren");
        }
        return rs.getResponseMessage();
    }

    /**
     * Getter for the Children.
     *
     * @param subject the Subject.
     * @param rq the MetadataMsg.
     * @return the MetadataMsg.
     * @throws SearchException
     */
    public MetadataMsg getChildren(MetadataMsg rq, Subject subject) throws SearchException {
        ServiceRequest<MetadataMsg> request = new ServiceRequest<MetadataMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<MetadataMsg> rs;
        if ((service != null)) {
            rs = service.getChildren(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchMetadata.getChildren");
        }
        return rs.getResponseMessage();
    }

    /**
     * Getter for the Parents.
     *
     * @param rq the MetadataMsg.
     * @return the MetadataMsg.
     * @throws SearchException
     */
    public MetadataMsg getParents(MetadataMsg rq) throws SearchException {
        ServiceRequest<MetadataMsg> request = new ServiceRequest<MetadataMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<MetadataMsg> rs;
        if ((service != null)) {
            rs = service.getParents(request);
        } else {
            throw new SearchException("Cannot execute service operation: SearchMetadata.getParents");
        }
        return rs.getResponseMessage();
    }

    /**
     * Getter for the Parents.
     *
     * @param subject the Subject.
     * @param rq the MetadataMsg.
     * @return the MetadataMsg.
     * @throws SearchException
     */
    public MetadataMsg getParents(MetadataMsg rq, Subject subject) throws SearchException {
        ServiceRequest<MetadataMsg> request = new ServiceRequest<MetadataMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<MetadataMsg> rs;
        if ((service != null)) {
            rs = service.getParents(request);
        } else {
            throw new SearchException("Cannot execute service operation: SearchMetadata.getParents");
        }
        return rs.getResponseMessage();
    }
}
