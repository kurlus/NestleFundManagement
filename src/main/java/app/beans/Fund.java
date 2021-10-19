package app.beans;

import java.io.Serializable;

public class Fund implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3651736168994271469L;
	
	public Fund()
	{
		
	}
	
	
	public String getFundCode() 
	{
		return fundCode;
	}

	public void setFundCode(String fundCode) 
	{
		this.fundCode = fundCode;
	}

	public String getFundName() 
	{
		return fundName;
	}

	public void setFundName(String fundName) 
	{
		this.fundName = fundName;
	}

	public String getShortName() 
	{
		return shortName;
	}

	public void setShortName(String shortName) 
	{
		this.shortName = shortName;
	}

	public int getCategoryId() 
	{
		return categoryId;
	}

	public void setCategoryId(int categoryId) 
	{
		this.categoryId = categoryId;
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
	
	public String getExceptionMsgString() 
	{
		return this.exceptionMsgString;
	}

	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}

	private int post;
	private long logID;
	private int categoryId;
	private String fundCode;
	private String fundName;
	private String shortName;
	private String exceptionMsgString;
	
}
