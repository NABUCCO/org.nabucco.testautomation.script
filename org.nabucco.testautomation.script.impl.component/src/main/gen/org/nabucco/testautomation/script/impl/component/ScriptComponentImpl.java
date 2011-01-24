/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.impl.component;

import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.service.componentrelation.ComponentRelationService;
import org.nabucco.framework.base.impl.component.ComponentSupport;
import org.nabucco.testautomation.facade.component.TestautomationComponent;
import org.nabucco.testautomation.script.facade.component.ScriptComponent;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainFolder;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainMetadata;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainSubEngineCode;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainTestScript;
import org.nabucco.testautomation.script.facade.service.produce.ProduceFolder;
import org.nabucco.testautomation.script.facade.service.produce.ProduceMetadata;
import org.nabucco.testautomation.script.facade.service.produce.ProduceTestScriptElement;
import org.nabucco.testautomation.script.facade.service.search.SearchFolder;
import org.nabucco.testautomation.script.facade.service.search.SearchMetadata;
import org.nabucco.testautomation.script.facade.service.search.SearchSubEngineCode;
import org.nabucco.testautomation.script.facade.service.search.SearchTestScript;

/**
 * ScriptComponentImpl<p/>TestScript component<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-09
 */
public class ScriptComponentImpl extends ComponentSupport implements ScriptComponent {

    private static final long serialVersionUID = 1L;

    private ComponentRelationService componentRelationService;

    private TestautomationComponent testautomationComponent;

    private MaintainTestScript maintainTestScript;

    private ProduceTestScriptElement produceTestScriptElement;

    private SearchTestScript searchTestScript;

    private MaintainMetadata maintainMetadata;

    private ProduceMetadata produceMetadata;

    private SearchMetadata searchMetadata;

    private SearchFolder searchFolder;

    private MaintainFolder maintainFolder;

    private MaintainSubEngineCode maintainSubEngineCode;

    private SearchSubEngineCode searchSubEngineCode;

    private ProduceFolder produceFolder;

    /** Constructs a new ScriptComponentImpl instance. */
    public ScriptComponentImpl() {
        super();
    }

    @Override
    public ComponentRelationService getComponentRelationService() throws ServiceException {
        return this.componentRelationService;
    }

    /**
     * Getter for the TestautomationComponent.
     *
     * @return the TestautomationComponent.
     */
    public TestautomationComponent getTestautomationComponent() {
        return this.testautomationComponent;
    }

    /**
     * Getter for the MaintainTestScript.
     *
     * @return the MaintainTestScript.
     */
    public MaintainTestScript getMaintainTestScript() {
        return this.maintainTestScript;
    }

    /**
     * Getter for the ProduceTestScriptElement.
     *
     * @return the ProduceTestScriptElement.
     */
    public ProduceTestScriptElement getProduceTestScriptElement() {
        return this.produceTestScriptElement;
    }

    /**
     * Getter for the SearchTestScript.
     *
     * @return the SearchTestScript.
     */
    public SearchTestScript getSearchTestScript() {
        return this.searchTestScript;
    }

    /**
     * Getter for the MaintainMetadata.
     *
     * @return the MaintainMetadata.
     */
    public MaintainMetadata getMaintainMetadata() {
        return this.maintainMetadata;
    }

    /**
     * Getter for the ProduceMetadata.
     *
     * @return the ProduceMetadata.
     */
    public ProduceMetadata getProduceMetadata() {
        return this.produceMetadata;
    }

    /**
     * Getter for the SearchMetadata.
     *
     * @return the SearchMetadata.
     */
    public SearchMetadata getSearchMetadata() {
        return this.searchMetadata;
    }

    /**
     * Getter for the SearchFolder.
     *
     * @return the SearchFolder.
     */
    public SearchFolder getSearchFolder() {
        return this.searchFolder;
    }

    /**
     * Getter for the MaintainFolder.
     *
     * @return the MaintainFolder.
     */
    public MaintainFolder getMaintainFolder() {
        return this.maintainFolder;
    }

    /**
     * Getter for the MaintainSubEngineCode.
     *
     * @return the MaintainSubEngineCode.
     */
    public MaintainSubEngineCode getMaintainSubEngineCode() {
        return this.maintainSubEngineCode;
    }

    /**
     * Getter for the SearchSubEngineCode.
     *
     * @return the SearchSubEngineCode.
     */
    public SearchSubEngineCode getSearchSubEngineCode() {
        return this.searchSubEngineCode;
    }

    /**
     * Getter for the ProduceFolder.
     *
     * @return the ProduceFolder.
     */
    public ProduceFolder getProduceFolder() {
        return this.produceFolder;
    }
}
