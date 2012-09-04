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
package org.nabucco.testautomation.script.impl.service.maintain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainScript;

/**
 * MaintainScriptImpl<p/>Folder maintenance service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-09
 */
public class MaintainScriptImpl extends ServiceSupport implements MaintainScript {

    private static final long serialVersionUID = 1L;

    private static final String ID = "MaintainScript";

    private static Map<String, String[]> ASPECTS;

    private MaintainFolderServiceHandler maintainFolderServiceHandler;

    private MaintainMetadataServiceHandler maintainMetadataServiceHandler;

    private MaintainSubEngineCodeServiceHandler maintainSubEngineCodeServiceHandler;

    private MaintainTestScriptServiceHandler maintainTestScriptServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new MaintainScriptImpl instance. */
    public MaintainScriptImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.maintainFolderServiceHandler = injector.inject(MaintainFolderServiceHandler.getId());
        if ((this.maintainFolderServiceHandler != null)) {
            this.maintainFolderServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainFolderServiceHandler.setLogger(super.getLogger());
        }
        this.maintainMetadataServiceHandler = injector.inject(MaintainMetadataServiceHandler.getId());
        if ((this.maintainMetadataServiceHandler != null)) {
            this.maintainMetadataServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainMetadataServiceHandler.setLogger(super.getLogger());
        }
        this.maintainSubEngineCodeServiceHandler = injector.inject(MaintainSubEngineCodeServiceHandler.getId());
        if ((this.maintainSubEngineCodeServiceHandler != null)) {
            this.maintainSubEngineCodeServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainSubEngineCodeServiceHandler.setLogger(super.getLogger());
        }
        this.maintainTestScriptServiceHandler = injector.inject(MaintainTestScriptServiceHandler.getId());
        if ((this.maintainTestScriptServiceHandler != null)) {
            this.maintainTestScriptServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainTestScriptServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("maintainFolder", NO_ASPECTS);
            ASPECTS.put("maintainMetadata", NO_ASPECTS);
            ASPECTS.put("maintainSubEngineCode", NO_ASPECTS);
            ASPECTS.put("maintainTestScript", NO_ASPECTS);
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<FolderMsg> maintainFolder(ServiceRequest<FolderMsg> rq) throws MaintainException {
        if ((this.maintainFolderServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainFolder().");
            throw new InjectionException("No service implementation configured for maintainFolder().");
        }
        ServiceResponse<FolderMsg> rs;
        this.maintainFolderServiceHandler.init();
        rs = this.maintainFolderServiceHandler.invoke(rq);
        this.maintainFolderServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<MetadataMsg> maintainMetadata(ServiceRequest<MetadataMsg> rq) throws MaintainException {
        if ((this.maintainMetadataServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainMetadata().");
            throw new InjectionException("No service implementation configured for maintainMetadata().");
        }
        ServiceResponse<MetadataMsg> rs;
        this.maintainMetadataServiceHandler.init();
        rs = this.maintainMetadataServiceHandler.invoke(rq);
        this.maintainMetadataServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<SubEngineCodeMsg> maintainSubEngineCode(ServiceRequest<SubEngineCodeMsg> rq)
            throws MaintainException {
        if ((this.maintainSubEngineCodeServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainSubEngineCode().");
            throw new InjectionException("No service implementation configured for maintainSubEngineCode().");
        }
        ServiceResponse<SubEngineCodeMsg> rs;
        this.maintainSubEngineCodeServiceHandler.init();
        rs = this.maintainSubEngineCodeServiceHandler.invoke(rq);
        this.maintainSubEngineCodeServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<TestScriptMsg> maintainTestScript(ServiceRequest<TestScriptMsg> rq) throws MaintainException {
        if ((this.maintainTestScriptServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainTestScript().");
            throw new InjectionException("No service implementation configured for maintainTestScript().");
        }
        ServiceResponse<TestScriptMsg> rs;
        this.maintainTestScriptServiceHandler.init();
        rs = this.maintainTestScriptServiceHandler.invoke(rq);
        this.maintainTestScriptServiceHandler.finish();
        return rs;
    }
}
