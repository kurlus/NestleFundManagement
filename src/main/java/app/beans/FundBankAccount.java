package app.beans;

import java.io.Serializable;

public class FundBankAccount  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FundBankAccount()
	{
		
	}
	
	public long getFundBankAccountID() 
	{
		return fundBankAccountID;
	}

	public void setFundBankAccountID(long fundBankAccountId) 
	{
		this.fundBankAccountID = fundBankAccountId;
	}

	public String getFundShortName() 
	{
		return fundShortName;
	}

	public void setFundShortName(String fundShortName) 
	{
		this.fundShortName = fundShortName;
	}

	public String getBankShortName() 
	{
		return bankShortName;
	}

	public void setBankShortName(String bankShortName) 
	{
		this.bankShortName = bankShortName;
	}

	public String getBranchShort() 
	{
		return branchShort;
	}

	public void setBranchShort(String branchShort) 
	{
		this.branchShort = branchShort;
	}

	public String getGlMFCode() 
	{
		return glMFCode;
	}

	public void setGlMFCode(String glMFCode) 
	{
		this.glMFCode = glMFCode;
	}

	public String getDefaultAccount() {
		return defaultAccount;
	}

	public void setDefaultAccount(String defaultAccount) 
	{
		this.defaultAccount = defaultAccount;
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
	
	public String getAccountNo() 
	{
		return accountNo;
	}

	public void setAccountNo(String accountNo) 
	{
		this.accountNo = accountNo;
	}

	
	public String getExceptionMsgString() 
	{
		return this.exceptionMsgString;
	}

	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}
	
	private int post;
	private long logID;
	private String glMFCode;
	private String accountNo;
	private String branchShort;
	private String fundShortName;
	private String bankShortName;
	private String defaultAccount;
	private long fundBankAccountID;
	private String exceptionMsgString;
	
}
