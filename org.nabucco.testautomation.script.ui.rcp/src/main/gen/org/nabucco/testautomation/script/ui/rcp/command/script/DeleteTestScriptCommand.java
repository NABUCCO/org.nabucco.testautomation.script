/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.command.script;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * DeleteTestScriptCommand<p/>This command should delete a TestScript<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-14
 */
public class DeleteTestScriptCommand implements NabuccoCommand {

    private DeleteTestScriptHandler deleteTestScriptHandler = NabuccoInjector.getInstance(
            DeleteTestScriptCommand.class).inject(DeleteTestScriptHandler.class);

    public static final String ID = "org.nabucco.testautomation.script.ui.command.script.DeleteTestScriptCommand";

    /** Constructs a new DeleteTestScriptCommand instance. */
    public DeleteTestScriptCommand() {
        super();
    }

    @Override
    public void run() {
        deleteTestScriptHandler.deleteTestScript();
    }

    @Override
    public String getId() {
        return DeleteTestScriptCommand.ID;
    }
}
