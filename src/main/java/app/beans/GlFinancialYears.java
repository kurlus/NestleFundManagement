package app.beans;

import java.util.Date;
import java.io.Serializable;

import utilities.AppConstants;

public class GlFinancialYears implements Serializable 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1175192831083841206L;
	/**
	 * 
	 */
	public GlFinancialYears()
	{
		
	}
	
	public GlFinancialYears(int yearId,int yearCode,String description,Date fromDate,Date toDate,int post,int isLocked)
	{
		this.yearId=yearId;
		this.yearCode=yearCode;
		this.description=description;
		this.fromDate=fromDate;
		this.toDate=toDate;
		this.posted=post;
		this.isLocked=isLocked;
	}
	
	public int getYearId() 
	{
		return yearId;
	}
	
	public void setYearId(int yearId) 
	{
		this.yearId = yearId;
	}
	
	public int getYearCode() 
	{
		return yearCode;
	}
	
	public void setYearCode(int yearCode) 
	{
		this.yearCode = yearCode;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public Date getFromDate() 
	{
		return fromDate;
	}

	public void setFromDate(Date fromDate) 
	{
		this.fromDate = fromDate;
	}

	public Date getToDate() 
	{
		return toDate;
	}

	public void setToDate(Date toDate) 
	{
		this.toDate = toDate;
	}

	public int isPosted() 
	{
		return posted;
	}
	
	public void setPosted(int post) 
	{
		this.posted = post;
	}
	
	public int getIsLocked() 
	{
		return isLocked;
	}
	
	public void setIsLocked(int isLocked) 
	{
		this.isLocked = isLocked;
	}
	
	public void reset()
	{
		this.yearId = AppConstants.zero;
		this.yearCode = AppConstants.zero;
		this.description = AppConstants.BLANK;
		this.fromDate = null;
		this.toDate = null;
		this.posted = AppConstants.zero;
		this.isLocked = AppConstants.zero;
	}

	public String getExceptionMsgString() 
	{
		return exceptionMsgString;
	}

	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}
	
	public int getPosted() 
	{
		return posted;
	}
	public long getLogId() 
	{
		return logId;
	}

	public void setLogId(long logId) 
	{
		this.logId = logId;
	}
	
	
	private int posted;
	private Date toDate;
	private int isLocked;
	private Date fromDate;
	private int yearId;
	private int yearCode;
	private String description;
	private String exceptionMsgString;
	private long logId;
	
}
