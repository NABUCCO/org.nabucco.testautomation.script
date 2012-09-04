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
package org.nabucco.testautomation.script.facade.datatype.comparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptComposite;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElementContainer;


/**
 * TestScriptElementSorter
 * Sorts TestScriptElements by orderIndex
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class TestScriptElementSorter implements
		Comparator<TestScriptElementContainer> {

	public void sort(TestScriptElement element) {
		
		if (element instanceof TestScriptComposite) {
			List<TestScriptElementContainer> children = ((TestScriptComposite) element).getTestScriptElementList();
			Collections.sort(children, this);
			
			for (TestScriptElementContainer child : children) {
				this.sort(child.getElement());
			}
		}
	}
	
	@Override
	public int compare(TestScriptElementContainer o1,
			TestScriptElementContainer o2) {

		if (o1 != null && o2 != null && o1.getOrderIndex() != null
				&& o2.getOrderIndex() != null) {
			int order1 = o1.getOrderIndex().getValue();
			int order2 = o2.getOrderIndex().getValue();

			return order1 - order2;
		}
		return 0;
	}

}
