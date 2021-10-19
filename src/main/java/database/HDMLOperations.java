package database;

import java.io.Serializable;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utilities.AppConstants;
import utilities.AppUtility;
import app.beans.TransactionLog;

public class HDMLOperations implements Serializable
{
	private static final long serialVersionUID = 4436676699689165574L;
	
	public HDMLOperations()
	{		
	}
	
	public synchronized long transactionLogInstance(TransactionLog tranLlog) throws Exception 
	{
		try 
		{        	
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(tranLlog);
			session.flush();
			tx.commit();

			return tranLlog.getLogID();
		} 
		catch (RuntimeException e) 
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
			throw e;
		} 
		finally 
		{
			session.close();
		}
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
    }
	
	private TransactionLog populateTransactionLogInstance(String[] userOpLogParams)throws Exception
	{
		TransactionLog logBean = new TransactionLog();

		logBean.setEmployeeID(userOpLogParams[AppConstants.zero]);
		//logBean.setMenuID(userOpLogParams[AppConstants.one]);
		logBean.setOperationID(userOpLogParams[AppConstants.two]);
		logBean.setServerDate(AppUtility.formatDate(userOpLogParams[AppConstants.three]));
		logBean.setTerminalID(userOpLogParams[AppConstants.four]);
		logBean.setRemarks(userOpLogParams[AppConstants.five]);
		
		return logBean;
	}	

    
	private Transaction tx;
	private Session session ;
	private SessionFactory sessionFactory;
}
