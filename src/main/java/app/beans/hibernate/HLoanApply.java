package app.beans.hibernate;

import java.util.Set;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

public class HLoanApply implements Cloneable, Serializable
{
	private static final long serialVersionUID = -2505198061917906132L;

	public HLoanApply() 
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
	
	public long getLoanID() 
	{
		return loanID;
	}
	
	public void setLoanID(long loanID)
	{
		this.loanID = loanID;
	}
	
	public long getApplyID() 
	{
		return applyID;
	}
	
	public void setApplyID(long applyID) 
	{
		this.applyID = applyID;
	}
	
	public long getPeriodID() 
	{
		return periodID;
	}
	
	public void setPeriodID(long periodID) 
	{
		this.periodID = periodID;
	}
	
	public Date getApplyDate() 
	{
		return applyDate;
	}
	
	public void setApplyDate(Date applyDate)
	{
		this.applyDate = applyDate;
	}
	
	public long getLoanTypeID()
	{
		return loanTypeID;
	}
	
	public void setLoanTypeID(long loanTypeID) 
	{
		this.loanTypeID = loanTypeID;
	}
	
	public Long getTransBatchID() 
	{
		return transBatchID;
	}

	public void setTransBatchID(Long transBatchID) 
	{
		this.transBatchID = transBatchID;
	}

	public int getInstallmentNo() 
	{
		return installmentNo;
	}
	
	public void setInstallmentNo(int installmentNo) 
	{
		this.installmentNo = installmentNo;
	}
	
	public String getEmployeeID() 
	{
		return employeeID;
	}
	
	public void setEmployeeID(String employeeID) 
	{
		this.employeeID = employeeID;
	}
	
	public long getUploadBatchID() 
	{
		return uploadBatchID;
	}
	
	public void setUploadBatchID(long uploadBatchID)
	{
		this.uploadBatchID = uploadBatchID;
	}
	
	
	public long getUploadRecordID()
	{
		return uploadRecordID;
	}

	public void setUploadRecordID(long uploadRecordID) 
	{
		this.uploadRecordID = uploadRecordID;
	}

	public BigDecimal getApplyAmount() 
	{
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) 
	{
		this.applyAmount = applyAmount;
	}

	public BigDecimal getInterestAmount() 
	{
		return interestAmount;
	}

	public void setInterestAmount(BigDecimal interestAmount) 
	{
		this.interestAmount = interestAmount;
	}

	public BigDecimal getEligibleAmount() 
	{
		return eligibleAmount;
	}

	public void setEligibleAmount(BigDecimal eligibleAmount) 
	{
		this.eligibleAmount = eligibleAmount;
	}

	public BigDecimal getApprovedAmount()
	{
		return approvedAmount;
	}

	public void setApprovedAmount(BigDecimal approvedAmount) 
	{
		this.approvedAmount = approvedAmount;
	}

	public BigDecimal getMonthlyInstallment() 
	{
		return monthlyInstallment;
	}

	public void setMonthlyInstallment(BigDecimal monthlyInstallment) 
	{
		this.monthlyInstallment = monthlyInstallment;
	}

	public String getExceptionMsgString() 
	{
		return this.exceptionMsgString;
	}

	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}

	public long getBatchID() 
	{
		return batchID;
	}

	public void setBatchID(long batchID) 
	{
		this.batchID = batchID;
	}

	public long getRecordID() 
	{
		return recordID;
	}

	public void setRecordID(long recordID) 
	{
		this.recordID = recordID;
	}

	public Set<HProfileFiles> getProfileFiles() 
	{
		return profileFiles;
	}

	public void setProfileFiles(Set<HProfileFiles> profileFiles) 
	{
		this.profileFiles = profileFiles;
	}


	public int hashCode()
	{
		return String.valueOf(this.getApplyID()).hashCode();
	}

	public boolean equals (Object obj)
	{
		try
		{
			if (obj instanceof HLoanApply)
			{
				HLoanApply argObject = (HLoanApply) obj;
				if (argObject.getApplyID() == this.getApplyID())
					return true;
			}
			return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	
	@Override
    public Object clone() throws CloneNotSupportedException 
    {
        return super.clone();
    }

	//-Attributes
	private long logID;
	private long loanID;
	private long applyID;
	private long periodID;
	private Date applyDate;
	private long loanTypeID;
	private Long transBatchID;
	private int installmentNo;
	private String employeeID;
	private long uploadBatchID;
	private long uploadRecordID;
	private BigDecimal applyAmount;
	private String exceptionMsgString;
	private BigDecimal interestAmount;
	private BigDecimal eligibleAmount;
	private BigDecimal approvedAmount;
	private BigDecimal monthlyInstallment;
	private Set<HProfileFiles> profileFiles;
	
	private long batchID;
	private long recordID;
}
