package app.beans;

import java.io.Serializable;

public class GlBankBranch implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4008750730964428541L;
	
	public GlBankBranch()
	{
		
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
	
	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getFaxOne() 
	{
		return faxOne;
	}
	
	public void setFaxOne(String faxOne)
	{
		this.faxOne = faxOne;
	}
	
	public String getFaxTwo()
	{
		return faxTwo;
	}
	
	public void setFaxTwo(String faxTwo) 
	{
		this.faxTwo = faxTwo;
	}
	public String getPhoneOne() {
		return phoneOne;
	}
	
	public void setPhoneOne(String phoneOne) 
	{
		this.phoneOne = phoneOne;
	}
	
	public String getPhoneTwo() {
		return phoneTwo;
	}
	public void setPhoneTwo(String phoneTwo) 
	{
		this.phoneTwo = phoneTwo;
	}
	
	public String getBankShort() 
	{
		return bankShort;
	}
	
	public void setBankShort(String bankShort) 
	{
		this.bankShort = bankShort;
	}
	
	public String getCityShort() {
		return cityShort;
	}
	public void setCityShort(String cityShort) 
	{
		this.cityShort = cityShort;
	}
	
	public long getBankBranchID() 
	{
		return bankBranchID;
	}
	
	public void setBankBranchID(long bankBranchID) 
	{
		this.bankBranchID = bankBranchID;
	}
	
	public String getBranchShort() 
	{
		return branchShort;
	}
	
	public void setBranchShort(String branchShort)
	{
		this.branchShort = branchShort;
	}
	
	public String getCountryShort() 
	{
		return countryShort;
	}
	
	public void setCountryShort(String countryShort) 
	{
		this.countryShort = countryShort;
	}
	
	public String getBankBranchShort() 
	{
		return bankBranchShort;
	}
	
	public void setBankBranchShort(String bankBranchShort)
	{
		this.bankBranchShort = bankBranchShort;
	}
	
	public String getBankBranchAddress() 
	{
		return bankBranchAddress;
	}
	
	public void setBankBranchAddress(String bankBranchAddress)
	{
		this.bankBranchAddress = bankBranchAddress;
	}
	
	public String getExceptionMsgString() 
	{
		return exceptionMsgString;
	}
	
	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}


	private int post;
	private long logID;
	private String email;
	private String faxOne;
	private String faxTwo;
	private String phoneOne;
	private String phoneTwo;
	private String bankShort;
	private String cityShort;
	private long bankBranchID;
	private String branchShort;
	private String countryShort;
	private String bankBranchShort;
	private String bankBranchAddress;
	private String exceptionMsgString;

}
