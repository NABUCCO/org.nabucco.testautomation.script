/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.command.folder;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * MaintainFolderStructureCommand<p/>maintains the folder structure<p/>
 *
 * @author Markus Jorroch, PRODYNA AG, 2010-10-15
 */
public class MaintainFolderStructureCommand implements NabuccoCommand {

    private MaintainFolderStructureHandler maintainFolderStructureHandler = NabuccoInjector
            .getInstance(MaintainFolderStructureCommand.class).inject(
                    MaintainFolderStructureHandler.class);

    public static final String ID = "org.nabucco.testautomation.script.ui.command.folder.MaintainFolderStructureCommand";

    /** Constructs a new MaintainFolderStructureCommand instance. */
    public MaintainFolderStructureCommand() {
        super();
    }

    @Override
    public void run() {
        maintainFolderStructureHandler.maintainFolderStructure();
    }

    @Override
    public String getId() {
        return MaintainFolderStructureCommand.ID;
    }
}
