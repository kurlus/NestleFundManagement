package app.beans;

import java.io.Serializable;
import java.util.Set;

public class PayRollLoan implements Serializable
{
	private static final long serialVersionUID = -2505198061917906132L;
	
	public PayRollLoan()
	{
		
	}
	
	public long getBatchId() 
	{
		return batchId;
	}
	public void setBatchId(long batchId)
	{
		this.batchId = batchId;
	}
	public long getRecordId() 
	{
		return recordId;
	}
	public void setRecordId(long recordId) 
	{
		this.recordId = recordId;
	}
	public long getPeriodId()
	{
		return periodId;
	}
	public void setPeriodId(long periodId)
	{
		this.periodId = periodId;
	}
	public String getEmployeeId()
	{
		return employeeId;
	}
	public void setEmployeeId(String employeeId) 
	{
		this.employeeId = employeeId;
	}
	public double getLoanInstallment()
	{
		return loanInstallment;
	}
	public void setLoanInstallment(double loanInstallment) 
	{
		this.loanInstallment = loanInstallment;
	}
	public double getPensionerPayment()
	{
		return pensionerPayment;
	}
	public void setPensionerPayment(double pensionerPayment) 
	{
		this.pensionerPayment = pensionerPayment;
	}
	public double getInterestInstallment() 
	{
		return interestInstallment;
	}
	public void setInterestInstallment(double interestInstallment) 
	{
		this.interestInstallment = interestInstallment;
	}
	public String getExceptionMsgString() 
	{
		return exceptionMsgString;
	}
	public void setExceptionMsgString(String exceptionMsgString)
	{
		this.exceptionMsgString = exceptionMsgString;
	}


	long batchId;
	long recordId;
	long periodId;
	String employeeId;
	double loanInstallment;
	double pensionerPayment;
	double interestInstallment;
	private String exceptionMsgString;
	
}
