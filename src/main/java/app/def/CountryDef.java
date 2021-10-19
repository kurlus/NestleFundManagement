package app.def;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import org.hibernate.Query;
import java.io.IOException;
import org.hibernate.Session;
import java.util.logging.Level;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

import database.DMLBase;
import database.DefQueries;

import app.Definition;
import app.DefResultSet;

import app.beans.Country;
import utilities.AppUtility;
import utilities.AppConstants;
import app.beans.LoginEmployee;

public class CountryDef implements Definition 
{
	public CountryDef() 
	{
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
			countryBeans = new Country[v.size()];		
			v.toArray(countryBeans);		
			defResultSet.setCountryBeans(countryBeans);
		}
		DMLBase.loadUserRights(this.getLoginEmployee());
		defResultSet.setLoginEmployee(getLoginEmployee());
		return defResultSet;
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
        	if (loginEmployee.getFilterBy()!= null && (loginEmployee.getFilterBy().equals(AppConstants.filterByALL)))
			{
				query = session.createQuery(DefQueries.findCountryQuery);
			} 
			else if (loginEmployee.getFilterBy()!= null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findCountryPostedQuery);
			}
        	
            List<Country> resultlist = query.list();
            System.out.println("counties data found: " + resultlist.size());
            for(Country bean: resultlist) 
            {
    			v.add(bean);
            }
            
        } catch (RuntimeException e) 
        {
        	AppUtility.logger.log(Level.SEVERE, "loading data failed ="+e.getMessage());
            throw e;

        } finally 
        {
            session.close();
        }
    }
	
	
	
	private Country[] countryBeans;
	private DefResultSet defResultSet;
	private LoginEmployee loginEmployee;
	private SessionFactory sessionFactory;
	private Vector<Country> v = new Vector<Country>();
	private Map<String, Object> parameters = new HashMap<String, Object>();	

}
