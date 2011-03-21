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
package org.nabucco.testautomation.script.impl.service.produce;

import java.util.HashMap;
import java.util.Map;

import org.nabucco.framework.base.facade.component.NabuccoInstance;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineActionCode;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.message.ProduceTestScriptElementMsg;
import org.nabucco.testautomation.script.impl.service.FolderSupport;
import org.nabucco.testautomation.script.impl.service.produce.clone.PrepareTestScriptElementCloneVisitor;
import org.nabucco.testautomation.script.impl.service.produce.clone.TestScriptElementCloneVisitor;


/**
 * ProduceTestScriptElementCloneServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ProduceTestScriptElementCloneServiceHandlerImpl extends ProduceTestScriptElementCloneServiceHandler {

	private static final long serialVersionUID = 1L;

	@Override
	protected ProduceTestScriptElementMsg produceTestScriptElementClone(
			ProduceTestScriptElementMsg msg) throws ProduceException {
		
		TestScriptElement orgElement = msg.getTestScriptElement();
		
		if (orgElement == null) {
			throw new ProduceException("No TestScriptElement to clone");
		}
		
		Map<Long, TestScript> scriptMap = new HashMap<Long, TestScript>();
		Map<Long, Metadata> metadataMap = new HashMap<Long, Metadata>();
		Map<Long, SubEngineActionCode> subEngineCodeMap = new HashMap<Long, SubEngineActionCode>();
		
		try {
			// Cache EmbeddedTestScripts, Metadata and ActionCodes, they must not be cloned
			PrepareTestScriptElementCloneVisitor preparationVisitor = new PrepareTestScriptElementCloneVisitor(
					subEngineCodeMap, metadataMap, scriptMap);
			orgElement.accept(preparationVisitor);
		} catch (VisitorException ex) {
			throw new ProduceException("Could not prepare TestConfigElement for cloning", ex);
		}
		
		TestScriptElement clone = orgElement.cloneObject();
		
		try {
			// Restore Actions with cached EmbeddedTestScripts, Metadata and ActionCodes
			TestScriptElementCloneVisitor visitor = new TestScriptElementCloneVisitor(subEngineCodeMap, metadataMap, scriptMap);
			clone.accept(visitor);
		} catch (VisitorException e) {
			throw new ProduceException("Could not clone TestScriptElement: " + e.getMessage());
		}
		
		if (clone.getType() == TestScriptElementType.SCRIPT) {
			
			if (msg.getImportElement() != null 
					&& msg.getImportElement().getValue() != null 
					&& msg.getImportElement().getValue().booleanValue()) {
				// Import of TestScript -> Add TestScript to root folder of current owner
				try {
					Folder root = FolderSupport.getInstance().getRootFolder(
							super.getContext(), NabuccoInstance.getInstance().getOwner());
					((TestScript) clone).setFolder(root);
				} catch (SearchException ex) {
					throw new ProduceException(ex);
				}
			} else {
				// Copy&Paste of TestScript
				clone.setName("Copy of " + clone.getName().getValue());
			}
		}
		
		TestScriptElementContainer container = new TestScriptElementContainer();
		container.setDatatypeState(DatatypeState.INITIALIZED);
		container.setElement(clone);
		msg.setTestScriptElementContainer(container);
		msg.setTestScriptElement(clone);
		return msg;
	}
	
}
