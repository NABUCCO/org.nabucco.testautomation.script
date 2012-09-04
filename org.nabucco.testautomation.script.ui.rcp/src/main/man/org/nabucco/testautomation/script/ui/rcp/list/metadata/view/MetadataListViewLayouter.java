/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.testautomation.script.ui.rcp.list.metadata.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoAbstractListLayouter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoDefaultListContentProvider;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoDefaultTableSorter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableColumnInfo;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableParameter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableViewer;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.list.metadata.model.MetadataListViewModel;
import org.nabucco.testautomation.script.ui.rcp.list.metadata.view.comparator.MetadataListViewMetadataDescriptionComparator;
import org.nabucco.testautomation.script.ui.rcp.list.metadata.view.comparator.MetadataListViewMetadataNameComparator;
import org.nabucco.testautomation.script.ui.rcp.list.metadata.view.label.MetadataListViewMetadataDescriptionLabelProvider;
import org.nabucco.testautomation.script.ui.rcp.list.metadata.view.label.MetadataListViewMetadataKeyLabelProvider;
import org.nabucco.testautomation.script.ui.rcp.list.metadata.view.label.MetadataListViewMetadataNameLabelProvider;

/**
 * MetadataListViewLayouter
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class MetadataListViewLayouter extends NabuccoAbstractListLayouter<MetadataListViewModel> {

    private static final String DESCRIPTION_COLUMN_KEY = "org.nabucco.testautomation.script.ui.rcp.list.metadata.view.description";

    private static final String NAME_COLUMN_KEY = "org.nabucco.testautomation.script.ui.rcp.list.metadata.view.name";

    private static final String KEY_COLUMN_KEY = "org.nabucco.testautomation.script.ui.rcp.list.metadata.view.key";

    /**
     * Layouts the table
     **/
    @Override
    public NabuccoTableViewer layout(Composite parent, NabuccoMessageManager messageManager,
            MetadataListViewModel model, Layoutable<MetadataListViewModel> view) {

        NabuccoFormToolkit ntk = new NabuccoFormToolkit(parent);

        MetadataListViewWidgetFactory widgetFactory = new MetadataListViewWidgetFactory(ntk);

        NabuccoTableParameter parameter = new NabuccoTableParameter(new NabuccoDefaultTableSorter<Metadata>(
                createComparators()), new MetadataListViewTableFilter(), new NabuccoDefaultListContentProvider(model),
                createTableColumnInfo(), getDoubleClickCommand(view.getManagedFormViewPart()));

        return widgetFactory.createTable(parent, parameter, model);
    }

    private List<Comparator<Metadata>> createComparators() {
        List<Comparator<Metadata>> comparators = new ArrayList<Comparator<Metadata>>();
        comparators.add(new MetadataListViewMetadataNameComparator());
        comparators.add(new MetadataListViewMetadataDescriptionComparator());

        return comparators;
    }

    /**
     * Creates needed tables.
     * 
     * @return table columns
     */
    private NabuccoTableColumnInfo[] createTableColumnInfo() {
        NabuccoTableColumnInfo col1 = new NabuccoTableColumnInfo(KEY_COLUMN_KEY, KEY_COLUMN_KEY, 150, SWT.LEFT,
                SWT.CENTER, new MetadataListViewMetadataKeyLabelProvider());

        col1.setHidable(false);

        NabuccoTableColumnInfo col2 = new NabuccoTableColumnInfo(NAME_COLUMN_KEY, NAME_COLUMN_KEY, 400, SWT.LEFT,
                SWT.CENTER, new MetadataListViewMetadataNameLabelProvider());

        col2.setHidable(false);

        NabuccoTableColumnInfo col3 = new NabuccoTableColumnInfo(DESCRIPTION_COLUMN_KEY, DESCRIPTION_COLUMN_KEY, 400,
                SWT.LEFT, SWT.LEFT, new MetadataListViewMetadataDescriptionLabelProvider());

        return new NabuccoTableColumnInfo[] { col1, col2, col3 };
    }

}
