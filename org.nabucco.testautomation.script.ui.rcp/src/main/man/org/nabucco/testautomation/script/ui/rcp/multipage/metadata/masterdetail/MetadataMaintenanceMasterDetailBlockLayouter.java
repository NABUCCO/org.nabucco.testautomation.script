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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail;

import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailBlockLayouter;
import org.nabucco.framework.plugin.base.layout.I18NLabelProviderContributor;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataMaintenanceMultiPageEditViewModel;


/**
 * MetadataMaintenanceMasterDetailBlockLayouter
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class MetadataMaintenanceMasterDetailBlockLayouter extends MasterDetailBlockLayouter<MetadataMaintenanceMultiPageEditViewModel> {

    /**
     * Default constructor.
     */
    public MetadataMaintenanceMasterDetailBlockLayouter() {
        super(
                "org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.title",
                "org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.description");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public I18NLabelProviderContributor getMasterDetailLabelProvider() {
        return MetadataMaintenanceMasterDetailLabelProvider.getInstance();
    }

}