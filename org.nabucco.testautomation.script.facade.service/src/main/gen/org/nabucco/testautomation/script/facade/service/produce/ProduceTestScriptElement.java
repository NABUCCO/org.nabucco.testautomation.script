/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.service.produce;

import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.testautomation.script.facade.message.ProduceTestScriptElementMsg;

/**
 * ProduceTestScriptElement<p/>TestScript produce service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-13
 */
public interface ProduceTestScriptElement extends Service {

    /**
     * Missing description at method produceTestScriptElement.
     *
     * @param rq the ServiceRequest<ProduceTestScriptElementMsg>.
     * @return the ServiceResponse<ProduceTestScriptElementMsg>.
     * @throws ProduceException
     */
    ServiceResponse<ProduceTestScriptElementMsg> produceTestScriptElement(
            ServiceRequest<ProduceTestScriptElementMsg> rq) throws ProduceException;

    /**
     * Missing description at method produceTestScriptElementClone.
     *
     * @param rq the ServiceRequest<ProduceTestScriptElementMsg>.
     * @return the ServiceResponse<ProduceTestScriptElementMsg>.
     * @throws ProduceException
     */
    ServiceResponse<ProduceTestScriptElementMsg> produceTestScriptElementClone(
            ServiceRequest<ProduceTestScriptElementMsg> rq) throws ProduceException;
}
