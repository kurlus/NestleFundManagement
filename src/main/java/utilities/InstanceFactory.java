package utilities;

import app.admin.EmployeeRoleMappingAdmin;
import app.admin.LoginEmployeeAdmin;
import app.admin.WorkFlowDefTransitionAdmin;
import app.admin.WorkFlowEventActionAdmin;
import app.admin.WorkFlowEventTypeAdmin;
import app.admin.WorkFlowTransitionAdmin;
import app.beans.Employee;
import app.beans.GlVoucherTemplateType;
import app.beans.ImportPayroll;
import app.beans.LoanApply;
import app.beans.LoanPayment;
import app.def.LoanDefinitionDef;
import app.transacs.EmployeeTransacs;
import app.transacs.ImportEmployeeTransacs;
import app.transacs.ImportLoanApplyTransacs;
import app.transacs.ImportLoanPaymentTransacs;
import app.transacs.ImportPayrollTransacs;
import app.transacs.LoaderRunTransacs;
import app.transacs.LoanApprovalTransacs;
import app.transacs.LoanPaymentScheduleTransacs;
import app.transacs.LoanPaymentTransacs;
import app.transacs.LoanProcessTransacs;
import app.transacs.VoucherTemplateMFTransacs;
import app.transacs.VoucherTemplateTypeTransacs;

public  class InstanceFactory 
{

	public static synchronized EmployeeTransacs getEmployeeTransacsFactory()
	{
		return new EmployeeTransacs();
	}
	
	public static synchronized LoginEmployeeAdmin getLoginEmployeeAdminFactory()
	{
		return new LoginEmployeeAdmin();
	}
	
	public static synchronized LoanApprovalTransacs getLoanApprovalTransacsFactory()
	{
		return new LoanApprovalTransacs();
	}
	
	public static synchronized LoaderRunTransacs getLoaderRunTransacsFactory()
	{
		return new LoaderRunTransacs();
	}
	
	public static synchronized ImportEmployeeTransacs getImportEmployeeTransacsFactory()
	{
		return new ImportEmployeeTransacs();
	}
	
	public static synchronized ImportLoanApplyTransacs getImportLoanApplyTransacsFactory()
	{
		return new ImportLoanApplyTransacs();
	}
	
	public static synchronized ImportPayrollTransacs getImportPayrollTransacsFactory()
	{
		return new ImportPayrollTransacs();
	}
	
	public static synchronized ImportLoanPaymentTransacs getImportLoanPaymentTransacsFactory()
	{
		return new ImportLoanPaymentTransacs();
	}
	
	public static synchronized EmployeeRoleMappingAdmin getEmployeeRoleMappingAdminFactory()
	{
		return new EmployeeRoleMappingAdmin();
	}
	
	public static synchronized LoanPaymentScheduleTransacs getLoanPaymentScheduleTransacsFactory()
	{
		return new LoanPaymentScheduleTransacs();
	}
	
	public static synchronized LoanProcessTransacs getLoanProcessTransacsFactory()
	{
		return new LoanProcessTransacs();
	}
	
	public static synchronized LoanPaymentTransacs getLoanPaymentTransacsFactory()
	{
		return new LoanPaymentTransacs();
	}
	
	public static synchronized LoanApply getEmployeeLoanApplyInstance() throws Exception
	{
		return new LoanApply();
	}

	public static synchronized LoanPayment getEmployeeLoanPaymentInstance() throws Exception
	{
		return new LoanPayment();
	}

	public static synchronized Employee getEmployeeProfileInstance() throws Exception
	{
		return new Employee();
	}

	public static synchronized ImportPayroll getEmployeePayrollTransacInstance() throws Exception
	{
		return new ImportPayroll();
	}
	
	public static synchronized VoucherTemplateTypeTransacs getVoucherTemplateTypeTransacs() throws Exception
	{
		return new VoucherTemplateTypeTransacs();
	}
	
	public static synchronized LoanDefinitionDef getLoanDefinitionDef() throws Exception
	{
		return new LoanDefinitionDef();
	}
	
	public static synchronized VoucherTemplateMFTransacs getVoucherTemplateMFTransacs() throws Exception
	{
		return new VoucherTemplateMFTransacs();
	}
	
	public static synchronized WorkFlowTransitionAdmin getWorkFlowTransitionAdmin() throws Exception
	{
		return new WorkFlowTransitionAdmin();
	}
	
	public static synchronized WorkFlowEventTypeAdmin getWorkFlowEventTypeAdmin() throws Exception
	{
		return new WorkFlowEventTypeAdmin();
	}
	
	public static synchronized WorkFlowEventActionAdmin getWorkFlowEventActionAdmin() throws Exception
	{
		return new WorkFlowEventActionAdmin();
	}
	
	public static synchronized WorkFlowDefTransitionAdmin getWorkFlowDefTransitionAdmin() throws Exception
	{
		return new WorkFlowDefTransitionAdmin();
	}

		
}
