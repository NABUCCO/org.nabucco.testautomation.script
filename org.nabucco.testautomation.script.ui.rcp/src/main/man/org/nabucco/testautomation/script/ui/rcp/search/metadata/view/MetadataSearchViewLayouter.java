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
package org.nabucco.testautomation.script.ui.rcp.search.metadata.view;

import java.util.List;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Section;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerCombo;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerComboParameter;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.layout.NabuccoLayouter;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.widgetcreators.ObjectListContentProvider;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.widgetcreators.metadata.subengine.SubEngineCodeLabelProvider;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataElementFactory;
import org.nabucco.testautomation.script.ui.rcp.search.metadata.model.MetadataSearchViewModel;
import org.nabucco.testautomation.script.ui.rcp.search.metadata.view.MetadataSearchView;
import org.nabucco.testautomation.script.ui.rcp.search.metadata.view.MetadataSearchViewWidgetFactory;


/**
 * Layouter for Metadata Search View.
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class MetadataSearchViewLayouter implements NabuccoLayouter<MetadataSearchViewModel> {

	private final static String MESSAGE_OWNER_ID = "org.nabucco.testautomation.script.ui.search.script.view.MetaDataSearchViewLayouter";
	private final static String TEST_SCRIPT_TEXT = MetadataSearchView.ID
	+ ".section";
	private final static String SUB_ENGINE = MetadataSearchView.ID
	+ ".subEngine";


	private MetadataSearchViewModel model;

	@Override
	public Composite layout(Composite parent,
			NabuccoMessageManager messageManager, MetadataSearchViewModel model,
			Layoutable<MetadataSearchViewModel> view) {
		return layout(parent, messageManager,
				(MetadataSearchViewModel) model);
	}

	@Override
	public Composite layout(Composite parent,
			final NabuccoMessageManager messageManager, MetadataSearchViewModel model) {
		this.model = model;

		NabuccoFormToolkit ntk = new NabuccoFormToolkit(parent);
		MetadataSearchViewWidgetFactory widgetFactory = new MetadataSearchViewWidgetFactory(
				ntk, model);

		// define paint listener
		parent.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent arg0) {
				messageManager.showMessages(MESSAGE_OWNER_ID);
			}
		});

		// build a section to host the other controls
		Section section = ntk.createSection(parent, TEST_SCRIPT_TEXT,
				new RowLayout());
		Composite child = ntk
		.createComposite(section, new GridLayout(2, false));
		section.setClient(child);

		// add description and name label and input fields (pair-wise)

		layoutName(widgetFactory, child);
		layoutDescription(widgetFactory, child);
		layoutSubEngineCode(ntk, child);

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
	private void layoutName(MetadataSearchViewWidgetFactory widgetFactory,
			Composite parent) {
		Label label = widgetFactory.createLabelMetadataName(parent);
		this.layout(label);

		Text text = widgetFactory.createInputFieldMetadataName(parent);
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
	private void layoutDescription(
			MetadataSearchViewWidgetFactory widgetFactory, Composite parent) {
		Label label = widgetFactory.createLabelMetadataDescription(parent);
		this.layout(label);

		Text text = widgetFactory.createInputFieldMetadataDescription(parent);
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

	/**
	 * Layout the control.
	 * 
	 * @param label
	 *            the control
	 */
	private void layout(Control control) {
		GridData data = new GridData();
		data.widthHint = 180;
		control.setLayoutData(data);
	}

	private void layoutSubEngineCode(NabuccoFormToolkit nabuccoFormToolkit,
			Composite child) {

		Label label = nabuccoFormToolkit.createRealLabel(child, SUB_ENGINE);
		this.layout(label);

		final List<SubEngineCode> subEngineCodes = MetadataElementFactory
		.getSubEngineCodes(null);
		ObjectListContentProvider subEngineCodeContentProvider = new ObjectListContentProvider(
				subEngineCodes);
		ElementPickerComboParameter subEngineCodeParameter = new ElementPickerComboParameter(
				subEngineCodeContentProvider, new SubEngineCodeLabelProvider());
		Control newWidget = nabuccoFormToolkit.createElementPickerCombo(child,
				subEngineCodeParameter, false, true);
		((ElementPickerCombo) newWidget)
		.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String selectedText = ((Combo) arg0.getSource())
				.getText();
				for (SubEngineCode subEngineCode : subEngineCodes) {
					String subEngineCodeName = subEngineCode.getName()
					.toString();
					if (subEngineCodeName.equals(selectedText)) {
						model.getMetadata().setSubEngine(subEngineCode);
					} else if(selectedText.isEmpty()){
						model.getMetadata().setSubEngine(null);
					}
				}
			}

		});

		this.layout(newWidget);
	}

}
