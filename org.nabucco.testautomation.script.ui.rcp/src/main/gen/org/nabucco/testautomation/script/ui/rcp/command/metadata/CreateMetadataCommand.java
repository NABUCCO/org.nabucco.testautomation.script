/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.command.metadata;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * CreateMetadataCommand<p/>This command is for creating a Metadata<p/>
 *
 * @author Markus Jorroch, PRODYNA AG, 2010-10-15
 */
public class CreateMetadataCommand implements NabuccoCommand {

    private CreateMetadataHandler createMetadataHandler = NabuccoInjector.getInstance(
            CreateMetadataCommand.class).inject(CreateMetadataHandler.class);

    public static final String ID = "org.nabucco.testautomation.script.ui.command.metadata.CreateMetadataCommand";

    /** Constructs a new CreateMetadataCommand instance. */
    public CreateMetadataCommand() {
        super();
    }

    @Override
    public void run() {
        createMetadataHandler.createMetadata();
    }

    @Override
    public String getId() {
        return CreateMetadataCommand.ID;
    }
}
