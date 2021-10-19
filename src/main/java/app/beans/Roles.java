package app.beans;

import java.io.Serializable;

public class Roles implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6423995737213169885L;
	
	public Roles()
	{
		
	}
	
	public String getRoleId() 
	{
		return roleId;
	}
	
	public void setRoleId(String roleId) 
	{
		this.roleId = roleId;
	}
	
	public String getRoleDescription() 
	{
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) 
	{
		this.roleDescription = roleDescription;
	}

	public String getShortName() 
	{
		return shortName;
	}

	public void setShortName(String shortName) 
	{
		this.shortName = shortName;
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
	
	public String getExceptionMsgString() 
	{
		return this.exceptionMsgString;
	}

	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}
	
	public String getRoleTypeID()
	{
		return roleTypeID;
	}

	public void setRoleTypeID(String roleTypeID)
	{
		this.roleTypeID = roleTypeID;
	}

	private int post;
	private long logID;
	private String roleId;
	private String shortName;
	private String roleTypeID;
	private String roleDescription;
	private String exceptionMsgString;
	
}
