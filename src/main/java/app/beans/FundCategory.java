package app.beans;

import java.io.Serializable;

public class FundCategory implements Serializable
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3917837632040594053L;
	
	public FundCategory()
	{
		
	}
	
	public int getFundCategoryID() 
	{
		return fundCategoryID;
	}


	public void setFundCategoryID(int fundCategoryID) 
	{
		this.fundCategoryID = fundCategoryID;
	}


	public String getDescription() 
	{
		return description;
	}


	public void setDescription(String description) 
	{
		this.description = description;
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


	public static long getSerialversionuid() 
	{
		return serialVersionUID;
	}
	
	private int fundCategoryID;
	private String description;
	private String shortName;
	private int post;
	private long logID;

}
