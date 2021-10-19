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
import app.beans.WfEventType;

public class WorkFlowEventTypeAdmin implements Definition
{

	public  WorkFlowEventTypeAdmin()
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
			wfEventTypeBeans = new WfEventType[v.size()];
			v.toArray(wfEventTypeBeans);
			defResultSet.setWfEventTypeBeans(wfEventTypeBeans);
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
			query = session.createQuery(DefQueries.findWFEventType);
			
			List<WfEventType> resultlist = query.list();
			System.out.println("Work Flow Event type Size=: " + resultlist.size());
			  for(WfEventType bean: resultlist) 
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
	private WfEventType[] wfEventTypeBeans;
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Vector<WfEventType> v = new Vector<WfEventType>();
	private Map<String, Object> parameters = new HashMap<String, Object>();
}
