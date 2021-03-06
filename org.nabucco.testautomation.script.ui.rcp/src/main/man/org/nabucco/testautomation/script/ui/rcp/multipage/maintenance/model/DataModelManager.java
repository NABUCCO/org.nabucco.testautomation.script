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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableColumnInfo;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableSorter;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailHelper;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.contextmenu.NewDatatypeMenuItem;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.contextmenu.RemoveDatatypeMenuItem;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.contextmenu.SelectDatatypeMenuItem;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerContentProvider;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerParameter;
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
import org.nabucco.testautomation.property.facade.datatype.base.PropertyComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Assertion;
import org.nabucco.testautomation.script.facade.datatype.dictionary.BreakLoop;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Condition;
import org.nabucco.testautomation.script.facade.datatype.dictionary.EmbeddedTestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Execution;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Foreach;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Function;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Logger;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Loop;
import org.nabucco.testautomation.script.facade.datatype.dictionary.PropertyAction;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TextMessage;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;
import org.nabucco.testautomation.script.ui.rcp.images.ScriptImageRegistry;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.masterdetails.ScriptMaintenanceMasterDetailLabelProvider;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.embedded.TestScriptSelectKeyColumnDialogLabelProvider;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.embedded.TestScriptSelectNameColumnDialogLabelProvider;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.embedded.TestScriptSelectTableFilter;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.embedded.TestScriptSelectTableSorter;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.embedded.TestScriptSelectionPickerContentProvider;

