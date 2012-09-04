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
package org.nabucco.testautomation.script.impl.service.produce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.facade.message.MetadataLabelMsg;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.ProduceTestScriptElementMsg;
import org.nabucco.testautomation.script.facade.service.produce.ProduceScript;

/**
 * ProduceScriptImpl<p/>Folder produce service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-20
 */
public class ProduceScriptImpl extends ServiceSupport implements ProduceScript {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ProduceScript";

    private static Map<String, String[]> ASPECTS;

    private ProduceFolderServiceHandler produceFolderServiceHandler;

    private ProduceMetadataServiceHandler produceMetadataServiceHandler;

    private ProduceMetadataCloneServiceHandler produceMetadataCloneServiceHandler;

    private ProduceMetadataLabelServiceHandler produceMetadataLabelServiceHandler;

    private ProduceMetadataLabelCloneServiceHandler produceMetadataLabelCloneServiceHandler;

    private ProduceTestScriptElementServiceHandler produceTestScriptElementServiceHandler;

    private ProduceTestScriptElementCloneServiceHandler produceTestScriptElementCloneServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new ProduceScriptImpl instance. */
    public ProduceScriptImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.produceFolderServiceHandler = injector.inject(ProduceFolderServiceHandler.getId());
        if ((this.produceFolderServiceHandler != null)) {
            this.produceFolderServiceHandler.setPersistenceManager(persistenceManager);
            this.produceFolderServiceHandler.setLogger(super.getLogger());
        }
        this.produceMetadataServiceHandler = injector.inject(ProduceMetadataServiceHandler.getId());
        if ((this.produceMetadataServiceHandler != null)) {
            this.produceMetadataServiceHandler.setPersistenceManager(persistenceManager);
            this.produceMetadataServiceHandler.setLogger(super.getLogger());
        }
        this.produceMetadataCloneServiceHandler = injector.inject(ProduceMetadataCloneServiceHandler.getId());
        if ((this.produceMetadataCloneServiceHandler != null)) {
            this.produceMetadataCloneServiceHandler.setPersistenceManager(persistenceManager);
            this.produceMetadataCloneServiceHandler.setLogger(super.getLogger());
        }
        this.produceMetadataLabelServiceHandler = injector.inject(ProduceMetadataLabelServiceHandler.getId());
        if ((this.produceMetadataLabelServiceHandler != null)) {
            this.produceMetadataLabelServiceHandler.setPersistenceManager(persistenceManager);
            this.produceMetadataLabelServiceHandler.setLogger(super.getLogger());
        }
        this.produceMetadataLabelCloneServiceHandler = injector.inject(ProduceMetadataLabelCloneServiceHandler.getId());
        if ((this.produceMetadataLabelCloneServiceHandler != null)) {
            this.produceMetadataLabelCloneServiceHandler.setPersistenceManager(persistenceManager);
            this.produceMetadataLabelCloneServiceHandler.setLogger(super.getLogger());
        }
        this.produceTestScriptElementServiceHandler = injector.inject(ProduceTestScriptElementServiceHandler.getId());
        if ((this.produceTestScriptElementServiceHandler != null)) {
            this.produceTestScriptElementServiceHandler.setPersistenceManager(persistenceManager);
            this.produceTestScriptElementServiceHandler.setLogger(super.getLogger());
        }
        this.produceTestScriptElementCloneServiceHandler = injector.inject(ProduceTestScriptElementCloneServiceHandler
                .getId());
        if ((this.produceTestScriptElementCloneServiceHandler != null)) {
            this.produceTestScriptElementCloneServiceHandler.setPersistenceManager(persistenceManager);
            this.produceTestScriptElementCloneServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("produceFolder", NO_ASPECTS);
            ASPECTS.put("produceMetadata", NO_ASPECTS);
            ASPECTS.put("produceMetadataClone", NO_ASPECTS);
            ASPECTS.put("produceMetadataLabel", NO_ASPECTS);
            ASPECTS.put("produceMetadataLabelClone", NO_ASPECTS);
            ASPECTS.put("produceTestScriptElement", NO_ASPECTS);
            ASPECTS.put("produceTestScriptElementClone", NO_ASPECTS);
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<FolderMsg> produceFolder(ServiceRequest<EmptyServiceMessage> rq) throws ProduceException {
        if ((this.produceFolderServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceFolder().");
            throw new InjectionException("No service implementation configured for produceFolder().");
        }
        ServiceResponse<FolderMsg> rs;
        this.produceFolderServiceHandler.init();
        rs = this.produceFolderServiceHandler.invoke(rq);
        this.produceFolderServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<MetadataMsg> produceMetadata(ServiceRequest<EmptyServiceMessage> rq) throws ProduceException {
        if ((this.produceMetadataServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceMetadata().");
            throw new InjectionException("No service implementation configured for produceMetadata().");
        }
        ServiceResponse<MetadataMsg> rs;
        this.produceMetadataServiceHandler.init();
        rs = this.produceMetadataServiceHandler.invoke(rq);
        this.produceMetadataServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<MetadataMsg> produceMetadataClone(ServiceRequest<MetadataMsg> rq) throws ProduceException {
        if ((this.produceMetadataCloneServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceMetadataClone().");
            throw new InjectionException("No service implementation configured for produceMetadataClone().");
        }
        ServiceResponse<MetadataMsg> rs;
        this.produceMetadataCloneServiceHandler.init();
        rs = this.produceMetadataCloneServiceHandler.invoke(rq);
        this.produceMetadataCloneServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<MetadataLabelMsg> produceMetadataLabel(ServiceRequest<EmptyServiceMessage> rq)
            throws ProduceException {
        if ((this.produceMetadataLabelServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceMetadataLabel().");
            throw new InjectionException("No service implementation configured for produceMetadataLabel().");
        }
        ServiceResponse<MetadataLabelMsg> rs;
        this.produceMetadataLabelServiceHandler.init();
        rs = this.produceMetadataLabelServiceHandler.invoke(rq);
        this.produceMetadataLabelServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<MetadataLabelMsg> produceMetadataLabelClone(ServiceRequest<MetadataLabelMsg> rq)
            throws ProduceException {
        if ((this.produceMetadataLabelCloneServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceMetadataLabelClone().");
            throw new InjectionException("No service implementation configured for produceMetadataLabelClone().");
        }
        ServiceResponse<MetadataLabelMsg> rs;
        this.produceMetadataLabelCloneServiceHandler.init();
        rs = this.produceMetadataLabelCloneServiceHandler.invoke(rq);
        this.produceMetadataLabelCloneServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ProduceTestScriptElementMsg> produceTestScriptElement(
            ServiceRequest<ProduceTestScriptElementMsg> rq) throws ProduceException {
        if ((this.produceTestScriptElementServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceTestScriptElement().");
            throw new InjectionException("No service implementation configured for produceTestScriptElement().");
        }
        ServiceResponse<ProduceTestScriptElementMsg> rs;
        this.produceTestScriptElementServiceHandler.init();
        rs = this.produceTestScriptElementServiceHandler.invoke(rq);
        this.produceTestScriptElementServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ProduceTestScriptElementMsg> produceTestScriptElementClone(
            ServiceRequest<ProduceTestScriptElementMsg> rq) throws ProduceException {
        if ((this.produceTestScriptElementCloneServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceTestScriptElementClone().");
            throw new InjectionException("No service implementation configured for produceTestScriptElementClone().");
        }
        ServiceResponse<ProduceTestScriptElementMsg> rs;
        this.produceTestScriptElementCloneServiceHandler.init();
        rs = this.produceTestScriptElementCloneServiceHandler.invoke(rq);
        this.produceTestScriptElementCloneServiceHandler.finish();
        return rs;
    }
}
