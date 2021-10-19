package dml.def;

import org.hibernate.Session;
import java.util.logging.Level;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import utilities.AppConstants;
import utilities.AppUtility;
import app.beans.GlBookType;

public class GlBookTypeDefDML implements DMLHandler 
{

	public GlBookTypeDefDML()
	{
	}
	
	@Override
	public DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
			throws Exception 
	{
		if(obj instanceof GlBookType)
		{
			dmlResultSet = new DMLTransWrapper();
			glBookTypeBean = (GlBookType) obj;
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
			dmlResultSet.setGlbookType(glBookTypeBean);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "Gl book type having" + glBookTypeBean.getBookType() + " ID with title "+ glBookTypeBean.getDescription()
					+ " going to perform " 	+ oprType + " operation. reference log id is " + glBookTypeBean.getLogID();
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
            session.save(glBookTypeBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            glBookTypeBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
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
            session.update(glBookTypeBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            glBookTypeBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
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
            session.delete(glBookTypeBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            glBookTypeBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
          } 
		finally 
        {
            session.close();
        }

	}
	
	private Transaction tx;
	private Session session ;
	private GlBookType glBookTypeBean;
	private DMLTransWrapper dmlResultSet;
    private SessionFactory sessionFactory;

}
