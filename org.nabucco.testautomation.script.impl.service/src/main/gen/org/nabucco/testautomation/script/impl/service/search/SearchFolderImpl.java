/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.impl.service.search;

import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.testautomation.script.facade.message.FolderListMsg;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.facade.message.FolderSearchMsg;
import org.nabucco.testautomation.script.facade.service.search.SearchFolder;

/**
 * SearchFolderImpl<p/>Folder search service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-13
 */
public class SearchFolderImpl extends ServiceSupport implements SearchFolder {

    private static final long serialVersionUID = 1L;

    private static final String ID = "SearchFolder";

    private EntityManager em;

    private GetRootFolderServiceHandler getRootFolderServiceHandler;

    private GetFolderStructureServiceHandler getFolderStructureServiceHandler;

    /** Constructs a new SearchFolderImpl instance. */
    public SearchFolderImpl() {
        super();
    }

    /** PostConstruct. */
    public void postConstruct() {
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        this.getRootFolderServiceHandler = injector.inject(GetRootFolderServiceHandler.getId());
        if ((this.getRootFolderServiceHandler != null)) {
            this.getRootFolderServiceHandler.setEntityManager(this.em);
            this.getRootFolderServiceHandler.setLogger(super.getLogger());
        }
        this.getFolderStructureServiceHandler = injector.inject(GetFolderStructureServiceHandler
                .getId());
        if ((this.getFolderStructureServiceHandler != null)) {
            this.getFolderStructureServiceHandler.setEntityManager(this.em);
            this.getFolderStructureServiceHandler.setLogger(super.getLogger());
        }
    }

    /** PreDestroy. */
    public void preDestroy() {
    }

    @Override
    public ServiceResponse<FolderMsg> getRootFolder(ServiceRequest<FolderSearchMsg> rq)
            throws SearchException {
        if ((this.getRootFolderServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for getRootFolder().");
            throw new InjectionException(
                    "No service implementation configured for getRootFolder().");
        }
        ServiceResponse<FolderMsg> rs;
        this.getRootFolderServiceHandler.init();
        rs = this.getRootFolderServiceHandler.invoke(rq);
        this.getRootFolderServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<FolderListMsg> getFolderStructure(ServiceRequest<FolderSearchMsg> rq)
            throws SearchException {
        if ((this.getFolderStructureServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for getFolderStructure().");
            throw new InjectionException(
                    "No service implementation configured for getFolderStructure().");
        }
        ServiceResponse<FolderListMsg> rs;
        this.getFolderStructureServiceHandler.init();
        rs = this.getFolderStructureServiceHandler.invoke(rq);
        this.getFolderStructureServiceHandler.finish();
        return rs;
    }
}
