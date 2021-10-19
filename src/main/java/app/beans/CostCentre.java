package app.beans;

import java.io.Serializable;

public class CostCentre implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -85282809840211798L;
	
	public CostCentre()
	{
		
	}

	public String getCostCentreID() 
	{
		return costCentreID;
	}

	public void setCostCentreID(String costCentreID) 
	{
		this.costCentreID = costCentreID;
	}

	public String getCostCentreName() 
	{
		return costCentreName;
	}

	public void setCostCentreName(String costCentreName) 
	{
		this.costCentreName = costCentreName;
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

	private int post = 0;
	private long logID;
	private String shortName;
	private String costCentreID;
	private String costCentreName;
	private String exceptionMsgString;

}
