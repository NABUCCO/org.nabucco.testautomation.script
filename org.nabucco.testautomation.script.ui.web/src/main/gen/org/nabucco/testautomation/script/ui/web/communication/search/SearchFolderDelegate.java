/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.web.communication.search;

import org.nabucco.framework.base.facade.datatype.session.NabuccoSession;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
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
     * @param session the NabuccoSession.
     * @param rq the FolderSearchMsg.
     * @return the FolderMsg.
     * @throws SearchException
     */
    public FolderMsg getRootFolder(FolderSearchMsg rq, NabuccoSession session)
            throws SearchException {
        ServiceRequest<FolderSearchMsg> request = new ServiceRequest<FolderSearchMsg>(
                super.createServiceContext(session));
        request.setRequestMessage(rq);
        ServiceResponse<FolderMsg> rs;
        if ((service != null)) {
            rs = service.getRootFolder(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchFolder.getRootFolder");
        }
        return rs.getResponseMessage();
    }

    /**
     * Getter for the FolderStructure.
     *
     * @param session the NabuccoSession.
     * @param rq the FolderSearchMsg.
     * @return the FolderListMsg.
     * @throws SearchException
     */
    public FolderListMsg getFolderStructure(FolderSearchMsg rq, NabuccoSession session)
            throws SearchException {
        ServiceRequest<FolderSearchMsg> request = new ServiceRequest<FolderSearchMsg>(
                super.createServiceContext(session));
        request.setRequestMessage(rq);
        ServiceResponse<FolderListMsg> rs;
        if ((service != null)) {
            rs = service.getFolderStructure(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchFolder.getFolderStructure");
        }
        return rs.getResponseMessage();
    }
}
