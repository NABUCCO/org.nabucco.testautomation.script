/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.web.communication;

import org.nabucco.framework.base.facade.component.connection.Connection;
import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.component.connection.ConnectionFactory;
import org.nabucco.framework.base.facade.component.connection.ConnectionSpecification;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.testautomation.script.facade.component.ScriptComponent;
import org.nabucco.testautomation.script.facade.component.ScriptComponentLocator;
import org.nabucco.testautomation.script.ui.web.communication.export.ExportScriptDelegate;
import org.nabucco.testautomation.script.ui.web.communication.importing.ImportScriptDelegate;
import org.nabucco.testautomation.script.ui.web.communication.maintain.MaintainFolderDelegate;
import org.nabucco.testautomation.script.ui.web.communication.maintain.MaintainMetadataDelegate;
import org.nabucco.testautomation.script.ui.web.communication.maintain.MaintainSubEngineCodeDelegate;
import org.nabucco.testautomation.script.ui.web.communication.maintain.MaintainTestScriptDelegate;
import org.nabucco.testautomation.script.ui.web.communication.produce.ProduceFolderDelegate;
import org.nabucco.testautomation.script.ui.web.communication.produce.ProduceMetadataDelegate;
import org.nabucco.testautomation.script.ui.web.communication.produce.ProduceTestScriptElementDelegate;
import org.nabucco.testautomation.script.ui.web.communication.search.SearchFolderDelegate;
import org.nabucco.testautomation.script.ui.web.communication.search.SearchMetadataDelegate;
import org.nabucco.testautomation.script.ui.web.communication.search.SearchSubEngineCodeDelegate;
import org.nabucco.testautomation.script.ui.web.communication.search.SearchTestScriptDelegate;

/**
 * ServiceDelegateFactoryTemplate<p/>TestScript component<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-09
 */
public class ScriptComponentServiceDelegateFactory {

    private static ScriptComponentServiceDelegateFactory instance = new ScriptComponentServiceDelegateFactory();

    private ScriptComponent component;

    private MaintainTestScriptDelegate maintainTestScriptDelegate;

    private ProduceTestScriptElementDelegate produceTestScriptElementDelegate;

    private SearchTestScriptDelegate searchTestScriptDelegate;

    private MaintainMetadataDelegate maintainMetadataDelegate;

    private ProduceMetadataDelegate produceMetadataDelegate;

    private SearchMetadataDelegate searchMetadataDelegate;

    private SearchFolderDelegate searchFolderDelegate;

    private MaintainFolderDelegate maintainFolderDelegate;

    private MaintainSubEngineCodeDelegate maintainSubEngineCodeDelegate;

    private SearchSubEngineCodeDelegate searchSubEngineCodeDelegate;

    private ProduceFolderDelegate produceFolderDelegate;

    private ExportScriptDelegate exportScriptDelegate;

    private ImportScriptDelegate importScriptDelegate;

    /** Constructs a new ScriptComponentServiceDelegateFactory instance. */
    private ScriptComponentServiceDelegateFactory() {
        super();
    }

    /**
     * Getter for the Component.
     *
     * @return the ScriptComponent.
     * @throws ConnectionException
     */
    private ScriptComponent getComponent() throws ConnectionException {
        if ((this.component == null)) {
            this.initComponent();
        }
        return this.component;
    }

    /**
     * InitComponent.
     *
     * @throws ConnectionException
     */
    private void initComponent() throws ConnectionException {
        ConnectionSpecification specification = ConnectionSpecification.getCurrentSpecification();
        Connection connection = ConnectionFactory.getInstance().createConnection(specification);
        this.component = ScriptComponentLocator.getInstance().getComponent(connection);
    }

