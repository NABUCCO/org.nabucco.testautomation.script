/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.component;

import org.nabucco.framework.base.facade.component.Component;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.testautomation.facade.component.TestautomationComponent;
import org.nabucco.testautomation.script.facade.service.export.ExportScript;
import org.nabucco.testautomation.script.facade.service.importing.ImportScript;
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
 * ScriptComponent<p/>TestScript component<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-09
 */
public interface ScriptComponent extends Component {

    final String COMPONENT_NAME = "org.nabucco.testautomation.script";

    /**
     * Getter for the TestautomationComponent.
     *
     * @return the TestautomationComponent.
     * @throws ServiceException
     */
    TestautomationComponent getTestautomationComponent() throws ServiceException;

    /**
     * Getter for the MaintainTestScript.
     *
     * @return the MaintainTestScript.
     * @throws ServiceException
     */
    MaintainTestScript getMaintainTestScript() throws ServiceException;

    /**
     * Getter for the ProduceTestScriptElement.
     *
     * @return the ProduceTestScriptElement.
     * @throws ServiceException
     */
    ProduceTestScriptElement getProduceTestScriptElement() throws ServiceException;

    /**
     * Getter for the SearchTestScript.
     *
     * @return the SearchTestScript.
     * @throws ServiceException
     */
    SearchTestScript getSearchTestScript() throws ServiceException;

    /**
     * Getter for the MaintainMetadata.
     *
     * @return the MaintainMetadata.
     * @throws ServiceException
     */
    MaintainMetadata getMaintainMetadata() throws ServiceException;

    /**
     * Getter for the ProduceMetadata.
     *
     * @return the ProduceMetadata.
     * @throws ServiceException
     */
    ProduceMetadata getProduceMetadata() throws ServiceException;

    /**
     * Getter for the SearchMetadata.
     *
     * @return the SearchMetadata.
     * @throws ServiceException
     */
    SearchMetadata getSearchMetadata() throws ServiceException;

    /**
     * Getter for the SearchFolder.
     *
     * @return the SearchFolder.
     * @throws ServiceException
     */
    SearchFolder getSearchFolder() throws ServiceException;

    /**
     * Getter for the MaintainFolder.
     *
     * @return the MaintainFolder.
     * @throws ServiceException
     */
    MaintainFolder getMaintainFolder() throws ServiceException;

    /**
     * Getter for the MaintainSubEngineCode.
     *
     * @return the MaintainSubEngineCode.
     * @throws ServiceException
     */
    MaintainSubEngineCode getMaintainSubEngineCode() throws ServiceException;

    /**
     * Getter for the SearchSubEngineCode.
     *
     * @return the SearchSubEngineCode.
     * @throws ServiceException
     */
    SearchSubEngineCode getSearchSubEngineCode() throws ServiceException;

    /**
     * Getter for the ProduceFolder.
     *
     * @return the ProduceFolder.
     * @throws ServiceException
     */
    ProduceFolder getProduceFolder() throws ServiceException;

    /**
     * Getter for the ExportScript.
     *
     * @return the ExportScript.
     * @throws ServiceException
     */
    ExportScript getExportScript() throws ServiceException;

    /**
     * Getter for the ImportScript.
     *
     * @return the ImportScript.
     * @throws ServiceException
     */
    ImportScript getImportScript() throws ServiceException;
}
