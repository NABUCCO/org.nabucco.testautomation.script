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

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import org.nabucco.framework.plugin.base.component.picker.dialog.AbstractElementPickerContentProvider;
import org.nabucco.testautomation.script.facade.datatype.dictionary.EmbeddedTestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.model.ScriptElementFactory;

/**
 * TestScriptSelectionPickerContentProvider
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class TestScriptSelectionPickerContentProvider extends AbstractElementPickerContentProvider {

    private SoftReference<TestScriptElementContainer[]> cache;

    @Override
    public Object[] getElements(Object arg0) {
        if (this.cache == null || this.cache.get() == null) {
            this.loadCache();
        }

        return this.cache.get();
    }

    /**
     * Load test scripts and add to the cache.
     */
    private void loadCache() {
        TestScript[] existingScripts = ScriptElementFactory.getExistingTestScripts();
        List<TestScriptElementContainer> embeddedTestScripts = new ArrayList<TestScriptElementContainer>();

        for (TestScript script : existingScripts) {
            TestScriptElementContainer scriptContainer = (TestScriptElementContainer) ScriptElementFactory
                    .create(EmbeddedTestScript.class);

            EmbeddedTestScript embeddedTestScript = (EmbeddedTestScript) scriptContainer.getElement();
            TestScriptToEmbeddedTestScriptMapper.map(script, embeddedTestScript);
            embeddedTestScripts.add(scriptContainer);
        }

        TestScriptElementContainer[] containers = embeddedTestScripts
                .toArray(new TestScriptElementContainer[embeddedTestScripts.size()]);

        this.cache = new SoftReference<TestScriptElementContainer[]>(containers);
    }

}
