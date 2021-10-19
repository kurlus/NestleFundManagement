package dml.def;

import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utilities.AppConstants;
import utilities.AppUtility;
import app.beans.GlSlMapping;

public class GlSlMappingDefDML implements DMLHandler 
{
	public GlSlMappingDefDML()
	{
	}
	
	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
			throws Exception 
	{
		if(obj instanceof GlSlMapping)
		{
			glSlMappingBean = (GlSlMapping) obj;
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
			dmlResultSet.setGlSlMapping(glSlMappingBean);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "Bank having" + glSlMappingBean.getGlSlID() + " ID with title "+ glSlMappingBean.getGlSlType()
					+ " going to perform " 	+ oprType + " operation. reference log id is " + glSlMappingBean.getLogID();
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
            session.save(glSlMappingBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            glSlMappingBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
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
            session.update(glSlMappingBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            glSlMappingBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
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
            session.delete(glSlMappingBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            glSlMappingBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
            

        } 
		finally 
        {
            session.close();
        }
	}

	
	private Transaction tx;
	private Session session ;
	private GlSlMapping glSlMappingBean;
	private DMLTransWrapper dmlResultSet;
    private SessionFactory sessionFactory;
}
