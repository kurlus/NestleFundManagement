package database;


public interface DefQueries 
{
	String glParentDataQuery = " With n(gl_mf_code, gl_mf_description, gl_mf_level, gl_gen_detail, gl_family_id) AS "+
								" (select g.gl_mf_code,g.gl_mf_description,g.gl_mf_level,g.gl_gen_detail, g.gl_family_id from GLMF g "+
								" where g.gl_mf_parent is null or g.gl_mf_parent='' "+
								" union all"+
								" select s.gl_mf_code, s.gl_mf_description, s.gl_mf_level, s.gl_gen_detail, s.gl_family_id from GLMF s, n "+
								" where s.gl_mf_parent = n.gl_mf_code and s.gl_gen_detail='G')"+
								" select gl_mf_description, gl_mf_code, gl_mf_level, gl_gen_detail, gl_family_id from n order by gl_mf_code ";
	
	String getGlmfCode = "select max(g.gl_mf_code) gl_mf_code from GLMF g where g.gl_mf_parent like :gl_mf_code";
	String insertGlGlmf = "insert into GLMF(gl_mf_code, gl_mf_description, gl_mf_family, gl_mf_parent, gl_mf_level, "+
						  " gl_gen_detail, gl_active, post, log_id) "+
						  " values(:gl_mf_code, :gl_mf_description, :gl_mf_family, :gl_mf_parent, :gl_mf_level, :gl_gen_detail, "+
						  " :gl_active, :post, :log_id)";
	String findGlSlMappingByGlSlCode = "from GlSlMapping where gl_mf_code=:gl_mf_code and post=1";
	String getBookTypeMaxSeqQuery = "select TOP(1) gl_book_type from GLBOOKTYPE where gl_form_type=:gl_form_type ORDER by gl_book_type desc";
	String getMenuParentDesc = "select upper(menu_description) from appmenus where menu_id = (select parent_menu_id from appmenus where menu_id=:menu_id)";
	String findCityQuery = "from City";
	String findBankQuery = "from Bank";
	String findPayRollQuery = "from PayRoll";
	String findPayRollLoanQuery = "from PayRollLoan";
	String findPayRollPFContribQuery = "from PayRollPFContribution";
	String findPayRollPNContribQuery = "from PayRollPNContribution";
	String findGlmfQuery = "from Glmf";
	String findFundQuery = "from Fund";
	String findGlmfLevel7Query = "from Glmf where gl_mf_level=7";
	String findRolesQuery = "from Roles";
	String findWorkFlowQuery = "from WorkFlow";
	String findTemplateQuery = "from Template";   
	String findEmployeeTypeQuery = "from EmployeeType"; 
	String findEmployeeFundOpeningQuery = "from EmployeeFundOpening";
	String findWorkFlowEventsQuery = "from WorkFlowEvents";
	String findWorkFlowHistoryQuery = "from WorkFlowHistory";
	String findRolesByRoleIDQuery = "from Roles where role_type_id=:role_id";
	String findBranchQuery = "from GlBranch";
	String findCountryQuery = "from Country";
	String findEmployeeQuery = "from Employee";
	String findLoanTypeQuery = "from LoanType";
	String findGlSlCodesQuery = "from GlSlCode";
	String findRoleTypeQuery = "from RoleType";
	String findLoaderRunQuery = "from LoaderRun where loader_id=:loader_id";
	String findCurrencyQuery = "from GlCurrency";
	String findLoanApplyQuery = "from LoanApply";
	String findCostCentreQuery = "from CostCentre";
	String findRoleDetailQuery = "from RoleDetail";
	String findLoaderInfoQuery = "from LoaderInfo";
	String findBankBranchQuery = "from GlBankBranch";
	String findGlFamilyTypeQuery = "from GlFamilyType";
	String findFinanPeriodQuery = "from GlFinancialPeriod";
	String findLoanSettlementQuery = "from LoanSettlement";
	String findLoanInterstRateQuery = "from LoanInterestRate";
	String findGlFinancialYearsQuery = "from GlFinancialYears";
	String findWorkFlowEventActionQuery = "from WorkFlowEventAction";
	String findWorkFlowEventTypeQuery = "from WorkFlowEventType";
	
