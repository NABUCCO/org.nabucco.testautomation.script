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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.widgetcreators.metadata.subengine;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.MasterTreeExternalDatatypeTransfer;
import org.nabucco.testautomation.script.facade.datatype.code.CodeParameter;


/**
 * SubEngineOperationParamterDragListener
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class SubEngineOperationParamterDragListener implements DragSourceListener {

    private TableViewer viewer;

    public SubEngineOperationParamterDragListener(TableViewer viewer) {
        this.viewer = viewer;
    }

    @Override
    public void dragStart(DragSourceEvent event) {
        Activator.getDefault().logInfo("Drag Started!");

        event.doit = !this.viewer.getSelection().isEmpty();
    }

    @Override
    public void dragSetData(DragSourceEvent event) {
        ISelection selection = this.viewer.getSelection();
        if (selection instanceof IStructuredSelection) {
            Object element = ((IStructuredSelection) selection).getFirstElement();

            if (element instanceof CodeParameter) {
            	CodeParameter codeParameter = (CodeParameter) element;

                if (MasterTreeExternalDatatypeTransfer.getInstance().isSupportedType(event.dataType)) {
                    event.data = codeParameter;
                    return;
                }
            }
        }
        Activator.getDefault().logWarning("Drag not supported!");
    }

    @Override
    public void dragFinished(DragSourceEvent event) {
        Activator.getDefault().logInfo("Drag Finished!");
    }

}
