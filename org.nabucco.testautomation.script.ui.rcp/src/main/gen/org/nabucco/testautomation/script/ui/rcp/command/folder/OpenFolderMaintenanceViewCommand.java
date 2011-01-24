/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.command.folder;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenFolderMaintenanceViewCommand<p/>open maintenance view for folders<p/>
 *
 * @author Markus Jorroch, PRODYNA AG, 2010-10-15
 */
public class OpenFolderMaintenanceViewCommand implements NabuccoCommand {

    private OpenFolderMaintenanceViewHandler openFolderMaintenanceViewHandler = NabuccoInjector
            .getInstance(OpenFolderMaintenanceViewCommand.class).inject(
                    OpenFolderMaintenanceViewHandler.class);

    public static final String ID = "org.nabucco.testautomation.script.ui.command.folder.OpenFolderMaintenanceViewCommand";

    /** Constructs a new OpenFolderMaintenanceViewCommand instance. */
    public OpenFolderMaintenanceViewCommand() {
        super();
    }

    @Override
    public void run() {
        openFolderMaintenanceViewHandler.openFolderMaintenanceView();
    }

    @Override
    public String getId() {
        return OpenFolderMaintenanceViewCommand.ID;
    }
}
