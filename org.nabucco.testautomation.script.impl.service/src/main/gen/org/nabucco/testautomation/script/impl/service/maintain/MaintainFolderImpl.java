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
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainFolder;

/**
 * MaintainFolderImpl<p/>Folder maintenance service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-09
 */
public class MaintainFolderImpl extends ServiceSupport implements MaintainFolder {

    private static final long serialVersionUID = 1L;

    private static final String ID = "MaintainFolder";

    private EntityManager em;

    private MaintainFolderServiceHandler maintainFolderServiceHandler;

    /** Constructs a new MaintainFolderImpl instance. */
    public MaintainFolderImpl() {
        super();
    }

    /** PostConstruct. */
    public void postConstruct() {
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        this.maintainFolderServiceHandler = injector.inject(MaintainFolderServiceHandler.getId());
        if ((this.maintainFolderServiceHandler != null)) {
            this.maintainFolderServiceHandler.setEntityManager(this.em);
            this.maintainFolderServiceHandler.setLogger(super.getLogger());
        }
    }

    /** PreDestroy. */
    public void preDestroy() {
    }

    @Override
    public ServiceResponse<FolderMsg> maintainFolder(ServiceRequest<FolderMsg> rq)
            throws MaintainException {
        if ((this.maintainFolderServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainFolder().");
            throw new InjectionException(
                    "No service implementation configured for maintainFolder().");
        }
        ServiceResponse<FolderMsg> rs;
        this.maintainFolderServiceHandler.init();
        rs = this.maintainFolderServiceHandler.invoke(rq);
        this.maintainFolderServiceHandler.finish();
        return rs;
    }
}
