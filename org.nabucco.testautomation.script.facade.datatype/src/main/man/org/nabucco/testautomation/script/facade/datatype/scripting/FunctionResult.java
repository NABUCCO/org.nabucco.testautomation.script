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
package org.nabucco.testautomation.script.facade.datatype.scripting;

import org.nabucco.testautomation.property.facade.datatype.PropertyList;


/**
 * FunctionResult
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class FunctionResult {

    private PropertyList propertyList;

    /**
     * @param propertyList the propertyList to set
     */
    public void setPropertyList(PropertyList propertyList) {
        this.propertyList = propertyList;
    }

    /**
     * @return the propertyList
     */
    public PropertyList getPropertyList() {
        return propertyList;
    }
    
}
