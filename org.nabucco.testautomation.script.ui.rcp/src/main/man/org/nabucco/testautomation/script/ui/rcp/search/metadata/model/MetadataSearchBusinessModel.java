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
package org.nabucco.testautomation.script.ui.rcp.search.metadata.model;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchModel;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.message.MetadataListMsg;
import org.nabucco.testautomation.script.facade.message.MetadataSearchMsg;
import org.nabucco.testautomation.script.ui.rcp.browser.metadata.MetadataListViewBrowserElement;
import org.nabucco.testautomation.script.ui.rcp.communication.ScriptComponentServiceDelegateFactory;
import org.nabucco.testautomation.script.ui.rcp.communication.search.SearchMetadataDelegate;
import org.nabucco.testautomation.script.ui.rcp.search.metadata.model.MetadataSearchViewModel;


/**
 * Does the search for Metadata.
 * 
 * @author Markus Jorroch, PRODYNA AG
 * 
 */
public class MetadataSearchBusinessModel implements NabuccoComponentSearchModel {

    public static final String ID = "org.nabucco.testautomation.script.ui.rcp.search.metadata.model.MetadataSearchBusinessModel";

    /**
     * {@inheritDoc}
     */
    @Override
    public MetadataListViewBrowserElement search(NabuccoComponentSearchParameter searchViewModel) {
        MetadataListViewBrowserElement result = null;
        if (searchViewModel instanceof MetadataSearchViewModel) {
            MetadataSearchViewModel testScriptSearchViewModel = (MetadataSearchViewModel) searchViewModel;
            MetadataSearchMsg rq = createMetadataSearchMsg(testScriptSearchViewModel);
            result = new MetadataListViewBrowserElement(search(rq).toArray(new Metadata[0]));

        }
        return result;
    }

    private List<Metadata> search(final MetadataSearchMsg rq) {
        List<Metadata> result = null;
        try {
            SearchMetadataDelegate searchDelegate = ScriptComponentServiceDelegateFactory
                    .getInstance().getSearchMetadata();
            MetadataListMsg response = searchDelegate.searchMetadata(rq);
            result = response.getMetadataList();
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
        return result;
    }

    private MetadataSearchMsg createMetadataSearchMsg(MetadataSearchViewModel searchViewModel) {
        MetadataSearchMsg result = new MetadataSearchMsg();

        result.setName(getNameFromModel(searchViewModel));
        result.setDescription(getDescriptionFromModel(searchViewModel)); 
        result.setSubEngine(searchViewModel.getMetadata().getSubEngine());

        return result;
    }

    private Description getDescriptionFromModel(final MetadataSearchViewModel searchViewModel) {
        Description result = new Description();
        String description = searchViewModel.getMetadataDescription();

        result.setValue((description == null || description.length() == 0) ? null : description);
        return result;
    }

    private Name getNameFromModel(MetadataSearchViewModel searchViewModel) {
        Name result = new Name();
        String name = searchViewModel.getMetadataName();

        result.setValue((name == null || name.length() == 0) ? null : name);
        return result;
    }

}
