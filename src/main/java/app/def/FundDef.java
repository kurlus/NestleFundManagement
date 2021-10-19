package app.def;


import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.io.IOException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

import app.DefResultSet;
import app.Definition;
import app.beans.Fund;
import app.beans.LoginEmployee;
import utilities.AppConstants;
import utilities.AppUtility;
import database.DMLBase;
import database.DefQueries;


public class FundDef implements Definition 
{

	public FundDef()
	{
		
	}
	
	@Override
	public DefResultSet loadDefinitionData(LoginEmployee loginEmployee) throws Exception
	{
		v.clear();
		parameters.clear();
		defResultSet = new DefResultSet();
		this.setLoginEmployee(loginEmployee);
		execute();

		if (v.size() > AppConstants.zero)
		{
			fund = new Fund[v.size()];
			v.toArray(fund);
			defResultSet.setFundBeans(fund);
		}

		DMLBase.loadUserRights(this.getLoginEmployee());
		defResultSet.setLoginEmployee(getLoginEmployee());

		return defResultSet;
	}
	
	public LoginEmployee getLoginEmployee()
	{
		return loginEmployee;
	}

	public void setLoginEmployee(LoginEmployee loginEmployee)
	{
		this.loginEmployee = loginEmployee;
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	private void setListData(Object[] listOfObjs)
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
            if (loginEmployee.getFilterBy()!= null && (loginEmployee.getFilterBy().equals(AppConstants.filterByALL)))
			{
				query = session.createQuery(DefQueries.findFundQuery);
			} 
			else if (loginEmployee.getFilterBy()!= null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findFundPostedQuery);
			}
			
			List<Fund> resultlist = query.list();
			System.out.println("Fund list Size=: " + resultlist.size());
			for (Fund bean : resultlist)
			{
    			v.add(bean);
			}
			
		} catch (RuntimeException e)
		{
			throw e;

		} finally
		{
			session.close();
		}
	}

	private Fund[] fund;
	private DefResultSet defResultSet;
	private SessionFactory sessionFactory;
	private Vector<Fund> v = new Vector<Fund>();
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Map<String, Object> parameters = new HashMap<String, Object>();

}


