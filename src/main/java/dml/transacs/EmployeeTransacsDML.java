package dml.transacs;

import org.hibernate.Session;
import java.util.logging.Level;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import dml.def.DMLHandler;
import app.beans.Employee;
import utilities.AppUtility;
import utilities.AppConstants;
import dml.def.DMLTransWrapper;
import app.beans.EmployeeContacts;

public class EmployeeTransacsDML implements DMLHandler 
{
	public EmployeeTransacsDML()
	{
	}

	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType) throws Exception 
	{		
		
		if(obj instanceof Employee)
		{
			dmlResultSet = new DMLTransWrapper();
			
			employee = (Employee) obj;
			AppUtility.logger.log(Level.INFO, toString(oprType));
			
			switch(oprType)
			{				
				case AppConstants.oprSave:
					save();
					break;
				case AppConstants.oprEdit:
					update();
					break;
				case AppConstants.oprDel:
					delete();
					break;
				default:
					AppUtility.logger.log(Level.SEVERE, "Wrong operation type is requested "+oprType);
					break;
			}
			dmlResultSet.setEmployee(employee);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "Employee having" + employee.getEmployeeID() + " ID with title "
				+ " going to perform " 	+ oprType + " operation. reference log id is " + employee.getLogID();
		return str;
	}
	
	public  void setSessionFactory(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
    }

	private void save()
	{
		try 
        {        	
        	session = sessionFactory.openSession();
        	tx = session.beginTransaction();
        	
            session.save(employee);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+" Cause by "+e.getCause().toString());
            employee.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
           

        } 
		finally 
        {
            session.close();
        }
	}

	private void update()
	{
		try 
        {        	
        	session = sessionFactory.openSession();
        	
            tx = session.beginTransaction();
            session.update(employee);
            session.flush();
            tx.commit();        
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+" Cause by "+e.getCause().toString());
            employee.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
           

        } finally 
        {
            session.close();
        }

	}

	private void delete() 
	{
		try 
        {        	
        	session = sessionFactory.openSession();
        	tx = session.beginTransaction();
        	session.delete(employee);
            session.flush();
            tx.commit();        
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+" Cause by "+e.getCause().toString());
            employee.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
           

        } finally 
        {
            session.close();
        }

	}
	
	private Transaction tx;
	private Session session ;
	private Employee employee;
	private DMLTransWrapper dmlResultSet = null;
	private SessionFactory sessionFactory;

}
