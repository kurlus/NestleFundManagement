package dml.admin;

import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import app.beans.Roles;
import dml.def.DMLHandler;
import utilities.AppUtility;
import utilities.AppConstants;
import dml.def.DMLTransWrapper;

public class UserRoleAdminDML implements DMLHandler 
{

	public UserRoleAdminDML()
	{
	}

	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
	 
	{
		if(obj instanceof Roles)
		{
			dmlResultSet = new DMLTransWrapper();
			userRoleBean = (Roles) obj;
			AppUtility.logger.log(Level.INFO, toString(oprType));
			userRoleBean.setExceptionMsgString(AppConstants.BLANK);
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
			dmlResultSet.setUserRoleBean(userRoleBean);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "User Role having" + userRoleBean.getRoleId() + " ID with title "+ userRoleBean.getRoleDescription()
					+ " going to perform " 	+ oprType + " operation. reference log id is " + userRoleBean.getLogID();
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
            session.save(userRoleBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            userRoleBean.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause()); 
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
            session.update(userRoleBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            userRoleBean.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause()); 
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
            session.delete(userRoleBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            userRoleBean.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause()); 
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
             

        } 
		finally 
        {
            session.close();
        }
		
	}
	
	private Transaction tx;
	private Session session ;
	private Roles userRoleBean;
	private DMLTransWrapper dmlResultSet;
    private SessionFactory sessionFactory;

}
