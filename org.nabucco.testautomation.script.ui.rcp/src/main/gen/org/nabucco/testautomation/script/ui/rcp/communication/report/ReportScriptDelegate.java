/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.nabucco.testautomation.script.ui.rcp.communication.report;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.testautomation.script.facade.message.DashboardStatisticMsg;
import org.nabucco.testautomation.script.facade.service.report.ReportScript;

/**
 * ReportScriptDelegate<p/>Script reporting service<p/>
 *
 * @version 1.0
 * @author Steffen Schmidt, PRODYNA AG, 2011-12-22
 */
public class ReportScriptDelegate extends ServiceDelegateSupport {

    private ReportScript service;

    /**
     * Constructs a new ReportScriptDelegate instance.
     *
     * @param service the ReportScript.
     */
    public ReportScriptDelegate(ReportScript service) {
        super();
        this.service = service;
    }

    /**
     * Getter for the DashboardStatistic.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the EmptyServiceMessage.
     * @return the DashboardStatisticMsg.
     * @throws ClientException
     */
    public DashboardStatisticMsg getDashboardStatistic(EmptyServiceMessage message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<DashboardStatisticMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.getDashboardStatistic(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ReportScript.class, "getDashboardStatistic", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ReportScript.getDashboardStatistic");
    }
}
