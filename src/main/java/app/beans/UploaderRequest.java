package app.beans;

import java.io.Serializable;

public class UploaderRequest implements Serializable
{
	private static final long serialVersionUID = 7957427666928772437L;
	
	public UploaderRequest()
	{
		
	}
	
	public String getEmployeeID() 
	{
		return employeeID;
	}
	
	public void setEmployeeID(String employeeID) 
	{
		this.employeeID = employeeID;
	}
	
	public String getUploaderTypeID() 
	{
		return uploaderTypeID;
	}
	
	public void setUploaderTypeID(String uploaderTypeID) 
	{
		this.uploaderTypeID = uploaderTypeID;
	}
	
	public String getUploaderResponse()
	{		
		return uploaderResponse;
	}
	
	public void setUploaderResponse(String uploaderResponse) 
	{
		this.uploaderResponse = uploaderResponse;
	}
	
	public String getUploaderFilePathName() 
	{
		return uploaderFilePathName;
	}
	
	public void setUploaderFilePathName(String uploaderFilePathName) 
	{
		this.uploaderFilePathName = uploaderFilePathName;
	}
	
	private String employeeID;
	private String uploaderTypeID;
	private String uploaderResponse;
	private String uploaderFilePathName;
}
