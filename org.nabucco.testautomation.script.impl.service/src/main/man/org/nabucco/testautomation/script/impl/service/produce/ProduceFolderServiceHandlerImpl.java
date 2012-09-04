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

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.message.FolderMsg;

/**
 * ProduceFolderServiceHandlerImpl
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ProduceFolderServiceHandlerImpl extends ProduceFolderServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    public FolderMsg produceFolder(EmptyServiceMessage msg) throws ProduceException {

        FolderMsg rs = new FolderMsg();
        Folder folder = new Folder();
        folder.setDatatypeState(DatatypeState.INITIALIZED);
        folder.setRoot(Boolean.FALSE);
        folder.setName("Enter name ...");
        folder.setIdentificationKey("");
        rs.setFolder(folder);
        return rs;
    }

}