/**
 * DataModelManager
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class DataModelManager {

    private static final String NEW_ELEMENT = ".NewElement";

    private static final String REMOVE = ".Remove";

    private static final String EMBEDDED_SCRIPT = ".EmbeddedScript";

    @SuppressWarnings("unchecked")
    public static Class<? extends Datatype>[] getPossibleChildrenTypes(Datatype datatype) {

        // Determine allowed types
        Class<? extends Datatype>[] possibleChildrenTypes;
        if (datatype instanceof TestScript) {
            possibleChildrenTypes = getPossibleChildrenTypes((TestScript) datatype);
        } else if (datatype instanceof EmbeddedTestScript) {
            possibleChildrenTypes = getPossibleChildrenTypes((EmbeddedTestScript) datatype);
        } else if (datatype instanceof Action) {
            possibleChildrenTypes = getPossibleChildrenTypes((Action) datatype);
        } else if (datatype instanceof Assertion) {
            possibleChildrenTypes = getPossibleChildrenTypes((Assertion) datatype);
        } else if (datatype instanceof BreakLoop) {
            possibleChildrenTypes = getPossibleChildrenTypes((BreakLoop) datatype);
        } else if (datatype instanceof Condition) {
            possibleChildrenTypes = getPossibleChildrenTypes((Condition) datatype);
        } else if (datatype instanceof Execution) {
            possibleChildrenTypes = getPossibleChildrenTypes((Execution) datatype, false);
        } else if (datatype instanceof Foreach) {
            possibleChildrenTypes = getPossibleChildrenTypes((Foreach) datatype);
        } else if (datatype instanceof Function) {
            possibleChildrenTypes = getPossibleChildrenTypes((Function) datatype);
        } else if (datatype instanceof Logger) {
            possibleChildrenTypes = getPossibleChildrenTypes((Logger) datatype);
        } else if (datatype instanceof Loop) {
            possibleChildrenTypes = getPossibleChildrenTypes((Loop) datatype);
        } else if (datatype instanceof PropertyAction) {
            possibleChildrenTypes = getPossibleChildrenTypes((PropertyAction) datatype);
        } else if (datatype instanceof TextMessage) {
            possibleChildrenTypes = getPossibleChildrenTypes((TextMessage) datatype);
        } else if (datatype instanceof PropertyComposite) {
            possibleChildrenTypes = getPossibleChildrenTypes((PropertyComposite) datatype);
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
            children.add(ScriptElementFactory.create(clazz));
        }
        Datatype[] possibleChildrenInstances = children.toArray(new Datatype[0]);
        result.put("New Elements", possibleChildrenInstances);
        return result;
    }

    public static boolean hasPossibleChildren(Datatype datatype) {
        if (datatype instanceof TestScriptElement) {
            return getPossibleChildrenTypes(datatype).length > 0;
        } else if (datatype instanceof PropertyComposite) {
            return true;
        } else if (datatype instanceof Property) {
            return false;
        } else {
            Activator.getDefault().logError(
                    "Error. No children-mapping for type '"
                            + datatype.getClass() + "' in " + DataModelManager.class.getCanonicalName() + " found.");
            return false;
        }
    }

    public static Menu getContextMenu(ISelection selection, TreeViewer treeViewer,
            ScriptMaintenanceMultiplePageEditViewModelHandlerImpl modelHandler) {
        Menu result = new Menu(treeViewer.getTree());
        TreeSelection treeSelection = (TreeSelection) selection;
        Object firstElement = treeSelection.getFirstElement();
        if (firstElement instanceof MasterDetailTreeNode) {
            MasterDetailTreeNode treeNode = (MasterDetailTreeNode) firstElement;
            Datatype datatype = treeNode.getDatatype();

            if (!MasterDetailHelper.isDatatypeEditable(datatype)) {
                return null;
            }

            Menu newElementMenu = createMenu(result, ScriptMaintenanceMultiplePageEditViewModelHandlerImpl.ID
                    + NEW_ELEMENT, "icons/add.png");

            if (datatype instanceof TestScript) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((TestScript) datatype);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
            } else if (datatype instanceof EmbeddedTestScript) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((EmbeddedTestScript) datatype);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
            } else if (datatype instanceof Action) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((Action) datatype);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
            } else if (datatype instanceof Assertion) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((Assertion) datatype);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
            } else if (datatype instanceof BreakLoop) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((BreakLoop) datatype);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
            } else if (datatype instanceof Condition) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((Condition) datatype);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
            } else if (datatype instanceof Execution) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((Execution) datatype, true);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
            } else if (datatype instanceof Foreach) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((Foreach) datatype);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
            } else if (datatype instanceof Function) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((Function) datatype);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
            } else if (datatype instanceof Logger) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((Logger) datatype);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
            } else if (datatype instanceof Loop) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((Loop) datatype);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
            } else if (datatype instanceof PropertyAction) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((PropertyAction) datatype);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
            } else if (datatype instanceof TextMessage) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((TextMessage) datatype);
                addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
            } else if (datatype instanceof PropertyComposite) {
                Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((PropertyComposite) datatype);
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
                        ScriptMaintenanceMultiplePageEditViewModelHandlerImpl.ID + REMOVE, null, image);
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
            ScriptMaintenanceMultiplePageEditViewModelHandlerImpl modelHandler) {
        // Produce elements
        for (Class<? extends Datatype> clazz : possibleChildrenTypes) {

            // Special Case EmbeddedTestScript
            if (clazz.equals(EmbeddedTestScript.class)) {
                HashMap<String, String> variableTextMap = new HashMap<String, String>();
                Image image = ImageProvider.createImage(ScriptImageRegistry.ICON_SCRIPT.getId());
                // Select
                new SelectDatatypeMenuItem(parentMenu, treeNode, modelHandler, treeViewer,
                        ScriptMaintenanceMultiplePageEditViewModelHandlerImpl.ID + EMBEDDED_SCRIPT,
                        getElementPickerParameterForSelectAndClone(new TestScriptSelectionPickerContentProvider()),
                        modelHandler.getLabelForDialog(), variableTextMap, image);
            } else {
                Datatype datatype = ScriptElementFactory.create(clazz);
                String imagePath = ScriptMaintenanceMasterDetailLabelProvider.getInstance().getImage(datatype);
                Image image = ImageProvider.createImage(imagePath);

                new NewDatatypeMenuItem(parentMenu, treeNode, modelHandler, datatype, treeViewer,
                        ScriptMaintenanceMultiplePageEditViewModelHandlerImpl.ID + "." + clazz.getSimpleName(), null,
                        image);
            }
        }
    }

    public static void addTestScriptElementChild(Datatype targetDatatype, Datatype child) {
        if (targetDatatype instanceof TestScriptElement) {
            // handle propertyList child
            if (child instanceof PropertyList && ((TestScriptElement) targetDatatype).getPropertyList() == null) {
                ((TestScriptElement) targetDatatype).setPropertyList((PropertyList) child);
            }
            // handle TestScriptElement children for TestScriptComposites
            else if (targetDatatype instanceof TestScriptComposite) {
                TestScriptComposite testScriptComposite = (TestScriptComposite) targetDatatype;
                List<TestScriptElementContainer> testScriptElementList = testScriptComposite.getTestScriptElementList();
                TestScriptElementContainer testScriptElementContainerToAdd = (TestScriptElementContainer) child;
                testScriptElementContainerToAdd.setOrderIndex(testScriptElementList.size());
                testScriptElementList.add(testScriptElementContainerToAdd);
            }
        }
    }

    public static void normalizeOrderIndicies(TestScriptComposite composite, boolean recursively) {
        List<TestScriptElementContainer> testScriptElementList = composite.getTestScriptElementList();
        for (int i = 0; i < testScriptElementList.size(); i++) {
            TestScriptElementContainer testScriptElementContainer = testScriptElementList.get(i);
            if (testScriptElementContainer.getOrderIndex().getValue() != i) {
                testScriptElementContainer.setOrderIndex(i);
                if (testScriptElementContainer.getDatatypeState() == DatatypeState.PERSISTENT) {
                    testScriptElementContainer.setDatatypeState(DatatypeState.MODIFIED);
                }
            }
        }
        if (recursively) {
            for (TestScriptElementContainer testScriptElementContainer : testScriptElementList) {
                TestScriptElement element = testScriptElementContainer.getElement();
                if (element instanceof TestScriptComposite) {
                    normalizeOrderIndicies((TestScriptComposite) element, true);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(TestScript testScript) {
        if (testScript.getPropertyList() == null) {
            return new Class[] { PropertyList.class, Assertion.class, Condition.class, Execution.class, Function.class,
                    Foreach.class, Logger.class, Loop.class, PropertyAction.class };
        }
        return new Class[] { Assertion.class, Condition.class, Execution.class, Foreach.class, Function.class,
                Logger.class, Loop.class, PropertyAction.class };
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(Action action) {

        if (action.getPropertyList() == null) {
            return new Class[] { PropertyList.class };
        }
        return new Class[] {};
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(Assertion assertion) {

        if (assertion.getPropertyList() == null) {
            return new Class[] { PropertyList.class };
        }
        return new Class[] {};
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(BreakLoop breakLoop) {
        List<TestScriptElementContainer> testScriptElementList = breakLoop.getTestScriptElementList();
        // A BreakLoop is only allowed to have one child on first level (a
        // condition).
        if (testScriptElementList != null && testScriptElementList.size() > 0) {
            return new Class[] {};
        }
        return new Class[] { Condition.class };
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(Condition condition) {

        Flag isBreakCondition = condition.getIsBreakCondition();
        if (isBreakCondition != null && isBreakCondition.getValue() && condition.getOperator() != null) {
            return new Class[] { Condition.class };
        }
        return new Class[] { Assertion.class, Condition.class, Execution.class, Foreach.class, Function.class,
                Logger.class, Loop.class, PropertyAction.class };
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(Execution execution, boolean contextMenu) {

        if (contextMenu) {
            return new Class[] { Action.class, EmbeddedTestScript.class, Function.class, Logger.class };
        } else {
            return new Class[] { Action.class, Function.class, Logger.class };
        }
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(Foreach foreach) {

        return new Class[] { Assertion.class, BreakLoop.class, Condition.class, Execution.class, Foreach.class,
                Function.class, Logger.class, Loop.class, PropertyAction.class };
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(Logger logger) {

        return new Class[] { TextMessage.class };
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(Loop loop) {

        return new Class[] { Assertion.class, BreakLoop.class, Condition.class, Execution.class, Foreach.class,
                Function.class, Logger.class, Loop.class, PropertyAction.class };
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(PropertyAction propertyAction) {

        return new Class[] {};
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(TextMessage textMessage) {

        return new Class[] {};
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(Function function) {

        if (function.getPropertyList() == null) {
            return new Class[] { PropertyList.class };
        }
        return new Class[] {};
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(PropertyComposite datatype) {

        switch (datatype.getType()) {
        case LIST:
            return new Class[] { PropertyList.class, BooleanProperty.class, DateProperty.class, NumericProperty.class,
                    TextProperty.class, XmlProperty.class, XPathProperty.class, FileProperty.class, SqlProperty.class };
        case XPATH:
            return new Class[] { XPathProperty.class };
        default:
            return new Class[] {};
        }
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(Property datatype) {
        return new Class[] {};
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Datatype>[] getPossibleChildrenTypes(EmbeddedTestScript datatype) {
        return new Class[] {};
    }

    private static ElementPickerParameter getElementPickerParameterForSelectAndClone(
            ElementPickerContentProvider contentProvider) {
        ILabelProvider inputFieldLabelProvider = null;
        NabuccoTableColumnInfo[] tableColumnInfo = createColumnInfoForSelectAndClone();
        NabuccoTableSorter tableSorter = new TestScriptSelectTableSorter();
        TestScriptSelectTableFilter tableFilter = new TestScriptSelectTableFilter();
        ElementPickerParameter result = new ElementPickerParameter(tableSorter, tableFilter, inputFieldLabelProvider,
                contentProvider, tableColumnInfo);

        return result;
    }

    private static NabuccoTableColumnInfo[] createColumnInfoForSelectAndClone() {
        NabuccoTableColumnInfo[] result = new NabuccoTableColumnInfo[] {
                new NabuccoTableColumnInfo(ScriptMaintenanceMultiplePageEditViewModelHandlerImpl.ID
                        + ".EmbedTestScriptDialog.column.key.name",
                        ScriptMaintenanceMultiplePageEditViewModelHandlerImpl.ID
                                + ".EmbedTestScriptDialog.column.key.tooltip", 80, SWT.LEFT, SWT.LEFT,
                        new TestScriptSelectKeyColumnDialogLabelProvider()),
                new NabuccoTableColumnInfo(ScriptMaintenanceMultiplePageEditViewModelHandlerImpl.ID
                        + ".EmbedTestScriptDialog.column.name.name",
                        ScriptMaintenanceMultiplePageEditViewModelHandlerImpl.ID
                                + ".EmbedTestScriptDialog.column.name.tooltip", 150, SWT.LEFT, SWT.LEFT,
                        new TestScriptSelectNameColumnDialogLabelProvider()) };
        return result;
    }
    
    public static boolean canBeChildOf(Datatype child, Datatype parent) {
        Class<? extends Datatype>[] datatypes = null;
        if(parent instanceof TestScript) {
            datatypes = getPossibleChildrenTypes((TestScript)parent);
        } else if(parent instanceof Action) {
            datatypes = getPossibleChildrenTypes((Action)parent);
        } else if(parent instanceof Assertion) {
            datatypes = getPossibleChildrenTypes((Assertion)parent);
        } else if(parent instanceof BreakLoop) {
            datatypes = getPossibleChildrenTypes((BreakLoop)parent);
        } else if(parent instanceof Condition) {
            datatypes = getPossibleChildrenTypes((Condition)parent);
        } else if(parent instanceof Execution) {
            datatypes = getPossibleChildrenTypes((Execution)parent);
        } else if(parent instanceof Foreach) {
            datatypes = getPossibleChildrenTypes((Foreach)parent);
        } else if(parent instanceof Logger) {
            datatypes = getPossibleChildrenTypes((Logger)parent);
        } else if(parent instanceof Loop) {
            datatypes = getPossibleChildrenTypes((Loop)parent);
        } else if(parent instanceof PropertyAction) {
            datatypes = getPossibleChildrenTypes((PropertyAction)parent);
        } else if(parent instanceof TextMessage) {
            datatypes = getPossibleChildrenTypes((TextMessage)parent);
        } else if(parent instanceof Function) {
            datatypes = getPossibleChildrenTypes((Function)parent);
        } else if(parent instanceof PropertyComposite) {
            datatypes = getPossibleChildrenTypes((PropertyComposite)parent);
        } else if(parent instanceof Property) {
            datatypes = getPossibleChildrenTypes((Property)parent);
        } else if(parent instanceof EmbeddedTestScript) {
            datatypes = getPossibleChildrenTypes((EmbeddedTestScript)parent);
        } else {
            return false;
        }
        
        for(Class<? extends Datatype> datatype: datatypes) {
            if(datatype.isAssignableFrom(child.getClass())) {
                return true;
            }
        }
        
        return false;
    }
}
