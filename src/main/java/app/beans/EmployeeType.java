package app.beans;

import java.io.Serializable;

public class EmployeeType implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmployeeType()
	{
		
	}
	
	public int getEmployeeTypeId()
	{
		return employeeTypeId;
	}

	public void setEmployeeTypeId(int employeeTypeId)
	{
		this.employeeTypeId = employeeTypeId;
	}

	
	public String getEmployeeTypeDescription() 
	{
		return employeeTypeDescription;
	}


	public void setEmployeeTypeDescription(String employeeTypeDescription)
	{
		this.employeeTypeDescription = employeeTypeDescription;
	}




	public long getLogID() 
	{
		return logID;
	}

	public void setLogID(long logId) 
	{
		this.logID = logId;
	}

	public int getPost() 
	{
		return post;
	}

	public void setPost(int post) 
	{
		this.post = post;
	}

	public String getShortName() 
	{
		return shortName;
	}
	
	public void setShortName(String shortName) 
	{
		this.shortName = shortName;
	}
	
	public String getExceptionMsgString() 
	{
		return this.exceptionMsgString;
	}

	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}
	
	private int post = 0;
	private long logID;
	private String shortName;
	private int employeeTypeId;
	private String employeeTypeDescription;
	private String exceptionMsgString;
	
}
