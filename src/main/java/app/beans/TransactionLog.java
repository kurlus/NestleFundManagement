package app.beans;

import java.io.Serializable;
import java.util.Date;

public class TransactionLog implements Serializable
{
	
	private static final long serialVersionUID = 6139403030940318042L;
	
	public TransactionLog()
	{
		
	}
	
	public long getLogID() 
	{
		return logID;
	}
	
	public void setLogID(long logID) 
	{
		this.logID = logID;
	}
	
	public long getMenuID() 
	{
		return menuID;
	}
	
	public void setMenuID(long menuID) 
	{
		this.menuID = menuID;
	}
	
	public String getRemarks() 
	{
		return remarks;
	}
	
	public void setRemarks(String remarks) 
	{
		this.remarks = remarks;
	}
	
	public Date getServerDate() 
	{
		return serverDate;
	}
	
	public void setServerDate(Date serverDate) 
	{
		this.serverDate = serverDate;
	}
	
	public String getTerminalID() 
	{
		return terminalID;
	}
	
	public void setTerminalID(String terminalID) 
	{
		this.terminalID = terminalID;
	}
	
	public String getEmployeeID() 
	{
		return employeeID;
	}
	
	public void setEmployeeID(String employeeID) 
	{
		this.employeeID = employeeID;
	}
	
	public String getOperationID() 
	{
		return operationID;
	}
	
	public void setOperationID(String operationID) 
	{
		this.operationID = operationID;
	}
	
	private long logID;
	private long menuID;
	private String remarks;
	private Date serverDate;
	private String terminalID;
	private String employeeID;
	private String operationID;
	
}
