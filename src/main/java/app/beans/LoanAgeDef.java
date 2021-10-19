package app.beans;

public class LoanAgeDef 
{
	
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
	
	public long getLoanTypeID() 
	{
		return loanTypeID;
	}
	
	public void setLoanTypeID(long loanTypeID) 
	{
		this.loanTypeID = loanTypeID;
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



	private int age;
	private long sequence;
	private String dmlType;
	private long loanTypeID;	
	private int fundPercentage;
	private String logicalOperator;
	
}
