package app.beans;

import java.io.Serializable;

public class WfEventAction implements Serializable
{
	private static final long serialVersionUID = -7201257530697192727L;


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
	
	public String getActionName() 
	{
		return actionName;
	}
	
	public void setActionName(String actionName) 
	{
		this.actionName = actionName;
	}

	public String getActionDescription() 
	{
		return actionDescription;
	}

	public void setActionDescription(String actionDescription) 
	{
		this.actionDescription = actionDescription;
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
	private String actionName;
	private String actionDescription;
	private String exceptionMsgString;
}
