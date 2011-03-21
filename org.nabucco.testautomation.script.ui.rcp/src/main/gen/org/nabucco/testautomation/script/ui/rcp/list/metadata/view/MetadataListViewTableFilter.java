/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.list.metadata.view;

import org.eclipse.jface.viewers.Viewer;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableFilter;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;

/**
 * MetadataListViewTableFilter<p/>ListView for Metadata objects<p/>
 *
 * @author Markus Jorroch, PRODYNA AG, 2010-10-15
 */
public class MetadataListViewTableFilter extends NabuccoTableFilter {

    /** Constructs a new MetadataListViewTableFilter instance. */
    public MetadataListViewTableFilter() {
        super();
    }

    @Override
    public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
        boolean result = false;
        if (((null == searchFilter.getFilter()) || (0 == searchFilter.getFilter().length()))) {
            result = true;
        } else if ((element instanceof Metadata)) {
            Metadata datatype = ((Metadata) element);
            result = (result || this.contains(datatype.getIdentificationKey(),
                    searchFilter.getFilter()));
            result = (result || this.contains(datatype.getName(), searchFilter.getFilter()));
            result = (result || this.contains(datatype.getDescription(), searchFilter.getFilter()));
            result = (result || this.contains(datatype.getOwner(), searchFilter.getFilter()));
        }
        return result;
    }
}
