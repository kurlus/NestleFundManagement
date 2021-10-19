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

import database.DMLBase;
import database.DefQueries;

import utilities.AppUtility;
import utilities.AppConstants;

import app.Definition;
import app.DefResultSet;
import database.DMLOperations;
import app.beans.LoginEmployee;
import app.beans.ImportLoanApply;

public class ImportLoanApplyTransacs implements Definition
{
	public ImportLoanApplyTransacs()
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
			importLoanApplyBeans = new ImportLoanApply[v.size()];		
			v.toArray(importLoanApplyBeans);		
			defResultSet.setImportLoanApplyBeans(importLoanApplyBeans);
		}
		
		DMLBase.loadUserRights(this.getLoginEmployee());
		defResultSet.setLoginEmployee(getLoginEmployee());
		
		return defResultSet;
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

	private void setListData(Object[] listOfObjs) 
	{
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
        	Map output = DMLOperations.callProcedure(null, AppConstants.prcImportLoanApplyValidationAll, map);
           
        	query = session.createQuery(DefQueries.findImportLoanApply);
        	query.setParameter(AppConstants.parameterBatchID, this.getLoginEmployee().getBatchID());
            List<ImportLoanApply> resultList =  query.list();
            System.out.println("Import Loan Apply found: " + resultList.size());
            for(ImportLoanApply bean: resultList) 
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
	private ImportLoanApply[] importLoanApplyBeans;
	private Vector<ImportLoanApply> v = new Vector<ImportLoanApply>();
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Map<String, Object> parameters = new HashMap<String, Object>();	

}
