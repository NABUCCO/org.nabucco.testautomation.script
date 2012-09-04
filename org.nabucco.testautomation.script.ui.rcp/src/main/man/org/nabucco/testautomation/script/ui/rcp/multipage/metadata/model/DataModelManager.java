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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model;

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
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailHelper;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.contextmenu.NewDatatypeMenuItem;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.contextmenu.RemoveDatatypeMenuItem;
import org.nabucco.framework.plugin.base.layout.ImageProvider;
import org.nabucco.testautomation.property.facade.datatype.BooleanProperty;
import org.nabucco.testautomation.property.facade.datatype.DateProperty;
import org.nabucco.testautomation.property.facade.datatype.FileProperty;
import org.nabucco.testautomation.property.facade.datatype.NumericProperty;
import org.nabucco.testautomation.property.facade.datatype.PropertyList;
import org.nabucco.testautomation.property.facade.datatype.SqlProperty;
import org.nabucco.testautomation.property.facade.datatype.TextProperty;
import org.nabucco.testautomation.property.facade.datatype.XPathProperty;
import org.nabucco.testautomation.property.facade.datatype.XmlProperty;
import org.nabucco.testautomation.property.facade.datatype.base.Property;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;
import org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail.MetadataMaintenanceMasterDetailLabelProvider;

/**
 * DataModelManager
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class DataModelManager {

    private static final String NEW_ELEMENT = ".NewElement";

    private static final String REMOVE = ".Remove";

    public static boolean hasPossibleChildren(Datatype datatype) {
        if (datatype instanceof Metadata) {
            return getPossibleChildrenTypes((Metadata) datatype).length > 0;
        } else if (datatype instanceof MetadataLabel) {
            return getPossibleChildrenTypes((MetadataLabel) datatype).length > 0;
        } else if (datatype instanceof PropertyList) {
            return getPossibleChildrenTypes((PropertyList) datatype).length > 0;
        } else if (datatype instanceof XPathProperty) {
            return getPossibleChildrenTypes((XPathProperty) datatype).length > 0;
        } else if (datatype instanceof Property) {
            return getPossibleChildrenTypes((Property) datatype).length > 0;
        } else {
            Activator.getDefault().logError(
                    "Error. No children-mapping for type '"
                            + datatype.getClass() + "' in " + DataModelManager.class.getCanonicalName() + " found.");
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public static Class<? extends Datatype>[] getPossibleChildrenTypes(Datatype datatype) {

        // Determine allowed types
        Class<? extends Datatype>[] possibleChildrenTypes;
        if (datatype instanceof Metadata) {
            possibleChildrenTypes = getPossibleChildrenTypes((Metadata) datatype);
        } else if (datatype instanceof MetadataLabel) {
            possibleChildrenTypes = getPossibleChildrenTypes((MetadataLabel) datatype);
        } else if (datatype instanceof PropertyList) {
            possibleChildrenTypes = getPossibleChildrenTypes((PropertyList) datatype);
        } else if (datatype instanceof XPathProperty) {
            possibleChildrenTypes = getPossibleChildrenTypes((XPathProperty) datatype);
        } else if (datatype instanceof Property) {
            possibleChildrenTypes = getPossibleChildrenTypes((Property) datatype);
        } else {
            Activator.getDefault().logError(
                    "Error. No children-mapping for type '"
                            + datatype.getClass() + "' in " + DataModelManager.class.getCanonicalName() + " found.");
            possibleChildrenTypes = new Class[] {};
        }
        return possibleChildrenTypes;
    }

    public static Map<String, Datatype[]> getPossibleChildren(Datatype datatype) {
        Map<String, Datatype[]> result = new HashMap<String, Datatype[]>();

        Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes(datatype);
        // Produce elements
        Set<Datatype> children = new HashSet<Datatype>();
        for (Class<? extends Datatype> clazz : possibleChildrenTypes) {
            children.add(MetadataElementFactory.create(clazz));
        }
        Datatype[] possibleChildrenInstances = children.toArray(new Datatype[0]);
        result.put("New Elements", possibleChildrenInstances);
        return result;
    }

    public static Menu getContextMenu(ISelection selection, TreeViewer treeViewer,
            MetadataMaintenanceMultiPageEditViewModelHandlerImpl modelHandler) {
        Menu result = new Menu(treeViewer.getTree());
        TreeSelection treeSelection = (TreeSelection) selection;
        Object firstElement = treeSelection.getFirstElement();
        if (firstElement instanceof MasterDetailTreeNode) {
            MasterDetailTreeNode treeNode = (MasterDetailTreeNode) firstElement;
            Datatype datatype = treeNode.getDatatype();

            if (!MasterDetailHelper.isDatatypeEditable(datatype)) {
                return null;
            }

            Menu newElementMenu = createMenu(result, MetadataMaintenanceMultiPageEditViewModelHandlerImpl.ID
                    + NEW_ELEMENT, "icons/add.png");

            if (datatype instanceof Metadata) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((Metadata) datatype);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
            } else if (datatype instanceof MetadataLabel) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((MetadataLabel) datatype);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
            } else if (datatype instanceof PropertyList) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((PropertyList) datatype);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
            } else if (datatype instanceof XPathProperty) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((XPathProperty) datatype);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
            } else if (datatype instanceof Property) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((Property) datatype);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
            } else {
                Activator.getDefault()
                        .logError(
                                "Error. No children-mapping for type '"
                                        + datatype.getClass() + "' in " + DataModelManager.class.getCanonicalName()
                                        + " found.");
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
                new RemoveDatatypeMenuItem(result, treeNode, modelHandler, treeViewer,
                        MetadataMaintenanceMultiPageEditViewModelHandlerImpl.ID + REMOVE, null, image);
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

    private static void addMenuItems(final Menu parentMenu, Class<? extends Datatype>[] possibleChildrenTypes,
            MasterDetailTreeNode treeNode, TreeViewer treeViewer,
            MetadataMaintenanceMultiPageEditViewModelHandlerImpl modelHandler) {
        // Produce elements
        for (Class<? extends Datatype> clazz : possibleChildrenTypes) {

            // create menu entries for each allowed class
            Datatype datatype = MetadataElementFactory.create(clazz);
            String imagePath = MetadataMaintenanceMasterDetailLabelProvider.getInstance().getImage(datatype);
            Image image = ImageProvider.createImage(imagePath);

            new NewDatatypeMenuItem(parentMenu, treeNode, modelHandler, datatype, treeViewer,
                    MetadataMaintenanceMultiPageEditViewModelHandlerImpl.ID + "." + clazz.getSimpleName(), null, image);
        }
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(Metadata datatype) {
        return new Class[] { Metadata.class, MetadataLabel.class };
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(MetadataLabel datatype) {

        if (datatype.getPropertyList() == null) {
            return new Class[] { PropertyList.class };
        }
        return new Class[] {};
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(PropertyList datatype) {
        return new Class[] { PropertyList.class, BooleanProperty.class, DateProperty.class, NumericProperty.class,
                TextProperty.class, XPathProperty.class, XmlProperty.class, FileProperty.class, SqlProperty.class };
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(XPathProperty datatype) {
        return new Class[] { XPathProperty.class };
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(Property datatype) {
        return new Class[] {};
    }

}
