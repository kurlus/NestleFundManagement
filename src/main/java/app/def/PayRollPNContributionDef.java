package app.def;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;

import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import utilities.AppConstants;
import utilities.AppUtility;
import app.DefResultSet;
import app.Definition;
import app.beans.Bank;
import app.beans.LoginEmployee;
import app.beans.PayRollPNContribution;
import database.DMLBase;
import database.DefQueries;

public class PayRollPNContributionDef implements Definition 
{
	public PayRollPNContributionDef() 
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
			payRollPNContributionBeans = new PayRollPNContribution[v.size()];		
			v.toArray(payRollPNContributionBeans);		
			defResultSet.setPayRollPNContribution(payRollPNContributionBeans);		}
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
				query = session.createQuery(DefQueries.findPayRollPNContribQuery);
			} else if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				//query = session.createQuery(DefQueries.findBankPostedQuery);
			}
        	
            List<PayRollPNContribution> resultlist = query.list();
            System.out.println("PayRollPNContribution data found: " + resultlist.size());
            for(PayRollPNContribution bean: resultlist) 
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
	
	
	private PayRollPNContribution[] payRollPNContributionBeans;
	private DefResultSet defResultSet;
	private LoginEmployee loginEmployee;
	private SessionFactory sessionFactory;
	private Vector<PayRollPNContribution> v = new Vector<PayRollPNContribution>();
	private Map<String, Object> parameters = new HashMap<String, Object>();

}
