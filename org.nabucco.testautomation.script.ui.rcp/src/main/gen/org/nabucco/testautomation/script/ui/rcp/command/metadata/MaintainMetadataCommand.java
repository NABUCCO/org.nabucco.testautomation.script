/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.command.metadata;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * MaintainMetadataCommand<p/>This command should maintain a Metadata<p/>
 *
 * @author Markus Jorroch, PRODYNA AG, 2010-10-15
 */
public class MaintainMetadataCommand implements NabuccoCommand {

    private MaintainMetadataHandler maintainMetadataHandler = NabuccoInjector.getInstance(
            MaintainMetadataCommand.class).inject(MaintainMetadataHandler.class);

    public static final String ID = "org.nabucco.testautomation.script.ui.command.metadata.MaintainMetdadataCommand";

    /** Constructs a new MaintainMetadataCommand instance. */
    public MaintainMetadataCommand() {
        super();
    }

    @Override
    public void run() {
        maintainMetadataHandler.maintainMetadata();
    }

    @Override
    public String getId() {
        return MaintainMetadataCommand.ID;
    }
}
