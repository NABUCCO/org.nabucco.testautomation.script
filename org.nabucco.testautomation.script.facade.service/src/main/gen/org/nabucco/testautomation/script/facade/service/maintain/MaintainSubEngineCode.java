/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.service.maintain;

import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeMsg;

/**
 * MaintainSubEngineCode<p/>SubEngineCode maintenance service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-09
 */
public interface MaintainSubEngineCode extends Service {

    /**
     * Missing description at method maintainSubEngineCode.
     *
     * @param rq the ServiceRequest<SubEngineCodeMsg>.
     * @return the ServiceResponse<SubEngineCodeMsg>.
     * @throws MaintainException
     */
    ServiceResponse<SubEngineCodeMsg> maintainSubEngineCode(ServiceRequest<SubEngineCodeMsg> rq)
            throws MaintainException;
}
