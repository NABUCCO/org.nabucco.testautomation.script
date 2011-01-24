/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.search.script.view;

import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.plugin.base.view.AbstractNabuccoSearchView;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.framework.plugin.base.view.NabuccoSearchView;
import org.nabucco.testautomation.script.ui.rcp.search.script.model.TestScriptSearchViewModel;

/**
 * TestScriptSearchView<p/>Search view for datatype TestScript<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-13
 */
public class TestScriptSearchView extends AbstractNabuccoSearchView<TestScriptSearchViewModel>
        implements NabuccoSearchView {

    private TestScriptSearchViewModel model;

    public static final String ID = "org.nabucco.testautomation.script.ui.search.script.TestScriptSearchView";

    /** Constructs a new TestScriptSearchView instance. */
    public TestScriptSearchView() {
        super();
        model = new TestScriptSearchViewModel(this.getCorrespondingListView());
    }

    @Override
    public void createPartControl(final Composite parent,
            final NabuccoMessageManager aMessageManager) {
        this.getLayouter().layout(parent, aMessageManager, model);
    }

    @Override
    public TestScriptSearchViewModel getModel() {
        return model;
    }

    @Override
    public String getId() {
        return TestScriptSearchView.ID;
    }
}
