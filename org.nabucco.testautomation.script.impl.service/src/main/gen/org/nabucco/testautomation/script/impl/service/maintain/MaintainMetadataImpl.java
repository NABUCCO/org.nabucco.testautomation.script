/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.impl.service.maintain;

import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainMetadata;

/**
 * MaintainMetadataImpl<p/>Metadata maintenance service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-19
 */
public class MaintainMetadataImpl extends ServiceSupport implements MaintainMetadata {

    private static final long serialVersionUID = 1L;

    private static final String ID = "MaintainMetadata";

    private EntityManager em;

    private MaintainMetadataServiceHandler maintainMetadataServiceHandler;

    /** Constructs a new MaintainMetadataImpl instance. */
    public MaintainMetadataImpl() {
        super();
    }

    /** PostConstruct. */
    public void postConstruct() {
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        this.maintainMetadataServiceHandler = injector.inject(MaintainMetadataServiceHandler
                .getId());
        if ((this.maintainMetadataServiceHandler != null)) {
            this.maintainMetadataServiceHandler.setEntityManager(this.em);
            this.maintainMetadataServiceHandler.setLogger(super.getLogger());
        }
    }

    /** PreDestroy. */
    public void preDestroy() {
    }

    @Override
    public ServiceResponse<MetadataMsg> maintainMetadata(ServiceRequest<MetadataMsg> rq)
            throws MaintainException {
        if ((this.maintainMetadataServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainMetadata().");
            throw new InjectionException(
                    "No service implementation configured for maintainMetadata().");
        }
        ServiceResponse<MetadataMsg> rs;
        this.maintainMetadataServiceHandler.init();
        rs = this.maintainMetadataServiceHandler.invoke(rq);
        this.maintainMetadataServiceHandler.finish();
        return rs;
    }
}
