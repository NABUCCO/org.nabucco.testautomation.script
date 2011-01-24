/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.communication.maintain;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.framework.plugin.base.logging.NabuccoLogMessage;
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
     * @throws ClientException
     */
    public FolderMsg maintainFolder(FolderMsg rq) throws ClientException {
        ServiceRequest<FolderMsg> request = new ServiceRequest<FolderMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<FolderMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.maintainFolder(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(MaintainFolderDelegate.class, "Service: ",
                                "MaintainFolder.maintainFolder", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainFolder.maintainFolder");
    }
}
