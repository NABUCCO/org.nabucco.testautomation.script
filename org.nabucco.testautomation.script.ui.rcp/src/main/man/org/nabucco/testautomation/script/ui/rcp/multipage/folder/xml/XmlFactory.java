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

import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;

public class XmlFactory {

    public static String createStartTag(Folder folder) {
        String startTag = "<Folder name=\"" + folder.getName().getValue() + "\">";
        return startTag;
    }

    public static String createEndTag(Folder folder) {
        String endTag = "</Folder>";
        return endTag;
    }

    public static String createContent(Folder folder) {
        return null;
    }

}
