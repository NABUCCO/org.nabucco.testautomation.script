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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.masterdetails.widgetcreators.action.metadata;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.nabucco.framework.plugin.base.layout.ImageProvider;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.ui.rcp.images.ScriptImageRegistry;


/**
 * MetadataPickerDialogLabelProvider
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MetadataPickerDialogLabelProvider implements ILabelProvider {

	@Override
	public void dispose() {
	}

	@Override
	public void addListener(ILabelProviderListener arg0) {
	}

	@Override
	public boolean isLabelProperty(Object arg0, String arg1) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener arg0) {
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof Metadata) {
			return ImageProvider.createImage(ScriptImageRegistry.ICON_METADATA
					.getId());
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		String result = null;
		if (element instanceof Metadata) {
			Metadata metadata = (Metadata) element;
			if (metadata.getName() != null) {
				result = metadata.getName().getValue();
			}
		}
		return result;
	}

}
