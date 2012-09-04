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
package org.nabucco.testautomation.script.impl.service;

import java.util.List;

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.framework.support.scripting.facade.component.ScriptingComponentLocator;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.message.ScriptMsg;
import org.nabucco.framework.support.scripting.facade.service.resolve.ScriptResolveService;
import org.nabucco.testautomation.script.facade.component.ScriptComponentLocator;
import org.nabucco.testautomation.script.facade.datatype.dictionary.TestScript;
import org.nabucco.testautomation.script.facade.message.TestScriptListMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptMsg;
import org.nabucco.testautomation.script.facade.message.TestScriptSearchMsg;
import org.nabucco.testautomation.script.facade.service.maintain.MaintainScript;
import org.nabucco.testautomation.script.facade.service.search.SearchScript;

/**
 * ScriptSupport
 * 
 * @author Steffen Schmidt, PRODYNA AG
 */
public class ScriptSupport {

    private static ScriptSupport instance;

    private MaintainScript maintain;

    private SearchScript search;

    private ScriptResolveService resolve;

    private ScriptSupport() {
    }

    public static synchronized ScriptSupport getInstance() {

        if (instance == null) {
            instance = new ScriptSupport();
        }
        return instance;
    }

    public TestScript maintainTestScript(TestScript testScript, ServiceMessageContext ctx) throws MaintainException {

        if (this.maintain == null) {
            try {
                initMaintain();
            } catch (ServiceException e) {
                throw new MaintainException(e);
            } catch (ConnectionException e) {
                throw new MaintainException(e);
            }
        }

        ServiceRequest<TestScriptMsg> rq = new ServiceRequest<TestScriptMsg>(ctx);
        TestScriptMsg msg = new TestScriptMsg();
        msg.setTestScript(testScript);
        rq.setRequestMessage(msg);
        return maintain.maintainTestScript(rq).getResponseMessage().getTestScript();
    }

    public List<TestScript> searchTestScriptByFolder(Long folderId, ServiceMessageContext ctx) throws SearchException {

        if (this.search == null) {
            try {
                initSearch();
            } catch (ServiceException e) {
                throw new SearchException(e);
            } catch (ConnectionException e) {
                throw new SearchException(e);
            }
        }

        ServiceRequest<TestScriptSearchMsg> rq = new ServiceRequest<TestScriptSearchMsg>(ctx);
        TestScriptSearchMsg msg = new TestScriptSearchMsg();
        rq.setRequestMessage(msg);
        msg.setFolderId(new Identifier(folderId));
        TestScriptListMsg rs = this.search.searchTestScript(rq).getResponseMessage();
        return rs.getTestScriptList();
    }

    public Script resolveScript(long id, ServiceMessageContext ctx) throws ResolveException {

        if (this.resolve == null) {
            try {
                initResolve();
            } catch (ServiceException e) {
                throw new ResolveException(e);
            } catch (ConnectionException e) {
                throw new ResolveException(e);
            }
        }
        ServiceRequest<ScriptMsg> rq = new ServiceRequest<ScriptMsg>(ctx);
        ScriptMsg msg = new ScriptMsg();
        Script script = new Script();
        script.setId(id);
        msg.setScript(script);
        rq.setRequestMessage(msg);
        ScriptMsg rs = resolve.resolveScript(rq).getResponseMessage();
        return rs.getScript();
    }

    private void initMaintain() throws ServiceException, ConnectionException {
        this.maintain = ScriptComponentLocator.getInstance().getComponent().getMaintainScript();
    }

    private void initSearch() throws ServiceException, ConnectionException {
        this.search = ScriptComponentLocator.getInstance().getComponent().getSearchScript();
    }

    private void initResolve() throws ServiceException, ConnectionException {
        this.resolve = ScriptingComponentLocator.getInstance().getComponent().getScriptResolveService();
    }

}
