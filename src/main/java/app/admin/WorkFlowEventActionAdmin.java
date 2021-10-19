package app.admin;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.io.IOException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

import app.Definition;
import database.DMLBase;
import app.DefResultSet;
import database.DefQueries;
import utilities.AppUtility;
import utilities.AppConstants;
import app.beans.LoginEmployee;
import app.beans.WfEventAction;

public class WorkFlowEventActionAdmin implements Definition
{
	public WorkFlowEventActionAdmin()
	{
		
	}

	@Override
	public DefResultSet loadDefinitionData(LoginEmployee loginEmployee)throws Exception 
	{
		v.clear();
		parameters.clear();
		defResultSet = new DefResultSet();
		this.setLoginEmployee(loginEmployee);

		execute();

		if (v.size() > AppConstants.zero)
		{
			wfEventActionBeans = new WfEventAction[v.size()];
			v.toArray(wfEventActionBeans);
			defResultSet.setWfEventActionBeans(wfEventActionBeans);
		}

		DMLBase.loadUserRights(this.getLoginEmployee());
		defResultSet.setLoginEmployee(getLoginEmployee());

		return defResultSet;
	}
	
	public synchronized LoginEmployee getLoginEmployee()
	{
		return loginEmployee;
	}

	public synchronized void setLoginEmployee(LoginEmployee loginEmployee)
	{
		this.loginEmployee = loginEmployee;
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	private synchronized void setListData(Object[] listOfObjs)
	{
	}

	private void execute() throws MappingException, IOException, Exception
	{
		Session session = null;
		Query query = null;
		try
		{
			if(sessionFactory == null)
        		sessionFactory = (SessionFactory)AppUtility.getWebApplicationContext().getBean(AppConstants.hibernateSessionFactory);
        	
            session = sessionFactory.openSession();
			query = session.createQuery(DefQueries.findWFEventAction);
			
			List<WfEventAction> resultlist = query.list();
			System.out.println("Work Flow Event Size=: " + resultlist.size());
			  for(WfEventAction bean: resultlist) 
	          {
	    			v.add(bean);
	          }
	            
		} 
		
		catch (RuntimeException e)
		{
			throw e;

		} 
		
		finally
		{
			session.close();
		}
	}
	
	

	
	
	private DefResultSet defResultSet;
	private SessionFactory sessionFactory;
	private WfEventAction[] wfEventActionBeans;
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Vector<WfEventAction> v = new Vector<WfEventAction>();
	private Map<String, Object> parameters = new HashMap<String, Object>();
	
	
}
