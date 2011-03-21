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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.ui.forms.DetailsPart;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailBlock;
import org.nabucco.framework.plugin.base.component.multipage.masterdetail.MasterDetailBlockLayouter;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Action;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Assertion;
import org.nabucco.testautomation.script.facade.datatype.dictionary.BreakLoop;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Condition;
import org.nabucco.testautomation.script.facade.datatype.dictionary.EmbeddedTestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Execution;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Foreach;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Lock;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Logger;
import org.nabucco.testautomation.script.facade.datatype.dictionary.Loop;
import org.nabucco.testautomation.script.facade.datatype.dictionary.PropertyAction;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TextMessage;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.ScriptMaintainanceMultiplePageEditView;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.detail.action.ActionDetailPageView;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.detail.fileproperty.FilePropertyDetailPageView;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.detail.testscript.TestScriptDetailPageView;
import org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.model.ScriptMaintainanceMultiplePageEditViewModel;

import org.nabucco.testautomation.facade.datatype.property.BooleanProperty;
import org.nabucco.testautomation.facade.datatype.property.DateProperty;
import org.nabucco.testautomation.facade.datatype.property.DoubleProperty;
import org.nabucco.testautomation.facade.datatype.property.FileProperty;
import org.nabucco.testautomation.facade.datatype.property.IntegerProperty;
import org.nabucco.testautomation.facade.datatype.property.LongProperty;
import org.nabucco.testautomation.facade.datatype.property.PropertyList;
import org.nabucco.testautomation.facade.datatype.property.SqlProperty;
import org.nabucco.testautomation.facade.datatype.property.StringProperty;
import org.nabucco.testautomation.facade.datatype.property.XPathProperty;
import org.nabucco.testautomation.facade.datatype.property.XmlProperty;
import org.nabucco.testautomation.ui.rcp.multipage.detail.TestautomationDetailPageView;

/**
 * ScriptMaintainanceMasterDetailBlock
 * 
 * @author Markus Jorroch, PRODYNA AG
 */
