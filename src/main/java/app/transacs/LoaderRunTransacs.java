package app.transacs;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.io.IOException;
import java.util.logging.Level;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

import app.Definition;
import database.DMLBase;
import app.DefResultSet;
import app.beans.LoaderRun;
import database.DefQueries;
import utilities.AppUtility;
import utilities.AppConstants;
import app.beans.LoginEmployee;

public class LoaderRunTransacs implements Definition 
{
	public LoaderRunTransacs()
	{
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
			loaderRunBeans = new LoaderRun[v.size()];		
			v.toArray(loaderRunBeans);		
			defResultSet.setLoaderRunBeans(loaderRunBeans);
		}
		DMLBase.loadUserRights(this.getLoginEmployee());
		defResultSet.setLoginEmployee(getLoginEmployee());
		return defResultSet;
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
            
        	query = session.createQuery(DefQueries.findLoaderRunQuery);
        	query.setParameter(AppConstants.loaderRunID, loginEmployee.getLoaderID());
            List<LoaderRun> resultlist = query.list();
            for(LoaderRun bean: resultlist) 
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
	private LoaderRun[] loaderRunBeans;
	private LoginEmployee loginEmployee;
	private SessionFactory sessionFactory;
	private Vector<LoaderRun> v = new Vector<LoaderRun>();
	private Map<String, Object> parameters = new HashMap<String, Object>();

}
