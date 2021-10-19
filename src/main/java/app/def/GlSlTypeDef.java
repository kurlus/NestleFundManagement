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
import app.beans.GlSlType;
import database.DefQueries;
import utilities.AppUtility;
import utilities.AppConstants;
import app.beans.LoginEmployee;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

public class GlSlTypeDef implements Definition
{

	public GlSlTypeDef()
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
			glSlTypeBeans = new GlSlType[v.size()];		
			v.toArray(glSlTypeBeans);		
			defResultSet.setGlSlTypeBeans(glSlTypeBeans);
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
				query = session.createQuery(DefQueries.findGlSlType);
			} else if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findGlSlTypePosted);
			}
        	
            List<GlSlType> resultList =  query.list();
            System.out.println("GlSlType found: " + resultList.size());
            for(GlSlType bean: resultList) 
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
	

	private GlSlType[] glSlTypeBeans;
	private DefResultSet defResultSet;
	private SessionFactory sessionFactory;
	private Vector<GlSlType> v = new Vector<GlSlType>();
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Map<String, Object> parameters = new HashMap<String, Object>();	
}
