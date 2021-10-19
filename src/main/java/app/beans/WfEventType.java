package app.beans;

import java.io.Serializable;

public class WfEventType implements Serializable
{
	private static final long serialVersionUID = 7707068951347254268L;
	
	public long getLogID() 
	{
		return logID;
	}
	
	public void setLogID(long logID) 
	{
		this.logID = logID;
	}
	
	public String getEventType() 
	{
		return eventType;
	}
	
	public void setEventType(String eventTypeID) 
	{
		this.eventType = eventTypeID;
	}
	
	public String getEventTypeName() 
	{
		return eventTypeName;
	}
	
	public void setEventTypeName(String eventTypeName) 
	{
		this.eventTypeName = eventTypeName;
	}
	
	public String getEventShortName() 
	{
		return eventShortName;
	}
	
	public void setEventShortName(String eventShortName) 
	{
		this.eventShortName = eventShortName;
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
	private String eventType;
	private String eventTypeName;
	private String eventShortName;
	private String exceptionMsgString;
}
