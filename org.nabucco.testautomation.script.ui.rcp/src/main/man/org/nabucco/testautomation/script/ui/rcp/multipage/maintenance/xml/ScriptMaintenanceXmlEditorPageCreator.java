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

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.plugin.base.component.multipage.xml.DatatypeXMLEditorTextPartCreator;
import org.nabucco.framework.plugin.base.component.multipage.xml.XMLEditorTextPart;
import org.nabucco.framework.plugin.base.component.multipage.xml.XMLEditorTextPartCreatorImpl;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;


/**
 * WorkflowConditionXmlEditorPageCreator
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class ScriptMaintenanceXmlEditorPageCreator extends XMLEditorTextPartCreatorImpl<Datatype> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected XMLEditorTextPart createXMLNodeWithTypedDatatype(Datatype datatype,
            XMLEditorTextPart parent, DatatypeXMLEditorTextPartCreator builder) {
        XMLEditorTextPart result = null;

        if (datatype instanceof TestScript) {
            TestScript script = (TestScript) datatype;
            result = new XMLEditorTextPart(0, XmlFactory.createStartTag(script),
                    XmlFactory.createEndTag(script), XmlFactory.createContent(script), null,
                    datatype);

            for (TestScriptElementContainer container : script.getTestScriptElementList()) {
                result.getChildren().add(builder.create(container.getElement(), result));
            }
        } else if (datatype instanceof TestScriptElement) {
            TestScriptElement se = (TestScriptElement) datatype;
            result = new XMLEditorTextPart(0, XmlFactory.createStartTag(se),
                    XmlFactory.createEndTag(se), XmlFactory.createContent(se), parent, datatype);

            if (se instanceof TestScriptComposite) {
                for (TestScriptElementContainer container : ((TestScriptComposite) se)
                        .getTestScriptElementList()) {
                    result.getChildren().add(builder.create(container.getElement(), result));
                }
            }
        }
        return result;
    }

}
