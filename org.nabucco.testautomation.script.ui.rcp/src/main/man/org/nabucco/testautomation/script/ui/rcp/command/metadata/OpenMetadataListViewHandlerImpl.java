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
package org.nabucco.testautomation.script.ui.rcp.command.metadata;

import org.nabucco.framework.plugin.base.command.AbstractNabuccoOpenCorrespondingListViewHandlerImpl;
import org.nabucco.testautomation.script.ui.rcp.browser.metadata.MetadataListViewBrowserElement;
import org.nabucco.testautomation.script.ui.rcp.command.metadata.OpenMetadataListViewHandler;
import org.nabucco.testautomation.script.ui.rcp.list.metadata.model.MetadataListViewModel;
import org.nabucco.testautomation.script.ui.rcp.list.metadata.view.MetadataListView;


/**
 * OpenMetadataListViewHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class OpenMetadataListViewHandlerImpl
        extends
        AbstractNabuccoOpenCorrespondingListViewHandlerImpl<MetadataListViewBrowserElement, MetadataListViewModel>
        implements OpenMetadataListViewHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void openMetadataListView() {
        super.run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getListViewId() {
        return MetadataListView.ID;
    }

}
