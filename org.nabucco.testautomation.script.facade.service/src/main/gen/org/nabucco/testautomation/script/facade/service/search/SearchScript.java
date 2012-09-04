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
package org.nabucco.testautomation.script.facade.service.search;

import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.testautomation.property.facade.message.QuickSearchMsg;
import org.nabucco.testautomation.property.facade.message.QuickSearchRs;
import org.nabucco.testautomation.script.facade.message.FolderListMsg;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.facade.message.FolderSearchMsg;
import org.nabucco.testautomation.script.facade.message.MetadataListMsg;
import org.nabucco.testautomation.script.facade.message.MetadataSearchMsg;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeListMsg;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeSearchMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptListMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptSearchMsg;

/**
 * SearchScript<p/>Script search service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-13
 */
public interface SearchScript extends Service {

    /**
     * Missing description at method searchMetadata.
     *
     * @param rq the ServiceRequest<MetadataSearchMsg>.
     * @return the ServiceResponse<MetadataListMsg>.
     * @throws SearchException
     */
    ServiceResponse<MetadataListMsg> searchMetadata(ServiceRequest<MetadataSearchMsg> rq) throws SearchException;

    /**
     * Missing description at method searchSubEngineCode.
     *
     * @param rq the ServiceRequest<SubEngineCodeSearchMsg>.
     * @return the ServiceResponse<SubEngineCodeListMsg>.
     * @throws SearchException
     */
    ServiceResponse<SubEngineCodeListMsg> searchSubEngineCode(ServiceRequest<SubEngineCodeSearchMsg> rq)
            throws SearchException;

    /**
     * Missing description at method searchTestScript.
     *
     * @param rq the ServiceRequest<TestScriptSearchMsg>.
     * @return the ServiceResponse<TestScriptListMsg>.
     * @throws SearchException
     */
    ServiceResponse<TestScriptListMsg> searchTestScript(ServiceRequest<TestScriptSearchMsg> rq) throws SearchException;

    /**
     * Missing description at method quickSearch.
     *
     * @param rq the ServiceRequest<QuickSearchMsg>.
     * @return the ServiceResponse<QuickSearchRs>.
     * @throws SearchException
     */
    ServiceResponse<QuickSearchRs> quickSearch(ServiceRequest<QuickSearchMsg> rq) throws SearchException;

    /**
     * Returns the flat root folder
     *
     * @param rq the ServiceRequest<FolderSearchMsg>.
     * @return the ServiceResponse<FolderMsg>.
     * @throws SearchException
     */
    ServiceResponse<FolderMsg> getRootFolder(ServiceRequest<FolderSearchMsg> rq) throws SearchException;

    /**
     * Returns the root folder with loaded subfolders an flat loaded testscripts
     *
     * @param rq the ServiceRequest<FolderSearchMsg>.
     * @return the ServiceResponse<FolderListMsg>.
     * @throws SearchException
     */
    ServiceResponse<FolderListMsg> getFolderStructure(ServiceRequest<FolderSearchMsg> rq) throws SearchException;
}
