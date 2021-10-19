package app.beans;

import java.util.Set;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import utilities.AppUtility;
import utilities.AppConstants;


public class LoanApply implements Serializable
{
	private static final long serialVersionUID = -2505198061917906132L;

	public LoanApply() 
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

	public Set<ProfileFiles> getProfileFiles() 
	{
		return profileFiles;
	}

	public void setProfileFiles(Set<ProfileFiles> profileFiles) 
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
			if (obj instanceof LoanApply)
			{
				LoanApply argObject = (LoanApply) obj;
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


	public void excelSheetTupleMapper(int attributeIndex, Object cellValue) throws Exception
	{
		try
		{
			if (cellValue == null) return;
			if ((cellValue != null) && (StringUtils.isEmpty(cellValue.toString()))) return; 

			switch(attributeIndex)
			{
			case recordIDIdx:
				setRecordID(Long.valueOf(String.valueOf(cellValue).trim()));
				break;
			case applyIDIdx:
				setApplyID(Long.valueOf(String.valueOf(cellValue).trim()));
				break;
			case applyDateIdx:
				setApplyDate(AppUtility.loaderFormatDate(String.valueOf(cellValue).trim()));
				break;
			case employeeIDIdx:
				setEmployeeID(String.valueOf(cellValue).trim());
				break;
			case loanTypeIDIdx:
				setLoanTypeID(Long.valueOf(String.valueOf(cellValue).trim()));
				break;
			case applyAmountIdx:
				setApplyAmount(new BigDecimal(String.valueOf(cellValue).trim()));
				break;
			case monthlyInstallmentIdx:
				setMonthlyInstallment(new BigDecimal(String.valueOf(cellValue).trim()));
				break;
			case approvedAmountIdx:
				setApprovedAmount(new BigDecimal(String.valueOf(cellValue).trim()));
				break;
			default:
				System.out.println(" No Index Found Exception"+ cellValue);
			}

		}
		catch(Exception e)
		{
			throw e;
		}
	}

	public int getLastRowCellIndex()
	{
		return approvedAmountIdx;
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
	private BigDecimal applyAmount;
	private long uploadBatchID;
	private long uploadRecordID;
	private BigDecimal interestAmount;
	private BigDecimal eligibleAmount;
	private BigDecimal approvedAmount;
	private BigDecimal monthlyInstallment;
	
	private String exceptionMsgString;
	private Set<ProfileFiles> profileFiles;

	/*
	 * Loan Apply data uploaded excel sheet attribute indexes
	 * Must follow the sequence of loader file i.e Loan Apply Upload   
	 * 
	 */
	private final int recordIDIdx = AppConstants.zero;	
	private final int applyIDIdx = AppConstants.one;
	private final int applyDateIdx = AppConstants.two;
	private final int employeeIDIdx = AppConstants.three;
	private final int loanTypeIDIdx = AppConstants.four;
	private final int applyAmountIdx = AppConstants.five;
	private final int monthlyInstallmentIdx =  AppConstants.six;
	private final int approvedAmountIdx = AppConstants.seven;

	// Loan attributes for Loan Apply-loader
	//master table attributes
	private long batchID;
	private long recordID;
}
