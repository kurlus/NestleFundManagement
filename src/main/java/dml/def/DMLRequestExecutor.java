package dml.def;

import java.util.logging.Level;
import java.util.concurrent.Callable;

import app.admin.WorkFlowEventActionAdmin;
import app.admin.WorkFlowEventTypeAdmin;
import app.beans.WfMF;
import database.DMLBase;
import utilities.AppUtility;
import utilities.AppConstants;
import database.HDMLOperations;
import dml.admin.UserRoleAdminDML;
import dml.admin.WorkFlowEventActionAdminDML;
import dml.admin.WorkFlowEventTypeAdminDML;
import dml.admin.WorkFlowMFAdminDML;
import dml.admin.WorkFlowTransitionAdminDML;
import dml.transacs.EmployeeTransacsDML;
import dml.admin.EmployeeRoleMappAdminDML;
import dml.transacs.GlJournalTransacsDML;
import dml.transacs.LoanPaymentTransacsDML;
import dml.transacs.LoanApprovalTransacsDML;
import dml.transacs.ImportPayrollTransacsDML;
import dml.transacs.GLBankPaymentTransacsDML;
import dml.transacs.GLBankReceiptTransacsDML;
import dml.transacs.GLCashPaymentTransacsDML;
import dml.transacs.GLCashReceiptTransacsDML;
import dml.transacs.LoanSettlementTransacsDML;
import dml.transacs.ImportEmployeeTransacsDML;
import dml.transacs.ImportLoanApplyTransacsDML;
import dml.transacs.ImportLoanPaymentTransacsDML;
import dml.transacs.LoanApplyBalLimitTransacsDML;
import dml.transacs.VoucherTemplateMFTransacsDML;
import dml.transacs.VoucherTemplateTypeTransacsDML;

public class DMLRequestExecutor implements Callable<DMLTransWrapper>
{
	public DMLRequestExecutor(DMLTransWrapper dmlTransWrapper, String oprType, String requestIdentifier) 
	{
		this.oprType = oprType;
		this.requestIdentifier = requestIdentifier;
		this.dmlTransWrapper = dmlTransWrapper;
	}

