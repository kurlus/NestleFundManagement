package app.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class LoanSettlement {

	public LoanSettlement()
	{
		// TODO Auto-generated constructor stub
	}

	public long getLogID()
	{
		return logID;
	}

	public void setLogID(long logID)
	{
		this.logID = logID;
	}

	public int getPost()
	{
		return post;
	}

	public void setPost(int post)
	{
		this.post = post;
	}

	public String getExceptionMsgString()
	{
		return exceptionMsgString;
	}

	public void setExceptionMsgString(String exceptionMsgString)
	{
		this.exceptionMsgString = exceptionMsgString;
	}

	public long getSettlementID()
	{
		return settlementID;
	}

	public void setSettlementID(long settlementID)
	{
		this.settlementID = settlementID;
	}

	public String getEmployeeID()
	{
		return employeeID;
	}

	public void setEmployeeID(String employeeID)
	{
		this.employeeID = employeeID;
	}

	
	public Date getSettlementDate() 
	{
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) 
	{
		this.settlementDate = settlementDate;
	}

	public BigDecimal getInterestAmount()
	{
		return interestAmount;
	}

	public void setInterestAmount(BigDecimal interestAmount)
	{
		this.interestAmount = interestAmount;
	}

	public BigDecimal getPrincipalAmount()
	{
		return principalAmount;
	}

	public void setPrincipalAmount(BigDecimal principalAmount)
	{
		this.principalAmount = principalAmount;
	}
	
	public String getSettlementTypeID() 
	{
		return settlementTypeID;
	}

	public void setSettlementTypeID(String settlementTypeID) 
	{
		this.settlementTypeID = settlementTypeID;
	}
	
	
	public long getLoanID() 
	{
		return loanID;
	}

	public void setLoanID(long loanID) 
	{
		this.loanID = loanID;
	}

	public Set<LoanSettlementFiles> getLoanSettlementFiles()
	{
		return loanSettlementFiles;
	}

	public void setLoanSettlementFiles(Set<LoanSettlementFiles> loanSettlementFiles)
	{
		this.loanSettlementFiles = loanSettlementFiles;
	}






	private long logID;
	private long loanID;
	private int post = 0;
	private long settlementID;
	private String employeeID;
	private Date settlementDate;
	private String settlementTypeID;
	private String exceptionMsgString;
	private BigDecimal interestAmount;
	private BigDecimal principalAmount;
	private Set<LoanSettlementFiles> loanSettlementFiles;
	
}
