package app.beans;

import java.io.Serializable;

public class  EmployeeContacts implements Serializable
{
	private static final long serialVersionUID = 5101471260963640583L;

	public EmployeeContacts()
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

	public long getContactID() 
	{
		return contactID;
	}

	public void setContactID(long contactID) 
	{
		this.contactID = contactID;
	}

	public String getContact() 
	{
		return contact;
	}

	public void setContact(String contact) 
	{
		this.contact = contact;
	}

	public String getEmployeeID() 
	{
		return employeeID;
	}

	public void setEmployeeID(String employeeID) 
	{
		this.employeeID = employeeID;
	}

	public String getAddressType() 
	{
		return addressType;
	}

	public void setAddressType(String addressType) 
	{
		this.addressType = addressType;
	}

	public String getCityShort() {
		return cityShort;
	}

	public void setCityShort(String cityShort) {
		this.cityShort = cityShort;
	}
	public String getCountryShort() {
		return countryShort;
	}

	public void setCountryShort(String countryShort) {
		this.countryShort = countryShort;
	}

	public String getAddressLine1() 
	{
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) 
	{
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() 
	{
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) 
	{
		this.addressLine2 = addressLine2;
	}



	private int post;	
	private long logID;
	private long contactID;
	private String cityShort;
	private String countryShort;
	private String addressLine1;
	private String addressLine2;
	private String contact;	
	private String employeeID;
	private String addressType;

}
