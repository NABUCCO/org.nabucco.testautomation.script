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
package org.nabucco.testautomation.script.impl.service.maintain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;

/**
 * ReferenceSupport
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ReferenceSupport {

    private static ReferenceSupport instance;

    private ReferenceSupport() {
    }

    public static ReferenceSupport getInstance() {

        if (instance == null) {
            instance = new ReferenceSupport();
        }
        return instance;
    }

    public List<Long> findReferencingTestConfigElements(PersistenceManager pm, Long testScriptId) throws MaintainException {

        try {
            NabuccoQuery<BigInteger> query = pm
                    .createNativeQuery("SELECT DISTINCT test_config_element_test_script_list_id FROM conf_test_script_container WHERE test_script_ref_id = "
                            + testScriptId);

            List<BigInteger> result = query.getResultList();
            List<Long> idList = new ArrayList<Long>();

            for (BigInteger bigInt : result) {
                idList.add(bigInt.longValue());
            }
            return idList;
        } catch (PersistenceException e) {
            throw new MaintainException("Could search for referencing TestConfigElements", e);
        }
    }

}
