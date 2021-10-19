package app.def;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.util.logging.Level;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

import app.DefResultSet;
import app.Definition;
import app.beans.GlFamilyType;
import app.beans.LoginEmployee;

import java.io.IOException;

import database.DefQueries;
import utilities.AppUtility;
import utilities.AppConstants;

public class GlFamilyDef implements Definition 
{
	public GlFamilyDef()
	{
	}

	public void setSessionFactory(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
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
			glFamilyTypeBeans = new GlFamilyType[v.size()];		
			v.toArray(glFamilyTypeBeans);		
			defResultSet.setGlFamilyTypeBeans(glFamilyTypeBeans);
		}
		
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
        	query = session.createQuery(DefQueries.findGlFamilyTypeQuery);
            List<GlFamilyType> resultlist = query.list();
            System.out.println("gl family taypes data found: " + resultlist.size());
            for(GlFamilyType bean: resultlist) 
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
	private SessionFactory sessionFactory;
	private GlFamilyType[] glFamilyTypeBeans;
	private Vector<GlFamilyType> v = new Vector<GlFamilyType>();
	private Map<String, Object> parameters = new HashMap<String, Object>();	
	

}
