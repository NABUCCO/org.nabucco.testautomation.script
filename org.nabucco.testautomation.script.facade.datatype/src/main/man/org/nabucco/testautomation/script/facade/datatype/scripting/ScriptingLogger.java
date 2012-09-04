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

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;

/**
 * ScriptingLogger
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ScriptingLogger implements NabuccoLogger {

    /** The delegated logger. */
    private Logger logger;

    /** The internal StringBuilder */
    private StringBuilder buf;

    /**
     * Creates a new {@link ScriptingLogger} instance that also logs into the given StringBuilder.
     * 
     * @param nams
     *            the name of the Script
     */
    public ScriptingLogger(String name) {
        this.buf = new StringBuilder();

        if (name != null) {
            this.logger = Logger.getLogger(name);
        } else {
            this.logger = Logger.getLogger("Script");
            this.logger.warn("No logging class defined using default 'Script'!");
        }
    }
    
    /**
     * Returns the content of the internal buffer.
     * 
     * @return the internal buffer as string
     */
    public String getInternalBuffer() {
        return this.buf.toString();
    }

    @Override
    public void fatal(Throwable throwable, Object... message) {
            this.logger.fatal(this.appendMessages(message), throwable);
            this.appendToBuilder("FATAL", message);
            this.appendToBuilder(throwable);
    }

    @Override
    public void fatal(Object... message) {
        this.logger.fatal(this.appendMessages(message));
        this.appendToBuilder("FATAL", message);
    }

    @Override
    public void error(Throwable throwable, Object... message) {
        this.logger.error(this.appendMessages(message), throwable);
        this.appendToBuilder("ERROR", message);
        this.appendToBuilder(throwable);
    }

    @Override
    public void error(Object... message) {
        this.logger.error(this.appendMessages(message));
        this.appendToBuilder("ERROR", message);
    }

    @Override
    public void warning(Throwable throwable, Object... message) {
            this.logger.warn(this.appendMessages(message), throwable);
            this.appendToBuilder("WARNING", message);
            this.appendToBuilder(throwable);
    }

    @Override
    public void warning(Object... message) {
        this.logger.warn(this.appendMessages(message));
        this.appendToBuilder("WARNING", message);
    }

    @Override
    public void info(Throwable throwable, Object... message) {
        if (this.logger.isInfoEnabled()) {
            this.logger.error(this.appendMessages(message), throwable);
            this.appendToBuilder("INFO", message);
            this.appendToBuilder(throwable);
        }
    }

    @Override
    public void info(Object... message) {
        if (this.logger.isInfoEnabled()) {
            this.logger.info(this.appendMessages(message));
            this.appendToBuilder("INFO", message);
        }
    }

    @Override
    public void debug(Throwable throwable, Object... message) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(this.appendMessages(message), throwable);
            this.appendToBuilder("DEBUG", message);
            this.appendToBuilder(throwable);
        }
    }

    @Override
    public void debug(Object... message) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(this.appendMessages(message));
            this.appendToBuilder("DEBUG", message);
        }
    }

    @Override
    public void trace(Object... message) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(this.appendMessages(message));
            this.appendToBuilder("TRACE", message);
        }
    }

    @Override
    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    @Override
    public boolean isTraceEnabled() {
        return this.logger.isTraceEnabled();
    }

    /**
     * Append the message fragments into a common string.
     * 
     * @param messages
     *            the message tokens
     * 
     * @return the resulting message
     */
    private String appendMessages(Object... messages) {

        int size = messages.length;

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < size; i++) {
            String message = String.valueOf(messages[i]);
            if (message != null) {
                result.append(messages[i]);
            }
        }

        return result.toString();
    }

    /**
     * Appends the message fragments to the internal StringBuilder
     * 
     * @param logLevel
     *            the logging level
     * @param messages
     *            the message tokens
     */
    private void appendToBuilder(String logLevel, Object... messages) {

        int size = messages.length;

        this.buf.append("[" + logLevel + "] ");
        this.buf.append("#" + this.logger.getName() + ": ");

        for (int i = 0; i < size; i++) {
            String message = String.valueOf(messages[i]);
            if (message != null) {
                this.buf.append(messages[i]);
            }
        }
        this.buf.append("\r\n");
    }

    /**
     * Appends the StackTrace of the given Throwable to the internal StringBuilder
     * 
     * @param logLevel
     *            the logging level
     * @param messages
     *            the message tokens
     */
    private void appendToBuilder(Throwable th) {
        
        if (th == null) {
            return;
        }
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        th.printStackTrace(pw);
        pw.flush();
        this.buf.append(sw.getBuffer().toString());
        this.buf.append("\r\n");
        pw.close();
    }
    
}
