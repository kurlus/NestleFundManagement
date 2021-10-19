package app.beans;

import java.io.Serializable;

public class MemberType implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MemberType()
	{
		
	}
	
	
	public int getMemberTypeId() 
	{
		return memberTypeId;
	}

	public void setMemberTypeId(int memberTypeId) 
	{
		this.memberTypeId = memberTypeId;
	}

	public String getTypeDescription() 
	{
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) 
	{
		this.typeDescription = typeDescription;
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
	private int memberTypeId;
	private String typeDescription;
	private String exceptionMsgString;
	
}
