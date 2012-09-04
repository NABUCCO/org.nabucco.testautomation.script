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
import org.nabucco.testautomation.script.facade.component.ScriptComponent;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainScript;
import org.nabucco.testautomation.script.facade.service.produce.ProduceScript;
import org.nabucco.testautomation.script.facade.service.report.ReportScript;
import org.nabucco.testautomation.script.facade.service.resolve.ResolveScript;
import org.nabucco.testautomation.script.facade.service.search.SearchScript;

/**
 * ScriptComponentLocalProxy<p/>TestScript component<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-09
 */
public class ScriptComponentLocalProxy implements ScriptComponent {

    private static final long serialVersionUID = 1L;

    private final ScriptComponentLocal delegate;

    /**
     * Constructs a new ScriptComponentLocalProxy instance.
     *
     * @param delegate the ScriptComponentLocal.
     */
    public ScriptComponentLocalProxy(ScriptComponentLocal delegate) {
        super();
        if ((delegate == null)) {
            throw new IllegalArgumentException("Cannot create local proxy for component [null].");
        }
        this.delegate = delegate;
    }

    @Override
    public String getId() {
        return this.delegate.getId();
    }

    @Override
    public String getName() {
        return this.delegate.getName();
    }

    @Override
    public String getJndiName() {
        return this.delegate.getJndiName();
    }

    @Override
    public ComponentRelationService getComponentRelationService() throws ServiceException {
        return this.delegate.getComponentRelationServiceLocal();
    }

    @Override
    public QueryFilterService getQueryFilterService() throws ServiceException {
        return this.delegate.getQueryFilterServiceLocal();
    }

    @Override
    public String toString() {
        return this.delegate.toString();
    }

    @Override
    public MaintainScript getMaintainScript() throws ServiceException {
        return this.delegate.getMaintainScriptLocal();
    }

    @Override
    public ProduceScript getProduceScript() throws ServiceException {
        return this.delegate.getProduceScriptLocal();
    }

    @Override
    public SearchScript getSearchScript() throws ServiceException {
        return this.delegate.getSearchScriptLocal();
    }

    @Override
    public ResolveScript getResolveScript() throws ServiceException {
        return this.delegate.getResolveScriptLocal();
    }

    @Override
    public ReportScript getReportScript() throws ServiceException {
        return this.delegate.getReportScriptLocal();
    }
}
