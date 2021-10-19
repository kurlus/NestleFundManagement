package app.beans;

import java.io.Serializable;
import java.util.Date;

public class LoaderRun implements Serializable
{
	private static final long serialVersionUID = -7419581574756985309L;

	public LoaderRun()
	{
		
	}
	
	public int getPost() 
	{
		return post;
	}
	
	public void setPost(int post) 
	{
		this.post = post;
	}
	
	public long getLogID() 
	{
		return logID;
	}
	
	public void setLogID(long logID) 
	{
		this.logID = logID;
	}
	
	public long getLoaderID() 
	{
		return loaderID;
	}
	
	public void setLoaderID(long loaderID) 
	{
		this.loaderID = loaderID;
	}
	
	public String getSessionID() 
	{
		return sessionID;
	}
	
	public void setSessionID(String sessionID) 
	{
		this.sessionID = sessionID;
	}
	
	public long getBatchID() 
	{
		return batchID;
	}
	
	public void setBatchID(long batchID) 
	{
		this.batchID = batchID;
	}
	public String getEmployeeID() 
	{
		return employeeID;
	}
	
	public void setEmployeeID(String employeeID) 
	{
		this.employeeID = employeeID;
	}
	
	public Date getBatchDate()
	{
		return batchDate;
	}
	
	public void setBatchDate(Date batchDate) 
	{
		this.batchDate = batchDate;
	}
	
	public String getExceptionMsgString() 
	{
		return exceptionMsgString;
	}
	
	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}


	//Fields
	private int post = 0;
	private long logID;
	private long batchID;
	private long loaderID;
	private Date batchDate;
	private String sessionID;
	private String employeeID;
	private String exceptionMsgString;
}
