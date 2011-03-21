/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.list.script.view;

import org.eclipse.jface.viewers.Viewer;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableFilter;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;

/**
 * TestScriptListViewTableFilter<p/>ListView for TestScripts<p/>
 *
 * @author Stefan Huettenrauch, PRODYNA AG, 2010-05-25
 */
public class TestScriptListViewTableFilter extends NabuccoTableFilter {

    /** Constructs a new TestScriptListViewTableFilter instance. */
    public TestScriptListViewTableFilter() {
        super();
    }

    @Override
    public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
        boolean result = false;
        if (((null == searchFilter.getFilter()) || (0 == searchFilter.getFilter().length()))) {
            result = true;
        } else if ((element instanceof TestScript)) {
            TestScript datatype = ((TestScript) element);
            result = (result || this.contains(datatype.getIdentificationKey(),
                    searchFilter.getFilter()));
            result = (result || this.contains(datatype.getName(), searchFilter.getFilter()));
            result = (result || this.contains(datatype.getDescription(), searchFilter.getFilter()));
            result = (result || this.contains(datatype.getOwner(), searchFilter.getFilter()));
        }
        return result;
    }
}
