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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.detail.fileproperty;

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
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.widgetcreators.fileproperty.NameContentCombinationWidgetCreator;

import org.nabucco.testautomation.facade.datatype.property.FileProperty;
import org.nabucco.testautomation.ui.rcp.multipage.detail.TestautomationDetailPageViewLayouter;

/**
 * MetadataDetailPageViewLayouter
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class FilePropertyDetailPageViewLayouter extends
TestautomationDetailPageViewLayouter {

	private static final String PROPERTY_NAME = "name";
	
	private static final String PROPERTY_CONTENT = "content";

	/**
	 * Creates a new {@link FilePropertyDetailPageViewLayouter} instance.
	 * 
	 * @param title
	 *            the detail view title
	 */
	public FilePropertyDetailPageViewLayouter(String title) {
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

		NabuccoFormToolkit nabuccoFormToolkit = new NabuccoFormToolkit(parent);
		super.addWidgetCreator(new NameContentCombinationWidgetCreator(
				nabuccoFormToolkit));

		return super.layout(parent, datatype, masterBlockId,
				invisibleProperties, readOnlyProperties, externalViewModel,
				messageManager);
	}

	@Override
	protected Control layoutElement(Composite parent,
			BaseTypeWidgetFactory widgetFactory, Datatype datatype,
			String masterBlockId, Object property, String propertyName,
			GridData data, boolean readOnly, ViewModel externalViewModel,
			NabuccoMessageManager messageManager) {

		// Validate property multiplicity
		if ((property instanceof List<?>)) {
			return super.layoutElement(parent, widgetFactory, datatype,
					masterBlockId, property, propertyName, data, readOnly,
					externalViewModel, messageManager);
		}

		// Validate parent Type
		if (!(datatype instanceof FileProperty)) {
			return super.layoutElement(parent, widgetFactory, datatype,
					masterBlockId, property, propertyName, data, readOnly,
					externalViewModel, messageManager);
		}

		// Validate property name
		if (propertyName.equalsIgnoreCase(PROPERTY_CONTENT)) {
			return null;
		}
			
		if (propertyName.equalsIgnoreCase(PROPERTY_NAME)) {

			return this.layoutFileChooser(parent, widgetFactory, datatype,
					masterBlockId, property, propertyName, data, readOnly,
					externalViewModel, messageManager);

		}

		return super.layoutElement(parent, widgetFactory, datatype,
				masterBlockId, property, propertyName, data, readOnly,
				externalViewModel, messageManager);
	}

	private Control layoutFileChooser(Composite parent,
			BaseTypeWidgetFactory widgetFactory, Datatype datatype,
			String masterBlockId, Object property, String propertyName,
			GridData data, boolean readOnly, ViewModel externalViewModel,
			NabuccoMessageManager messageManager) {

		FileProperty fileProperty = (FileProperty) datatype;
		NabuccoFormToolkit nft = widgetFactory.getNabuccoFormToolKit();
		NameContentCombinationWidgetCreator widgetCreator = new NameContentCombinationWidgetCreator(nft);
		Control[] newWidget = widgetCreator.createWidget(parent, data, fileProperty, externalViewModel, widgetFactory, masterBlockId);
		
		if (newWidget.length > 0 && newWidget[0] == null) {
			Activator.getDefault().logError(
					"Cannot create FileProperty Widget [null].");
			newWidget[0] = nft.createRealLabel(parent, "INVALID");
		} else {
			for (Control control : newWidget) {
				super.setDataForWidget(data, control);
			}
		}
		return null;
	}
}
