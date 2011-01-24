/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.impl.service.produce;

import org.nabucco.framework.base.facade.exception.NabuccoException;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.impl.service.handler.ServiceHandler;
import org.nabucco.framework.base.impl.service.handler.ServiceHandlerSupport;
import org.nabucco.testautomation.script.facade.message.ProduceTestScriptElementMsg;

/**
 * ProduceTestScriptElementServiceHandler<p/>TestScript produce service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-13
 */
public abstract class ProduceTestScriptElementServiceHandler extends ServiceHandlerSupport
        implements ServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String ID = "org.nabucco.testautomation.script.impl.service.produce.ProduceTestScriptElementServiceHandler";

    /** Constructs a new ProduceTestScriptElementServiceHandler instance. */
    public ProduceTestScriptElementServiceHandler() {
        super();
    }

    /**
     * Invokes the service handler method.
     *
     * @param rq the ServiceRequest<ProduceTestScriptElementMsg>.
     * @return the ServiceResponse<ProduceTestScriptElementMsg>.
     * @throws ProduceException
     */
    protected ServiceResponse<ProduceTestScriptElementMsg> invoke(
            ServiceRequest<ProduceTestScriptElementMsg> rq) throws ProduceException {
        ServiceResponse<ProduceTestScriptElementMsg> rs;
        ProduceTestScriptElementMsg msg;
        try {
            this.validateRequest(rq);
            this.setContext(rq.getContext());
            msg = this.produceTestScriptElement(rq.getRequestMessage());
            if ((msg == null)) {
                super.getLogger().warning("No response message defined.");
            } else {
                super.cleanServiceMessage(msg);
            }
            rs = new ServiceResponse<ProduceTestScriptElementMsg>(rq.getContext());
            rs.setResponseMessage(msg);
            return rs;
        } catch (ProduceException e) {
            super.getLogger().error(e);
            throw e;
        } catch (NabuccoException e) {
            super.getLogger().error(e);
            ProduceException wrappedException = new ProduceException(e);
            throw wrappedException;
        } catch (Exception e) {
            super.getLogger().error(e);
            throw new ProduceException(e.getMessage());
        }
    }

    /**
     * Missing description at method produceTestScriptElement.
     *
     * @param msg the ProduceTestScriptElementMsg.
     * @return the ProduceTestScriptElementMsg.
     * @throws ProduceException
     */
    protected abstract ProduceTestScriptElementMsg produceTestScriptElement(
            ProduceTestScriptElementMsg msg) throws ProduceException;

    /**
     * Getter for the Id.
     *
     * @return the String.
     */
    protected static String getId() {
        return ID;
    }
}
