/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.nabucco.testautomation.script.ui.rcp.search.metadata.model;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Key;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchViewModel;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;

/**
 * MetadataSearchViewModel<p/>Search view for datatype Metadata<p/>
 *
 * @version 1.0
 * @author Markus Jorroch, PRODYNA AG, 2010-10-15
 */
public class MetadataSearchViewModel extends NabuccoComponentSearchViewModel<Metadata> implements
        NabuccoComponentSearchParameter {

    public static final String ID = "org.nabucco.testautomation.script.ui.search.metadata.MetadataSearchViewModel";

    private Metadata metadata;

    public static final String PROPERTY_METADATA_NAME = "metadataName";

    public static final String PROPERTY_METADATA_IDENTIFICATIONKEY = "metadataIdentificationKey";

    public static String TITLE = (ID + "Title");

    /**
     * Constructs a new MetadataSearchViewModel instance.
     *
     * @param viewId the String.
     */
    public MetadataSearchViewModel(String viewId) {
        super();
        correspondingListView = viewId;
        this.metadata = new Metadata();
    }

    @Override
    public String getSearchModelId() {
        return searchModelId;
    }

    @Override
    public NabuccoComponentSearchParameter getSearchParameter() {
        return this;
    }

    /**
     * Getter for the Metadata.
     *
     * @return the Metadata.
     */
    public Metadata getMetadata() {
        return this.metadata;
    }

    /**
     * Setter for the MetadataName.
     *
     * @param newName the String.
     */
    public void setMetadataName(String newName) {
        if (((metadata != null) && (metadata.getName() == null))) {
            Name name = new Name();
            metadata.setName(name);
        }
        String oldVal = metadata.getName().getValue();
        metadata.getName().setValue(newName);
        this.updateProperty(PROPERTY_METADATA_NAME, oldVal, newName);
        if (((!oldVal.equals(newName)) && metadata.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            metadata.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the MetadataName.
     *
     * @return the String.
     */
    public String getMetadataName() {
        if ((((metadata == null) || (metadata.getName() == null)) || (metadata.getName().getValue() == null))) {
            return "";
        }
        return metadata.getName().getValue();
    }

    /**
     * Setter for the MetadataIdentificationKey.
     *
     * @param newIdentificationKey the String.
     */
    public void setMetadataIdentificationKey(String newIdentificationKey) {
        if (((metadata != null) && (metadata.getIdentificationKey() == null))) {
            Key identificationKey = new Key();
            metadata.setIdentificationKey(identificationKey);
        }
        String oldVal = metadata.getIdentificationKey().getValue();
        metadata.getIdentificationKey().setValue(newIdentificationKey);
        this.updateProperty(PROPERTY_METADATA_IDENTIFICATIONKEY, oldVal, newIdentificationKey);
        if (((!oldVal.equals(newIdentificationKey)) && metadata.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            metadata.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the MetadataIdentificationKey.
     *
     * @return the String.
     */
    public String getMetadataIdentificationKey() {
        if ((((metadata == null) || (metadata.getIdentificationKey() == null)) || (metadata.getIdentificationKey()
                .getValue() == null))) {
            return "";
        }
        return metadata.getIdentificationKey().getValue();
    }

    @Override
    public String getId() {
        return MetadataSearchViewModel.ID;
    }
}
