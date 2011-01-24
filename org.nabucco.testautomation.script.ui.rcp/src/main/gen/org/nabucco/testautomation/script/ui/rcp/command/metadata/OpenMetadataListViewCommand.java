/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.command.metadata;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenMetadataListViewCommand<p/>open list view for Metadata<p/>
 *
 * @author Markus Jorroch, PRODYNA AG, 2010-10-15
 */
public class OpenMetadataListViewCommand implements NabuccoCommand {

    private OpenMetadataListViewHandler openMetadataListViewHandler = NabuccoInjector.getInstance(
            OpenMetadataListViewCommand.class).inject(OpenMetadataListViewHandler.class);

    public static final String ID = "org.nabucco.testautomation.script.ui.command.metadata.OpenMetadataListViewCommand";

    /** Constructs a new OpenMetadataListViewCommand instance. */
    public OpenMetadataListViewCommand() {
        super();
    }

    @Override
    public void run() {
        openMetadataListViewHandler.openMetadataListView();
    }

    @Override
    public String getId() {
        return OpenMetadataListViewCommand.ID;
    }
}
