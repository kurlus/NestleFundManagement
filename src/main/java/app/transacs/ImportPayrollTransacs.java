package app.transacs;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.io.IOException;
import java.util.logging.Level;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;
import org.hibernate.criterion.Restrictions;

import app.Definition;
import database.DMLBase;
import app.DefResultSet;
import utilities.AppUtility;
import utilities.AppConstants;
import database.DMLOperations;
import app.beans.ImportPayroll;
import app.beans.LoginEmployee;

public class ImportPayrollTransacs implements Definition 
{
	public ImportPayrollTransacs()
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
			importPayrollBeans = new ImportPayroll[v.size()];		
			v.toArray(importPayrollBeans);		
			defResultSet.setImportPayroll(importPayrollBeans);
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
		Session session = null;
		Map<String, Object> map = null;
      try 
        {
        	map = new HashMap<String, Object>();
        	map.put(AppConstants.paramEmployeeValidationBatchID, this.getLoginEmployee().getBatchID());
        	Map output = DMLOperations.callProcedure(null, AppConstants.prcImportPayrollValidationAll, map);
                    
            if(sessionFactory == null)
        		sessionFactory = (SessionFactory)AppUtility.getWebApplicationContext().getBean(AppConstants.hibernateSessionFactory);
        	
            session = sessionFactory.openSession();
            
            Criteria criteria = session.createCriteria(app.beans.ImportPayroll.class);
            criteria.add(Restrictions.eq(AppConstants.importPayRollBatchIDParam, getLoginEmployee().getBatchID()));
            List<ImportPayroll> resultListPayRoll = criteria.list();
            
            System.out.println("ImportPayroll found: " + resultListPayRoll.size());
            
            for(ImportPayroll bean: resultListPayRoll) 
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
	
	private ImportPayroll[] importPayrollBeans;
	private DefResultSet defResultSet;
	private SessionFactory sessionFactory;
	private Vector<ImportPayroll> v = new Vector<ImportPayroll>();
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Map<String, Object> parameters = new HashMap<String, Object>();	
}
