package app.beans;

import java.util.Set;
import java.util.Date;
import java.math.BigDecimal;
import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

import utilities.AppUtility;
import utilities.AppConstants;


public class ImportLoanApply implements Serializable
{
	private static final long serialVersionUID = 5484088288696065143L;

	public ImportLoanApply()
	{		
	}


	public long getApplyID() 
	{
		return applyID;
	}

	public void setApplyID(long applyID) 
	{
		this.applyID = applyID;
	}

	public Date getApplyDate() 
	{
		return applyDate;
	}

	public void setApplyDate(Date applyDate) 
	{
		this.applyDate = applyDate;
	}

	public String getEmployeeID() 
	{
		return employeeID;
	}

	public void setEmployeeID(String employeeID) 
	{
		this.employeeID = employeeID;
	}

	public long getLoanTypeID() 
	{
		return loanTypeID;
	}

	public void setLoanTypeID(long loanTypeID) 
	{
		this.loanTypeID = loanTypeID;
	}

	public BigDecimal getApplyAmount() 
	{
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) 
	{
		this.applyAmount = applyAmount;
	}

	public BigDecimal getInterestRate() 
	{
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) 
	{
		this.interestRate = interestRate;
	}

	public BigDecimal getInterestAmount() 
	{
		return interestAmount;
	}

	public void setInterestAmount(BigDecimal interestAmount) 
	{
		this.interestAmount = interestAmount;
	}

	public int getInstallments() 
	{
		return installments;
	}

	public void setInstallments(int installments) 
	{
		this.installments = installments;
	}

	public BigDecimal getMonthlyInstallment() 
	{
		return monthlyInstallment;
	}

	public void setMonthlyInstallment(BigDecimal monthlyInstallment) 
	{
		this.monthlyInstallment = monthlyInstallment;
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

	public BigDecimal getApprovedAmount() 
	{
		return approvedAmount;
	}

	public void setApprovedAmount(BigDecimal approvedAmount) 
	{
		this.approvedAmount = approvedAmount;
	}

	public String getPurpose() 
	{
		return purpose;
	}

	public void setPurpose(String purpose) 
	{
		this.purpose = purpose;
	}

	public int getFlagManager() 
	{
		return flagManager;
	}

	public void setFlagManager(int flagManager) 
	{
		this.flagManager = flagManager;
	}

	public int getFlagT1() 
	{
		return flagT1;
	}

	public void setFlagT1(int flagT1) 
	{
		this.flagT1 = flagT1;
	}

	public int getFlagT2() 
	{
		return flagT2;
	}

	public void setFlagT2(int flagT2) 
	{
		this.flagT2 = flagT2;
	}

	public String getRemarksManager() 
	{
		return remarksManager;
	}

	public void setRemarksManager(String remarksManager) 
	{
		this.remarksManager = remarksManager;
	}

	public String getRemarksT1() 
	{
		return remarksT1;
	}

	public void setRemarksT1(String remarksT1) 
	{
		this.remarksT1 = remarksT1;
	}

	public String getRemarksT2() 
	{
		return remarksT2;
	}

	public void setRemarksT2(String remarksT2) 
	{
		this.remarksT2 = remarksT2;
	}

	public int getPost() 
	{
		return post;
	}

	public void setPost(int post) 
	{
		this.post = post;
	}

	public long getLogID() 
	{
		return logID;
	}

	public void setLogID(long logID) 
	{
		this.logID = logID;
	}

	public String getExceptionMsgString() 
	{
		return this.exceptionMsgString;
	}

	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}

	public Set<ProfileFiles> getProfileFiles() 
	{
		return profileFiles;
	}

