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
package org.nabucco.testautomation.script.impl.service.maintain;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionAccessor;
import org.nabucco.framework.base.facade.datatype.visitor.DatatypeVisitor;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceExceptionMapper;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceHelper;
import org.nabucco.testautomation.script.facade.datatype.comparator.TestScriptElementSorter;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;
import org.nabucco.testautomation.script.impl.service.FolderSupport;
import org.nabucco.testautomation.script.impl.service.PropertySupport;
import org.nabucco.testautomation.script.impl.service.SubEngineCodeSupport;
import org.nabucco.testautomation.script.impl.service.cache.SubEngineCodeCache;
import org.nabucco.testautomation.script.impl.service.maintain.MaintainTestScriptServiceHandler;
import org.nabucco.testautomation.script.impl.service.maintain.visitor.TestScriptModificationVisitor;

import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.facade.datatype.property.base.PropertyUsageType;
import org.nabucco.testautomation.facade.datatype.visitor.PropertyModificationVisitor;

/**
 * MaintainTestScriptServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class MaintainTestScriptServiceHandlerImpl extends MaintainTestScriptServiceHandler {

	private static final long serialVersionUID = 1L;

	private static final String PREFIX = "TSC-";
	
	private static final TestScriptElementSorter elementSorter = new TestScriptElementSorter();

	private PersistenceHelper persistenceHelper;
	
	@Override
	public TestScriptMsg maintainTestScript(TestScriptMsg msg)
			throws MaintainException {
		
		TestScript script = msg.getTestScript();

		try {
			// initialize PersistenceHelper
			this.persistenceHelper = new PersistenceHelper(super.getEntityManager());
			
			if (script.getDatatypeState() == DatatypeState.PERSISTENT) {
	            DatatypeVisitor visitor = new TestScriptModificationVisitor(
	            		script);
	            script.accept(visitor);
	        }
		
            switch (script.getDatatypeState()) {

            case CONSTRUCTED:
                throw new MaintainException("TestScript is not initialized.");
            case INITIALIZED:
                script = this.create(script);
                break;
            case MODIFIED:
                script = this.update(script);
                break;
            case DELETED:
                this.delete(script);
                script = null;
                break;
            case TRANSIENT:
                break;
            case PERSISTENT:
                break;
            default:
                throw new MaintainException("Datatype state '"
                        + script.getDatatypeState()
                        + "' is not valid for TestScript.");
            }
        } catch (VisitorException e) {
            throw new MaintainException(e);
		} catch (MaintainException e) {
			throw e;
		} catch (Exception ex) {
			throw new MaintainException("Error maintaining TestScript",
					PersistenceExceptionMapper.resolve(ex,
							TestScript.class.getName(),
							script.getId()));
        }
		
		this.persistenceHelper.flush();
        this.persistenceHelper = null;
		
        if (script != null) {
        	
        	if (!SubEngineCodeCache.getInstance().isInitialized()) {
				SubEngineCodeSupport.getInstance().initCache(this.getEntityManager());
			}
        	
        	load(script);
        	
        	// Detach Entity
			this.getEntityManager().clear();
			
			// Sort
			elementSorter.sort(script);
        }
        
        msg.setTestScript(script);
		return msg;
	}
	
	private void load(TestScriptElement testScriptElement) {
		
		try {
			PropertySupport.getInstance().resolveProperties(testScriptElement, super.getContext());
		} catch (Exception e) {
			super.getLogger().error(e, "Could not resolve Properties for TestScriptElement '" 
					+ testScriptElement.getId() + "'");
		}
		
		if (testScriptElement instanceof TestScriptComposite) {
			List<TestScriptElementContainer> testScriptElementList = ((TestScriptComposite) testScriptElement)
					.getTestScriptElementList();
			
			for (TestScriptElementContainer testScriptElementContainer : testScriptElementList) {
				TestScriptElement element = testScriptElementContainer.getElement();
				testScriptElementContainer.setDatatypeState(DatatypeState.PERSISTENT);
				element.setDatatypeState(DatatypeState.PERSISTENT);
				load(element);
			}
		} else if (testScriptElement.getType() == TestScriptElementType.ACTION) {
			Action action = (Action) testScriptElement;
			
			if (action.getMetadata() != null) {
				try {
					SubEngineCodeSupport.getInstance().resolveSubEngineCodeShallow(action.getMetadata());
				} catch (SearchException e) {
					super.getLogger().error(e, "Could not resolve SubEngineCodes for Metadata of Action '" 
							+ testScriptElement.getId() + "'");
				}
			}
			if (action.getAction() != null) {
				action.setAction(SubEngineCodeCache.getInstance()
						.getActionCode(action.getAction().getId()));
			}
		}
	}
	
	private TestScript create(TestScript testScript) throws PersistenceException {

		// if no folder selected, add script to root folder
		if (testScript.getFolder() == null){
			try {
				testScript.setFolder(FolderSupport.getInstance().getRootFolder(super.getContext()));
			} catch (Exception e) {
				super.getLogger().error(e,
						"Could not set Rootfolder for TestScript '", testScript.getName().getValue(), "'");
			}
		}
		
		List<TestScriptElementContainer> elements = testScript.getTestScriptElementList();
		
		for (int i = 0; i < elements.size(); i++) {
			TestScriptElementContainer updatedElement = create(elements.get(i));
			elements.set(i, updatedElement);
		}
		
		testScript = (TestScript) create((TestScriptElement) testScript);
		testScript.setTestScriptKey(PREFIX + testScript.getId());
		testScript.setDatatypeState(DatatypeState.MODIFIED);
		testScript = this.persistenceHelper.persist(testScript);
		return testScript;
	}
	
	private TestScriptElementContainer create(TestScriptElementContainer entity) throws PersistenceException {
		
		TestScriptElement element = entity.getElement();
		
		if (element instanceof TestScriptComposite) {
			List<TestScriptElementContainer> testScriptElementList = ((TestScriptComposite) element).getTestScriptElementList();
	
			for (int i = 0; i < testScriptElementList.size(); i++) {
				TestScriptElementContainer updatedElement = create(testScriptElementList
						.get(i));
				testScriptElementList.set(i, updatedElement);
			}
		}

		// Create TestScriptElement
		element = create(element);
		entity.setElement(element);

		// Create TestScriptElementContainer
		entity = this.persistenceHelper.persist(entity);
		entity.setElement(element);
		return entity;
    }
	
	private TestScriptElement create(TestScriptElement entity) throws PersistenceException {
		
		PropertyList propertyList = entity.getPropertyList();
		
		if (propertyList != null) {
        	propertyList = update(propertyList);
        	entity.setPropertyList(propertyList);
        }
		
		entity = this.persistenceHelper.persist(entity);
		return entity;
	}
	
	private TestScript update(TestScript testScript) throws PersistenceException {
        
		if(testScript.getFolder() == null){
			try {
				testScript.setFolder(FolderSupport.getInstance().getRootFolder(super.getContext()));
			} catch (Exception e) {
				super.getLogger().error(e,
						"Could not set Rootfolder for TestScript '" + testScript.getId() + "'");
			}
		}
		
		List<TestScriptElementContainer> elements = testScript.getTestScriptElementList();
		List<TestScriptElementContainer> deletedContainer = NabuccoCollectionAccessor.getInstance().getUnassignedList(elements);
		
		// Create or update children 
		for (int i = 0; i < elements.size(); i++) {
			TestScriptElementContainer updatedElement = update(elements.get(i));
			elements.set(i, updatedElement);
		}
		
		// Delete removed TestScriptElementContainer
		for (TestScriptElementContainer testScriptElementContainer : deletedContainer) {
			delete(testScriptElementContainer);
		}
		
		// Update TestScript
		testScript = (TestScript) update((TestScriptElement) testScript);
		return testScript;
    }
	
	private TestScriptElementContainer update(TestScriptElementContainer entity) throws PersistenceException {
		
		TestScriptElement element = entity.getElement();

		if (element instanceof TestScriptComposite) {
			List<TestScriptElementContainer> testScriptElementList = ((TestScriptComposite) element).getTestScriptElementList();
			List<TestScriptElementContainer> deletedContainer = NabuccoCollectionAccessor.getInstance().getUnassignedList(testScriptElementList);
			
			// Create or update children
			for (int i = 0; i < testScriptElementList.size(); i++) {
				TestScriptElementContainer updatedElement = update(testScriptElementList
						.get(i));
				testScriptElementList.set(i, updatedElement);
			}
			
			// Delete removed TestScriptElementContainer
			for (TestScriptElementContainer testScriptElementContainer : deletedContainer) {
				delete(testScriptElementContainer);
			}
		}
		
    	// Update TestScriptElement
		element = update(element);
		entity.setElement(element);

        // Update TestScriptElementContainer
        entity = this.persistenceHelper.persist(entity);
        return entity;
	}
	
	private TestScriptElement update(TestScriptElement entity) throws PersistenceException {
		
		PropertyList propertyList = entity.getPropertyList();
		
		if (propertyList != null) {
        	propertyList = update(propertyList);
        	entity.setPropertyList(propertyList);
        }
		
		entity = this.persistenceHelper.persist(entity);
		return entity;
	}
	
	private PropertyList update(PropertyList propertyList) {
    	
    	if (propertyList.getDatatypeState() == DatatypeState.PERSISTENT) {
        	try {
				PropertyModificationVisitor visitor = new PropertyModificationVisitor(propertyList);
				propertyList.accept(visitor);
			} catch (VisitorException e) {
				super.getLogger().error(e, "Could not check PropertyList '", propertyList.getName().getValue(),
						"' (ID:" + propertyList.getId() + ") for modification");
			}
        }
        	
    	if (propertyList.getDatatypeState() != DatatypeState.PERSISTENT) {        	
            try {
            	propertyList.setUsageType(PropertyUsageType.TEST_SCRIPT_ELEMENT_PARAM);
				propertyList = PropertySupport.getInstance().maintainPropertyList(propertyList, super.getContext());
			} catch (Exception e) {
				super.getLogger().error(e, "Could not maintain PropertyList '", propertyList.getName().getValue(),
						"' (ID:" + propertyList.getId() + ")");
			}
    	}
    	return propertyList;
    }
	
	private void delete(TestScript script) throws PersistenceException {
        
		if (script.getId() == null) {
            throw new IllegalArgumentException("TestScript is not persistent.");
        }
		
		// Check, if TestScript is not referenced by any TestConfigElement
		integrityCheck(script.getId());
		
		// Delete children
		List<TestScriptElementContainer> testScriptElementList = script.getTestScriptElementList();
		
		for (TestScriptElementContainer testScriptElementContainer : testScriptElementList) {
			testScriptElementContainer.setDatatypeState(DatatypeState.DELETED);
			delete(testScriptElementContainer);
		}
		
		PropertyList propertyList = script.getPropertyList();
		
		// Delete Properties
		if (propertyList != null) {
			propertyList.setDatatypeState(DatatypeState.DELETED);
        	propertyList = update(propertyList);
        	script.setPropertyList(null);
		}
		
		// Delete TestScript
		script.setDatatypeState(DatatypeState.DELETED);
		this.persistenceHelper.persist(script);
    }
	
	private void delete(TestScriptElementContainer entity) throws PersistenceException {
		
		// Do not delete TestScriptElementContainer that were moved by Drag&Drop
		if (entity.getDatatypeState() != DatatypeState.DELETED) {
			return;
		}
		
		TestScriptElement element = entity.getElement();
		
		// TestScripts must not be deleted, only the TestScriptElementContainer
		if (element.getType() == TestScriptElementType.SCRIPT) {
			this.persistenceHelper.persist(entity);
			return;
		}
		
		// Delete children
		if (element instanceof TestScriptComposite) {
			TestScriptComposite composite = (TestScriptComposite) element;
			
			for (TestScriptElementContainer child : composite.getTestScriptElementList()) {
				child.setDatatypeState(DatatypeState.DELETED);
				delete(child);
			}
		}
		
		// Delete TestScriptElementContainer
		this.persistenceHelper.persist(entity);
		
		PropertyList propertyList = element.getPropertyList();
		
		// Delete PropertyList
		if (propertyList != null) {
			propertyList.setDatatypeState(DatatypeState.DELETED);
        	propertyList = update(propertyList);
        	element.setPropertyList(null);
        }
		
		// Delete TestScriptElement
		element.setDatatypeState(DatatypeState.DELETED);
		this.persistenceHelper.persist(element);		
	}
	
	private void integrityCheck(Long testScriptId) throws PersistenceException {

		List<Long> testConfigElementIdList = ReferenceSupport.getInstance()
				.findReferencingTestConfigElements(super.getEntityManager(),
						testScriptId);

		if (!testConfigElementIdList.isEmpty()) {
			StringBuffer buf = new StringBuffer();
			buf.append("[");
			
			for (Long refId : testConfigElementIdList) {
				buf.append(refId);
				buf.append(" ");
			}
			buf.append("]");
			
			throw new PersistenceException("Cannot delete TestScript ("
					+ testScriptId + "). It is referenced by "
					+ testConfigElementIdList.size() + " TestConfigElements "
					+ buf.toString());
		}
	}

}
