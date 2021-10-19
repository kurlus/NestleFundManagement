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

import utilities.AppUtility;
import utilities.AppConstants;

import app.DefResultSet;
import app.Definition;
import app.beans.CostCentre;
import app.beans.LoginEmployee;

public class CostCentreDef implements Definition 
{
	public CostCentreDef() 
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
	
	public  void setSessionFactory(SessionFactory sessionFactory) 
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
			costCentreBeans = new CostCentre[v.size()];		
			v.toArray(costCentreBeans);		
			defResultSet.setCostCentreBeans(costCentreBeans);
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
		Query query = null;
        Session session = sessionFactory.openSession();
       
        try 
        {	
        	if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterByALL)))
			{
				query = session.createQuery(DefQueries.findCostCentreQuery);
			} else if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findCostCentrePostedQuery);
			}
        	
            List<CostCentre> resultlist = query.list();
            System.out.println("Cost Centre Data found: " + resultlist.size());
            for(CostCentre bean: resultlist) 
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
	private CostCentre[] costCentreBeans;
	private SessionFactory sessionFactory;
	private Vector<CostCentre> v = new Vector<CostCentre>();
	private Map<String, Object> parameters = new HashMap<String, Object>();	

}
