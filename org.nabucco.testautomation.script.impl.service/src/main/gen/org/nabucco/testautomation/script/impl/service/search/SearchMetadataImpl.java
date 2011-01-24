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
import org.nabucco.testautomation.script.facade.message.MetadataListMsg;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.MetadataSearchMsg;
import org.nabucco.testautomation.script.facade.service.search.SearchMetadata;

/**
 * SearchMetadataImpl<p/>Metadata search service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-20
 */
public class SearchMetadataImpl extends ServiceSupport implements SearchMetadata {

    private static final long serialVersionUID = 1L;

    private static final String ID = "SearchMetadata";

    private EntityManager em;

    private SearchMetadataServiceHandler searchMetadataServiceHandler;

    private GetMetadataServiceHandler getMetadataServiceHandler;

    private GetChildrenServiceHandler getChildrenServiceHandler;

    private GetParentsServiceHandler getParentsServiceHandler;

    /** Constructs a new SearchMetadataImpl instance. */
    public SearchMetadataImpl() {
        super();
    }

    /** PostConstruct. */
    public void postConstruct() {
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        this.searchMetadataServiceHandler = injector.inject(SearchMetadataServiceHandler.getId());
        if ((this.searchMetadataServiceHandler != null)) {
            this.searchMetadataServiceHandler.setEntityManager(this.em);
            this.searchMetadataServiceHandler.setLogger(super.getLogger());
        }
        this.getMetadataServiceHandler = injector.inject(GetMetadataServiceHandler.getId());
        if ((this.getMetadataServiceHandler != null)) {
            this.getMetadataServiceHandler.setEntityManager(this.em);
            this.getMetadataServiceHandler.setLogger(super.getLogger());
        }
        this.getChildrenServiceHandler = injector.inject(GetChildrenServiceHandler.getId());
        if ((this.getChildrenServiceHandler != null)) {
            this.getChildrenServiceHandler.setEntityManager(this.em);
            this.getChildrenServiceHandler.setLogger(super.getLogger());
        }
        this.getParentsServiceHandler = injector.inject(GetParentsServiceHandler.getId());
        if ((this.getParentsServiceHandler != null)) {
            this.getParentsServiceHandler.setEntityManager(this.em);
            this.getParentsServiceHandler.setLogger(super.getLogger());
        }
    }

    /** PreDestroy. */
    public void preDestroy() {
    }

    @Override
    public ServiceResponse<MetadataListMsg> searchMetadata(ServiceRequest<MetadataSearchMsg> rq)
            throws SearchException {
        if ((this.searchMetadataServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for searchMetadata().");
            throw new InjectionException(
                    "No service implementation configured for searchMetadata().");
        }
        ServiceResponse<MetadataListMsg> rs;
        this.searchMetadataServiceHandler.init();
        rs = this.searchMetadataServiceHandler.invoke(rq);
        this.searchMetadataServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<MetadataMsg> getMetadata(ServiceRequest<MetadataSearchMsg> rq)
            throws SearchException {
        if ((this.getMetadataServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for getMetadata().");
            throw new InjectionException("No service implementation configured for getMetadata().");
        }
        ServiceResponse<MetadataMsg> rs;
        this.getMetadataServiceHandler.init();
        rs = this.getMetadataServiceHandler.invoke(rq);
        this.getMetadataServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<MetadataMsg> getChildren(ServiceRequest<MetadataMsg> rq)
            throws SearchException {
        if ((this.getChildrenServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for getChildren().");
            throw new InjectionException("No service implementation configured for getChildren().");
        }
        ServiceResponse<MetadataMsg> rs;
        this.getChildrenServiceHandler.init();
        rs = this.getChildrenServiceHandler.invoke(rq);
        this.getChildrenServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<MetadataMsg> getParents(ServiceRequest<MetadataMsg> rq)
            throws SearchException {
        if ((this.getParentsServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for getParents().");
            throw new InjectionException("No service implementation configured for getParents().");
        }
        ServiceResponse<MetadataMsg> rs;
        this.getParentsServiceHandler.init();
        rs = this.getParentsServiceHandler.invoke(rq);
        this.getParentsServiceHandler.finish();
        return rs;
    }
}
