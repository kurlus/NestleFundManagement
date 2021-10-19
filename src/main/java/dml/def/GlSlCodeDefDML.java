package dml.def;

import org.hibernate.Session;

import java.util.logging.Level;

import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import app.beans.GlSlCode;
import utilities.AppUtility;
import utilities.AppConstants;

public class GlSlCodeDefDML implements DMLHandler
{
	public GlSlCodeDefDML()
	{
	}

	@Override
	public DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
	  
	{
		if(obj instanceof GlSlCode)
		{
			dmlResultSet = new DMLTransWrapper();
			glslCode = (GlSlCode) obj;
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
			dmlResultSet.setGlSlCode(glslCode);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "Loan type having" + glslCode.getSlCode()+ " ID with title "+ glslCode.getSlDescription() 
					+ " going to perform " 	+ oprType + " operation. reference log id is "+glslCode.getLogID();
		return str;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
    }

	private synchronized void save()   
	{
		
        try 
        {        	
        	session = sessionFactory.openSession();
        	tx = session.beginTransaction();
            session.save(glslCode);
            session.flush();
            tx.commit();        
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+"Caused By : "+e.getCause());
            glslCode.setExceptionMsgString(e.getMessage()+" Caused By : "+e.getCause());

        } finally 
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
            session.update(glslCode);
            session.flush();
            tx.commit();        
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+"Caused By : "+e.getCause());
            glslCode.setExceptionMsgString(e.getMessage()+" Caused By : "+e.getCause());

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
            session.delete(glslCode);
            session.flush();
            tx.commit();        
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+"Caused By : "+e.getCause());
            glslCode.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause());

        } finally 
        {
            session.close();
        }
		
	}
	
	private Transaction tx;
	private Session session ;
	private GlSlCode glslCode;
	private DMLTransWrapper dmlResultSet = null;
    private SessionFactory sessionFactory;

}

