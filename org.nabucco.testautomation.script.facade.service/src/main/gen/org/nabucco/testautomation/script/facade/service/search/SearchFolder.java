/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.service.search;

import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.testautomation.script.facade.message.FolderListMsg;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.facade.message.FolderSearchMsg;

/**
 * SearchFolder<p/>Folder search service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-13
 */
public interface SearchFolder extends Service {

    /**
     * Returns the flat root folder
     *
     * @param rq the ServiceRequest<FolderSearchMsg>.
     * @return the ServiceResponse<FolderMsg>.
     * @throws SearchException
     */
    ServiceResponse<FolderMsg> getRootFolder(ServiceRequest<FolderSearchMsg> rq)
            throws SearchException;

    /**
     * Returns the root folder with loaded subfolders an flat loaded testscripts
     *
     * @param rq the ServiceRequest<FolderSearchMsg>.
     * @return the ServiceResponse<FolderListMsg>.
     * @throws SearchException
     */
    ServiceResponse<FolderListMsg> getFolderStructure(ServiceRequest<FolderSearchMsg> rq)
            throws SearchException;
}
