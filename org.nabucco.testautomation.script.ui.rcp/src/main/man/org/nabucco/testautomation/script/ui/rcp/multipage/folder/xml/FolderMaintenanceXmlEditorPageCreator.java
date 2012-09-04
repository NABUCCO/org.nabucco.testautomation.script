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
package org.nabucco.testautomation.script.ui.rcp.multipage.folder.xml;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.plugin.base.component.multipage.xml.DatatypeXMLEditorTextPartCreator;
import org.nabucco.framework.plugin.base.component.multipage.xml.XMLEditorTextPart;
import org.nabucco.framework.plugin.base.component.multipage.xml.XMLEditorTextPartCreatorImpl;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;


/**
 * FolderMaintenanceXmlEditorPageCreator
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class FolderMaintenanceXmlEditorPageCreator extends XMLEditorTextPartCreatorImpl<Datatype> {

    /**
     * {@inheritDoc}
     */
    @Override
    public XMLEditorTextPart createXMLNodeWithTypedDatatype(Datatype datatype,
            XMLEditorTextPart parent, DatatypeXMLEditorTextPartCreator builder) {
        XMLEditorTextPart result = null;

        if (datatype instanceof Folder) {
        	Folder folder = (Folder) datatype;
            result = new XMLEditorTextPart(0, XmlFactory.createStartTag(folder),
                    XmlFactory.createEndTag(folder), XmlFactory.createContent(folder), null,
                    datatype);

            for (Folder folderChild : folder.getSubFolderList()) {
                result.getChildren().add(builder.create(folderChild, result));
            }
        } 
        return result;
    }

}
