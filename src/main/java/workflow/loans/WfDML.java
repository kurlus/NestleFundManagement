package workflow.loans;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import utilities.AppConstants;
import app.beans.WfMF;
import app.beans.WfDetail;
import database.DMLOperations;
import database.JDBCTemplateFactory;


public class WfDML extends DMLOperations
{

	public synchronized void updateWorkFlow(WfMF workflow) throws WfException, TransactionException
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
			throw new TransactionException(LoanWfQueries.wfTransactionException, exception);
		}

		try
		{
			if (workflow.getWfTransitions() == null) throw new NullPointerException(" work-flow transitions null......");

			synchronized(workflow)
			{
				String wfMfName = workflow.getWorkFlowName();
				String wfMfEvent = workflow.getEventType();
				int wfMfAction = workflow.getActionID();
				long wfMfLogID = workflow.getLogID();
				long wfMfID = workflow.getWorkFlowId();
				
				JDBCTemplateFactory.getJdbcTemplateInstance().update(LoanWfQueries.updateWfMf, new Object[] 
						{new String(wfMfName), new String(wfMfEvent), new Integer(wfMfAction), new Long(wfMfLogID), 
								new Long(wfMfID)});

				for (Iterator<WfDetail> wfDet = workflow.getWfTransitions().iterator(); wfDet.hasNext();)
				{
					WfDetail transitionDetail = wfDet.next();

					Long wfDetailID = new Long(transitionDetail.getWfDetailID());
					Long wfID = new Long(transitionDetail.getWorkFlowId());
					String fromTransition = transitionDetail.getFromTransition();
					String toTransition = transitionDetail.getToTransition();
					Integer wfSequence = new Integer(transitionDetail.getWorkFlowSeq());
					Integer memberEmail = new Integer(transitionDetail.getIsMemberEmail());

					int result = JDBCTemplateFactory.getJdbcTemplateInstance().update(LoanWfQueries.updateWfDetail, new Object[] 
							{new String(fromTransition), new String(toTransition), wfSequence, memberEmail, 
							wfDetailID, wfID});
					if(result== AppConstants.zero)
					{
						JDBCTemplateFactory.getJdbcTemplateInstance().update(LoanWfQueries.insertWfDetail, getWfDetID(), wfID, 
								transitionDetail.getFromTransition(), transitionDetail.getToTransition(), 
								new Integer(transitionDetail.getWorkFlowSeq()), 
								new Integer(transitionDetail.getIsMemberEmail()));
					}
				}	
			}
			transacManager.commit(status);
		}
		catch(Exception exception)
		{
			transacManager.rollback(status);
			throw new WfException(LoanWfQueries.wfException, exception);
		}

	}

	public synchronized void insertWorkFlow(WfMF workflow) throws WfException, TransactionException
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
			throw new TransactionException(LoanWfQueries.wfTransactionException, exception);
		}

		try
		{
			if (workflow.getWfTransitions() == null) throw new NullPointerException(" work-flow transitions null......");

			Long wfID = getWfID();
			workflow.setWorkFlowId(wfID);
			JDBCTemplateFactory.getJdbcTemplateInstance().update(LoanWfQueries.insertWfMaster, wfID, workflow.getWorkFlowName(), 
					workflow.getEventType(), new Integer(workflow.getActionID()), new Long(workflow.getLogID()));

			for (Iterator<WfDetail> wfDet=workflow.getWfTransitions().iterator(); wfDet.hasNext();)
			{
				WfDetail transitionDetail = wfDet.next();
				JDBCTemplateFactory.getJdbcTemplateInstance().update(LoanWfQueries.insertWfDetail, getWfDetID(), wfID, 
						transitionDetail.getFromTransition(), transitionDetail.getToTransition(), 
						new Integer(transitionDetail.getWorkFlowSeq()), 
						new Integer(transitionDetail.getIsMemberEmail()));
			}
			transacManager.commit(status);
		}
		catch(Exception exception)
		{
			transacManager.rollback(status);
			throw new WfException(LoanWfQueries.wfException, exception);
		}

	}

	private Long getWfID() throws Exception
	{
		try
		{
			Map <String, Object> params = new HashMap<String, Object>();
			Long nextWfID = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForObject(LoanWfQueries.getNextWfID, params, Long.class);
			return nextWfID;
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	private Long getWfDetID() throws Exception
	{
		try
		{
			Map <String, Object> params = new HashMap<String, Object>();
			Long nextWfDetID = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForObject(LoanWfQueries.getNextWfDetID, params, Long.class);
			return nextWfDetID;
		}
		catch(Exception e)
		{
			throw e;
		}
	}

}
