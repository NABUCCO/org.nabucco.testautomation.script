/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.service.search;

import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeListMsg;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeSearchMsg;

/**
 * SearchSubEngineCode<p/>SubEngineCode search service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-13
 */
public interface SearchSubEngineCode extends Service {

    /**
     * Missing description at method searchSubEngineCode.
     *
     * @param rq the ServiceRequest<SubEngineCodeSearchMsg>.
     * @return the ServiceResponse<SubEngineCodeListMsg>.
     * @throws SearchException
     */
    ServiceResponse<SubEngineCodeListMsg> searchSubEngineCode(
            ServiceRequest<SubEngineCodeSearchMsg> rq) throws SearchException;
}
