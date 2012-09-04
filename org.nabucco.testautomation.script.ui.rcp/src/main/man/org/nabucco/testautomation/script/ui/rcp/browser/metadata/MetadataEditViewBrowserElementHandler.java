/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.testautomation.script.ui.rcp.browser.metadata;

import org.nabucco.framework.plugin.base.command.CommandHandler;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataMaintenanceMultiPageEditViewModel;

/**
 * MetadataEditViewBrowserElementHandler
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public interface MetadataEditViewBrowserElementHandler extends CommandHandler {

    /**
     * LoadFull.
     * 
     * @param testScript
     *            the TestScriptEditViewModel.
     * @return the TestScriptEditViewModel.
     */
    MetadataMaintenanceMultiPageEditViewModel loadFull(
            final MetadataMaintenanceMultiPageEditViewModel testScript);

    /**
     * CreateChildren.
     * 
     * @param element
     *            the TestScriptEditViewBrowserElement.
     * @param viewModel
     *            the TestScriptEditViewModel.
     */
    void createChildren(final MetadataMaintenanceMultiPageEditViewModel viewModel,
            final MetadataEditViewBrowserElement element);
}
