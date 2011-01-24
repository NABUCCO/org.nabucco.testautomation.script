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

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;
import org.nabucco.testautomation.script.facade.message.ProduceTestScriptElementMsg;
import org.nabucco.testautomation.script.impl.service.produce.ProduceTestScriptElementCloneServiceHandler;
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
		
		TestScriptElement clone = orgElement.cloneObject();
		
		try {
			TestScriptElementCloneVisitor visitor = new TestScriptElementCloneVisitor();
			clone.accept(visitor);
		} catch (VisitorException e) {
			throw new ProduceException("Could not clone TestScriptElement: " + e.getMessage());
		}
		
		TestScriptElementContainer container = new TestScriptElementContainer();
		container.setDatatypeState(DatatypeState.INITIALIZED);
		container.setElement(clone);
		msg.setTestScriptElementContainer(container);
		msg.setTestScriptElement(clone);
		return msg;
	}
	
}
