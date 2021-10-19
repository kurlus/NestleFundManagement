package dml.def;

import app.beans.Bank;
import utilities.AppUtility;
import utilities.AppConstants;

import org.hibernate.Session;
import java.util.logging.Level;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

public class BankDefDML implements DMLHandler 
{
	public BankDefDML()
	{
	}
	
	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
			throws Exception 
	{
		if(obj instanceof Bank)
		{
			bankBean = (Bank) obj;
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
			dmlResultSet.setBankBean(bankBean);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "Bank having" + bankBean.getBankCode() + " ID with title "+ bankBean.getBankDescription()
					+ " going to perform " 	+ oprType + " operation. reference log id is " + bankBean.getLogID();
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
            session.save(bankBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            bankBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
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
            session.update(bankBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            bankBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
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
            session.delete(bankBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            bankBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
            

        } 
		finally 
        {
            session.close();
        }
	}

	
	private Transaction tx;
	private Session session ;
	private Bank bankBean;
	private DMLTransWrapper dmlResultSet;
    private SessionFactory sessionFactory;
}
