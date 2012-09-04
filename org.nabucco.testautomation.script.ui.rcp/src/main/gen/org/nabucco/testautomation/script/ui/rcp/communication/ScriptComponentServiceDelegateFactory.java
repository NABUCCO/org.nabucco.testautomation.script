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
package org.nabucco.testautomation.script.ui.rcp.communication;

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateFactorySupport;
import org.nabucco.testautomation.script.facade.component.ScriptComponent;
import org.nabucco.testautomation.script.facade.component.ScriptComponentLocator;
import org.nabucco.testautomation.script.ui.rcp.communication.maintain.MaintainScriptDelegate;
import org.nabucco.testautomation.script.ui.rcp.communication.produce.ProduceScriptDelegate;
import org.nabucco.testautomation.script.ui.rcp.communication.report.ReportScriptDelegate;
import org.nabucco.testautomation.script.ui.rcp.communication.resolve.ResolveScriptDelegate;
import org.nabucco.testautomation.script.ui.rcp.communication.search.SearchScriptDelegate;

/**
 * ServiceDelegateFactoryTemplate<p/>TestScript component<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-09
 */
public class ScriptComponentServiceDelegateFactory extends ServiceDelegateFactorySupport<ScriptComponent> {

    private static ScriptComponentServiceDelegateFactory instance = new ScriptComponentServiceDelegateFactory();

    private MaintainScriptDelegate maintainScriptDelegate;

    private ProduceScriptDelegate produceScriptDelegate;

    private SearchScriptDelegate searchScriptDelegate;

    private ResolveScriptDelegate resolveScriptDelegate;

    private ReportScriptDelegate reportScriptDelegate;

    /** Constructs a new ScriptComponentServiceDelegateFactory instance. */
    private ScriptComponentServiceDelegateFactory() {
        super(ScriptComponentLocator.getInstance());
    }

    /**
     * Getter for the MaintainScript.
     *
     * @return the MaintainScriptDelegate.
     * @throws ClientException
     */
    public MaintainScriptDelegate getMaintainScript() throws ClientException {
        try {
            if ((this.maintainScriptDelegate == null)) {
                this.maintainScriptDelegate = new MaintainScriptDelegate(this.getComponent().getMaintainScript());
            }
            return this.maintainScriptDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: ScriptComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: MaintainScript", e);
        }
    }

    /**
     * Getter for the ProduceScript.
     *
     * @return the ProduceScriptDelegate.
     * @throws ClientException
     */
    public ProduceScriptDelegate getProduceScript() throws ClientException {
        try {
            if ((this.produceScriptDelegate == null)) {
                this.produceScriptDelegate = new ProduceScriptDelegate(this.getComponent().getProduceScript());
            }
            return this.produceScriptDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: ScriptComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ProduceScript", e);
        }
    }

    /**
     * Getter for the SearchScript.
     *
     * @return the SearchScriptDelegate.
     * @throws ClientException
     */
    public SearchScriptDelegate getSearchScript() throws ClientException {
        try {
            if ((this.searchScriptDelegate == null)) {
                this.searchScriptDelegate = new SearchScriptDelegate(this.getComponent().getSearchScript());
            }
            return this.searchScriptDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: ScriptComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: SearchScript", e);
        }
    }

    /**
     * Getter for the ResolveScript.
     *
     * @return the ResolveScriptDelegate.
     * @throws ClientException
     */
    public ResolveScriptDelegate getResolveScript() throws ClientException {
        try {
            if ((this.resolveScriptDelegate == null)) {
                this.resolveScriptDelegate = new ResolveScriptDelegate(this.getComponent().getResolveScript());
            }
            return this.resolveScriptDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: ScriptComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ResolveScript", e);
        }
    }

    /**
     * Getter for the ReportScript.
     *
     * @return the ReportScriptDelegate.
     * @throws ClientException
     */
    public ReportScriptDelegate getReportScript() throws ClientException {
        try {
            if ((this.reportScriptDelegate == null)) {
                this.reportScriptDelegate = new ReportScriptDelegate(this.getComponent().getReportScript());
            }
            return this.reportScriptDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: ScriptComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ReportScript", e);
        }
    }

    /**
     * Getter for the Instance.
     *
     * @return the ScriptComponentServiceDelegateFactory.
     */
    public static ScriptComponentServiceDelegateFactory getInstance() {
        return instance;
    }
}
