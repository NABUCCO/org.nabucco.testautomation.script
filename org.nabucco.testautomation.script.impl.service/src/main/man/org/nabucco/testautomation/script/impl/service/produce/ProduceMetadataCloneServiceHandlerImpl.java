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

import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.message.MetadataMsg;
import org.nabucco.testautomation.script.impl.service.produce.ProduceMetadataCloneServiceHandler;
import org.nabucco.testautomation.script.impl.service.produce.clone.MetadataCloneVisitor;


/**
 * ProduceMetadataCloneServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ProduceMetadataCloneServiceHandlerImpl extends ProduceMetadataCloneServiceHandler {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected MetadataMsg produceMetadataClone(MetadataMsg msg)
			throws ProduceException {
		
		Metadata orgMetadata = msg.getMetadata();
		
		if (orgMetadata == null) {
			throw new ProduceException("No Metadata to clone");
		}
		
		Metadata clone = orgMetadata.cloneObject();
		
		try {
			MetadataCloneVisitor visitor = new MetadataCloneVisitor();
			clone.accept(visitor);
		} catch (VisitorException e) {
			throw new ProduceException("Could not clone Metadata: " + e.getMessage());
		}
		
		msg.setMetadata(clone);
		return msg;
	}

}
