package app.beans;

import java.util.Date;
import java.util.Set;

public class LoanDefHistory 
{
	private static final long serialVersionUID = 1830289114831536924L;

	public LoanDefHistory() 
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

	public int getRefundable() 
	{
		return refundable;
	}

	public void setRefundable(int refundable) 
	{
		this.refundable = refundable;
	}

	public int getFundBalance() 
	{
		return fundBalance;
	}

	public void setFundBalance(int fundBalance) 
	{
		this.fundBalance = fundBalance;
	}

	public long getLoanTypeId() 
	{
		return loanTypeId;
	}

	public void setLoanTypeId(long loanTypeId) 
	{
		this.loanTypeId = loanTypeId;
	}

	public int getSalaryMonths() 
	{
		return salaryMonths;
	}

	public void setSalaryMonths(int salaryMonths) 
	{
		this.salaryMonths = salaryMonths;
	}

	public Date getLoanDefDate() 
	{
		return loanDefDate;
	}

	public void setLoanDefDate(Date loanDefDate) 
	{
		this.loanDefDate = loanDefDate;
	}

	public int getAgeDependent() 
	{
		return ageDependent;
	}

	public void setAgeDependent(int ageDependent) 
	{
		this.ageDependent = ageDependent;
	}

	public String getConditions() 
	{
		return conditions;
	}

	public void setConditions(String conditions) 
	{
		this.conditions = conditions;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public String getLoanTypeName() 
	{
		return loanTypeName;
	}

	public void setLoanTypeName(String loanTypeName) 
	{
		this.loanTypeName = loanTypeName;
	}

	public String getExceptionMsgString() 
	{
		return ExceptionMsgString;
	}

	public void setExceptionMsgString(String exceptionMsgString) 
	{
		ExceptionMsgString = exceptionMsgString;
	}
	

	public long getHistoryID() 
	{
		return historyID;
	}

	public void setHistoryID(long historyID)
	{
		this.historyID = historyID;
	}

	public Set<LoanAgeDefHistory> getLoanAgeDefHistory()
	{
		return loanAgeDefHistory;
	}

	public void setLoanAgeDefHistory(Set<LoanAgeDefHistory> loanAgeDefHistory)
	{
		this.loanAgeDefHistory = loanAgeDefHistory;
	}




	private long logID;
	private long historyID;
	private int refundable;
	private int fundBalance;
	private long loanTypeId;
	private int salaryMonths;
	private Date loanDefDate;
	private int ageDependent;
	private String conditions;
	private String description;
	private String loanTypeName;	
	private String ExceptionMsgString;
	private Set<LoanAgeDefHistory> loanAgeDefHistory;
	
}
