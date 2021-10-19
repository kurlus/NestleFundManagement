package workflow.loans;

public interface LoanWfQueries 
{
	
	//- database attributes
	String logID = "log_id";
	String wfID = "workflow_id";
	String wfName = "workflow_name";	
	String wfActionID = "action_id";
	String loanApplyID = "apply_id";
	String wfEventType = "event_type";
	String wfSequence = "workflow_seq";
	String wfDetID = "workflow_det_id";	
	String wfActionName = "action_name";		
	String wfMemberEmail = "member_email";
	String wfToTransition = "to_transition";
	String historyEmployeeID = "employee_id";
	String wfEventTypeName = "event_type_name";
	String wfFromTransition = "from_transition";
	String transacHistoryCnt = "transacHistoryCnt";
	
	String historyIdParam = "history_id";
	String historySourceID = "source_id";
	
	
	enum events {Loan}
	enum transitionCode {HR, Member, Trustee, TrusteeII, Treasurer};
	String[] transitionCodeArray = {"HR", "Member", "Trustee", "TrusteeII", "Treasurer"}; 

	enum eventActions {Pending, Approved, Rejected, ReWork, Payment};
	String[] eventActionsArray =  {"Pending", "Approved", "Rejected", "ReWork", "Payment"};
	
	//- workflow attribs.
	String wfException = "work-flow exception";
	String wfTransactionException = "work-flow transaction manager exception";
	
	//- Queries
	String getNextWfID = " select isnull(max(workflow_id), 0)+1 workflow_id from workflowmf ";
	String getNextWfDetID = " select isnull(max(workflow_det_id), 0)+1 workflow_det_id from workflowdet ";
	String getNextWfHistoryID = " select isnull(max(history_id), 0)+1 workflow_his_id from workflowhistory ";

	String getWfEventTypeID = " select event_type from workfloweventtype where event_type_name = :event_type_name ";
	String getWfActionID = " select action_id from workfloweventaction where action_name =  :action_name";	

	String getWfIDByEventAndAction = " select workflow_id from workflowmf where event_type = :event_type and "
			+ " action_id = :action_id ";
	String getWfTransacByIDEventAndAction = " select workflow_id, workflow_name, event_type, action_id, log_id from workflowmf "
			+ " where workflow_id = ? and event_type = ? and action_id = ? ";

	String getWfDetIDByFromToAndWfID = " select workflow_det_id from workflowdet where workflow_id = :workflow_id and "
			+ " from_transition = :from_transition and to_transition = :to_transition ";
	String getWfDetTransacByFromToAndWfID = " select workflow_det_id, workflow_id, from_transition, to_transition, workflow_seq, "
			+ " member_email from workflowdet where from_transition = ? and to_transition = ? and workflow_id = ? ";
	String isTransacHistoryExists = " select count(*) transacHistoryCnt from workflowhistory h where workflow_det_id = "
			+ " :workflow_det_id and workflow_id = :workflow_id and event_type = :event_type and action_id = :action_id "
			+ " and source_id = :source_id and employee_id = :employee_id ";

	String getLoanApplyIDs =  " select distinct source_id apply_id from  workflowhistory where event_type = :event_type and "
			+ " workflow_det_id != :workflow_det_id and history_date is not null ";
	String wfResultSet = " select wfh.history_id, wfh.source_id, wfh.workflow_det_id, wfh.workflow_id, wfmf.workflow_name, "
			+ " wfe.event_type, wfac.action_name, wfh.employee_id, wfdet.workflow_seq, wfdet.from_transition, wfdet.to_transition "
			+ " from workflowhistory wfh, workflowmf wfmf, workflowdet wfdet, workfloweventaction wfac, workfloweventtype wfe where "
			+ " wfh.workflow_id = wfmf.workflow_id and	wfmf.workflow_id = wfdet.workflow_id and wfh.workflow_det_id = "
			+ " wfdet.workflow_det_id and wfh.action_id = wfmf.action_id and wfmf.action_id = wfac.action_id and wfh.event_type = "
			+ " wfmf.event_type and wfmf.event_type = wfe.event_type and wfh.source_id = :source_id order by history_id desc ";
	
	String insertWfMaster = " insert into workflowmf (workflow_id, workflow_name, event_type, action_id, log_id) "
			+ "values (?, ?, ?, ?, ?) ";
	String insertWfDetail = " insert into workflowdet (workflow_det_id, workflow_id, from_transition, to_transition, "
			+ "workflow_seq, member_email) values (?, ?, ?, ?, ?, ?) ";
	String insertWfHistory = " insert into workflowhistory(history_id, workflow_det_id, workflow_id, event_type, "
			+ "action_id, source_id, employee_id, history_date, log_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	String updateWfMf = " update workflowmf set workflow_name = ?, event_type = ?, action_id = ?, log_id = ? where "
			+ "workflow_id = ? ";
	String updateWfDetail =	 " update workflowdet set from_transition = ?, to_transition = ?, workflow_seq = ?, "
			+ " member_email = ? where workflow_det_id = ? and workflow_id = ? ";
	
}
