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
package org.nabucco.testautomation.script.ui.rcp.multipage.folder.model;

import java.util.HashMap;
import java.util.Map;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.facade.message.FolderMsg;
import org.nabucco.testautomation.script.ui.rcp.communication.ScriptComponentServiceDelegateFactory;
import org.nabucco.testautomation.script.ui.rcp.communication.produce.ProduceFolderDelegate;


public class FolderElementFactory {

    private static ScriptComponentServiceDelegateFactory scriptComponentServiceDelegateFactory = ScriptComponentServiceDelegateFactory
            .getInstance();

    private static Map<Class<?>, Datatype> cache = new HashMap<Class<?>, Datatype>();


    public static Datatype create(Class<?> className) {
        Datatype cachedElement = cache.get(className);
        if (cachedElement != null) {
            return cachedElement.cloneObject();
        }
        Datatype result = null;
        try {
            if (className.equals(Folder.class)) {
                ProduceFolderDelegate produceFolderDelegate = scriptComponentServiceDelegateFactory
                        .getProduceFolder();
                FolderMsg response = produceFolderDelegate
                        .produceFolder(new EmptyServiceMessage());
                if (response != null) {
                	Folder folder = response.getFolder();
                    result = folder;
                }
            }
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
        cache.put(className, result.cloneObject());
        return result;
    }

	public static Folder getFolderStructure() {
		try {
			FolderMsg response = scriptComponentServiceDelegateFactory.getSearchFolder().getFolderStructure(new EmptyServiceMessage());
			Folder folder = response.getFolder();
			if(folder == null){
				Activator.getDefault().logError("Fatal error. No root folder found!");
			} else {
//				List<TestScript> rootScripts = loadRootScripts();
//				folder.getTestScriptList().addAll(rootScripts);
				return folder;
			}
		} catch (ClientException e) {
			Activator.getDefault().logError(e);
		}
		return null;
	}
	
//	private static List<TestScript> loadRootScripts() {
//        try {
//        	SearchTestScriptDelegate searchTestScriptDelegate = ScriptComponentServiceDelegateFactory
//            	.getInstance().getSearchTestScript();
//
//        	TestScriptSearchMsg rq = new TestScriptSearchMsg();
//        	rq.setHasFolder(new Flag(false));
//            TestScriptListMsg rs = searchTestScriptDelegate.searchTestScript(rq);
//
//            return rs.getTestScriptList();
//
//        } catch (ClientException e) {
//            Activator.getDefault().logError(e);
//        }
//
//		return new ArrayList<TestScript>();
//    }

}
