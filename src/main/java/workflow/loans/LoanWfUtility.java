package workflow.loans;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import app.beans.WfMF;
import app.beans.WfDetail;
import database.JDBCTemplateFactory;

public class LoanWfUtility 
{

	public static synchronized WfComparator getWfComparatorInstance(Long wfID, Long wfDetID, String wfAction, String fromTransition, String toTransition)
	{
		return new WfComparator(wfID, wfDetID, wfAction, fromTransition, toTransition); 
	}

	public static synchronized WfComparator getWfComparatorInstance()
	{
		return new WfComparator(); 
	}

	public static synchronized List <Map<String, Object>> queryList(String query, Map parameterSource) throws Exception 
	{
		return JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForList(query, parameterSource);
	}

	public static synchronized Integer getActionID(String actionName) throws Exception
	{
		try
		{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put(LoanWfQueries.wfActionName, actionName);

			Integer actionID = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForObject(LoanWfQueries.getWfActionID, param, Integer.class);
			return actionID;
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	public static synchronized Long getWfIDByEventAndAction(String eventType, int actionID) throws Exception
	{
		try
		{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(LoanWfQueries.wfEventType, eventType);
			params.put(LoanWfQueries.wfActionID, actionID);

			Long wfID = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForObject(LoanWfQueries.getWfIDByEventAndAction, params, Long.class);
			return wfID;
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	public static synchronized Long getWfDetIDByWfIDAndTransitions(long wfID, String fromTransition, String toTransition) throws Exception
	{
		try
		{
			Map<String, Object> params = new HashMap<String, Object>();

			params.put(LoanWfQueries.wfID, wfID);
			params.put(LoanWfQueries.wfFromTransition, fromTransition);
			params.put(LoanWfQueries.wfToTransition, toTransition);

			Long wfDetID = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForObject(LoanWfQueries.getWfDetIDByFromToAndWfID, params, Long.class);
			return wfDetID;
		}
		catch(Exception e)
		{
			throw e;
		}

	}

	public static synchronized WfMF getWfMFTransac(Long wfID, String eventType, int actionID) throws Exception
	{
		try
		{
			WfMF wfMFTransac = JDBCTemplateFactory.getJdbcTemplateInstance().queryForObject(LoanWfQueries.getWfTransacByIDEventAndAction, 
					new Object[]{new Long(wfID), eventType, new Integer(actionID)}, new WfTransitionMfMapper());
			return wfMFTransac;
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	public static synchronized WfDetail getWfDetailTransac(String fromTransition, String toTransition, long wfID) throws Exception
	{
		try
		{
			WfDetail wfDetailTransac = JDBCTemplateFactory.getJdbcTemplateInstance().queryForObject(LoanWfQueries.getWfDetTransacByFromToAndWfID, 
					new Object[]{ new String(fromTransition), new String(toTransition), new Long(wfID)}, new WfTransitionDetailMapper());

			return wfDetailTransac;
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	public static synchronized boolean isTransacHistoryExists(long wfDetID, long wfID, String event, int actionID, long sourceID, String employeeID) throws Exception
	{
		try
		{
			Map<String, Object> params = new HashMap<String, Object>();

			params.put(LoanWfQueries.wfDetID, wfDetID);
			params.put(LoanWfQueries.wfID, wfID);
			params.put(LoanWfQueries.wfEventType, event);
			params.put(LoanWfQueries.wfActionID, actionID);
			params.put(LoanWfQueries.historySourceID, sourceID);
			params.put(LoanWfQueries.historyEmployeeID, employeeID);

			Integer count = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForObject(LoanWfQueries.isTransacHistoryExists, params, Integer.class);
			return count.intValue() == 0;
		}
		catch(Exception e)
		{
			throw e;
		}

	}

	public static synchronized List <Map<String, Object>> getDistinctApplyIDsNotMarkedForPayment(String event, long wfDetID) throws Exception
	{
		try
		{
			Map<String, Object> params = new HashMap<String, Object>();

			params.put(LoanWfQueries.wfEventType, event);
			params.put(LoanWfQueries.wfDetID, wfDetID);

			return JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForList(LoanWfQueries.getLoanApplyIDs, params);
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	public static synchronized List <Map<String, Object>> getLoanWfHisoryTransactions(long sourceID) throws Exception
	{
		try
		{
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put(LoanWfQueries.historySourceID, sourceID);

			return JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForList(LoanWfQueries.wfResultSet, params);
		}
		catch(Exception e)
		{
			throw e;
		}
	}



}
