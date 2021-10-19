package workflow.loans;

import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import app.beans.WfMF;
import app.beans.WfDetail;
import app.beans.LoanApply;
import database.JDBCTemplateFactory;

public class WfTransitionHistory 
{

	public WfTransitionHistory()
	{
	}

	public synchronized void triggerWfHistory(String 
			roleType, String event, String eventAction, String employeeID, List<LoanApply> loanAppliedList) throws Exception
	{
		if (StringUtils.isEmpty(roleType)) throw new NullPointerException(" Role type is null .....");

		if ((LoanWfQueries.events.Loan.toString().equals(event)) && 
				(LoanWfQueries.eventActions.Pending.toString().equals(eventAction)))
		{
			switch(roleType)
			{
			case "Member":
				LoanApply loanApplied = loanAppliedList.get(zero);
				MemberHREventLoanActionPending(event, eventAction, employeeID, loanApplied);
				break;
			}

		}
		else if ((LoanWfQueries.events.Loan.toString().equals(event)) && 
				(LoanWfQueries.eventActions.Approved.toString().equals(eventAction)))
		{
			switch(roleType)
			{
			case "HR":
				HRTrusteeEventLoanactionApproved(event, eventAction, employeeID, loanAppliedList);
				break;
			case "Trustee":
				TrusteeHREventLoanActionApproved(event, eventAction, employeeID, loanAppliedList);
				break;
			}
		}
		else if ((LoanWfQueries.events.Loan.toString().equals(event)) && 
				(LoanWfQueries.eventActions.Payment.toString().equals(eventAction)))
		{
			switch(roleType)
			{
			case "HR":
				HRTreasurerEventLoanActionPayment(event, eventAction, employeeID, loanAppliedList);
				break;
			}

		}
		else if ((LoanWfQueries.events.Loan.toString().equals(event)) && 
				(LoanWfQueries.eventActions.ReWork.toString().equals(eventAction)))
		{
			switch(roleType)
			{
			case "HR":
				HRMemberEventLoanActionReWork(event, eventAction, employeeID, loanAppliedList);
				break;
			case "Trustee":
				TrusteeHREventLoanActionReWork(event, eventAction, employeeID, loanAppliedList);
				break;
			}
		}
		else if ((LoanWfQueries.events.Loan.toString().equals(event)) && 
				(LoanWfQueries.eventActions.Rejected.toString().equals(eventAction)))
		{
			switch(roleType)
			{
			case "HR":
				HRMemberEventLoanActionRejected(event, eventAction, employeeID, loanAppliedList);
				break;
			case "Trustee":
				TrusteeHREventLoanActionRejected(event, eventAction, employeeID, loanAppliedList);
				break;
			}
		}

	}

