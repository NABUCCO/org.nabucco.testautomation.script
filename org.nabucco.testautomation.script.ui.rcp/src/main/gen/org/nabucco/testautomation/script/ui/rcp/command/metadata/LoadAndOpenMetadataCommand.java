/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.command.metadata;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * LoadAndOpenMetadataCommand<p/>This command is for reading a Metadata<p/>
 *
 * @author Markus Jorroch, PRODYNA AG, 2010-10-15
 */
public class LoadAndOpenMetadataCommand implements NabuccoCommand {

    private LoadAndOpenMetadataHandler loadAndOpenMetadataHandler = NabuccoInjector.getInstance(
            LoadAndOpenMetadataCommand.class).inject(LoadAndOpenMetadataHandler.class);

    public static final String ID = "org.nabucco.testautomation.script.ui.command.metadata.LoadAndOpenMetadataCommand";

    /** Constructs a new LoadAndOpenMetadataCommand instance. */
    public LoadAndOpenMetadataCommand() {
        super();
    }

    @Override
    public void run() {
        loadAndOpenMetadataHandler.loadAndOpenMetadata();
    }

    @Override
    public String getId() {
        return LoadAndOpenMetadataCommand.ID;
    }
}
