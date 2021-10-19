package app.beans;

import java.io.Serializable;

public class WfDetail implements Serializable
{
	private static final long serialVersionUID = 2953868947481398959L;
	
	
	public int getWorkFlowSeq() 
	{
		return workFlowSeq;
	}
	
	public void setWorkFlowSeq(int workFlowSeq) 
	{
		this.workFlowSeq = workFlowSeq;
	}
	
	public long getWfDetailID() 
	{
		return wfDetailID;
	}
	
	public void setWfDetailID(long wfDetailID) 
	{
		this.wfDetailID = wfDetailID;
	}
	
	public long getWorkFlowId() 
	{
		return workFlowId;
	}
	
	public void setWorkFlowId(long workFlowId) 
	{
		this.workFlowId = workFlowId;
	}
	
	public int getIsMemberEmail() 
	{
		return isMemberEmail;
	}
	
	public void setIsMemberEmail(int isMemberEmail) 
	{
		this.isMemberEmail = isMemberEmail;
	}
	
	public String getToTransition() 
	{
		return toTransition;
	}
	
	public void setToTransition(String toTransition) 
	{
		this.toTransition = toTransition;
	}
	
	public String getFromTransition() 
	{
		return fromTransition;
	}
	
	public void setFromTransition(String fromTransition) 
	{
		this.fromTransition = fromTransition;
	}	

	public boolean isMemberEmail() 
	{
		return memberEmail;
	}

	public void setMemberEmail(boolean memberEmail) 
	{
		this.memberEmail = memberEmail;
	}


	
	
	private int workFlowSeq;
	private long wfDetailID;
	private long workFlowId;
	private int isMemberEmail;
	private boolean memberEmail;
	private String toTransition;
	private String fromTransition;
}
