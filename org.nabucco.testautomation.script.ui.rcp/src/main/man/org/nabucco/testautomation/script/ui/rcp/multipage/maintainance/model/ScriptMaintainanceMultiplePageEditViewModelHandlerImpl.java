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

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
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
import org.nabucco.testautomation.script.facade.datatype.comparator.TestScriptElementSorter;
import org.nabucco.testautomation.script.facade.datatype.dictionary.BreakLoop;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Condition;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;

import org.nabucco.testautomation.facade.datatype.comparator.PropertySorter;
import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.facade.datatype.property.base.Property;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyContainer;

/**
 * ScriptMaintainanceMultiplePageEditViewModelHandlerImpl
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class ScriptMaintainanceMultiplePageEditViewModelHandlerImpl implements
        ScriptMaintainanceMultiplePageEditViewModelHandler {

    public static final String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.ScriptMaintainanceMultiplePageEditViewModelHandlerImpl";

    private static final TestScriptElementSorter elementSorter = new TestScriptElementSorter();

    private static final PropertySorter propertySorter = new PropertySorter();

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

        if (newChild instanceof TestScript) { // Root case
            TestScript testScript = (TestScript) newChild;
            result = addChildToTree(parent, testScript);
        } else if (newChild instanceof TestScriptElementContainer) {
            TestScriptElementContainer testScriptElementContainer = (TestScriptElementContainer) newChild;

            if (parentDatatype instanceof TestScriptComposite) {
                TestScriptComposite parentTestScriptComposite = (TestScriptComposite) parentDatatype;
                // New Datatypes have to get an order
                testScriptElementContainer.setOrderIndex(parentTestScriptComposite
                        .getTestScriptElementList().size());
                result = addChildToTree(parent, testScriptElementContainer.getElement());
                parentTestScriptComposite.getTestScriptElementList()
                        .add(testScriptElementContainer);
            }
            // Special behavior for condition
            if(testScriptElementContainer.getElement() instanceof Condition){
            	if(parentDatatype instanceof BreakLoop){
                	Condition condition = (Condition) testScriptElementContainer.getElement();
                	condition.setIsBreakCondition(true);
            	} else if(parentDatatype instanceof Condition){
            		Condition condition = (Condition) testScriptElementContainer.getElement();
            		condition.setIsBreakCondition(((Condition)parentDatatype).getIsBreakCondition());
            	}
            }
            
        } else if (newChild instanceof PropertyContainer) {
            PropertyContainer newPropertyContainer = (PropertyContainer) newChild;
            if (parentDatatype instanceof PropertyList) {
                PropertyList parentPropertyList = (PropertyList) parentDatatype;
                // New Datatypes have to get an order
                newPropertyContainer.setOrderIndex(parentPropertyList.getPropertyList().size());
                result = addChildToTree(parent, newPropertyContainer.getProperty());
                parentPropertyList.getPropertyList().add(newPropertyContainer);
            } else if (parentDatatype instanceof TestScriptElement
                    && newPropertyContainer.getProperty() instanceof PropertyList) {
                TestScriptElement parentTestScriptElement = (TestScriptElement) parentDatatype;
                PropertyList newPropertyList = (PropertyList) newPropertyContainer.getProperty();
                result = addChildToTree(parent, newPropertyContainer.getProperty());
                parentTestScriptElement.setPropertyList(newPropertyList);
            }
        }
        if (result == null) {
            Activator.getDefault().logError(
                    new NabuccoLogMessage(
                            ScriptMaintainanceMultiplePageEditViewModelHandlerImpl.class,
                            "FAILED to add new child!"));
        }
        return result;
    }

    private MasterDetailTreeNode addChildToTree(final MasterDetailTreeNode parent,
            final Datatype newChild) {
        MasterDetailTreeNode result = MasterDetailTreeNodeCreatorForAllDatatypes.getInstance()
                .create(newChild, parent);
        if (newChild instanceof PropertyList && !(parent.getDatatype() instanceof PropertyList)) {
            parent.getChildren().add(0, result);
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
        if (parentNode == null) {
            return;
        }
        Datatype parentDatatype = parentNode.getDatatype();
        boolean removedFromDataModel = false;
        int indexOfNodeToDelete = parentNode.getChildren().indexOf(nodeToRemove);
        if (datatypeToRemove instanceof TestScriptElement) {
            TestScriptComposite parentTestScriptComposite = (TestScriptComposite) parentDatatype;
            // Check for PropertyList and increase index
            if (parentTestScriptComposite.getPropertyList() != null) {
                indexOfNodeToDelete--;
            }
            List<TestScriptElementContainer> testScriptElementContainerList = parentTestScriptComposite
                    .getTestScriptElementList();
            TestScriptElementContainer containerToBeDeleted = testScriptElementContainerList
                    .get(indexOfNodeToDelete);
            // Decrease Order Of AllElements With Order Index Higher Than Index Of Node To Delete
            updateOrdersAfterBeforeDeletion(indexOfNodeToDelete, testScriptElementContainerList);
            if(testScriptElementContainerList.remove(containerToBeDeleted)){
            	removedFromDataModel = true;
            	DataModelManager.normalizeOrderIndicies(parentTestScriptComposite, false);
            	DatatypeState datatypeState = containerToBeDeleted.getDatatypeState();
				if(datatypeState == DatatypeState.PERSISTENT || datatypeState == DatatypeState.MODIFIED){
            		containerToBeDeleted.setDatatypeState(DatatypeState.DELETED);
            	}
            }
        } else if (datatypeToRemove instanceof Property) {
            if (parentDatatype instanceof PropertyList) {
                PropertyList propertyList = (PropertyList) parentDatatype;
                List<PropertyContainer> propertyListProperyList = propertyList.getPropertyList();
                decreaseOrderOfAllElementWithOrderIndexHigherThanIndexOfNodeToDelete(
                        indexOfNodeToDelete, propertyListProperyList);
                propertyListProperyList.remove(indexOfNodeToDelete);
                org.nabucco.testautomation.ui.rcp.model.property.DataModelManager.normalizeOrderIndicies(propertyList, false);
                removedFromDataModel = true;
            } else if (parentDatatype instanceof TestScriptElement) {
                TestScriptElement testScriptElement = (TestScriptElement) parentDatatype;
                testScriptElement.setPropertyList(null);
                removedFromDataModel = true;
            }
        }
        // Set states and remove from tree
        if (removedFromDataModel) {
            if (parentDatatype.getDatatypeState() == DatatypeState.PERSISTENT) {
                parentDatatype.setDatatypeState(DatatypeState.MODIFIED);
            }
            DatatypeState datatypeState = datatypeToRemove.getDatatypeState();
            if (datatypeState == DatatypeState.PERSISTENT
                    || datatypeState == DatatypeState.MODIFIED) {
                datatypeToRemove.setDatatypeState(DatatypeState.DELETED);
            }
            if (parentNode.getChildren().remove(nodeToRemove))
                ;
            {
                Activator.getDefault().logError(
                        new NabuccoLogMessage(
                                ScriptMaintainanceMultiplePageEditViewModelHandlerImpl.class,
                                "FAILED to remove datatype from tree!"));
            }
        } else {
            Activator.getDefault().logError(
                    new NabuccoLogMessage(
                            ScriptMaintainanceMultiplePageEditViewModelHandlerImpl.class,
                            "FAILED to remove datatype from business model!"));
        }
    }

    private void updateOrdersAfterBeforeDeletion(int indexOfNodeToDelete,
            List<TestScriptElementContainer> testScriptElementContainerList) {
        for (TestScriptElementContainer testScriptElementContainer : testScriptElementContainerList) {
            Integer order = testScriptElementContainer.getOrderIndex().getValue();
            if (order > indexOfNodeToDelete) {
                testScriptElementContainer.setOrderIndex(--order);
                if (testScriptElementContainer.getDatatypeState() == DatatypeState.PERSISTENT) {
                	testScriptElementContainer.setDatatypeState(DatatypeState.MODIFIED);
                }
            }
        }
    }

    private void decreaseOrderOfAllElementWithOrderIndexHigherThanIndexOfNodeToDelete(
            int indexOfNodeToDelete, List<PropertyContainer> propertyListProperyList) {
        for (PropertyContainer propertyContainer : propertyListProperyList) {
            Integer order = propertyContainer.getOrderIndex().getValue();
            if (order > indexOfNodeToDelete) {
                propertyContainer.setOrderIndex(--order);
                if (propertyContainer.getDatatypeState() == DatatypeState.PERSISTENT) {
                	propertyContainer.setDatatypeState(DatatypeState.MODIFIED);
                }
            }
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
    @SuppressWarnings("unchecked")
    @Override
    public Datatype create(Class<?> className) {
        return ScriptElementFactory.create((Class<? extends Datatype>) className);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TestScript createDefaultDatatype() {
        return (TestScript) ((TestScriptElementContainer) create(TestScript.class)).getElement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddDialogLabelProvider getAddDialogLabelProvider() {
        return new ScriptMaintainanceMasterDetailAddDialogLabelProvider();
    }

    @Override
    public ElementPickerParameter getElementPickerParameter(Datatype parentDatatype) {
        NabuccoTableColumnInfo[] tableColumnInfo = createColumnInfo();
        NabuccoTableSorter tableSorter = new ScriptSingleColumnTableSorter();
        ILabelProvider inputFieldLabelProvider = null;
        ElementPickerContentProvider contentProvider = new ElementPickerDefaultContentProvider(
                getPossibleChildren(parentDatatype));
        ElementPickerParameter result = new ElementPickerParameter(tableSorter,
                inputFieldLabelProvider, contentProvider, tableColumnInfo);
        return result;
    }

    private NabuccoTableColumnInfo[] createColumnInfo() {
        NabuccoTableColumnInfo[] result = new NabuccoTableColumnInfo[] { new NabuccoTableColumnInfo(
                "org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.title",
                "org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.tooltip",
                200, new ScriptMaintainanceMasterDetailAddDialogLabelProvider()) };
        return result;
    }

    @Override
    public LabelForDialog getLabelForDialog() {
        LabelForDialog result = new LabelForDialog(
                "org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.title",
                "org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.message",
                "org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.shellTitle",
                "org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.messageTable",
                "org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.messageCombo",
                "org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.pathLabel");
        return result;
    }

    @Override
    public boolean up(ISelection selection) {
        TreeSelection treeSelection = (TreeSelection) selection;
        Object firstElement = treeSelection.getFirstElement();
        if (firstElement instanceof MasterDetailTreeNode) {
            MasterDetailTreeNode treeNode = (MasterDetailTreeNode) firstElement;
            Datatype datatypeToMove = treeNode.getDatatype();
            MasterDetailTreeNode parentTreeNode = treeNode.getParent();
            Datatype parentDatatype = parentTreeNode.getDatatype();
            int indexOfNodeToMove = parentTreeNode.getChildren().indexOf(treeNode);
            if (datatypeToMove instanceof TestScriptElement
                    && parentDatatype instanceof TestScriptComposite) {
                TestScriptComposite parentTestScriptComposite = (TestScriptComposite) parentDatatype;
                // Check for PropertyList and increase index
                if (parentTestScriptComposite.getPropertyList() != null) {
                    indexOfNodeToMove--;
                }
                TestScriptElementContainer elementContainerToMove = parentTestScriptComposite
                        .getTestScriptElementList().get(indexOfNodeToMove);
                Integer currentOrder = elementContainerToMove.getOrderIndex().getValue();
                // catch the case that a property list is on first index of the tree
                if (indexOfNodeToMove > 0) {
                    switchContainerOrder(elementContainerToMove, parentTestScriptComposite.getTestScriptElementList().get(currentOrder - 1));
                    elementSorter.sort(parentTestScriptComposite);
                    DataModelManager.normalizeOrderIndicies(parentTestScriptComposite, false);
                    return true;
                }
            } else if (datatypeToMove instanceof Property && parentDatatype instanceof PropertyList) {
                PropertyList parentPropertyList = (PropertyList) parentDatatype;
                PropertyContainer propertyContainer = parentPropertyList.getPropertyList().get(
                        indexOfNodeToMove);
                Integer currentOrder = propertyContainer.getOrderIndex().getValue();
                switchContainerOrder(propertyContainer, parentPropertyList.getPropertyList().get(currentOrder - 1));
                propertySorter.sort(parentPropertyList);
                org.nabucco.testautomation.ui.rcp.model.property.DataModelManager.normalizeOrderIndicies(parentPropertyList, false);
                return true;
            }
        }
        return false;
    }


	@Override
    public boolean down(ISelection selection) {
        TreeSelection treeSelection = (TreeSelection) selection;
        Object firstElement = treeSelection.getFirstElement();
        if (firstElement instanceof MasterDetailTreeNode) {
            MasterDetailTreeNode treeNode = (MasterDetailTreeNode) firstElement;
            Datatype datatypeToMove = treeNode.getDatatype();
            MasterDetailTreeNode parentTreeNode = treeNode.getParent();
            Datatype parentDatatype = parentTreeNode.getDatatype();
            int indexOfNodeToMove = parentTreeNode.getChildren().indexOf(treeNode);
            if (datatypeToMove instanceof TestScriptElement
                    && parentDatatype instanceof TestScriptComposite) {
                TestScriptComposite parentTestScriptComposite = (TestScriptComposite) parentDatatype;
                // Check for PropertyList and increase index
                if (parentTestScriptComposite.getPropertyList() != null) {
                    indexOfNodeToMove--;
                }
                TestScriptElementContainer elementContainerToMove = parentTestScriptComposite
                        .getTestScriptElementList().get(indexOfNodeToMove);
                Integer currentOrder = elementContainerToMove.getOrderIndex().getValue();
                switchContainerOrder(elementContainerToMove, parentTestScriptComposite.getTestScriptElementList().get(currentOrder + 1));
                elementSorter.sort(parentTestScriptComposite);
                DataModelManager.normalizeOrderIndicies(parentTestScriptComposite, false);
                return true;
            } else if (datatypeToMove instanceof Property && parentDatatype instanceof PropertyList) {
                PropertyList parentPropertyList = (PropertyList) parentDatatype;
                PropertyContainer propertyContainer = parentPropertyList.getPropertyList().get(
                        indexOfNodeToMove);
                Integer currentOrder = propertyContainer.getOrderIndex().getValue();
                switchContainerOrder(propertyContainer, parentPropertyList.getPropertyList().get(currentOrder + 1));
                propertySorter.sort(parentPropertyList);
                org.nabucco.testautomation.ui.rcp.model.property.DataModelManager.normalizeOrderIndicies(parentPropertyList, false);
                return true;
            }
        }
        return false;
    }

	 private void switchContainerOrder(
				TestScriptElementContainer elementContainerToMove,
				TestScriptElementContainer testScriptElementContainer) {
			
	    	Integer value = elementContainerToMove.getOrderIndex().getValue();
	    	elementContainerToMove.setOrderIndex(testScriptElementContainer.getOrderIndex().getValue());
	    	testScriptElementContainer.setOrderIndex(value);
	    	
	    	if(elementContainerToMove.getDatatypeState() == DatatypeState.PERSISTENT){
	    		elementContainerToMove.setDatatypeState(DatatypeState.MODIFIED);
	    	}
	    	if(testScriptElementContainer.getDatatypeState() == DatatypeState.PERSISTENT){
	    		testScriptElementContainer.setDatatypeState(DatatypeState.MODIFIED);
	    	}
		}
	
	 private void switchContainerOrder(
			 PropertyContainer propertyContainer1,
			 PropertyContainer propertyContainer2) {
			
	    	Integer value = propertyContainer1.getOrderIndex().getValue();
	    	propertyContainer1.setOrderIndex(propertyContainer2.getOrderIndex().getValue());
	    	propertyContainer2.setOrderIndex(value);
	    	
	    	if(propertyContainer1.getDatatypeState() == DatatypeState.PERSISTENT){
	    		propertyContainer1.setDatatypeState(DatatypeState.MODIFIED);
	    	}
	    	if(propertyContainer2.getDatatypeState() == DatatypeState.PERSISTENT){
	    		propertyContainer2.setDatatypeState(DatatypeState.MODIFIED);
	    	}
		}
	

}
