package dml.admin;

import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utilities.AppConstants;
import utilities.AppUtility;
import app.beans.WfEventAction;
import app.beans.WfTransition;
import dml.def.DMLHandler;
import dml.def.DMLTransWrapper;
import exceptionhandling.ExceptionHandler;

public class WorkFlowTransitionAdminDML implements DMLHandler
{
	public WorkFlowTransitionAdminDML()
	{}

	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType)throws Exception 
	{
		if(obj instanceof WfTransition)
		{
			dmlResultSet = new DMLTransWrapper();
			wfTransitionBean = (WfTransition) obj;
			AppUtility.logger.log(Level.INFO, toString(oprType));
			wfTransitionBean.setExceptionMsg(AppConstants.BLANK);
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
			dmlResultSet.setWfTransitionBean(wfTransitionBean);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "WF Event transition having" + wfTransitionBean.getTransitionCode() + " ID with title "+ wfTransitionBean.getTransitionName()
					+ " going to perform " 	+ oprType + " operation. reference log id is " + wfTransitionBean.getLogID();
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
            session.save(wfTransitionBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            
            wfTransitionBean.setExceptionMsg(ExceptionHandler.getExceptionResultMSg (e.getMessage()+"Caused By : "+e.getCause(), wfTransitionBean.getTransitionCode())); 
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
            session.update(wfTransitionBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            wfTransitionBean.setExceptionMsg(ExceptionHandler.getExceptionResultMSg (e.getMessage()+"Caused By : "+e.getCause(), wfTransitionBean.getTransitionCode())); 
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
            session.delete(wfTransitionBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            wfTransitionBean.setExceptionMsg(e.getMessage()+"Caused By : "+e.getCause()); 
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
	private WfTransition wfTransitionBean;
	

}
