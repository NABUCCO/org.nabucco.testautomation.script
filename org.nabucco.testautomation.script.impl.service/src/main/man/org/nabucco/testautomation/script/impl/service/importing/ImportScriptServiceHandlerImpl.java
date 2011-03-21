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
package org.nabucco.testautomation.script.impl.service.importing;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.importing.ImportContainer;
import org.nabucco.framework.base.facade.datatype.importing.ImportContext;
import org.nabucco.framework.base.facade.datatype.serialization.SerializationException;
import org.nabucco.framework.base.facade.exception.importing.ImportException;
import org.nabucco.framework.base.facade.message.importing.ImportRq;
import org.nabucco.framework.base.facade.message.importing.ImportRs;
import org.nabucco.testautomation.facade.service.importing.BasicImporter;


/**
 * ImportScriptServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ImportScriptServiceHandlerImpl extends
		ImportScriptServiceHandler {

	private static final long serialVersionUID = 1L;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ImportRs importScript(ImportRq msg)
			throws ImportException {

		long start = System.currentTimeMillis();
		getLogger().info("Start importing Script ...");
		ImportContext importContext = msg.getImportContext();
        ImportContainer container = msg.getContainer();

        ImportRs result = new ImportRs();
        result.setImportContext(importContext);

        // Import SubEngineCodes first
        BasicImporter importer = new SubEngineCodeImporter(this.getEntityManager(), importContext);
        importer.handleImport(container);
        
        TestScriptImporter testScriptImporter = new TestScriptImporter(getEntityManager(), importContext);
        MetadataImporter metadataImporter = new MetadataImporter(getEntityManager(), importContext);
        Owner owner = testScriptImporter.getOwner();
		
        try {
			List<Datatype> deserialized = metadataImporter.deserialize(container);

			// Delete all existing TestScripts and Folder for importing owner
			testScriptImporter.deleteAll(owner);

			// Delete all existing Metadata for importing owner
			metadataImporter.deleteAll(owner);
			
			// Import Metadata
			importContext.getEntries().addAll(metadataImporter.importAll(deserialized));
			
			// Import TestScripts and Folder
			importContext.getEntries().addAll(testScriptImporter.importAll(deserialized));
			getLogger().info("Finished importing of Script. Duration: " + (System.currentTimeMillis() - start) + " ms");
			
			return result;
		} catch (SerializationException ex) {
			 throw new ImportException("Deserialization of ImportData failed !", ex);
		}
	}
	
}
