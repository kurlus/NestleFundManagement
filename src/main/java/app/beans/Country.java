package app.beans;

import java.io.Serializable;

public class Country implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4822883663580442368L;
	
	public Country()
	{
		
	}
	public int getCountryID() 
	{
		return countryID;
	}

	public void setCountryID(int countryID) 
	{
		this.countryID = countryID;
	}
	
	public String getCountry() 
	{
		return country;
	}
	
	public void setCountry(String name) 
	{
		this.country = name;
	}
	
	public String getShortName() 
	{
		return shortName;
	}
	
	public void setShortName(String shortName) 
	{
		this.shortName = shortName;
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
	
	public String getExceptionMsgString() 
	{
		return this.exceptionMsgString;
	}

	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}
	
	private long logID;
	private int post = 0;
	private int countryID;
	private String country;
	private String shortName;
	private String exceptionMsgString;
	
}
