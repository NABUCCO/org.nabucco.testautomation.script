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

import java.util.Map;

import org.nabucco.common.scripting.runner.ScriptExecutionException;
import org.nabucco.testautomation.property.facade.datatype.PropertyList;
import org.nabucco.testautomation.script.facade.datatype.scripting.exception.FunctionException;

/**
 * FunctionScript
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public abstract class FunctionScript extends TestAutomationScriptable {

    public static final String RESULT = "result";

    @Override
    public final void execute(Map<String, Object> inputParameter, Map<String, Object> outputParameter)
            throws ScriptExecutionException {

        super.execute(inputParameter, outputParameter);

        // Get the input parameter
        PropertyList propertyList = (PropertyList) inputParameter.get(PROPERTY_LIST);

        // Call the function implementation
        PropertyList resultProperty = this.executeFunction(propertyList);

        // Set the result
        FunctionResult result = (FunctionResult) outputParameter.get(RESULT);

        if (result == null) {
            throw new ScriptExecutionException("ReturnParameter with name '" + RESULT + "' not found");
        }
        result.setPropertyList(resultProperty);
    }

    /**
     * This operation provides the implementation of the function. It may use the given input
     * parameter and may return a PropertyList or null, if the function does not return any value.
     * 
     * @param propertyList
     *            the input parameter
     * @return a PropertyList or null
     * @throws FunctionException
     *             thrown, if an error occurs during the execution of the function
     */
    protected abstract PropertyList executeFunction(PropertyList propertyList) throws FunctionException;

}
