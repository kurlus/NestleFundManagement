package app.beans.hibernate;

public class HLoanAgeDef 
{
	
	public int getAge() 
	{
		return age;
	}
	public void setAge(int age) 
	{
		this.age = age;
	}
	public long getSequenceID() 
	{
		return sequenceID;
	}
	public void setSequenceID(long sequence) 
	{
		this.sequenceID = sequence;
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

	public HLoanDef getLoanDef() 
	{
		return loanDef;
	}
	
	public void setLoanDef(HLoanDef loanDef) 
	{
		this.loanDef = loanDef;
	}
	
	private int age;
	private long sequenceID;
	private HLoanDef loanDef;
	private int fundPercentage;
	private String logicalOperator;
	
	
}
