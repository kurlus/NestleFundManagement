package app.beans;

import java.io.Serializable;

public class GlBranch  implements Serializable
{
	private static final long serialVersionUID = -7241811100440910624L;

	public GlBranch()
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
	
	public String getBranchCode() 
	{
		return branchCode;
	}
	
	public void setBranchCode(String branchCode)
	{
		this.branchCode = branchCode;
	}
	
	public String getBranchShort() 
	{
		return branchShort;
	}
	
	public void setBranchShort(String branchShort) 
	{
		this.branchShort = branchShort;
	}
	
	public String getShortDescription() 
	{
		return shortDescription;
	}
	
	public void setShortDescription(String shortDescription) 
	{
		this.shortDescription = shortDescription;
	}
	
	public String getBranchDescription() 
	{
		return branchDescription;
	}
	
	public void setBranchDescription(String branchDescription)
	{
		this.branchDescription = branchDescription;
	}

	public String getExceptionMsgString()
	{
		return exceptionMsgString;
	}

	public void setExceptionMsgString(String exceptionMsgString)
	{
		this.exceptionMsgString = exceptionMsgString;
	}



	private int post;
	private long logID;
	private String branchCode;
	private String branchShort;
	private String shortDescription;
	private String branchDescription;
	private String exceptionMsgString;
	
}
