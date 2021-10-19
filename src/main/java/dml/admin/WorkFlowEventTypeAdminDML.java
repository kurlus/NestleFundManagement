package dml.admin;

import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



import utilities.AppUtility;
import app.beans.WfEventType;
import dml.def.DMLHandler;
import utilities.AppConstants;
import dml.def.DMLTransWrapper;
import exceptionhandling.ExceptionHandler;

public class WorkFlowEventTypeAdminDML implements DMLHandler
{
	public WorkFlowEventTypeAdminDML()
	{}

	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType)throws Exception 
	{
		if(obj instanceof WfEventType)
		{
			dmlResultSet = new DMLTransWrapper();
			wfEventTypeBean = (WfEventType) obj;
			AppUtility.logger.log(Level.INFO, toString(oprType));
			wfEventTypeBean.setExceptionMsgString(AppConstants.BLANK);
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
			dmlResultSet.setWfEventType(wfEventTypeBean);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "WF Event type having" + wfEventTypeBean.getEventType() + " ID with title "+ wfEventTypeBean.getEventTypeName()
					+ " going to perform " 	+ oprType + " operation. reference log id is " + wfEventTypeBean.getLogID();
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
            session.save(wfEventTypeBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            wfEventTypeBean.setExceptionMsgString(ExceptionHandler.getExceptionResultMSg (e.getMessage()+"Caused By : "+e.getCause(), wfEventTypeBean.getEventType())); 
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
            session.update(wfEventTypeBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            wfEventTypeBean.setExceptionMsgString(ExceptionHandler.getExceptionResultMSg (e.getMessage()+"Caused By : "+e.getCause(), wfEventTypeBean.getEventType())); 
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
            session.delete(wfEventTypeBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            wfEventTypeBean.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause()); 
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
             

        } 
		finally 
        {
            session.close();
        }
		
	}
	
	private Transaction tx;
	private Session session;
	private DMLTransWrapper dmlResultSet;
    private SessionFactory sessionFactory;
	private WfEventType wfEventTypeBean;
	

}
