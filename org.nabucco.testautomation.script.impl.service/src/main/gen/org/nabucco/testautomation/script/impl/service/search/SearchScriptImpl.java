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
package org.nabucco.testautomation.script.impl.service.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
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
import org.nabucco.testautomation.script.facade.service.search.SearchScript;

/**
 * SearchScriptImpl<p/>Script search service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-13
 */
public class SearchScriptImpl extends ServiceSupport implements SearchScript {

    private static final long serialVersionUID = 1L;

    private static final String ID = "SearchScript";

    private static Map<String, String[]> ASPECTS;

    private SearchMetadataServiceHandler searchMetadataServiceHandler;

    private SearchSubEngineCodeServiceHandler searchSubEngineCodeServiceHandler;

    private SearchTestScriptServiceHandler searchTestScriptServiceHandler;

    private QuickSearchServiceHandler quickSearchServiceHandler;

    private GetRootFolderServiceHandler getRootFolderServiceHandler;

    private GetFolderStructureServiceHandler getFolderStructureServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new SearchScriptImpl instance. */
    public SearchScriptImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.searchMetadataServiceHandler = injector.inject(SearchMetadataServiceHandler.getId());
        if ((this.searchMetadataServiceHandler != null)) {
            this.searchMetadataServiceHandler.setPersistenceManager(persistenceManager);
            this.searchMetadataServiceHandler.setLogger(super.getLogger());
        }
        this.searchSubEngineCodeServiceHandler = injector.inject(SearchSubEngineCodeServiceHandler.getId());
        if ((this.searchSubEngineCodeServiceHandler != null)) {
            this.searchSubEngineCodeServiceHandler.setPersistenceManager(persistenceManager);
            this.searchSubEngineCodeServiceHandler.setLogger(super.getLogger());
        }
        this.searchTestScriptServiceHandler = injector.inject(SearchTestScriptServiceHandler.getId());
        if ((this.searchTestScriptServiceHandler != null)) {
            this.searchTestScriptServiceHandler.setPersistenceManager(persistenceManager);
            this.searchTestScriptServiceHandler.setLogger(super.getLogger());
        }
        this.quickSearchServiceHandler = injector.inject(QuickSearchServiceHandler.getId());
        if ((this.quickSearchServiceHandler != null)) {
            this.quickSearchServiceHandler.setPersistenceManager(persistenceManager);
            this.quickSearchServiceHandler.setLogger(super.getLogger());
        }
        this.getRootFolderServiceHandler = injector.inject(GetRootFolderServiceHandler.getId());
        if ((this.getRootFolderServiceHandler != null)) {
            this.getRootFolderServiceHandler.setPersistenceManager(persistenceManager);
            this.getRootFolderServiceHandler.setLogger(super.getLogger());
        }
        this.getFolderStructureServiceHandler = injector.inject(GetFolderStructureServiceHandler.getId());
        if ((this.getFolderStructureServiceHandler != null)) {
            this.getFolderStructureServiceHandler.setPersistenceManager(persistenceManager);
            this.getFolderStructureServiceHandler.setLogger(super.getLogger());
        }
    }

    @Override
    public void preDestroy() {
        super.preDestroy();
    }

    @Override
    public String[] getAspects(String operationName) {
        if ((ASPECTS == null)) {
            ASPECTS = new HashMap<String, String[]>();
            ASPECTS.put("searchMetadata", NO_ASPECTS);
            ASPECTS.put("searchSubEngineCode", NO_ASPECTS);
            ASPECTS.put("searchTestScript", NO_ASPECTS);
            ASPECTS.put("quickSearch", NO_ASPECTS);
            ASPECTS.put("getRootFolder", NO_ASPECTS);
            ASPECTS.put("getFolderStructure", NO_ASPECTS);
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<MetadataListMsg> searchMetadata(ServiceRequest<MetadataSearchMsg> rq) throws SearchException {
        if ((this.searchMetadataServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for searchMetadata().");
            throw new InjectionException("No service implementation configured for searchMetadata().");
        }
        ServiceResponse<MetadataListMsg> rs;
        this.searchMetadataServiceHandler.init();
        rs = this.searchMetadataServiceHandler.invoke(rq);
        this.searchMetadataServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<SubEngineCodeListMsg> searchSubEngineCode(ServiceRequest<SubEngineCodeSearchMsg> rq)
            throws SearchException {
        if ((this.searchSubEngineCodeServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for searchSubEngineCode().");
            throw new InjectionException("No service implementation configured for searchSubEngineCode().");
        }
        ServiceResponse<SubEngineCodeListMsg> rs;
        this.searchSubEngineCodeServiceHandler.init();
        rs = this.searchSubEngineCodeServiceHandler.invoke(rq);
        this.searchSubEngineCodeServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<TestScriptListMsg> searchTestScript(ServiceRequest<TestScriptSearchMsg> rq)
            throws SearchException {
        if ((this.searchTestScriptServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for searchTestScript().");
            throw new InjectionException("No service implementation configured for searchTestScript().");
        }
        ServiceResponse<TestScriptListMsg> rs;
        this.searchTestScriptServiceHandler.init();
        rs = this.searchTestScriptServiceHandler.invoke(rq);
        this.searchTestScriptServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<QuickSearchRs> quickSearch(ServiceRequest<QuickSearchMsg> rq) throws SearchException {
        if ((this.quickSearchServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for quickSearch().");
            throw new InjectionException("No service implementation configured for quickSearch().");
        }
        ServiceResponse<QuickSearchRs> rs;
        this.quickSearchServiceHandler.init();
        rs = this.quickSearchServiceHandler.invoke(rq);
        this.quickSearchServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<FolderMsg> getRootFolder(ServiceRequest<FolderSearchMsg> rq) throws SearchException {
        if ((this.getRootFolderServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for getRootFolder().");
            throw new InjectionException("No service implementation configured for getRootFolder().");
        }
        ServiceResponse<FolderMsg> rs;
        this.getRootFolderServiceHandler.init();
        rs = this.getRootFolderServiceHandler.invoke(rq);
        this.getRootFolderServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<FolderListMsg> getFolderStructure(ServiceRequest<FolderSearchMsg> rq) throws SearchException {
        if ((this.getFolderStructureServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for getFolderStructure().");
            throw new InjectionException("No service implementation configured for getFolderStructure().");
        }
        ServiceResponse<FolderListMsg> rs;
        this.getFolderStructureServiceHandler.init();
        rs = this.getFolderStructureServiceHandler.invoke(rq);
        this.getFolderStructureServiceHandler.finish();
        return rs;
    }
}
