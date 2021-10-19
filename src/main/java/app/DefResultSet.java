package app;

import java.io.Serializable;
import java.util.List;

import app.beans.Bank;
import app.beans.City;
import app.beans.CostCentre;
import app.beans.Country;
import app.beans.Employee;
import app.beans.EmployeeType;
import app.beans.Fund;
import app.beans.GlBankBranch;
import app.beans.GLBankPaymentVoucher;
import app.beans.GlBankReceiptVoucher;
import app.beans.GlBookType;
import app.beans.GlBranch;
import app.beans.GlCashPaymentVoucher;
import app.beans.GlCashReceiptVoucher;
import app.beans.GlCurrency;
import app.beans.GlFamilyType;
import app.beans.GlFinancialPeriod;
import app.beans.GlFinancialYears;
import app.beans.GlFormType;
import app.beans.GlJournalVoucher;
import app.beans.GlParentData;
import app.beans.GlSlCode;
import app.beans.GlSlMapping;
import app.beans.GlSlType;
import app.beans.GlVoucherTemplateMf;
import app.beans.GlVoucherTemplateType;
import app.beans.Glmf;
import app.beans.ImportEmployee;
import app.beans.ImportLoanApply;
import app.beans.ImportLoanPayment;
import app.beans.ImportPayroll;
import app.beans.LoaderInfo;
import app.beans.LoaderRun;
import app.beans.Loan;
import app.beans.LoanApply;
import app.beans.LoanDef;
import app.beans.LoanInterestRate;
import app.beans.LoanPayment;
import app.beans.LoanSchedule;
import app.beans.LoanSettlement;
import app.beans.LoanType;
import app.beans.LoginEmployee;
import app.beans.PayRollLoan;
import app.beans.PayRollPFContribution;
import app.beans.PayRollPNContribution;
import app.beans.Payroll;
import app.beans.RoleDetail;
import app.beans.RoleType;
import app.beans.Roles;
import app.beans.SubGroupSubMenu;
import app.beans.Template;
import app.beans.WfEventAction;
import app.beans.WfEventType;
import app.beans.WfMF;
import app.beans.WfTransition;


public class DefResultSet implements Serializable 
{
	private static final long serialVersionUID = -8214064992956572682L;

	public DefResultSet() 
	{
	}
	
	public DefResultSet loadDefinitionData() 
	{
		return this;
	}
	
	public GlParentData[] getGlBeans() 
	{
		return glBeans;
	}

	public void setGlBeans(GlParentData[] glBeans) 
	{
		this.glBeans = glBeans;
	}
	
	public LoanType[] getLoanTypes() 
	{
		return loanTypes;
	}

	public void setLoanTypes(LoanType[] loanTypeBeans) 
	{
		this.loanTypes = loanTypeBeans;
	}

	public Roles[] getRoles() 
	{
		return roles;
	}

	public void setRoles(Roles[] roles) 
	{
		this.roles = roles;
	}

	public LoanApply[] getLoanApply() 
	{
		return loanApply;
	}

	public void setLoanApply(LoanApply[] loanApply) 
	{
		this.loanApply = loanApply;
	}


	public City[] getCityBeans() 
	{
		return cityBeans;
	}

	public void setCityBeans(City[] cityBeans) 
	{
		this.cityBeans = cityBeans;
	}

	public Country[] getCountryBeans() 
	{
		return countryBeans;
	}

	public void setCountryBeans(Country[] countryBeans) 
	{
		this.countryBeans = countryBeans;
	}

	public CostCentre[] getCostCentreBeans() 
	{
		return costCentreBeans;
	}

	public void setCostCentreBeans(CostCentre[] costCentreBeans) 
	{
		this.costCentreBeans = costCentreBeans;
	}
	
	public Employee[] getEmployeeBeans() 
	{
		return employeeBeans;
	}

	public void setEmployeeBeans(Employee[] employeeBeans) 
	{
		this.employeeBeans = employeeBeans;
	}

	public Bank[] getGlBankBeans() 
	{
		return glBankBeans;
	}

