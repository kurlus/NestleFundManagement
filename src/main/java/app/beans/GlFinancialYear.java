package app.beans;

import java.util.Date;

import utilities.AppConstants;

public class GlFinancialYear 
{
	
	public GlFinancialYear()
	{
		
	}
	
	public GlFinancialYear(String yearId,String yearCode,String description,Date fromDate,Date toDate,int post,int isLocked)
	{
		this.yearId=yearId;
		this.yearCode=yearCode;
		this.description=description;
		this.fromDate=fromDate;
		this.toDate=toDate;
		this.posted=post;
		this.isLocked=isLocked;
	}
	
	public String getYearId() 
	{
		return yearId;
	}
	
	public void setYearId(String yearId) 
	{
		this.yearId = yearId;
	}
	
	public String getYearCode() 
	{
		return yearCode;
	}
	
	public void setYearCode(String yearCode) 
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
		this.yearId = AppConstants.BLANK;
		this.yearCode = AppConstants.BLANK;
		this.description = AppConstants.BLANK;
		this.fromDate = null;
		this.toDate = null;
		this.posted = AppConstants.zero;
		this.isLocked = AppConstants.zero;
	}
	
	private String yearId;
	private String yearCode;
	private String description;
	private Date fromDate;
	private Date toDate;
	private int posted;
	private int isLocked;
	
}
