/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.list.metadata.view.comparator;

import org.nabucco.framework.plugin.base.component.list.view.NabuccoColumComparator;
import org.nabucco.testautomation.script.facade.datatype.metadata.Metadata;

/**
 * MetadataListViewMetadataKeyComparator
 *
 * @author Undefined
 */
public class MetadataListViewMetadataKeyComparator extends NabuccoColumComparator<Metadata> {

    /** Constructs a new MetadataListViewMetadataKeyComparator instance. */
    public MetadataListViewMetadataKeyComparator() {
        super();
    }

    @Override
    public int compareConcrete(Metadata object1, Metadata object2) {
        return this.compareBasetype(object1.getIdentificationKey(), object2.getIdentificationKey());
    }
}
