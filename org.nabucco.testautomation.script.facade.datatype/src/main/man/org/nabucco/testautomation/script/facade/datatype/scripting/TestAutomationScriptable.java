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
import org.nabucco.common.scripting.runner.java.JavaScriptable;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;

/**
 * TestAutomationScriptable
 * 
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public abstract class TestAutomationScriptable implements JavaScriptable {

    public static final String PROPERTY_LIST = "propertyList";
    
    public static final String LOGGER = "logger";

    private NabuccoLogger logger;

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Map<String, Object> inputParameter, Map<String, Object> outputParameter)
            throws ScriptExecutionException {
        
        // Set the logger
        this.logger = (NabuccoLogger) inputParameter.get(LOGGER);
        
        if (this.logger == null) {
            throw new ScriptExecutionException("InputParameter with name '" + LOGGER + "' not found");
        }
    }

    /**
     * Returns the logger set for the current execution.
     * 
     * @return the logger
     */
    protected NabuccoLogger getLogger() {
        return this.logger;
    }

}
