/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.impl.service.importing;

import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.importing.ImportException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.message.importing.ImportRq;
import org.nabucco.framework.base.facade.message.importing.ImportRs;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.testautomation.script.facade.service.importing.ImportScript;

/**
 * ImportScriptImpl<p/>Service to import Script<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2011-02-14
 */
public class ImportScriptImpl extends ServiceSupport implements ImportScript {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ImportScript";

    private EntityManager entityManager;

    private ImportScriptServiceHandler importScriptServiceHandler;

    /** Constructs a new ImportScriptImpl instance. */
    public ImportScriptImpl() {
        super();
    }

    /** PostConstruct. */
    public void postConstruct() {
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        this.importScriptServiceHandler = injector.inject(ImportScriptServiceHandler.getId());
        if ((this.importScriptServiceHandler != null)) {
            this.importScriptServiceHandler.setEntityManager(this.entityManager);
            this.importScriptServiceHandler.setLogger(super.getLogger());
        }
    }

    /** PreDestroy. */
    public void preDestroy() {
    }

    @Override
    public ServiceResponse<ImportRs> importScript(ServiceRequest<ImportRq> rq)
            throws ImportException {
        if ((this.importScriptServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for importScript().");
            throw new InjectionException("No service implementation configured for importScript().");
        }
        ServiceResponse<ImportRs> rs;
        this.importScriptServiceHandler.init();
        rs = this.importScriptServiceHandler.invoke(rq);
        this.importScriptServiceHandler.finish();
        return rs;
    }
}
