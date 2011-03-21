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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.detail.metadata;

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
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.widgetcreators.metadata.subengine.WidgetCreatorForSubEngineComboPair;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataElementFactory;

import org.nabucco.testautomation.ui.rcp.multipage.detail.TestautomationDetailPageViewLayouter;

/**
 * MetadataDetailPageViewLayouter
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class MetadataDetailPageViewLayouter extends TestautomationDetailPageViewLayouter {

	private static final String PROPERTY_SUB_ENGINE = "subEngine";

	private static final String PROPERTY_OPERATION = "operation";

	/**
	 * Creates a new {@link MetadataDetailPageViewLayouter} instance.
	 * 
	 * @param title
	 *            the detail view title
	 */
	public MetadataDetailPageViewLayouter(String title) {
		super(title);
	}

	@Override
	public Composite layout(Composite parent, Datatype datatype, String masterBlockId,
			Set<String> invisibleProperties, Set<String> readOnlyProperties,
			ViewModel externalViewModel, NabuccoMessageManager messageManager) {

		if (parent == null || datatype == null) {
			return null;
		}

		return super.layout(parent, datatype, masterBlockId, invisibleProperties,
				readOnlyProperties, externalViewModel, messageManager);
	}

	@Override
	protected Control layoutElement(Composite parent, BaseTypeWidgetFactory widgetFactory,
			Datatype datatype, String masterBlockId, NabuccoProperty property,
			GridData data, boolean readOnly, ViewModel externalViewModel,
			NabuccoMessageManager messageManager) {

		// Validate property multiplicity
		if ((property instanceof List<?>)) {
			return super.layoutElement(parent, widgetFactory, datatype, masterBlockId, property,
					data, readOnly, externalViewModel, messageManager);
		}

		// Validate parent Type
		if (!(datatype instanceof Metadata)) {
			return super.layoutElement(parent, widgetFactory, datatype, masterBlockId, property,
					data, readOnly, externalViewModel, messageManager);
		}

		// Validate property name
		String propertyName = property.getName();
		
		if (propertyName.equalsIgnoreCase(PROPERTY_SUB_ENGINE)) {

			return this.layoutSubEngineDropDownBoxPair(parent, widgetFactory, datatype,
					masterBlockId, property, propertyName, data, readOnly, externalViewModel,
					messageManager);

		} else if (propertyName.equalsIgnoreCase(PROPERTY_OPERATION)) {
			return null;
		}

		return super.layoutElement(parent, widgetFactory, datatype, masterBlockId, property,
				data, readOnly, externalViewModel, messageManager);
	}

	private Control layoutSubEngineDropDownBoxPair(Composite parent,
			BaseTypeWidgetFactory widgetFactory, Datatype datatype, String masterBlockId,
			NabuccoProperty property, String propertyName, GridData data, boolean readOnly,
			ViewModel externalViewModel, NabuccoMessageManager messageManager) {

		readOnly = !property.getConstraints().isEditable() || readOnly;
		
		Metadata metadata = (Metadata) datatype;
		List<SubEngineCode> subEngineCodes = MetadataElementFactory.getSubEngineCodes(metadata);
		try {
			NabuccoFormToolkit nft = widgetFactory.getNabuccoFormToolKit();
			WidgetCreatorForSubEngineComboPair widgetCreatorForSubEngineCode = new WidgetCreatorForSubEngineComboPair(
					parent, widgetFactory, data, nft, externalViewModel, masterBlockId, metadata, subEngineCodes, readOnly);
			Control[] newWidget = widgetCreatorForSubEngineCode.createWidget();
			if (newWidget.length > 0 && newWidget[0] == null) {
				Activator.getDefault().logError(
						"Cannot create Metadata Widget [null].");
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

		} catch (SecurityException e) {
			Activator.getDefault().logError(e);
		}
		return null;

	}

}
