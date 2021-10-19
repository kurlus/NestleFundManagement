package app.beans;

import java.io.Serializable;

public class PayRollPNContribution  implements Serializable
{
	private static final long serialVersionUID = -2505198061917906132L;
	
	public PayRollPNContribution()
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


	public double getSelfPnContribution() 
	{
		return selfPnContribution;
	}
	public void setSelfPnContribution(double selfPnContribution) 
	{
		this.selfPnContribution = selfPnContribution;
	}
	public double getCompanyPnContribution()
	{
		return companyPnContribution;
	}
	public void setCompanyPnContribution(double companyPnContribution) 
	{
		this.companyPnContribution = companyPnContribution;
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
	double selfPnContribution;
	double companyPnContribution;
	private String exceptionMsgString;
}
