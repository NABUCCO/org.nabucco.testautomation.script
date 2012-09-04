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
package org.nabucco.testautomation.script.impl.service.resolve;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.MetadataSearchMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptSearchMsg;
import org.nabucco.testautomation.script.facade.service.resolve.ResolveScript;

/**
 * ResolveScriptImpl<p/>Script resolve service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-13
 */
public class ResolveScriptImpl extends ServiceSupport implements ResolveScript {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ResolveScript";

    private static Map<String, String[]> ASPECTS;

    private ResolveTestScriptServiceHandler resolveTestScriptServiceHandler;

    private ResolveMetadataServiceHandler resolveMetadataServiceHandler;

    private ResolveChildrenServiceHandler resolveChildrenServiceHandler;

    private ResolveParentsServiceHandler resolveParentsServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new ResolveScriptImpl instance. */
    public ResolveScriptImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.resolveTestScriptServiceHandler = injector.inject(ResolveTestScriptServiceHandler.getId());
        if ((this.resolveTestScriptServiceHandler != null)) {
            this.resolveTestScriptServiceHandler.setPersistenceManager(persistenceManager);
            this.resolveTestScriptServiceHandler.setLogger(super.getLogger());
        }
        this.resolveMetadataServiceHandler = injector.inject(ResolveMetadataServiceHandler.getId());
        if ((this.resolveMetadataServiceHandler != null)) {
            this.resolveMetadataServiceHandler.setPersistenceManager(persistenceManager);
            this.resolveMetadataServiceHandler.setLogger(super.getLogger());
        }
        this.resolveChildrenServiceHandler = injector.inject(ResolveChildrenServiceHandler.getId());
        if ((this.resolveChildrenServiceHandler != null)) {
            this.resolveChildrenServiceHandler.setPersistenceManager(persistenceManager);
            this.resolveChildrenServiceHandler.setLogger(super.getLogger());
        }
        this.resolveParentsServiceHandler = injector.inject(ResolveParentsServiceHandler.getId());
        if ((this.resolveParentsServiceHandler != null)) {
            this.resolveParentsServiceHandler.setPersistenceManager(persistenceManager);
            this.resolveParentsServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("resolveTestScript", NO_ASPECTS);
            ASPECTS.put("resolveMetadata", new String[] { "org.nabucco.aspect.resolving" });
            ASPECTS.put("resolveChildren", new String[] { "org.nabucco.aspect.resolving" });
            ASPECTS.put("resolveParents", new String[] { "org.nabucco.aspect.resolving" });
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<TestScriptMsg> resolveTestScript(ServiceRequest<TestScriptSearchMsg> rq)
            throws ResolveException {
        if ((this.resolveTestScriptServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for resolveTestScript().");
            throw new InjectionException("No service implementation configured for resolveTestScript().");
        }
        ServiceResponse<TestScriptMsg> rs;
        this.resolveTestScriptServiceHandler.init();
        rs = this.resolveTestScriptServiceHandler.invoke(rq);
        this.resolveTestScriptServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<MetadataMsg> resolveMetadata(ServiceRequest<MetadataSearchMsg> rq) throws ResolveException {
        if ((this.resolveMetadataServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for resolveMetadata().");
            throw new InjectionException("No service implementation configured for resolveMetadata().");
        }
        ServiceResponse<MetadataMsg> rs;
        this.resolveMetadataServiceHandler.init();
        rs = this.resolveMetadataServiceHandler.invoke(rq);
        this.resolveMetadataServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<MetadataMsg> resolveChildren(ServiceRequest<MetadataMsg> rq) throws ResolveException {
        if ((this.resolveChildrenServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for resolveChildren().");
            throw new InjectionException("No service implementation configured for resolveChildren().");
        }
        ServiceResponse<MetadataMsg> rs;
        this.resolveChildrenServiceHandler.init();
        rs = this.resolveChildrenServiceHandler.invoke(rq);
        this.resolveChildrenServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<MetadataMsg> resolveParents(ServiceRequest<MetadataMsg> rq) throws ResolveException {
        if ((this.resolveParentsServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for resolveParents().");
            throw new InjectionException("No service implementation configured for resolveParents().");
        }
        ServiceResponse<MetadataMsg> rs;
        this.resolveParentsServiceHandler.init();
        rs = this.resolveParentsServiceHandler.invoke(rq);
        this.resolveParentsServiceHandler.finish();
        return rs;
    }
}
