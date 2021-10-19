package workflow.loans;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import app.beans.WfDetail;
import app.beans.WfHistory;
import database.JDBCTemplateFactory;

public class WfEventActionResultSet 
{
	public WfEventActionResultSet()
	{
	}

	public synchronized Map<String, Vector<WfHistory>> getEventActionResultSet(String roleType, String event, String employeeID) throws Exception
	{
		if (StringUtils.isEmpty(roleType)) throw new NullPointerException(" Role type is null ........");

		WfDetail transitionDet = null;
		List <Map<String, Object>> loanApplyIDs = null;

		/*
		 *  1. workflow id that has event loan and action payment
		 *  2. workflow detail id that has workflow-id mentioned at serial 1 and from_transition = 'HR'
		 *     and to_transition = 'Treasurer'
		 *  3. compute list for all loan apply-ids that have wf detail-id mentioned at serial 2    
		 */
		TransactionStatus status = null;
		TransactionDefinition def = null;
		PlatformTransactionManager transacManager = null;		

		try
		{
			def = new DefaultTransactionDefinition();
			transacManager = JDBCTemplateFactory.getDSTransactionManager();
			status = transacManager.getTransaction(def);

			int eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.Payment.toString());
			Long wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
			transitionDet = LoanWfUtility.getWfDetailTransac(LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.Treasurer.toString(), wfID);

			loanApplyIDs = LoanWfUtility.getDistinctApplyIDsNotMarkedForPayment(event, transitionDet.getWfDetailID());
			transacManager.commit(status);
		}
		catch(Exception exception)
		{
			transacManager.rollback(status);
			throw new TransactionException("MemberHREventLoanActionPending", exception);
		}

		if ((loanApplyIDs == null) || (loanApplyIDs.size() == zero))
			throw new NullPointerException(" no loan apply-ids found for "+ roleType.trim()+"......");

