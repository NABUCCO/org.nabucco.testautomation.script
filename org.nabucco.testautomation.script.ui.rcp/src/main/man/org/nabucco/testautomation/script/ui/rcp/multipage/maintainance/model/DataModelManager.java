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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model;

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
import org.nabucco.testautomation.facade.datatype.property.BooleanProperty;
import org.nabucco.testautomation.facade.datatype.property.DateProperty;
import org.nabucco.testautomation.facade.datatype.property.DoubleProperty;
import org.nabucco.testautomation.facade.datatype.property.FileProperty;
import org.nabucco.testautomation.facade.datatype.property.IntegerProperty;
import org.nabucco.testautomation.facade.datatype.property.LongProperty;
import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.facade.datatype.property.SqlProperty;
import org.nabucco.testautomation.facade.datatype.property.StringProperty;
import org.nabucco.testautomation.facade.datatype.property.XPathProperty;
import org.nabucco.testautomation.facade.datatype.property.XmlProperty;
import org.nabucco.testautomation.facade.datatype.property.base.Property;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Assertion;
import org.nabucco.testautomation.script.facade.datatype.dictionary.BreakLoop;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Condition;
import org.nabucco.testautomation.script.facade.datatype.dictionary.EmbeddedTestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Execution;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Foreach;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Lock;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Logger;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Loop;
import org.nabucco.testautomation.script.facade.datatype.dictionary.PropertyAction;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TextMessage;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;
import org.nabucco.testautomation.script.ui.rcp.images.ScriptImageRegistry;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.ScriptMaintainanceMasterDetailLabelProvider;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.embedded.TestScriptSelectKeyColumnDialogLabelProvider;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.embedded.TestScriptSelectNameColumnDialogLabelProvider;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.embedded.TestScriptSelectTableFilter;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.embedded.TestScriptSelectTableSorter;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.embedded.TestScriptSelectionPickerContentProvider;

