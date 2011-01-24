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
import org.nabucco.testautomation.script.facade.message.TestScriptListMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptSearchMsg;
import org.nabucco.testautomation.script.facade.service.search.SearchTestScript;

/**
 * SearchTestScriptImpl<p/>TestScript search service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-13
 */
public class SearchTestScriptImpl extends ServiceSupport implements SearchTestScript {

    private static final long serialVersionUID = 1L;

    private static final String ID = "SearchTestScript";

    private EntityManager em;

    private SearchTestScriptServiceHandler searchTestScriptServiceHandler;

    private GetTestScriptServiceHandler getTestScriptServiceHandler;

    /** Constructs a new SearchTestScriptImpl instance. */
    public SearchTestScriptImpl() {
        super();
    }

    /** PostConstruct. */
    public void postConstruct() {
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        this.searchTestScriptServiceHandler = injector.inject(SearchTestScriptServiceHandler
                .getId());
        if ((this.searchTestScriptServiceHandler != null)) {
            this.searchTestScriptServiceHandler.setEntityManager(this.em);
            this.searchTestScriptServiceHandler.setLogger(super.getLogger());
        }
        this.getTestScriptServiceHandler = injector.inject(GetTestScriptServiceHandler.getId());
        if ((this.getTestScriptServiceHandler != null)) {
            this.getTestScriptServiceHandler.setEntityManager(this.em);
            this.getTestScriptServiceHandler.setLogger(super.getLogger());
        }
    }

    /** PreDestroy. */
    public void preDestroy() {
    }

    @Override
    public ServiceResponse<TestScriptListMsg> searchTestScript(
            ServiceRequest<TestScriptSearchMsg> rq) throws SearchException {
        if ((this.searchTestScriptServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for searchTestScript().");
            throw new InjectionException(
                    "No service implementation configured for searchTestScript().");
        }
        ServiceResponse<TestScriptListMsg> rs;
        this.searchTestScriptServiceHandler.init();
        rs = this.searchTestScriptServiceHandler.invoke(rq);
        this.searchTestScriptServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<TestScriptMsg> getTestScript(ServiceRequest<TestScriptSearchMsg> rq)
            throws SearchException {
        if ((this.getTestScriptServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for getTestScript().");
            throw new InjectionException(
                    "No service implementation configured for getTestScript().");
        }
        ServiceResponse<TestScriptMsg> rs;
        this.getTestScriptServiceHandler.init();
        rs = this.getTestScriptServiceHandler.invoke(rq);
        this.getTestScriptServiceHandler.finish();
        return rs;
    }
}
