package app.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class GlJournalDet implements Serializable
{
	private static final long serialVersionUID = -2786577461450013216L;
	
	public GlJournalDet()
	{
		
	}
	
	public String getExceptionMsgString() 
	{
		return exceptionMsgString;
	}
	
	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}

	
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

	public String getNarration() 
	{
		return narration;
	}

	public void setNarration(String narration)
	{
		this.narration = narration;
	}

	public long getGlVoucherNo() 
	{
		return glVoucherNo;
	}

	public void setGlVoucherNo(long glVoucherNo)
	{
		this.glVoucherNo = glVoucherNo;
	}

	public String getEmployeeID() 
	{
		return employeeID;
	}

	public void setEmployeeID(String employeeID) 
	{
		this.employeeID = employeeID;
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
	
	public String getChequeNo() 
	{
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) 
	{
		this.chequeNo = chequeNo;
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
	private String chequeNo;
	private String narration;
	private long glVoucherNo;
	private String employeeID;
	private BigDecimal debitAmount;
	private BigDecimal creditAmount;
	private String exceptionMsgString;
	
}

