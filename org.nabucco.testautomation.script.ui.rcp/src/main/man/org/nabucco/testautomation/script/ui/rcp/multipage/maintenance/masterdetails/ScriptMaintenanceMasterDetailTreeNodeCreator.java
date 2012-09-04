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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.masterdetails;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNodeCreatorForAllDatatypes;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNodeCreatorImpl;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyComposite;
import org.nabucco.testautomation.property.facade.datatype.base.PropertyContainer;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.dictionary.EmbeddedTestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.embedded.TestScriptToEmbeddedTestScriptMapper;

/**
 * ScriptMaintenanceMasterDetailTreeNodeCreator
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class ScriptMaintenanceMasterDetailTreeNodeCreator extends MasterDetailTreeNodeCreatorImpl<Datatype> {

    @Override
    public MasterDetailTreeNode createNodeTyped(Datatype dataType, MasterDetailTreeNode parent,
            MasterDetailTreeNodeCreatorForAllDatatypes builder) {
        MasterDetailTreeNode result = new MasterDetailTreeNode(dataType, parent);

        if (dataType instanceof TestScriptComposite) {
            TestScriptComposite testScriptElementParent = (TestScriptComposite) dataType;
            NabuccoList<TestScriptElementContainer> testScriptElementList = (NabuccoList<TestScriptElementContainer>) testScriptElementParent
                    .getTestScriptElementList();
            NabuccoCollectionState state = testScriptElementList.getState();
            if (state != NabuccoCollectionState.LAZY) {
                for (TestScriptElementContainer testScriptElement : testScriptElementParent.getTestScriptElementList()) {
                    MasterDetailTreeNode child = MasterDetailTreeNodeCreatorForAllDatatypes.getInstance().create(
                            testScriptElement.getElement(), result);
                    result.getChildren().add(child);
                }
            }
            // force property list node to index 0
            if (testScriptElementParent.getPropertyList() != null) {
                MasterDetailTreeNode child = MasterDetailTreeNodeCreatorForAllDatatypes.getInstance().create(
                        testScriptElementParent.getPropertyList(), result);
                result.getChildren().add(0, child);
            }
        } else if (dataType instanceof TestScriptElement) {
            // Handle embedded scripts
            if (dataType instanceof EmbeddedTestScript) {
                TestScript testScript = ((EmbeddedTestScript) dataType).getTestScript();
                TestScriptToEmbeddedTestScriptMapper.map(testScript, (EmbeddedTestScript) dataType);
            }

            TestScriptElement testScriptElementParent = (TestScriptElement) dataType;
            // force property list node to index 0
            if (testScriptElementParent.getPropertyList() != null) {
                MasterDetailTreeNode child = MasterDetailTreeNodeCreatorForAllDatatypes.getInstance().create(
                        testScriptElementParent.getPropertyList(), result);
                result.getChildren().add(0, child);
            }
        } else if (dataType instanceof Action) {
            Action action = (Action) dataType;
            // force property list node to index 0
            if (action.getPropertyList() != null) {
                MasterDetailTreeNode child = MasterDetailTreeNodeCreatorForAllDatatypes.getInstance().create(
                        action.getPropertyList(), result);
                result.getChildren().add(child);
            }
        } else if (dataType instanceof PropertyComposite) {
            PropertyComposite propertyList = (PropertyComposite) dataType;

            for (PropertyContainer prop : propertyList.getPropertyList()) {
                MasterDetailTreeNode child = MasterDetailTreeNodeCreatorForAllDatatypes.getInstance().create(
                        prop.getProperty(), result);
                result.getChildren().add(child);
            }
        }
        return result;
    }

}
