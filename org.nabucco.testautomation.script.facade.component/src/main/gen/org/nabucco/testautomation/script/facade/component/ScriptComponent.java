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

import org.nabucco.framework.base.facade.component.Component;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainScript;
import org.nabucco.testautomation.script.facade.service.produce.ProduceScript;
import org.nabucco.testautomation.script.facade.service.report.ReportScript;
import org.nabucco.testautomation.script.facade.service.resolve.ResolveScript;
import org.nabucco.testautomation.script.facade.service.search.SearchScript;

/**
 * ScriptComponent<p/>TestScript component<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-09
 */
public interface ScriptComponent extends Component {

    final String COMPONENT_NAME = "org.nabucco.testautomation.script";

    final String COMPONENT_PREFIX = "scpt";

    final String JNDI_NAME = ((((JNDI_PREFIX + "/") + COMPONENT_NAME) + "/") + "org.nabucco.testautomation.script.facade.component.ScriptComponent");

    /**
     * Getter for the MaintainScript.
     *
     * @return the MaintainScript.
     * @throws ServiceException
     */
    MaintainScript getMaintainScript() throws ServiceException;

    /**
     * Getter for the ProduceScript.
     *
     * @return the ProduceScript.
     * @throws ServiceException
     */
    ProduceScript getProduceScript() throws ServiceException;

    /**
     * Getter for the SearchScript.
     *
     * @return the SearchScript.
     * @throws ServiceException
     */
    SearchScript getSearchScript() throws ServiceException;

    /**
     * Getter for the ResolveScript.
     *
     * @return the ResolveScript.
     * @throws ServiceException
     */
    ResolveScript getResolveScript() throws ServiceException;

    /**
     * Getter for the ReportScript.
     *
     * @return the ReportScript.
     * @throws ServiceException
     */
    ReportScript getReportScript() throws ServiceException;
}
