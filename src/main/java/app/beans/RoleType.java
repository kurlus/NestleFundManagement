package app.beans;

import java.io.Serializable;

public class RoleType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -162622250699353289L;

	public RoleType()
	{
		// TODO Auto-generated constructor stub
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

	public String getShortName()
	{
		return shortName;
	}

	public void setShortName(String shortName)
	{
		this.shortName = shortName;
	}

	public String getRoleTypeID()
	{
		return roleTypeID;
	}

	public void setRoleTypeID(String roleTypeID)
	{
		this.roleTypeID = roleTypeID;
	}

	public String getExceptionMsgString()
	{
		return exceptionMsgString;
	}

	public void setExceptionMsgString(String exceptionMsgString)
	{
		this.exceptionMsgString = exceptionMsgString;
	}

	public String getRoleTypeDescription()
	{
		return roleTypeDescription;
	}

	public void setRoleTypeDescription(String roleTypeDescription)
	{
		this.roleTypeDescription = roleTypeDescription;
	}

	private int post;
	private long logID;
	private String shortName;
	private String roleTypeID;
	private String exceptionMsgString;
	private String roleTypeDescription;
}
