package app.beans.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;

public class HBankPaymentDet implements Serializable
{
	private static final long serialVersionUID = 3433152395209641307L;
	
	public HBankPaymentDet()
	{
		
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
	
	public HBankPaymentGlVoucher getGlVoucher() 
	{
		return glVoucher;
	}

	public void setGlVoucher(HBankPaymentGlVoucher glVoucher) 
	{
		this.glVoucher = glVoucher;
	}
	
	public String getChequeNo() 
	{
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) 
	{
		this.chequeNo = chequeNo;
	}

	

	private int glLineNo;
	private String glMFCode;
	private String glSLType;
	private String chequeNo;
	private String narration;
	private String employeeID;
	private BigDecimal debitAmount;
	private BigDecimal creditAmount;
	private HBankPaymentGlVoucher glVoucher;
}
