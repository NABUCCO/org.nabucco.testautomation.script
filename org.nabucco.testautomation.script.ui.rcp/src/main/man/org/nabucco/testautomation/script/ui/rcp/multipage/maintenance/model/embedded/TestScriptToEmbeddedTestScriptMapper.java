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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.embedded;

import org.nabucco.testautomation.script.facade.datatype.dictionary.EmbeddedTestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;

/**
 * TestScriptToEmbeddedTestScriptMapper
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class TestScriptToEmbeddedTestScriptMapper {

    public static void map(TestScript testScript, EmbeddedTestScript embeddedTestScript) {
        embeddedTestScript.setTestScript(testScript);
        embeddedTestScript.setName(testScript.getName());
        embeddedTestScript.setTestScriptKey(testScript.getIdentificationKey());
        embeddedTestScript.setDescription(testScript.getDescription());

        Folder folder = testScript.getFolder();

        if (folder != null) {
            embeddedTestScript.setFolder(folder.getName());
        }
    }

}
