/*
* Copyright 2010 PRODYNA AG
*
* Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.opensource.org/licenses/eclipse-1.0.php or
* http://www.nabucco-source.org/nabucco-license.html
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.nabucco.testautomation.script.ui.rcp.list.script.view;

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
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.ui.rcp.list.script.model.TestScriptListViewModel;
import org.nabucco.testautomation.script.ui.rcp.list.script.view.TestScriptListViewTableFilter;
import org.nabucco.testautomation.script.ui.rcp.list.script.view.TestScriptListViewWidgetFactory;
import org.nabucco.testautomation.script.ui.rcp.list.script.view.comparator.TestScriptListViewTestScriptDescriptionComparator;
import org.nabucco.testautomation.script.ui.rcp.list.script.view.comparator.TestScriptListViewTestScriptNameComparator;
import org.nabucco.testautomation.script.ui.rcp.list.script.view.label.TestScriptListViewTestScriptDescriptionLabelProvider;
import org.nabucco.testautomation.script.ui.rcp.list.script.view.label.TestScriptListViewTestScriptNameLabelProvider;


/**
 * @author Stefan Hüttenrauch, PRODYNA AG
 * 
 */
public class TestScriptListViewLayouter extends NabuccoAbstractListLayouter<TestScriptListViewModel> {

    /**
     * Layouts the table
     **/
    @Override
    public NabuccoTableViewer layout(final Composite parent,
            final NabuccoMessageManager messageManager, final TestScriptListViewModel model,
            final Layoutable<TestScriptListViewModel> view) {

        NabuccoFormToolkit ntk = new NabuccoFormToolkit(parent);

        TestScriptListViewWidgetFactory widgetFactory = new TestScriptListViewWidgetFactory(ntk);

        NabuccoTableParameter parameter = new NabuccoTableParameter(
                new NabuccoDefaultTableSorter<TestScript>(createComparators()),
                new TestScriptListViewTableFilter(), new NabuccoDefaultListContentProvider(model),
                createTableColumnInfo(), getDoubleClickCommand(view.getManagedFormViewPart()));

        return widgetFactory.createTable(parent, parameter, model);
    }

    private List<Comparator<TestScript>> createComparators() {
        List<Comparator<TestScript>> comparators = new ArrayList<Comparator<TestScript>>();
        comparators.add(new TestScriptListViewTestScriptNameComparator());
        comparators.add(new TestScriptListViewTestScriptDescriptionComparator());

        return comparators;
    }

    /**
     * Creates needed tables.
     * 
     * @return table columns
     */
    private NabuccoTableColumnInfo[] createTableColumnInfo() {
        NabuccoTableColumnInfo[] result = {
                new NabuccoTableColumnInfo("Name", "This is a script name.", 200, SWT.CENTER,
                        SWT.CENTER, new TestScriptListViewTestScriptNameLabelProvider()),
                new NabuccoTableColumnInfo("Description", "This is a script description", 300,
                        SWT.LEFT, SWT.LEFT,
                        new TestScriptListViewTestScriptDescriptionLabelProvider()) };

        return result;
    }

}