/**
 * DataModelManager
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class DataModelManager {

	private static final String NEW_ELEMENT = ".NewElement";
	private static final String REMOVE = ".Remove";
	private static final String EMBEDDED_SCRIPT = ".EmbeddedScript";

	// FIXME: Replace else-if by switch

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
		}else if (datatype instanceof Assertion) {
			possibleChildrenTypes = getPossibleChildrenTypes((Assertion) datatype);
		} else if (datatype instanceof BreakLoop) {
			possibleChildrenTypes = getPossibleChildrenTypes((BreakLoop) datatype);
		} else if (datatype instanceof Condition) {
			possibleChildrenTypes = getPossibleChildrenTypes((Condition) datatype);
		} else if (datatype instanceof Execution) {
			possibleChildrenTypes = getPossibleChildrenTypes((Execution) datatype);
		} else if (datatype instanceof Foreach) {
			possibleChildrenTypes = getPossibleChildrenTypes((Foreach) datatype);
		} else if (datatype instanceof Lock) {
			possibleChildrenTypes = getPossibleChildrenTypes((Lock) datatype);
		} else if (datatype instanceof Logger) {
			possibleChildrenTypes = getPossibleChildrenTypes((Logger) datatype);
		} else if (datatype instanceof Loop) {
			possibleChildrenTypes = getPossibleChildrenTypes((Loop) datatype);
		} else if (datatype instanceof PropertyAction) {
			possibleChildrenTypes = getPossibleChildrenTypes((PropertyAction) datatype);
		} else if (datatype instanceof TextMessage) {
			possibleChildrenTypes = getPossibleChildrenTypes((TextMessage) datatype);
		} else if (datatype instanceof PropertyList) {
			possibleChildrenTypes = getPossibleChildrenTypes((PropertyList) datatype);
		} else if (datatype instanceof Property) {
			possibleChildrenTypes = getPossibleChildrenTypes((Property) datatype);
		} else {
			Activator.getDefault().logError(
					"Error. No children-mapping for type '" + datatype.getClass() + "' in " + DataModelManager.class.getCanonicalName() + " found.");
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
		} else if (datatype instanceof PropertyList) {
			return true;
		} else if (datatype instanceof Property) {
			return false;
		} else {
			Activator.getDefault().logError(
					"Error. No children-mapping for type '" + datatype.getClass() + "' in " + DataModelManager.class.getCanonicalName() + " found.");
			return false;
		}
	}

	public static Menu getContextMenu(ISelection selection, TreeViewer treeViewer, ScriptMaintainanceMultiplePageEditViewModelHandlerImpl modelHandler) {
		Menu result = new Menu(treeViewer.getTree());
		TreeSelection treeSelection = (TreeSelection) selection;
		Object firstElement = treeSelection.getFirstElement();
		if (firstElement instanceof MasterDetailTreeNode) {
			MasterDetailTreeNode treeNode = (MasterDetailTreeNode) firstElement;
			Datatype datatype = treeNode.getDatatype();

			if(!MasterDetailHelper.isDatatypeEditable(datatype)){
				return null;
			}
			
			Menu newElementMenu = createMenu(result, ScriptMaintainanceMultiplePageEditViewModelHandlerImpl.ID + NEW_ELEMENT, "icons/add.png");

			if (datatype instanceof TestScript) {
				Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((TestScript) datatype);
				addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
			} else if (datatype instanceof EmbeddedTestScript) {
				Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((EmbeddedTestScript) datatype);
				addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
			}else if (datatype instanceof Action) {
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
				Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((Execution) datatype);
				addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
			} else if (datatype instanceof Foreach) {
				Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((Foreach) datatype);
				addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
			} else if (datatype instanceof Lock) {
				Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((Lock) datatype);
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
			} else if (datatype instanceof PropertyList) {
				Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((PropertyList) datatype);
				addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
			} else if (datatype instanceof Property) {
				Class<? extends Datatype>[] possibleChildrenTypes = getPossibleChildrenTypes((Property) datatype);
				addMenuItems(newElementMenu, possibleChildrenTypes, treeNode, treeViewer, modelHandler);
			} else {
				Activator.getDefault().logError(
						"Error. No children-mapping for type '" + datatype.getClass() + "' in " + DataModelManager.class.getCanonicalName() + " found.");
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
				new RemoveDatatypeMenuItem(result, treeNode, modelHandler, treeViewer, ScriptMaintainanceMultiplePageEditViewModelHandlerImpl.ID + REMOVE,
						null, image);
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

	private static void addMenuItems(final Menu parentMenu, Class<? extends Datatype>[] possibleChildrenTypes, MasterDetailTreeNode treeNode,
			TreeViewer treeViewer, ScriptMaintainanceMultiplePageEditViewModelHandlerImpl modelHandler) {
		// Produce elements
		for (Class<? extends Datatype> clazz : possibleChildrenTypes) {

			// Special Case EmbeddedTestScript
			if(clazz.equals(EmbeddedTestScript.class)){
				HashMap<String, String> variableTextMap = new HashMap<String, String>();
				Image image = ImageProvider.createImage(ScriptImageRegistry.ICON_SCRIPT.getId());
				// Select
				new SelectDatatypeMenuItem(parentMenu, treeNode, modelHandler, treeViewer,
						ScriptMaintainanceMultiplePageEditViewModelHandlerImpl.ID + EMBEDDED_SCRIPT,
						getElementPickerParameterForSelectAndClone(new TestScriptSelectionPickerContentProvider()),
						modelHandler.getLabelForDialog(), variableTextMap, image);
			} else {
				Datatype datatype = ScriptElementFactory.create(clazz);
				String imagePath = ScriptMaintainanceMasterDetailLabelProvider.getInstance().getImage(datatype);
				Image image = ImageProvider.createImage(imagePath);
				
				new NewDatatypeMenuItem(parentMenu, treeNode, modelHandler, datatype, treeViewer,
						ScriptMaintainanceMultiplePageEditViewModelHandlerImpl.ID + "." + clazz.getSimpleName(), null, image);
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
			return new Class[] { PropertyList.class, Assertion.class, Condition.class, Execution.class, Foreach.class, Logger.class, Loop.class,
					PropertyAction.class };
		}
		return new Class[] { Assertion.class, Condition.class, Execution.class, Foreach.class, Logger.class, Loop.class, PropertyAction.class };
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
		return new Class[] { Assertion.class, Condition.class, Execution.class, Foreach.class, Logger.class, Loop.class, PropertyAction.class };
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends Datatype>[] getPossibleChildrenTypes(Execution execution) {

		return new Class[] { Action.class, EmbeddedTestScript.class, Logger.class };
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends Datatype>[] getPossibleChildrenTypes(Foreach foreach) {

		return new Class[] { Assertion.class, BreakLoop.class, Condition.class, Execution.class, Foreach.class, Logger.class, Loop.class, PropertyAction.class };
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends Datatype>[] getPossibleChildrenTypes(Lock lock) {

		return new Class[] { Assertion.class, Condition.class, Execution.class, Foreach.class, Logger.class, Loop.class, PropertyAction.class };
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends Datatype>[] getPossibleChildrenTypes(Logger logger) {

		return new Class[] { TextMessage.class };
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends Datatype>[] getPossibleChildrenTypes(Loop loop) {

		return new Class[] { Assertion.class, BreakLoop.class, Condition.class, Execution.class, Foreach.class, Logger.class, Loop.class, PropertyAction.class };
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
	private static Class<? extends Datatype>[] getPossibleChildrenTypes(PropertyList datatype) {
		return new Class[] { PropertyList.class, BooleanProperty.class, DateProperty.class, DoubleProperty.class, IntegerProperty.class, LongProperty.class,
				StringProperty.class, XmlProperty.class, XPathProperty.class, FileProperty.class, SqlProperty.class };
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends Datatype>[] getPossibleChildrenTypes(Property datatype) {
		return new Class[] {};
	}
	
	@SuppressWarnings("unchecked")
	private static Class<? extends Datatype>[] getPossibleChildrenTypes(EmbeddedTestScript datatype) {
		return new Class[] {};
	}
	
	private static ElementPickerParameter getElementPickerParameterForSelectAndClone(ElementPickerContentProvider contentProvider) {
		ILabelProvider inputFieldLabelProvider = null;
		NabuccoTableColumnInfo[] tableColumnInfo = createColumnInfoForSelectAndClone();
		NabuccoTableSorter tableSorter = new TestScriptSelectTableSorter();
		TestScriptSelectTableFilter tableFilter = new TestScriptSelectTableFilter();
		ElementPickerParameter result = new ElementPickerParameter(tableSorter, tableFilter, inputFieldLabelProvider, contentProvider, tableColumnInfo);

		return result;
	}
	
	private static NabuccoTableColumnInfo[] createColumnInfoForSelectAndClone() {
		NabuccoTableColumnInfo[] result = new NabuccoTableColumnInfo[] {
				new NabuccoTableColumnInfo(ScriptMaintainanceMultiplePageEditViewModelHandlerImpl.ID + ".EmbedTestScriptDialog.column.key.name",
						ScriptMaintainanceMultiplePageEditViewModelHandlerImpl.ID + ".EmbedTestScriptDialog.column.key.tooltip", 80, SWT.LEFT,
						SWT.LEFT, new TestScriptSelectKeyColumnDialogLabelProvider()),
				new NabuccoTableColumnInfo(ScriptMaintainanceMultiplePageEditViewModelHandlerImpl.ID + ".EmbedTestScriptDialog.column.name.name",
						ScriptMaintainanceMultiplePageEditViewModelHandlerImpl.ID + ".EmbedTestScriptDialog.column.name.tooltip", 150, SWT.LEFT,
						SWT.LEFT, new TestScriptSelectNameColumnDialogLabelProvider()) };
		return result;
	}

}
