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
package org.nabucco.testautomation.script.facade.datatype.comparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.nabucco.testautomation.script.facade.datatype.code.SubEngineActionCode;

/**
 * SubEngineActionSorter
 * Sorts SubEngineActionCodes by name
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class SubEngineActionSorter implements
		Comparator<SubEngineActionCode> {

	public void sort(List<SubEngineActionCode> actionList) {
		Collections.sort(actionList, this);
	}
	
	@Override
	public int compare(SubEngineActionCode o1,
			SubEngineActionCode o2) {

		if (o1 != null && o2 != null && o1.getName() != null
				&& o2.getName() != null) {
			return o1.getName().getValue().compareTo(o2.getName().getValue());
		}
		return 0;
	}

}
