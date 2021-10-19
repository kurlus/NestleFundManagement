package app.beans;

public class LoanAgeDefHistory 
{
	public LoanAgeDefHistory()
	{
		
	}
	public int getAge() 
	{
		return age;
	}
	
	public void setAge(int age) 
	{
		this.age = age;
	}
	
	public long getSequence() 
	{
		return sequence;
	}
	
	public void setSequence(long sequence) 
	{
		this.sequence = sequence;
	}
	
	
	public int getFundPercentage() 
	{
		return fundPercentage;
	}
	
	public void setFundPercentage(int fundPercentage) 
	{
		this.fundPercentage = fundPercentage;
	}
	
	public String getLogicalOperator() 
	{
		return logicalOperator;
	}
	
	public void setLogicalOperator(String logicalOperator) 
	{
		this.logicalOperator = logicalOperator;
	}
	
	
	public String getDmlType() 
	{
		return dmlType;
	}

	public void setDmlType(String dmlType) 
	{
		this.dmlType = dmlType;
	}

	public long getHistoryID() 
	{
		return historyID;
	}
	public void setHistoryID(long historyID)
	{
		this.historyID = historyID;
	}

	public LoanDefHistory getLoanDefHistory() 
	{
		return loanDefHistory;
	}
	
	public void setLoanDefHistory(LoanDefHistory loanDefHistory) 
	{
		this.loanDefHistory = loanDefHistory;
	}





	private int age;
	private long sequence;
	private String dmlType;
	private long historyID;
	private int fundPercentage;
	private String logicalOperator;
	private LoanDefHistory loanDefHistory;	
	
}
