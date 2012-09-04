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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.masterdetails.widgetcreators.scripting;

import java.lang.reflect.Method;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.widget.AbstractBaseTypeWidgetCreator;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.widget.BaseTypeWidgetFactory;
import org.nabucco.framework.plugin.base.component.newpicker.composite.element.ElementPickerComposite;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.tree.TreePickerDialog;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.tree.TreePickerDialogLabel;
import org.nabucco.framework.plugin.base.component.newpicker.dialog.tree.TreePickerDialogParameter;
import org.nabucco.framework.plugin.base.model.ViewModel;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;

/**
 * ScriptPickerActionComboWidgetCreator
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ScriptPickerActionComboWidgetCreator extends AbstractBaseTypeWidgetCreator<Script> {

    private static final String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.script.maintenance.masterdetails.widgetcreators.scripting.ScriptPicker";

    private static final String TITLE = ID + ".dialogTitle";

    private static final String SHELL_TITLE = ID + ".shellTitle";

    private static final String MESSAGE = ID + ".dialogMessage";

    private static final String TREE_TITLE = ID + ".treeTitle";

    private boolean readOnly;

    /**
     * Creates a new {@link ScriptPickerActionComboWidgetCreator} instance.
     * 
     * @param nft
     *            the form toolkit
     */
    public ScriptPickerActionComboWidgetCreator(NabuccoFormToolkit nft) {
        super(nft);
    }

    public Control createWidget(Composite parent, Script script, Method method, Object dataObject, boolean readOnly,
            ViewModel viewModel, NabuccoMessageManager messageManager, String propertyName, GridData data,
            String masterBlockId) {

        Control result = null;
        this.readOnly = readOnly;

        TestScriptElement testScriptElement = (TestScriptElement) dataObject;
        ScriptPickerActionComboMiniModel model = new ScriptPickerActionComboMiniModel(viewModel, testScriptElement);
        model.setScript(script);
        BaseTypeWidgetFactory widgetFactory = new BaseTypeWidgetFactory(super.getFormToolkit());

        // Layout Folder-Picker and Label
        Label label = widgetFactory.createLabel(parent, masterBlockId + "." + ScriptPickerActionComboMiniModel.SCRIPT);
        label.setToolTipText(label.getText());
        label.setLayoutData(data);
        result = this.layoutPicker(parent, testScriptElement, viewModel, model);

        return result;
    }

    /**
     * Layout the script table.
     * 
     * @param parent
     *            the parent composite
     * @param model
     * 
     * @return the layouted table
     */
    private Control layoutPicker(Composite parent, TestScriptElement testScriptElement, ViewModel viewModel,
            ScriptPickerActionComboMiniModel model) {

        ElementPickerComposite<TreePickerDialog> picker = this.createPicker(parent, model, testScriptElement);

        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(picker.getTextField(), SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model,
                ScriptPickerActionComboMiniModel.SCRIPT_NAME);

        bindingContext.bindValue(uiElement, modelElement, null, null);

        return picker;
    }

    /**
     * Creates the picker composite.
     * 
     * @param parent
     *            the parent composite
     * @param model
     *            the mini model
     * 
     * @return the layouted composite
     */
    private ElementPickerComposite<TreePickerDialog> createPicker(Composite parent,
            ScriptPickerActionComboMiniModel model, TestScriptElement testScriptElement) {

        TreePickerDialogLabel label = new TreePickerDialogLabel(TITLE, MESSAGE, SHELL_TITLE, "Tree", TREE_TITLE);

        TreePickerDialogParameter parameter = new TreePickerDialogParameter(label, model);
        parameter.setContentProvider(new ScriptPickerDialogContentProvider(testScriptElement.getType()));
        parameter.setLabelProvider(new ScriptPickerDialogLabelProvider());

        TreePickerDialog dialog = new ScriptTreePickerDialog(parent.getShell(), parameter);

        dialog.addSelectionListener(ScriptPickerDialogListener.ID, new ScriptPickerDialogListener(model));

        return new ElementPickerComposite<TreePickerDialog>(parent, SWT.NONE, dialog, this.readOnly);
    }

    @Override
    protected Control createWidget(Composite parent, Script specialized, Method method, Object object,
            boolean readOnly, ViewModel externalViewModel, NabuccoMessageManager messageManager, String propertyName) {
        throw new UnsupportedOperationException();
    }

}
