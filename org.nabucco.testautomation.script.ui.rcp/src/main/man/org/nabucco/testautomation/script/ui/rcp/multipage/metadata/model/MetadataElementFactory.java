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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.testautomation.property.facade.datatype.BooleanProperty;
import org.nabucco.testautomation.property.facade.datatype.DateProperty;
import org.nabucco.testautomation.property.facade.datatype.FileProperty;
import org.nabucco.testautomation.property.facade.datatype.NumericProperty;
import org.nabucco.testautomation.property.facade.datatype.PropertyList;
import org.nabucco.testautomation.property.facade.datatype.SqlProperty;
import org.nabucco.testautomation.property.facade.datatype.TextProperty;
import org.nabucco.testautomation.property.facade.datatype.XPathProperty;
import org.nabucco.testautomation.property.facade.datatype.XmlProperty;
import org.nabucco.testautomation.property.facade.datatype.base.Property;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyContainer;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyType;
import org.nabucco.testautomation.property.facade.message.ProducePropertyMsg;
import org.nabucco.testautomation.property.facade.message.PropertyMsg;
import org.nabucco.testautomation.property.ui.rcp.communication.PropertyComponentServiceDelegateFactory;
import org.nabucco.testautomation.property.ui.rcp.communication.produce.ProducePropertyDelegate;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;
import org.nabucco.testautomation.script.facade.message.MetadataLabelMsg;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeListMsg;
import org.nabucco.testautomation.script.facade.message.SubEngineCodeSearchMsg;
import org.nabucco.testautomation.script.ui.rcp.communication.ScriptComponentServiceDelegateFactory;
import org.nabucco.testautomation.script.ui.rcp.communication.produce.ProduceScriptDelegate;
import org.nabucco.testautomation.script.ui.rcp.communication.search.SearchScriptDelegate;

/**
 * MetadataElementFactory
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class MetadataElementFactory {

    private static List<SubEngineCode> subEngineCodeList = null;

    private static Map<Class<?>, Datatype> cache = new HashMap<Class<?>, Datatype>();

    private static Map<Class<? extends Property>, PropertyType> propertyTypeMap = new HashMap<Class<? extends Property>, PropertyType>();

    private static ProduceScriptDelegate produceScriptDelegate;

    private static ProducePropertyDelegate producePropertyDelegate;

    static {
        propertyTypeMap.put(PropertyList.class, PropertyType.LIST);
        propertyTypeMap.put(BooleanProperty.class, PropertyType.BOOLEAN);
        propertyTypeMap.put(DateProperty.class, PropertyType.DATE);
        propertyTypeMap.put(NumericProperty.class, PropertyType.NUMERIC);
        propertyTypeMap.put(FileProperty.class, PropertyType.FILE);
        propertyTypeMap.put(TextProperty.class, PropertyType.TEXT);
        propertyTypeMap.put(XPathProperty.class, PropertyType.XPATH);
        propertyTypeMap.put(XmlProperty.class, PropertyType.XML);
        propertyTypeMap.put(SqlProperty.class, PropertyType.SQL);
    }

    private static void init() {

        if (produceScriptDelegate == null) {
            try {
                produceScriptDelegate = ScriptComponentServiceDelegateFactory.getInstance().getProduceScript();
            } catch (ClientException e) {
                Activator.getDefault().logError(e);
            }
        }

        if (producePropertyDelegate == null) {
            try {
                producePropertyDelegate = PropertyComponentServiceDelegateFactory.getInstance().getProduceProperty();
            } catch (ClientException e) {
                Activator.getDefault().logError(e);
            }
        }

        if (subEngineCodeList == null) {
            try {
                SearchScriptDelegate searchScriptDelegate = ScriptComponentServiceDelegateFactory.getInstance()
                        .getSearchScript();
                SubEngineCodeSearchMsg rq = new SubEngineCodeSearchMsg();
                SubEngineCodeListMsg response = searchScriptDelegate.searchSubEngineCode(rq);

                if (response != null) {
                    subEngineCodeList = new ArrayList<SubEngineCode>();
                    subEngineCodeList.addAll(response.getSubEngineCodeList());
                }
            } catch (ClientException e) {
                Activator.getDefault().logError(e);
            }
        }
    }

    public static List<SubEngineCode> getSubEngineCodes(Metadata metadata) {
        init();
        return subEngineCodeList;
    }

    public static Datatype create(Class<?> className) {
        
        init();
        Datatype cachedElement = cache.get(className);

        if (cachedElement != null) {
            return cachedElement.cloneObject();
        }
        Datatype result = null;

        try {
            if (className.equals(Metadata.class)) {
                MetadataMsg response = produceScriptDelegate.produceMetadata(new EmptyServiceMessage());
                if (response != null) {
                    Metadata metadata = response.getMetadata();
                    result = metadata;
                }
            } else if (className.equals(MetadataLabel.class)) {
                MetadataLabelMsg response = produceScriptDelegate.produceMetadataLabel(new EmptyServiceMessage());
                if (response != null) {
                    result = response.getMetadataLabel();
                }
            } else if (propertyTypeMap.keySet().contains(className)) {
                ProducePropertyMsg producePropertyMsg = new ProducePropertyMsg();
                producePropertyMsg.setPropertyType(propertyTypeMap.get(className));
                PropertyMsg response = producePropertyDelegate.produceProperty(producePropertyMsg);
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

        init();
        MetadataMsg rq = new MetadataMsg();
        rq.setMetadata(metadata);
        Metadata clone;

        try {
            clone = produceScriptDelegate.produceMetadataClone(rq).getMetadata();
            return clone;
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
        return null;
    }

    public static Datatype clone(MetadataLabel metadataLabel) {

        init();
        MetadataLabelMsg rq = new MetadataLabelMsg();
        rq.setMetadataLabel(metadataLabel);
        MetadataLabel clone;

        try {
            clone = produceScriptDelegate.produceMetadataLabelClone(rq).getMetadataLabel();
            return clone;
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
        return null;
    }

}
