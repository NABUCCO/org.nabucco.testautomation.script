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
package org.nabucco.testautomation.script.ui.rcp.list.metadata.view;

import java.io.Serializable;
import java.util.Map;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Form;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoComponentListView;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoCompositeTextFilter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableViewer;
import org.nabucco.testautomation.script.ui.rcp.list.metadata.model.MetadataListViewModel;

/**
 * MetadataListView<p/>ListView for Metadata objects<p/>
 *
 * @author Markus Jorroch, PRODYNA AG, 2010-10-15
 */
public class MetadataListView extends NabuccoComponentListView<MetadataListViewModel> {

    public static final String ID = "org.nabucco.testautomation.script.ui.list.metadata.MetadataListView";

    public static final String TAB_TITLE = (ID + ".tabTitle");

    public static final String TITLE = (ID + ".title");

    /** Constructs a new MetadataListView instance. */
    public MetadataListView() {
        super();
        model = new MetadataListViewModel();
    }

    @Override
    protected void createFormControl(Form form) {
        Composite o = this.getLayouter().layout(form.getBody(), this.getMessageManager(), model, this);
        if ((o instanceof NabuccoTableViewer)) {
            tableViewer = ((NabuccoTableViewer) o);
        }
    }

    @Override
    protected NabuccoCompositeTextFilter createFilter(final Composite parent) {
        return new NabuccoCompositeTextFilter(parent);
    }

    @Override
    public void setFocus() {
    }

    /**
     * Getter for the Values.
     *
     * @return the Map<String, Serializable>.
     */
    public Map<String, Serializable> getValues() {
        return model.getValues();
    }

    @Override
    public String getNewPartName() {
        return I18N.i18n(TAB_TITLE, this.getValues());
    }

    @Override
    public String getManagedFormTitle() {
        return I18N.i18n(TITLE);
    }

    @Override
    public String getId() {
        return MetadataListView.ID;
    }
}
