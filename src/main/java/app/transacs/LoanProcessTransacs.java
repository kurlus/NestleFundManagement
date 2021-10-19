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
import app.beans.Loan;
import database.DMLBase;
import database.DefQueries;
import app.DefResultSet;
import utilities.AppConstants;
import utilities.AppUtility;
import app.beans.LoginEmployee;

public class LoanProcessTransacs implements Definition 
{
	public LoanProcessTransacs()
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
			loanProcess = new Loan[v.size()];
			v.toArray(loanProcess);
			defResultSet.setLoanProcess(loanProcess);
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
				query = session.createQuery("from Loan");
			} 
			else if (loginEmployee.getFilterBy()!= null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery("from Loan where post=1");
			}
			List<Loan> resultlist = query.list();
			System.out.println("Loan  Size=: " + resultlist.size());
			  for(Loan bean: resultlist) 
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

	private Loan[] loanProcess;
	private DefResultSet defResultSet;
	private SessionFactory sessionFactory;
	private Vector<Loan> v = new Vector<Loan>();
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Map<String, Object> parameters = new HashMap<String, Object>();	
}
