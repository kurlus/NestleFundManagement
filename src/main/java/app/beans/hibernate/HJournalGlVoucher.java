package app.beans.hibernate;

import java.io.Serializable;
import java.util.Set;
import java.util.Date;

public class HJournalGlVoucher implements Serializable
{
	private static final long serialVersionUID = -2476099901435464446L;
	
	public HJournalGlVoucher()
	{
		
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
	
	public int getGlFormNo() 
	{
		return glFormNo;
	}
	
	public void setGlFormNo(int glFormNo)
	{
		this.glFormNo = glFormNo;
	}
	
	public long getPeriodID() 
	{
		return periodID;
	}
	
	public void setPeriodID(long periodID) 
	{
		this.periodID = periodID;
	}
	
	public String getFundShort() 
	{
		return fundShort;
	}
	
	public void setFundShort(String fundShort) 
	{
		this.fundShort = fundShort;
	}
	
	public Date getGlVoucherDate() 
	{
		return glVoucherDate;
	}
	
	public void setGlVoucherDate(Date glVoucherDate) 
	{
		this.glVoucherDate = glVoucherDate;
	}
	
	public String getBookTypeShort() 
	{
		return bookTypeShort;
	}
	
	public void setBookTypeShort(String bookTypeShort) 
	{
		this.bookTypeShort = bookTypeShort;
	}
	public String getExceptionMsgString() 
	{
		return exceptionMsgString;
	}
	
	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}
	

	public Set<HJournalDet> getGlJournalDet() 
	{
		return glJournalDet;
	}

	public void setGlJournalDet(Set<HJournalDet> glJournalDet) 
	{
		this.glJournalDet = glJournalDet;
	}

	public long getGlVoucherNo() 
	{
		return glVoucherNo;
	}

	public void setGlVoucherNo(long glVoucherNo) 
	{
		this.glVoucherNo = glVoucherNo;
	}
	
	public String getNarration() 
	{
		return narration;
	}

	public void setNarration(String narration) 
	{
		this.narration = narration;
	}

	public int getFlagTreasury() 
	{
		return flagTreasury;
	}

	public void setFlagTreasury(int flagTreasury) 
	{
		this.flagTreasury = flagTreasury;
	}	

	public String getRemarksTreasury() 
	{
		return remarksTreasury;
	}

	public void setRemarksTreasury(String remarksTreasury) 
	{
		this.remarksTreasury = remarksTreasury;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public int getMonth()
	{
		return month;
	}

	public void setMonth(int month)
	{
		this.month = month;
	}

	public int getFinancialID()
	{
		return financialID;
	}

	public void setFinancialID(int financialID)
	{
		this.financialID = financialID;
	}


	public String getFormType()
	{
		return formType;
	}

	public void setFormType(String formType)
	{
		this.formType = formType;
	}

	private String formType;
	private int post;
	private int year;
	private int month;
	private int financialID;
	private long logID;
	private int glFormNo;
	private long periodID;
	private String narration;
	private int flagTreasury;
	private String fundShort;
	private long glVoucherNo;
	private Date glVoucherDate;
	private String bookTypeShort;
	private String remarksTreasury;	
	private String exceptionMsgString;
	private Set<HJournalDet> glJournalDet;
	
}
