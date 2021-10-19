package app.beans.hibernate;

import java.math.BigDecimal;


public class HCashReceiptDet 
{

	public int getGlLineNo() 
	{
		return glLineNo;
	}
	
	public void setGlLineNo(int glLineNo) 
	{
		this.glLineNo = glLineNo;
	}
	
	public String getGlMfCode() 
	{
		return glMfCode;
	}
	
	public void setGlMfCode(String glMfCode) 
	{
		this.glMfCode = glMfCode;
	}
	
	public String getGlSlType() 
	{
		return glSlType;
	}
	
	public void setGlSlType(String glSlType) 
	{
		this.glSlType = glSlType;
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

	public HCashReceiptGlVoucher getCashReceiptGlVoucher() 
	{
		return cashReceiptGlVoucher;
	}

	public void setCashReceiptGlVoucher(HCashReceiptGlVoucher cashReceiptGlVoucher) 
	{
		this.cashReceiptGlVoucher = cashReceiptGlVoucher;
	}



	private int glLineNo;
	private String glMfCode;
	private String glSlType;
	private String narration;
	private String employeeID;
	private BigDecimal debitAmount;
	private BigDecimal creditAmount;
	private HCashReceiptGlVoucher cashReceiptGlVoucher;
}
