package app;

import utilities.AppUtility;
import utilities.AppConstants;
import utilities.InstanceFactory;

import java.util.logging.Level;
import java.util.concurrent.Callable;

import dml.admin.WorkFlowMFAdminDML;
import app.beans.LoginEmployee;
import app.def.GlSlMappingDef;
import app.def.LoanDefinitionDef;
import app.admin.LoginEmployeeAdmin;
import app.admin.WorkFlowDefTransitionAdmin;
import app.admin.WorkFlowEventActionAdmin;
import app.admin.WorkFlowEventTypeAdmin;
import app.admin.WorkFlowTransitionAdmin;
import app.transacs.EmployeeTransacs;
import app.transacs.LoaderRunTransacs;
import app.transacs.LoanPaymentTransacs;
import app.transacs.LoanProcessTransacs;
import app.transacs.LoanApprovalTransacs;
import app.transacs.ImportPayrollTransacs;
import app.transacs.VoucherTemplateMFTransacs;
import app.transacs.VoucherTemplateTypeTransacs;
import app.admin.EmployeeRoleMappingAdmin;
import app.transacs.ImportEmployeeTransacs;
import app.transacs.ImportLoanApplyTransacs;
import app.transacs.ImportLoanPaymentTransacs;
import app.transacs.LoanPaymentScheduleTransacs;

public class DefRequestHandler implements Callable<DefResultSet> 
{

	public DefRequestHandler(LoginEmployee loginEmployee)
	{
		this.loginEmployee = loginEmployee;
	}

