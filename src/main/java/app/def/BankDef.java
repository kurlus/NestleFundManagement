package app.def;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.io.IOException;
import java.util.logging.Level;

import app.beans.Bank;
import app.Definition;
import app.DefResultSet;
import utilities.AppUtility;
import utilities.AppConstants;
import app.beans.LoginEmployee;

import database.DMLBase;
import database.DefQueries;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

public class BankDef implements Definition 
{
	public BankDef() 
	{
	}
	
	@Override
	public synchronized DefResultSet loadDefinitionData(LoginEmployee loginEmployee) throws Exception 
	{
		v.clear();
		parameters.clear();
		defResultSet = new DefResultSet();
		this.setLoginEmployee(loginEmployee);
		execute();

		if (v.size() > AppConstants.zero)
		{
			glBankBeans = new Bank[v.size()];		
			v.toArray(glBankBeans);		
			defResultSet.setGlBankBeans(glBankBeans);
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

	private synchronized void setListData(Object[] listOfObjs) {
		// TODO Auto-generated method stub
	}
	
	private void execute() throws MappingException, IOException, Exception 
    {
		Query query = null;
        Session session = sessionFactory.openSession();        
        
        try 
        {	
        	if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterByALL)))
			{
				query = session.createQuery(DefQueries.findBankQuery);
			} else if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findBankPostedQuery);
			}
        	
            List<Bank> resultlist = query.list();
            System.out.println("banks data found: " + resultlist.size());
            for(Bank bean: resultlist) 
            {
    			v.add(bean);
            }
            
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
	
	
	private Bank[] glBankBeans;
	private DefResultSet defResultSet;
	private LoginEmployee loginEmployee;
	private SessionFactory sessionFactory;
	private Vector<Bank> v = new Vector<Bank>();
	private Map<String, Object> parameters = new HashMap<String, Object>();

}
