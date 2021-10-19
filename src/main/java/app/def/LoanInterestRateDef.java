package app.def;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import utilities.AppConstants;
import database.DMLBase;
import database.DefQueries;
import app.DefResultSet;
import app.Definition;
import app.beans.GlFinancialYears;
import app.beans.LoanInterestRate;
import app.beans.LoginEmployee;

public class LoanInterestRateDef implements Definition
{
	public LoanInterestRateDef() 
	{
	}
	
	@Override
	public synchronized DefResultSet loadDefinitionData(LoginEmployee loginEmployee) throws Exception 
	{
		v.clear();
		parameters.clear();
		defResultSet = new DefResultSet();
		this.setLoginEmployee(loginEmployee);
		execute();

		if (v.size() > AppConstants.zero)
		{
			loanInterestRateBeans = new LoanInterestRate[v.size()];		
			v.toArray(loanInterestRateBeans);		
			defResultSet.setRatesBeans(loanInterestRateBeans);
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
        Session session = sessionFactory.openSession();
        Query query = null;
        try 
        {	
        	if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterByALL)))
			{
				query = session.createQuery(DefQueries.findLoanInterstRateQuery);
			} 
        	else if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findLoanInterestRatePostedQuery);
			}
           
            List<LoanInterestRate> resultlist = query.list();
            System.out.println("Loan Interest Rate data found: " + resultlist.size());
            for(LoanInterestRate bean: resultlist) 
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
	
	private DefResultSet defResultSet;
	private LoanInterestRate[] loanInterestRateBeans;
	private SessionFactory sessionFactory;
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Vector<LoanInterestRate> v = new Vector<LoanInterestRate>();
	private Map<String, Object> parameters = new HashMap<String, Object>();
}
