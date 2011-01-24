/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.communication.search;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.framework.plugin.base.logging.NabuccoLogMessage;
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
     * @throws ClientException
     */
    public MetadataListMsg searchMetadata(MetadataSearchMsg rq) throws ClientException {
        ServiceRequest<MetadataSearchMsg> request = new ServiceRequest<MetadataSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<MetadataListMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.searchMetadata(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(SearchMetadataDelegate.class, "Service: ",
                                "SearchMetadata.searchMetadata", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException("Cannot execute service operation: SearchMetadata.searchMetadata");
    }

    /**
     * Getter for the Metadata.
     *
     * @param rq the MetadataSearchMsg.
     * @return the MetadataMsg.
     * @throws ClientException
     */
    public MetadataMsg getMetadata(MetadataSearchMsg rq) throws ClientException {
        ServiceRequest<MetadataSearchMsg> request = new ServiceRequest<MetadataSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<MetadataMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.getMetadata(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(SearchMetadataDelegate.class, "Service: ",
                                "SearchMetadata.getMetadata", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException("Cannot execute service operation: SearchMetadata.getMetadata");
    }

    /**
     * Getter for the Children.
     *
     * @param rq the MetadataMsg.
     * @return the MetadataMsg.
     * @throws ClientException
     */
    public MetadataMsg getChildren(MetadataMsg rq) throws ClientException {
        ServiceRequest<MetadataMsg> request = new ServiceRequest<MetadataMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<MetadataMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.getChildren(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(SearchMetadataDelegate.class, "Service: ",
                                "SearchMetadata.getChildren", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException("Cannot execute service operation: SearchMetadata.getChildren");
    }

    /**
     * Getter for the Parents.
     *
     * @param rq the MetadataMsg.
     * @return the MetadataMsg.
     * @throws ClientException
     */
    public MetadataMsg getParents(MetadataMsg rq) throws ClientException {
        ServiceRequest<MetadataMsg> request = new ServiceRequest<MetadataMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<MetadataMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.getParents(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(SearchMetadataDelegate.class, "Service: ",
                                "SearchMetadata.getParents", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException("Cannot execute service operation: SearchMetadata.getParents");
    }
}
