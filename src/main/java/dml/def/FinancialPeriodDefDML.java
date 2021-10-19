package dml.def;

import utilities.AppUtility;
import utilities.AppConstants;
import java.util.logging.Level;
import app.beans.GlFinancialPeriod;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;


public class FinancialPeriodDefDML implements DMLHandler 
{

	public FinancialPeriodDefDML()
	{
	}
	
	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType) throws Exception 
	{
		if(obj instanceof GlFinancialPeriod)
		{
			dmlResultSet = new DMLTransWrapper();
			
			glFinancialPeriod = (GlFinancialPeriod) obj;
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
			dmlResultSet.setGlFinancialPeriod(glFinancialPeriod);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "financial Period having" + glFinancialPeriod.getPeriodId() + " ID with title " + glFinancialPeriod.getPeriodDesc()
				+ " going to perform " 	+ oprType + " operation. reference log id is " ;
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
        	
            session.save(glFinancialPeriod);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            glFinancialPeriod.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+" Cause by "+e.getCause().toString());
            

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
        	
            session.update(glFinancialPeriod);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            glFinancialPeriod.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+" Cause by "+e.getCause().toString());
           

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
        	
            session.delete(glFinancialPeriod);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            glFinancialPeriod.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+" Cause by "+e.getCause().toString());
            

        } 
		finally 
        {
            session.close();
        }

	}
	
	
	private Transaction tx;
	private Session session ;
	private SessionFactory sessionFactory;
	private GlFinancialPeriod glFinancialPeriod;
	private DMLTransWrapper dmlResultSet = null;
	

}
