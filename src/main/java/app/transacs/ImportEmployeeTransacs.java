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
import database.DefQueries;
import utilities.AppUtility;
import database.DMLOperations;
import utilities.AppConstants;
import app.beans.ImportEmployee;
import app.beans.LoginEmployee;

public class ImportEmployeeTransacs implements Definition
{
	
	public ImportEmployeeTransacs()
	{
	}
	
	@Override
	public DefResultSet loadDefinitionData(LoginEmployee loginEmployee)
			throws Exception 
	{
		v.clear();
		parameters.clear();
		defResultSet = new DefResultSet();
		this.setLoginEmployee(loginEmployee);
		
		execute();

		if (v.size() > AppConstants.zero)
		{
			importEmployeeBeans = new ImportEmployee[v.size()];		
			v.toArray(importEmployeeBeans);		
			defResultSet.setImportEmplyeeBeans(importEmployeeBeans);
		}
		
		DMLBase.loadUserRights(this.getLoginEmployee());
		defResultSet.setLoginEmployee(getLoginEmployee());
		
		return defResultSet;
	}
	
	public  LoginEmployee getLoginEmployee() 
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

	private void setListData(Object[] listOfObjs) 
	{
		// TODO Auto-generated method stub
	}
	
	private void execute() throws MappingException, IOException, Exception 
    {
		Query query = null;
        Map<String, Object> map = new HashMap<String, Object>();
        Session session = null;
        
        try 
        {
        	if(sessionFactory == null)
        		sessionFactory = (SessionFactory)AppUtility.getWebApplicationContext().getBean(AppConstants.hibernateSessionFactory);
        	
            session = sessionFactory.openSession();
        	
        	map.put(AppConstants.paramEmployeeValidationBatchID, this.getLoginEmployee().getBatchID());
        	Map output = DMLOperations.callProcedure(null, AppConstants.prcEmployeeValidationAll, map);
        	
        	query = session.createQuery(DefQueries.findImportEmployee);
        	query.setParameter(AppConstants.parameterBatchID, this.getLoginEmployee().getBatchID());
        	
            List<ImportEmployee> resultList =  query.list();
            System.out.println("ImportEmployee found: " + resultList.size());
            for(ImportEmployee bean: resultList) 
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
	private ImportEmployee[] importEmployeeBeans;
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Vector<ImportEmployee> v = new Vector<ImportEmployee>();
	private Map<String, Object> parameters = new HashMap<String, Object>();	
}
