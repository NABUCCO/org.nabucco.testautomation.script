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
package org.nabucco.testautomation.script.impl.service.produce.clone;

import java.util.Map;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineActionCode;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.dictionary.EmbeddedTestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.visitor.TestScriptVisitor;

/**
 * PrepareTestScriptElementCloneVisitor
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class PrepareTestScriptElementCloneVisitor extends TestScriptVisitor {

	private Map<Long, SubEngineActionCode> actionCodeMap;
	
	private Map<Long, Metadata> metadataMap;
	
	private Map<Long, TestScript> scriptMap;
	
	public PrepareTestScriptElementCloneVisitor(Map<Long, SubEngineActionCode> subEngineCodeMap,
			Map<Long, Metadata> metadataMap, Map<Long, TestScript> scriptMap) {
		this.actionCodeMap = subEngineCodeMap;
		this.metadataMap = metadataMap;
		this.scriptMap = scriptMap;
	}
	
	@Override
	public void visit(Datatype datatype) throws VisitorException {
		
		if (datatype instanceof EmbeddedTestScript) {
			// Only visit EmbeddedTestScript and not the contained TestScript
			this.visit((EmbeddedTestScript) datatype);
		} else {
			super.visit(datatype);
		}
	}

	@Override 
	protected void visit(Action element) throws VisitorException {
		
		SubEngineActionCode actionCode = element.getActionCode();
		Metadata metadata = element.getMetadata();
		
		if (actionCode != null) {
			this.actionCodeMap.put(element.getId(), actionCode);
			element.setActionCode(null);
		}
		
		if (metadata != null) {
			this.metadataMap.put(element.getId(), metadata);
			element.setMetadata(null);
		}
		super.visit(element);
	}
	
	@Override 
    protected void visit(EmbeddedTestScript element) throws VisitorException {
        
		TestScript script = element.getTestScript();
        
        if (script != null) {
        	this.scriptMap.put(element.getId(), script);
        	element.setTestScript(null);
        }
		super.visit(element);
    }
	
}
