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
package org.nabucco.testautomation.script.impl.service;

import java.util.List;

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.testautomation.property.facade.component.PropertyComponentLocator;
import org.nabucco.testautomation.property.facade.datatype.PropertyList;
import org.nabucco.testautomation.property.facade.datatype.base.Property;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyType;
import org.nabucco.testautomation.property.facade.message.ProducePropertyMsg;
import org.nabucco.testautomation.property.facade.message.PropertyListMsg;
import org.nabucco.testautomation.property.facade.message.PropertyMsg;
import org.nabucco.testautomation.property.facade.message.PropertySearchMsg;
import org.nabucco.testautomation.property.facade.service.maintain.MaintainProperty;
import org.nabucco.testautomation.property.facade.service.produce.ProduceProperty;
import org.nabucco.testautomation.property.facade.service.search.SearchProperty;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;

/**
 * PropertySupport
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class PropertySupport {

    private static PropertySupport instance;

    private SearchProperty searchProperty;

    private MaintainProperty maintainProperty;

    private ProduceProperty produceProperty;

    private PropertySupport() {
    }

    public static synchronized PropertySupport getInstance() {

        if (instance == null) {
            instance = new PropertySupport();
        }
        return instance;
    }

    public void resolveProperties(TestScriptElement testScriptElement, ServiceMessageContext ctx)
            throws SearchException {

        // resolve PropertyList
        if (testScriptElement != null && testScriptElement.getPropertyListRefId() != null) {
            PropertyList properties = getPropertyList(testScriptElement.getPropertyListRefId(), ctx);
            testScriptElement.setPropertyList(properties);
        }
    }

    public void resolveProperties(MetadataLabel label, ServiceMessageContext ctx) throws SearchException {

        // resolve PropertyList
        if (label != null && label.getPropertyListRefId() != null) {
            PropertyList properties = getPropertyList(label.getPropertyListRefId(), ctx);
            label.setPropertyList(properties);
        }
    }

    public PropertyList maintainPropertyList(PropertyList property, ServiceMessageContext ctx) throws MaintainException {

        if (this.maintainProperty == null) {
            try {
                initMaintain();
            } catch (ServiceException e) {
                throw new MaintainException(e);
            } catch (ConnectionException e) {
                throw new MaintainException(e);
            }
        }

        ServiceRequest<PropertyMsg> rq = new ServiceRequest<PropertyMsg>(ctx);
        PropertyMsg msg = new PropertyMsg();
        msg.setProperty(property);
        rq.setRequestMessage(msg);
        PropertyMsg rs = this.maintainProperty.maintainProperty(rq).getResponseMessage();
        return (PropertyList) rs.getProperty();
    }

    public PropertyList producePropertyList(ServiceMessageContext ctx) throws ProduceException {

        if (this.produceProperty == null) {
            try {
                initProduce();
            } catch (ServiceException e) {
                throw new ProduceException(e);
            } catch (ConnectionException e) {
                throw new ProduceException(e);
            }
        }

        ServiceRequest<ProducePropertyMsg> rq = new ServiceRequest<ProducePropertyMsg>(ctx);
        ProducePropertyMsg msg = new ProducePropertyMsg();
        msg.setPropertyType(PropertyType.LIST);
        rq.setRequestMessage(msg);
        PropertyMsg rs = this.produceProperty.produceProperty(rq).getResponseMessage();
        PropertyList property = (PropertyList) rs.getProperty();
        return property;
    }

    public PropertyList getPropertyList(Long refId, ServiceMessageContext ctx) throws SearchException {

        if (refId == null) {
            return null;
        }

        if (this.searchProperty == null) {
            try {
                initSearch();
            } catch (ServiceException e) {
                throw new SearchException(e);
            } catch (ConnectionException e) {
                throw new SearchException(e);
            }
        }

        ServiceRequest<PropertySearchMsg> rq = new ServiceRequest<PropertySearchMsg>(ctx);
        PropertySearchMsg msg = new PropertySearchMsg();
        msg.setPropertyId(new Identifier(refId));
        rq.setRequestMessage(msg);
        PropertyListMsg rs = this.searchProperty.searchProperty(rq).getResponseMessage();
        List<Property> resultList = rs.getPropertyList();

        if (resultList.isEmpty()) {
            return null;
        }
        return (PropertyList) resultList.get(0);
    }

    private void initSearch() throws ServiceException, ConnectionException {
        this.searchProperty = PropertyComponentLocator.getInstance().getComponent().getSearchProperty();
    }

    private void initMaintain() throws ServiceException, ConnectionException {
        this.maintainProperty = PropertyComponentLocator.getInstance().getComponent().getMaintainProperty();
    }

    private void initProduce() throws ServiceException, ConnectionException {
        this.produceProperty = PropertyComponentLocator.getInstance().getComponent().getProduceProperty();
    }

}
