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
package org.nabucco.testautomation.script.ui.rcp.search.metadata.view;

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
import org.nabucco.testautomation.script.ui.rcp.search.metadata.model.MetadataSearchViewModel;

/**
 * MetadataSearchViewWidgetFactory<p/>Search view for datatype Metadata<p/>
 *
 * @version 1.0
 * @author Markus Jorroch, PRODYNA AG, 2010-10-15
 */
public class MetadataSearchViewWidgetFactory extends WidgetFactory {

    private MetadataSearchViewModel model;

    public static final String LABEL_METADATANAME = "metadata.name";

    public static final String OBSERVE_VALUE_METADATANAME = MetadataSearchViewModel.PROPERTY_METADATA_NAME;

    public static final String LABEL_METADATAKEY = "metadata.identificationKey";

    public static final String OBSERVE_VALUE_METADATAKEY = MetadataSearchViewModel.PROPERTY_METADATA_IDENTIFICATIONKEY;

    /**
     * Constructs a new MetadataSearchViewWidgetFactory instance.
     *
     * @param aModel the MetadataSearchViewModel.
     * @param nabuccoFormToolKit the NabuccoFormToolkit.
     */
    public MetadataSearchViewWidgetFactory(final NabuccoFormToolkit nabuccoFormToolKit,
            final MetadataSearchViewModel aModel) {
        super(nabuccoFormToolKit);
        model = aModel;
    }

    /**
     * CreateLabelMetadataName.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelMetadataName(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_METADATANAME);
    }

    /**
     * CreateInputFieldMetadataName.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldMetadataName(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_METADATANAME);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelMetadataKey.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelMetadataKey(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_METADATAKEY);
    }

    /**
     * CreateInputFieldMetadataKey.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldMetadataKey(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_METADATAKEY);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }
}
