package app.beans;

import java.io.Serializable;

public class WfTransition implements Serializable
{

	private static final long serialVersionUID = 7270163860212512023L;

	public WfTransition()
	{
		
	}
	
	
	public long getLogID() 
	{
		return logID;
	}
	
	public void setLogID(long logID) 
	{
		this.logID = logID;
	}
	
	public String getExceptionMsg() 
	{
		return exceptionMsg;
	}
	
	public void setExceptionMsg(String exceptionMsg) 
	{
		this.exceptionMsg = exceptionMsg;
	}
	
	public String getTransitionCode() 
	{
		return transitionCode;
	}
	
	public void setTransitionCode(String transitionCode) 
	{
		this.transitionCode = transitionCode;
	}
	
	public String getTransitionName() 
	{
		return transitionName;
	}
	
	public void setTransitionName(String transitionName) 
	{
		this.transitionName = transitionName;
	}

	private long logID;
	private String exceptionMsg;
	private String transitionCode;
	private String transitionName;
	
	
}