	@Override
	public DefResultSet call() throws Exception 
	{
		
		switch(loginEmployee.getReqIdentifier())
		{
			case AppConstants.employee:
				AppUtility.logger.log(Level.INFO, "load definition Request for Employee");
				EmployeeTransacs employeeDef = InstanceFactory.getEmployeeTransacsFactory(); 
				defResultSet = employeeDef.loadDefinitionData(loginEmployee);
				break;
				
			case AppConstants.employeeRolesMapping:
				AppUtility.logger.log(Level.INFO, "load definition Request for Employee Role mapping");
				EmployeeRoleMappingAdmin roleDef = InstanceFactory.getEmployeeRoleMappingAdminFactory();
				defResultSet = roleDef.loadDefinitionData(loginEmployee);
				break; 
				
			case AppConstants.importEmployee:
				AppUtility.logger.log(Level.INFO, "load definition Request for import employee");
				importEmployeeDefinition = InstanceFactory.getImportEmployeeTransacsFactory(); 
				defResultSet = importEmployeeDefinition.loadDefinitionData(loginEmployee);
				break;
				
			case AppConstants.importLoanApply:
				AppUtility.logger.log(Level.INFO, "load definition Request for import loan apply");
				importLoanApplyDefinition = InstanceFactory.getImportLoanApplyTransacsFactory(); 
				defResultSet = importLoanApplyDefinition.loadDefinitionData(loginEmployee);
				break;
				
			case AppConstants.importLoanPayments:
				AppUtility.logger.log(Level.INFO, "load definition Request for import loan payments");
				importLoanPaymentDefinition = InstanceFactory.getImportLoanPaymentTransacsFactory(); 
				defResultSet = importLoanPaymentDefinition.loadDefinitionData(loginEmployee);
				break;
				
			case AppConstants.importPayroll:
				AppUtility.logger.log(Level.INFO, "load definition Request for import Payroll");
				importPayrollDefinition = InstanceFactory.getImportPayrollTransacsFactory(); 
				defResultSet = importPayrollDefinition.loadDefinitionData(loginEmployee);
				break;
				
			case AppConstants.loanPayemtSchedule:
				AppUtility.logger.log(Level.INFO, "load definition Request for Loan Payment Schedule");
				LoanPaymentScheduleTransacs loanSchedule = InstanceFactory.getLoanPaymentScheduleTransacsFactory();
				defResultSet = loanSchedule.loadDefinitionData(loginEmployee);
				break;
				
			case AppConstants.loanProcess:
				AppUtility.logger.log(Level.INFO, "load definition Request for Loan Payment Schedule");
				LoanProcessTransacs loanDefinition = InstanceFactory.getLoanProcessTransacsFactory();
				defResultSet = loanDefinition.loadDefinitionData(loginEmployee);
				break;
				
			case AppConstants.loanPayment:
				AppUtility.logger.log(Level.INFO, "load definition Request for Loan Payment ");
				LoanPaymentTransacs loanPayment = InstanceFactory.getLoanPaymentTransacsFactory();
				defResultSet = loanPayment.loadDefinitionData(loginEmployee);
				break;
				
			case AppConstants.loaderRun:
				AppUtility.logger.log(Level.INFO, "load definition Request for Loader Run");
				loaderRunDef = InstanceFactory.getLoaderRunTransacsFactory(); 
				defResultSet = loaderRunDef.loadDefinitionData(loginEmployee);
				break;
				
			case AppConstants.loanApproval:
				AppUtility.logger.log(Level.INFO, "load definition Request for Loan Approval");
				LoanApprovalTransacs loanAppExecutor = InstanceFactory.getLoanApprovalTransacsFactory(); 
				defResultSet = loanAppExecutor.loadDefinitionData(loginEmployee);
				break;
				
			case  AppConstants.loginEmployee:
				loginEmployeeAdmin = InstanceFactory.getLoginEmployeeAdminFactory();
				defResultSet = loginEmployeeAdmin.loadDefinitionData(loginEmployee);
				break;
			case  AppConstants.glVoucherTemplateType:
				voucherTemplateTypeTransacs = InstanceFactory.getVoucherTemplateTypeTransacs();
				defResultSet = voucherTemplateTypeTransacs.loadDefinitionData(loginEmployee);
				break;
			case  AppConstants.loanDef:
				loanDefinitionDef = InstanceFactory.getLoanDefinitionDef();
				defResultSet = loanDefinitionDef.loadDefinitionData(loginEmployee);
				break;
			case  AppConstants.glVocuherTemplateMf:
				voucherTemplateMFTransacs = InstanceFactory.getVoucherTemplateMFTransacs();
				defResultSet = voucherTemplateMFTransacs.loadDefinitionData(loginEmployee);
				break;
			case  AppConstants.wfEventTransition:
				workFlowTransitionAdmin = InstanceFactory.getWorkFlowTransitionAdmin();
				defResultSet = workFlowTransitionAdmin.loadDefinitionData(loginEmployee);
				break;
			case  AppConstants.wfEventAction:
				workFlowEventActionAdmin = InstanceFactory.getWorkFlowEventActionAdmin();
				defResultSet = workFlowEventActionAdmin.loadDefinitionData(loginEmployee);
				break;			
			case  AppConstants.wfEventType:
				workFlowEventTypeAdmin = InstanceFactory.getWorkFlowEventTypeAdmin();
				defResultSet = workFlowEventTypeAdmin.loadDefinitionData(loginEmployee);
				break;
			case  AppConstants.wfMF:
				workFlowMfAdmin = InstanceFactory.getWorkFlowDefTransitionAdmin();
				defResultSet = workFlowMfAdmin.loadDefinitionData(loginEmployee);
				break; 
				
			default:
				DefinitionLoader defLoader = (DefinitionLoader) AppUtility.getWebApplicationContext().getBean(AppConstants.definitionLoader); 
				defLoader.loadDefinitions(loginEmployee);
				defResultSet = defLoader.getDefResultSet();
				break;
		}
		
		return defResultSet;
		
	}
	
	private DefResultSet defResultSet;
	private LoginEmployee loginEmployee;
	private LoaderRunTransacs loaderRunDef;
	private LoanDefinitionDef loanDefinitionDef;
	private LoginEmployeeAdmin loginEmployeeAdmin;
	private WorkFlowDefTransitionAdmin workFlowMfAdmin;
	private WorkFlowEventTypeAdmin workFlowEventTypeAdmin;
	private ImportPayrollTransacs importPayrollDefinition;
	private ImportEmployeeTransacs importEmployeeDefinition;
	private WorkFlowTransitionAdmin workFlowTransitionAdmin;
	private WorkFlowEventActionAdmin workFlowEventActionAdmin;
	private ImportLoanApplyTransacs importLoanApplyDefinition;
	private ImportLoanPaymentTransacs importLoanPaymentDefinition;
	private VoucherTemplateTypeTransacs voucherTemplateTypeTransacs;
	private VoucherTemplateMFTransacs voucherTemplateMFTransacs;
}