	public void setProfileFiles(Set<ProfileFiles> profileFiles) 
	{
		this.profileFiles = profileFiles;
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

	public int getOutStanding() 
	{
		return outStanding;
	}

	public void setOutStanding(int outStanding) 
	{
		this.outStanding = outStanding;
	}

	public int getFlagImport() 
	{
		return flagImport;
	}

	public void setFlagImport(int flagImport) 
	{
		this.flagImport = flagImport;
	}

	public String getFailReason() 
	{
		return failReason;
	}

	public void setFailReason(String failReason) 
	{
		this.failReason = failReason;
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
			case interestRateIdx:
				setInterestRate(new BigDecimal(String.valueOf(cellValue).trim()));
				break;
			case interestAmountIdx:
				setInterestAmount(new BigDecimal(String.valueOf(cellValue).trim()));
				break;
			case installmentsIdx:
				setInstallments(Integer.valueOf(String.valueOf(cellValue).trim()));
				break;
			case monthlyInstallmentIdx:
				setMonthlyInstallment(new BigDecimal(String.valueOf(cellValue).trim()));
				break;
			case selfAmountIdx:
				setSelfAmount(new BigDecimal(String.valueOf(cellValue).trim()));
				break;
			case companyAmountIdx:
				setCompanyAmount(new BigDecimal(String.valueOf(cellValue).trim()));
				break;
			case approvedAmountIdx:
				setApprovedAmount(new BigDecimal(String.valueOf(cellValue).trim()));
				break;
			case purposeIdx:
				setPurpose(String.valueOf(cellValue).trim());
				break;
			case flagManagerIdx:
				setFlagManager(Integer.valueOf(String.valueOf(cellValue).trim()));
				break;
			case flagT1Idx:
				setFlagT1(Integer.valueOf(String.valueOf(cellValue).trim())); 
				break;
			case flagT2Idx:
				setFlagT2(Integer.valueOf(String.valueOf(cellValue).trim()));
				break;
			case remarksManagerIdx:
				setRemarksManager(String.valueOf(cellValue).trim());
				break;
			case remarksT1Idx:
				setRemarksT1(String.valueOf(cellValue).trim());
				break;
			case remarksT2Idx:
				setRemarksT2(String.valueOf(cellValue).trim());
				break;
				/*	
			case outStandingIdx:
				setOutStanding(Integer.valueOf(String.valueOf(cellValue).trim()));
				break;
				 */	
			default:
				System.out.println(" No Index Found Exception"+ cellValue);
			}

		}
		catch(Exception e)
		{
			throw e;
		}
	}

	//-Attributes
	private int post = 0 ;
	private long logID;
	private int flagT1 =0;
	private int flagT2 =0;
	private long applyID;
	private Date applyDate;
	private String purpose;
	private int flagManager =0;
	private long loanTypeID;
	private String remarksT1;
	private String remarksT2;
	private int installments =0;
	private String employeeID;
	private String remarksManager;
	private BigDecimal selfAmount;
	private BigDecimal applyAmount;
	private BigDecimal interestRate;
	private BigDecimal companyAmount;
	private BigDecimal approvedAmount;
	private BigDecimal interestAmount;
	private String exceptionMsgString;
	private BigDecimal monthlyInstallment;
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
	private final int interestRateIdx = AppConstants.six;
	private final int interestAmountIdx =  AppConstants.seven;
	private final int installmentsIdx = AppConstants.eight;
	private final int monthlyInstallmentIdx =  AppConstants.nine;
	private final int selfAmountIdx =  AppConstants.ten;
	private final int companyAmountIdx = AppConstants.eleven;
	private final int approvedAmountIdx = AppConstants.twelve;
	private final int purposeIdx = AppConstants.thirteen;
	private final int flagManagerIdx =  AppConstants.fourteen;
	private final int flagT1Idx = AppConstants.fifteen;
	private final int flagT2Idx = AppConstants.sixteen;
	private final int remarksManagerIdx = AppConstants.seventeen;
	private final int remarksT1Idx = AppConstants.eighteen;
	private final int remarksT2Idx =  AppConstants.ninteen;
	private final int outStandingIdx = AppConstants.twenty;

	// Loan attributes for Loan Apply-loader
	//master table attributes
	private long batchID;
	private long recordID;
	private int flagImport;
	private int outStanding;
	private String failReason;

}
