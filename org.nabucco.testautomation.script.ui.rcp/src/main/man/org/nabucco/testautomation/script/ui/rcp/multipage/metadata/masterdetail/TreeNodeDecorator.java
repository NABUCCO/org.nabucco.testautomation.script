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
package org.nabucco.testautomation.script.ui.rcp.multipage.metadata.masterdetail;

import java.util.Map;

import org.eclipse.jface.viewers.IDecoration;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailTreeNode;
import org.nabucco.testautomation.property.facade.datatype.PropertyList;
import org.nabucco.testautomation.script.ui.rcp.images.ScriptImageRegistry;

/**
 * TreeNodeDecorator
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class TreeNodeDecorator {

    public static void decorateNode(MasterDetailTreeNode node, Datatype datatype) {

        if (datatype instanceof PropertyList) {
            PropertyList propertyList = (PropertyList) datatype;
            Map<Integer, String> decorations = node.getDecorations();

            if (propertyList.getReused() != null
                    && propertyList.getReused().getValue() != null
                    && propertyList.getReused().getValue().booleanValue()) {
                if (!decorations.containsKey(IDecoration.BOTTOM_RIGHT)) {
                    decorations.put(IDecoration.BOTTOM_RIGHT, ScriptImageRegistry.ICON_REUSED.getId());
                }
            } else if (decorations.containsKey(IDecoration.BOTTOM_RIGHT)) {
                decorations.remove(IDecoration.BOTTOM_RIGHT);
            }
        }
    }

}
