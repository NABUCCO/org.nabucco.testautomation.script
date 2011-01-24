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
import org.nabucco.testautomation.script.facade.message.SubEngineCodeListMsg;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeSearchMsg;
import org.nabucco.testautomation.script.facade.service.search.SearchSubEngineCode;

/**
 * SearchSubEngineCodeImpl<p/>SubEngineCode search service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-13
 */
public class SearchSubEngineCodeImpl extends ServiceSupport implements SearchSubEngineCode {

    private static final long serialVersionUID = 1L;

    private static final String ID = "SearchSubEngineCode";

    private EntityManager em;

    private SearchSubEngineCodeServiceHandler searchSubEngineCodeServiceHandler;

    /** Constructs a new SearchSubEngineCodeImpl instance. */
    public SearchSubEngineCodeImpl() {
        super();
    }

    /** PostConstruct. */
    public void postConstruct() {
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        this.searchSubEngineCodeServiceHandler = injector.inject(SearchSubEngineCodeServiceHandler
                .getId());
        if ((this.searchSubEngineCodeServiceHandler != null)) {
            this.searchSubEngineCodeServiceHandler.setEntityManager(this.em);
            this.searchSubEngineCodeServiceHandler.setLogger(super.getLogger());
        }
    }

    /** PreDestroy. */
    public void preDestroy() {
    }

    @Override
    public ServiceResponse<SubEngineCodeListMsg> searchSubEngineCode(
            ServiceRequest<SubEngineCodeSearchMsg> rq) throws SearchException {
        if ((this.searchSubEngineCodeServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for searchSubEngineCode().");
            throw new InjectionException(
                    "No service implementation configured for searchSubEngineCode().");
        }
        ServiceResponse<SubEngineCodeListMsg> rs;
        this.searchSubEngineCodeServiceHandler.init();
        rs = this.searchSubEngineCodeServiceHandler.invoke(rq);
        this.searchSubEngineCodeServiceHandler.finish();
        return rs;
    }
}
