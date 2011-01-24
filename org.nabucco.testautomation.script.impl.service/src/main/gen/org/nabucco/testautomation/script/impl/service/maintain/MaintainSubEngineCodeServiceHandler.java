/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.impl.service.maintain;

import org.nabucco.framework.base.facade.exception.NabuccoException;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.impl.service.handler.ServiceHandler;
import org.nabucco.framework.base.impl.service.handler.ServiceHandlerSupport;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeMsg;

/**
 * MaintainSubEngineCodeServiceHandler<p/>SubEngineCode maintenance service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-09
 */
public abstract class MaintainSubEngineCodeServiceHandler extends ServiceHandlerSupport implements
        ServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String ID = "org.nabucco.testautomation.script.impl.service.maintain.MaintainSubEngineCodeServiceHandler";

    /** Constructs a new MaintainSubEngineCodeServiceHandler instance. */
    public MaintainSubEngineCodeServiceHandler() {
        super();
    }

    /**
     * Invokes the service handler method.
     *
     * @param rq the ServiceRequest<SubEngineCodeMsg>.
     * @return the ServiceResponse<SubEngineCodeMsg>.
     * @throws MaintainException
     */
    protected ServiceResponse<SubEngineCodeMsg> invoke(ServiceRequest<SubEngineCodeMsg> rq)
            throws MaintainException {
        ServiceResponse<SubEngineCodeMsg> rs;
        SubEngineCodeMsg msg;
        try {
            this.validateRequest(rq);
            this.setContext(rq.getContext());
            msg = this.maintainSubEngineCode(rq.getRequestMessage());
            if ((msg == null)) {
                super.getLogger().warning("No response message defined.");
            } else {
                super.cleanServiceMessage(msg);
            }
            rs = new ServiceResponse<SubEngineCodeMsg>(rq.getContext());
            rs.setResponseMessage(msg);
            return rs;
        } catch (MaintainException e) {
            super.getLogger().error(e);
            throw e;
        } catch (NabuccoException e) {
            super.getLogger().error(e);
            MaintainException wrappedException = new MaintainException(e);
            throw wrappedException;
        } catch (Exception e) {
            super.getLogger().error(e);
            throw new MaintainException(e.getMessage());
        }
    }

    /**
     * Missing description at method maintainSubEngineCode.
     *
     * @param msg the SubEngineCodeMsg.
     * @return the SubEngineCodeMsg.
     * @throws MaintainException
     */
    protected abstract SubEngineCodeMsg maintainSubEngineCode(SubEngineCodeMsg msg)
            throws MaintainException;

    /**
     * Getter for the Id.
     *
     * @return the String.
     */
    protected static String getId() {
        return ID;
    }
}
