/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.nabucco.testautomation.script.facade.component;

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.component.locator.ComponentLocator;
import org.nabucco.framework.base.facade.component.locator.ComponentLocatorSupport;

/**
 * Locator for ScriptComponent.
 *
 * @author NABUCCO Generator, PRODYNA AG
 */
public class ScriptComponentLocator extends ComponentLocatorSupport<ScriptComponent> implements
        ComponentLocator<ScriptComponent> {

    private static ScriptComponentLocator instance;

    /**
     * Constructs a new ScriptComponentLocator instance.
     *
     * @param component the Class<ScriptComponent>.
     * @param jndiName the String.
     */
    private ScriptComponentLocator(String jndiName, Class<ScriptComponent> component) {
        super(jndiName, component);
    }

    @Override
    public ScriptComponent getComponent() throws ConnectionException {
        ScriptComponent component = super.getComponent();
        if ((component instanceof ScriptComponentLocal)) {
            return new ScriptComponentLocalProxy(((ScriptComponentLocal) component));
        }
        return component;
    }

    /**
     * Getter for the Instance.
     *
     * @return the ScriptComponentLocator.
     */
    public static ScriptComponentLocator getInstance() {
        if ((instance == null)) {
            instance = new ScriptComponentLocator(ScriptComponent.JNDI_NAME, ScriptComponent.class);
        }
        return instance;
    }
}
