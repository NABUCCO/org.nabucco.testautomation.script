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
package org.nabucco.testautomation.script.impl.service.search;

import java.util.List;

import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.testautomation.property.facade.datatype.base.TestAutomationDatatype;
import org.nabucco.testautomation.property.facade.message.QuickSearchMsg;
import org.nabucco.testautomation.property.facade.message.QuickSearchRs;
import org.nabucco.testautomation.property.facade.service.search.WildcardSupport;

/**
 * QuickSearchServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class QuickSearchServiceHandlerImpl extends QuickSearchServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected QuickSearchRs quickSearch(QuickSearchMsg msg) throws SearchException {

        if (msg.getSearchString() == null || msg.getSearchString().getValue() == null) {
            throw new SearchException("No search string defined");
        }

        QuickSearchRs rs = new QuickSearchRs();

        String value = WildcardSupport.parse(msg.getSearchString().getValue());
        StringBuilder queryString = new StringBuilder();
        queryString.append("FROM TestScript s WHERE s.identificationKey LIKE '");
        queryString.append(value);
        queryString.append("' OR s.name LIKE '");
        queryString.append(value);
        queryString.append("'");

        try {
            NabuccoQuery<TestAutomationDatatype> query = super.getPersistenceManager().createQuery(
                    queryString.toString());

            List<TestAutomationDatatype> resultList = query.getResultList();
            rs.getResultList().addAll(resultList);

            queryString = new StringBuilder();
            queryString.append("FROM Metadata m WHERE m.identificationKey LIKE '");
            queryString.append(value);
            queryString.append("' OR m.name LIKE '");
            queryString.append(value);
            queryString.append("'");

            query = super.getPersistenceManager().createQuery(queryString.toString());

            resultList = query.getResultList();
            rs.getResultList().addAll(resultList);

            return rs;
        } catch (PersistenceException e) {
            throw new SearchException(e);
        }
    }

}
