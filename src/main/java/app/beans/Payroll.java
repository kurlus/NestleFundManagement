package app.beans;

import java.io.Serializable;
import java.util.Set;


public class Payroll implements Serializable
{
	private static final long serialVersionUID = -2505198061917906132L;
	
	public Payroll()
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
	public long getLogId()
	{
		return logId;
	}
	public void setLogId(long logId) 
	{
		this.logId = logId;
	}
	public long getBatchId() 
	{
		return batchId;
	}
	public void setBatchId(long batchId) 
	{
		this.batchId = batchId;
	}
	public long getPeriodId() 
	{
		return periodId;
	}
	public void setPeriodId(long periodId)
	{
		this.periodId = periodId;
	}
	public long getRecordId() 
	{
		return recordId;
	}
	public void setRecordId(long recordId) {
		
		this.recordId = recordId;
	}
	public String getEmployeeId()
	{
		return employeeId;
	}
	public void setEmployeeId(String employeeId) 
	{
		this.employeeId = employeeId;
	}

	public String getExceptionMsgString() 
	{
		return exceptionMsgString;
	}


	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}

	int post;
	long logId;
	long batchId;
	long periodId;
	long recordId;
	String employeeId;
	private String exceptionMsgString;
}
