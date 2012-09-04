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
package org.nabucco.testautomation.script.ui.rcp.multipage.folder.masterdetail;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.framework.plugin.base.layout.I18NLabelProviderContributor;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.Folder;
import org.nabucco.testautomation.script.ui.rcp.images.ScriptImageRegistry;

/**
 * FolderMaintenanceMasterDetailLabelProvider
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class FolderMaintenanceMasterDetailLabelProvider implements I18NLabelProviderContributor {

    /**
     * Comment for <code>NAME</code>
     */
    private static final String NAME = "name";

    /**
     * Comment for <code>MASTER_DETAIL_TREE_TEST_SCRIPT</code>
     */
    private static final String MASTER_DETAIL_TREE_TEST_SCRIPT = "MasterDetailTree.TestScript";

    /**
     * Comment for <code>MASTER_DETAIL_TREE_FOLDER</code>
     */
    private static final String MASTER_DETAIL_TREE_FOLDER = "MasterDetailTree.Folder";

    private static final String ICON_FOLDER = "icons/folder.png";

    private static FolderMaintenanceMasterDetailLabelProvider instance = new FolderMaintenanceMasterDetailLabelProvider();

    private FolderMaintenanceMasterDetailLabelProvider() {

    }

    public static FolderMaintenanceMasterDetailLabelProvider getInstance() {
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map.Entry<String, Map<String, ? extends Serializable>> getText(final Object element) {
        Map.Entry<String, Map<String, ? extends Serializable>> result = null;
        if (element instanceof MasterDetailTreeNode) {
            MasterDetailTreeNode treeNode = (MasterDetailTreeNode) element;
            result = getText(treeNode.getDatatype());
        }
        return result;
    }

    /**
     * String representing a special datatype.
     * 
     * @param datatype
     * @return
     */
    private Map.Entry<String, Map<String, ? extends Serializable>> getText(final Datatype datatype) {
        Map.Entry<String, Map<String, ? extends Serializable>> result;

        if (datatype instanceof Folder) {
            Folder folder = (Folder) datatype;
            if (folder.getName() != null) {
                result = createEntry(MASTER_DETAIL_TREE_FOLDER, NAME, folder.getName().getValue());
            } else {
                result = createEntry(MASTER_DETAIL_TREE_FOLDER, NAME, folder.getClass().getSimpleName());
            }
        } else if (datatype instanceof TestScript) {
            TestScript testScript = (TestScript) datatype;
            if (testScript.getName() != null) {
                result = createEntry(MASTER_DETAIL_TREE_TEST_SCRIPT, NAME, testScript.getName().getValue());
            } else {
                result = createEntry(MASTER_DETAIL_TREE_TEST_SCRIPT, NAME, testScript.getClass().getSimpleName());
            }
        } else {
            result = new AbstractMap.SimpleEntry<String, Map<String, ? extends Serializable>>(datatype.getClass()
                    .getSimpleName(), null);
        }

        return result;
    }

    private SimpleEntry<String, Map<String, ? extends Serializable>> createEntry(final String i18n, String key,
            String value) {
        return new AbstractMap.SimpleEntry<String, Map<String, ? extends Serializable>>(i18n, createMap(key, value));
    }

    /**
     * @param string
     * @param name
     * @return
     */
    private Map<String, ? extends Serializable> createMap(final String key, final String value) {
        Map<String, String> result = new HashMap<String, String>();
        result.put(key, value);
        return result;
    }

    @Override
    public String getImage(Object element) {
        if (element instanceof MasterDetailTreeNode) {
            MasterDetailTreeNode treeNode = (MasterDetailTreeNode) element;
            Datatype datatype = treeNode.getDatatype();
            return getImage(datatype);
        } else if (element instanceof Datatype) {
            return getImage((Datatype) element);
        }
        return ICON_FOLDER;
    }

    private String getImage(Datatype datatype) {
        if (datatype instanceof Folder) {
            return ICON_FOLDER;
        } else if (datatype instanceof TestScript) {
            return ScriptImageRegistry.ICON_SCRIPT.getId();
        }
        return null;
    }

}
