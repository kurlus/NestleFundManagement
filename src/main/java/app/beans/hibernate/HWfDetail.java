package app.beans.hibernate;

import java.io.Serializable;

public class HWfDetail implements Serializable
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

	public HWfMF getWorkFlowMF() 
	{
		return workFlowMF;
	}

	public void setWorkFlowMF(HWfMF workFlowMF) 
	{
		this.workFlowMF = workFlowMF;
	}
	
	private int workFlowSeq;
	private long wfDetailID;
	private int isMemberEmail;
	private String toTransition;
	private String fromTransition;
	private HWfMF workFlowMF;
}
