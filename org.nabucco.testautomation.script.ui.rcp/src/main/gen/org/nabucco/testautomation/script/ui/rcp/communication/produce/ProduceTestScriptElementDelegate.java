/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.communication.produce;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.framework.plugin.base.logging.NabuccoLogMessage;
import org.nabucco.testautomation.script.facade.message.ProduceTestScriptElementMsg;
import org.nabucco.testautomation.script.facade.service.produce.ProduceTestScriptElement;

/**
 * ProduceTestScriptElementDelegate<p/>TestScript produce service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-13
 */
public class ProduceTestScriptElementDelegate extends ServiceDelegateSupport {

    private ProduceTestScriptElement service;

    /**
     * Constructs a new ProduceTestScriptElementDelegate instance.
     *
     * @param service the ProduceTestScriptElement.
     */
    public ProduceTestScriptElementDelegate(ProduceTestScriptElement service) {
        super();
        this.service = service;
    }

    /**
     * ProduceTestScriptElement.
     *
     * @param rq the ProduceTestScriptElementMsg.
     * @return the ProduceTestScriptElementMsg.
     * @throws ClientException
     */
    public ProduceTestScriptElementMsg produceTestScriptElement(ProduceTestScriptElementMsg rq)
            throws ClientException {
        ServiceRequest<ProduceTestScriptElementMsg> request = new ServiceRequest<ProduceTestScriptElementMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<ProduceTestScriptElementMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.produceTestScriptElement(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ProduceTestScriptElementDelegate.class, "Service: ",
                                "ProduceTestScriptElement.produceTestScriptElement", " Time: ",
                                String.valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ProduceTestScriptElement.produceTestScriptElement");
    }

    /**
     * ProduceTestScriptElementClone.
     *
     * @param rq the ProduceTestScriptElementMsg.
     * @return the ProduceTestScriptElementMsg.
     * @throws ClientException
     */
    public ProduceTestScriptElementMsg produceTestScriptElementClone(ProduceTestScriptElementMsg rq)
            throws ClientException {
        ServiceRequest<ProduceTestScriptElementMsg> request = new ServiceRequest<ProduceTestScriptElementMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<ProduceTestScriptElementMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.produceTestScriptElementClone(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ProduceTestScriptElementDelegate.class, "Service: ",
                                "ProduceTestScriptElement.produceTestScriptElementClone",
                                " Time: ", String.valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ProduceTestScriptElement.produceTestScriptElementClone");
    }
}
