/*
* Copyright 2010 PRODYNA AG
*
* Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.opensource.org/licenses/eclipse-1.0.php or
* http://www.nabucco-source.org/nabucco-license.html
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.nabucco.testautomation.script.ui.rcp.browser.metadata;

import org.nabucco.framework.plugin.base.model.browser.BrowserElement;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.browser.metadata.MetadataListViewBrowserElement;
import org.nabucco.testautomation.script.ui.rcp.browser.metadata.MetadataListViewBrowserElementHandler;
import org.nabucco.testautomation.script.ui.rcp.list.metadata.model.MetadataListViewModel;


/**
 * TestScriptListViewBrowserElementHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class MetadataListViewBrowserElementHandlerImpl implements
        MetadataListViewBrowserElementHandler {

    @Override
    public void createChildren(MetadataListViewModel viewModel,
            MetadataListViewBrowserElement element) {
        Metadata[] elements = viewModel.getElements();
        for (Metadata metadata : elements) {
            element.addBrowserElement(new MetadataEditViewBrowserElement(metadata));
        }
    }

    @Override
    public void removeChild(BrowserElement toBeRemoved, MetadataListViewBrowserElement element) {
        // do nothing
    }

}