	String findGlVoucherTemplateMfQuery = "from HGlVoucherTemplateMf";
	String findBankPaymentGlVoucherQuery = "from HBankPaymentGlVoucher where gl_form_type='GBP'";
	String findCashPaymentGlVoucherQuery = "from HCashPaymentGlVoucher where gl_form_type='GCP'";
	String findBankReceiptGlVoucherQuery = "from HBankReceiptGlVoucher where gl_form_type='GBR'";
	String findCashReceiptGlVoucherQuery = "from HCashReceiptGlVoucher where gl_form_type='GCR'";
	String findJournalGlVoucherQuery = "from HJournalGlVoucher  where gl_form_type='GJV'";
	
	
	//Queries for Posted Records only
	String findFundPostedQuery = "from Fund where post=1";
	String findCityPostedQuery = "from City where post=1";
	String findBankPostedQuery = "from Bank where post=1";
	String findPayRollPostedQuery = "from PayRoll where post=1";
	String findGlmfPostedQuery = "from Glmf where post=1";
	String findRolesPostedQuery = "from Roles where post=1";
	String findWorkFlowPostedQuery = "from WorkFlow where post=1";
	String findTemplatePostedQuery = "from Template where post=1";   
	String findEmployeeTypePostedQuery = "from EmployeeType where post=1";
	String findWorkFlowEventsPostedQuery = "from WorkFlowEvents where post=1";
	String findRolesByRoleIDPostedQuery = "from Roles where role_type_id=:role_id and post=1";
	String findBranchPostedQuery = "from GlBranch where post=1";
	String findCountryPostedQuery = "from Country where post=1";
	String findEmployeePostedQuery = "from Employee where post=1";
	String findLoanTypePostedQuery = "from LoanType where post=1";
	String findRoleTypePostedQuery = "from RoleType where post=1";
	String findGlSlCodesPostedQuery = "from GlSlCode where post=1";
	String findCurrencyPostedQuery = "from GlCurrency where post=1";
	String findLoanApplyPostedQuery = "from LoanApply where post=1";
	String findCostCentrePostedQuery = "from CostCentre where post=1";
	String findRoleDetailPostedQuery = "from RoleDetail where post=1";
	String findLoaderInfoPostedQuery = "from LoaderInfo where post=1";
	String findBankBranchPostedQuery = "from GlBankBranch where post=1";
	String findGlFamilyTypePostedQuery = "from GlFamilyType where post=1";
	String findFinanPeriodPostedQuery = "from GlFinancialPeriod where post=1";
	String findLoanSettlementPostedQuery = "from LoanSettlement where post=1";
	String findWorkFlowHistoryPostedQuery = "from WorkFlowHistory where post=1";
	String findGlFinancialYearsPostedQuery = "from GlFinancialYears where post=1";
	String findLoanInterestRatePostedQuery = "from LoanInterestRate where post=1";
	String findWorkFlowEventActionPostedQuery = "from WorkFlowEventAction where post=1";
	String findWorkFlowEventTypePostedQuery = "from WorkFlowEventType where post=1";
	String findGlSlTypePosted = "from GlSlType where post=1";
	String findGlBookTypePosted = "from GlBookType where post=1";
	String findGlFormTypePosted = "from GlFormType where post=1";
	String findGlSlMappingPosted = "from GlSlMapping where post=1";
	
	
	String findGlSlType = "from GlSlType";
	String findGlBookType = "from GlBookType";
	String findGlFormType = "from GlFormType";
	String findGlSlMapping = "from GlSlMapping";
	String findImportEmployee = "from ImportEmployee where flag_import=0 and batch_id=:batch_id ";
	String findImportLoanApply = "from ImportLoanApply where flag_import=0 and batch_id=:batch_id ";
	String findImportLoanPayments = "from ImportLoanPayment where flag_import=0 and batch_id=:batch_id ";
	String findImportPayroll = "from ImportPayroll where flag_import=0 and batch_id=:batch_id ";
	
	String payrollQuery = "from PayrollLoan as prollLoan INNER JOIN prollLoan.payroll";
	String findWfMfRelationQuery = "from HWfDetail as hWfMfDetial INNER JOIN hWfMfDetial.workFlowMF";
	String findWfMfQuery = "from HWfMF";
	String findWfDetQuery = "from HWfDetail where workflow_id=";
	String findLoanDefQuery = "from HLoanDef";
	String findWFEventType = "from WfEventType";
	String findWFEventAction = "from WfEventAction";
	String findWFEventTransition = "from WfTransition";
	String findGlVoucherTemplateType = "from GlVoucherTemplateType";
	
	
	
	
	
}
