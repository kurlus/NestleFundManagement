package app.beans;

import java.io.Serializable;

public class LoanType  implements Serializable 
{
	private static final long serialVersionUID = 1830289114831536924L;

	public LoanType() 
	{
	}

	public int getPosted() 
	{
		return posted;
	}

	public void setPosted(int posted) 
	{
		this.posted = posted;
	}

	public long getLogID() 
	{
		return logID;
	}

	public void setLogID(long logID) 
	{
		this.logID = logID;
	}

	public int getSalary() 
	{
		return salary;
	}

	public void setSalary(int salary) 
	{
		this.salary = salary;
	}

	public int getBalance() 
	{
		return balance;
	}

	public void setBalance(int balance) 
	{
		this.balance = balance;
	}

	public int getRefundable() 
	{
		return refundable;
	}

	public void setRefundable(int refundable) 
	{
		this.refundable = refundable;
	}

	public long getLoanTypeId() 
	{
		return loanTypeId;
	}

	public void setLoanTypeId(long loanTypeId) 
	{
		this.loanTypeId = loanTypeId;
	}

	public int getMinServiceMonths() 
	{
		return minServiceMonths;
	}

	public void setMinServiceMonths(int minServiceMonths) 
	{
		this.minServiceMonths = minServiceMonths;
	}

	public String getLimits() 
	{
		return limits;
	}

	public void setLimits(String limits) 
	{
		this.limits = limits;
	}

	public String getShortName() 
	{
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getConditions() 
	{
		return conditions;
	}

	public void setConditions(String conditions) 
	{
		this.conditions = conditions;
	}

	public String getLoanTypeName() 
	{
		return loanTypeName;
	}

	public void setLoanTypeName(String loanTypeName) 
	{
		this.loanTypeName = loanTypeName;
	}

	public String getCircumstances() 
	{
		return circumstances;
	}

	public void setCircumstances(String circumstances) 
	{
		this.circumstances = circumstances;
	}

	public String getExceptionMsgString() 
	{
		return ExceptionMsgString;
	}

	public void setExceptionMsgString(String exceptionMsgString) 
	{
		ExceptionMsgString = exceptionMsgString;
	}


	private int posted;	
	private long logID;
	private int salary;
	private int balance;
	private String limits;
	private int refundable;
	private long loanTypeId;
	private String shortName;
	private String conditions;
	private String loanTypeName;	
	private String circumstances;
	private int minServiceMonths;
	private String ExceptionMsgString;

	
	

}
