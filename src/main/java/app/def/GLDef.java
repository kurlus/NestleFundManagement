package app.def;

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

import app.beans.Glmf;
import app.Definition;
import app.DefResultSet;
import database.DMLBase;
import database.DefQueries;
import utilities.AppUtility;
import database.QueryWrapper;
import utilities.AppConstants;
import app.beans.GlParentData;
import database.DMLOperations;
import app.beans.LoginEmployee;

import static database.DefQueries.*;
import static utilities.AppConstants.*;



public class GLDef implements Definition 
{

	public GLDef() 
	{
	}
	
	@Override
	public synchronized DefResultSet loadDefinitionData(LoginEmployee loginEmployee) throws Exception
	{
		try
		{
		v.clear();
	
		parameters.clear();
		defResultSet = new DefResultSet();
		this.setLoginEmployee(loginEmployee);
		
		populateGLParentData();
		if (v.size() > AppConstants.zero)
		{
			glBeans = new GlParentData[v.size()];		
			v.toArray(glBeans);		
			defResultSet.setGlBeans(glBeans);
		}
		
		execute();
		defResultSet.setGlmfBeans(glmfBeans);
		if (vGlmf.size() > AppConstants.zero)
		{
			//glmfBeans = new Glmf[vGlmf.size()];		
			//.toArray(glmfBeans);		
			
		}
		
		DMLBase.loadUserRights(this.getLoginEmployee());
		defResultSet.setLoginEmployee(getLoginEmployee());
		}
		catch(Exception ex)
		{
			AppUtility.logger.log(Level.SEVERE, ex.getMessage());
			AppUtility.logger.log(Level.SEVERE, ex.getLocalizedMessage());
		}
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
	
	public  void setSessionFactory(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
    }
	
	private void execute() throws MappingException, IOException, Exception 
    {
        Session session = sessionFactory.openSession();
        Query query = null;
        try 
        { 	
        	if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterByALL)))
			{
				query = session.createQuery(DefQueries.findGlmfQuery);
			} else if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findGlmfPostedQuery);
			}
			else if(loginEmployee.getFilterBy()!= null && loginEmployee.getFilterBy().equals(filterBySelected))
        	{
        		query = session.createQuery(DefQueries.findGlmfLevel7Query);
        	}
           
            List<Glmf> resultlist = query.list();
            System.out.println("Glmf data found: " + resultlist.size());
            glmfBeans=resultlist;
            /*for(Glmf bean: resultlist) 
            {
    			vGlmf.add(bean);
            }*/
            
        } catch (RuntimeException e) 
        {
        	AppUtility.logger.log(Level.SEVERE, "loading data failed ="+e.getMessage());
            throw e;

        } 
        finally 
        {
            session.close();
        }
    }
	
	private void populateGLParentData() throws Exception
	{ 
		try
		{
			QueryWrapper qWrapper = new QueryWrapper(glParentDataQuery, parameters);
			List<Map<String, Object>> glList = DMLOperations.queryList(qWrapper);
	
			for (Map<String, Object> entry : glList) 
			{	
				String glmfCode = (String) entry.get(glMFCode);
				String glmfDesc = (String) entry.get(glMFDescription);
				String glmfLevel = String.valueOf(entry.get(glMFlevel));
				String glmfLevelDesc = (String) entry.get(glMFlevelDesc);
				String glmfFamilyID = (String) entry.get(glMFFamilyID);
	
				GlParentData bean = getParentDataFactory();
				
				bean.setParentCode(glmfCode);
				bean.setParentLevel(glmfLevel);
				bean.setParentFamily(glmfFamilyID);
				bean.setParentDescription(glmfDesc);
				bean.setGlLevelDescription(glmfLevelDesc);
				
				v.add(bean);
			}
		}
		catch(Exception ex)
		{
			AppUtility.logger.log(Level.INFO, ex.getMessage());
			throw ex;
		}

	}

	private synchronized void setListData(Object[] listOfObjs) 
	{
	}

	private GlParentData getParentDataFactory()
	{
		return new GlParentData();
	}

	private List<Glmf> glmfBeans;
	private GlParentData[] glBeans;
	private DefResultSet defResultSet;
	private LoginEmployee loginEmployee;
	private SessionFactory sessionFactory;
	private Vector<GlParentData> v = new Vector<GlParentData>();
	private Vector<Glmf> vGlmf = new Vector<Glmf>();
	private Map<String, Object> parameters = new HashMap<String, Object>();	

}
