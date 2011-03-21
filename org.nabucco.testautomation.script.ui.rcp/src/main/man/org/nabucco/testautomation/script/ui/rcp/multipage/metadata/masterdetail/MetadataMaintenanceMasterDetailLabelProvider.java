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

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.code.Code;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.layout.I18NLabelProviderContributor;
import org.nabucco.testautomation.facade.datatype.property.base.Property;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyContainer;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;
import org.nabucco.testautomation.script.ui.rcp.images.ScriptImageRegistry;
import org.nabucco.testautomation.ui.rcp.images.TestautomationImageRegistry;

/**
 * MetadataMaintenanceMasterDetailLabelProvider
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class MetadataMaintenanceMasterDetailLabelProvider implements I18NLabelProviderContributor {

	private static final String ANY = "*";
	
	private static MetadataMaintenanceMasterDetailLabelProvider instance = new MetadataMaintenanceMasterDetailLabelProvider();
	
	private MetadataMaintenanceMasterDetailLabelProvider(){
	}
	
	public static MetadataMaintenanceMasterDetailLabelProvider getInstance(){
		return instance;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map.Entry<String, Map<String, ? extends Serializable>> getText(final Object element) {
		Map.Entry<String, Map<String, ? extends Serializable>> result = null;
		if (element instanceof MasterDetailTreeNode) {
			MasterDetailTreeNode treeNode = (MasterDetailTreeNode) element;
			result = getText(treeNode.getDatatype());
		}
		return result;
	}

	/**
	 * String representing a special datatype.
	 * 
	 * @param datatype
	 * @return
	 */
	private Map.Entry<String, Map<String, ? extends Serializable>> getText(final Datatype datatype) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Map.Entry<String, Map<String, ? extends Serializable>> result = new AbstractMap.SimpleEntry(
				datatype.toString(), null);

		if (datatype instanceof Metadata) {
			Metadata metadata = (Metadata) datatype;
			if (metadata.getName() != null) {
				result = createEntry("MasterDetailTree.Metadata", "name", metadata.getName()
						.getValue());
			} else {
				result = createEntry("MasterDetailTree.Metadata", "name", metadata.getClass()
						.getSimpleName());
			}
		} else if (datatype instanceof MetadataLabel) {
			MetadataLabel metadataLabel = (MetadataLabel) datatype;

			String brandTypeName = ANY;
			Code brandType = metadataLabel.getBrandType();
			if (brandType != null && brandType.getName() != null) {
				brandTypeName = brandType.getName().getValue();
			}

			String environmentTypeName = ANY;
			Code environmentType = metadataLabel.getEnvironmentType();
			if (environmentType != null && environmentType.getName() != null) {
				environmentTypeName = environmentType.getName().getValue();
			}

			String releaseTypeName = ANY;
			Code releaseType = metadataLabel.getReleaseType();
			if (releaseType != null && releaseType.getName() != null) {
				releaseTypeName = releaseType.getName().getValue();
			}
			result = createEntry("MasterDetailTree.MetadataLabel", "name", brandTypeName
					+ "/" + environmentTypeName + "/" + releaseTypeName);
		} else if (datatype instanceof Property) {
			Property property = (Property) datatype;
			if (property.getName() != null) {
				result = createEntry("MasterDetailTree.Property", "name", property.getName()
						.getValue());
			} else {
				result = createEntry("MasterDetailTree.Property", "name", property.getClass()
						.getSimpleName());
			}
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private SimpleEntry<String, Map<String, ? extends Serializable>> createEntry(final String i18n,
			String key, String value) {
		return new AbstractMap.SimpleEntry(i18n, createMap(key, value));
	}

	/**
	 * @param string
	 * @param name
	 * @return
	 */
	private Map<String, ? extends Serializable> createMap(final String key, final String value) {
		Map<String, String> result = new HashMap<String, String>();
		result.put(key, value);
		return result;
	}


	@Override
	public String getImage(Object element) {
		if (element instanceof MasterDetailTreeNode) {

			MasterDetailTreeNode treeNode = (MasterDetailTreeNode) element;
			Datatype datatype = treeNode.getDatatype();
			return getImage(datatype);
		} else if(element instanceof Datatype){
			return getImage((Datatype) element);
		}
		return ScriptImageRegistry.ICON_FOLDER.getId();
	}

	private String getImage(Datatype datatype) {
		
		if(datatype instanceof PropertyContainer){
			datatype = ((PropertyContainer) datatype).getProperty();
		}
		
		if (datatype instanceof Metadata) {
			return ScriptImageRegistry.ICON_METADATA.getId();
		} else if (datatype instanceof MetadataLabel) {
			return ScriptImageRegistry.ICON_LABEL.getId();
		} else if (datatype instanceof Property) {
			switch (((Property) datatype).getType()) {
			case LIST:
				return TestautomationImageRegistry.ICON_PROPERTY_LIST.getId();
			case STRING:
				return TestautomationImageRegistry.ICON_PROPERTY_STRING.getId();
			case LONG:
			case DOUBLE:
			case INTEGER:
				return TestautomationImageRegistry.ICON_PROPERTY_NUMERIC.getId();
			case XML:
				return TestautomationImageRegistry.ICON_PROPERTY_XML.getId();
			case SQL:
				return TestautomationImageRegistry.ICON_PROPERTY_SQL.getId();
			case FILE:
				return TestautomationImageRegistry.ICON_PROPERTY_FILE.getId();
			case DATE:
				return TestautomationImageRegistry.ICON_PROPERTY_DATE.getId();
			case BOOLEAN:
				return TestautomationImageRegistry.ICON_PROPERTY_BOOLEAN.getId();
			case XPATH:
				return TestautomationImageRegistry.ICON_PROPERTY_XPATH.getId();
			default:
				return TestautomationImageRegistry.ICON_PROPERTY.getId();
			}
		}
		return ScriptImageRegistry.ICON_FOLDER.getId();
	}

}
