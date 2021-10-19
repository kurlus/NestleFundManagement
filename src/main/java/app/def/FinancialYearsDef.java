package app.def;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import org.hibernate.Query;
import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

import app.Definition;
import app.DefResultSet;
import database.DMLBase;
import database.DefQueries;
import utilities.AppConstants;
import app.beans.LoginEmployee;
import app.beans.GlFinancialYears;

public class FinancialYearsDef implements Definition
{
	
	public FinancialYearsDef() 
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
			yearsBeans = new GlFinancialYears[v.size()];		
			v.toArray(yearsBeans);		
			defResultSet.setGlFinancialYears(yearsBeans);
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
				query = session.createQuery(DefQueries.findGlFinancialYearsQuery);
			} else if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findGlFinancialYearsPostedQuery);
			}
           
            List<GlFinancialYears> resultlist = query.list();
            System.out.println("Year data found: " + resultlist.size());
            for(GlFinancialYears bean: resultlist) 
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
	private GlFinancialYears[] yearsBeans;
	private SessionFactory sessionFactory;
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Vector<GlFinancialYears> v = new Vector<GlFinancialYears>();
	private Map<String, Object> parameters = new HashMap<String, Object>();	
}
