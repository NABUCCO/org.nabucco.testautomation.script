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
import org.nabucco.testautomation.script.facade.message.SubEngineCodeMsg;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainSubEngineCode;

/**
 * MaintainSubEngineCodeImpl<p/>SubEngineCode maintenance service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-08-09
 */
public class MaintainSubEngineCodeImpl extends ServiceSupport implements MaintainSubEngineCode {

    private static final long serialVersionUID = 1L;

    private static final String ID = "MaintainSubEngineCode";

    private EntityManager em;

    private MaintainSubEngineCodeServiceHandler maintainSubEngineCodeServiceHandler;

    /** Constructs a new MaintainSubEngineCodeImpl instance. */
    public MaintainSubEngineCodeImpl() {
        super();
    }

    /** PostConstruct. */
    public void postConstruct() {
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        this.maintainSubEngineCodeServiceHandler = injector
                .inject(MaintainSubEngineCodeServiceHandler.getId());
        if ((this.maintainSubEngineCodeServiceHandler != null)) {
            this.maintainSubEngineCodeServiceHandler.setEntityManager(this.em);
            this.maintainSubEngineCodeServiceHandler.setLogger(super.getLogger());
        }
    }

    /** PreDestroy. */
    public void preDestroy() {
    }

    @Override
    public ServiceResponse<SubEngineCodeMsg> maintainSubEngineCode(
            ServiceRequest<SubEngineCodeMsg> rq) throws MaintainException {
        if ((this.maintainSubEngineCodeServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for maintainSubEngineCode().");
            throw new InjectionException(
                    "No service implementation configured for maintainSubEngineCode().");
        }
        ServiceResponse<SubEngineCodeMsg> rs;
        this.maintainSubEngineCodeServiceHandler.init();
        rs = this.maintainSubEngineCodeServiceHandler.invoke(rq);
        this.maintainSubEngineCodeServiceHandler.finish();
        return rs;
    }
}
