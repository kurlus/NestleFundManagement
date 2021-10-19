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

import app.Definition;
import app.DefResultSet;
import database.DMLBase;
import database.DefQueries;
import utilities.AppUtility;
import utilities.AppConstants;
import app.beans.GlBankBranch;
import app.beans.LoginEmployee;

public class BankBranchDef implements Definition 
{

	public BankBranchDef() 
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
			glBankBranchBeans = new GlBankBranch[v.size()];		
			v.toArray(glBankBranchBeans);		
			defResultSet.setGlBankBranchBeans(glBankBranchBeans);
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
	
	public  void setSessionFactory(SessionFactory sessionFactory) 
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
        	if (loginEmployee.getFilterBy()!= null && (loginEmployee.getFilterBy().equals(AppConstants.filterByALL)))
			{
				query = session.createQuery(DefQueries.findBankBranchQuery);
			} 
			else if (loginEmployee.getFilterBy()!= null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findBankBranchPostedQuery);
			} 
            List<GlBankBranch> resultlist = query.list();
            System.out.println("bank branch data found: " + resultlist.size());
            for(GlBankBranch bean: resultlist) 
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
	
	private DefResultSet defResultSet;
	private LoginEmployee loginEmployee;
	private SessionFactory sessionFactory;
	private GlBankBranch[] glBankBranchBeans;
	private Vector<GlBankBranch> v = new Vector<GlBankBranch>();
	private Map<String, Object> parameters = new HashMap<String, Object>();
}
