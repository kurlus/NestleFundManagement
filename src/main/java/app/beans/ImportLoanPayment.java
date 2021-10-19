package app.beans;

import java.io.Serializable;
import java.util.Date;

public class ImportLoanPayment implements Serializable
{
	public long getBatchID() 
	{
		return batchID;
	}
	
	public void setBatchID(long batchID) 
	{
		this.batchID = batchID;
	}
	
	public long getRecordID() 
	{
		return recordID;
	}
	
	public void setRecordID(long recordID) 
	{
		this.recordID = recordID;
	}
	
	public int getFlagImport() 
	{
		return flagImport;
	}
	
	public void setFlagImport(int flagImport) 
	{
		this.flagImport = flagImport;
	}
	
	public String getFailReason() 
	{
		return failReason;
	}
	
	public void setFailReason(String failReason) 
	{
		this.failReason = failReason;
	}
	
	public String getExceptionMsgString() 
	{
		return exceptionMsgString;
	}
	
	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}
	
	public long getLoanID() 
	{
		return loanID;
	}

	public void setLoanID(long loanID) 
	{
		this.loanID = loanID;
	}

	public double getZakat() 
	{
		return zakat;
	}

	public void setZakat(double zakat) 
	{
		this.zakat = zakat;
	}

	public String getBankShort() 
	{
		return bankShort;
	}

	public void setBankShort(String bankShort) 
	{
		this.bankShort = bankShort;
	}

	public Date getPaymentDate() 
	{		
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) 
	{
		this.paymentDate = paymentDate;
	}

	public long getAdjustLoanID() 
	{
		return adjustLoanID;
	}

	public void setAdjustLoanID(long adjustLoanID) 
	{
		this.adjustLoanID = adjustLoanID;
	}

	public long getLoanPaymentID() 
	{
		return loanPaymentID;
	}

	public void setLoanPaymentID(long loanPaymentID) 
	{
		this.loanPaymentID = loanPaymentID;
	}

	public String getBranchShort() 
	{
		return branchShort;
	}

	public void setBranchShort(String branchShort) 
	{
		this.branchShort = branchShort;
	}

	public double getPaymentAmount() 
	{
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) 
	{
		this.paymentAmount = paymentAmount;
	}

	public String getLoanAccountNo() 
	{
		return loanAccountNo;
	}

	public void setLoanAccountNo(String loanAccountNo) 
	{
		this.loanAccountNo = loanAccountNo;
	}


	public String getIban() 
	{
		return iban;
	}

	public void setIban(String iban) 
	{
		this.iban = iban;
	}



	private String iban;
	private long loanID;//
	private long batchID;//
	private double zakat;//
	private int flagImport;//
	private long  recordID;//
	private String bankShort;//
	private Date paymentDate;//
	private long adjustLoanID;//
	private String failReason;//
	private long loanPaymentID;//
	private String branchShort;//
	private double paymentAmount;//
	private String loanAccountNo;//
	private String exceptionMsgString;

}
