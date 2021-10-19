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

import app.DefResultSet;
import app.Definition;
import app.beans.LoanPayment;
import app.beans.LoginEmployee;
import utilities.AppConstants;
import utilities.AppUtility;
import database.DMLBase;


public class LoanPaymentTransacs implements Definition 
{

	public LoanPaymentTransacs()
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
			loanSchedule = new LoanPayment[v.size()];
			v.toArray(loanSchedule);
			defResultSet.setLoanPayment(loanSchedule);
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
				query = session.createQuery("from LoanPayment");
			} 
			else if (loginEmployee.getFilterBy()!= null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery("from LoanPayment where post=1");
			}
			
			List<LoanPayment> resultlist = query.list();
			System.out.println("Loan Schedule Size=: " + resultlist.size());
			for (LoanPayment bean : resultlist)
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

	private LoanPayment[] loanSchedule;
	private DefResultSet defResultSet;
	private SessionFactory sessionFactory;
	private Vector<LoanPayment> v = new Vector<LoanPayment>();
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Map<String, Object> parameters = new HashMap<String, Object>();

}
