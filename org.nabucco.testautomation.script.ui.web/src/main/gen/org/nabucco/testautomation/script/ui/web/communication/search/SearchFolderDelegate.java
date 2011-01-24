/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.web.communication.search;

import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
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
     * @param rq the EmptyServiceMessage.
     * @return the FolderMsg.
     * @throws SearchException
     */
    public FolderMsg getRootFolder(EmptyServiceMessage rq) throws SearchException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext());
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
     * Getter for the RootFolder.
     *
     * @param subject the Subject.
     * @param rq the EmptyServiceMessage.
     * @return the FolderMsg.
     * @throws SearchException
     */
    public FolderMsg getRootFolder(EmptyServiceMessage rq, Subject subject) throws SearchException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(subject));
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
     * @param rq the EmptyServiceMessage.
     * @return the FolderMsg.
     * @throws SearchException
     */
    public FolderMsg getFolderStructure(EmptyServiceMessage rq) throws SearchException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<FolderMsg> rs;
        if ((service != null)) {
            rs = service.getFolderStructure(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchFolder.getFolderStructure");
        }
        return rs.getResponseMessage();
    }

    /**
     * Getter for the FolderStructure.
     *
     * @param subject the Subject.
     * @param rq the EmptyServiceMessage.
     * @return the FolderMsg.
     * @throws SearchException
     */
    public FolderMsg getFolderStructure(EmptyServiceMessage rq, Subject subject)
            throws SearchException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<FolderMsg> rs;
        if ((service != null)) {
            rs = service.getFolderStructure(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchFolder.getFolderStructure");
        }
        return rs.getResponseMessage();
    }
}
