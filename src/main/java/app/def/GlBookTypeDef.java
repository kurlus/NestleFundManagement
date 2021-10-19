package app.def;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.io.IOException;
import java.util.logging.Level;

import database.DMLBase;
import database.DefQueries;

import org.hibernate.Query;

import utilities.AppUtility;
import app.DefResultSet;
import app.Definition;
import app.beans.GlBookType;

import org.hibernate.Session;

import utilities.AppConstants;
import app.beans.LoginEmployee;

import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

public class GlBookTypeDef implements Definition
{

	public GlBookTypeDef()
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
			glBookTypeBeans = new GlBookType[v.size()];		
			v.toArray(glBookTypeBeans);		
			defResultSet.setGlBookTypeBeans(glBookTypeBeans);
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
				query = session.createQuery(DefQueries.findGlBookType);
			} else if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findGlBookTypePosted);
			}
        	
            List<GlBookType> resultList =  query.list();
            System.out.println("GlBookType found: " + resultList.size());
            for(GlBookType bean: resultList) 
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
	private GlBookType[] glBookTypeBeans;
	private SessionFactory sessionFactory;
	private Vector<GlBookType> v = new Vector<GlBookType>();
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Map<String, Object> parameters = new HashMap<String, Object>();	


}
