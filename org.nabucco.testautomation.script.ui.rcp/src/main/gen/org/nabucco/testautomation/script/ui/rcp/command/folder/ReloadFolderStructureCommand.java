/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.command.folder;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * ReloadFolderStructureCommand<p/>loads the folder structure<p/>
 *
 * @author Markus Jorroch, PRODYNA AG, 2010-10-15
 */
public class ReloadFolderStructureCommand implements NabuccoCommand {

    private ReloadFolderStructureHandler reloadFolderStructureHandler = NabuccoInjector
            .getInstance(ReloadFolderStructureCommand.class).inject(
                    ReloadFolderStructureHandler.class);

    public static final String ID = "org.nabucco.testautomation.script.ui.command.folder.ReloadFolderStructureCommand";

    /** Constructs a new ReloadFolderStructureCommand instance. */
    public ReloadFolderStructureCommand() {
        super();
    }

    @Override
    public void run() {
        reloadFolderStructureHandler.reloadFolderStructure();
    }

    @Override
    public String getId() {
        return ReloadFolderStructureCommand.ID;
    }
}
