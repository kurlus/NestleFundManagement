package app.transacs;


import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.util.HashSet;
import java.io.IOException;
import java.util.logging.Level;

import utilities.AppUtility;
import utilities.AppConstants;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

import database.DMLBase;
import database.DefQueries;

import app.Definition;
import app.DefResultSet;
import app.beans.Employee;
import app.beans.LoginEmployee;
import app.beans.EmployeeContacts;

public class EmployeeTransacs implements Definition 
{
	
	public EmployeeTransacs() 
	{
	}
	
	@Override
	public  DefResultSet loadDefinitionData(LoginEmployee loginEmployee) throws Exception 
	{
		v.clear();
		parameters.clear();
		defResultSet = new DefResultSet();
		this.setLoginEmployee(loginEmployee);
		execute();

		if (v.size() > AppConstants.zero)
		{
			employeeBeans = new Employee[v.size()];		
			v.toArray(employeeBeans);		
			defResultSet.setEmployeeBeans(employeeBeans);
		}
		DMLBase.loadUserRights(this.getLoginEmployee());
		defResultSet.setLoginEmployee(getLoginEmployee());
		return defResultSet;
	}
	
	public  LoginEmployee getLoginEmployee() 
	{
		return loginEmployee;
	}

	public  void setLoginEmployee(LoginEmployee loginEmployee) 
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
	
	private  void execute() throws MappingException, IOException, Exception 
    {
        Query query =null;
        EmployeeContacts contact ;
        HashSet<EmployeeContacts> contacts ; 
        
        if(sessionFactory == null)
    		sessionFactory = (SessionFactory)AppUtility.getWebApplicationContext().getBean(AppConstants.hibernateSessionFactory);
    	
        Session session = sessionFactory.openSession();
        
        try 
        {
        	if (loginEmployee.getFilterBy()!= null && (loginEmployee.getFilterBy().equals(AppConstants.filterByALL)))
			{
				query = session.createQuery(DefQueries.findEmployeeQuery);
			} 
			else if (loginEmployee.getFilterBy()!= null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findEmployeePostedQuery);
			} 
        	
            List<Employee> resultlist = query.list();
            System.out.println("employee data found: " + resultlist.size());
            
            for(Employee bean: resultlist) 
            {
            	contacts = new HashSet<EmployeeContacts>();
            	Query query1 = session.createQuery("from EmployeeContacts where employee_id='"+bean.getEmployeeID()+"'");
            	List<EmployeeContacts> resultContactList = query1.list();
            	
            	for(EmployeeContacts employeeContacts : resultContactList)
            	{
            		
        			contact = getEmployeeContactFactory();
        			contact.setPost(employeeContacts.getPost());
        			contact.setEmployeeID(bean.getEmployeeID());
        			contact.setLogID(employeeContacts.getLogID());
        			contact.setContact(employeeContacts.getContact());
        			contact.setAddressLine1(employeeContacts.getAddressLine1());
        			contact.setAddressLine2(employeeContacts.getAddressLine2());
        			contact.setCityShort(employeeContacts.getCityShort());
        			contact.setContactID(employeeContacts.getContactID());
        			contact.setAddressType(employeeContacts.getAddressType());
        			contact.setCountryShort(employeeContacts.getCountryShort());
        			contacts.add(contact);
            	}
            	
            	bean.setEmployeeContacts(contacts);
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
	
	private EmployeeContacts getEmployeeContactFactory()
	{
		return new EmployeeContacts();
	}
	
	private Employee[] employeeBeans;
	private DefResultSet defResultSet;
	private LoginEmployee loginEmployee;
	private SessionFactory sessionFactory;
	private Vector<Employee> v = new Vector<Employee>();
	private Map<String, Object> parameters = new HashMap<String, Object>();

}
