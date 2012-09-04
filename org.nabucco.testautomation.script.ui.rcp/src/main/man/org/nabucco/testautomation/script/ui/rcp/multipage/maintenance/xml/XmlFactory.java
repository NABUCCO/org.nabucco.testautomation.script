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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.xml;

import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;

public class XmlFactory {

    public static String createStartTag(TestScript testScript) {
        String startTag = "<TestScript name=\"" + testScript.getName().getValue() + "\">";
        return startTag;
    }

    public static String createEndTag(TestScript testScript) {
        String endTag = "</TestScript>";
        return endTag;
    }

    public static String createContent(TestScript testScript) {
        return null;
    }

    public static String createStartTag(TestScriptElement scriptElement) {
        String startTag = "<TestScriptElement type=\"" + scriptElement.getType() + "\">";
        return startTag;
    }

    public static String createEndTag(TestScriptElement scriptElement) {
        String endTag = "</TestScriptElement>";
        return endTag;
    }

    public static String createContent(TestScriptElement scriptElement) {
        return null;
    }

}
