/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
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

    public static final String LABEL_METADATADESCRIPTION = "metadata.description";

    public static final String OBSERVE_VALUE_METADATADESCRIPTION = MetadataSearchViewModel.PROPERTY_METADATA_DESCRIPTION;

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
        IObservableValue modelElement = BeansObservables.observeValue(model,
                OBSERVE_VALUE_METADATANAME);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelMetadataDescription.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelMetadataDescription(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_METADATADESCRIPTION);
    }

    /**
     * CreateInputFieldMetadataDescription.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldMetadataDescription(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model,
                OBSERVE_VALUE_METADATADESCRIPTION);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }
}
