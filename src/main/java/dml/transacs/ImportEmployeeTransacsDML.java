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
import app.beans.ImportEmployee;

public class ImportEmployeeTransacsDML implements DMLHandler 
{

	public ImportEmployeeTransacsDML()
	{
	}

	@Override
	public DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
			throws Exception 
	{
			if(obj instanceof ImportEmployee)
		{
			dmlResultSet = new DMLTransWrapper();
			
			impEmployee = (ImportEmployee) obj;
			AppUtility.logger.log(Level.INFO, toString(oprType));
			
			switch(oprType)
			{	
				case AppConstants.oprEdit:
					update();
					break;
				
				default:
					AppUtility.logger.log(Level.SEVERE, "Wrong operation type is requested "+oprType);
					break;
			}
			dmlResultSet.setImportEmployee(impEmployee);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "Import Employee having" + impEmployee.getEmployeeID() + " ID with title " + impEmployee.getFirstName() + " " + impEmployee.getLastName()
				+ " going to perform " 	+ oprType + " operation. reference batch id is " + impEmployee.getBatchID();
		return str;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
    }
	
	private void update() throws Exception 
	{
		try 
        {        	
        	session = sessionFactory.openSession();
        	
            tx = session.beginTransaction();
            session.update(impEmployee);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
            impEmployee.setExceptionMsgString(e.getMessage());
            throw e;

        } 
		finally 
        {
            session.close();
        }

	}
	
	private Transaction tx;
	private Session session ;
	private ImportEmployee impEmployee;
	private SessionFactory sessionFactory;
	private DMLTransWrapper dmlResultSet = null;
	

}
