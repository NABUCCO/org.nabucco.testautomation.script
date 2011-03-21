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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.action.metadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.util.TreeUtility;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.ui.rcp.command.metadata.OpenMetadataHandlerImpl;
import org.nabucco.testautomation.script.ui.rcp.communication.ScriptComponentServiceDelegateFactory;
import org.nabucco.testautomation.script.ui.rcp.communication.search.SearchMetadataDelegate;
import org.nabucco.testautomation.ui.rcp.base.dialog.OwnerSelectionTreePickerDialog;
import org.nabucco.testautomation.ui.rcp.base.dialog.OwnerSelectionTreePickerDialogParameter;

/**
 * MetadataTreePickerDialog
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class MetadataTreePickerDialog extends OwnerSelectionTreePickerDialog {

    public static final String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.widgetcreators.action.metadata.MetadataTreePickerDialog";

    private static final String EDIT = "editMetadata";

    private static Object[] lastTreePath;

    private Metadata selectedMetadata;

    public MetadataTreePickerDialog(Shell parentShell,
            OwnerSelectionTreePickerDialogParameter parameter, Metadata selectedMetadata) {
        super(parentShell, parameter);
        setInitialExpandLevel(1);
        this.selectedMetadata = selectedMetadata;
    }

    @Override
    protected void createViewer(Composite parent) {
        super.createViewer(parent);

        if (this.selectedMetadata != null) {
            this.treeViewer.collapseAll();

            // Resolve parents of selected Metadata
            if (this.selectedMetadata.getParent() == null) {
                try {
                    SearchMetadataDelegate delegate = ScriptComponentServiceDelegateFactory
                            .getInstance().getSearchMetadata();
                    MetadataMsg rq = new MetadataMsg();
                    rq.setMetadata(selectedMetadata);
                    this.selectedMetadata = delegate.getParents(rq).getMetadata();
                } catch (ClientException e) {
                    Activator.getDefault().logError(e);
                }
            }

            // Expand Tree and select Metadata
            if (this.selectedMetadata.getParent() != null) {
                List<Metadata> parents = new ArrayList<Metadata>();
                Metadata p = this.selectedMetadata.getParent();

                while ((p = p.getParent()) != null) {
                    parents.add(p);
                }

                Collections.reverse(parents);

                for (Metadata pm : parents) {
                    TreeUtility.expand(this.treeViewer, pm);
                }

                this.select(this.selectedMetadata.getParent(), this.selectedMetadata);
            } else {
                this.select(this.selectedMetadata, this.selectedMetadata);
            }
        } else if (lastTreePath != null) {
            this.treeViewer.setExpandedElements(lastTreePath);
        }
    }

    private void select(Object parent, Object node) {
        TreeUtility.expand(this.treeViewer, parent);

        TreePath[] paths = this.treeViewer.getExpandedTreePaths();
        for (TreePath path : paths) {
            if (path.getLastSegment().equals(parent)) {
                TreePath childPath = path.createChildPath(node);
                this.treeViewer.setSelection(new TreeSelection(childPath), true);
                break;
            }
        }
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {

        // increment the number of columns in the button bar
        ((GridLayout) parent.getLayout()).numColumns++;
        Button button = new Button(parent, SWT.PUSH);
        button.setText(I18N.i18n(ID + "." + EDIT));
        button.setFont(JFaceResources.getDialogFont());
        button.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent event) {
                openMetadataForEdit();
            }
        });
        setButtonLayoutData(button);

        super.createButtonsForButtonBar(parent);
    }

    private void openMetadataForEdit() {
        StructuredSelection selection = (StructuredSelection) this.treeViewer.getSelection();
        if (selection != null) {
            Metadata selectedMetadata = (Metadata) selection.getFirstElement();
            OpenMetadataHandlerImpl openMetadataHandlerImpl = new OpenMetadataHandlerImpl(
                    selectedMetadata.getId());
            openMetadataHandlerImpl.run();
        }
        this.treeViewer.setSelection(null);
        this.close();
    }

    @Override
    protected void okPressed() {
        lastTreePath = this.treeViewer.getExpandedElements();
        super.okPressed();
    }

}
