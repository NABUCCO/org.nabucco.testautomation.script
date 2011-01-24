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
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainTestScript;

/**
 * MaintainTestScriptImpl<p/>TestScript maintenance service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-09
 */
public class MaintainTestScriptImpl extends ServiceSupport implements MaintainTestScript {

    private static final long serialVersionUID = 1L;

    private static final String ID = "MaintainTestScript";

    private EntityManager em;

    private MaintainTestScriptServiceHandler maintainTestScriptServiceHandler;

    /** Constructs a new MaintainTestScriptImpl instance. */
    public MaintainTestScriptImpl() {
        super();
    }

    /** PostConstruct. */
    public void postConstruct() {
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        this.maintainTestScriptServiceHandler = injector.inject(MaintainTestScriptServiceHandler
                .getId());
        if ((this.maintainTestScriptServiceHandler != null)) {
            this.maintainTestScriptServiceHandler.setEntityManager(this.em);
            this.maintainTestScriptServiceHandler.setLogger(super.getLogger());
        }
    }

    /** PreDestroy. */
    public void preDestroy() {
    }

    @Override
    public ServiceResponse<TestScriptMsg> maintainTestScript(ServiceRequest<TestScriptMsg> rq)
            throws MaintainException {
        if ((this.maintainTestScriptServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for maintainTestScript().");
            throw new InjectionException(
                    "No service implementation configured for maintainTestScript().");
        }
        ServiceResponse<TestScriptMsg> rs;
        this.maintainTestScriptServiceHandler.init();
        rs = this.maintainTestScriptServiceHandler.invoke(rq);
        this.maintainTestScriptServiceHandler.finish();
        return rs;
    }
}
