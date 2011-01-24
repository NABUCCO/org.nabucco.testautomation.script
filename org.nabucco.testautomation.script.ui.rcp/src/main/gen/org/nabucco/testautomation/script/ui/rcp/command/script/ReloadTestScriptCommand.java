/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.command.script;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * ReloadTestScriptCommand<p/>This command should reload a TestScript<p/>
 *
 * @author Marco Sussek, PRODYNA AG, 2010-10-21
 */
public class ReloadTestScriptCommand implements NabuccoCommand {

    private ReloadTestScriptHandler reloadTestScriptHandler = NabuccoInjector.getInstance(
            ReloadTestScriptCommand.class).inject(ReloadTestScriptHandler.class);

    public static final String ID = "org.nabucco.testautomation.script.ui.command.script.ReloadTestScriptCommand";

    /** Constructs a new ReloadTestScriptCommand instance. */
    public ReloadTestScriptCommand() {
        super();
    }

    @Override
    public void run() {
        reloadTestScriptHandler.reloadTestScript();
    }

    @Override
    public String getId() {
        return ReloadTestScriptCommand.ID;
    }
}
