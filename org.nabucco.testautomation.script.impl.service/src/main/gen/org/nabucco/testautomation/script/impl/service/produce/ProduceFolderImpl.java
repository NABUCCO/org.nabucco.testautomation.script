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
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.facade.service.produce.ProduceFolder;

/**
 * ProduceFolderImpl<p/>Folder produce service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-20
 */
public class ProduceFolderImpl extends ServiceSupport implements ProduceFolder {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ProduceFolder";

    private ProduceFolderServiceHandler produceFolderServiceHandler;

    /** Constructs a new ProduceFolderImpl instance. */
    public ProduceFolderImpl() {
        super();
    }

    /** PostConstruct. */
    public void postConstruct() {
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        this.produceFolderServiceHandler = injector.inject(ProduceFolderServiceHandler.getId());
        if ((this.produceFolderServiceHandler != null)) {
            this.produceFolderServiceHandler.setEntityManager(null);
            this.produceFolderServiceHandler.setLogger(super.getLogger());
        }
    }

    /** PreDestroy. */
    public void preDestroy() {
    }

    @Override
    public ServiceResponse<FolderMsg> produceFolder(ServiceRequest<EmptyServiceMessage> rq)
            throws ProduceException {
        if ((this.produceFolderServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceFolder().");
            throw new InjectionException(
                    "No service implementation configured for produceFolder().");
        }
        ServiceResponse<FolderMsg> rs;
        this.produceFolderServiceHandler.init();
        rs = this.produceFolderServiceHandler.invoke(rq);
        this.produceFolderServiceHandler.finish();
        return rs;
    }
}
