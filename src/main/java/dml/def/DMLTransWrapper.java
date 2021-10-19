package dml.def;

import java.util.List;
import java.io.Serializable;



import app.beans.GeneralRequestBean;
import app.beans.GlFormType;
import app.beans.GlVoucherTemplateMf;
import app.beans.GlVoucherTemplateType;
import app.beans.Glmf;
import app.beans.Loan;
import app.beans.Bank;
import app.beans.Fund;
import app.beans.City;
import app.beans.LoanDef;
import app.beans.LoanSettlement;
import app.beans.Roles;
import app.beans.Country;
import app.beans.Template;
import app.beans.GlSlType;
import app.beans.LoanType;
import app.beans.Employee;
import app.beans.GlSlCode;
import app.beans.GlBranch;
import app.beans.LoanApply;
import app.beans.GlCurrency;
import app.beans.GlBookType;
import app.beans.CostCentre;
import app.beans.RoleDetail;
import app.beans.LoanPayment;
import app.beans.GlSlMapping;
import app.beans.EmployeeType;
import app.beans.FundCategory;
import app.beans.GLBankPaymentVoucher;
import app.beans.GlBankBranch;
import app.beans.ImportPayroll;
import app.beans.LoginEmployee;
import app.beans.ImportEmployee;
import app.beans.UploaderRequest;
import app.beans.ImportLoanApply;
import app.beans.GlJournalVoucher;
import app.beans.LoanInterestRate;
import app.beans.GlFinancialYears;
import app.beans.GlFinancialPeriod;
import app.beans.ImportLoanPayment;
import app.beans.LoanApplyBalLimit;
import app.beans.GlBankReceiptVoucher;
import app.beans.GlCashPaymentVoucher;
import app.beans.GlCashReceiptVoucher;
import app.beans.WfEventAction;
import app.beans.WfEventType;
import app.beans.WfMF;
import app.beans.WfTransition;

public class DMLTransWrapper implements Serializable 
{
	private static final long serialVersionUID = -5171197322728762262L;

	public LoanType getLoanType()
	{
		return loanType;
	}

	public void setLoanType(LoanType loanType)
	{
		this.loanType = loanType;
	}

	public Employee getEmployee()
	{
		return employee;
	}

	public void setEmployee(Employee employee)
	{
		this.employee = employee;
	}

	public Roles getUserRoleBean()
	{
		return userRoleBean;
	}

	public void setUserRoleBean(Roles userRoleBean)
	{
		this.userRoleBean = userRoleBean;
	}

	public Fund getFundBean()
	{
		return fundBean;
	}

	public void setFundBean(Fund fundBean)
	{
		this.fundBean = fundBean;
	}

	public Bank getBankBean()
	{
		return bankBean;
	}

	public void setBankBean(Bank bankBean)
	{
		this.bankBean = bankBean;
	}

	public City getCityBean()
	{
		return cityBean;
	}

	public void setCityBean(City cityBean)
	{
		this.cityBean = cityBean;
	}

	public Country getCountryBean()
	{
		return countryBean;
	}

	public void setCountryBean(Country countryBean)
	{
		this.countryBean = countryBean;
	}

	public GlCurrency getCurrencyBean()
	{
		return currencyBean;
	}

	public void setCurrencyBean(GlCurrency currencyBean)
	{
		this.currencyBean = currencyBean;
	}

	public GlBankBranch getBankBranchBean()
	{
		return bankBranchBean;
	}

	public void setBankBranchBean(GlBankBranch bankBranchBean)
	{
		this.bankBranchBean = bankBranchBean;
	}

	public CostCentre getCostCentreBean()
	{
		return costCentreBean;
	}

	public void setCostCentreBean(CostCentre costCenterBean)
	{
		this.costCentreBean = costCenterBean;
	}

	public FundCategory getFundCategoryBean()
	{
		return fundCategoryBean;
	}

	public void setFundCategoryBean(FundCategory fundCategoryBean)
	{
		this.fundCategoryBean = fundCategoryBean;
	}

	public LoanApply getLoanApprovalBean()
	{
		return loanApprovalBean;
	}

	public void setLoanApprovalBean(LoanApply loanApprovalBean)
	{
		this.loanApprovalBean = loanApprovalBean;
	}

	public GlFinancialPeriod getGlFinancialPeriod()
	{
		return glFinancialPeriod;
	}

	public void setGlFinancialPeriod(GlFinancialPeriod glFinancialPeriod)
	{
		this.glFinancialPeriod = glFinancialPeriod;
	}

	public GlFinancialYears getGlFinancialYears()
	{
		return glFinancialYears;
	}

	public void setGlFinancialYears(GlFinancialYears glFinancialYears)
	{
		this.glFinancialYears = glFinancialYears;
	}

	public Glmf getGlmf()
	{
		return glmf;
	}

	public void setGlmf(Glmf glmf)
	{
		this.glmf = glmf;
	}

	public LoginEmployee getLoginEmployee()
	{
		return loginEmployee;
	}

