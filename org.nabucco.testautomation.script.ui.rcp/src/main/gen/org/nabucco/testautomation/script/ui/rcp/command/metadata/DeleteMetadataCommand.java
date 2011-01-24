/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.command.metadata;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * DeleteMetadataCommand<p/>This command should delete a Metadata<p/>
 *
 * @author Markus Jorroch, PRODYNA AG, 2010-10-15
 */
public class DeleteMetadataCommand implements NabuccoCommand {

    private DeleteMetadataHandler deleteMetadataHandler = NabuccoInjector.getInstance(
            DeleteMetadataCommand.class).inject(DeleteMetadataHandler.class);

    public static final String ID = "org.nabucco.testautomation.script.ui.command.metadata.DeleteMetadataCommand";

    /** Constructs a new DeleteMetadataCommand instance. */
    public DeleteMetadataCommand() {
        super();
    }

    @Override
    public void run() {
        deleteMetadataHandler.deleteMetadata();
    }

    @Override
    public String getId() {
        return DeleteMetadataCommand.ID;
    }
}
