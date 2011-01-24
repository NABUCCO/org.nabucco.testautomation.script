/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.list.metadata.view.comparator;

import org.nabucco.framework.plugin.base.component.list.view.NabuccoColumComparator;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;

/**
 * MetadataListViewMetadataDescriptionComparator
 *
 * @author Undefined
 */
public class MetadataListViewMetadataDescriptionComparator extends NabuccoColumComparator<Metadata> {

    /** Constructs a new MetadataListViewMetadataDescriptionComparator instance. */
    public MetadataListViewMetadataDescriptionComparator() {
        super();
    }

    @Override
    public int compareConcrete(Metadata object1, Metadata object2) {
        return this.compareBasetype(object1.getDescription(), object2.getDescription());
    }
}
