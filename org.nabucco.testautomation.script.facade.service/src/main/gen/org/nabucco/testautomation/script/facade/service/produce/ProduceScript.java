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
package org.nabucco.testautomation.script.facade.service.produce;

import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.facade.message.MetadataLabelMsg;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.ProduceTestScriptElementMsg;

/**
 * ProduceScript<p/>Folder produce service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-20
 */
public interface ProduceScript extends Service {

    /**
     * Missing description at method produceFolder.
     *
     * @param rq the ServiceRequest<EmptyServiceMessage>.
     * @return the ServiceResponse<FolderMsg>.
     * @throws ProduceException
     */
    ServiceResponse<FolderMsg> produceFolder(ServiceRequest<EmptyServiceMessage> rq) throws ProduceException;

    /**
     * Missing description at method produceMetadata.
     *
     * @param rq the ServiceRequest<EmptyServiceMessage>.
     * @return the ServiceResponse<MetadataMsg>.
     * @throws ProduceException
     */
    ServiceResponse<MetadataMsg> produceMetadata(ServiceRequest<EmptyServiceMessage> rq) throws ProduceException;

    /**
     * Missing description at method produceMetadataClone.
     *
     * @param rq the ServiceRequest<MetadataMsg>.
     * @return the ServiceResponse<MetadataMsg>.
     * @throws ProduceException
     */
    ServiceResponse<MetadataMsg> produceMetadataClone(ServiceRequest<MetadataMsg> rq) throws ProduceException;

    /**
     * Missing description at method produceMetadataLabel.
     *
     * @param rq the ServiceRequest<EmptyServiceMessage>.
     * @return the ServiceResponse<MetadataLabelMsg>.
     * @throws ProduceException
     */
    ServiceResponse<MetadataLabelMsg> produceMetadataLabel(ServiceRequest<EmptyServiceMessage> rq)
            throws ProduceException;

    /**
     * Missing description at method produceMetadataLabelClone.
     *
     * @param rq the ServiceRequest<MetadataLabelMsg>.
     * @return the ServiceResponse<MetadataLabelMsg>.
     * @throws ProduceException
     */
    ServiceResponse<MetadataLabelMsg> produceMetadataLabelClone(ServiceRequest<MetadataLabelMsg> rq)
            throws ProduceException;

    /**
     * Missing description at method produceTestScriptElement.
     *
     * @param rq the ServiceRequest<ProduceTestScriptElementMsg>.
     * @return the ServiceResponse<ProduceTestScriptElementMsg>.
     * @throws ProduceException
     */
    ServiceResponse<ProduceTestScriptElementMsg> produceTestScriptElement(ServiceRequest<ProduceTestScriptElementMsg> rq)
            throws ProduceException;

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
