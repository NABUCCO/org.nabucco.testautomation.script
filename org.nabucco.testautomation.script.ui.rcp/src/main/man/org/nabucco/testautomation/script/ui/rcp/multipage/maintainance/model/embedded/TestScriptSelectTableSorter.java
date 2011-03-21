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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.embedded;

import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableSorter;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.ScriptMaintainanceMasterDetailAddDialogLabelProvider;

/**
 * TestScriptSelectTableSorter
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class TestScriptSelectTableSorter extends NabuccoTableSorter {

	@Override
	protected int compare(Object e1, Object e2) {
		if(currentColumn == 0){
			TestScriptSelectKeyColumnDialogLabelProvider provider = new TestScriptSelectKeyColumnDialogLabelProvider();
			return (provider.getText(e1).compareTo(provider.getText(e2)));
		} else {
			ScriptMaintainanceMasterDetailAddDialogLabelProvider provider = new ScriptMaintainanceMasterDetailAddDialogLabelProvider();
			return (provider.getText(e1).compareTo(provider.getText(e2)));
		}
	}

}
