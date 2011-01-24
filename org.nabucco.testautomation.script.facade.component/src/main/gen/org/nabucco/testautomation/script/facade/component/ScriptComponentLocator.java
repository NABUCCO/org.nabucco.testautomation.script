/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.component;

import org.nabucco.framework.base.facade.component.locator.ComponentLocator;
import org.nabucco.framework.base.facade.component.locator.ComponentLocatorSupport;

/**
 * Locator for ScriptComponent.
 *
 * @author NABUCCO Generator, PRODYNA AG
 */
public class ScriptComponentLocator extends ComponentLocatorSupport<ScriptComponent> implements
        ComponentLocator<ScriptComponent> {

    private static final String JNDI_NAME = ((((ComponentLocator.COMPONENTS + "/") + ScriptComponent.COMPONENT_NAME) + "/") + "org.nabucco.testautomation.script.facade.component.ScriptComponent");

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

    /**
     * Getter for the Instance.
     *
     * @return the ScriptComponentLocator.
     */
    public static ScriptComponentLocator getInstance() {
        if ((instance == null)) {
            instance = new ScriptComponentLocator(JNDI_NAME, ScriptComponent.class);
        }
        return instance;
    }
}
