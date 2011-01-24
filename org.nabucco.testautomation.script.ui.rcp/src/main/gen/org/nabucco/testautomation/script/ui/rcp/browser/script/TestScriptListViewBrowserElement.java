/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.browser.script;

import java.util.List;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;
import org.nabucco.framework.plugin.base.model.browser.BrowserListElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.ui.rcp.list.script.model.TestScriptListViewModel;

/**
 * TestScriptListViewBrowserElement
 *
 * @author Undefined
 */
public class TestScriptListViewBrowserElement extends BrowserListElement<TestScriptListViewModel>
        implements NabuccoInjectionReciever {

    private TestScriptListViewBrowserElementHandler listViewBrowserElementHandler;

    /**
     * Constructs a new TestScriptListViewBrowserElement instance.
     *
     * @param datatypeList the List<TestScript>.
     */
    public TestScriptListViewBrowserElement(final List<TestScript> datatypeList) {
        this(datatypeList.toArray(new TestScript[datatypeList.size()]));
    }

    /**
     * Constructs a new TestScriptListViewBrowserElement instance.
     *
     * @param datatypeArray the TestScript[].
     */
    public TestScriptListViewBrowserElement(final TestScript[] datatypeArray) {
        super();
        NabuccoInjector instance = NabuccoInjector
                .getInstance(TestScriptListViewBrowserElement.class);
        listViewBrowserElementHandler = instance
                .inject(TestScriptListViewBrowserElementHandler.class);
        viewModel = new TestScriptListViewModel();
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
