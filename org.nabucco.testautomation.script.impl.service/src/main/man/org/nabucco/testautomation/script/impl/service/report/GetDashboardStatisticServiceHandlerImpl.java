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
package org.nabucco.testautomation.script.impl.service.report;

import org.nabucco.framework.base.facade.datatype.Number;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.testautomation.script.facade.message.DashboardStatisticMsg;

/**
 * GetDashboardStatisticServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class GetDashboardStatisticServiceHandlerImpl extends GetDashboardStatisticServiceHandler {

    private static final long serialVersionUID = -8167928192317347976L;

    @Override
    protected DashboardStatisticMsg getDashboardStatistic(EmptyServiceMessage msg) throws SearchException {

        try {
            DashboardStatisticMsg rs = new DashboardStatisticMsg();

            NabuccoQuery<Long> query = super.getPersistenceManager().createQuery("SELECT count(s) FROM TestScript s");
            Long result = query.getSingleResult();
            rs.setNumTestScripts(new Number(result.intValue()));

            query = super.getPersistenceManager().createQuery("SELECT count(m) FROM Metadata m");
            result = query.getSingleResult();
            rs.setNumMetadata(new Number(result.intValue()));

            return rs;
        } catch (PersistenceException e) {
            throw new SearchException(e);
        }
    }

}
