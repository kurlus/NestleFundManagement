package app.beans;

import java.io.Serializable;

public class GlCurrency implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2479968554660074743L;
	
	public GlCurrency() 
	{
	
	}

	public int getCurrencyID() 
	{
		return currencyID;
	}
	
	public void setCurrencyID(int currencyID) 
	{
		this.currencyID = currencyID;
	}
	
	public String getCurrencyCode() 
	{
		return currencyCode;
	}
	
	public void setCurrencyCode(String currencyCode) 
	{
		this.currencyCode = currencyCode;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public String getSymbol() 
	{
		return symbol;
	}
	
	public void setSymbol(String symbol) 
	{
		this.symbol = symbol;
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
	
	private int post;
	private long logID;
	private String symbol;
	private int currencyID;
	private String description;
	private String currencyCode;
	private String exceptionMsgString;
	
}
