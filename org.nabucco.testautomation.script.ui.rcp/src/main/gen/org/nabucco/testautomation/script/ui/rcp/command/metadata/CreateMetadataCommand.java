/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.nabucco.testautomation.script.ui.rcp.command.metadata;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * CreateMetadataCommand<p/>This command is for creating a Metadata<p/>
 *
 * @author Markus Jorroch, PRODYNA AG, 2010-10-15
 */
public class CreateMetadataCommand implements NabuccoCommand {

    private CreateMetadataHandler createMetadataHandler = NabuccoInjector.getInstance(CreateMetadataCommand.class)
            .inject(CreateMetadataHandler.class);

    public static final String ID = "org.nabucco.testautomation.script.ui.command.metadata.CreateMetadataCommand";

    /** Constructs a new CreateMetadataCommand instance. */
    public CreateMetadataCommand() {
        super();
    }

    @Override
    public void run() {
        createMetadataHandler.createMetadata();
    }

    @Override
    public String getId() {
        return CreateMetadataCommand.ID;
    }
}
