/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.service.maintain;

import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.testautomation.script.facade.message.FolderMsg;

/**
 * MaintainFolder<p/>Folder maintenance service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-09
 */
public interface MaintainFolder extends Service {

    /**
     * Missing description at method maintainFolder.
     *
     * @param rq the ServiceRequest<FolderMsg>.
     * @return the ServiceResponse<FolderMsg>.
     * @throws MaintainException
     */
    ServiceResponse<FolderMsg> maintainFolder(ServiceRequest<FolderMsg> rq)
            throws MaintainException;
}
