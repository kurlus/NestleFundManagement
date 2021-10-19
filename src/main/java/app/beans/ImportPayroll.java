package app.beans;

import java.util.Date;
import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import utilities.AppUtility;
import utilities.AppConstants;


public class ImportPayroll implements Serializable
{
	private static final long serialVersionUID = 1930894450541726030L;

	public ImportPayroll() 
	{
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

	public Date getPayRollDate() 
	{
		return payRollDate;
	}

	public void setPayRollDate(Date payRollDate) 
	{
		this.payRollDate = payRollDate;
	}

	public String getEmployeeID() 
	{
		return employeeID;
	}

	public void setEmployeeID(String employeeID) 
	{
		this.employeeID = employeeID;
	}

	public double getLoanInstallment() 
	{
		return loanInstallment;
	}

	public void setLoanInstallment(double loanInstallment) 
	{
		this.loanInstallment = loanInstallment;
	}

	public double getPensionerPayment() 
	{
		return pensionerPayment;
	}

	public void setPensionerPayment(double pensionerPayment) 
	{
		this.pensionerPayment = pensionerPayment;
	}

	public double getSelfPensionContrib() 
	{
		return selfPensionContrib;
	}

	public void setSelfPensionContrib(double selfPensionContrib) 
	{
		this.selfPensionContrib = selfPensionContrib;
	}

	public double getInterestInstallment() 
	{
		return interestInstallment;
	}

	public void setInterestInstallment(double interestInstallment) 
	{
		this.interestInstallment = interestInstallment;
	}

	public double getCompanyPensionContrib() 
	{
		return companyPensionContrib;
	}

	public void setCompanyPensionContrib(double companyPensionContrib) 
	{
		this.companyPensionContrib = companyPensionContrib;
	}

	public double getSelfProvidentFundContrib() 
	{
		return selfProvidentFundContrib;
	}

	public void setSelfProvidentFundContrib(double selfProvidentFundContrib) 
	{
		this.selfProvidentFundContrib = selfProvidentFundContrib;
	}

	public double getCompanyProvidentFundContrib() 
	{
		return companyProvidentFundContrib;
	}

	public void setCompanyProvidentFundContrib(double companyProvidentFundContrib) 
	{
		this.companyProvidentFundContrib = companyProvidentFundContrib;
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
			case employeeIDIdx:
				setEmployeeID(String.valueOf(cellValue).trim());
				break;
			case periodIDIdx:				
				setPayRollDate(AppUtility.loaderFormatDate(String.valueOf(cellValue).trim()));
				break;
			case selfProvidentFundContribIdx:
				setSelfProvidentFundContrib(Double.valueOf(String.valueOf(cellValue).trim()));
				break;
			case companyProvidentFundContribIdx:
				setCompanyProvidentFundContrib(Double.valueOf(String.valueOf(cellValue).trim()));
				break;
			case selfPensionContribIdx:
				setSelfPensionContrib(Double.valueOf(String.valueOf(cellValue).trim()));
				break;
			case companyPensionContribdx:
				setCompanyPensionContrib(Double.valueOf(String.valueOf(cellValue).trim()));
				break;
			case loanInstallmentIdx:
				setLoanInstallment(Double.valueOf(String.valueOf(cellValue).trim()));
				break;
			case interestInstallmentIdx:
				setInterestInstallment(Double.valueOf(String.valueOf(cellValue).trim()));
				break;
			case pensionerPaymentIdx:
				setPensionerPayment(Double.valueOf(String.valueOf(cellValue).trim()));
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
		return pensionerPaymentIdx;
	}

	/*
	 * Payroll data uploaded excel sheet attribute indexes
	 * Must follow the sequence of loader file i.e Payroll Upload   
	 * 
	 */

	private final int recordIDIdx = AppConstants.zero;	
	private final int employeeIDIdx = AppConstants.one;
	private final int periodIDIdx = AppConstants.two;	
	private final int selfProvidentFundContribIdx = AppConstants.three;
	private final int companyProvidentFundContribIdx = AppConstants.four;
	private final int selfPensionContribIdx = AppConstants.five;
	private final int companyPensionContribdx = AppConstants.six;
	private final int loanInstallmentIdx = AppConstants.seven;
	private final int interestInstallmentIdx =  AppConstants.eight;
	private final int pensionerPaymentIdx = AppConstants.nine;

	private long batchID;
	private long recordID;
	private int flagImport;
	private Date payRollDate;
	private String employeeID;
	private String failReason;
	private double loanInstallment;
	private double pensionerPayment;	
	private double selfPensionContrib;
	private double interestInstallment;
	private double companyPensionContrib;
	private double selfProvidentFundContrib;	
	private double companyProvidentFundContrib;
}
