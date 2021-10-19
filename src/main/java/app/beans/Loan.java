package app.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Loan implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Loan()
	{
		// TODO Auto-generated constructor stub
	}

	public long getLoanId()
	{
		return loanId;
	}

	public void setLoanId(long loanId)
	{
		this.loanId = loanId;
	}

	public long getLoanTypeId()
	{
		return loanTypeId;
	}

	public void setLoanTypeId(long loanTypeId)
	{
		this.loanTypeId = loanTypeId;
	}

	public Date getLoanDate()
	{
		return loanDate;
	}

	public void setLoanDate(Date loanDate)
	{
		this.loanDate = loanDate;
	}

	public BigDecimal getLoanAmount()
	{
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount)
	{
		this.loanAmount = loanAmount;
	}

	public BigDecimal getInterestAmount()
	{
		return interestAmount;
	}

	public void setInterestAmount(BigDecimal interestAmount)
	{
		this.interestAmount = interestAmount;
	}

	public int getNumOfInstallments()
	{
		return numOfInstallments;
	}

	public void setNumOfInstallments(int numOfInstallments)
	{
		this.numOfInstallments = numOfInstallments;
	}

	public BigDecimal getMonthlyInstallment()
	{
		return monthlyInstallment;
	}

	public void setMonthlyInstallment(BigDecimal monthlyInstallment)
	{
		this.monthlyInstallment = monthlyInstallment;
	}

	public int getPosted()
	{
		return posted;
	}

	public void setPosted(int posted)
	{
		this.posted = posted;
	}

	public int getOutstanding()
	{
		return outstanding;
	}

	public void setOutstanding(int outStanding)
	{
		this.outstanding = outStanding;
	}

	public long getApplyId()
	{
		return applyId;
	}

	public void setApplyId(long applyId)
	{
		this.applyId = applyId;
	}

	public BigDecimal getSelfAmount()
	{
		return selfAmount;
	}

	public void setSelfAmount(BigDecimal selfAmount)
	{
		this.selfAmount = selfAmount;
	}

	public BigDecimal getCompanyAmount()
	{
		return companyAmount;
	}

	public void setCompanyAmount(BigDecimal companyAmount)
	{
		this.companyAmount = companyAmount;
	}

	public BigDecimal getInterestRate()
	{
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate)
	{
		this.interestRate = interestRate;
	}

	public String getEmployeeId()
	{
		return employeeId;
	}

	public void setEmployeeId(String employeeId)
	{
		this.employeeId = employeeId;
	}

	public long getLogID()
	{
		return logID;
	}

	public void setLogID(long logID)
	{
		this.logID = logID;
	}

	private long logID;
	private int posted;
	private long loanId;
	private long applyId;
	private Date loanDate;
	private long loanTypeId;
	private int outstanding;
	private String employeeId;
	private int numOfInstallments;
	private BigDecimal loanAmount;
	private BigDecimal selfAmount;
	private BigDecimal interestRate;
	private BigDecimal companyAmount;
	private BigDecimal interestAmount;
	private BigDecimal monthlyInstallment;
}
