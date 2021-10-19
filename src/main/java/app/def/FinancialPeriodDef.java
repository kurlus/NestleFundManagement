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

import app.Definition;
import database.DMLBase;
import app.DefResultSet;
import database.DefQueries;
import utilities.AppUtility;
import utilities.AppConstants;
import app.beans.LoginEmployee;
import app.beans.GlFinancialPeriod;

public class FinancialPeriodDef implements Definition 
{
	public FinancialPeriodDef()
	{		
	}

	@Override
	public synchronized DefResultSet loadDefinitionData(LoginEmployee loginEmployee)
	throws Exception 
	{
		v.clear();
		parameters.clear();
		defResultSet = new DefResultSet();
		this.setLoginEmployee(loginEmployee);
		
		execute();

		if (v.size() > AppConstants.zero)
		{
			financialPeriodBeans = new GlFinancialPeriod[v.size()];		
			v.toArray(financialPeriodBeans);		
			defResultSet.setGlFinancialPeriodBeans(financialPeriodBeans);
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
				query = session.createQuery(DefQueries.findFinanPeriodQuery);
			} else if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findFinanPeriodPostedQuery);
			}
        	
            List<GlFinancialPeriod> finPeriodList =  query.list();
            AppUtility.logger.log(Level.INFO, "Financial Period found: " + finPeriodList.size());
            for(GlFinancialPeriod bean: finPeriodList) 
            {
    			v.add(bean);
            }
            
        } 
        catch (RuntimeException e) 
        {
        	AppUtility.logger.log(Level.SEVERE, "loading data failed ="+e.getMessage());
            throw e;

        } finally 
        {
            session.close();
        }
    }	
	
	private DefResultSet defResultSet;
	private SessionFactory sessionFactory;
	private GlFinancialPeriod[] financialPeriodBeans;
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Map<String, Object> parameters = new HashMap<String, Object>();	
	private Vector<GlFinancialPeriod> v = new Vector<GlFinancialPeriod>();

}
