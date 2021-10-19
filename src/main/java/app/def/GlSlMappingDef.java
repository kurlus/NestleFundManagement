package app.def;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.io.IOException;
import java.util.logging.Level;

import app.Definition;
import database.DMLBase;
import app.DefResultSet;
import database.DefQueries;
import utilities.AppUtility;
import app.beans.GlSlMapping;
import utilities.AppConstants;
import app.beans.LoginEmployee;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

public class GlSlMappingDef implements Definition 
{
	public GlSlMappingDef()
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
			glSlMappingBeans = new GlSlMapping[v.size()];		
			v.toArray(glSlMappingBeans);		
			defResultSet.setGlSlMappingBeans(glSlMappingBeans);
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
				query = session.createQuery(DefQueries.findGlSlMapping);
			} else if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findGlSlMappingPosted);
			}else if (loginEmployee.getFilterBy() != null ){
				query = session.createQuery(DefQueries.findGlSlMappingByGlSlCode);
				query.setParameter(AppConstants.glMFCode,loginEmployee.getFilterBy());
			}
            List<GlSlMapping> resultList =  query.list();
            System.out.println("GlSlMapping found: " + resultList.size());
            for(GlSlMapping bean: resultList) 
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
	
	private GlSlMapping[] glSlMappingBeans;
	private DefResultSet defResultSet;
	private SessionFactory sessionFactory;
	private Vector<GlSlMapping> v = new Vector<GlSlMapping>();
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Map<String, Object> parameters = new HashMap<String, Object>();	
}
