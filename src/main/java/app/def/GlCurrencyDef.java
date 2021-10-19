package app.def;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.HashMap;
import java.io.IOException;
import java.util.logging.Level;

import app.Definition;
import app.DefResultSet;
import database.DMLBase;
import database.DefQueries;
import app.beans.GlCurrency;
import utilities.AppUtility;
import utilities.AppConstants;
import app.beans.LoginEmployee;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

public class GlCurrencyDef implements Definition 
{

	public GlCurrencyDef() 
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
			glCurrencyBeans = new GlCurrency[v.size()];		
			v.toArray(glCurrencyBeans);		
			defResultSet.setGlCurrencyBeans(glCurrencyBeans);
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
				query = session.createQuery(DefQueries.findCurrencyQuery);
			} else if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findCurrencyPostedQuery);
			}
        	
            List<GlCurrency> resultlist = query.list();
            System.out.println("Currency found: " + resultlist.size());
            for(GlCurrency bean: resultlist) 
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
	
	private DefResultSet defResultSet;
	private LoginEmployee loginEmployee;
	private SessionFactory sessionFactory;
	private GlCurrency[] glCurrencyBeans;
	private Vector<GlCurrency> v = new Vector<GlCurrency>();
	private Map<String, Object> parameters = new HashMap<String, Object>();

}