public class ScriptMaintainanceMasterDetailBlock extends
        MasterDetailBlock<ScriptMaintainanceMultiplePageEditViewModel> implements
        Layoutable<ScriptMaintainanceMultiplePageEditViewModel> {

    public static String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.maintainance.masterdetails.ScriptMaintainanceMasterDetailBlock";

    /**
     * Creates a new {@link ScriptMaintainanceMasterDetailBlock} instance.
     * 
     * @param model
     *            the model
     * @param messageManager
     *            the message manager
     * @param view
     *            the multi page edit view
     */
    public ScriptMaintainanceMasterDetailBlock(ScriptMaintainanceMultiplePageEditViewModel model,
            NabuccoMessageManager messageManager, ScriptMaintainanceMultiplePageEditView view) {
        super(messageManager, view, model);
    }

    @Override
    protected void registerPages(DetailsPart arg0) {
    	Map<Class<? extends Datatype>, Set<String>> typeToInvisiblePropertiesMap = this.getTypeToInvisiblePropertiesMap();
        Set<String> invisibleProperties;
        Set<String> readOnlyProperties;

        invisibleProperties = new HashSet<String>();
        readOnlyProperties = new HashSet<String>();

        /*
         * TestScript
         */
    	invisibleProperties.add("id");
    	invisibleProperties.add("version");
    	readOnlyProperties.add("owner");
        readOnlyProperties.add("type");
        readOnlyProperties.add("identificationKey");
        
        detailsPart.registerPage(TestScript.class, new TestScriptDetailPageView(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, ID, ID  + "TestScript",
                invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(TestScript.class, invisibleProperties);
        
        /*
         * Embedded TestScript
         */
        invisibleProperties = new HashSet<String>();
        readOnlyProperties = new HashSet<String>();
        invisibleProperties.add("id");
    	invisibleProperties.add("version");
    	invisibleProperties.add("identificationKey");
        readOnlyProperties.add("type");
        readOnlyProperties.add("owner");
        readOnlyProperties.add("folder");
        readOnlyProperties.add("description");
        readOnlyProperties.add("name");
        readOnlyProperties.add("testScriptKey");
        
        detailsPart.registerPage(EmbeddedTestScript.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, ID, ID  + "EmbeddedTestScript",
                invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(EmbeddedTestScript.class, invisibleProperties);
        
        /*
         * Action
         */
        invisibleProperties = new HashSet<String>();
        readOnlyProperties = new HashSet<String>();
        readOnlyProperties.add("type");
        readOnlyProperties.add("identificationKey");
        invisibleProperties.add("id");
        invisibleProperties.add("version");
        invisibleProperties.add("owner");
        
        detailsPart.registerPage(Action.class, new ActionDetailPageView(this, getManagedForm(),
                getManagedFormViewPart(), nabuccoMessageManager, ID + ".Action", ID + "Action",
                invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(Action.class, invisibleProperties);
        
        /*
         * TestScriptElements
         */        
        invisibleProperties = new HashSet<String>();
        readOnlyProperties = new HashSet<String>();
        readOnlyProperties.add("type");
        invisibleProperties.add("owner");
        invisibleProperties.add("id");
        invisibleProperties.add("version");
        invisibleProperties.add("identificationKey");
        
        detailsPart.registerPage(Assertion.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this, getManagedForm(),
                getManagedFormViewPart(), nabuccoMessageManager, ID, ID + "Assertion",
                invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(Assertion.class, invisibleProperties);
        
        detailsPart.registerPage(BreakLoop.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this, getManagedForm(),
                getManagedFormViewPart(), nabuccoMessageManager, ID, ID + "BreakLoop",
                invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(BreakLoop.class, invisibleProperties);
        
        detailsPart.registerPage(Execution.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this, getManagedForm(),
                getManagedFormViewPart(), nabuccoMessageManager, ID, ID + "Execution",
                invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(Execution.class, invisibleProperties);
        
        detailsPart.registerPage(Foreach.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this, getManagedForm(),
                getManagedFormViewPart(), nabuccoMessageManager, ID, ID + "Foreach",
                invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(Foreach.class, invisibleProperties);
        
        detailsPart.registerPage(Lock.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this, getManagedForm(),
                getManagedFormViewPart(), nabuccoMessageManager, ID, ID + "Lock",
                invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(Lock.class, invisibleProperties);
        
        detailsPart.registerPage(Logger.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this, getManagedForm(),
                getManagedFormViewPart(), nabuccoMessageManager, ID, ID + "Logger",
                invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(Logger.class, invisibleProperties);
        
        detailsPart.registerPage(Loop.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this, getManagedForm(),
                getManagedFormViewPart(), nabuccoMessageManager, ID, ID + "Loop",
                invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(Loop.class, invisibleProperties);
        
        detailsPart.registerPage(PropertyAction.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, ID, ID
                        + "PropertyAction", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(PropertyAction.class, invisibleProperties);
        
        detailsPart.registerPage(TextMessage.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, ID, ID
                        + "TextMessage", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(TextMessage.class, invisibleProperties);
        
        invisibleProperties.add("isBreakCondition");
        
        readOnlyProperties.add("operator");
        detailsPart.registerPage(Condition.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this, getManagedForm(),
        		getManagedFormViewPart(), nabuccoMessageManager, ID, ID + "Condition",
        		invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(Condition.class, invisibleProperties);
        
        /*
         * Property
         */
        String propertyId = ID + ".Property";
        invisibleProperties = new HashSet<String>();
        readOnlyProperties = new HashSet<String>();
        
        invisibleProperties.add("owner");
        invisibleProperties.add("identificationKey");
    	invisibleProperties.add("id");
    	invisibleProperties.add("version");
    	invisibleProperties.add("filename");
        readOnlyProperties.add("type");
        
        detailsPart.registerPage(StringProperty.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, propertyId, ID
                        + "StringProperty", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(StringProperty.class, invisibleProperties);
        
        detailsPart.registerPage(BooleanProperty.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, propertyId, ID
                        + "BooleanProperty", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(BooleanProperty.class, invisibleProperties);
        
        detailsPart.registerPage(DateProperty.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, propertyId, ID
                        + "DateProperty", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(DateProperty.class, invisibleProperties);
        
        detailsPart.registerPage(DoubleProperty.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, propertyId, ID
                        + "DoubleProperty", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(DoubleProperty.class, invisibleProperties);
        
        detailsPart.registerPage(IntegerProperty.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, propertyId, ID
                        + "IntegerProperty", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(IntegerProperty.class, invisibleProperties);
        
        detailsPart.registerPage(LongProperty.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, propertyId, ID
                        + "LongProperty", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(LongProperty.class, invisibleProperties);
        
        detailsPart.registerPage(XmlProperty.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, propertyId, ID
                        + "XmlProperty", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(XmlProperty.class, invisibleProperties);
        
        detailsPart.registerPage(XPathProperty.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, propertyId, ID
                        + "XPathProperty", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(XPathProperty.class, invisibleProperties);
        
        detailsPart.registerPage(SqlProperty.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, propertyId, ID
                        + "SqlProperty", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(SqlProperty.class, invisibleProperties);
        
        detailsPart.registerPage(FileProperty.class, new FilePropertyDetailPageView(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, propertyId, ID
                        + "FileProperty", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(FileProperty.class, invisibleProperties);
        
        /*
         * PropertyList
         */
        invisibleProperties = new HashSet<String>();
        readOnlyProperties = new HashSet<String>();
        
        readOnlyProperties.add("owner");
        readOnlyProperties.add("type");
        readOnlyProperties.add("identificationKey");
    	invisibleProperties.add("id");
    	invisibleProperties.add("version");
        invisibleProperties.add("reused");
        invisibleProperties.add("usageType");
        
        detailsPart.registerPage(PropertyList.class, new TestautomationDetailPageView<ScriptMaintainanceMultiplePageEditViewModel>(this,
                getManagedForm(), getManagedFormViewPart(), nabuccoMessageManager, propertyId, ID
                        + "PropertyList", invisibleProperties, readOnlyProperties));
        typeToInvisiblePropertiesMap.put(PropertyList.class, invisibleProperties);
    }

    @Override
    protected MasterDetailBlockLayouter<ScriptMaintainanceMultiplePageEditViewModel> getLayouter() {
        return new ScriptMaintainanceMasterDetailBlockLayouter();
    }

}
