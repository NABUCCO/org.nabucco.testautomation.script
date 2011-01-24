/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.command.script;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenCorrespondingTestScriptListViewCommand<p/>open list view for TestScript<p/>
 *
 * @author Stefan Huettenrauch, PRODYNA AG, 2010-05-28
 */
public class OpenCorrespondingTestScriptListViewCommand implements NabuccoCommand {

    private OpenCorrespondingTestScriptListViewHandler openCorrespondingTestScriptListViewHandler = NabuccoInjector
            .getInstance(OpenCorrespondingTestScriptListViewCommand.class).inject(
                    OpenCorrespondingTestScriptListViewHandler.class);

    public static final String ID = "org.nabucco.testautomation.script.ui.command.script.OpenCorrespondingTestScriptListViewCommand";

    /** Constructs a new OpenCorrespondingTestScriptListViewCommand instance. */
    public OpenCorrespondingTestScriptListViewCommand() {
        super();
    }

    @Override
    public void run() {
        openCorrespondingTestScriptListViewHandler.openCorrespondingTestScriptListView();
    }

    @Override
    public String getId() {
        return OpenCorrespondingTestScriptListViewCommand.ID;
    }
}
