package app.beans;

import java.util.Set;
import java.util.Date;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.logging.Level;

import utilities.AppUtility;
import utilities.AppConstants;
import org.apache.commons.lang3.StringUtils;


public class Employee implements Serializable 
{
	private static final long serialVersionUID = -3708329557337035175L;

	public Employee()
	{
	}

	public String getEmployeeID() 
	{
		return employeeID;
	}

	public void setEmployeeID(String employeeID) 
	{
		this.employeeID = employeeID;
	}

	public String getEmployeeName() 
	{
		return employeeName;
	}

	public void setEmployeeName(String employeeName) 
	{
		this.employeeName = employeeName;
	}

	public String getNIC() 
	{
		return NIC;
	}

	public void setNIC(String nIC) 
	{
		NIC = nIC;
	}

	public String getNtn() 
	{
		return ntn;
	}

	public void setNtn(String ntn) 
	{
		this.ntn = ntn;
	}

	public Date getDateOfBirth() 
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) 
	{
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfJoining() 
	{
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) 
	{
		this.dateOfJoining = dateOfJoining;
	}

	public Date getLastWorkingDay() 
	{
		return lastWorkingDay;
	}

	public void setLastWorkingDay(Date lastWorkingDay) 
	{
		this.lastWorkingDay = lastWorkingDay;
	}

	public Date getDateOfConfirmation() 
	{
		return dateOfConfirmation;
	}

	public void setDateOfConfirmation(Date dateOfConfirmation) 
	{
		this.dateOfConfirmation = dateOfConfirmation;
	}

	public String getReason() 
	{
		return reason;
	}

	public void setReason(String reason) 
	{
		this.reason = reason;
	}

	public String getGender() 
	{
		return gender;
	}

	public void setGender(String gender) 
	{
		this.gender = gender;
	}

	public int getActive() 
	{
		return active;
	}

	public void setActive(int active) 
	{
		this.active = active;
	}

	public String getCostCentreID() 
	{
		return costCentreID;
	}

	public void setCostCentreID(String costCentreID) 
	{
		this.costCentreID = costCentreID;
	}

	public String getManagerID() 
	{
		return managerID;
	}

	public void setManagerID(String managerID) 
	{
		this.managerID = managerID;
	}

	public String getUserID() 
	{
		return userID;
	}

	public void setUserID(String userID) 
	{
		this.userID = userID;
	}

	public String getLocation() 
	{
		return location;
	}

	public void setLocation(String location) 
	{
		this.location = location;
	}

	public String getOrgUnit() 
	{
		return orgUnit;
	}

	public void setOrgUnit(String orgUnit) 
	{
		this.orgUnit = orgUnit;
	}

	public String getBusiness() 
	{
		return business;
	}

	public void setBusiness(String business) 
	{
		this.business = business;
	}

	public String getEmployeeGroup() 
	{
		return employeeGroup;
	}

	public void setEmployeeGroup(String employeeGroup) 
	{
		this.employeeGroup = employeeGroup;
	}

	public String getSubGroup() 
	{
		return subGroup;
	}

	public void setSubGroup(String subGroup) 
	{
		this.subGroup = subGroup;
	}

	public String getEmployeeFunction() 
	{
		return employeeFunction;
	}

	public void setEmployeeFunction(String employeeFunction) 
	{
		this.employeeFunction = employeeFunction;
	}

	public String getSubFunction() 
	{
		return subFunction;
	}

	public void setSubFunction(String subFunction) 
	{
		this.subFunction = subFunction;
	}

	public String getDesignation() 
	{
		return designation;
	}

	public void setDesignation(String designation) 
	{
		this.designation = designation;
	}

	public String getDepartment() 
	{
		return department;
	}

	public void setDepartment(String department) 
	{
		this.department = department;
	}

	public String getNationality() 
	{
		return nationality;
	}

	public void setNationality(String nationality) 
	{
		this.nationality = nationality;
	}

	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getRoleID() 
	{
		return roleID;
	}

	public void setRoleID(String roleID) 
	{
		this.roleID = roleID;
	}

	public String getBankName() 
	{
		return bankName;
	}

	public void setBankName(String bankName) 
	{
		this.bankName = bankName;
	}

	public String getBankShortName() 
	{
		return bankShortName;
	}

	public void setBankShortName(String bankShortName) 
	{
		this.bankShortName = bankShortName;
	}

	public String getBranchShortName() 
	{
		return branchShortName;
	}

	public void setBranchShortName(String branchShortName) 
	{
		this.branchShortName = branchShortName;
	}

	public String getAccountNo() 
	{
		return accountNo;
	}

	public void setAccountNo(String accountNo) 
	{
		this.accountNo = accountNo;
	}

	public BigDecimal getBasicPay() 
	{
		return basicPay;
	}

	public void setBasicPay(BigDecimal basicPay) 
	{
		this.basicPay = basicPay;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getExceptionMsgString() 
	{
		return ExceptionMsgString;
	}

	public void setExceptionMsgString(String exceptionMsgString) 
	{
		ExceptionMsgString = exceptionMsgString;
	}

	public Set<EmployeeContacts> getEmployeeContacts() 
	{
		return employeeContacts;
	}

	public void setEmployeeContacts(Set<EmployeeContacts> employeeContacts) 
	{
		this.employeeContacts = employeeContacts;
	}

	public int getOnBehalfOf() 
	{
		return onBehalfOf;
	}

	public void setOnBehalfOf(int onBehalfOf) 
	{
		this.onBehalfOf = onBehalfOf;
	}

	/*
	 *  Employee Contact Getters and Setters for employee loader 
	 * ==========================================================
	 */
	public String gettCity() 
	{
		return tCity;
	}

	public void settCity(String tCity) 
	{
		this.tCity = tCity;
	}

	public String gettCountry() 
	{
		return tCountry;
	}

	public void settCountry(String tCountry) 
	{
		this.tCountry = tCountry;
	}

	public String gettContact() 
	{
		return tContact;
	}

	public void settContact(String tContact) 
	{
		this.tContact = tContact;
	}

	public String gettAddress() {
		return tAddress;
	}

	public void settAddress(String tAddress) 
	{
		this.tAddress = tAddress;
	}

	public String getpCity() 
	{
		return pCity;
	}

	public void setpCity(String pCity) 
	{
		this.pCity = pCity;
	}

	public String getpCountry() 
	{
		return pCountry;
	}

	public void setpCountry(String pCountry) 
	{
		this.pCountry = pCountry;
	}

	public String getpContact() 
	{
		return pContact;
	}

	public void setpContact(String pContact) 
	{
		this.pContact = pContact;
	}

	public String getpAddress() 
	{
		return pAddress;
	}

	public void setpAddress(String pAddress) 
	{
		this.pAddress = pAddress;
	}
	
	
	public int getEmployeeType() 
	{
		return employeeType;
	}

	public void setEmployeeType(int employeeType) 
	{
		this.employeeType = employeeType;
	}

	/*
	 *  Employee record id Getters and Setters for employee loader  
	 * ===========================================================
	 */
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

	public long getLogID() 
	{
		return logID;
	}

	public void setLogID(long logID) 
	{
		this.logID = logID;
	}

	public int getPost() 
	{
		return post;
	}

	public void setPost(int post) 
	{
		this.post = post;
	}

	public String getIban() 
	{
		return iban;
	}

	public void setIban(String iban) 
	{
		this.iban = iban;
	}

	
	
	public String getBankTitle() 
	{
		return bankTitle;
	}

	public void setBankTitle(String bankTitle) 
	{
		this.bankTitle = bankTitle;
	}

	public String getBranchCode() 
	{
		return branchCode;
	}

	public void setBranchCode(String branchCode) 
	{
		this.branchCode = branchCode;
	}
	
	//31-8-2016
	
	public Date getDojPF() 
	{
		return dojPF;
	}

	public void setDojPF(Date dojPF)
	{
		this.dojPF = dojPF;
	}

	public Date getDojPN()
	{
		return dojPN;
	}

	public void setDojPN(Date dojPN) 
	{
		this.dojPN = dojPN;
	}

	public Date getDojGR()
	{
		return dojGR;
	}

	public void setDojGR(Date dojGR) 
	{
		this.dojGR = dojGR;
	}

	public String getPnPlan() 
	{
		return pnPlan;
	}

	public void setPnPlan(String pnPlan)
	{
		this.pnPlan = pnPlan;
	}

	public String getSelfPNRate() 
	{
		return selfPNRate;
	}

	public void setSelfPNRate(String selfPNRate) 
	{
		this.selfPNRate = selfPNRate;
	}

	public String getCompanyPNRate()
	{
		return companyPNRate;
	}

	public void setCompanyPNRate(String companyPNRate) 
	{
		this.companyPNRate = companyPNRate;
	}

	
	//End

	public int hashCode()
	{
		return getEmployeeID().hashCode();
	}

	public boolean equals (Object obj)
	{
		try
		{
			if (obj instanceof Employee)
			{
				Employee argObject = (Employee) obj;
				if (argObject.getEmployeeID().trim().equals(getEmployeeID().trim()))
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
			if ((cellValue != null) && (StringUtils.isEmpty(cellValue.toString().trim()))) return; 

			switch(attributeIndex)
			{
			case recordIDIdx:
				setRecordID(Long.valueOf(String.valueOf(cellValue).trim()));
				break;
			case empIDIdx:
				setEmployeeID(String.valueOf(cellValue).trim());
				break;
			case empNameIdx:
				setEmployeeName(String.valueOf(cellValue).trim());
				break;
			case NICIdx:
				setNIC(String.valueOf(cellValue).trim());
				break;
			case NTNIdx:
				setNtn(String.valueOf(cellValue).trim());
				break;
			case DOBIdx:
				setDateOfBirth(AppUtility.loaderFormatDate(String.valueOf(cellValue).trim()));
				break;
			case DOJIdx:
				setDateOfJoining(AppUtility.loaderFormatDate(String.valueOf(cellValue).trim()));
				break;
			case DOCIdx:
				setDateOfConfirmation(AppUtility.loaderFormatDate(String.valueOf(cellValue).trim()));
				break;
			case LWDIdx:
				setLastWorkingDay(AppUtility.loaderFormatDate(String.valueOf(cellValue).trim()));
				break;
			case reasonIdx:
				setReason(String.valueOf(cellValue).trim());
				break;
			case genderIdx:
				setGender(String.valueOf(cellValue).trim());
				break;
			case activeIdx:
				setActive(Integer.valueOf(String.valueOf(cellValue).trim()).intValue());
				break;
			case costCentreIdx:
				setCostCentreID(String.valueOf(cellValue).trim());
				break;
			case managerIDIdx:
				setManagerID(String.valueOf(cellValue).trim());
				break;
			case userIDIdx:
				setUserID(String.valueOf(cellValue).trim()); 
				break;
			case locationIdx:
				setLocation(String.valueOf(cellValue).trim());
				break;
			case orgUnitIDIdx:
				setOrgUnit(String.valueOf(cellValue).trim());
				break;
			case businessIdx:
				setBusiness(String.valueOf(cellValue).trim());
				break;
			case groupIdx:
				setEmployeeGroup(String.valueOf(cellValue).trim());
				break;
			case subGroupIdx:
				setSubGroup(String.valueOf(cellValue).trim());
				break;
			case functionIdx:
				setEmployeeFunction(String.valueOf(cellValue).trim());
				break;
			case subFunctionIdx:
				setSubFunction(String.valueOf(cellValue).trim());
				break;
			case designationIdx:
				setDesignation(String.valueOf(cellValue).trim());
				break;
			case departmentIdx:
				setDepartment(String.valueOf(cellValue).trim());
				break;
			case nationalityIdx:
				setNationality(String.valueOf(cellValue).trim());
				break;
			case statusIdx:
				setStatus(String.valueOf(cellValue).trim());
				break;
			case onBehalfOfIdx:
				setOnBehalfOf(Integer.valueOf(String.valueOf(cellValue).trim()).intValue());
				break;
			case roleIdIdx:
				setRoleID(String.valueOf(cellValue).trim());
				break;
			case bankTitleIdx:
				setBankName(String.valueOf(cellValue).trim());
				break;
			case bankIdx:
				setBankShortName(String.valueOf(cellValue).trim());
				break;
			case bankBranchIdx:
				setBranchShortName(String.valueOf(cellValue).trim());
				break;
			case bankAccountIdx:
				setAccountNo(String.valueOf(cellValue).trim());
				break;
			case basicPayIdx:
				setBasicPay(new BigDecimal(String.valueOf(cellValue).trim()));
				break;
			case emailIdx:
				setEmail(String.valueOf(cellValue).trim());
				break;
			case tAddressIdx:
				settAddress(String.valueOf(cellValue).trim());
				break;
			case pAddressIdx:
				setpAddress(String.valueOf(cellValue).trim());
				break;
			case tContactIdx:
				settContact(String.valueOf(cellValue).trim());
				break;
			case pContactIdx:
				setpContact(String.valueOf(cellValue).trim());
				break;
			case tCityIdx:
				settCity(String.valueOf(cellValue).trim());
				break;
			case pCityIdx:
				setpCity(String.valueOf(cellValue).trim());
				break;
			case tCountryIdx:
				settCountry(String.valueOf(cellValue).trim());
				break;
			case pCountryIdx:
				setpCountry(String.valueOf(cellValue).trim());
				break;
			case IBANIdx:
				setIban(String.valueOf(cellValue).trim());
				break;
			default:
				System.out.println(" No Index Found Exception"+ cellValue);
			}

		}
		catch(Exception e)
		{
			AppUtility.logger.log(Level.SEVERE, 
					" Exception for attribute index "+attributeIndex +" with value "+cellValue.toString()+ " "+ e.getMessage());
			throw e;
		}
	}

	public int getLastRowCellIndex()
	{
		return IBANIdx;
	}

	
	
	private Date dojPF;
	private Date dojPN;
	private Date dojGR;
	private long logID;
	private String NIC;
	private String ntn;
	private String iban;
	private int post = 0;
	private String email;
	private String pnPlan;
	private String roleID;
	private String status;
	private String gender;
	private String reason;
	private String userID;
	private String orgUnit;
	private String location;
	private String business;
	private int active = 0;
	private String bankTitle;
	private String bankName;
	private String subGroup;
	private int employeeType;
	private Date dateOfBirth;
	private String managerID;
	private String accountNo;
	private String selfPNRate;
	private String employeeID;
	private String department;
	private String subFunction;
	private String designation;	
	private String nationality;	
	private int onBehalfOf = 0;
	private Date dateOfJoining;
	private String branchCode;
	private Date lastWorkingDay;
	private String costCentreID;
	private BigDecimal basicPay;
	private String employeeName;
	private String employeeGroup;
	private String bankShortName;
	private String companyPNRate;
	private String branchShortName;
	private String employeeFunction;
	private Date dateOfConfirmation;
	private String ExceptionMsgString;

	private Set<EmployeeContacts> employeeContacts;

	/*
	 * Employee data uploaded excel sheet attribute indexes
	 * Must follow the sequence of loader file i.e Member-Profile Upload   
	 * 
	 */
	private final int recordIDIdx = AppConstants.zero;	
	private final int empIDIdx = AppConstants.one;
	private final int empNameIdx = AppConstants.two;
	private final int NICIdx = AppConstants.three;
	private final int NTNIdx = AppConstants.four;
	private final int DOBIdx = AppConstants.five;
	private final int DOJIdx =  AppConstants.six;
	private final int DOCIdx = AppConstants.seven;
	private final int LWDIdx =  AppConstants.eight;
	private final int reasonIdx =  AppConstants.nine;
	private final int genderIdx = AppConstants.ten;
	private final int activeIdx = AppConstants.eleven;
	private final int costCentreIdx = AppConstants.twelve;
	private final int managerIDIdx =  AppConstants.thirteen;
	private final int userIDIdx = AppConstants.fourteen;
	private final int locationIdx = AppConstants.fifteen;
	private final int orgUnitIDIdx = AppConstants.sixteen;
	private final int businessIdx = AppConstants.seventeen;
	private final int groupIdx =  AppConstants.eighteen;
	private final int subGroupIdx = AppConstants.ninteen;
	private final int functionIdx =  AppConstants.twenty;
	private final int subFunctionIdx =  AppConstants.twentyone;
	private final int designationIdx =  AppConstants.twentytwo;
	private final int departmentIdx =  AppConstants.twentythree;
	private final int nationalityIdx = AppConstants.twentyfour;
	private final int statusIdx = AppConstants.twentyfive;
	private final int onBehalfOfIdx = AppConstants.twentysix; 
	private final int roleIdIdx = AppConstants.twentyseven;
	private final int bankTitleIdx = AppConstants.twentyeight;
	private final int bankIdx =  AppConstants.twentynine;
	private final int bankBranchIdx = AppConstants.thirty;
	private final int bankAccountIdx = AppConstants.thirtyone;
	private final int basicPayIdx = AppConstants.thirtytwo;
	private final int emailIdx = AppConstants.thirtythree;
	private final int tAddressIdx = AppConstants.thirtyfour;
	private final int pAddressIdx = AppConstants.thirtyfive;
	private final int tContactIdx = AppConstants.thirtysix;
	private final int pContactIdx = AppConstants.thirtyseven;
	private final int tCityIdx = AppConstants.thirtyeight;
	private final int pCityIdx = AppConstants.thirtynine;
	private final int tCountryIdx = AppConstants.forty;
	private final int pCountryIdx = AppConstants.fortyone;
	private final int IBANIdx = AppConstants.fortytwo;

	// Employee Contact attributes for Employee-loader
	//master table attributes
	private long batchID;
	private long recordID;
	// temporary address for Employee-loader
	private String tCity;
	private String tCountry;
	private String tContact;
	private String tAddress;
	// permanent address for Employee-loader
	private String pCity;
	private String pCountry;
	private String pContact;
	private String pAddress;

}
