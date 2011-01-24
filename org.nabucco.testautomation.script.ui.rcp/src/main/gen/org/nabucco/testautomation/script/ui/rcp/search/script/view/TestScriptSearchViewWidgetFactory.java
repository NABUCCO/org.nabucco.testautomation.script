/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.search.script.view;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.nabucco.framework.plugin.base.layout.WidgetFactory;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.testautomation.script.ui.rcp.search.script.model.TestScriptSearchViewModel;

/**
 * TestScriptSearchViewWidgetFactory<p/>Search view for datatype TestScript<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-13
 */
public class TestScriptSearchViewWidgetFactory extends WidgetFactory {

    private TestScriptSearchViewModel model;

    public static final String LABEL_TESTSCRIPTNAME = "testScript.name";

    public static final String OBSERVE_VALUE_TESTSCRIPTNAME = TestScriptSearchViewModel.PROPERTY_TESTSCRIPT_NAME;

    public static final String LABEL_TESTSCRIPTDESCRIPTION = "testScript.description";

    public static final String OBSERVE_VALUE_TESTSCRIPTDESCRIPTION = TestScriptSearchViewModel.PROPERTY_TESTSCRIPT_DESCRIPTION;

    /**
     * Constructs a new TestScriptSearchViewWidgetFactory instance.
     *
     * @param aModel the TestScriptSearchViewModel.
     * @param nabuccoFormToolKit the NabuccoFormToolkit.
     */
    public TestScriptSearchViewWidgetFactory(final NabuccoFormToolkit nabuccoFormToolKit,
            final TestScriptSearchViewModel aModel) {
        super(nabuccoFormToolKit);
        model = aModel;
    }

    /**
     * CreateLabelTestScriptName.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelTestScriptName(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_TESTSCRIPTNAME);
    }

    /**
     * CreateInputFieldTestScriptName.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldTestScriptName(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model,
                OBSERVE_VALUE_TESTSCRIPTNAME);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelTestScriptDescription.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelTestScriptDescription(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_TESTSCRIPTDESCRIPTION);
    }

    /**
     * CreateInputFieldTestScriptDescription.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldTestScriptDescription(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model,
                OBSERVE_VALUE_TESTSCRIPTDESCRIPTION);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }
}
