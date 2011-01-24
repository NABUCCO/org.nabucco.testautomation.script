/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.command.script;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * SaveTestScriptCommand<p/>This command should save a TestScript<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-14
 */
public class SaveTestScriptCommand implements NabuccoCommand {

    private SaveTestScriptHandler saveTestScriptHandler = NabuccoInjector.getInstance(
            SaveTestScriptCommand.class).inject(SaveTestScriptHandler.class);

    public static final String ID = "org.nabucco.testautomation.script.ui.command.script.SaveTestScriptCommand";

    /** Constructs a new SaveTestScriptCommand instance. */
    public SaveTestScriptCommand() {
        super();
    }

    @Override
    public void run() {
        saveTestScriptHandler.saveTestScript();
    }

    @Override
    public String getId() {
        return SaveTestScriptCommand.ID;
    }
}
