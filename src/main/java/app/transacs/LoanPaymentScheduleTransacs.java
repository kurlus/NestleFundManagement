package app.transacs;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.io.IOException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

import app.Definition;
import database.DMLBase;
import app.DefResultSet;
import utilities.AppConstants;
import utilities.AppUtility;
import app.beans.LoanSchedule;
import app.beans.LoginEmployee;

public class LoanPaymentScheduleTransacs  implements Definition
{
	public LoanPaymentScheduleTransacs()
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
			loanSchedule = new LoanSchedule[v.size()];
			v.toArray(loanSchedule);
			defResultSet.setLoanPaymentSchedule(loanSchedule);
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
				query = session.createQuery("from LoanSchedule");
			} 
			else if (loginEmployee.getFilterBy()!= null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery("from LoanSchedule where post=1");
			}
			
			List<LoanSchedule> resultlist = query.list();
			for(LoanSchedule bean: resultlist) 
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
	
	private LoanSchedule[] loanSchedule;
	private DefResultSet defResultSet;
	private SessionFactory sessionFactory;
	private Vector<LoanSchedule> v = new Vector<LoanSchedule>();
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Map<String, Object> parameters = new HashMap<String, Object>();	
}
