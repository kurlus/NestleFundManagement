package app.beans;

import java.io.Serializable;
import java.util.Date;

import utilities.AppConstants;

public class GlFinancialPeriod implements Serializable 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1372367431606723444L;
	public GlFinancialPeriod()
	{
	
	}
	
	public GlFinancialPeriod(int periodId, String periodCode, String periodDesc, Date fromDate2, Date toDate2, int post, int isLocked, int year)
	{
		this.periodId = periodId;
		this.periodShortName = periodCode;
		this.periodDesc = periodDesc;
		this.fromDate = fromDate2;
		this.toDate = toDate2;
		this.posted = post;
		this.isLocked = isLocked;
		this.financialYear = year;
	}
	
	
	public int getPeriodId() 
	{
		return periodId;
	}
	
	public void setPeriodId(int periodId) 
	{
		this.periodId = periodId;
	}
	
	public String getPeriodDesc() 
	{
		return periodDesc;
	}
	public void setPeriodDesc(String periodDesc) 
	{
		this.periodDesc = periodDesc;
	}
	
	public int getFinancialYear() 
	{
		return financialYear;
	}
	
	public void setFinancialYear(int financialYear) 
	{
		this.financialYear = financialYear;
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
	public String getPeriodShortName() 
	{
		return periodShortName;
	}

	public void setPeriodShortName(String periodShortName) 
	{
		this.periodShortName = periodShortName;
	}

	public String getPeriodType() 
	{
		return periodType;
	}

	public void setPeriodType(String periodType) 
	{
		this.periodType = periodType;
	}

	public void reset()
	{
		this.periodId = AppConstants.zero;
		this.periodShortName = AppConstants.BLANK;
		this.periodDesc = AppConstants.BLANK;
		this.financialYear = AppConstants.zero;
		this.fromDate = null;
		this.toDate = null;
		this.posted = AppConstants.zero;
		this.isLocked = AppConstants.zero;
	}
	
	private Date toDate;
	private Date fromDate;
	private int posted;
	private int periodId;
	private int isLocked;
	private String periodShortName;
	private String periodDesc;
	private String periodType;
	private String exceptionMsgString;
	private int financialYear;
	private long logId;
	
	
}
