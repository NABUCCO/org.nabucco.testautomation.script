/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.command.metadata;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenMetadataSelectedInSearchResultListViewTableCommand<p/>open Metadata from ListView<p/>
 *
 * @author Markus Jorroch, PRODYNA AG, 2010-27-09
 */
public class OpenMetadataSelectedInSearchResultListViewTableCommand implements NabuccoCommand {

    private OpenMetadataSelectedInSearchResultListViewTableHandler openMetadataSelectedInSearchResultListViewTableHandler = NabuccoInjector
            .getInstance(OpenMetadataSelectedInSearchResultListViewTableCommand.class).inject(
                    OpenMetadataSelectedInSearchResultListViewTableHandler.class);

    public static final String ID = "org.nabucco.testautomation.script.ui.command.metadata.OpenMetadataSelectedInSearchResultListViewTableCommand";

    /** Constructs a new OpenMetadataSelectedInSearchResultListViewTableCommand instance. */
    public OpenMetadataSelectedInSearchResultListViewTableCommand() {
        super();
    }

    @Override
    public void run() {
        openMetadataSelectedInSearchResultListViewTableHandler
                .openMetadataSelectedInSearchResultListViewTable();
    }

    @Override
    public String getId() {
        return OpenMetadataSelectedInSearchResultListViewTableCommand.ID;
    }
}
