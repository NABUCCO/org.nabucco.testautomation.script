/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.search.metadata.model;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
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

    public static final String PROPERTY_METADATA_DESCRIPTION = "metadataDescription";

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
        if (((!oldVal.equals(newName)) && metadata.getDatatypeState().equals(
                DatatypeState.PERSISTENT))) {
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
     * Setter for the MetadataDescription.
     *
     * @param newDescription the String.
     */
    public void setMetadataDescription(String newDescription) {
        if (((metadata != null) && (metadata.getDescription() == null))) {
            Description description = new Description();
            metadata.setDescription(description);
        }
        String oldVal = metadata.getDescription().getValue();
        metadata.getDescription().setValue(newDescription);
        this.updateProperty(PROPERTY_METADATA_DESCRIPTION, oldVal, newDescription);
        if (((!oldVal.equals(newDescription)) && metadata.getDatatypeState().equals(
                DatatypeState.PERSISTENT))) {
            metadata.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the MetadataDescription.
     *
     * @return the String.
     */
    public String getMetadataDescription() {
        if ((((metadata == null) || (metadata.getDescription() == null)) || (metadata
                .getDescription().getValue() == null))) {
            return "";
        }
        return metadata.getDescription().getValue();
    }

    @Override
    public String getId() {
        return MetadataSearchViewModel.ID;
    }
}