	public void setGlBankBeans(Bank[] glBankBeans) 
	{
		this.glBankBeans = glBankBeans;
	}
	
	public LoaderRun[] getLoaderRunBeans() 
	{
		return loaderRunBeans;
	}

	public void setLoaderRunBeans(LoaderRun[] loaderRunBeans) 
	{
		this.loaderRunBeans = loaderRunBeans;
	}
	
	public LoginEmployee[] getLoginEmployeeBeans() 
	{
		return loginEmployeeBeans;
	}

	public void setLoginEmployeeBeans(LoginEmployee[] loginEmployeeBeans) 
	{
		this.loginEmployeeBeans = loginEmployeeBeans;
	}

	public GlBankBranch[] getGlBankBranchBeans() 
	{
		return glBankBranchBeans;
	}

	public void setGlBankBranchBeans(GlBankBranch[] glBankBranchBeans) 
	{
		this.glBankBranchBeans = glBankBranchBeans;
	}
	
	public LoginEmployee getLoginEmployee() 
	{
		return loginEmployee;
	}

	public void setLoginEmployee(LoginEmployee loginEmployee) 
	{
		this.loginEmployee = loginEmployee;
	}

	public GlFinancialPeriod[] getGlFinancialPeriodBeans() 
	{
		return glFinancialPeriodBeans;
	}

	public void setGlFinancialPeriodBeans(GlFinancialPeriod[] glFinancialPeriodBeans) 
	{
		this.glFinancialPeriodBeans = glFinancialPeriodBeans;
	}
	
	public GlFinancialYears[] getGlFinancialYears() 
	{
		return glFinancialYears;
	}

	public void setGlFinancialYears(GlFinancialYears[] glFinancialYears)
	{
		this.glFinancialYears = glFinancialYears;
	}
	
	public RoleDetail[] getRoleDetailBeans() 
	{
		return roleDetailBeans;
	}

	public void setRoleDetailBeans(RoleDetail[] roleDetailBeans) 
	{
		this.roleDetailBeans = roleDetailBeans;
	}
	
	public GlFamilyType[] getGlFamilyTypeBeans() 
	{
		return glFamilyTypeBeans;
	}

	public void setGlFamilyTypeBeans(GlFamilyType[] glFamilyTypeBeans) 
	{
		this.glFamilyTypeBeans = glFamilyTypeBeans;
	}

	public LoaderInfo[] getLoaderInfo() 
	{
		return loaderInfo;
	}

	public void setLoaderInfo(LoaderInfo[] loaderInfo) 
	{
		this.loaderInfo = loaderInfo;
	}
	
	public ImportPayroll[] getImportPayroll() 
	{
		return ImportPayroll;
	}

	public void setImportPayroll(ImportPayroll[] importPayroll) 
	{
		ImportPayroll = importPayroll;
	}

	public ImportEmployee[] getImportEmplyeeBeans() 
	{
		return importEmplyeeBeans;
	}

	public void setImportEmplyeeBeans(ImportEmployee[] importEmplyeeBeans) 
	{
		this.importEmplyeeBeans = importEmplyeeBeans;
	}

	public ImportLoanApply[] getImportLoanApplyBeans() 
	{
		return importLoanApplyBeans;
	}

	public void setImportLoanApplyBeans(ImportLoanApply[] importLoanApplyBeans) 
	{
		this.importLoanApplyBeans = importLoanApplyBeans;
	}

	public GlBookType[] getGlBookTypeBeans() 
	{
		return glBookTypeBeans;
	}

	public void setGlBookTypeBeans(GlBookType[] glBookTypeBeans) 
	{
		this.glBookTypeBeans = glBookTypeBeans;
	}

	public GlSlMapping[] getGlSlMappingBeans() 
	{
		return glSlMappingBeans;
	}

	public void setGlSlMappingBeans(GlSlMapping[] glSlMappingBeans) 
	{
		this.glSlMappingBeans = glSlMappingBeans;
	}

	public GlSlType[] getGlSlTypeBeans() 
	{
		return glSlTypeBeans;
	}

