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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.detail.action;

import java.util.List;
import java.util.Set;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.detail.widget.BaseTypeWidgetFactory;
import org.nabucco.framework.plugin.base.model.ViewModel;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.action.MetadataPickerActionComboWidgetCreator;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.detail.metadata.MetadataDetailPageViewLayouter;

import org.nabucco.testautomation.ui.rcp.multipage.detail.TestautomationDetailPageViewLayouter;

/**
 * ActionDetailPageViewLayouter
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class ActionDetailPageViewLayouter extends TestautomationDetailPageViewLayouter {

	private static final String PROPERTY_ACTION = "action";

	private static final String PROPERTY_METADATA = "metadata";

	/**
	 * Creates a new {@link MetadataDetailPageViewLayouter} instance.
	 * 
	 * @param title
	 *            the detail view title
	 */
	public ActionDetailPageViewLayouter(String title) {
		super(title);

	}

	@Override
	public Composite layout(Composite parent, Datatype datatype, String masterBlockId, Set<String> invisibleProperties, Set<String> readOnlyProperties,
			ViewModel externalViewModel, NabuccoMessageManager messageManager) {

		if (parent == null || datatype == null) {
			return null;
		}

		return super.layout(parent, datatype, masterBlockId, invisibleProperties, readOnlyProperties, externalViewModel, messageManager);
	}

	@Override
	protected Control layoutElement(Composite parent, BaseTypeWidgetFactory widgetFactory, Datatype datatype, String masterBlockId, Object property,
			String propertyName, GridData data, boolean readOnly, ViewModel externalViewModel, NabuccoMessageManager messageManager) {

		// Validate property multiplicity
		if ((property instanceof List<?>)) {
			return super.layoutElement(parent, widgetFactory, datatype, masterBlockId, property, propertyName, data, readOnly, externalViewModel,
					messageManager);
		}

		// Validate parent Type
		if (!(datatype instanceof Action)) {
			return super.layoutElement(parent, widgetFactory, datatype, masterBlockId, property, propertyName, data, readOnly, externalViewModel,
					messageManager);
		}

		// Validate property name
		if (propertyName.equalsIgnoreCase(PROPERTY_ACTION)) {
			return null;
		} else if (propertyName.equalsIgnoreCase(PROPERTY_METADATA)) {

			return this.layoutMetadataTable(parent, widgetFactory, datatype, masterBlockId, property, propertyName, data, readOnly, externalViewModel,
					messageManager);
		}

		return super.layoutElement(parent, widgetFactory, datatype, masterBlockId, property, propertyName, data, readOnly, externalViewModel, messageManager);
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
	private Control layoutMetadataTable(Composite parent, BaseTypeWidgetFactory widgetFactory, Datatype datatype, String masterBlockId, Object property,
			String propertyName, GridData data, boolean readOnly, ViewModel externalViewModel, NabuccoMessageManager messageManager) {

		NabuccoFormToolkit nft = widgetFactory.getNabuccoFormToolKit();

		MetadataPickerActionComboWidgetCreator widgetCreator = new MetadataPickerActionComboWidgetCreator(parent, widgetFactory, data, nft, externalViewModel,
				masterBlockId, (Metadata) property, (Action) datatype);
		Control[] newWidget = widgetCreator.createWidgets();

		if (newWidget.length > 0 && newWidget[0] == null) {
			Activator.getDefault().logError("Cannot create TestScriptPicker Widget [null].");
			newWidget[0] = nft.createRealLabel(parent, "INVALID");
			newWidget[1] = nft.createRealLabel(parent, "INVALID");
			newWidget[2] = nft.createRealLabel(parent, "INVALID");
		} else {
			super.setDataForWidget(data, newWidget[0]);
			super.setDataForWidget(data, newWidget[1]);
			GridData gridData = new GridData();
			gridData.grabExcessVerticalSpace = false;
			gridData.minimumHeight = 100;
			gridData.heightHint = 100;
			gridData.widthHint = data.widthHint;
			gridData.horizontalAlignment = GridData.FILL;
			gridData.grabExcessHorizontalSpace = true;
			newWidget[2].setLayoutData(gridData);
		}

		return newWidget[0];
	}
}
