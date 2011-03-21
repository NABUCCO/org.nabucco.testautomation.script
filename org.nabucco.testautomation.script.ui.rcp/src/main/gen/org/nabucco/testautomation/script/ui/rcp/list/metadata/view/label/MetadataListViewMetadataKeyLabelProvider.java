/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.list.metadata.view.label;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;

/**
 * MetadataListViewMetadataKeyLabelProvider
 *
 * @author Undefined
 */
public class MetadataListViewMetadataKeyLabelProvider implements ILabelProvider {

    /** Constructs a new MetadataListViewMetadataKeyLabelProvider instance. */
    public MetadataListViewMetadataKeyLabelProvider() {
        super();
    }

    @Override
    public Image getImage(Object arg0) {
        return null;
    }

    @Override
    public String getText(Object arg0) {
        String result = "";
        if ((arg0 instanceof Metadata)) {
            Metadata metadata = ((Metadata) arg0);
            result = ((metadata.getIdentificationKey() != null) ? metadata.getIdentificationKey()
                    .toString() : "");
        }
        return result;
    }

    @Override
    public void addListener(ILabelProviderListener arg0) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean isLabelProperty(Object arg0, String arg1) {
        return false;
    }

    @Override
    public void removeListener(ILabelProviderListener arg0) {
    }
}
