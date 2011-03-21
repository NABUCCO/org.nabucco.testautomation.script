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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.addDialog.AddDialogLabelProvider;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;

import org.nabucco.testautomation.facade.datatype.property.base.Property;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyContainer;

/**
 * MetadataMaintenanceMasterDetailAddDialogLabelProvider
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class MetadataMaintenanceMasterDetailAddDialogLabelProvider extends AddDialogLabelProvider {

    @Override
    public String getText(Object obj) {
        String result = null;
        if (obj instanceof Metadata) {
            Metadata element = (Metadata) obj;
            DatatypeState datatypeState = element.getDatatypeState();
            if (datatypeState != DatatypeState.INITIALIZED) {
                result = element.getName().getValue();
            } else {
                result = I18N.i18n(element.getClass().getCanonicalName());
            }
        } else if (obj instanceof MetadataLabel) {
            MetadataLabel element = (MetadataLabel) obj;
            result = I18N.i18n(element.getClass().getCanonicalName());
        } else if (obj instanceof Property) {
            Property property = ((Property) obj);
            DatatypeState datatypeState = property.getDatatypeState();
            if (datatypeState != DatatypeState.INITIALIZED) {
                result = property.getName().getValue();
            }
        } else if (obj instanceof PropertyContainer) {
            Property property = ((PropertyContainer) obj).getProperty();
            DatatypeState datatypeState = property.getDatatypeState();
            if (datatypeState != DatatypeState.INITIALIZED) {
                result = property.getName().getValue();
            } else {
                result = I18N.i18n(property.getClass().getCanonicalName());
            }
        }
        if (result == null) {
            result = I18N.i18n(obj.getClass().getCanonicalName());
        }
        return result;
    }

}
