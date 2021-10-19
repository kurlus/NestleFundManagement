package app.beans;

import java.util.Date;
import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import utilities.AppUtility;
import utilities.AppConstants;


public class LoanPayment implements Serializable 
{
	private static final long serialVersionUID = 6348687649269706406L;

	public LoanPayment() 
	{
	}

	public long getLoanID() 
	{
		return loanID;
	}

	public void setLoanID(long loanID) 
	{
		this.loanID = loanID;
	}

	public long getBatchID() 
	{
		return batchID;
	}

	public void setBatchID(long batchID) 
	{
		this.batchID = batchID;
	}

	public double getZakat() 
	{
		return zakat;
	}

	public void setZakat(double zakat) 
	{
		this.zakat = zakat;
	}

	public long getRecordID() 
	{
		return recordID;
	}

	public void setRecordID(long recordID) 
	{
		this.recordID = recordID;
	}

	public Date getPaymentDate() 
	{
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) 
	{
		this.paymentDate = paymentDate;
	}

	public String getBankShort() 
	{
		return bankShort;
	}

	public void setBankShort(String bankShort) 
	{
		this.bankShort = bankShort;
	}

	public long getAdjustLoanID() 
	{
		return adjustLoanID;
	}

	public void setAdjustLoanID(long adjustLoanID) 
	{
		this.adjustLoanID = adjustLoanID;
	}

	public long getLoanPaymentID() 
	{
		return loanPaymentID;
	}

	public void setLoanPaymentID(long loanPaymentID) 
	{
		this.loanPaymentID = loanPaymentID;
	}

	public String getBranchShort() 
	{
		return branchShort;
	}

	public void setBranchShort(String branchShort) 
	{
		this.branchShort = branchShort;
	}

	public String getAccountNumber() 
	{
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) 
	{
		this.accountNumber = accountNumber;
	}

	public double getPaymentAmount() 
	{
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) 
	{
		this.paymentAmount = paymentAmount;
	}
	
	public String getIBAN() 
	{
		return IBAN;
	}

	public void setIBAN(String iBAN) 
	{
		IBAN = iBAN;
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
		return exceptionMsgString;
	}
	
	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
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
			case loanPaymentIDIdx:
				setLoanPaymentID(Long.valueOf(String.valueOf(cellValue).trim()));
				break;
			case loanIdIdx:
				setLoanID(Long.valueOf(String.valueOf(cellValue).trim()));
				break;
			case paymentDateIdx:
				setPaymentDate(AppUtility.loaderFormatDate(String.valueOf(cellValue).trim()));
				break;
			case adjustLoanIDIdx:
				setAdjustLoanID(Long.valueOf(String.valueOf(cellValue).trim()));
				break;
			case zakatIdx:
				setZakat(Double.valueOf(String.valueOf(cellValue).trim()));
				break;
			case paymentAmountIdx:
				setPaymentAmount(Double.valueOf(String.valueOf(cellValue).trim()));
				break;
			case bankShortIdx:
				setBankShort(String.valueOf(cellValue).trim());
				break;
			case branchShortIdx:
				setBranchShort(String.valueOf(cellValue).trim());
				break;
			case accountNumberIdx:
				setAccountNumber(String.valueOf(cellValue).trim());
				break;
			case IBANIdx:
				setIBAN(String.valueOf(cellValue).trim());
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
		return IBANIdx;
	}


	public double getSelfAmount()
	{
		return selfAmount;
	}

	public void setSelfAmount(double selfAmount)
	{
		this.selfAmount = selfAmount;
	}

	public double getCompanyAmount()
	{
		return companyAmount;
	}

	public void setCompanyAmount(double compabnyAmount)
	{
		this.companyAmount = compabnyAmount;
	}


	private long logID;
	private int post =0;
	private String IBAN;
	private long loanID;
	private long batchID;
	private double zakat;	
	private long recordID;	
	private Date paymentDate;
	private double selfAmount;
	private String bankShort;
	private long adjustLoanID;
	private long loanPaymentID;
	private String branchShort;
	private String accountNumber;
	private double paymentAmount;
	private double companyAmount;
	private String exceptionMsgString;

	private final int recordIDIdx = AppConstants.zero;	
	private final int loanPaymentIDIdx = AppConstants.one;
	private final int loanIdIdx = AppConstants.two;
	private final int paymentDateIdx = AppConstants.three;
	private final int adjustLoanIDIdx = AppConstants.four;
	private final int zakatIdx = AppConstants.five;
	private final int paymentAmountIdx = AppConstants.six;
	private final int bankShortIdx =  AppConstants.seven;
	private final int branchShortIdx =  AppConstants.eight;
	private final int accountNumberIdx = AppConstants.nine;
	private final int IBANIdx = AppConstants.ten;
}
