/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.search.metadata.view;

import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.plugin.base.view.AbstractNabuccoSearchView;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.framework.plugin.base.view.NabuccoSearchView;
import org.nabucco.testautomation.script.ui.rcp.search.metadata.model.MetadataSearchViewModel;

/**
 * MetadataSearchView<p/>Search view for datatype Metadata<p/>
 *
 * @version 1.0
 * @author Markus Jorroch, PRODYNA AG, 2010-10-15
 */
public class MetadataSearchView extends AbstractNabuccoSearchView<MetadataSearchViewModel>
        implements NabuccoSearchView {

    private MetadataSearchViewModel model;

    public static final String ID = "org.nabucco.testautomation.script.ui.search.metadata.MetadataSearchView";

    /** Constructs a new MetadataSearchView instance. */
    public MetadataSearchView() {
        super();
        model = new MetadataSearchViewModel(this.getCorrespondingListView());
    }

    @Override
    public void createPartControl(final Composite parent,
            final NabuccoMessageManager aMessageManager) {
        this.getLayouter().layout(parent, aMessageManager, model);
    }

    @Override
    public MetadataSearchViewModel getModel() {
        return model;
    }

    @Override
    public String getId() {
        return MetadataSearchView.ID;
    }
}
