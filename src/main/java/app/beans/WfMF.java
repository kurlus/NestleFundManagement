package app.beans;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class WfMF implements Serializable 
{
	private static final long serialVersionUID = -2139517361500475125L;
	
	public long getLogID() 
	{
		return logID;
	}
	
	public void setLogID(long logID) 
	{
		this.logID = logID;
	}
	
	public int getActionID() 
	{
		return actionID;
	}
	
	public void setActionID(int actionID) 
	{
		this.actionID = actionID;
	}
	
	public String getEventType() 
	{
		return eventType;
	}
	
	public void setEventType(String eventType) 
	{
		this.eventType = eventType;
	}
	
	public long getWorkFlowId() 
	{
		return workFlowId;
	}
	
	public void setWorkFlowId(long workFlowId) 
	{
		this.workFlowId = workFlowId;
	}
	
	public String getWorkFlowName() 
	{
		return workFlowName;
	}
	
	public void setWorkFlowName(String workFlowName) 
	{
		this.workFlowName = workFlowName;
	}
	
	public List<WfDetail> getWfTransitions() 
	{
		return wfTransitions;
	}

	public void setWfTransitions(List<WfDetail> wfTransitions) 
	{
		this.wfTransitions = wfTransitions;
	}
	
	public String getExceptionMsgString() 
	{
		return exceptionMsgString;
	}

	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}

	
	private long logID;
	private int actionID;
	private String eventType;	
	private String workFlowName;
	private long workFlowId = -1L;
	private String exceptionMsgString;
	private List<WfDetail> wfTransitions = new ArrayList<WfDetail>();
}
