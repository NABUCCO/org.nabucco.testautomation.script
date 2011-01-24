/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.command.script;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * ReadTestScriptCommand<p/>This command is for reading a TestScript<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-14
 */
public class ReadTestScriptCommand implements NabuccoCommand {

    private ReadTestScriptHandler readTestScriptHandler = NabuccoInjector.getInstance(
            ReadTestScriptCommand.class).inject(ReadTestScriptHandler.class);

    public static final String ID = "org.nabucco.testautomation.script.ui.command.script.ReadTestScriptCommand";

    /** Constructs a new ReadTestScriptCommand instance. */
    public ReadTestScriptCommand() {
        super();
    }

    @Override
    public void run() {
        readTestScriptHandler.readTestScript();
    }

    @Override
    public String getId() {
        return ReadTestScriptCommand.ID;
    }
}
