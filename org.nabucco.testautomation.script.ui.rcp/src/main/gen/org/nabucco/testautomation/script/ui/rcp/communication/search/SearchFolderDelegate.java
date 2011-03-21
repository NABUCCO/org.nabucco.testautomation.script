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
import org.nabucco.testautomation.script.facade.message.FolderListMsg;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.facade.message.FolderSearchMsg;
import org.nabucco.testautomation.script.facade.service.search.SearchFolder;

/**
 * SearchFolderDelegate<p/>Folder search service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-13
 */
public class SearchFolderDelegate extends ServiceDelegateSupport {

    private SearchFolder service;

    /**
     * Constructs a new SearchFolderDelegate instance.
     *
     * @param service the SearchFolder.
     */
    public SearchFolderDelegate(SearchFolder service) {
        super();
        this.service = service;
    }

    /**
     * Getter for the RootFolder.
     *
     * @param rq the FolderSearchMsg.
     * @return the FolderMsg.
     * @throws ClientException
     */
    public FolderMsg getRootFolder(FolderSearchMsg rq) throws ClientException {
        ServiceRequest<FolderSearchMsg> request = new ServiceRequest<FolderSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<FolderMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.getRootFolder(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(SearchFolderDelegate.class, "Service: ",
                                "SearchFolder.getRootFolder", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException("Cannot execute service operation: SearchFolder.getRootFolder");
    }

    /**
     * Getter for the FolderStructure.
     *
     * @param rq the FolderSearchMsg.
     * @return the FolderListMsg.
     * @throws ClientException
     */
    public FolderListMsg getFolderStructure(FolderSearchMsg rq) throws ClientException {
        ServiceRequest<FolderSearchMsg> request = new ServiceRequest<FolderSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<FolderListMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.getFolderStructure(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(SearchFolderDelegate.class, "Service: ",
                                "SearchFolder.getFolderStructure", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: SearchFolder.getFolderStructure");
    }
}
