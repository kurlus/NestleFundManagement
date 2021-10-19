package dml.admin;

import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utilities.AppConstants;
import utilities.AppUtility;
import app.beans.WfEventAction;
import dml.def.DMLHandler;
import dml.def.DMLTransWrapper;
import exceptionhandling.ExceptionHandler;

public class WorkFlowEventActionAdminDML implements DMLHandler
{
	public WorkFlowEventActionAdminDML()
	{
		
	}

	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType) throws Exception 
	{
		if(obj instanceof WfEventAction)
		{
			dmlResultSet = new DMLTransWrapper();
			wfEventActionBean = (WfEventAction) obj;
			AppUtility.logger.log(Level.INFO, toString(oprType));
			wfEventActionBean.setExceptionMsgString(AppConstants.BLANK);
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
			dmlResultSet.setWfEventAction(wfEventActionBean);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "WF Event Action having" + wfEventActionBean.getActionID() + " ID with title "+ wfEventActionBean.getActionName()
					+ " going to perform " 	+ oprType + " operation. reference log id is " + wfEventActionBean.getLogID();
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
            session.save(wfEventActionBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            wfEventActionBean.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause()); 
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
            session.update(wfEventActionBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            wfEventActionBean.setExceptionMsgString(ExceptionHandler.getExceptionResultMSg (e.getMessage()+"Caused By : "+e.getCause(), wfEventActionBean.getActionName())); 
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
            session.delete(wfEventActionBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            wfEventActionBean.setExceptionMsgString(ExceptionHandler.getExceptionResultMSg (e.getMessage()+"Caused By : "+e.getCause(), wfEventActionBean.getActionName())); 
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
	private WfEventAction wfEventActionBean;
	

}
