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
package org.nabucco.testautomation.script.ui.rcp.multipage.folder.model;

import java.util.Map;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Menu;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableColumnInfo;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableSorter;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNodeCreatorForAllDatatypes;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.addDialog.AddDialogLabelProvider;
import org.nabucco.framework.plugin.base.component.multipage.xml.DatatypeXMLEditorTextPartCreator;
import org.nabucco.framework.plugin.base.component.multipage.xml.XMLEditorTextPart;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerContentProvider;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerDefaultContentProvider;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerParameter;
import org.nabucco.framework.plugin.base.component.picker.dialog.LabelForDialog;
import org.nabucco.framework.plugin.base.logging.NabuccoLogMessage;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;


/**
 * FolderMaintenanceMultiPageEditViewModelHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class FolderMaintenanceMultiPageEditViewModelHandlerImpl implements
        FolderMaintenanceMultiPageEditViewModelHandler {

    public static final String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.folder.model.FolderMaintenanceMultiPageEditViewModelHandlerImpl";

    @Override
    public Menu getContextMenu(ISelection selection, TreeViewer treeViewer) {
        return DataModelManager.getContextMenu(selection, treeViewer, this);
    }

    /**
     * Return whether possible children are available.
     */
    @Override
    public boolean hasPossibleChildren(Datatype datatype) {
        return DataModelManager.hasPossibleChildren(datatype);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Datatype[]> getPossibleChildren(Datatype datatype) {
        return DataModelManager.getPossibleChildren(datatype);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MasterDetailTreeNode addChild(final MasterDetailTreeNode parent, final Datatype newChild) {
        MasterDetailTreeNode result = null;
        Datatype parentDatatype = parent.getDatatype();

        // Only to composites children should be added
        if (newChild instanceof Folder) {
        	Folder newFolder = (Folder) newChild;
            if (parentDatatype instanceof Folder) {
            	Folder parentFolder = (Folder) parentDatatype;
                result = addChildToTree(parent, newFolder);
                parentFolder.getSubFolderList().add(newFolder);
            }
        } else if (newChild instanceof TestScript) {
        	TestScript newTestScript = (TestScript) newChild;
            if (parentDatatype instanceof Folder) {
            	Folder parentFolder = (Folder) parentDatatype;
                result = addChildToTree(parent, newTestScript);
                parentFolder.getTestScriptList().add(newTestScript);
            }
        } 
        if (result == null) {
            Activator.getDefault().logError(
                    new NabuccoLogMessage(
                            FolderMaintenanceMultiPageEditViewModelHandlerImpl.class,
                            "FAILED to add new child!"));
        }
        return result;
    }

    private MasterDetailTreeNode addChildToTree(final MasterDetailTreeNode parent,
            final Datatype newChild) {
        MasterDetailTreeNode result = MasterDetailTreeNodeCreatorForAllDatatypes.getInstance()
                .create(newChild, parent);
        if (newChild instanceof TestScript) {
            Datatype parentDatatype = parent.getDatatype();
            if (parentDatatype instanceof Folder) {
            	Folder parentFolder = (Folder) parentDatatype;
                parent.getChildren().add(parentFolder.getTestScriptList().size(), result);
            } else {
                Activator
                        .getDefault()
                        .logError(
                                new NabuccoLogMessage(
                                        FolderMaintenanceMultiPageEditViewModelHandlerImpl.class,
                                        "FAILED to add new child. MetadataLabel can only be added to Metadata!"));
            }
        } else {
            parent.getChildren().add(result);
        }
        Datatype datatype = parent.getDatatype();
        if (datatype.getDatatypeState() == DatatypeState.PERSISTENT) {
            datatype.setDatatypeState(DatatypeState.MODIFIED);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(ISelection child) {
        StructuredSelection ssel = (StructuredSelection) child;
        MasterDetailTreeNode nodeToRemove = (MasterDetailTreeNode) ssel.getFirstElement();
        Datatype datatypeToRemove = nodeToRemove.getDatatype();
        MasterDetailTreeNode parentNode = nodeToRemove.getParent();
        Datatype parentDatatype = parentNode.getDatatype();
        boolean removedFromBusinessModel = false;
        if (parentDatatype instanceof Folder) {
            Folder parentFolder = (Folder) parentDatatype;
            if (datatypeToRemove instanceof Folder) {
                removedFromBusinessModel = parentFolder.getSubFolderList().remove(datatypeToRemove);
            }
            if (datatypeToRemove instanceof TestScript) {
                removedFromBusinessModel = parentFolder.getTestScriptList().remove(datatypeToRemove);
            }
        } 
        if (removedFromBusinessModel) {
            if (datatypeToRemove.getDatatypeState() == DatatypeState.PERSISTENT || datatypeToRemove.getDatatypeState() == DatatypeState.MODIFIED) {
                datatypeToRemove.setDatatypeState(DatatypeState.DELETED);
            }
            if (parentDatatype.getDatatypeState() == DatatypeState.PERSISTENT) {
                parentDatatype.setDatatypeState(DatatypeState.MODIFIED);
            }
            if (!parentNode.getChildren().remove(nodeToRemove)){
                Activator.getDefault().logError(
                        new NabuccoLogMessage(
                                FolderMaintenanceMultiPageEditViewModelHandlerImpl.class,
                                "FAILED to remove datatype from tree!"));
            }
        } else {
            Activator.getDefault().logError(
                    new NabuccoLogMessage(
                            FolderMaintenanceMultiPageEditViewModelHandlerImpl.class,
                            "FAILED to remove datatype from business model!"));
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public MasterDetailTreeNode createMasterDetailRepresentation(Datatype datatype) {
        return MasterDetailTreeNodeCreatorForAllDatatypes.getInstance().create(datatype, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XMLEditorTextPart createXmlRepresentation(Datatype datatype) {
        return DatatypeXMLEditorTextPartCreator.getInstance().create(datatype, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Datatype create(Class<?> className) {
        return FolderElementFactory.create(className);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Folder createDefaultDatatype() {
        return new Folder() ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddDialogLabelProvider getAddDialogLabelProvider() {
        return new FolderMaintenanceMasterDetailAddDialogLabelProvider();
    }

    @Override
    public ElementPickerParameter getElementPickerParameter(Datatype parentDatatype) {
        NabuccoTableColumnInfo[] tableColumnInfo = createColumnInfo();
        NabuccoTableSorter tableSorter = null;
        ILabelProvider inputFieldLabelProvider = null;
        ElementPickerContentProvider contentProvider = new ElementPickerDefaultContentProvider(
                getPossibleChildren(parentDatatype));
        ElementPickerParameter result = new ElementPickerParameter(tableSorter,
                inputFieldLabelProvider, contentProvider, tableColumnInfo);
        return result;
    }

    private NabuccoTableColumnInfo[] createColumnInfo() {
        NabuccoTableColumnInfo[] result = new NabuccoTableColumnInfo[] { new NabuccoTableColumnInfo(
                "org.nabucco.testautomation.folder.ui.rcp.multipage.maintenance.model.title",
                "org.nabucco.testautomation.folder.ui.rcp.multipage.maintenance.model.tooltip",
                200, new FolderMaintenanceMasterDetailAddDialogLabelProvider()) };
        return result;
    }

    @Override
    public LabelForDialog getLabelForDialog() {
        LabelForDialog result = new LabelForDialog(
                "org.nabucco.testautomation.folder.ui.rcp.multipage.maintenance.model.title",
                "org.nabucco.testautomation.folder.ui.rcp.multipage.maintenance.model.message",
                "org.nabucco.testautomation.folder.ui.rcp.multipage.maintenance.model.shellTitle",
                "org.nabucco.testautomation.folder.ui.rcp.multipage.maintenance.model.messageTable",
                "org.nabucco.testautomation.folder.ui.rcp.multipage.maintenance.model.messageCombo",
                "org.nabucco.testautomation.folder.ui.rcp.multipage.maintainance.model.pathLabel");
        return result;
    }

    @Override
    public boolean up(ISelection selection) {
        return false;
    }

    @Override
    public boolean down(ISelection selection) {
        return false;
    }

}
