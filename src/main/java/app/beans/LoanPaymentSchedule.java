package app.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoanPaymentSchedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	public LoanPaymentSchedule() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public long getLoanId() {
		return loanId;
	}
	public void setLoanId(long loanId) {
		this.loanId = loanId;
	}
	public long getPeriodId() {
		return periodId;
	}
	public void setPeriodId(long periodId) {
		this.periodId = periodId;
	}
	public BigDecimal getPrincipalAmount() {
		return principalAmount;
	}
	public void setPrincipalAmount(BigDecimal principalAmount) {
		this.principalAmount = principalAmount;
	}
	public BigDecimal getInterestAmount() {
		return interestAmount;
	}
	public void setInterestAmount(BigDecimal interestAmount) {
		this.interestAmount = interestAmount;
	}
	public BigDecimal getInstallmentAmount() {
		return installmentAmount;
	}
	public void setInstallmentAmount(BigDecimal installmentAmount) {
		this.installmentAmount = installmentAmount;
	}
	public int getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(int interestRate) {
		this.interestRate = interestRate;
	}
	public int getPosted() {
		return posted;
	}
	public void setPosted(int posted) {
		this.posted = posted;
	}




	private long loanId;
	private long periodId;
	private BigDecimal principalAmount;
	private BigDecimal interestAmount;
	private BigDecimal installmentAmount;
	private int interestRate;
	private int posted;
	
	
}
