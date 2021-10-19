package app.beans;

import java.io.Serializable;

public class Bank implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6619722937301483595L;

	public Bank()
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
	
	public String getBankCode() 
	{
		return bankCode;
	}
	
	public void setBankCode(String bankCode)
	{
		this.bankCode = bankCode;
	}
	
	public String getBankShort() 
	{
		return bankShort;
	}
	
	public void setBankShort(String bankShort) 
	{
		this.bankShort = bankShort;
	}
	
	public String getBankDescription() 
	{
		return bankDescription;
	}
	
	public void setBankDescription(String bankDescription) 
	{
		this.bankDescription = bankDescription;
	}
	
	public String getShortDescription() 
	{
		return shortDescription;
	}
	
	public void setShortDescription(String shortDescription) 
	{
		this.shortDescription = shortDescription;
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
	private String bankCode;
	private String bankShort;
	private String bankDescription;
	private String shortDescription;
	private String exceptionMsgString;
	
}
