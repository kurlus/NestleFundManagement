package app.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;


public class LoanApplyBalLimit {

	public LoanApplyBalLimit()
	{
		// TODO Auto-generated constructor stub
	}

	public long getLoanTypeID()
	{
		return loanTypeID;
	}
	public void setLoanTypeID(long loanTypeID)
	{
		this.loanTypeID = loanTypeID;
	}
	
	public String getEmployeeID()
	{
		return employeeID;
	}
	public void setEmployeeID(String employeeID)
	{
		this.employeeID = employeeID;
	}
	public BigDecimal getBalance()
	{
		return Balance;
	}
	public void setBalance(BigDecimal balance)
	{
		Balance = balance;
	}
	public BigDecimal getApplyLimit()
	{
		return ApplyLimit;
	}
	public void setApplyLimit(BigDecimal applyLimit)
	{
		ApplyLimit = applyLimit;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}


	public String getRequestType()
	{
		return requestType;
	}


	public void setRequestType(String requestType)
	{
		this.requestType = requestType;
	}


	public BigDecimal getInterestAmount()
	{
		return interestAmount;
	}


	public void setInterestAmount(BigDecimal interestAmount)
	{
		this.interestAmount = interestAmount;
	}

	public BigDecimal getApplyAmount()
	{
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount)
	{
		this.applyAmount = applyAmount;
	}


	public BigDecimal getPreviousInterest()
	{
		return previousInterest;
	}

	public void setPreviousInterest(BigDecimal previousInterest)
	{
		this.previousInterest = previousInterest;
	}


	public BigDecimal getPreviousPrincipal()
	{
		return previousPrincipal;
	}


	public void setPreviousPrincipal(BigDecimal previousPrincipal)
	{
		this.previousPrincipal = previousPrincipal;
	}

	public BigDecimal getZakatAmount()
	{
		return zakatAmount;
	}

	public void setZakatAmount(BigDecimal zakatAmount)
	{
		this.zakatAmount = zakatAmount;
	}



	private Date date;
	private long loanTypeID;
	private String employeeID;
	private String requestType;
	private BigDecimal Balance;
	private BigDecimal ApplyLimit;
	private BigDecimal applyAmount;
	private BigDecimal zakatAmount;
	private BigDecimal interestAmount;
	private BigDecimal previousInterest;
	private BigDecimal previousPrincipal;
}