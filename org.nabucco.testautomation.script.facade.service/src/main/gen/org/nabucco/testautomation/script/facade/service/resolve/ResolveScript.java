/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.nabucco.testautomation.script.facade.service.resolve;

import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.MetadataSearchMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptSearchMsg;

/**
 * ResolveScript<p/>Script resolve service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-13
 */
public interface ResolveScript extends Service {

    /**
     * Missing description at method resolveTestScript.
     *
     * @param rq the ServiceRequest<TestScriptSearchMsg>.
     * @return the ServiceResponse<TestScriptMsg>.
     * @throws ResolveException
     */
    ServiceResponse<TestScriptMsg> resolveTestScript(ServiceRequest<TestScriptSearchMsg> rq) throws ResolveException;

    /**
     * Missing description at method resolveMetadata.
     *
     * @param rq the ServiceRequest<MetadataSearchMsg>.
     * @return the ServiceResponse<MetadataMsg>.
     * @throws ResolveException
     */
    ServiceResponse<MetadataMsg> resolveMetadata(ServiceRequest<MetadataSearchMsg> rq) throws ResolveException;

    /**
     * Missing description at method resolveChildren.
     *
     * @param rq the ServiceRequest<MetadataMsg>.
     * @return the ServiceResponse<MetadataMsg>.
     * @throws ResolveException
     */
    ServiceResponse<MetadataMsg> resolveChildren(ServiceRequest<MetadataMsg> rq) throws ResolveException;

    /**
     * Missing description at method resolveParents.
     *
     * @param rq the ServiceRequest<MetadataMsg>.
     * @return the ServiceResponse<MetadataMsg>.
     * @throws ResolveException
     */
    ServiceResponse<MetadataMsg> resolveParents(ServiceRequest<MetadataMsg> rq) throws ResolveException;
}
