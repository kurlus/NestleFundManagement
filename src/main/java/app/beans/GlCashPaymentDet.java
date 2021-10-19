package app.beans;

import java.math.BigDecimal;

public class GlCashPaymentDet 
{
	
	public int getGlLineNo() 
	{
		return glLineNo;
	}
	public void setGlLineNo(int glLineNo) 
	{
		this.glLineNo = glLineNo;
	}
	public String getGlMFCode() 
	{
		return glMFCode;
	}
	public void setGlMFCode(String glMFCode) 
	{
		this.glMFCode = glMFCode;
	}
	public String getGlSLType() 
	{
		return glSLType;
	}
	public void setGlSLType(String glSLType) 
	{
		this.glSLType = glSLType;
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
	public String getEmployeeID() 
	{
		return employeeID;
	}
	public void setEmployeeID(String employeeID) 
	{
		this.employeeID = employeeID;
	}
	
	public String getExceptionMsgString() 
	{
		return exceptionMsgString;
	}
	public void setExceptionMsgString(String exceptionMsgString)
	{
		this.exceptionMsgString = exceptionMsgString;
	}
	
	
	public BigDecimal getDebitAmount()
	{
		return debitAmount;
	}
	public void setDebitAmount(BigDecimal debitAmount) 
	{
		this.debitAmount = debitAmount;
	}
	public BigDecimal getCreditAmount() 
	{
		return creditAmount;
	}
	public void setCreditAmount(BigDecimal creditAmount) 
	{
		this.creditAmount = creditAmount;
	}


	public String getDMLType()
	{
		return DMLType;
	}

	public void setDMLType(String dMLType)
	{
		DMLType = dMLType;
	}

	private int glLineNo;
	private String DMLType;
	private String glMFCode;
	private String glSLType;
	private long glVoucherNo;
	private String narration;
	private String employeeID;
	private BigDecimal debitAmount;
	private BigDecimal creditAmount;
	private String exceptionMsgString;
}
