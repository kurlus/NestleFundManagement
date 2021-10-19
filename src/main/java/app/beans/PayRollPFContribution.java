package app.beans;

import java.io.Serializable;

public class PayRollPFContribution implements Serializable
{
	private static final long serialVersionUID = -2505198061917906132L;
	
	public PayRollPFContribution()
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
	public double getSelfPfContribution() 
	{
		return selfPfContribution;
	}
	public void setSelfPfContribution(double selfPfContribution)
	{
		this.selfPfContribution = selfPfContribution;
	}
	public double getCompanyPfContribution() 
	{
		return companyPfContribution;
	}
	public void setCompanyPfContribution(double companyPfContribution) 
	{
		this.companyPfContribution = companyPfContribution;
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
	double selfPfContribution;
	double companyPfContribution;
	private String exceptionMsgString;
}
