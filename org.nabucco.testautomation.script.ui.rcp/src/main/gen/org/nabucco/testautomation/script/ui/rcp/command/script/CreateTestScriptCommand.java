/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.command.script;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * CreateTestScriptCommand<p/>This command is for creating a TestScript<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-14
 */
public class CreateTestScriptCommand implements NabuccoCommand {

    private CreateTestScriptCommandHandler createTestScriptCommandHandler = NabuccoInjector
            .getInstance(CreateTestScriptCommand.class)
            .inject(CreateTestScriptCommandHandler.class);

    public static final String ID = "org.nabucco.testautomation.script.ui.command.script.CreateTestScriptCommand";

    /** Constructs a new CreateTestScriptCommand instance. */
    public CreateTestScriptCommand() {
        super();
    }

    @Override
    public void run() {
        createTestScriptCommandHandler.createTestScriptCommand();
    }

    @Override
    public String getId() {
        return CreateTestScriptCommand.ID;
    }
}