	public void setGlSlTypeBeans(GlSlType[] glSlTypeBeans) 
	{
		this.glSlTypeBeans = glSlTypeBeans;
	}

	public ImportLoanPayment[] getImportLoanPaymentBeans() 
	{
		return ImportLoanPaymentBeans;
	}

	public void setImportLoanPaymentBeans(ImportLoanPayment[] importLoanPaymentBeans) 
	{
		ImportLoanPaymentBeans = importLoanPaymentBeans;
	}
	
	public GlCurrency[] getGlCurrencyBeans() 
	{
		return glCurrencyBeans;
	}

	public void setGlCurrencyBeans(GlCurrency[] glCurrencyBeans) 
	{
		this.glCurrencyBeans = glCurrencyBeans;
	}
	
	public SubGroupSubMenu[] getSubMenusBeans() 
	{
		return subMenusBeans;
	}

	public void setSubMenusBeans(SubGroupSubMenu[] subMenusBeans) 
	{
		this.subMenusBeans = subMenusBeans;
	}
	
	public LoanSchedule[] getLoanPaymentSchedule()
	{
		return loanPaymentSchedule;
	}

	public void setLoanPaymentSchedule(LoanSchedule[] loanPaymentSchedule)
	{
		this.loanPaymentSchedule = loanPaymentSchedule;
	}

	public Loan[] getLoanProcess()
	{
		return loanProcess;
	}

	public void setLoanProcess(Loan[] loanProcess)
	{
		this.loanProcess = loanProcess;
	}

	public LoanPayment[] getLoanPayment()
	{
		return loanPayment;
	}

	public void setLoanPayment(LoanPayment[] loanPayment)
	{
		this.loanPayment = loanPayment;
	}
	
	public GlBranch[] getGlBranchBeans() 
	{
		return glBranchBeans;
	}

	public void setGlBranchBeans(GlBranch[] glBranchBeans) 
	{
		this.glBranchBeans = glBranchBeans;
	}
	public List<Glmf> getGlmfBeans() 
	{
		return glmfBeans;
	}

	public void setGlmfBeans(List<Glmf> glmfBeans) 
	{
		this.glmfBeans = glmfBeans;
	}

	

	public RoleType[] getRoleTypes()
	{
		return roleTypes;
	}

	public void setRoleTypes(RoleType[] roleTypes)
	{
		this.roleTypes = roleTypes;
	}

	

	public EmployeeType[] getEmployeeTypeBeans() 
	{
		return employeeTypeBeans;
	}

	public void setEmployeeTypeBeans(EmployeeType[] employeeTypeBeans) 
	{
		this.employeeTypeBeans = employeeTypeBeans;
	}



	public Template[] getGlTemplateBeans() 
	{
		return glTemplateBeans;
	}

	public void setGlTemplateBeans(Template[] glTemplateBeans) 
	{
		this.glTemplateBeans = glTemplateBeans;
	}
	
	public GlSlCode[] getGlSlCodeBeans()
	{
		return glSlCodeBeans;
	}

	public void setGlSlCodeBeans(GlSlCode[] glSlCodeBeans)
	{
		this.glSlCodeBeans = glSlCodeBeans;
	}

	public LoanInterestRate[] getRatesBeans() 
	{
		return ratesBeans;
	}

	public void setRatesBeans(LoanInterestRate[] ratesBeans) 
	{
		this.ratesBeans = ratesBeans;
	}

	public Payroll[] getPayRollBeans() 
	{
		return payRollBeans;
	}

	public void setPayRollBeans(Payroll[] payRollBeans)
	{
		this.payRollBeans = payRollBeans;
	}

	public PayRollLoan[] getPayRollLoanBeans()
	{
		return payRollLoanBeans;
	}

	public void setPayRollLoanBeans(PayRollLoan[] payRollLoanBeans)
	{
		this.payRollLoanBeans = payRollLoanBeans;
	}

	public PayRollPFContribution[] getPayRollPFContribution()
	{
		return payRollPFContribution;
	}

	public void setPayRollPFContribution(PayRollPFContribution[] payRollPFContribution) 
	{
		this.payRollPFContribution = payRollPFContribution;
	}

