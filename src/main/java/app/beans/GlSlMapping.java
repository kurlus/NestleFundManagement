package app.beans;

import java.io.Serializable;

public class GlSlMapping implements Serializable
{
	private static final long serialVersionUID = -2632905267215471400L;
	
	public GlSlMapping()
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
	
	public int getPost() 
	{
		return post;
	}
	
	public void setPost(int post) 
	{
		this.post = post;
	}
	
	public long getGlSlID()
	{
		return glSlID;
	}
	
	public void setGlSlID(long glSlID) 
	{
		this.glSlID = glSlID;
	}
	
	public String getGlMfCode() 
	{
		return glMfCode;
	}
	
	public void setGlMfCode(String glMfCode)
	{
		this.glMfCode = glMfCode;
	}
	
	public String getGlSlType() 
	{
		return glSlType;
	}
	
	public void setGlSlType(String glSlType)
	{
		this.glSlType = glSlType;
	}
	
	public String getEmployeeID() 
	{
		return employeeID;
	}
	
	public void setEmployeeID(String employeeID) 
	{
		this.employeeID = employeeID;
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
	private int post =0;
	private long glSlID;
	private String glMfCode;
	private String glSlType;
	private String employeeID;
	private String exceptionMsgString;
}
