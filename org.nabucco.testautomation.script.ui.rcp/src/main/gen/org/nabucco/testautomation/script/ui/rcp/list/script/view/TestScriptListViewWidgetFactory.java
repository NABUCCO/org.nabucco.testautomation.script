/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.list.script.view;

import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableParameter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableViewer;
import org.nabucco.framework.plugin.base.layout.WidgetFactory;
import org.nabucco.framework.plugin.base.model.ListViewModel;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;

/**
 * TestScriptListViewWidgetFactory<p/>ListView for TestScripts<p/>
 *
 * @author Stefan Huettenrauch, PRODYNA AG, 2010-05-25
 */
public class TestScriptListViewWidgetFactory extends WidgetFactory {

    /**
     * Constructs a new TestScriptListViewWidgetFactory instance.
     *
     * @param nabuccoFormToolKit the NabuccoFormToolkit.
     */
    public TestScriptListViewWidgetFactory(NabuccoFormToolkit nabuccoFormToolKit) {
        super(nabuccoFormToolKit);
    }

    /**
     * CreateTable.
     *
     * @param model the ListViewModel<?>.
     * @param parameter the NabuccoTableParameter.
     * @param parent the Composite.
     * @return the NabuccoTableViewer.
     */
    public NabuccoTableViewer createTable(Composite parent, NabuccoTableParameter parameter,
            ListViewModel<?> model) {
        return nabuccoFormToolKit.createNabuccoTable(parent, parameter, model);
    }
}
