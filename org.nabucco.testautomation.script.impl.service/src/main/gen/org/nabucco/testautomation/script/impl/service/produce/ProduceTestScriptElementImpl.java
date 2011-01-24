/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.impl.service.produce;

import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.testautomation.script.facade.message.ProduceTestScriptElementMsg;
import org.nabucco.testautomation.script.facade.service.produce.ProduceTestScriptElement;

/**
 * ProduceTestScriptElementImpl<p/>TestScript produce service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-13
 */
public class ProduceTestScriptElementImpl extends ServiceSupport implements
        ProduceTestScriptElement {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ProduceTestScriptElement";

    private ProduceTestScriptElementServiceHandler produceTestScriptElementServiceHandler;

    private ProduceTestScriptElementCloneServiceHandler produceTestScriptElementCloneServiceHandler;

    /** Constructs a new ProduceTestScriptElementImpl instance. */
    public ProduceTestScriptElementImpl() {
        super();
    }

    /** PostConstruct. */
    public void postConstruct() {
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        this.produceTestScriptElementServiceHandler = injector
                .inject(ProduceTestScriptElementServiceHandler.getId());
        if ((this.produceTestScriptElementServiceHandler != null)) {
            this.produceTestScriptElementServiceHandler.setEntityManager(null);
            this.produceTestScriptElementServiceHandler.setLogger(super.getLogger());
        }
        this.produceTestScriptElementCloneServiceHandler = injector
                .inject(ProduceTestScriptElementCloneServiceHandler.getId());
        if ((this.produceTestScriptElementCloneServiceHandler != null)) {
            this.produceTestScriptElementCloneServiceHandler.setEntityManager(null);
            this.produceTestScriptElementCloneServiceHandler.setLogger(super.getLogger());
        }
    }

    /** PreDestroy. */
    public void preDestroy() {
    }

    @Override
    public ServiceResponse<ProduceTestScriptElementMsg> produceTestScriptElement(
            ServiceRequest<ProduceTestScriptElementMsg> rq) throws ProduceException {
        if ((this.produceTestScriptElementServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for produceTestScriptElement().");
            throw new InjectionException(
                    "No service implementation configured for produceTestScriptElement().");
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
            super.getLogger().error(
                    "No service implementation configured for produceTestScriptElementClone().");
            throw new InjectionException(
                    "No service implementation configured for produceTestScriptElementClone().");
        }
        ServiceResponse<ProduceTestScriptElementMsg> rs;
        this.produceTestScriptElementCloneServiceHandler.init();
        rs = this.produceTestScriptElementCloneServiceHandler.invoke(rq);
        this.produceTestScriptElementCloneServiceHandler.finish();
        return rs;
    }
}