    /**
     * Getter for the MaintainTestScript.
     *
     * @return the MaintainTestScriptDelegate.
     * @throws ClientException
     */
    public MaintainTestScriptDelegate getMaintainTestScript() throws ClientException {
        try {
            if ((this.maintainTestScriptDelegate == null)) {
                this.maintainTestScriptDelegate = new MaintainTestScriptDelegate(this
                        .getComponent().getMaintainTestScript());
            }
            return this.maintainTestScriptDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: MaintainTestScript", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the ProduceTestScriptElement.
     *
     * @return the ProduceTestScriptElementDelegate.
     * @throws ClientException
     */
    public ProduceTestScriptElementDelegate getProduceTestScriptElement() throws ClientException {
        try {
            if ((this.produceTestScriptElementDelegate == null)) {
                this.produceTestScriptElementDelegate = new ProduceTestScriptElementDelegate(this
                        .getComponent().getProduceTestScriptElement());
            }
            return this.produceTestScriptElementDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: ProduceTestScriptElement", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the SearchTestScript.
     *
     * @return the SearchTestScriptDelegate.
     * @throws ClientException
     */
    public SearchTestScriptDelegate getSearchTestScript() throws ClientException {
        try {
            if ((this.searchTestScriptDelegate == null)) {
                this.searchTestScriptDelegate = new SearchTestScriptDelegate(this.getComponent()
                        .getSearchTestScript());
            }
            return this.searchTestScriptDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: SearchTestScript", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the MaintainMetadata.
     *
     * @return the MaintainMetadataDelegate.
     * @throws ClientException
     */
    public MaintainMetadataDelegate getMaintainMetadata() throws ClientException {
        try {
            if ((this.maintainMetadataDelegate == null)) {
                this.maintainMetadataDelegate = new MaintainMetadataDelegate(this.getComponent()
                        .getMaintainMetadata());
            }
            return this.maintainMetadataDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: MaintainMetadata", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the ProduceMetadata.
     *
     * @return the ProduceMetadataDelegate.
     * @throws ClientException
     */
    public ProduceMetadataDelegate getProduceMetadata() throws ClientException {
        try {
            if ((this.produceMetadataDelegate == null)) {
                this.produceMetadataDelegate = new ProduceMetadataDelegate(this.getComponent()
                        .getProduceMetadata());
            }
            return this.produceMetadataDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: ProduceMetadata", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the SearchMetadata.
     *
     * @return the SearchMetadataDelegate.
     * @throws ClientException
     */
    public SearchMetadataDelegate getSearchMetadata() throws ClientException {
        try {
            if ((this.searchMetadataDelegate == null)) {
                this.searchMetadataDelegate = new SearchMetadataDelegate(this.getComponent()
                        .getSearchMetadata());
            }
            return this.searchMetadataDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: SearchMetadata", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the SearchFolder.
     *
     * @return the SearchFolderDelegate.
     * @throws ClientException
     */
    public SearchFolderDelegate getSearchFolder() throws ClientException {
        try {
            if ((this.searchFolderDelegate == null)) {
                this.searchFolderDelegate = new SearchFolderDelegate(this.getComponent()
                        .getSearchFolder());
            }
            return this.searchFolderDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: SearchFolder", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the MaintainFolder.
     *
     * @return the MaintainFolderDelegate.
     * @throws ClientException
     */
    public MaintainFolderDelegate getMaintainFolder() throws ClientException {
        try {
            if ((this.maintainFolderDelegate == null)) {
                this.maintainFolderDelegate = new MaintainFolderDelegate(this.getComponent()
                        .getMaintainFolder());
            }
            return this.maintainFolderDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: MaintainFolder", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the MaintainSubEngineCode.
     *
     * @return the MaintainSubEngineCodeDelegate.
     * @throws ClientException
     */
    public MaintainSubEngineCodeDelegate getMaintainSubEngineCode() throws ClientException {
        try {
            if ((this.maintainSubEngineCodeDelegate == null)) {
                this.maintainSubEngineCodeDelegate = new MaintainSubEngineCodeDelegate(this
                        .getComponent().getMaintainSubEngineCode());
            }
            return this.maintainSubEngineCodeDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: MaintainSubEngineCode", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the SearchSubEngineCode.
     *
     * @return the SearchSubEngineCodeDelegate.
     * @throws ClientException
     */
    public SearchSubEngineCodeDelegate getSearchSubEngineCode() throws ClientException {
        try {
            if ((this.searchSubEngineCodeDelegate == null)) {
                this.searchSubEngineCodeDelegate = new SearchSubEngineCodeDelegate(this
                        .getComponent().getSearchSubEngineCode());
            }
            return this.searchSubEngineCodeDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: SearchSubEngineCode", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the ProduceFolder.
     *
     * @return the ProduceFolderDelegate.
     * @throws ClientException
     */
    public ProduceFolderDelegate getProduceFolder() throws ClientException {
        try {
            if ((this.produceFolderDelegate == null)) {
                this.produceFolderDelegate = new ProduceFolderDelegate(this.getComponent()
                        .getProduceFolder());
            }
            return this.produceFolderDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: ProduceFolder", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the ExportScript.
     *
     * @return the ExportScriptDelegate.
     * @throws ClientException
     */
    public ExportScriptDelegate getExportScript() throws ClientException {
        try {
            if ((this.exportScriptDelegate == null)) {
                this.exportScriptDelegate = new ExportScriptDelegate(this.getComponent()
                        .getExportScript());
            }
            return this.exportScriptDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: ExportScript", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the ImportScript.
     *
     * @return the ImportScriptDelegate.
     * @throws ClientException
     */
    public ImportScriptDelegate getImportScript() throws ClientException {
        try {
            if ((this.importScriptDelegate == null)) {
                this.importScriptDelegate = new ImportScriptDelegate(this.getComponent()
                        .getImportScript());
            }
            return this.importScriptDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: ImportScript", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the Instance.
     *
     * @return the ScriptComponentServiceDelegateFactory.
     */
    public static ScriptComponentServiceDelegateFactory getInstance() {
        return instance;
    }
}
