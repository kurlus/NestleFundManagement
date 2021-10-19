package app.def;



import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.io.IOException;
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
import app.beans.Employee;
import app.beans.EmployeeContacts;
import app.beans.LoginEmployee;
import app.beans.Payroll;
import app.beans.PayRollLoan;
import app.beans.PayRollPFContribution;
import app.beans.PayRollPNContribution;
import database.DMLBase;
import database.DefQueries;

public class PayRollDef implements Definition 
{
	public PayRollDef() 
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
			payRollBeans = new Payroll[v.size()];		
			v.toArray(payRollBeans);		
			defResultSet.setPayRollBeans(payRollBeans);
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
				query = session.createQuery(DefQueries.findPayRollQuery);
			} 
        	else if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findPayRollPostedQuery);
			}
        	
            List<Payroll> resultlist = query.list();
            System.out.println("PayRoll data found: " + resultlist.size());
            
            for(Payroll bean: resultlist) 
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
	
	private PayRollLoan getPayRollLoanFactory()
	{
		return new PayRollLoan();
	}
	
	private PayRollPFContribution getPayRollPFContributionFactory()
	{
		return new PayRollPFContribution();
	}
	
	private PayRollPNContribution getPayRollPNContributionFactory()
	{
		return new PayRollPNContribution();
	}
	
	private Payroll[] payRollBeans;
	private DefResultSet defResultSet;
	private LoginEmployee loginEmployee;
	private SessionFactory sessionFactory;
	private Vector<Payroll> v = new Vector<Payroll>();
	private Map<String, Object> parameters = new HashMap<String, Object>();

}