	public PayRollPNContribution[] getPayRollPNContribution() 
	{
		return payRollPNContribution;
	}

	public void setPayRollPNContribution(
			PayRollPNContribution[] payRollPNContribution) 
	{
		this.payRollPNContribution = payRollPNContribution;
	}

	public GLBankPaymentVoucher[] getGlVoucherBeans() 
	{
		return glVoucherBeans;
	}

	public void setGlVoucherBeans(GLBankPaymentVoucher[] glVoucherBeans) 
	{
		this.glVoucherBeans = glVoucherBeans;
	}
	
	public GlBankReceiptVoucher[] getGlBankReceiptVoucherBeans() 
	{
		return glBankReceiptVoucherBeans;
	}

	public void setGlBankReceiptVoucherBeans(GlBankReceiptVoucher[] glBankReceiptVoucherBeans) 
	{
		this.glBankReceiptVoucherBeans = glBankReceiptVoucherBeans;
	}

	public GLBankPaymentVoucher[] getGlBankPaymentVoucherBeans() 
	{
		return glBankPaymentVoucherBeans;
	}

	public void setGlBankPaymentVoucherBeans(GLBankPaymentVoucher[] glBankPaymentVoucherBeans) 
	{
		this.glBankPaymentVoucherBeans = glBankPaymentVoucherBeans;
	}

	public GlCashPaymentVoucher[] getGlCashPaymentVoucherBeans() 
	{
		return glCashPaymentVoucherBeans;
	}

	public void setGlCashPaymentVoucherBeans(GlCashPaymentVoucher[] glCashPaymentVoucherBeans) 
	{
		this.glCashPaymentVoucherBeans = glCashPaymentVoucherBeans;
	}

	public GlCashReceiptVoucher[] getGlCashReceiptVoucherBeans() 
	{
		return glCashReceiptVoucherBeans;
	}

	public void setGlCashReceiptVoucherBeans(GlCashReceiptVoucher[] glCashReceiptVoucherBeans) 
	{
		this.glCashReceiptVoucherBeans = glCashReceiptVoucherBeans;
	}
	
	public GlJournalVoucher[] getGlJournalVoucherBeans()
	{
		return glJournalVoucherBeans;
	}

	public void setGlJournalVoucherBeans(GlJournalVoucher[] glJournalVoucherBeans)
	{
		this.glJournalVoucherBeans = glJournalVoucherBeans;
	}
	
	public Fund[] getFundBeans() 
	{
		return fundBeans;
	}

	public void setFundBeans(Fund[] fundBeans) 
	{
		this.fundBeans = fundBeans;
	}

	public GlVoucherTemplateType[] getGlVoucherTemplateTypeBeans() 
	{
		return glVoucherTemplateTypeBeans;
	}

	public void setGlVoucherTemplateTypeBeans(GlVoucherTemplateType[] glVoucherTemplateTypeBeans) 
	{
		this.glVoucherTemplateTypeBeans = glVoucherTemplateTypeBeans;
	}
	
	public GlVoucherTemplateMf[] getGlVoucherTemplateMfBeans() 
	{
		return glVoucherTemplateMfBeans;
	}

	public void setGlVoucherTemplateMfBeans(GlVoucherTemplateMf[] glVoucherTemplateMfBeans) 
	{
		this.glVoucherTemplateMfBeans = glVoucherTemplateMfBeans;
	}
	
	public LoanDef[] getLoanDefBeans() 
	{
		return loanDefBeans;
	}

	public void setLoanDefBeans(LoanDef[] loanDefBeans) 
	{
		this.loanDefBeans = loanDefBeans;
	}
	
	public WfEventType[] getWfEventTypeBeans() 
	{
		return wfEventTypeBeans;
	}

	public void setWfEventTypeBeans(WfEventType[] wfEventTypeBeans) 
	{
		this.wfEventTypeBeans = wfEventTypeBeans;
	}

	public WfTransition[] getWfTransitionBeans() 
	{
		return wfTransitionBeans;
	}

	public void setWfTransitionBeans(WfTransition[] wfTransitionBeans) 
	{
		this.wfTransitionBeans = wfTransitionBeans;
	}

