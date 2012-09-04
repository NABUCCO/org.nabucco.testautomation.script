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
package org.nabucco.testautomation.script.facade.component;

import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.service.componentrelation.ComponentRelationService;
import org.nabucco.framework.base.facade.service.queryfilter.QueryFilterService;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainScript;
import org.nabucco.testautomation.script.facade.service.produce.ProduceScript;
import org.nabucco.testautomation.script.facade.service.report.ReportScript;
import org.nabucco.testautomation.script.facade.service.resolve.ResolveScript;
import org.nabucco.testautomation.script.facade.service.search.SearchScript;

/**
 * ScriptComponentLocal<p/>TestScript component<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-09
 */
public interface ScriptComponentLocal extends ScriptComponent {

    /**
     * Getter for the ComponentRelationServiceLocal.
     *
     * @return the ComponentRelationService.
     * @throws ServiceException
     */
    ComponentRelationService getComponentRelationServiceLocal() throws ServiceException;

    /**
     * Getter for the QueryFilterServiceLocal.
     *
     * @return the QueryFilterService.
     * @throws ServiceException
     */
    QueryFilterService getQueryFilterServiceLocal() throws ServiceException;

    /**
     * Getter for the MaintainScriptLocal.
     *
     * @return the MaintainScript.
     * @throws ServiceException
     */
    MaintainScript getMaintainScriptLocal() throws ServiceException;

    /**
     * Getter for the ProduceScriptLocal.
     *
     * @return the ProduceScript.
     * @throws ServiceException
     */
    ProduceScript getProduceScriptLocal() throws ServiceException;

    /**
     * Getter for the SearchScriptLocal.
     *
     * @return the SearchScript.
     * @throws ServiceException
     */
    SearchScript getSearchScriptLocal() throws ServiceException;

    /**
     * Getter for the ResolveScriptLocal.
     *
     * @return the ResolveScript.
     * @throws ServiceException
     */
    ResolveScript getResolveScriptLocal() throws ServiceException;

    /**
     * Getter for the ReportScriptLocal.
     *
     * @return the ReportScript.
     * @throws ServiceException
     */
    ReportScript getReportScriptLocal() throws ServiceException;
}