	public void setLoginEmployee(LoginEmployee loginEmployee)
	{
		this.loginEmployee = loginEmployee;
	}

	public LoanPayment getLoanPayment()
	{
		return loanPayment;
	}

	public void setLoanPayment(LoanPayment loanPayment)
	{
		this.loanPayment = loanPayment;
	}

	public Loan getLoan()
	{
		return loan;
	}

	public void setLoan(Loan loan)
	{
		this.loan = loan;
	}

	public GlSlType getGlSlType()
	{
		return glSlType;
	}

	public void setGlSlType(GlSlType glSlType)
	{
		this.glSlType = glSlType;
	}

	public GlBookType getGlbookType()
	{
		return glbookType;
	}

	public void setGlbookType(GlBookType glbookType)
	{
		this.glbookType = glbookType;
	}

	public List<RoleDetail> getRoleDetailList()
	{
		return roleDetailList;
	}

	public void setRoleDetailList(List<RoleDetail> roleDetailList)
	{
		this.roleDetailList = roleDetailList;
	}

	public List<RoleDetail> getRoleDetailLookUpList()
	{
		return roleDetailLookUpList;
	}

	public void setRoleDetailLookUpList(List<RoleDetail> roleDetailLookUpList)
	{
		this.roleDetailLookUpList = roleDetailLookUpList;
	}

	public GlBranch getBranchBean()
	{
		return branchBean;
	}

	public void setBranchBean(GlBranch branchBean)
	{
		this.branchBean = branchBean;
	}

	public ImportEmployee getImportEmployee()
	{
		return importEmployee;
	}

	public void setImportEmployee(ImportEmployee importEmployee)
	{
		this.importEmployee = importEmployee;
	}

	public UploaderRequest getUploaderRequest()
	{
		return uploaderRequest;
	}

	public void setUploaderRequest(UploaderRequest uploaderRequest)
	{
		this.uploaderRequest = uploaderRequest;
	}

	public ImportPayroll getImportPayroll()
	{
		return importPayroll;
	}

	public void setImportPayroll(ImportPayroll importPayroll)
	{
		this.importPayroll = importPayroll;
	}

	public LoanApplyBalLimit getLoanApplyBalLimit()
	{
		return loanApplyBalLimit;
	}

	public void setLoanApplyBalLimit(LoanApplyBalLimit loanApplyBalLimit)
	{
		this.loanApplyBalLimit = loanApplyBalLimit;
	}
	
	public ImportLoanApply getImportLoanApply()
	{
		return importLoanApply;
	}

	public void setImportLoanApply(ImportLoanApply importLoanApply)
	{
		this.importLoanApply = importLoanApply;
	}


	public ImportLoanPayment getImportLoanPayment() 
	{
		return importLoanPayment;
	}

	public void setImportLoanPayment(ImportLoanPayment importLoanPayment) 
	{
		this.importLoanPayment = importLoanPayment;
	}


	public EmployeeType getEmployeeTypeBean() 
	{
		return employeeTypeBean;
	}

	public void setEmployeeTypeBean(EmployeeType employeeTypeBean) 
	{
		this.employeeTypeBean = employeeTypeBean;
	}

	public Template getTemplateBean() 
	{
		return templateBean;
	}

	public void setTemplateBean(Template templateBean)
	{
		this.templateBean = templateBean;
	}
	
	public GlSlCode getGlSlCode()
	{
		return glSlCode;
	}

	public void setGlSlCode(GlSlCode glSlCode)
	{
		this.glSlCode = glSlCode;
	}
	public GlSlMapping getGlSlMapping() 
	{
		return glSlMapping;
	}

	public void setGlSlMapping(GlSlMapping glSlMapping) 
	{
		this.glSlMapping = glSlMapping;
	}

	public LoanInterestRate getLoanInterestRateBean() 
	{
		return loanInterestRateBean;
	}

	public void setLoanInterestRateBean(LoanInterestRate loanInterestRateBean) 
	{
		this.loanInterestRateBean = loanInterestRateBean;
	}
	
	public GLBankPaymentVoucher getGlBankPaymentVoucher() 
	{
		return glBankPaymentVoucher;
	}

	public void setGlBankPaymentVoucher(GLBankPaymentVoucher glBankPaymentVoucher) 
	{
		this.glBankPaymentVoucher = glBankPaymentVoucher;
	}
	
	public GlBankReceiptVoucher getGlBankReceiptVoucher() 
	{
		return glBankReceiptVoucher;
	}

	public void setGlBankReceiptVoucher(GlBankReceiptVoucher glBankReceiptVoucher) 
	{
		this.glBankReceiptVoucher = glBankReceiptVoucher;
	}

	public GlCashPaymentVoucher getGlCashPaymentVoucher() 
	{
		return glCashPaymentVoucher;
	}

	public void setGlCashPaymentVoucher(GlCashPaymentVoucher glCashPaymentVoucher) 
	{
		this.glCashPaymentVoucher = glCashPaymentVoucher;
	}

