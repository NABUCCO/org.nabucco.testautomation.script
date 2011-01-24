/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.browser.metadata;

import org.nabucco.framework.plugin.base.command.CommandHandler;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;
import org.nabucco.testautomation.script.ui.rcp.list.metadata.model.MetadataListViewModel;

/**
 * MetadataListViewBrowserElementHandler
 *
 * @author Undefined
 */
public interface MetadataListViewBrowserElementHandler extends CommandHandler {

    /**
     * CreateChildren.
     *
     * @param element the MetadataListViewBrowserElement.
     * @param viewModel the MetadataListViewModel.
     */
    void createChildren(final MetadataListViewModel viewModel,
            final MetadataListViewBrowserElement element);

    /**
     * RemoveChild.
     *
     * @param element the MetadataListViewBrowserElement.
     * @param toBeRemoved the BrowserElement.
     */
    void removeChild(final BrowserElement toBeRemoved, final MetadataListViewBrowserElement element);
}
