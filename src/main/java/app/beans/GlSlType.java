package app.beans;

import java.io.Serializable;

public class GlSlType implements Serializable
{
	private static final long serialVersionUID = -2632905267215471400L;
	public GlSlType()
	{
		
	}
	
	public String getGlSlType() 
	{
		return glSlType;
	}

	public void setGlSlType(String glSlType) 
	{
		this.glSlType = glSlType;
	}

	public String getGlSlTypeName() 
	{
		return glSlTypeName;
	}
	
	public void setGlSlTypeName(String glSlTypeName) 
	{
		this.glSlTypeName = glSlTypeName;
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
		return exceptionMsgString;
	}
	
	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}
	
	
	private long logID;
	private int post =0;
	private String glSlType;
	private String glSlTypeName;
	private String exceptionMsgString;
}
