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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.detail.testscript;

import java.util.List;
import java.util.Set;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.widget.BaseTypeWidgetFactory;
import org.nabucco.framework.plugin.base.model.ViewModel;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.testscript.folder.FolderPickerActionComboWidgetCreator;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.detail.metadata.MetadataDetailPageViewLayouter;

import org.nabucco.testautomation.ui.rcp.multipage.detail.TestautomationDetailPageViewLayouter;

/**
 * TestScriptDetailPageViewLayouter
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class TestScriptDetailPageViewLayouter extends TestautomationDetailPageViewLayouter {

	private static final String PROPERTY_FOLDER = "folder";

	/**
	 * Creates a new {@link MetadataDetailPageViewLayouter} instance.
	 * 
	 * @param title
	 *            the detail view title
	 */
	public TestScriptDetailPageViewLayouter(String title) {
		super(title);
	}

	@Override
	public Composite layout(Composite parent, Datatype datatype,
			String masterBlockId, Set<String> invisibleProperties,
			Set<String> readOnlyProperties, ViewModel externalViewModel,
			NabuccoMessageManager messageManager) {

		if (parent == null || datatype == null) {
			return null;
		}

		NabuccoFormToolkit nft = new NabuccoFormToolkit(parent);
		super.addWidgetCreator(new FolderPickerActionComboWidgetCreator(nft));

		return super.layout(parent, datatype, masterBlockId,
				invisibleProperties, readOnlyProperties, externalViewModel,
				messageManager);
	}

	@Override
	protected Control layoutElement(Composite parent,
			BaseTypeWidgetFactory widgetFactory, Datatype datatype,
			String masterBlockId, NabuccoProperty property,
			GridData data, boolean readOnly, ViewModel externalViewModel,
			NabuccoMessageManager messageManager) {

		Object instance = property.getInstance();

		// Validate property multiplicity
		if (instance instanceof List<?>) {
			return super.layoutElement(parent, widgetFactory, datatype,
					masterBlockId, property, data, readOnly,
					externalViewModel, messageManager);
		}

		// Validate parent Type
		if (!(datatype instanceof TestScript)) {
			return super.layoutElement(parent, widgetFactory, datatype,
					masterBlockId, property, data, readOnly,
					externalViewModel, messageManager);
		}

		// Validate property name
		String propertyName = property.getName();
		
		if (propertyName.equalsIgnoreCase(PROPERTY_FOLDER)) {
			return this.layoutFolderPicker(parent, widgetFactory, datatype,
					masterBlockId, property, data, readOnly,
					externalViewModel, messageManager);
		}

		return super.layoutElement(parent, widgetFactory, datatype,
				masterBlockId, property, data, readOnly,
				externalViewModel, messageManager);
	}

	/**
	 * Layouts the {@link TestScript} table.
	 * 
	 * @param parent
	 *            the parent widget
	 * @param widgetFactory
	 *            the widget factory
	 * @param datatype
	 *            the datatype
	 * @param masterBlockId
	 *            the id
	 * @param property
	 *            the property instance
	 * @param propertyName
	 *            the property name
	 * @param data
	 *            the grid data
	 * @param readOnly
	 *            the read onyl flag
	 * @param externalViewModel
	 *            the external view model
	 * @param messageManager
	 *            the message manager
	 * 
	 * @return the layouted table
	 */
	private Control layoutFolderPicker(Composite parent,
			BaseTypeWidgetFactory widgetFactory, Datatype datatype,
			String masterBlockId, NabuccoProperty property,
			GridData data, boolean readOnly, ViewModel externalViewModel,
			NabuccoMessageManager messageManager) {

		readOnly = !property.getConstraints().isEditable() || readOnly;
		
		NabuccoFormToolkit nft = widgetFactory.getNabuccoFormToolKit();

		FolderPickerActionComboWidgetCreator widgetCreator = new FolderPickerActionComboWidgetCreator(
				nft);
		Control newWidget = widgetCreator.createWidget(parent,
				(Folder) property.getInstance(), null, datatype, readOnly,
				externalViewModel, messageManager, property.getName(), data,
				masterBlockId);

		if (newWidget == null) {
            Activator.getDefault().logError("Cannot create TestScriptPicker Widget [null].");
            newWidget = nft.createRealLabel(parent, "INVALID");
        } else {
            super.setDataForWidget(data, newWidget);
        }

		return newWidget;
	}
}
