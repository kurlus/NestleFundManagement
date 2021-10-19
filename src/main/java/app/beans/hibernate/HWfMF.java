package app.beans.hibernate;

import java.util.Set;
import java.util.HashSet;
import java.io.Serializable;

public class HWfMF implements Serializable 
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
	
	public String getEventTypeId() 
	{
		return eventTypeId;
	}
	
	public void setEventTypeId(String eventTypeId) 
	{
		this.eventTypeId = eventTypeId;
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

	public Set<HWfDetail> gethWfDetail() 
	{
		return hWfDetail;
	}

	public void sethWfDetail(Set<HWfDetail> hWfDetail) 
	{
		this.hWfDetail = hWfDetail;
	}
	

	
	private long logID;
	private int actionID;
	private String eventTypeId;	
	private String workFlowName;
	private long workFlowId = -1L;
	private Set<HWfDetail> hWfDetail = new HashSet<HWfDetail>();
}
