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

import app.DefResultSet;
import app.Definition;
import database.DefQueries;
import utilities.AppUtility;
import app.beans.LoaderInfo;
import utilities.AppConstants;
import app.beans.LoginEmployee;

public class LoaderInfoTransacs implements Definition 
{
	public LoaderInfoTransacs()
	{	
	}
	
	@Override
	public synchronized DefResultSet loadDefinitionData(LoginEmployee loginEmployee)
			throws Exception 
	{
		v.clear();
		parameters.clear();
		defResultSet = new DefResultSet();
		execute();

		if (v.size() > AppConstants.zero)
		{
			loaderInfoBeans = new LoaderInfo[v.size()];		
			v.toArray(loaderInfoBeans);		
			defResultSet.setLoaderInfo(loaderInfoBeans);
		}
		
		return defResultSet;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
    }

	private void setListData(Object[] listOfObjs)
	{
	}
	
	private void execute() throws MappingException, IOException, Exception 
    {
        Session session = sessionFactory.openSession();
        Query query = null;
        try 
        {
        	query = session.createQuery(DefQueries.findLoaderInfoQuery);
            List<LoaderInfo> resultList =  query.list();
            System.out.println("loader info found: " + resultList.size());
            for(LoaderInfo bean: resultList) 
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
	private LoaderInfo[] loaderInfoBeans;
	private SessionFactory sessionFactory;
	private Vector<LoaderInfo> v = new Vector<LoaderInfo>();
	private Map<String, Object> parameters = new HashMap<String, Object>();	
	
}
