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
package org.nabucco.testautomation.script.facade.datatype.scripting.exception;

import org.nabucco.common.scripting.runner.ScriptExecutionException;

/**
 * FunctionException<p/>Exception signaling that a function failed<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2011-09-15
 */
public class FunctionException extends ScriptExecutionException {

    private static final long serialVersionUID = 1L;

    /** Constructs a new FunctionException instance. */
    public FunctionException() {
        super();
    }

    /**
     * Constructs a new FunctionException instance.
     *
     * @param message the String.
     */
    public FunctionException(String message) {
        super(message);
    }

    /**
     * Constructs a new FunctionException instance.
     *
     * @param cause the Exception.
     */
    public FunctionException(Exception cause) {
        super(cause);
    }

    /**
     * Constructs a new FunctionException instance.
     *
     * @param cause the Exception.
     * @param message the String.
     */
    public FunctionException(String message, Exception cause) {
        super(message, cause);
    }
}
