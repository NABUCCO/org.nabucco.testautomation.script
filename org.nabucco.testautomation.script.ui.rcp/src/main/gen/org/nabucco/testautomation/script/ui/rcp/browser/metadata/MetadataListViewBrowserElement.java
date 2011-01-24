/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.browser.metadata;

import java.util.List;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;
import org.nabucco.framework.plugin.base.model.browser.BrowserListElement;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.list.metadata.model.MetadataListViewModel;

/**
 * MetadataListViewBrowserElement
 *
 * @author Undefined
 */
public class MetadataListViewBrowserElement extends BrowserListElement<MetadataListViewModel>
        implements NabuccoInjectionReciever {

    private MetadataListViewBrowserElementHandler listViewBrowserElementHandler;

    /**
     * Constructs a new MetadataListViewBrowserElement instance.
     *
     * @param datatypeList the List<Metadata>.
     */
    public MetadataListViewBrowserElement(final List<Metadata> datatypeList) {
        this(datatypeList.toArray(new Metadata[datatypeList.size()]));
    }

    /**
     * Constructs a new MetadataListViewBrowserElement instance.
     *
     * @param datatypeArray the Metadata[].
     */
    public MetadataListViewBrowserElement(final Metadata[] datatypeArray) {
        super();
        NabuccoInjector instance = NabuccoInjector
                .getInstance(MetadataListViewBrowserElement.class);
        listViewBrowserElementHandler = instance
                .inject(MetadataListViewBrowserElementHandler.class);
        viewModel = new MetadataListViewModel();
        viewModel.setElements(datatypeArray);
    }

    @Override
    protected void createChildren() {
        this.clearChildren();
        listViewBrowserElementHandler.createChildren(viewModel, this);
    }

    @Override
    public void removeBrowserElement(final BrowserElement element) {
        super.removeBrowserElement(element);
        listViewBrowserElementHandler.removeChild(element, this);
    }
}
