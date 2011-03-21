/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.ui.rcp.list.script.view.comparator;

import org.nabucco.framework.plugin.base.component.list.view.NabuccoColumComparator;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;

/**
 * TestScriptListViewTestScriptKeyComparator
 *
 * @author Undefined
 */
public class TestScriptListViewTestScriptKeyComparator extends NabuccoColumComparator<TestScript> {

    /** Constructs a new TestScriptListViewTestScriptKeyComparator instance. */
    public TestScriptListViewTestScriptKeyComparator() {
        super();
    }

    @Override
    public int compareConcrete(TestScript object1, TestScript object2) {
        return this.compareBasetype(object1.getIdentificationKey(), object2.getIdentificationKey());
    }
}