	@Override
	public DMLTransWrapper call() throws Exception 
	{
		try
		{
			long logID = 0;
			String remarks = "going to "+oprType+ " " ;
			HDMLOperations hdml = (HDMLOperations) AppUtility.getWebApplicationContext().getBean(AppConstants.HDMLFactory); 
			
			switch(requestIdentifier)
			{
			case AppConstants.glJournal:
				AppUtility.logger.log(Level.INFO, "DML Request for GL Journal");
				glJournalDMLExecutor = (GlJournalTransacsDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLGLJournalFactory); 
				dmlTransWrapper = glJournalDMLExecutor.dmlDataExecutor(dmlTransWrapper.getGlJournalVoucher(), oprType);
				break;
			case AppConstants.glBankPayment:
				AppUtility.logger.log(Level.INFO, "DML Request for GL Bank Payment");
				glBankPaymentDMLExecutor = (GLBankPaymentTransacsDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLGLBankPaymentFactory); 
				dmlTransWrapper = glBankPaymentDMLExecutor.dmlDataExecutor(dmlTransWrapper.getGlBankPaymentVoucher(), oprType);
				break;
			case AppConstants.glBankReceipt:
				AppUtility.logger.log(Level.INFO, "DML Request for GL Bank Receipt");
				glBankReceiptDMLExecutor = (GLBankReceiptTransacsDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLGLBankReceiptFactory); 
				dmlTransWrapper = glBankReceiptDMLExecutor.dmlDataExecutor(dmlTransWrapper.getGlBankReceiptVoucher(), oprType);
				break;
			case AppConstants.glCashPayment:
				AppUtility.logger.log(Level.INFO, "DML Request for GL Cash Payment");
				glCashPaymentTransacDMLExecutor = (GLCashPaymentTransacsDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLGLCashPaymentFactory); 
				dmlTransWrapper = glCashPaymentTransacDMLExecutor.dmlDataExecutor(dmlTransWrapper.getGlCashPaymentVoucher(), oprType);
				break;
			case AppConstants.glCashReceipt:
				AppUtility.logger.log(Level.INFO, "DML Request for GL Cash Receipt");
				glCashReceiptTransacDMLExecutor = (GLCashReceiptTransacsDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLGLCashReceiptFactory); 
				dmlTransWrapper = glCashReceiptTransacDMLExecutor.dmlDataExecutor(dmlTransWrapper.getGlCashReceiptVoucher(), oprType);
				break;		
			case AppConstants.template:
				AppUtility.logger.log(Level.INFO, "DML Request for Template");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.template));  
				dmlTransWrapper.getTemplateBean().setLogID(logID);

				templateDMLExecutor = (TemplateDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLTemplateFactory); 
				dmlTransWrapper = templateDMLExecutor.dmlDataExecutor(dmlTransWrapper.getTemplateBean(), oprType);
				break;
				
			case AppConstants.bank:
				AppUtility.logger.log(Level.INFO, "DML Request for Bank");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.bank));  
				dmlTransWrapper.getBankBean().setLogID(logID);

				bankDMLExecutor = (BankDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLBankFactory); 
				dmlTransWrapper = bankDMLExecutor.dmlDataExecutor(dmlTransWrapper.getBankBean(), oprType);
				break;
			case AppConstants.branch:
				AppUtility.logger.log(Level.INFO, "DML Request for Branch");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.branch));  
				dmlTransWrapper.getBranchBean().setLogID(logID);
				branchDMLExecutor = (BranchDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLBranchFactory); 
				dmlTransWrapper = branchDMLExecutor.dmlDataExecutor(dmlTransWrapper.getBranchBean(), oprType);
				break;
			case AppConstants.bankBranch:
				AppUtility.logger.log(Level.INFO, "DML Request for Bank Branch");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.bankBranch));  
				dmlTransWrapper.getBankBranchBean().setLogID(logID);

				bankBranchDMLExecutor = (BankBranchDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLBankBranchFactory); 
				dmlTransWrapper = bankBranchDMLExecutor.dmlDataExecutor(dmlTransWrapper.getBankBranchBean(), oprType);
				break;	
			case AppConstants.chartOfAccount:
				AppUtility.logger.log(Level.INFO, "DML Request for Chart of Account");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.chartOfAccount));  
				dmlTransWrapper.getGlmf().setLogID(logID);

				glmfDMLExecutor = (GlmfDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLGlmfFactory); 
				dmlTransWrapper = glmfDMLExecutor.dmlDataExecutor(dmlTransWrapper.getGlmf(), oprType);
				break;	
			case AppConstants.city:
				AppUtility.logger.log(Level.INFO, "DML Request for City");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.city));  
				dmlTransWrapper.getCityBean().setLogID(logID);
				cityDMLExecutor = (CityDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLCityFactory); 
				dmlTransWrapper = cityDMLExecutor.dmlDataExecutor(dmlTransWrapper.getCityBean(), oprType);
				break;
			case AppConstants.country:
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.country));  
				dmlTransWrapper.getCountryBean().setLogID(logID);
				AppUtility.logger.log(Level.INFO, "DML Request for Country");
				countryDMLExecutor = (CountryDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLCountryFactory); 
				dmlTransWrapper = countryDMLExecutor.dmlDataExecutor(dmlTransWrapper.getCountryBean(), oprType);
				break;
			case AppConstants.costCentre:
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.costCentre));  
				dmlTransWrapper.getCostCentreBean().setLogID(logID);
				AppUtility.logger.log(Level.INFO, "DML Request for Cost Centre");
				costCentreDMLExecutor = (CostCentreDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLCostCentreFactory); 
				dmlTransWrapper = costCentreDMLExecutor.dmlDataExecutor(dmlTransWrapper.getCostCentreBean(), oprType);
				break;
			case AppConstants.employee:
				AppUtility.logger.log(Level.INFO, "DML Request for Employee");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.employee));  
				dmlTransWrapper.getEmployee().setLogID(logID);
				employeeDMLExecutor = (EmployeeTransacsDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLEmployeeFactory); 
				dmlTransWrapper = employeeDMLExecutor.dmlDataExecutor(dmlTransWrapper.getEmployee(), oprType);
				break;
			case AppConstants.employeeRolesMapping:
				AppUtility.logger.log(Level.INFO, "DML Request for Employee Roles Mapping");
				
				employeeRoleMappAdminDML = (EmployeeRoleMappAdminDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLEmployeeRoleMappAdminFactory); 
				dmlTransWrapper = employeeRoleMappAdminDML.dmlDataExecutor(dmlTransWrapper, oprType);
				break;
			case AppConstants.financialPeriod :
				AppUtility.logger.log(Level.INFO, "DML Request for Financial Period");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.financialPeriod));  
				dmlTransWrapper.getGlFinancialPeriod().setLogId(logID);
				financialPeriodDMLExecutor = (FinancialPeriodDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLFinancialPeriodFactory); 
				dmlTransWrapper = financialPeriodDMLExecutor.dmlDataExecutor(dmlTransWrapper.getGlFinancialPeriod(), oprType);
				break;
			case AppConstants.financialYears:
				AppUtility.logger.log(Level.SEVERE, "DML Request for Financial Year");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.financialYears));  
				dmlTransWrapper.getGlFinancialYears().setLogId(logID);
				glFinancialYearsDMLExecuter = (FinancialYearsDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLFinancialYearsFactory); 
				dmlTransWrapper = glFinancialYearsDMLExecuter.dmlDataExecutor(dmlTransWrapper.getGlFinancialYears(), oprType);
				break;	
			case AppConstants.importEmployee:
				AppUtility.logger.log(Level.SEVERE, "DML Request for Import Employee");
				importEmployeeDML = (ImportEmployeeTransacsDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLImpEmployeeFactory); 
				dmlTransWrapper = importEmployeeDML.dmlDataExecutor(dmlTransWrapper.getImportEmployee(), oprType);
				break;
			case AppConstants.glCurrency:
				AppUtility.logger.log(Level.INFO, "DML Request for Currency");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.glCurrency));  
				dmlTransWrapper.getCurrencyBean().setLogID(logID);
				glCurrencyDefDMLExecutor = (CurrencyDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLGlCurrencyFactory); 
				dmlTransWrapper = glCurrencyDefDMLExecutor.dmlDataExecutor(dmlTransWrapper.getCurrencyBean(), oprType);
				break;
			case AppConstants.glBookType:
				AppUtility.logger.log(Level.INFO, "DML Request for Gl Book Type");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.glBookType));  
				dmlTransWrapper.getGlbookType().setLogID(logID);
				glBookTypeDefDML = (GlBookTypeDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLGLBookTypeDefFactory); 
				dmlTransWrapper = glBookTypeDefDML.dmlDataExecutor(dmlTransWrapper.getGlbookType(), oprType);
				break;
			case AppConstants.glSlType:
				AppUtility.logger.log(Level.INFO, "DML Request for GL SL Type");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.glSlType));  
				dmlTransWrapper.getGlSlType().setLogID(logID);
				glSlTypeDefDML = (GlSlTypeDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLGlSlTypeDefFactory); 
				dmlTransWrapper = glSlTypeDefDML.dmlDataExecutor(dmlTransWrapper.getGlSlType(), oprType);
				break;
			case AppConstants.glSlMapping:
				AppUtility.logger.log(Level.INFO, "DML Request for GL SL Mapping");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.glSlMapping));  
				dmlTransWrapper.getGlSlMapping().setLogID(logID);
				glSlMappingDefDML = (GlSlMappingDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLGlSlMappingDefFactory); 
				dmlTransWrapper = glSlMappingDefDML.dmlDataExecutor(dmlTransWrapper.getGlSlMapping(), oprType);
				break;
			case AppConstants.loanApproval:
				AppUtility.logger.log(Level.INFO, "DML Request for Loan Approval");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.loanApproval));  
				dmlTransWrapper.getLoanApprovalBean().setLogID(logID);
				LoanApprovalTransacsDML loanAppExecutor = (LoanApprovalTransacsDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLLoanApprovalFactory); 
				dmlTransWrapper = loanAppExecutor.dmlDataExecutor(dmlTransWrapper.getLoanApprovalBean(), oprType);
				break;
			case AppConstants.userRole:
				AppUtility.logger.log(Level.INFO, "DML Request for User Role");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.userRole));  
				dmlTransWrapper.getUserRoleBean().setLogID(logID);
				userRoleDMLExecutor = (UserRoleAdminDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLUserRoleFactory); 
				dmlTransWrapper = userRoleDMLExecutor.dmlDataExecutor(dmlTransWrapper.getUserRoleBean(), oprType);
				break;
			case AppConstants.loanType:
				AppUtility.logger.log(Level.SEVERE, "DML Request for loanType");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.loanType));  
				dmlTransWrapper.getLoanType().setLogID(logID);
				loanTypeDMLExecutor = (LoanTypeDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLLoanTypeFactory); 
				dmlTransWrapper = loanTypeDMLExecutor.dmlDataExecutor(dmlTransWrapper.getLoanType(), oprType);
				break;
			case AppConstants.loanPayment:
				AppUtility.logger.log(Level.SEVERE, "DML Request for loanPayment");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.loanPayment));  
				dmlTransWrapper.getLoanPayment().setLogID(logID);
				loanPaymentDMLExecutor = (LoanPaymentTransacsDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLLoanPaymentFactory); 
				dmlTransWrapper = loanPaymentDMLExecutor.dmlDataExecutor(dmlTransWrapper.getLoanPayment(), oprType);
				break;
			case AppConstants.importPayroll:
				AppUtility.logger.log(Level.INFO, "DML Request for Payroll Pn Contrib");
				importPayRollDMLExecutor = (ImportPayrollTransacsDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLImportPayrollTransacsFactory); 
				dmlTransWrapper = importPayRollDMLExecutor.dmlDataExecutor(dmlTransWrapper.getImportPayroll(), oprType);
				break;
			case AppConstants.loanApplyBalLimit:
				AppUtility.logger.log(Level.INFO, "Request for loanApplyBalLimit");
				loanApplyBalLimitTransacsDML = (LoanApplyBalLimitTransacsDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLLoanApplyBalLimitFactory); 
				dmlTransWrapper = loanApplyBalLimitTransacsDML.dmlDataExecutor(dmlTransWrapper.getLoanApplyBalLimit(), oprType);
				break;
			case AppConstants.importLoanApply:
				AppUtility.logger.log(Level.INFO, "DML Request for Import Loan Apply");
				importLoanApplyTransacsDML = (ImportLoanApplyTransacsDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLImportLoanApplyTransacsFactory); 
				dmlTransWrapper = importLoanApplyTransacsDML.dmlDataExecutor(dmlTransWrapper.getImportLoanApply(), oprType);
				break;
			case AppConstants.importLoanPayments:
				AppUtility.logger.log(Level.INFO, "DML Request for Import Loan Payment");
				importLoanPaymentTransacsDML = (ImportLoanPaymentTransacsDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLImportLoanPaymentTransacsFactory); 
				dmlTransWrapper = importLoanPaymentTransacsDML.dmlDataExecutor(dmlTransWrapper.getImportLoanPayment(), oprType);
				break;
			case AppConstants.employeeType:
				AppUtility.logger.log(Level.INFO, "DML Request for Employee Type");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.employeeType));  
				dmlTransWrapper.getEmployeeTypeBean().setLogID(logID);

				employeeTypeDMLExecutor = (EmployeeTypeDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLEmployeeTypeFactory); 
				dmlTransWrapper = employeeTypeDMLExecutor.dmlDataExecutor(dmlTransWrapper.getEmployeeTypeBean(), oprType);
				break;
				
			/*case AppConstants.employeeFundOpening:
				AppUtility.logger.log(Level.INFO, "DML Request for Employee Fund Opening");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.employeeFundOpening));  
				dmlTransWrapper.getEmployeeFundOpeningBean().setLogID(logID);

				employeeFundOpeningDMLExecutor = (EmployeeFundOpeningDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLEmployeeFundOpeningFactory); 
				dmlTransWrapper = employeeFundOpeningDMLExecutor.dmlDataExecutor(dmlTransWrapper.getEmployeeFundOpeningBean(), oprType);
				break;*/
			case AppConstants.glSlCode:
				AppUtility.logger.log(Level.INFO, "DML Request for GLSLCode");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.glSlCode));  
				dmlTransWrapper.getGlSlCode().setLogID(logID);
				GlSlCodeDefDML glSlCodeDefDML = (GlSlCodeDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLGlSlCodeDefFactory); 
				dmlTransWrapper = glSlCodeDefDML.dmlDataExecutor(dmlTransWrapper.getGlSlCode(), oprType);
				break;
			case AppConstants.loanInterestRate:
				AppUtility.logger.log(Level.INFO, "DML Request for GLSLCode");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.loanInterestRate));  
				dmlTransWrapper.getLoanInterestRateBean().setLogID(logID);
				LoanInterestRateDefDML loanInterestRateDefDML = (LoanInterestRateDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLLoanInterestRateFactory); 
				dmlTransWrapper = loanInterestRateDefDML.dmlDataExecutor(dmlTransWrapper.getLoanInterestRateBean(), oprType);
				break;	
			case AppConstants.glVoucherTemplateType:
				AppUtility.logger.log(Level.INFO, "DML Request for gl Voucher Template Type");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.glVoucherTemplateType));  
				dmlTransWrapper.getGlVoucherTemplateType().setLogID(logID);
				voucherTemplateTypeDMLExcecutor = (VoucherTemplateTypeTransacsDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLVoucherTemplateTypeFactory); 
				dmlTransWrapper = voucherTemplateTypeDMLExcecutor.dmlDataExecutor(dmlTransWrapper.getGlVoucherTemplateType(), oprType);
				break;
			case AppConstants.glVocuherTemplateMf:
				AppUtility.logger.log(Level.INFO, "DML Request for gl Voucher Template MF");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.glVocuherTemplateMf));  
				dmlTransWrapper.getGlVoucherTemplateMfBean().setLogID(logID);
				voucherTemplateMFTransacsDMLExecutor = (VoucherTemplateMFTransacsDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLVoucherTemplateMFFactory); 
				dmlTransWrapper = voucherTemplateMFTransacsDMLExecutor.dmlDataExecutor(dmlTransWrapper.getGlVoucherTemplateMfBean(), oprType);
				break; 
			case AppConstants.loanDef:
				AppUtility.logger.log(Level.INFO, "DML Request for lOAN def");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.loanDef));  
				dmlTransWrapper.getLoanDefBean().setLogID(logID);
				loanDefDMLExecutor = (LoanDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLLoanDefFactory); 
				dmlTransWrapper = loanDefDMLExecutor.dmlDataExecutor(dmlTransWrapper.getLoanDefBean(), oprType);
				break; 
			case AppConstants.wfEventTransition:
				AppUtility.logger.log(Level.INFO, "DML Request for work flow transition");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.wfEventTransition));  
				dmlTransWrapper.getWfTransitionBean().setLogID(logID);
				wfTransitionDMLExecutor = (WorkFlowTransitionAdminDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLWfTransitionFactory); 
				dmlTransWrapper = wfTransitionDMLExecutor.dmlDataExecutor(dmlTransWrapper.getWfTransitionBean(), oprType);
				break; 
			case AppConstants.wfEventType:
				AppUtility.logger.log(Level.INFO, "DML Request for work flow event type");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.wfEventType));  
				dmlTransWrapper.getWfEventType().setLogID(logID);
				wfEventTypeDMLExecutor = (WorkFlowEventTypeAdminDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLWfEventTypeFactory); 
				dmlTransWrapper = wfEventTypeDMLExecutor.dmlDataExecutor(dmlTransWrapper.getWfEventType(), oprType);
				break; 
			case AppConstants.wfEventAction:
				AppUtility.logger.log(Level.INFO, "DML Request for work flow event action");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.wfEventAction));  
				dmlTransWrapper.getWfEventAction().setLogID(logID);
				wfEventActionDMLExecutor = (WorkFlowEventActionAdminDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLWfEventActionFactory); 
				dmlTransWrapper = wfEventActionDMLExecutor.dmlDataExecutor(dmlTransWrapper.getWfEventAction(), oprType);
				break; 
			case AppConstants.wfMF:
				AppUtility.logger.log(Level.INFO, "DML Request for work flow MF transitions");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee() , oprType, remarks + AppConstants.wfMF));  
				dmlTransWrapper.getWfMFBean().setLogID(logID);
				workFlowMFAdminDMLExecutor = (WorkFlowMFAdminDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLWfMfAdminFactory); 
				dmlTransWrapper = workFlowMFAdminDMLExecutor.dmlDataExecutor(dmlTransWrapper.getWfMFBean(), oprType);
				break;
			case AppConstants.loanSettlement:
				AppUtility.logger.log(Level.INFO, "DML Request for loanSettlement");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee(), oprType, remarks + AppConstants.loanSettlement));
				dmlTransWrapper.getLoanSettlement().setLogID(logID);
				LoanSettlementTransacsDML loanSettlementTransacsDML = (LoanSettlementTransacsDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLLoanSettlementFactory);
				dmlTransWrapper = loanSettlementTransacsDML.dmlDataExecutor(dmlTransWrapper.getLoanSettlement(), oprType);
				break;
			case AppConstants.glFormType:
				AppUtility.logger.log(Level.INFO, "DML Request for Gl Form Type");
				logID = hdml.transactionLogInstance(DMLBase.populateTransLog(dmlTransWrapper.getLoginEmployee(), oprType, remarks + AppConstants.glFormType));
				dmlTransWrapper.getGlFormType().setLogID(logID);
				glFormTypeDefDML = (GlFormTypeDefDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLGLFormTypeDefFactory);
				dmlTransWrapper = glFormTypeDefDML.dmlDataExecutor(dmlTransWrapper.getGlFormType(), oprType);
				break;
			case AppConstants.generalRequest:
				AppUtility.logger.log(Level.INFO, "Request for GENERAL PROCEDURE CALL");
				generalRequestDML = (GeneralRequestDML) AppUtility.getWebApplicationContext().getBean(AppConstants.DMLGeneralRequestFactory);
				dmlTransWrapper = generalRequestDML.dmlDataExecutor(dmlTransWrapper.getGeneralRequestBean(), oprType);
				break;
			default:
				AppUtility.logger.log(Level.SEVERE, "No request identifier is defined");
			}
		}
		catch(Exception ex)
		{
			AppUtility.logger.log(Level.SEVERE, ex.getMessage());
			throw ex;
		}
		return dmlTransWrapper;
	}

	public DMLTransWrapper  getDMLTransWrapper() 
	{
		return this.dmlTransWrapper;
	}

	public void setDMLTransWrapper(DMLTransWrapper dMLTransWrapper) 
	{
		this.dmlTransWrapper = dMLTransWrapper;
	} 


	private String oprType;
	
	private String requestIdentifier;
	private BankDefDML bankDMLExecutor;
	private CityDefDML cityDMLExecutor;
	private GlmfDefDML glmfDMLExecutor;
	private LoanDefDML loanDefDMLExecutor;
	private GlSlTypeDefDML glSlTypeDefDML;
	private BranchDefDML branchDMLExecutor;
	private DMLTransWrapper dmlTransWrapper;
	private CountryDefDML countryDMLExecutor;
	private GlBookTypeDefDML glBookTypeDefDML;
	private GlFormTypeDefDML glFormTypeDefDML;
	private TemplateDefDML templateDMLExecutor;
	private LoanTypeDefDML loanTypeDMLExecutor;
	private GlSlMappingDefDML glSlMappingDefDML;
	private GeneralRequestDML generalRequestDML;
	private UserRoleAdminDML userRoleDMLExecutor;
	private BankBranchDefDML bankBranchDMLExecutor;
	private CostCentreDefDML costCentreDMLExecutor;
	private EmployeeTransacsDML employeeDMLExecutor;
	private CurrencyDefDML glCurrencyDefDMLExecutor;
	private GlJournalTransacsDML glJournalDMLExecutor;
	private EmployeeTypeDefDML employeeTypeDMLExecutor; 
	//private EmployeeFundOpeningDefDML employeeFundOpeningDMLExecutor; 
	private ImportEmployeeTransacsDML importEmployeeDML;
	private LoanPaymentTransacsDML loanPaymentDMLExecutor;
	private WorkFlowMFAdminDML workFlowMFAdminDMLExecutor;
	private WorkFlowEventTypeAdminDML wfEventTypeDMLExecutor;
	private FinancialYearsDefDML glFinancialYearsDMLExecuter;
	private FinancialPeriodDefDML financialPeriodDMLExecutor;
	private EmployeeRoleMappAdminDML employeeRoleMappAdminDML;	
	private GLBankPaymentTransacsDML glBankPaymentDMLExecutor;
	private ImportPayrollTransacsDML importPayRollDMLExecutor;
	private GLBankReceiptTransacsDML glBankReceiptDMLExecutor;
	private WorkFlowTransitionAdminDML wfTransitionDMLExecutor;
	private WorkFlowEventActionAdminDML wfEventActionDMLExecutor;
	private ImportLoanApplyTransacsDML importLoanApplyTransacsDML;
	private GLCashPaymentTransacsDML glCashPaymentTransacDMLExecutor;
	private GLCashReceiptTransacsDML glCashReceiptTransacDMLExecutor;
	private LoanApplyBalLimitTransacsDML loanApplyBalLimitTransacsDML;	
	private ImportLoanPaymentTransacsDML importLoanPaymentTransacsDML;
	private VoucherTemplateTypeTransacsDML voucherTemplateTypeDMLExcecutor;
	private VoucherTemplateMFTransacsDML voucherTemplateMFTransacsDMLExecutor ;
	
}
