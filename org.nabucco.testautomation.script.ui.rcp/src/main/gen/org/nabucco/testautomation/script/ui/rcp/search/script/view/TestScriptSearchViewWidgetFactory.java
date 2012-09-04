/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
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

    public static final String LABEL_TESTSCRIPTKEY = "testScript.identificationKey";

    public static final String OBSERVE_VALUE_TESTSCRIPTKEY = TestScriptSearchViewModel.PROPERTY_TESTSCRIPT_IDENTIFICATIONKEY;

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
        IObservableValue modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_TESTSCRIPTNAME);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelTestScriptKey.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelTestScriptKey(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_TESTSCRIPTKEY);
    }

    /**
     * CreateInputFieldTestScriptKey.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldTestScriptKey(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_TESTSCRIPTKEY);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }
}
