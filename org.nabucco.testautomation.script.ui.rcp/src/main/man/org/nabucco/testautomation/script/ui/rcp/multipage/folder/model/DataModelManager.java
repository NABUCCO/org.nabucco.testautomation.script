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
package org.nabucco.testautomation.script.ui.rcp.multipage.folder.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.contextmenu.NewDatatypeMenuItem;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.contextmenu.RemoveDatatypeMenuItem;
import org.nabucco.framework.plugin.base.layout.ImageProvider;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.ui.rcp.multipage.folder.masterdetail.FolderMaintenanceMasterDetailLabelProvider;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.ScriptMaintenanceMultiplePageEditViewModelHandlerImpl;

/**
 * DataModelManager
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class DataModelManager {

    private static final String NEW_ELEMENT = ".NewElement";
    
	private static final String REMOVE = ".Remove";
	
	@SuppressWarnings("unchecked")
    private static final Class<? extends Datatype>[] allowedChildren = new Class[] { Folder.class, TestScript.class };

    public static boolean hasPossibleChildren(Datatype datatype) {
        if (datatype instanceof Folder) {
            return getPossibleChildrenTypes((Folder) datatype).length > 0;
        } else if (datatype instanceof TestScript) {
            return false;
        } else {
            Activator.getDefault().logError(
                    "Error. No children-mapping for type '"
                            + datatype.getClass() + "' in "
                            + DataModelManager.class.getCanonicalName() + " found.");
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Datatype[]> getPossibleChildren(Datatype datatype) {
        HashMap<String, Datatype[]> result = new HashMap<String, Datatype[]>();

        // Determine allowed types
        Class<? extends Datatype>[] possibleChildrenTypes;
        if (datatype instanceof Folder) {
            possibleChildrenTypes = getPossibleChildrenTypes((Folder) datatype);
        } else if (datatype instanceof TestScript) {
        	possibleChildrenTypes = new Class[] {};
        } else {
            Activator.getDefault().logError(
                    "Error. No children-mapping for type '"
                            + datatype.getClass() + "' in "
                            + DataModelManager.class.getCanonicalName() + " found.");
            possibleChildrenTypes = new Class[] {};
        }
        // Produce elements
        Set<Datatype> children = new HashSet<Datatype>();
        for (Class<? extends Datatype> clazz : possibleChildrenTypes) {
            children.add(FolderElementFactory.create(clazz));
        }
        Datatype[] possibleChildrenInstances = children.toArray(new Datatype[0]);
        result.put(NEW_ELEMENT, possibleChildrenInstances);
        return result;
    }

    public static Menu getContextMenu(ISelection selection, TreeViewer treeViewer,
            FolderMaintenanceMultiPageEditViewModelHandlerImpl modelHandler) {
        Menu result = new Menu(treeViewer.getTree());
        TreeSelection treeSelection = (TreeSelection) selection;
        Object firstElement = treeSelection.getFirstElement();
        if (firstElement instanceof MasterDetailTreeNode) {
            MasterDetailTreeNode treeNode = (MasterDetailTreeNode) firstElement;
            Datatype datatype = treeNode.getDatatype();

            Menu newElementMenu = createMenu(result,
                    FolderMaintenanceMultiPageEditViewModelHandlerImpl.ID + NEW_ELEMENT, "icons/add.png");

            if (datatype instanceof Folder) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((Folder) datatype);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer,
                        modelHandler);
            } else if (datatype instanceof TestScript) {
            } else {
                Activator.getDefault().logError(
                        "Error. No children-mapping for type '"
                                + datatype.getClass() + "' in "
                                + DataModelManager.class.getCanonicalName() + " found.");
            }
            if (newElementMenu.getItemCount() == 0) {
                newElementMenu.getParentItem().setEnabled(false);
            }
            // If not root case
            if (treeNode.getParent() != null) {
            	// Separator
            	new MenuItem(result, SWT.SEPARATOR);
            	
            	// Remove
            	Image image = ImageProvider.createImage("icons/delete.png");
            	new RemoveDatatypeMenuItem(result, treeNode, modelHandler, treeViewer, ScriptMaintenanceMultiplePageEditViewModelHandlerImpl.ID + REMOVE, null, image);
            }
        }
        return result;
    }

    private static Menu createMenu(final Menu parentMenu, final String label, String imagePath) {
        MenuItem newElementMenuIten = new MenuItem(parentMenu, SWT.CASCADE);
		newElementMenuIten.setImage(ImageProvider.createImage(imagePath));
        newElementMenuIten.setText(I18N.i18n(label));
        Menu newElementMenu = new Menu(parentMenu);
        newElementMenuIten.setMenu(newElementMenu);
        return newElementMenu;
    }

    private static void addMenuItems(final Menu parentMenu,
            Class<? extends Datatype>[] possibleChildrenTypes, MasterDetailTreeNode treeNode,
            TreeViewer treeViewer, FolderMaintenanceMultiPageEditViewModelHandlerImpl modelHandler) {
        // Produce elements
        for (Class<? extends Datatype> clazz : possibleChildrenTypes) {

            // create menu entries for each allowed class
        	Datatype datatype = FolderElementFactory.create(clazz);
			String imagePath = FolderMaintenanceMasterDetailLabelProvider.getInstance().getImage(datatype);
			Image image = ImageProvider.createImage(imagePath);
        	
            new NewDatatypeMenuItem(parentMenu, treeNode, modelHandler,
                    datatype, treeViewer,
                    FolderMaintenanceMultiPageEditViewModelHandlerImpl.ID
                            + "." + clazz.getSimpleName(), null, image);
        }
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(Folder datatype) {
        return new Class[] { Folder.class };
    }
    
    public static boolean canBeChildOf(Datatype child, Datatype parent) {
        Class<? extends Datatype>[] datatypes = null;
        if(parent instanceof TestScript) {
            return false;
        } else if(parent instanceof Folder) {
            datatypes = allowedChildren;
        }
        
        for(Class<? extends Datatype> datatype: datatypes) {
            if(datatype.isAssignableFrom(child.getClass())) {
                return true;
            }
        }
        
        return false;
    }

}
