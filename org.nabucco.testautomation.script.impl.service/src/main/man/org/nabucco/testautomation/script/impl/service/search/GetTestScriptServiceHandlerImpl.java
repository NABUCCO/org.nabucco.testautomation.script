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
package org.nabucco.testautomation.script.impl.service.search;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.nabucco.framework.base.facade.component.NabuccoInstance;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.validation.constraint.element.ConstraintFactory;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceCleaner;
import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.script.facade.datatype.comparator.TestScriptElementSorter;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptSearchMsg;
import org.nabucco.testautomation.script.impl.service.PropertySupport;
import org.nabucco.testautomation.script.impl.service.SubEngineCodeSupport;
import org.nabucco.testautomation.script.impl.service.cache.SubEngineCodeCache;

/**
 * GetTestScriptServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class GetTestScriptServiceHandlerImpl extends GetTestScriptServiceHandler {

	private static final long serialVersionUID = 1L;
	
	private static final TestScriptElementSorter elementSorter = new TestScriptElementSorter();
	
	private List<Action> actionList = new ArrayList<Action>();
	
	@Override
	protected TestScriptMsg getTestScript(TestScriptSearchMsg msg)
			throws SearchException {

		if (msg.getIdentifier() == null || msg.getIdentifier().getValue() == null) {
			throw new SearchException("Mandatory Identifier is null");
		}
		
		String queryString = "select s from TestScript s where s.id = :id";
		Query query = super.getEntityManager().createQuery(
				queryString);
		query.setParameter("id", msg.getIdentifier().getValue());

		TestScript testScript = (TestScript) query.getSingleResult();

		if (testScript == null) {
			throw new SearchException("TestScript with id '" + msg.getIdentifier().getValue() + "' not found");
		}
		
		// load all children
		this.actionList.clear();
		this.load(testScript);
		
		// Detach Entity
		this.getEntityManager().clear();
		
		if (!SubEngineCodeCache.getInstance().isInitialized()) {
			SubEngineCodeSupport.getInstance().initCache(this.getContext());
		}

		// load actions
		for (Action action : this.actionList) {
			this.resolveSubEngineCodes(action);
		}
		this.actionList.clear();
		
		try {
			testScript.accept(new PersistenceCleaner());
		} catch (VisitorException e) {
			throw new SearchException(e);
		}
		
		// Sort
		elementSorter.sort(testScript);
		
		// Check owner and set Editable-Constraint
		if (!testScript.getOwner().equals(NabuccoInstance.getInstance().getOwner())) {
			try {
				testScript.addConstraint(ConstraintFactory.getInstance()
						.createEditableConstraint(false), true);
			} catch (VisitorException ex) {
				throw new SearchException(ex);
			}
		}
		
		TestScriptMsg rs = new TestScriptMsg();
		rs.setTestScript(testScript);
		return rs;
	}
	
	private void load(TestScriptElement testScriptElement) {
		
		try {
			PropertySupport.getInstance().resolveProperties(testScriptElement, super.getContext());
		} catch (Exception e) {
			super.getLogger().error(e, "Could not resolve Properties for TestScriptElement '" 
					+ testScriptElement.getId() + "'");
		}
		
		if (testScriptElement instanceof TestScriptComposite) {
			List<TestScriptElementContainer> testScriptElementList = ((TestScriptComposite) testScriptElement).getTestScriptElementList();
			
			for (TestScriptElementContainer testScriptElementContainer : testScriptElementList) {
				TestScriptElement element = testScriptElementContainer.getElement();
				testScriptElementContainer.setDatatypeState(DatatypeState.PERSISTENT);
				element.setDatatypeState(DatatypeState.PERSISTENT);
				load(element);
			}
		} else if (testScriptElement.getType() == TestScriptElementType.ACTION) {
			Action action = (Action) testScriptElement;
			this.actionList.add(action);
			
			// Resolving MetadataLabels must be done before detaching Entities
			Metadata metadata = action.getMetadata();
			
			if (metadata != null) {

				for (MetadataLabel label : metadata.getLabelList()) {
					try {
						PropertyList propertyList = PropertySupport
								.getInstance().getPropertyList(
										label.getPropertyListRefId(),
										super.getContext());
						label.setPropertyList(propertyList);
					} catch (Exception e) {
						super.getLogger().error(e, "Could not resolve PropertyList for MetadataLabel of Action '" 
								+ action.getId() + "'");
					}
				}
			}
		}
	}
	
	/**
	 * Resolves SubEngineCode and SubEngineActionCode. This must be done
	 * after detaching the Entity in order to avoid unnecessary updates.
	 * 
	 * @param action
	 */
	private void resolveSubEngineCodes(Action action) {
		
		Metadata metadata = action.getMetadata();
		
		if (metadata != null) {
			try {
				SubEngineCodeSupport.getInstance().resolveSubEngineCodeShallow(metadata);
			} catch (SearchException e) {
				super.getLogger().error(e, "Could not resolve SubEngineCodes for Metadata of Action '" 
						+ action.getId() + "'");
			}
		}
		if (action.getActionCode() != null) {
			action.setActionCode(SubEngineCodeCache.getInstance()
					.getActionCode(action.getActionCode().getId()));
		}
	}

}
