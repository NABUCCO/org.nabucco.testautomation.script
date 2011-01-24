/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.browser.script;

import org.nabucco.framework.plugin.base.command.CommandHandler;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;
import org.nabucco.testautomation.script.ui.rcp.list.script.model.TestScriptListViewModel;

/**
 * TestScriptListViewBrowserElementHandler
 *
 * @author Undefined
 */
public interface TestScriptListViewBrowserElementHandler extends CommandHandler {

    /**
     * CreateChildren.
     *
     * @param element the TestScriptListViewBrowserElement.
     * @param viewModel the TestScriptListViewModel.
     */
    void createChildren(final TestScriptListViewModel viewModel,
            final TestScriptListViewBrowserElement element);

    /**
     * RemoveChild.
     *
     * @param element the TestScriptListViewBrowserElement.
     * @param toBeRemoved the BrowserElement.
     */
    void removeChild(final BrowserElement toBeRemoved,
            final TestScriptListViewBrowserElement element);
}
