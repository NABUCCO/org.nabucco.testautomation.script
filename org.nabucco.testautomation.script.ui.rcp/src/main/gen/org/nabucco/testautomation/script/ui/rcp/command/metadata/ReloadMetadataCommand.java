/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.command.metadata;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * ReloadMetadataCommand<p/>This command should reload a Metadata<p/>
 *
 * @author Marco Sussek, PRODYNA AG, 2010-10-21
 */
public class ReloadMetadataCommand implements NabuccoCommand {

    private ReloadMetadataHandler reloadMetadataHandler = NabuccoInjector.getInstance(
            ReloadMetadataCommand.class).inject(ReloadMetadataHandler.class);

    public static final String ID = "org.nabucco.testautomation.script.ui.command.metadata.ReloadMetdadataCommand";

    /** Constructs a new ReloadMetadataCommand instance. */
    public ReloadMetadataCommand() {
        super();
    }

    @Override
    public void run() {
        reloadMetadataHandler.reloadMetadata();
    }

    @Override
    public String getId() {
        return ReloadMetadataCommand.ID;
    }
}