	public WfEventAction[] getWfEventActionBeans() 
	{
		return wfEventActionBeans;
	}

	public void setWfEventActionBeans(WfEventAction[] wfEventActionBeans) 
	{
		this.wfEventActionBeans = wfEventActionBeans;
	}	
	public WfMF[] getWfMfBeans() 
	{
		return wfMfBeans;
	}

	public void setWfMfBeans(WfMF[] wfMfBeans) 
	{
		this.wfMfBeans = wfMfBeans;
	}
	
	public GlFormType[] getGlFormTypeBeans()
	{
		return glFormTypeBeans;
	}

	public void setGlFormTypeBeans(GlFormType[] glFormTypeBeans)
	{
		this.glFormTypeBeans = glFormTypeBeans;
	}
	
	public LoanSettlement[] getLoanSettlement() 
	{
		return loanSettlement;
	}

	public void setLoanSettlement(LoanSettlement[] loanSettlement) 
	{
		this.loanSettlement = loanSettlement;
	}
	
	
	private Roles[] roles;
	private WfMF[] wfMfBeans;
	private Fund[] fundBeans;
	private City[] cityBeans;
	private Loan[] loanProcess;
	private Bank[] glBankBeans;
	private RoleType[] roleTypes;
	private List<Glmf> glmfBeans;
	private LoanType[] loanTypes;
	private LoanDef[] loanDefBeans;
	private LoanApply[] loanApply;
	private GlParentData[] glBeans;
	private Country[] countryBeans;
	private Payroll[] payRollBeans;
	private LoaderInfo[] loaderInfo;
	private Employee[] employeeBeans;
	private GlSlCode[] glSlCodeBeans;
	private GlSlType[] glSlTypeBeans;
	private GlBranch[] glBranchBeans;
	private LoanPayment[] loanPayment;
	private Template[] glTemplateBeans;  
	private LoaderRun[] loaderRunBeans;	
	private LoginEmployee loginEmployee;
	private GlCurrency[] glCurrencyBeans;
	private GlBookType[] glBookTypeBeans;
	private GlFormType[] glFormTypeBeans;
	private CostCentre[] costCentreBeans;
	private RoleDetail[] roleDetailBeans;
	private LoanInterestRate[] ratesBeans;
	private ImportPayroll[] ImportPayroll;
	private WfEventType[] wfEventTypeBeans;
	private PayRollLoan[] payRollLoanBeans;
	private GlSlMapping[] glSlMappingBeans;
	private SubGroupSubMenu[] subMenusBeans;
	private LoanSettlement[] loanSettlement;
	private EmployeeType[] employeeTypeBeans; 
	private GlFamilyType[] glFamilyTypeBeans;
	private GlBankBranch[] glBankBranchBeans;
	private WfTransition[] wfTransitionBeans;
	private LoanSchedule[] loanPaymentSchedule;
	private LoginEmployee[] loginEmployeeBeans;
	private WfEventAction[] wfEventActionBeans;
	private GlFinancialYears[] glFinancialYears;
	private ImportEmployee[] importEmplyeeBeans;
	private GLBankPaymentVoucher[] glVoucherBeans;
	private ImportLoanApply[] importLoanApplyBeans;
	private GlJournalVoucher[] glJournalVoucherBeans;
	private ImportLoanPayment[] ImportLoanPaymentBeans;
	private GlFinancialPeriod[] glFinancialPeriodBeans;
	private PayRollPFContribution[] payRollPFContribution;
	private PayRollPNContribution[] payRollPNContribution;
	private GlVoucherTemplateMf[] glVoucherTemplateMfBeans;
	private GlBankReceiptVoucher[] glBankReceiptVoucherBeans;
	private GLBankPaymentVoucher[] glBankPaymentVoucherBeans;
	private GlCashPaymentVoucher[] glCashPaymentVoucherBeans;
	private GlCashReceiptVoucher[] glCashReceiptVoucherBeans;
	private GlVoucherTemplateType[] glVoucherTemplateTypeBeans;
	
	
	
}
