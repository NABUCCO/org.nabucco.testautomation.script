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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.widgetcreators;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerContentProvider;

/**
 * ObjectListContentProvider
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class ObjectListContentProvider implements ElementPickerContentProvider {

    private List<? extends Object> elementList;

    /**
     * Creates a new {@link ObjectListContentProvider} instance.
     * 
     * @param enumeration
     *            the enumeration
     */
    public ObjectListContentProvider(List<? extends Object> elementList) {
        this.elementList = elementList;
    }

    @Override
    public String[] getPaths() {
        return null;
    }

    @Override
    public Object[] getElements(Object inputElement) {
        List<Object> list = new LinkedList<Object>();
        if (this.elementList != null) {
            for (Object element : this.elementList) {
                list.add(element);
            }
        }
        return list.toArray();
    }

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        if (newInput instanceof List<?>) {
            this.elementList = (List<?>) newInput;
        }
    }

}
