package app.beans;

import java.io.Serializable;

public class GlFamilyType implements Serializable
{
	public GlFamilyType()
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
	
	public String getGlFamilyID() 
	{
		return glFamilyID;
	}
	
	public void setGlFamilyID(String glFamilyID) 
	{
		this.glFamilyID = glFamilyID;
	}
	
	public String getGlFamilyDesc() 
	{
		return glFamilyDesc;
	}
	
	public void setGlFamilyDesc(String glFamilyDesc) 
	{
		this.glFamilyDesc = glFamilyDesc;
	}
	
	public String getGlFamilyShort() 
	{
		return glFamilyShort;
	}
	
	public void setGlFamilyShort(String glFamilyShort) 
	{
		this.glFamilyShort = glFamilyShort;
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
	private String glFamilyID;
	private String glFamilyDesc;
	private String glFamilyShort;
	private String exceptionMsgString;
	
	
}
