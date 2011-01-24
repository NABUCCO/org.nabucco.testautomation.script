/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.web.communication.maintain;

import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainFolder;

/**
 * MaintainFolderDelegate<p/>Folder maintenance service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-09
 */
public class MaintainFolderDelegate extends ServiceDelegateSupport {

    private MaintainFolder service;

    /**
     * Constructs a new MaintainFolderDelegate instance.
     *
     * @param service the MaintainFolder.
     */
    public MaintainFolderDelegate(MaintainFolder service) {
        super();
        this.service = service;
    }

    /**
     * MaintainFolder.
     *
     * @param rq the FolderMsg.
     * @return the FolderMsg.
     * @throws MaintainException
     */
    public FolderMsg maintainFolder(FolderMsg rq) throws MaintainException {
        ServiceRequest<FolderMsg> request = new ServiceRequest<FolderMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<FolderMsg> rs;
        if ((service != null)) {
            rs = service.maintainFolder(request);
        } else {
            throw new MaintainException(
                    "Cannot execute service operation: MaintainFolder.maintainFolder");
        }
        return rs.getResponseMessage();
    }

    /**
     * MaintainFolder.
     *
     * @param subject the Subject.
     * @param rq the FolderMsg.
     * @return the FolderMsg.
     * @throws MaintainException
     */
    public FolderMsg maintainFolder(FolderMsg rq, Subject subject) throws MaintainException {
        ServiceRequest<FolderMsg> request = new ServiceRequest<FolderMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<FolderMsg> rs;
        if ((service != null)) {
            rs = service.maintainFolder(request);
        } else {
            throw new MaintainException(
                    "Cannot execute service operation: MaintainFolder.maintainFolder");
        }
        return rs.getResponseMessage();
    }
}
