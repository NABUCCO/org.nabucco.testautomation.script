/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.impl.service.produce;

import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.testautomation.script.facade.message.MetadataLabelMsg;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.service.produce.ProduceMetadata;

/**
 * ProduceMetadataImpl<p/>Metadata produce service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-20
 */
public class ProduceMetadataImpl extends ServiceSupport implements ProduceMetadata {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ProduceMetadata";

    private ProduceMetadataServiceHandler produceMetadataServiceHandler;

    private ProduceMetadataCloneServiceHandler produceMetadataCloneServiceHandler;

    private ProduceMetadataLabelServiceHandler produceMetadataLabelServiceHandler;

    private ProduceMetadataLabelCloneServiceHandler produceMetadataLabelCloneServiceHandler;

    /** Constructs a new ProduceMetadataImpl instance. */
    public ProduceMetadataImpl() {
        super();
    }

    /** PostConstruct. */
    public void postConstruct() {
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        this.produceMetadataServiceHandler = injector.inject(ProduceMetadataServiceHandler.getId());
        if ((this.produceMetadataServiceHandler != null)) {
            this.produceMetadataServiceHandler.setEntityManager(null);
            this.produceMetadataServiceHandler.setLogger(super.getLogger());
        }
        this.produceMetadataCloneServiceHandler = injector
                .inject(ProduceMetadataCloneServiceHandler.getId());
        if ((this.produceMetadataCloneServiceHandler != null)) {
            this.produceMetadataCloneServiceHandler.setEntityManager(null);
            this.produceMetadataCloneServiceHandler.setLogger(super.getLogger());
        }
        this.produceMetadataLabelServiceHandler = injector
                .inject(ProduceMetadataLabelServiceHandler.getId());
        if ((this.produceMetadataLabelServiceHandler != null)) {
            this.produceMetadataLabelServiceHandler.setEntityManager(null);
            this.produceMetadataLabelServiceHandler.setLogger(super.getLogger());
        }
        this.produceMetadataLabelCloneServiceHandler = injector
                .inject(ProduceMetadataLabelCloneServiceHandler.getId());
        if ((this.produceMetadataLabelCloneServiceHandler != null)) {
            this.produceMetadataLabelCloneServiceHandler.setEntityManager(null);
            this.produceMetadataLabelCloneServiceHandler.setLogger(super.getLogger());
        }
    }

    /** PreDestroy. */
    public void preDestroy() {
    }

    @Override
    public ServiceResponse<MetadataMsg> produceMetadata(ServiceRequest<EmptyServiceMessage> rq)
            throws ProduceException {
        if ((this.produceMetadataServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceMetadata().");
            throw new InjectionException(
                    "No service implementation configured for produceMetadata().");
        }
        ServiceResponse<MetadataMsg> rs;
        this.produceMetadataServiceHandler.init();
        rs = this.produceMetadataServiceHandler.invoke(rq);
        this.produceMetadataServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<MetadataMsg> produceMetadataClone(ServiceRequest<MetadataMsg> rq)
            throws ProduceException {
        if ((this.produceMetadataCloneServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for produceMetadataClone().");
            throw new InjectionException(
                    "No service implementation configured for produceMetadataClone().");
        }
        ServiceResponse<MetadataMsg> rs;
        this.produceMetadataCloneServiceHandler.init();
        rs = this.produceMetadataCloneServiceHandler.invoke(rq);
        this.produceMetadataCloneServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<MetadataLabelMsg> produceMetadataLabel(
            ServiceRequest<EmptyServiceMessage> rq) throws ProduceException {
        if ((this.produceMetadataLabelServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for produceMetadataLabel().");
            throw new InjectionException(
                    "No service implementation configured for produceMetadataLabel().");
        }
        ServiceResponse<MetadataLabelMsg> rs;
        this.produceMetadataLabelServiceHandler.init();
        rs = this.produceMetadataLabelServiceHandler.invoke(rq);
        this.produceMetadataLabelServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<MetadataLabelMsg> produceMetadataLabelClone(
            ServiceRequest<MetadataLabelMsg> rq) throws ProduceException {
        if ((this.produceMetadataLabelCloneServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for produceMetadataLabelClone().");
            throw new InjectionException(
                    "No service implementation configured for produceMetadataLabelClone().");
        }
        ServiceResponse<MetadataLabelMsg> rs;
        this.produceMetadataLabelCloneServiceHandler.init();
        rs = this.produceMetadataLabelCloneServiceHandler.invoke(rq);
        this.produceMetadataLabelCloneServiceHandler.finish();
        return rs;
    }
}
