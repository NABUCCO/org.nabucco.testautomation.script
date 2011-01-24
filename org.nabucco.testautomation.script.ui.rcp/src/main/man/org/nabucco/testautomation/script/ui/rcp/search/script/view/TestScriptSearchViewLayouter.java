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
package org.nabucco.testautomation.script.ui.rcp.search.script.view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Section;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.layout.NabuccoLayouter;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.testautomation.script.ui.rcp.search.script.model.TestScriptSearchViewModel;
import org.nabucco.testautomation.script.ui.rcp.search.script.view.TestScriptSearchView;
import org.nabucco.testautomation.script.ui.rcp.search.script.view.TestScriptSearchViewWidgetFactory;


/**
 * Layouter for TestScript Search View.
 * 
 * @author Stefan Huettenrauch, PRODYNA AG
 * 
 */
public class TestScriptSearchViewLayouter implements NabuccoLayouter<TestScriptSearchViewModel> {

    private final static String MESSAGE_OWNER_ID = "org.nabucco.testautomation.script.ui.search.script.view.TestScriptSearchViewLayouter";

    private final static String SECTION_TITLE = TestScriptSearchView.ID + ".section";


    @Override
    public Composite layout(Composite parent, NabuccoMessageManager messageManager,
    		TestScriptSearchViewModel model, Layoutable<TestScriptSearchViewModel> view) {
        return layout(parent, messageManager, model);
    }

    @Override
    public Composite layout(Composite parent, final NabuccoMessageManager messageManager, TestScriptSearchViewModel model) {
        NabuccoFormToolkit ntk = new NabuccoFormToolkit(parent);
        TestScriptSearchViewWidgetFactory widgetFactory = new TestScriptSearchViewWidgetFactory(
                ntk, model);

        // define paint listener
        parent.addPaintListener(new PaintListener() {

            @Override
            public void paintControl(PaintEvent arg0) {
                messageManager.showMessages(MESSAGE_OWNER_ID);
            }
        });

        // build a section to host the other controls
        Section section = ntk.createSection(parent, SECTION_TITLE, new RowLayout());
        Composite child = ntk.createComposite(section, new GridLayout(2, false));
        section.setClient(child);

        // add description and name label and input fields (pair-wise)

        layoutName(widgetFactory, child);
        layoutDescription(widgetFactory, child);

        return null;
    }

    /**
     * Layouts the name label and text field.
     * 
     * @param widgetFactory
     *            the widget factory.
     * @param parent
     *            the parent composite
     */
    private void layoutName(TestScriptSearchViewWidgetFactory widgetFactory, Composite parent) {
        Label label = widgetFactory.createLabelTestScriptName(parent);
        this.layout(label);

        Text text = widgetFactory.createInputFieldTestScriptName(parent);
        this.layout(text);
    }

    /**
     * Layouts the description label and text field.
     * 
     * @param widgetFactory
     *            the widget factory
     * @param parent
     *            the parent composite
     */
    private void layoutDescription(TestScriptSearchViewWidgetFactory widgetFactory, Composite parent) {
        Label label = widgetFactory.createLabelTestScriptDescription(parent);
        this.layout(label);

        Text text = widgetFactory.createInputFieldTestScriptDescription(parent);
        this.layout(text);
    }

    /**
     * Layout the text field.
     * 
     * @param text
     *            the text field
     */
    private void layout(Text text) {
        GridData data = new GridData();
        data.widthHint = 180;
        text.setLayoutData(data);
    }

    /**
     * Layout the label.
     * 
     * @param label
     *            the label
     */
    private void layout(Label label) {
        GridData data = new GridData();
        data.widthHint = 100;
        label.setLayoutData(data);
    }

}
