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
package org.nabucco.testautomation.script.ui.rcp.multipage.maintenance.wizard.find;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.layout.ImageProvider;
import org.nabucco.testautomation.property.facade.datatype.base.Property;
import org.nabucco.testautomation.script.facade.datatype.dictionary.base.TestScriptElement;
import org.nabucco.testautomation.script.facade.datatype.visitor.FindAndReplaceTestScriptVisitor;
import org.nabucco.testautomation.script.ui.rcp.images.ScriptImageRegistry;

/**
 * FindAndReplaceWizard
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class FindAndReplaceWizard extends Wizard implements INewWizard {

    public static final String ID = "org.nabucco.testautomation.script.ui.rcp.multipage.script.maintenance.wizard.FindAndReplaceWizard";

    private FindAndReplaceWizardPage findAndReplaceWizardPage;

    private TestScriptElement testScript;
    
    private Property property;

    private int matches = 0;

    public FindAndReplaceWizard(TestScriptElement testScript) {
        this.testScript = testScript;
    }
    
    public FindAndReplaceWizard(Property property) {
    	this.property = property;
    }

    public TestScriptElement getTestScript() {
        return this.testScript;
    }

    public int getMatches() {
        return this.matches;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        setWindowTitle(I18N.i18n(ID + ".WindowTitle"));
        setDefaultPageImageDescriptor(ImageProvider.createImageDescriptor(ScriptImageRegistry.ICON_FIND.getId()));
        setNeedsProgressMonitor(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPages() {
        findAndReplaceWizardPage = new FindAndReplaceWizardPage();
        addPage(findAndReplaceWizardPage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performFinish() {

        FindAndReplaceTestScriptVisitor visitor = new FindAndReplaceTestScriptVisitor(
                findAndReplaceWizardPage.getFind(), findAndReplaceWizardPage.getReplace(),
                findAndReplaceWizardPage.getFindInName(), findAndReplaceWizardPage.getFindInReferences(),
                findAndReplaceWizardPage.getFindInProperties(), findAndReplaceWizardPage.getFindInMessages(),
                findAndReplaceWizardPage.isRegex());

        if (this.testScript != null) {
            try {
                this.testScript.accept(visitor);
                this.matches = visitor.getMatches();
            } catch (VisitorException e) {
                Activator.getDefault().logError(e);
                return false;
            }
        } else if(this.property != null) {
        	try {
                this.property.accept(visitor);
                this.matches = visitor.getMatches();
            } catch (VisitorException e) {
                Activator.getDefault().logError(e);
                return false;
            }
        }
        return true;
    }
}