		if (roleType.trim().equalsIgnoreCase(LoanWfQueries.transitionCode.HR.toString()))
			return getHRBucketsForLoanIDsAgainstEventActions(loanApplyIDs, event, employeeID);
		else if (roleType.trim().equalsIgnoreCase(LoanWfQueries.transitionCode.Trustee.toString()))
			return getTrusteeBucketsForLoanIDsAgainstEventActions(loanApplyIDs, event, employeeID);
		else
			throw new NullPointerException(" Not valid role type..........");
	}	

	// HR buckets
	Map<String, Vector<WfHistory>> getHRBucketsForLoanIDsAgainstEventActions(List <Map<String, Object>> loanApplyIDs, String event, String employeeID) throws Exception
	{
		TransactionStatus status = null;
		TransactionDefinition def = null;
		PlatformTransactionManager transacManager = null;
		Map<String, Vector<WfHistory>> wfHistoryTransacs = getEventActionHashMap();

		try
		{
			def = new DefaultTransactionDefinition();
			transacManager = JDBCTemplateFactory.getDSTransactionManager();
			status = transacManager.getTransaction(def);

			WfComparator tempWorkFlow = LoanWfUtility.getWfComparatorInstance();
			Vector<WfComparator> hrWorkFlows = getValidHRTransitions(event); 

			synchronized (loanApplyIDs)
			{
				for (int cnt=zero; cnt<loanApplyIDs.size(); cnt++)
				{
					Map<String, Object> loanApplyID =  loanApplyIDs.get(cnt);
					Long sourceID = (Long) loanApplyID.get(LoanWfQueries.loanApplyID);
					List <Map<String, Object>> loanHistoryTransacs = LoanWfUtility.getLoanWfHisoryTransactions(sourceID.longValue());

					if ((loanHistoryTransacs == null) || (loanHistoryTransacs.size() == zero)) 
						continue;

					tempWorkFlow.initialize();
					//- get the last history transaction. Only transaction to be considered 
					Map<String, Object> transacsAgainstApplyID = loanHistoryTransacs.get(zero);

					long historyIDParam = (Long) transacsAgainstApplyID.get(LoanWfQueries.historyIdParam);
					long sourceIDParam = (Long) transacsAgainstApplyID.get(LoanWfQueries.historySourceID);
					long wfDetIDParam = (Long) transacsAgainstApplyID.get(LoanWfQueries.wfDetID);
					long wfIDParam = (Long) transacsAgainstApplyID.get(LoanWfQueries.wfID);
					String wfNameParam = (String) transacsAgainstApplyID.get(LoanWfQueries.wfName);
					String wfEventTypeParam = (String) transacsAgainstApplyID.get(LoanWfQueries.wfEventType);
					String wfEventActionParam = (String) transacsAgainstApplyID.get(LoanWfQueries.wfActionName);
					String wfEmployeeIDParam = (String) transacsAgainstApplyID.get(LoanWfQueries.historyEmployeeID);
					int wfSeqParam = (Integer) transacsAgainstApplyID.get(LoanWfQueries.wfSequence);
					String wfFrmTransitionParam = (String) transacsAgainstApplyID.get(LoanWfQueries.wfFromTransition);
					String wfToTransitionParam = (String) transacsAgainstApplyID.get(LoanWfQueries.wfToTransition);

					tempWorkFlow.setWfID(wfIDParam);
					tempWorkFlow.setWfDetID(wfDetIDParam);
					tempWorkFlow.setWfAction(wfEventActionParam);
					tempWorkFlow.setFromTransition(wfFrmTransitionParam);
					tempWorkFlow.setToTransition(wfToTransitionParam);

					for (int hwf=zero; hwf < hrWorkFlows.size(); hwf++)
					{
						WfComparator wfDef = hrWorkFlows.get(hwf);

						if (wfDef.compareTo(tempWorkFlow) == zero)
						{
							WfHistory historyBean = new WfHistory(wfSeqParam, wfIDParam, wfDetIDParam, sourceIDParam, historyIDParam,
									wfNameParam, wfEmployeeIDParam, wfEventTypeParam, wfEventActionParam, wfFrmTransitionParam, wfToTransitionParam);

							switch(wfDef.getWfAction())
							{
							case "Pending": 
								Vector<WfHistory> pendingHRHistoryTransac = wfHistoryTransacs.get(LoanWfQueries.eventActions.Pending.toString());
								pendingHRHistoryTransac.add(historyBean);
								break;
							case "Approved":
								Vector<WfHistory> approvedHRHistoryTransac = wfHistoryTransacs.get(LoanWfQueries.eventActions.Approved.toString());
								approvedHRHistoryTransac.add(historyBean);
								break;
							case "Rejected":
								Vector<WfHistory> rejectedHRHistoryTransac = wfHistoryTransacs.get(LoanWfQueries.eventActions.Rejected.toString());
								rejectedHRHistoryTransac.add(historyBean);
								break;
							case "ReWork":
								Vector<WfHistory> reworkHRHistoryTransac = wfHistoryTransacs.get(LoanWfQueries.eventActions.ReWork.toString());
								reworkHRHistoryTransac.add(historyBean);
								break;
							case "Payment":
								Vector<WfHistory> paymentHRHistoryTransac = wfHistoryTransacs.get(LoanWfQueries.eventActions.Payment.toString());
								paymentHRHistoryTransac.add(historyBean);
								break;
							}
							// transaction inserted, exit the work flow definition loop
							break;
						} //-if
					}//- for	

				}	

			}//- synchronized	

			transacManager.commit(status);
		}
		catch(Exception exception)
		{
			transacManager.rollback(status);
			throw new TransactionException("MemberHREventLoanActionPending", exception);
		}
		return wfHistoryTransacs;
	}

	/*
	 *  - Member-HR			pending
	 *  ****************************
	 *  - HR-Trustee		approved
	 *  - HR-TrusteeII		approved
	 *  ****************************
	 *  - HR-Treasurer      payment
	 *  ****************************
	 *  - HR-Member			rejected
	 *  - Trustee-HR		rejected
	 *  - TrusteeII-HR		rejected
	 *  ****************************
	 *  - HR-Member			re-work
	 *  - Trustee-HR		re-work
	 *  - TrusteeII-HR		re-work
	 *  ****************************
	 */
	private Vector<WfComparator> getValidHRTransitions(String event) throws Exception
	{
		Vector<WfComparator> hrWFlows = new Vector<WfComparator>();  

		// - Member-HR			pending
		int eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.Pending.toString());
		Long wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
		Long wfDetID = LoanWfUtility.getWfDetIDByWfIDAndTransitions(wfID, LoanWfQueries.transitionCode.Member.toString(), LoanWfQueries.transitionCode.HR.toString());

		WfComparator pendingMemberHR = LoanWfUtility.getWfComparatorInstance(wfID, wfDetID, 
				LoanWfQueries.eventActions.Pending.toString(), LoanWfQueries.transitionCode.Member.toString(), LoanWfQueries.transitionCode.HR.toString());
		hrWFlows.add(pendingMemberHR);

		// - HR-Trustee			approved
		eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.Approved.toString());
		wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
		wfDetID = LoanWfUtility.getWfDetIDByWfIDAndTransitions(wfID, LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.Trustee.toString());

		WfComparator approvedHRTrustee = LoanWfUtility.getWfComparatorInstance(wfID, wfDetID, 
				LoanWfQueries.eventActions.Approved.toString(), LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.Trustee.toString());
		hrWFlows.add(approvedHRTrustee);

		//- HR-TrusteeII		approved
		eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.Approved.toString());
		wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
		wfDetID = LoanWfUtility.getWfDetIDByWfIDAndTransitions(wfID, LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.TrusteeII.toString());

		WfComparator approvedHRTrusteeII = LoanWfUtility.getWfComparatorInstance(wfID, wfDetID, 
				LoanWfQueries.eventActions.Approved.toString(), LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.TrusteeII.toString());
		hrWFlows.add(approvedHRTrusteeII);

		//- HR-Treasuree		payment		
		eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.Payment.toString());
		wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
		wfDetID = LoanWfUtility.getWfDetIDByWfIDAndTransitions(wfID, LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.Treasurer.toString());

		WfComparator paymentHRTreasurer = LoanWfUtility.getWfComparatorInstance(wfID, wfDetID, 
				LoanWfQueries.eventActions.Payment.toString(), LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.Treasurer.toString());
		hrWFlows.add(paymentHRTreasurer);

		//-HR-Member			rejected
		eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.Rejected.toString());
		wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
		wfDetID = LoanWfUtility.getWfDetIDByWfIDAndTransitions(wfID, LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.Member.toString());

		WfComparator rejectedHRMember = LoanWfUtility.getWfComparatorInstance(wfID, wfDetID, 
				LoanWfQueries.eventActions.Rejected.toString(), LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.Member.toString());
		hrWFlows.add(rejectedHRMember);

		//- Trustee-HR		rejected
		eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.Rejected.toString());
		wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
		wfDetID = LoanWfUtility.getWfDetIDByWfIDAndTransitions(wfID, LoanWfQueries.transitionCode.Trustee.toString(), LoanWfQueries.transitionCode.HR.toString());

		WfComparator rejectedTrusteeHR = LoanWfUtility.getWfComparatorInstance(wfID, wfDetID, 
				LoanWfQueries.eventActions.Rejected.toString(), LoanWfQueries.transitionCode.Trustee.toString(), LoanWfQueries.transitionCode.HR.toString());
		hrWFlows.add(rejectedTrusteeHR);

		//- TrusteeII-HR		rejected
		eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.Rejected.toString());
		wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
		wfDetID = LoanWfUtility.getWfDetIDByWfIDAndTransitions(wfID, LoanWfQueries.transitionCode.TrusteeII.toString(), LoanWfQueries.transitionCode.HR.toString());

		WfComparator rejectedTrusteeIIHR = LoanWfUtility.getWfComparatorInstance(wfID, wfDetID, 
				LoanWfQueries.eventActions.Rejected.toString(), LoanWfQueries.transitionCode.TrusteeII.toString(), LoanWfQueries.transitionCode.HR.toString());
		hrWFlows.add(rejectedTrusteeIIHR);

		//- HR-Member			re-work
		eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.ReWork.toString());
		wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
		wfDetID = LoanWfUtility.getWfDetIDByWfIDAndTransitions(wfID, LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.Member.toString());

		WfComparator reWorkHRMember = LoanWfUtility.getWfComparatorInstance(wfID, wfDetID, 
				LoanWfQueries.eventActions.ReWork.toString(), LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.Member.toString());
		hrWFlows.add(reWorkHRMember);

		//- Trustee-HR		re-work
		eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.ReWork.toString());
		wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
		wfDetID = LoanWfUtility.getWfDetIDByWfIDAndTransitions(wfID, LoanWfQueries.transitionCode.Trustee.toString(), LoanWfQueries.transitionCode.HR.toString());

		WfComparator reWorkTrusteeHR = LoanWfUtility.getWfComparatorInstance(wfID, wfDetID, 
				LoanWfQueries.eventActions.ReWork.toString(), LoanWfQueries.transitionCode.Trustee.toString(), LoanWfQueries.transitionCode.HR.toString());
		hrWFlows.add(reWorkTrusteeHR);

		//- TrusteeII-HR	re-work
		eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.ReWork.toString());
		wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
		wfDetID = LoanWfUtility.getWfDetIDByWfIDAndTransitions(wfID, LoanWfQueries.transitionCode.TrusteeII.toString(), LoanWfQueries.transitionCode.HR.toString());

		WfComparator reWorkTrusteeIIHR = LoanWfUtility.getWfComparatorInstance(wfID, wfDetID, 
				LoanWfQueries.eventActions.ReWork.toString(), LoanWfQueries.transitionCode.TrusteeII.toString(), LoanWfQueries.transitionCode.HR.toString());
		hrWFlows.add(reWorkTrusteeIIHR);

		return hrWFlows;
	}

	// Trustee buckets
	Map<String, Vector<WfHistory>> getTrusteeBucketsForLoanIDsAgainstEventActions(List <Map<String, Object>> loanApplyIDs, String event, String employeeID) throws Exception
	{
		TransactionStatus status = null;
		TransactionDefinition def = null;
		PlatformTransactionManager transacManager = null;
		Map<String, Vector<WfHistory>> wfHistoryTransacs = getEventActionHashMap();

		try
		{
			def = new DefaultTransactionDefinition();
			transacManager = JDBCTemplateFactory.getDSTransactionManager();
			status = transacManager.getTransaction(def);

			WfComparator tempWorkFlow = LoanWfUtility.getWfComparatorInstance();
			Vector<WfComparator> hrWorkFlows = getValidTrusteeTransitions(event); 

			synchronized (loanApplyIDs)
			{
				for (int cnt=zero; cnt<loanApplyIDs.size(); cnt++)
				{
					Map<String, Object> loanApplyID =  loanApplyIDs.get(cnt);
					Long sourceID = (Long) loanApplyID.get(LoanWfQueries.loanApplyID);
					List <Map<String, Object>> loanHistoryTransacs = LoanWfUtility.getLoanWfHisoryTransactions(sourceID.longValue());

					if ((loanHistoryTransacs == null) || (loanHistoryTransacs.size() == zero)) 
						continue;

					tempWorkFlow.initialize();
					//- get the last history transaction. Only transaction to be considered 
					Map<String, Object> transacsAgainstApplyID = loanHistoryTransacs.get(zero);

					long historyIDParam = (Long) transacsAgainstApplyID.get(LoanWfQueries.historyIdParam);
					long sourceIDParam = (Long) transacsAgainstApplyID.get(LoanWfQueries.historySourceID);
					long wfDetIDParam = (Long) transacsAgainstApplyID.get(LoanWfQueries.wfDetID);
					long wfIDParam = (Long) transacsAgainstApplyID.get(LoanWfQueries.wfID);
					String wfNameParam = (String) transacsAgainstApplyID.get(LoanWfQueries.wfName);
					String wfEventTypeParam = (String) transacsAgainstApplyID.get(LoanWfQueries.wfEventType);
					String wfEventActionParam = (String) transacsAgainstApplyID.get(LoanWfQueries.wfActionName);
					String wfEmployeeIDParam = (String) transacsAgainstApplyID.get(LoanWfQueries.historyEmployeeID);
					int wfSeqParam = (Integer) transacsAgainstApplyID.get(LoanWfQueries.wfSequence);
					String wfFrmTransitionParam = (String) transacsAgainstApplyID.get(LoanWfQueries.wfFromTransition);
					String wfToTransitionParam = (String) transacsAgainstApplyID.get(LoanWfQueries.wfToTransition);

					/*
					 *  Note : As two trustees part of work-flow, trustee of same id (i.e. employee id can see its
					 *   work-flow) history transactions   
					 */
					if (!wfEmployeeIDParam.trim().equalsIgnoreCase(employeeID.trim()))
						continue;

					tempWorkFlow.setWfID(wfIDParam);
					tempWorkFlow.setWfDetID(wfDetIDParam);
					tempWorkFlow.setWfAction(wfEventActionParam);
					tempWorkFlow.setFromTransition(wfFrmTransitionParam);
					tempWorkFlow.setToTransition(wfToTransitionParam);

					for (int hwf=zero; hwf < hrWorkFlows.size(); hwf++)
					{
						WfComparator wfDef = hrWorkFlows.get(hwf);

						if (wfDef.compareTo(tempWorkFlow) == zero)
						{
							WfHistory historyBean = new WfHistory(wfSeqParam, wfIDParam, wfDetIDParam, sourceIDParam, historyIDParam,
									wfNameParam, wfEmployeeIDParam, wfEventTypeParam, wfEventActionParam, wfFrmTransitionParam, wfToTransitionParam);

							switch(wfDef.getWfAction())
							{
							case "Pending": 
								Vector<WfHistory> pendingHRHistoryTransac = wfHistoryTransacs.get(LoanWfQueries.eventActions.Pending.toString());
								pendingHRHistoryTransac.add(historyBean);
								break;
							case "Approved":
								Vector<WfHistory> approvedHRHistoryTransac = wfHistoryTransacs.get(LoanWfQueries.eventActions.Approved.toString());
								approvedHRHistoryTransac.add(historyBean);
								break;
							case "Rejected":
								Vector<WfHistory> rejectedHRHistoryTransac = wfHistoryTransacs.get(LoanWfQueries.eventActions.Rejected.toString());
								rejectedHRHistoryTransac.add(historyBean);
								break;
							case "ReWork":
								Vector<WfHistory> reworkHRHistoryTransac = wfHistoryTransacs.get(LoanWfQueries.eventActions.ReWork.toString());
								reworkHRHistoryTransac.add(historyBean);
								break;
							case "Payment":
								Vector<WfHistory> paymentHRHistoryTransac = wfHistoryTransacs.get(LoanWfQueries.eventActions.Payment.toString());
								paymentHRHistoryTransac.add(historyBean);
								break;
							}
							// transaction inserted, exit the work flow definition loop
							break;
						} //-if
					}//- for	

				}	

			}//- synchronized	

			transacManager.commit(status);
		}
		catch(Exception exception)
		{
			transacManager.rollback(status);
			throw new TransactionException("MemberHREventLoanActionPending", exception);
		}
		return wfHistoryTransacs;
	}

	/*
	 *  - HR-Trsutee		pending
	 *  - HR-TrsuteeII		pending
	 *  ****************************
	 *  - Trustee-HR		approved
	 *  - TrusteeII-HR		approved
	 *  ****************************
	 *  - Trustee-HR		rejected
	 *  - TrusteeII-HR		rejected
	 *  ****************************
	 *  - Trustee-HR		re-work
	 *  - TrusteeII-HR		re-work
	 *  ****************************
	 */
	private Vector<WfComparator> getValidTrusteeTransitions(String event) throws Exception
	{
		Vector<WfComparator> hrWFlows = new Vector<WfComparator>();  

		// - HR-Trustee			pending
		int eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.Pending.toString());
		Long wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
		Long wfDetID = LoanWfUtility.getWfDetIDByWfIDAndTransitions(wfID, LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.Trustee.toString());

		WfComparator pendingHRTrustee = LoanWfUtility.getWfComparatorInstance(wfID, wfDetID, 
				LoanWfQueries.eventActions.Pending.toString(), LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.Trustee.toString());
		hrWFlows.add(pendingHRTrustee);

		// - HR-TrusteeII		pending
		eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.Pending.toString());
		wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
		wfDetID = LoanWfUtility.getWfDetIDByWfIDAndTransitions(wfID, LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.TrusteeII.toString());

		WfComparator pendingHRTrusteeII = LoanWfUtility.getWfComparatorInstance(wfID, wfDetID, 
				LoanWfQueries.eventActions.Pending.toString(), LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.TrusteeII.toString());
		hrWFlows.add(pendingHRTrusteeII);

		// - Trustee-HR		 	approved
		eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.Approved.toString());
		wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
		wfDetID = LoanWfUtility.getWfDetIDByWfIDAndTransitions(wfID, LoanWfQueries.transitionCode.Trustee.toString(), LoanWfQueries.transitionCode.HR.toString());

		WfComparator approvedTrusteeHR = LoanWfUtility.getWfComparatorInstance(wfID, wfDetID, 
				LoanWfQueries.eventActions.Approved.toString(), LoanWfQueries.transitionCode.Trustee.toString(), LoanWfQueries.transitionCode.HR.toString());
		hrWFlows.add(approvedTrusteeHR);

		// - TrusteeII-HR	 	approved
		eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.Approved.toString());
		wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
		wfDetID = LoanWfUtility.getWfDetIDByWfIDAndTransitions(wfID, LoanWfQueries.transitionCode.TrusteeII.toString(), LoanWfQueries.transitionCode.HR.toString());

		WfComparator approvedTrusteeIIHR = LoanWfUtility.getWfComparatorInstance(wfID, wfDetID, 
				LoanWfQueries.eventActions.Approved.toString(), LoanWfQueries.transitionCode.TrusteeII.toString(), LoanWfQueries.transitionCode.HR.toString());
		hrWFlows.add(approvedTrusteeIIHR);

		//- Trustee-HR		rejected
		eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.Rejected.toString());
		wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
		wfDetID = LoanWfUtility.getWfDetIDByWfIDAndTransitions(wfID, LoanWfQueries.transitionCode.Trustee.toString(), LoanWfQueries.transitionCode.HR.toString());

		WfComparator rejectedTrusteeHR = LoanWfUtility.getWfComparatorInstance(wfID, wfDetID, 
				LoanWfQueries.eventActions.Rejected.toString(), LoanWfQueries.transitionCode.Trustee.toString(), LoanWfQueries.transitionCode.HR.toString());
		hrWFlows.add(rejectedTrusteeHR);

		//- TrusteeII-HR		rejected
		eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.Rejected.toString());
		wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
		wfDetID = LoanWfUtility.getWfDetIDByWfIDAndTransitions(wfID, LoanWfQueries.transitionCode.TrusteeII.toString(), LoanWfQueries.transitionCode.HR.toString());

		WfComparator rejectedTrusteeIIHR = LoanWfUtility.getWfComparatorInstance(wfID, wfDetID, 
				LoanWfQueries.eventActions.Rejected.toString(), LoanWfQueries.transitionCode.TrusteeII.toString(), LoanWfQueries.transitionCode.HR.toString());
		hrWFlows.add(rejectedTrusteeIIHR);

		//- Trustee-HR		re-work
		eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.ReWork.toString());
		wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
		wfDetID = LoanWfUtility.getWfDetIDByWfIDAndTransitions(wfID, LoanWfQueries.transitionCode.Trustee.toString(), LoanWfQueries.transitionCode.HR.toString());

		WfComparator reWorkTrusteeHR = LoanWfUtility.getWfComparatorInstance(wfID, wfDetID, 
				LoanWfQueries.eventActions.ReWork.toString(), LoanWfQueries.transitionCode.Trustee.toString(), LoanWfQueries.transitionCode.HR.toString());
		hrWFlows.add(reWorkTrusteeHR);

		//- TrusteeII-HR	re-work
		eventActionID = LoanWfUtility.getActionID(LoanWfQueries.eventActions.ReWork.toString());
		wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);
		wfDetID = LoanWfUtility.getWfDetIDByWfIDAndTransitions(wfID, LoanWfQueries.transitionCode.TrusteeII.toString(), LoanWfQueries.transitionCode.HR.toString());

		WfComparator reWorkTrusteeIIHR = LoanWfUtility.getWfComparatorInstance(wfID, wfDetID, 
				LoanWfQueries.eventActions.ReWork.toString(), LoanWfQueries.transitionCode.TrusteeII.toString(), LoanWfQueries.transitionCode.HR.toString());
		hrWFlows.add(reWorkTrusteeIIHR);


		return hrWFlows;
	}

	private Map<String, Vector<WfHistory>> getEventActionHashMap()
	{
		Map<String, Vector<WfHistory>> loans = new HashMap<String, Vector<WfHistory>>();

		loans.put(LoanWfQueries.eventActions.Pending.toString(), new Vector<WfHistory>());
		loans.put(LoanWfQueries.eventActions.Approved.toString(), new Vector<WfHistory>());
		loans.put(LoanWfQueries.eventActions.Rejected.toString(), new Vector<WfHistory>());
		loans.put(LoanWfQueries.eventActions.ReWork.toString(), new Vector<WfHistory>());
		loans.put(LoanWfQueries.eventActions.Payment.toString(), new Vector<WfHistory>());

		return loans;
	}


	private static final int zero = 0;
}
