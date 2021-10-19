package app.beans;

import java.io.Serializable;

public class LoaderInfo implements Serializable
{
	
	private static final long serialVersionUID = 7594714132736425160L;
	
	public LoaderInfo()
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
	
	public long getLoaderID() 
	{
		return loaderID;
	}
	
	public void setLoaderID(long loaderID) 
	{
		this.loaderID = loaderID;
	}
	
	public String getShortName() 
	{
		return shortName;
	}
	
	public void setShortName(String shortName) 
	{
		this.shortName = shortName;
	}
	
	public String getLoaderTypeID() 
	{
		return loaderTypeID;
	}
	
	public void setLoaderTypeID(String loaderTypeID) 
	{
		this.loaderTypeID = loaderTypeID;
	}
	
	public String getLoaderDescription() 
	{
		return loaderDescription;
	}
	
	public void setLoaderDescription(String loaderDescription) 
	{
		this.loaderDescription = loaderDescription;
	}
	
	private long logID;
	private int post =0;
	private long loaderID;
	private String shortName;
	private String loaderTypeID;
	private String loaderDescription;
	
	
	
}
