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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.ui.forms.DetailsPart;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailBlock;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailBlockLayouter;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.testautomation.property.facade.datatype.BooleanProperty;
import org.nabucco.testautomation.property.facade.datatype.DateProperty;
import org.nabucco.testautomation.property.facade.datatype.FileProperty;
import org.nabucco.testautomation.property.facade.datatype.NumericProperty;
import org.nabucco.testautomation.property.facade.datatype.PropertyList;
import org.nabucco.testautomation.property.facade.datatype.SqlProperty;
import org.nabucco.testautomation.property.facade.datatype.TextProperty;
import org.nabucco.testautomation.property.facade.datatype.XPathProperty;
import org.nabucco.testautomation.property.facade.datatype.XmlProperty;
import org.nabucco.testautomation.property.ui.rcp.multipage.detail.PropertyDetailPageView;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.MetadataMaintenanceMultiPageEditView;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.detail.fileproperty.FilePropertyDetailPageView;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.detail.metadata.MetadataDetailPageView;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataMaintenanceMultiPageEditViewModel;

/**
 * MetadataMaintenanceMasterDetailBlock
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class MetadataMaintenanceMasterDetailBlock extends
        MasterDetailBlock<MetadataMaintenanceMultiPageEditViewModel> implements
        Layoutable<MetadataMaintenanceMultiPageEditViewModel> {

    public static String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.MetadataMaintenanceMasterDetailBlock";

    public MetadataMaintenanceMasterDetailBlock(MetadataMaintenanceMultiPageEditViewModel model,
            NabuccoMessageManager messageManager, MetadataMaintenanceMultiPageEditView view) {
        super(messageManager, view, model);
    }

    @Override
    protected void registerPages(DetailsPart arg0) {
    	Map<Class<? extends Datatype>, Set<String>> typeToInvisiblePropertiesMap = this.getTypeToInvisiblePropertiesMap();
    	
    	Set<String> readOnlyProperties = new HashSet<String>();
    	Set<String> invisibleProperties = new HashSet<String>();

    	/*
    	 * Metadata
    	 */
    	readOnlyProperties.add("identificationKey");
    	readOnlyProperties.add("owner");
    	invisibleProperties.add("id");
    	invisibleProperties.add("version");
    	
    	detailsPart.registerPage(Metadata.class, new MetadataDetailPageView(this, getManagedForm(),
                getManagedFormViewPart(), nabuccoMessageManager, ID, ID + "Metadata",
                invisibleProperties, readOnlyProperties));
        detailsPart.registerPage(MetadataLabel.class, new PropertyDetailPageView<MetadataMaintenanceMultiPageEditViewModel>(
                this, getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, ID, ID
                        + "MetadataLabel", invisibleProperties, new HashSet<String>()));

        /*
         * Property        
         */
        invisibleProperties = new HashSet<String>();
        readOnlyProperties = new HashSet<String>();
        
        invisibleProperties.add("owner");
        invisibleProperties.add("identificationKey");
    	invisibleProperties.add("id");
    	invisibleProperties.add("version");
    	invisibleProperties.add("filename");
    	invisibleProperties.add("reference");
        readOnlyProperties.add("type");
    	
        detailsPart.registerPage(TextProperty.class, new PropertyDetailPageView<MetadataMaintenanceMultiPageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, ID, ID
                        + "TextProperty", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(TextProperty.class, invisibleProperties);
        
        detailsPart.registerPage(BooleanProperty.class, new PropertyDetailPageView<MetadataMaintenanceMultiPageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, ID, ID
                        + "BooleanProperty", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(BooleanProperty.class, invisibleProperties);
        
        detailsPart.registerPage(DateProperty.class, new PropertyDetailPageView<MetadataMaintenanceMultiPageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, ID, ID
                        + "DateProperty", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(DateProperty.class, invisibleProperties);
        
        detailsPart.registerPage(NumericProperty.class, new PropertyDetailPageView<MetadataMaintenanceMultiPageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, ID, ID
                        + "NumericProperty", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(NumericProperty.class, invisibleProperties);
        
        detailsPart.registerPage(XPathProperty.class, new PropertyDetailPageView<MetadataMaintenanceMultiPageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, ID, ID
                        + "XPathProperty", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(XPathProperty.class, invisibleProperties);
        
        detailsPart.registerPage(XmlProperty.class, new PropertyDetailPageView<MetadataMaintenanceMultiPageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, ID, ID
                        + "XmlProperty", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(XmlProperty.class, invisibleProperties);
        
        detailsPart.registerPage(FileProperty.class, new FilePropertyDetailPageView(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, ID, ID
                        + "FileProperty", invisibleProperties, readOnlyProperties));
        
        detailsPart.registerPage(SqlProperty.class, new PropertyDetailPageView<MetadataMaintenanceMultiPageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, ID, ID
                        + "SqlProperty", invisibleProperties, readOnlyProperties));
        
        /*
         * PropertyList
         */
        invisibleProperties = new HashSet<String>();
        readOnlyProperties = new HashSet<String>();
        
        readOnlyProperties.add("owner");
        readOnlyProperties.add("type");
        readOnlyProperties.add("identificationKey");
    	invisibleProperties.add("id");
    	invisibleProperties.add("version");
        invisibleProperties.add("reused");
        invisibleProperties.add("usageType");
        invisibleProperties.add("reference");
        
        detailsPart.registerPage(PropertyList.class, new PropertyDetailPageView<MetadataMaintenanceMultiPageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, ID + ".PropertyList", ID
                        + "PropertyList", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(PropertyList.class, invisibleProperties);
    }

    /**
     * {@inheritDoc}
     * 
     * @return
     */
    protected MasterDetailBlockLayouter<MetadataMaintenanceMultiPageEditViewModel> getLayouter() {
        return new MetadataMaintenanceMasterDetailBlockLayouter();
    }

}