	public GlCashReceiptVoucher getGlCashReceiptVoucher() 
	{
		return glCashReceiptVoucher;
	}

	public void setGlCashReceiptVoucher(GlCashReceiptVoucher glCashReceiptVoucher) 
	{
		this.glCashReceiptVoucher = glCashReceiptVoucher;
	}
	
	public GlJournalVoucher getGlJournalVoucher()
	{
		return glJournalVoucher;
	}

	public void setGlJournalVoucher(GlJournalVoucher glJournalVoucher)
	{
		this.glJournalVoucher = glJournalVoucher;
	}
	
	public GlVoucherTemplateType getGlVoucherTemplateType() 
	{
		return glVoucherTemplateType;
	}

	public void setGlVoucherTemplateType(GlVoucherTemplateType glVoucherTemplateType) 
	{
		this.glVoucherTemplateType = glVoucherTemplateType;
	}

	public GlVoucherTemplateMf getGlVoucherTemplateMfBean() 
	{
		return glVoucherTemplateMfBean;
	}

	public void setGlVoucherTemplateMfBean(GlVoucherTemplateMf glVoucherTemplateMfBean) 
	{
		this.glVoucherTemplateMfBean = glVoucherTemplateMfBean;
	}

	public LoanDef getLoanDefBean() 
	{
		return loanDefBean;
	}

	public void setLoanDefBean(LoanDef loanDefBean) 
	{
		this.loanDefBean = loanDefBean;
	}

	public WfEventType getWfEventType() 
	{
		return wfEventType;
	}

	public void setWfEventType(WfEventType wfEventType) 
	{
		this.wfEventType = wfEventType;
	}

	public WfEventAction getWfEventAction() 
	{
		return wfEventAction;
	}

	public void setWfEventAction(WfEventAction wfEventAction) 
	{
		this.wfEventAction = wfEventAction;
	}

	public WfTransition getWfTransitionBean() 
	{
		return wfTransitionBean;
	}

	public void setWfTransitionBean(WfTransition wfTransitionBean) 
	{
		this.wfTransitionBean = wfTransitionBean;
	}

	public WfMF getWfMFBean() 
	{
		return wfMFBean;
	}

	public void setWfMFBean(WfMF wfMFBean) 
	{
		this.wfMFBean = wfMFBean;
	}
	
	public GlFormType getGlFormType()
	{
		return glFormType;
	}

	public void setGlFormType(GlFormType glFormType)
	{
		this.glFormType = glFormType;
	}

	public GeneralRequestBean getGeneralRequestBean()
	{
		return generalRequestBean;
	}

	public void setGeneralRequestBean(GeneralRequestBean generalRequestBean)
	{
		this.generalRequestBean = generalRequestBean;
	}
	
	public LoanSettlement getLoanSettlement() 
	{
		return loanSettlement;
	}

	public void setLoanSettlement(LoanSettlement loanSettlement) 
	{
		this.loanSettlement = loanSettlement;
	}

	
	
	private Loan loan;
	private Glmf glmf;
	private WfMF wfMFBean;
	private Fund fundBean;
	private City cityBean;
	private Bank bankBean;
	private GlSlType glSlType;
	private LoanType loanType;
	private Employee employee;
	private GlSlCode glSlCode; 
	private Roles userRoleBean;
	private LoanDef loanDefBean;
	private Country countryBean;
	private GlBranch branchBean;
	private Template templateBean;
	private GlFormType glFormType;
	private GlBookType glbookType;
	private WfEventType wfEventType;
	private GlSlMapping glSlMapping;
	private LoanPayment loanPayment;
	private GlCurrency currencyBean;
	private CostCentre costCentreBean;
	private LoanApply loanApprovalBean;
	private ImportPayroll importPayroll;
	private LoginEmployee loginEmployee;
	private GlBankBranch bankBranchBean;
	private WfEventAction wfEventAction;
	private WfTransition wfTransitionBean;
	private EmployeeType employeeTypeBean;
	private FundCategory fundCategoryBean;
	private ImportEmployee importEmployee;
	private LoanSettlement loanSettlement;
	private List<RoleDetail> roleDetailList;
	private ImportLoanApply importLoanApply;
	private UploaderRequest uploaderRequest;
	private GlFinancialYears glFinancialYears;
	private GlJournalVoucher glJournalVoucher;
	private LoanApplyBalLimit loanApplyBalLimit;
	private GlFinancialPeriod glFinancialPeriod;
	private ImportLoanPayment importLoanPayment;
	private GeneralRequestBean generalRequestBean;
	private LoanInterestRate loanInterestRateBean;
	private List<RoleDetail> roleDetailLookUpList;
	private GLBankPaymentVoucher glBankPaymentVoucher;
	private GlBankReceiptVoucher glBankReceiptVoucher;
	private GlCashPaymentVoucher glCashPaymentVoucher;
	private GlCashReceiptVoucher glCashReceiptVoucher;
	private GlVoucherTemplateType glVoucherTemplateType;
	private GlVoucherTemplateMf glVoucherTemplateMfBean;
	
}
