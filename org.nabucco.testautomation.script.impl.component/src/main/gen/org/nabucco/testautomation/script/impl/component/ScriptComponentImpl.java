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
package org.nabucco.testautomation.script.impl.component;

import org.nabucco.framework.base.facade.component.handler.PostConstructHandler;
import org.nabucco.framework.base.facade.component.handler.PreDestroyHandler;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.service.componentrelation.ComponentRelationService;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.facade.service.queryfilter.QueryFilterService;
import org.nabucco.framework.base.impl.component.ComponentSupport;
import org.nabucco.testautomation.script.facade.component.ScriptComponentLocal;
import org.nabucco.testautomation.script.facade.component.ScriptComponentRemote;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainScript;
import org.nabucco.testautomation.script.facade.service.produce.ProduceScript;
import org.nabucco.testautomation.script.facade.service.report.ReportScript;
import org.nabucco.testautomation.script.facade.service.resolve.ResolveScript;
import org.nabucco.testautomation.script.facade.service.search.SearchScript;

/**
 * ScriptComponentImpl<p/>TestScript component<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-09
 */
public class ScriptComponentImpl extends ComponentSupport implements ScriptComponentLocal, ScriptComponentRemote {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ScriptComponent";

    /** Constructs a new ScriptComponentImpl instance. */
    public ScriptComponentImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PostConstructHandler handler = injector.inject(PostConstructHandler.getId());
        if ((handler == null)) {
            if (super.getLogger().isDebugEnabled()) {
                super.getLogger().debug("No post construct handler configured for \'", ID, "\'.");
            }
            return;
        }
        handler.setLocatable(this);
        handler.setLogger(super.getLogger());
        handler.invoke();
    }

    @Override
    public void preDestroy() {
        super.preDestroy();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PreDestroyHandler handler = injector.inject(PreDestroyHandler.getId());
        if ((handler == null)) {
            if (super.getLogger().isDebugEnabled()) {
                super.getLogger().debug("No pre destroy handler configured for \'", ID, "\'.");
            }
            return;
        }
        handler.setLocatable(this);
        handler.setLogger(super.getLogger());
        handler.invoke();
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getName() {
        return COMPONENT_NAME;
    }

    @Override
    public String getJndiName() {
        return JNDI_NAME;
    }

    @Override
    public ComponentRelationService getComponentRelationService() throws ServiceException {
        return super.lookup(ScriptComponentJndiNames.COMPONENT_RELATION_SERVICE_REMOTE, ComponentRelationService.class);
    }

    @Override
    public ComponentRelationService getComponentRelationServiceLocal() throws ServiceException {
        return super.lookup(ScriptComponentJndiNames.COMPONENT_RELATION_SERVICE_LOCAL, ComponentRelationService.class);
    }

    @Override
    public QueryFilterService getQueryFilterService() throws ServiceException {
        return super.lookup(ScriptComponentJndiNames.QUERY_FILTER_SERVICE_REMOTE, QueryFilterService.class);
    }

    @Override
    public QueryFilterService getQueryFilterServiceLocal() throws ServiceException {
        return super.lookup(ScriptComponentJndiNames.QUERY_FILTER_SERVICE_LOCAL, QueryFilterService.class);
    }

    @Override
    public MaintainScript getMaintainScriptLocal() throws ServiceException {
        return super.lookup(ScriptComponentJndiNames.MAINTAIN_SCRIPT_LOCAL, MaintainScript.class);
    }

    @Override
    public MaintainScript getMaintainScript() throws ServiceException {
        return super.lookup(ScriptComponentJndiNames.MAINTAIN_SCRIPT_REMOTE, MaintainScript.class);
    }

    @Override
    public ProduceScript getProduceScriptLocal() throws ServiceException {
        return super.lookup(ScriptComponentJndiNames.PRODUCE_SCRIPT_LOCAL, ProduceScript.class);
    }

    @Override
    public ProduceScript getProduceScript() throws ServiceException {
        return super.lookup(ScriptComponentJndiNames.PRODUCE_SCRIPT_REMOTE, ProduceScript.class);
    }

    @Override
    public SearchScript getSearchScriptLocal() throws ServiceException {
        return super.lookup(ScriptComponentJndiNames.SEARCH_SCRIPT_LOCAL, SearchScript.class);
    }

    @Override
    public SearchScript getSearchScript() throws ServiceException {
        return super.lookup(ScriptComponentJndiNames.SEARCH_SCRIPT_REMOTE, SearchScript.class);
    }

    @Override
    public ResolveScript getResolveScriptLocal() throws ServiceException {
        return super.lookup(ScriptComponentJndiNames.RESOLVE_SCRIPT_LOCAL, ResolveScript.class);
    }

    @Override
    public ResolveScript getResolveScript() throws ServiceException {
        return super.lookup(ScriptComponentJndiNames.RESOLVE_SCRIPT_REMOTE, ResolveScript.class);
    }

    @Override
    public ReportScript getReportScriptLocal() throws ServiceException {
        return super.lookup(ScriptComponentJndiNames.REPORT_SCRIPT_LOCAL, ReportScript.class);
    }

    @Override
    public ReportScript getReportScript() throws ServiceException {
        return super.lookup(ScriptComponentJndiNames.REPORT_SCRIPT_REMOTE, ReportScript.class);
    }
}