	/*
	 *  - on applying loan by member 
	 *  - inserts record into work-flow history
	 */
	void MemberHREventLoanActionPending(String event, String eventAction, String employeeID, LoanApply loanApplied) throws Exception 
	{

		TransactionStatus status = null;
		TransactionDefinition def = null;
		PlatformTransactionManager transacManager = null;		

		try
		{
			def = new DefaultTransactionDefinition();
			transacManager = JDBCTemplateFactory.getDSTransactionManager();
			status = transacManager.getTransaction(def);
		}
		catch(Exception exception)
		{
			throw new TransactionException("MemberHREventLoanActionPending", exception);
		}

		try
		{
			int eventActionID = LoanWfUtility.getActionID(eventAction);
			Long wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);

			WfMF transitionMf = LoanWfUtility.getWfMFTransac(wfID, event, eventActionID);
			WfDetail transitionDet = LoanWfUtility.getWfDetailTransac(LoanWfQueries.transitionCode.Member.toString(), LoanWfQueries.transitionCode.HR.toString(), wfID);

			JDBCTemplateFactory.getJdbcTemplateInstance().update(LoanWfQueries.insertWfHistory, getWfHistoryID(),  
					new Long(transitionDet.getWfDetailID()), new Long (transitionDet.getWorkFlowId()), transitionMf.getEventType(),
					new Integer(transitionMf.getActionID()), new Long (loanApplied.getApplyID()), employeeID, new Date(), loanApplied.getLogID());

			transacManager.commit(status);
		}
		catch(Exception e)
		{
			transacManager.rollback(status);
			throw new TransactionException("MemberHREventLoanActionPending", e);
		}
	}

	/*
	 *  - on event actions by HR role i.e. pending/approval/re-work/payment
	 *  - iterates through list of loans and inserts record into work-flow history  
	 */
	void HRTrusteeEventLoanactionApproved(String event, String eventAction, String employeeID, List<LoanApply> loanAppliedList) throws Exception
	{
		LoanApply loanApplied = null;
		TransactionStatus status = null;
		TransactionDefinition def = null;
		PlatformTransactionManager transacManager = null;		

		try
		{
			def = new DefaultTransactionDefinition();
			transacManager = JDBCTemplateFactory.getDSTransactionManager();
			status = transacManager.getTransaction(def);
		}
		catch(Exception exception)
		{
			throw new TransactionException("HRTrusteeEventLoanactionApproved", exception);
		}

		try
		{
			int eventActionID = LoanWfUtility.getActionID(eventAction);
			Long wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);

			WfMF transitionMf = LoanWfUtility.getWfMFTransac(wfID, event, eventActionID);
			WfDetail transitionDet = LoanWfUtility.getWfDetailTransac(LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.Trustee.toString(), wfID);

			for (Iterator<LoanApply> loanAList = loanAppliedList.iterator(); loanAList.hasNext();)
			{
				loanApplied = loanAList.next();
				boolean isFirstTrustee = LoanWfUtility.
						isTransacHistoryExists(transitionDet.getWfDetailID(), wfID, event, eventActionID, loanApplied.getApplyID(), employeeID);

				if (isFirstTrustee == false)
					transitionDet = LoanWfUtility.getWfDetailTransac(LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.TrusteeII.toString(), wfID);

				JDBCTemplateFactory.getJdbcTemplateInstance().update(LoanWfQueries.insertWfHistory, getWfHistoryID(),
						new Long(transitionDet.getWfDetailID()), new Long (transitionDet.getWorkFlowId()), transitionMf.getEventType(),
						new Integer(transitionMf.getActionID()), new Long (loanApplied.getApplyID()), employeeID, new Date(), loanApplied.getLogID());
			}	
			transacManager.commit(status);
		}
		catch(Exception e)
		{
			transacManager.rollback(status);
			throw new TransactionException("HRTrusteeEventLoanactionApproved", e);
		}

	}

	/*
	 *  - on event actions by trustee role i.e. pending/approval/re-work/payment
	 *  - iterates through list of loans and inserts record into work-flow history  
	 */
	void TrusteeHREventLoanActionApproved(String event, String eventAction, String employeeID, List<LoanApply> loanAppliedList) throws Exception
	{
		LoanApply loanApplied = null;
		TransactionStatus status = null;
		TransactionDefinition def = null;
		PlatformTransactionManager transacManager = null;		

		try
		{
			def = new DefaultTransactionDefinition();
			transacManager = JDBCTemplateFactory.getDSTransactionManager();
			status = transacManager.getTransaction(def);
		}
		catch(Exception exception)
		{
			throw new TransactionException("TrusteeHREventLoanActionApproved", exception);
		}

		try
		{
			int eventActionID = LoanWfUtility.getActionID(eventAction);
			Long wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);

			WfMF transitionMf = LoanWfUtility.getWfMFTransac(wfID, event, eventActionID);
			WfDetail transitionDet = LoanWfUtility.getWfDetailTransac(LoanWfQueries.transitionCode.Trustee.toString(), LoanWfQueries.transitionCode.HR.toString(), wfID);

			for (Iterator<LoanApply> loanAList = loanAppliedList.iterator(); loanAList.hasNext();)
			{
				loanApplied = loanAList.next();
				boolean isFirstTrustee = LoanWfUtility.
						isTransacHistoryExists(transitionDet.getWfDetailID(), wfID, event, eventActionID, loanApplied.getApplyID(), employeeID);

				if (isFirstTrustee == false)
					transitionDet = LoanWfUtility.getWfDetailTransac(LoanWfQueries.transitionCode.TrusteeII.toString(), LoanWfQueries.transitionCode.HR.toString(), wfID);

				JDBCTemplateFactory.getJdbcTemplateInstance().update(LoanWfQueries.insertWfHistory, getWfHistoryID(),
						new Long(transitionDet.getWfDetailID()), new Long (transitionDet.getWorkFlowId()), 
						transitionMf.getEventType(), new Integer(transitionMf.getActionID()),
						new Long (loanApplied.getApplyID()), employeeID, new Date(), loanApplied.getLogID());
			}	
			transacManager.commit(status);
		}
		catch(Exception e)
		{
			transacManager.rollback(status);
			throw new TransactionException("TrusteeHREventLoanActionApproved", e);
		}
	}

	/*
	 *  - on event actions by HR role i.e. pending/approval/re-work/payment
	 *  - iterates through list of loans and inserts record into work-flow history  
	 */
	void HRTreasurerEventLoanActionPayment(String event, String eventAction, String employeeID, List<LoanApply> loanAppliedList) throws Exception
	{
		LoanApply loanApplied = null;
		TransactionStatus status = null;
		TransactionDefinition def = null;
		PlatformTransactionManager transacManager = null;		

		try
		{
			def = new DefaultTransactionDefinition();
			transacManager = JDBCTemplateFactory.getDSTransactionManager();
			status = transacManager.getTransaction(def);
		}
		catch(Exception exception)
		{
			throw new TransactionException("HRTreasurerEventLoanActionPayment", exception);
		}

		try
		{
			int eventActionID = LoanWfUtility.getActionID(eventAction);
			Long wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);

			WfMF transitionMf = LoanWfUtility.getWfMFTransac(wfID, event, eventActionID);
			WfDetail transitionDet = LoanWfUtility.getWfDetailTransac(LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.Treasurer.toString(), wfID);

			for (Iterator<LoanApply> loanAList = loanAppliedList.iterator(); loanAList.hasNext();)
			{
				loanApplied = loanAList.next();

				JDBCTemplateFactory.getJdbcTemplateInstance().update(LoanWfQueries.insertWfHistory, getWfHistoryID(),
						new Long(transitionDet.getWfDetailID()), new Long (transitionDet.getWorkFlowId()), 
						transitionMf.getEventType(), new Integer(transitionMf.getActionID()),
						new Long (loanApplied.getApplyID()), employeeID, new Date(), loanApplied.getLogID());
			}	
			transacManager.commit(status);
		}
		catch(Exception e)
		{
			transacManager.rollback(status);
			throw new TransactionException("HRTreasurerEventLoanActionPayment", e);
		}

	}

	void HRMemberEventLoanActionRejected(String event, String eventAction, String employeeID, List<LoanApply> loanAppliedList) throws Exception
	{
		LoanApply loanApplied = null;
		TransactionStatus status = null;
		TransactionDefinition def = null;
		PlatformTransactionManager transacManager = null;		

		try
		{
			def = new DefaultTransactionDefinition();
			transacManager = JDBCTemplateFactory.getDSTransactionManager();
			status = transacManager.getTransaction(def);
		}
		catch(Exception exception)
		{
			throw new TransactionException("HRMemberEventLoanActionRejected", exception);
		}

		try
		{
			int eventActionID = LoanWfUtility.getActionID(eventAction);
			Long wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);

			WfMF transitionMf = LoanWfUtility.getWfMFTransac(wfID, event, eventActionID);
			WfDetail transitionDet = LoanWfUtility.getWfDetailTransac(LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.Member.toString(), wfID);

			for (Iterator<LoanApply> loanAList = loanAppliedList.iterator(); loanAList.hasNext();)
			{
				loanApplied = loanAList.next();

				JDBCTemplateFactory.getJdbcTemplateInstance().update(LoanWfQueries.insertWfHistory, getWfHistoryID(),
						new Long(transitionDet.getWfDetailID()), new Long (transitionDet.getWorkFlowId()), 
						transitionMf.getEventType(), new Integer(transitionMf.getActionID()),
						new Long (loanApplied.getApplyID()), employeeID, new Date(), loanApplied.getLogID());
			}	
			transacManager.commit(status);
		}
		catch(Exception e)
		{
			transacManager.rollback(status);
			throw new TransactionException("HRMemberEventLoanActionRejected", e);
		}

	}

	void TrusteeHREventLoanActionRejected(String event, String eventAction, String employeeID, List<LoanApply> loanAppliedList) throws Exception
	{
		LoanApply loanApplied = null;
		TransactionStatus status = null;
		TransactionDefinition def = null;
		PlatformTransactionManager transacManager = null;		

		try
		{
			def = new DefaultTransactionDefinition();
			transacManager = JDBCTemplateFactory.getDSTransactionManager();
			status = transacManager.getTransaction(def);
		}
		catch(Exception exception)
		{
			throw new TransactionException("TrusteeHREventLoanActionRejected", exception);
		}

		try
		{
			int eventActionID = LoanWfUtility.getActionID(eventAction);
			Long wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);

			WfMF transitionMf = LoanWfUtility.getWfMFTransac(wfID, event, eventActionID);
			WfDetail transitionDet = LoanWfUtility.getWfDetailTransac(LoanWfQueries.transitionCode.Trustee.toString(), LoanWfQueries.transitionCode.HR.toString(), wfID);

			for (Iterator<LoanApply> loanAList = loanAppliedList.iterator(); loanAList.hasNext();)
			{
				loanApplied = loanAList.next();
				boolean isFirstTrustee = LoanWfUtility.
						isTransacHistoryExists(transitionDet.getWfDetailID(), wfID, event, eventActionID, loanApplied.getApplyID(), employeeID);

				if (isFirstTrustee == false)
					transitionDet = LoanWfUtility.getWfDetailTransac(LoanWfQueries.transitionCode.TrusteeII.toString(), LoanWfQueries.transitionCode.HR.toString(), wfID);

				JDBCTemplateFactory.getJdbcTemplateInstance().update(LoanWfQueries.insertWfHistory, getWfHistoryID(),
						new Long(transitionDet.getWfDetailID()), new Long (transitionDet.getWorkFlowId()), 
						transitionMf.getEventType(), new Integer(transitionMf.getActionID()),
						new Long (loanApplied.getApplyID()), employeeID, new Date(), loanApplied.getLogID());
			}	
			transacManager.commit(status);
		}
		catch(Exception e)
		{
			transacManager.rollback(status);
			throw new TransactionException("TrusteeHREventLoanActionRejected", e);
		}

	}

	void HRMemberEventLoanActionReWork(String event, String eventAction, String employeeID, List<LoanApply> loanAppliedList) throws Exception
	{
		LoanApply loanApplied = null;
		TransactionStatus status = null;
		TransactionDefinition def = null;
		PlatformTransactionManager transacManager = null;		

		try
		{
			def = new DefaultTransactionDefinition();
			transacManager = JDBCTemplateFactory.getDSTransactionManager();
			status = transacManager.getTransaction(def);
		}
		catch(Exception exception)
		{
			throw new TransactionException("HRMemberEventLoanActionReWork", exception);
		}

		try
		{
			int eventActionID = LoanWfUtility.getActionID(eventAction);
			Long wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);

			WfMF transitionMf = LoanWfUtility.getWfMFTransac(wfID, event, eventActionID);
			WfDetail transitionDet = LoanWfUtility.getWfDetailTransac(LoanWfQueries.transitionCode.HR.toString(), LoanWfQueries.transitionCode.Member.toString(), wfID);

			for (Iterator<LoanApply> loanAList = loanAppliedList.iterator(); loanAList.hasNext();)
			{
				loanApplied = loanAList.next();

				JDBCTemplateFactory.getJdbcTemplateInstance().update(LoanWfQueries.insertWfHistory, getWfHistoryID(),
						new Long(transitionDet.getWfDetailID()), new Long (transitionDet.getWorkFlowId()), 
						transitionMf.getEventType(), new Integer(transitionMf.getActionID()),
						new Long (loanApplied.getApplyID()), employeeID, new Date(), loanApplied.getLogID());
			}	
			transacManager.commit(status);
		}
		catch(Exception e)
		{
			transacManager.rollback(status);
			throw new TransactionException("HRMemberEventLoanActionReWork", e);
		}

	}

	void TrusteeHREventLoanActionReWork(String event, String eventAction, String employeeID, List<LoanApply> loanAppliedList) throws Exception
	{
		LoanApply loanApplied = null;
		TransactionStatus status = null;
		TransactionDefinition def = null;
		PlatformTransactionManager transacManager = null;		

		try
		{
			def = new DefaultTransactionDefinition();
			transacManager = JDBCTemplateFactory.getDSTransactionManager();
			status = transacManager.getTransaction(def);
		}
		catch(Exception exception)
		{
			throw new TransactionException("TrusteeHREventLoanActionReWork", exception);
		}

		try
		{
			int eventActionID = LoanWfUtility.getActionID(eventAction);
			Long wfID = LoanWfUtility.getWfIDByEventAndAction(event, eventActionID);

			WfMF transitionMf = LoanWfUtility.getWfMFTransac(wfID, event, eventActionID);
			WfDetail transitionDet = LoanWfUtility.getWfDetailTransac(LoanWfQueries.transitionCode.Trustee.toString(), LoanWfQueries.transitionCode.HR.toString(), wfID);

			for (Iterator<LoanApply> loanAList = loanAppliedList.iterator(); loanAList.hasNext();)
			{
				loanApplied = loanAList.next();
				boolean isFirstTrustee = LoanWfUtility.
						isTransacHistoryExists(transitionDet.getWfDetailID(), wfID, event, eventActionID, loanApplied.getApplyID(), employeeID);

				if (isFirstTrustee == false)
					transitionDet = LoanWfUtility.getWfDetailTransac(LoanWfQueries.transitionCode.TrusteeII.toString(), LoanWfQueries.transitionCode.HR.toString(), wfID);

				JDBCTemplateFactory.getJdbcTemplateInstance().update(LoanWfQueries.insertWfHistory, getWfHistoryID(),
						new Long(transitionDet.getWfDetailID()), new Long (transitionDet.getWorkFlowId()), 
						transitionMf.getEventType(), new Integer(transitionMf.getActionID()),
						new Long (loanApplied.getApplyID()), employeeID, new Date(), loanApplied.getLogID());
			}	
			transacManager.commit(status);
		}
		catch(Exception e)
		{
			transacManager.rollback(status);
			throw new TransactionException("TrusteeHREventLoanActionReWork", e);
		}

	}

	private Long getWfHistoryID() throws Exception
	{
		try
		{
			Map <String, Object> params = new HashMap<String, Object>();
			Long nextWfHistoryID = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForObject(LoanWfQueries.getNextWfHistoryID, params, Long.class);
			return nextWfHistoryID;
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	private static int zero = 0;
}
