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
package org.nabucco.testautomation.script.impl.service.export;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.nabucco.framework.base.facade.component.NabuccoInstance;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.exporting.ExportContainer;
import org.nabucco.framework.base.facade.datatype.serialization.SerializationException;
import org.nabucco.framework.base.facade.datatype.serialization.SerializationResult;
import org.nabucco.framework.base.facade.datatype.serialization.xml.XmlSerializer;
import org.nabucco.framework.base.facade.exception.exporting.ExportException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.exporting.ExportRs;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineCode;
import org.nabucco.testautomation.script.facade.datatype.code.SubEngineOperationCode;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementType;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.impl.service.SubEngineCodeSupport;
import org.nabucco.testautomation.script.impl.service.cache.SubEngineCodeCache;

/**
 * ExportServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ExportServiceHandlerImpl extends ExportServiceHandler {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected ExportRs export(EmptyServiceMessage msg)
			throws ExportException {
		
		Owner owner = NabuccoInstance.getInstance().getOwner();
		ExportRs response = new ExportRs();
		response.setContainer(new ExportContainer());
		XmlSerializer xmlSerializer = new XmlSerializer();
		List<Datatype> entityList = new ArrayList<Datatype>();
		
		long start = System.currentTimeMillis();
		getLogger().info("Loading SubEngineCodes ...");
		
		if (!SubEngineCodeCache.getInstance().isInitialized()) {
			SubEngineCodeSupport.getInstance().initCache(this.getContext());
		}
		
		Query query = this.getEntityManager().createQuery("FROM SubEngineCode");
		
		@SuppressWarnings("unchecked")
		List<SubEngineCode> subEngineCodeList = query.getResultList();
		
		for (SubEngineCode subEngineCode : subEngineCodeList) {
			load(subEngineCode);
		}
		getLogger().info(subEngineCodeList.size() + " SubEngineCodes loaded. Duration: " + (System.currentTimeMillis() - start) + " ms");
		
		start = System.currentTimeMillis();
		getLogger().info("Loading Folder ...");
		
		query = this.getEntityManager().createQuery("FROM Folder f WHERE f.owner = :owner AND f.root = :root");
		query.setParameter("owner", owner);
		query.setParameter("root", new Flag(Boolean.TRUE));
		
		@SuppressWarnings("unchecked")
		List<Folder> folderList = query.getResultList();
		
		for (Folder rootFolder : folderList) {
			load(rootFolder);
		}
		getLogger().info(folderList.size() + " RootFolder loaded. Duration: " + (System.currentTimeMillis() - start) + " ms");
		
		start = System.currentTimeMillis();
		getLogger().info("Loading Metadata ...");

		query = this.getEntityManager().createQuery("FROM Metadata m WHERE m.owner = :owner");
		query.setParameter("owner", owner);
		
		@SuppressWarnings("unchecked")
		List<Metadata> metadataList = query.getResultList();
		
		for (Metadata metadata : metadataList) {
			load(metadata);
		}
		getLogger().info(metadataList.size() + " Metadata loaded. Duration: " + (System.currentTimeMillis() - start) + " ms");
		
		start = System.currentTimeMillis();
		getLogger().info("Loading TestScripts ...");

		query = this.getEntityManager().createQuery("FROM TestScript s WHERE s.owner = :owner");
		query.setParameter("owner", owner);
		
		@SuppressWarnings("unchecked")
		List<TestScript> testScriptList = query.getResultList();
		
		for (TestScript script : testScriptList) {
			load(script);
			load(script.getFolder());
		}
		getLogger().info(testScriptList.size() + " TestScripts loaded. Duration: " + (System.currentTimeMillis() - start) + " ms");
		
		query = this.getEntityManager().createNativeQuery("SELECT DISTINCT script.id FROM conf_test_script_container container " +
				"LEFT OUTER JOIN scpt_test_script_element script ON (container.test_script_ref_id = script.id) " +
				"LEFT OUTER JOIN conf_test_config_element element ON (container.test_config_element_test_script_list_id = element.id) " +
				"WHERE element.owner = '" + owner.getValue() + "' AND script.owner != '" + owner.getValue() + "'");
		
		@SuppressWarnings("unchecked")
		List<BigInteger> foreignScriptIdList = query.getResultList();
		List<TestScript> foreignScriptList = new ArrayList<TestScript>();
		
		for (BigInteger id : foreignScriptIdList) {
			TestScript script = getEntityManager().find(TestScript.class, id.longValue());
			load(script);
			load(script.getFolder());
			foreignScriptList.add(script);
		}
		
		this.getEntityManager().clear();
		
		for (TestScript script : foreignScriptList) {
			script.setOwner(owner);
			script.setFolder(null);
		}
		
		entityList.addAll(subEngineCodeList);
		entityList.addAll(folderList);
		entityList.addAll(metadataList);
		entityList.addAll(testScriptList);
		entityList.addAll(foreignScriptList);
		
		try {
			start = System.currentTimeMillis();
			getLogger().info("Start serializing Script ...");
			SerializationResult serializationResult = xmlSerializer.serialize(entityList, XmlSerializer.DEFAULT_INDENT, true);
			response.getContainer().setResult(serializationResult.getContent());
			response.getContainer().setResourceData(serializationResult.getResourceContainer().toByteArray());
			getLogger().info("Serializing of Script finished. Duration: " + (System.currentTimeMillis() - start) + " ms");
		} catch (SerializationException ex) {
			throw new ExportException("Fatal error during serialization of Script", ex);
		}
		
        return response;
	}
	
	protected void load(Folder folder) {
		
		for (Folder subfolder : folder.getSubFolderList()) {
			load(subfolder);
		}
	}
	
	protected void load(SubEngineCode subEngineCode) {
		
		for (SubEngineOperationCode operation : subEngineCode.getOperationList()) {
			operation.getActionList().size();
		}
	}
	
	private void load(Metadata metadata) throws ExportException {
		
		// Resolve MetadataLabels
		metadata.getLabelList().size();
		try {
			SubEngineCodeSupport.getInstance().resolveSubEngineCodeShallow(metadata);
		} catch (SearchException e) {
			throw new ExportException(e);
		}
		
		for (Metadata child : metadata.getChildren()) {
			load(child);
		}
	}
	
	private void load(TestScriptElement testScriptElement) throws ExportException {
		
		if (testScriptElement instanceof TestScriptComposite) {
			List<TestScriptElementContainer> testScriptElementList = ((TestScriptComposite) testScriptElement).getTestScriptElementList();
			
			for (TestScriptElementContainer container : testScriptElementList) {
				load(container.getElement());
			}
		} else if (testScriptElement.getType() == TestScriptElementType.ACTION) {
			Action action = (Action) testScriptElement;
			
			if (action.getMetadata() != null) {
				load(action.getMetadata());
			}
			
			if (action.getActionCode() != null) {
				action.getActionCode().getParameterList().size();
			}
		}
	}
	
}
