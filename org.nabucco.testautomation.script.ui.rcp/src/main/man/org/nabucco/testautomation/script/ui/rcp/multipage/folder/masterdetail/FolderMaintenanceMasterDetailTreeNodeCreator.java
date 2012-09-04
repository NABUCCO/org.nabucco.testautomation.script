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
package org.nabucco.testautomation.script.ui.rcp.multipage.folder.masterdetail;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNodeCreatorForAllDatatypes;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNodeCreatorImpl;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;


/**
 * FolderMaintenanceMasterDetailTreeNodeCreator
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class FolderMaintenanceMasterDetailTreeNodeCreator extends
        MasterDetailTreeNodeCreatorImpl<Datatype> {

    /**
     * {@inheritDoc}
     */
    @Override
    public MasterDetailTreeNode createNodeTyped(final Datatype dataType,
            final MasterDetailTreeNode parent,
            final MasterDetailTreeNodeCreatorForAllDatatypes builder) {
        MasterDetailTreeNode result = new MasterDetailTreeNode(dataType, parent);

        if (dataType instanceof Folder) {
        	Folder folder = (Folder) dataType;
            for (Folder childFolder : folder.getSubFolderList()) {
                MasterDetailTreeNode child = MasterDetailTreeNodeCreatorForAllDatatypes
                        .getInstance().create(childFolder, result);
                result.getChildren().add(child);
            }
            for (TestScript testScript : folder.getTestScriptList()) {
            	MasterDetailTreeNode child = MasterDetailTreeNodeCreatorForAllDatatypes
                .getInstance().create(testScript, result);
                result.getChildren().add(child);
            }
        } 
        return result;
    }

}
