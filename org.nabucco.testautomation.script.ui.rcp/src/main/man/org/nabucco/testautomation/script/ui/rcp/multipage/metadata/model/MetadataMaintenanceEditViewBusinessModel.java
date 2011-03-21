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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model;

import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.logging.Loggable;
import org.nabucco.framework.plugin.base.model.BusinessModel;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.facade.message.MetadataSearchMsg;
import org.nabucco.testautomation.script.ui.rcp.communication.ScriptComponentServiceDelegateFactory;
import org.nabucco.testautomation.script.ui.rcp.communication.maintain.MaintainMetadataDelegate;
import org.nabucco.testautomation.script.ui.rcp.communication.produce.ProduceMetadataDelegate;
import org.nabucco.testautomation.script.ui.rcp.communication.search.SearchMetadataDelegate;

/**
 * MetadataMaintenanceEditViewBusinessModel
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class MetadataMaintenanceEditViewBusinessModel implements BusinessModel, Loggable {

	public static String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.metadata.model.MetadataMaintenanceEditViewBusinessModel";

	@Override
	public String getID() {
		return MetadataMaintenanceEditViewBusinessModel.ID;
	}

	/**
	 * Saves a Metadata .
	 * 
	 * @param metadata
	 *            the Metadata
	 * @return the Metadata
	 * 
	 * @throws ClientException
	 *             if the save was unsuccesful
	 */
	public Metadata save(final Metadata metadata) throws ClientException {
		ScriptComponentServiceDelegateFactory scriptComponentServiceDelegateFactory = ScriptComponentServiceDelegateFactory
		.getInstance();

		MaintainMetadataDelegate maintainMetadataDelegate = scriptComponentServiceDelegateFactory
		.getMaintainMetadata();

		MetadataMsg rq = new MetadataMsg();
		rq.setMetadata(metadata);
		MetadataMsg response = maintainMetadataDelegate.maintainMetadata(rq);

		if (response != null) {
			return response.getMetadata();
		}

		return metadata;
	}

	public Metadata readMetadata(Metadata metadata) {
		try {
			ScriptComponentServiceDelegateFactory scriptComponentServiceDelegateFactory = ScriptComponentServiceDelegateFactory
			.getInstance();
			SearchMetadataDelegate searchMetadataDelegate = scriptComponentServiceDelegateFactory
			.getSearchMetadata();

			MetadataSearchMsg rq = new MetadataSearchMsg();
			rq.setIdentifier(new Identifier(metadata.getId()));
			MetadataMsg response = searchMetadataDelegate.getMetadata(rq);
			if (response != null) {
				return response.getMetadata();
			}
		} catch (ClientException e) {
			Activator.getDefault().logError(e);
		}
		return metadata;
	}

	public Metadata importDatatype(Metadata metadata) {
		try {
			ScriptComponentServiceDelegateFactory scriptComponentServiceDelegateFactory = ScriptComponentServiceDelegateFactory
			.getInstance();
			ProduceMetadataDelegate produceMetadataDelegate = scriptComponentServiceDelegateFactory.getProduceMetadata();

			MetadataMsg rq = new MetadataMsg();
			rq.setMetadata(metadata);
			MetadataMsg response = produceMetadataDelegate.produceMetadataClone(rq);
			if (response != null) {
				return response.getMetadata();
			}
		} catch (ClientException e) {
			Activator.getDefault().logError(e);
		}
		return metadata;
	}
}

