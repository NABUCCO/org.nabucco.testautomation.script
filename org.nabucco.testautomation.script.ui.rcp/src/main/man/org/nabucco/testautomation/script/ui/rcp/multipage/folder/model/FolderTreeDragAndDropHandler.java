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

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.master.draganddrop.AbstractDragAndDropHandler;
import org.nabucco.testautomation.property.ui.rcp.util.LoggingUtility;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;



/**
 * FolderTreeDragAndDropHandler
 * 
 * @author Florian Schmidt, PRODYNA AG
 */
public class FolderTreeDragAndDropHandler extends AbstractDragAndDropHandler {
    
    private static enum AllowedDataType {
        TestScript, Folder
    }
    
    private static AllowedDataType checkDataType(Datatype datatype) {
        if(datatype instanceof TestScript) {
            return AllowedDataType.TestScript;
        }
        if(datatype instanceof Folder) {
            return AllowedDataType.Folder;
        }
        return null;
    }
    
    private static AllowedDataType checkDataType(MasterDetailTreeNode node) {
        return checkDataType(node.getDatatype());
    }
    
    @Override
    public boolean validateDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
        if(!super.validateDrop(data, target, location)) {
            return false;
        }
        switch(checkDataType(data)) {
            case TestScript:
                return new TestScriptDragAndDropHandler().validateDrop(data, target, location);
            case Folder:
                return new FolderDragAndDropHandler().validateDrop(data, target, location);
        }
        
        return false;
    }
    
    @Override
    public boolean validateExternalDrop(MasterDetailTreeNode target, int location) {
        return false;
    }
    
    @Override
    public boolean postDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
        LoggingUtility.logDrop(data, target, location);
        switch(checkDataType(data)) {
            case TestScript:
                new TestScriptDragAndDropHandler().postDrop(data, target, location);
                break;
            case Folder:
                new FolderDragAndDropHandler().postDrop(data, target, location);
                break;
        }
        return this.performUiDrop(data, target, location);
    }
    
    @Override
    public boolean performUiDrop(MasterDetailTreeNode data, MasterDetailTreeNode target, int location) {
        switch(checkDataType(data)) {
            case TestScript:
                return new TestScriptDragAndDropHandler().performUiDrop(data, target, location);
            case Folder:
                return new FolderDragAndDropHandler().performUiDrop(data, target, location);
        }
        return false;
    }
    
    @Override
    public boolean postExternalDatatypeDrop(Datatype data, MasterDetailTreeNode targetNode, int location) {
        return false;
    }
    
    @Override
    public void dragFinished(MasterDetailTreeNode movedNode) {
        // TODO Auto-generated method stub
    }
}
