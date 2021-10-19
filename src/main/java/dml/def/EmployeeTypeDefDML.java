package dml.def;

import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utilities.AppConstants;
import utilities.AppUtility;
import app.beans.EmployeeType;

public class EmployeeTypeDefDML implements DMLHandler 
{
	public EmployeeTypeDefDML()
	{
	}
	
	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
			throws Exception 
	{
		if(obj instanceof EmployeeType)
		{
			employeeTypeBean = (EmployeeType) obj;
			dmlResultSet = new DMLTransWrapper();
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
			}
			dmlResultSet.setEmployeeTypeBean(employeeTypeBean);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "Bank having" + employeeTypeBean.getEmployeeTypeId() + " ID with title "+ employeeTypeBean.getEmployeeTypeDescription()
					+ " going to perform " 	+ oprType + " operation. reference log id is " + employeeTypeBean.getLogID();
		return str;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
    }

	private void save()  
	{
		try 
        {        	
        	session = sessionFactory.openSession();
        	tx = session.beginTransaction();
            session.save(employeeTypeBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            employeeTypeBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
            

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
            session.update(employeeTypeBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            employeeTypeBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
            

        } 
		finally 
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
            session.delete(employeeTypeBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            employeeTypeBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
            

        } 
		finally 
        {
            session.close();
        }
	}

	
	private Transaction tx;
	private Session session ;
	private EmployeeType employeeTypeBean;
	private DMLTransWrapper dmlResultSet;
    private SessionFactory sessionFactory;
}
