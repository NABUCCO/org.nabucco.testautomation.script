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
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;
import org.nabucco.testautomation.script.facade.datatype.metadata.MetadataLabel;

/**
 * MetadataFactory
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class MetadataFactory {

    private static MetadataFactory instance;

    private MetadataFactory() {
    }

    public static MetadataFactory getInstance() {

        if (instance == null) {
            instance = new MetadataFactory();
        }
        return instance;
    }

    public Metadata createMetadata() {

        Metadata metadata = new Metadata();
        metadata.setDatatypeState(DatatypeState.INITIALIZED);
        metadata.setName("Enter Name ...");
        metadata.getLabelList().add(createMetadataLabel());
        metadata.setIdentificationKey("");
        return metadata;
    }

    public MetadataLabel createMetadataLabel() {

        MetadataLabel label = new MetadataLabel();
        label.setDatatypeState(DatatypeState.INITIALIZED);
        return label;
    }

}
