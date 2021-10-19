package app.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;


public class LoanDefination implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LoanDefination() {
		// TODO Auto-generated constructor stub
	}

	

	

	public long getLoanId() {
		return loanId;
	}
	public void setLoanId(long loanId) {
		this.loanId = loanId;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public long getLoanTypeId() {
		return loanTypeId;
	}
	public void setLoanTypeId(long loanTypeId) {
		this.loanTypeId = loanTypeId;
	}
	public Date getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	public String getLoanDateString() {
		if(loanDate!=null)
			return new SimpleDateFormat("dd/MM/yyyy").format(loanDate);
		return "";
	}
	public void setLoanDateString(String loanDateString) {
		this.loanDateString = loanDateString;
	}
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public int getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(int interestRate) {
		this.interestRate = interestRate;
	}
	public BigDecimal getInterestAmount() {
		return interestAmount;
	}
	public void setInterestAmount(BigDecimal interestAmount) {
		this.interestAmount = interestAmount;
	}
	public int getNumOfInstallments() {
		return numOfInstallments;
	}
	public void setNumOfInstallments(int numOfInstallments) {
		this.numOfInstallments = numOfInstallments;
	}
	public BigDecimal getMonthlyInstallment() {
		return monthlyInstallment;
	}
	public void setMonthlyInstallment(BigDecimal monthlyInstallment) {
		this.monthlyInstallment = monthlyInstallment;
	}
	public String getLoanRemarks() {
		return loanRemarks;
	}
	public void setLoanRemarks(String loanRemarks) {
		this.loanRemarks = loanRemarks;
	}
	public int getPosted() {
		return posted;
	}
	public void setPosted(int posted) {
		this.posted = posted;
	}
	public int getOutstanding() {
		return outstanding;
	}
	public void setOutstanding(int outStanding) {
		this.outstanding = outStanding;
	}





	private long loanId;
	private long employeeId;
	private long loanTypeId;
	private Date loanDate;
	private String loanDateString;
	private BigDecimal loanAmount;
	private int interestRate;
	private BigDecimal interestAmount;
	private int numOfInstallments;
	private BigDecimal monthlyInstallment;
	private String loanRemarks;
	private int posted;
	private int outstanding;
}
