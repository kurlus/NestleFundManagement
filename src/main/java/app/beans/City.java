package app.beans;

import java.io.Serializable;

public class City implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -413106107235570906L;
	
	public City()
	{
		
	}
	
	public City(int cityId, String name, String shortName, long logId, int post	) 
	{
		
		this.cityID = cityId;
		this.cityName = name;
		this.cityShortName = shortName;
		this.logID = logId;
		this.post = post;
	}
	
	public int getCityID() 
	{
		return cityID;
	}
	
	public void setCityID(int cityId) 
	{
		this.cityID = cityId;
	}
	
	public String getCityName() 
	{
		return cityName;
	}
	
	public void setCityName(String cityName) 
	{
		this.cityName = cityName;
	}
	
	public String getCityShortName() 
	{
		return cityShortName;
	}
	
	public void setCityShortName(String cityShortName) 
	{
		this.cityShortName = cityShortName;
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
	private int cityID;
	private int post = 0;
	private String cityName;
	private String cityShortName;
	private String exceptionMsgString;
	
}
