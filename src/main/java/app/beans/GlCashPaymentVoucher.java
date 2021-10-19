package app.beans;

import java.util.Set;
import java.util.Date;
import java.io.Serializable;

public class GlCashPaymentVoucher implements Serializable
{
	private static final long serialVersionUID = 2527571683213896040L;
	
	public GlCashPaymentVoucher()
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
	public long getPeriodID() {
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
	public long getGlVoucherNo() 
	{
		return glVoucherNo;
	}
	public void setGlVoucherNo(long glVoucherNo) 
	{
		this.glVoucherNo = glVoucherNo;
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

	public Set<GlCashPaymentDet> getGlCashPaymentDet() 
	{
		return glCashPaymentDet;
	}

	public void setGlCashPaymentDet(Set<GlCashPaymentDet> glCashPaymentDet) 
	{
		this.glCashPaymentDet = glCashPaymentDet;
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
	
	private int post;
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
	private Set<GlCashPaymentDet> glCashPaymentDet;
}
