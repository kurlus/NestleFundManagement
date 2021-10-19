package app.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoanSchedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoanSchedule()
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

	public long getPeriodId()
	{
		return periodId;
	}

	public void setPeriodId(long periodId)
	{
		this.periodId = periodId;
	}

	public BigDecimal getPrincipalAmount()
	{
		return principalAmount;
	}

	public void setPrincipalAmount(BigDecimal principalAmount)
	{
		this.principalAmount = principalAmount;
	}

	public BigDecimal getInterestAmount()
	{
		return interestAmount;
	}

	public void setInterestAmount(BigDecimal interestAmount)
	{
		this.interestAmount = interestAmount;
	}

	public BigDecimal getInstallmentAmount()
	{
		return installmentAmount;
	}

	public void setInstallmentAmount(BigDecimal installmentAmount)
	{
		this.installmentAmount = installmentAmount;
	}

	public int getInterestRate()
	{
		return interestRate;
	}

	public void setInterestRate(int interestRate)
	{
		this.interestRate = interestRate;
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


	private int posted;
	private long logID;
	private long loanId;
	private long periodId;
	private int interestRate;
	private BigDecimal principalAmount;
	private BigDecimal interestAmount;
	private BigDecimal installmentAmount;

}
