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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;
import org.nabucco.testautomation.script.facade.message.MetadataLabelMsg;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeListMsg;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeSearchMsg;
import org.nabucco.testautomation.script.ui.rcp.communication.ScriptComponentServiceDelegateFactory;
import org.nabucco.testautomation.script.ui.rcp.communication.produce.ProduceMetadataDelegate;
import org.nabucco.testautomation.script.ui.rcp.communication.search.SearchSubEngineCodeDelegate;

import org.nabucco.testautomation.facade.datatype.property.BooleanProperty;
import org.nabucco.testautomation.facade.datatype.property.DateProperty;
import org.nabucco.testautomation.facade.datatype.property.DoubleProperty;
import org.nabucco.testautomation.facade.datatype.property.FileProperty;
import org.nabucco.testautomation.facade.datatype.property.IntegerProperty;
import org.nabucco.testautomation.facade.datatype.property.LongProperty;
import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.facade.datatype.property.SqlProperty;
import org.nabucco.testautomation.facade.datatype.property.StringProperty;
import org.nabucco.testautomation.facade.datatype.property.XPathProperty;
import org.nabucco.testautomation.facade.datatype.property.XmlProperty;
import org.nabucco.testautomation.facade.datatype.property.base.Property;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyContainer;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyType;
import org.nabucco.testautomation.facade.message.ProducePropertyMsg;
import org.nabucco.testautomation.facade.message.PropertyMsg;
import org.nabucco.testautomation.ui.rcp.communication.TestautomationComponentServiceDelegateFactory;
import org.nabucco.testautomation.ui.rcp.communication.produce.ProducePropertyDelegate;

public class MetadataElementFactory {

    private static List<SubEngineCode> subEngineCodeList = new ArrayList<SubEngineCode>();

    private static ScriptComponentServiceDelegateFactory scriptComponentServiceDelegateFactory = ScriptComponentServiceDelegateFactory
            .getInstance();

    private static Map<Class<?>, Datatype> cache = new HashMap<Class<?>, Datatype>();

    private static Map<Class<? extends Property>, PropertyType> propertyTypeMap = new HashMap<Class<? extends Property>, PropertyType>();

    static {
        propertyTypeMap.put(PropertyList.class, PropertyType.LIST);
        propertyTypeMap.put(BooleanProperty.class, PropertyType.BOOLEAN);
        propertyTypeMap.put(DateProperty.class, PropertyType.DATE);
        propertyTypeMap.put(DoubleProperty.class, PropertyType.DOUBLE);
        propertyTypeMap.put(FileProperty.class, PropertyType.FILE);
        propertyTypeMap.put(IntegerProperty.class, PropertyType.INTEGER);
        propertyTypeMap.put(LongProperty.class, PropertyType.LONG);
        propertyTypeMap.put(StringProperty.class, PropertyType.STRING);
        propertyTypeMap.put(XPathProperty.class, PropertyType.XPATH);
        propertyTypeMap.put(XmlProperty.class, PropertyType.XML);
        propertyTypeMap.put(SqlProperty.class, PropertyType.SQL);

        SearchSubEngineCodeDelegate searchSubEngineCode;
        try {
            searchSubEngineCode = scriptComponentServiceDelegateFactory.getSearchSubEngineCode();
            SubEngineCodeSearchMsg rq = new SubEngineCodeSearchMsg();
            SubEngineCodeListMsg response = searchSubEngineCode.searchSubEngineCode(rq);
            if (response != null) {
                subEngineCodeList.addAll(response.getSubEngineCodeList());
            }
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
    }

    public static List<SubEngineCode> getSubEngineCodes(Metadata metadata) {
        return subEngineCodeList;
    }

    public static Datatype create(Class<?> className) {
        Datatype cachedElement = cache.get(className);
        if (cachedElement != null) {
            return cachedElement.cloneObject();
        }
        Datatype result = null;
        try {
            if (className.equals(Metadata.class)) {
                ProduceMetadataDelegate produceMetadataDelegate = scriptComponentServiceDelegateFactory
                        .getProduceMetadata();
                MetadataMsg response = produceMetadataDelegate
                        .produceMetadata(new EmptyServiceMessage());
                if (response != null) {
                    Metadata metadata = response.getMetadata();
                    result = metadata;
                }
            } else if (className.equals(MetadataLabel.class)) {
                ProduceMetadataDelegate produceMetadataDelegate = scriptComponentServiceDelegateFactory
                        .getProduceMetadata();
                MetadataLabelMsg response = produceMetadataDelegate
                        .produceMetadataLabel(new EmptyServiceMessage());
                if (response != null) {
                	result = response.getMetadataLabel();
                }
            } else if (propertyTypeMap.keySet().contains(className)){
                TestautomationComponentServiceDelegateFactory testautomationComponentServiceDelegateFactory = TestautomationComponentServiceDelegateFactory
                        .getInstance();
                ProducePropertyDelegate produceProperty = testautomationComponentServiceDelegateFactory
                        .getProduceProperty();

                ProducePropertyMsg producePropertyMsg = new ProducePropertyMsg();
                producePropertyMsg.setPropertyType(propertyTypeMap.get(className));
                PropertyMsg response = produceProperty.produceProperty(producePropertyMsg);
                PropertyContainer propertyContainer = response.getPropertyContainer();
                result = propertyContainer;
            }
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
        cache.put(className, result.cloneObject());
        return result;
    }

    
	public static Datatype clone(Metadata metadata) {
		MetadataMsg rq = new MetadataMsg();
		rq.setMetadata(metadata);
		Metadata clone;
		try {
			clone = scriptComponentServiceDelegateFactory.getProduceMetadata().produceMetadataClone(rq).getMetadata();
			return clone;
		} catch (ClientException e) {
			Activator.getDefault().logError(e);
		}
		return null;
	}

	public static Datatype clone(MetadataLabel metadataLabel) {
		MetadataLabelMsg rq = new MetadataLabelMsg();
		rq.setMetadataLabel(metadataLabel);
		MetadataLabel clone;
		try {
			clone = scriptComponentServiceDelegateFactory.getProduceMetadata().produceMetadataLabelClone(rq).getMetadataLabel();
			return clone;
		} catch (ClientException e) {
			Activator.getDefault().logError(e);
		}
		return null;
	}

}
