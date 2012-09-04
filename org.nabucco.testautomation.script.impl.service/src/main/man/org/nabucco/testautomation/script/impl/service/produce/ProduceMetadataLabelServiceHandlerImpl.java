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
package org.nabucco.testautomation.script.impl.service.produce;

import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;
import org.nabucco.testautomation.script.facade.message.MetadataLabelMsg;
import org.nabucco.testautomation.script.impl.service.produce.ProduceMetadataLabelServiceHandler;


/**
 * ProduceMetadataLabelServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ProduceMetadataLabelServiceHandlerImpl extends ProduceMetadataLabelServiceHandler {

	private static final long serialVersionUID = 1L;
	
	@Override
	public MetadataLabelMsg produceMetadataLabel(EmptyServiceMessage msg)
			throws ProduceException {
		
		MetadataLabelMsg rs = new MetadataLabelMsg();
		MetadataLabel label = MetadataFactory.getInstance().createMetadataLabel();
		rs.setMetadataLabel(label);
		return rs;
	}

}
