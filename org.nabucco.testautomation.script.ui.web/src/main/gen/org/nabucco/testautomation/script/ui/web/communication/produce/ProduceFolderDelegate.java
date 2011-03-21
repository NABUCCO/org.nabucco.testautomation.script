/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.web.communication.produce;

import org.nabucco.framework.base.facade.datatype.session.NabuccoSession;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.facade.service.produce.ProduceFolder;

/**
 * ProduceFolderDelegate<p/>Folder produce service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-20
 */
public class ProduceFolderDelegate extends ServiceDelegateSupport {

    private ProduceFolder service;

    /**
     * Constructs a new ProduceFolderDelegate instance.
     *
     * @param service the ProduceFolder.
     */
    public ProduceFolderDelegate(ProduceFolder service) {
        super();
        this.service = service;
    }

    /**
     * ProduceFolder.
     *
     * @param session the NabuccoSession.
     * @param rq the EmptyServiceMessage.
     * @return the FolderMsg.
     * @throws ProduceException
     */
    public FolderMsg produceFolder(EmptyServiceMessage rq, NabuccoSession session)
            throws ProduceException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(session));
        request.setRequestMessage(rq);
        ServiceResponse<FolderMsg> rs;
        if ((service != null)) {
            rs = service.produceFolder(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceFolder.produceFolder");
        }
        return rs.getResponseMessage();
    }
}
