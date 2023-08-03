package org.bcie.fenix.common.utils;

import javax.faces.context.FacesContext;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.xml.parser.v2.XMLElement;


import oracle.bpel.services.workflow.client.IWorkflowServiceClient;
import oracle.bpel.services.workflow.query.ITaskQueryService;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.util.WorkflowServiceUtil;
import oracle.bpel.services.workflow.verification.IWorkflowContext;



public class BPMUtils
{
    public BPMUtils()
    {
        super();
    }
    
    public static XMLElement getPayloadInformation() throws WorkflowException
    {
        Task task = getCurrentTask();
        
        return (XMLElement) task.getPayloadAsElement();
    }
    
    public static Task getCurrentTask() throws WorkflowException
    {
        FacesContext context = FacesContext.getCurrentInstance();
        String bmpwlctx = (String) context.getApplication().evaluateExpressionGet(context, "#{pageFlowScope.bpmWorklistContext}", String.class);
        String tskId = (String)context.getApplication().evaluateExpressionGet(context, "#{pageFlowScope.bpmWorklistTaskId}", String.class);
        
        IWorkflowServiceClient workflowSvcClient = WorkflowServiceUtil.getWorkflowServiceClient();
        ITaskQueryService wfQueryService = workflowSvcClient.getTaskQueryService();
        IWorkflowContext wfContext = wfQueryService.getWorkflowContext(bmpwlctx);
        
        return  wfQueryService.getTaskDetailsById(wfContext, tskId);
    }
}