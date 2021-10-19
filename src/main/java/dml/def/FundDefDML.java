package dml.def;

import org.hibernate.Session;
import java.util.logging.Level;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import app.beans.Fund;
import utilities.AppUtility;
import utilities.AppConstants;

public class FundDefDML implements DMLHandler 
{

	public FundDefDML()
	{
	}
	
	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
			throws Exception 
	{
		if(obj instanceof Fund)
		{
			dmlResultSet = new DMLTransWrapper();
			fundBean = (Fund) obj;
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
			dmlResultSet.setFundBean(fundBean);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "Fund having" + fundBean.getFundCode() + " ID with title "+ fundBean.getFundName()
					+ " going to perform " 	+ oprType + " operation. reference log id is " + fundBean.getLogID();
		return str;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
    }

	private void save() throws Exception 
	{
		try 
        {        	
        	session = sessionFactory.openSession();
        	tx = session.beginTransaction();
            session.save(fundBean);
            session.flush();
            tx.commit();     
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

	private void update() throws Exception 
	{
		try 
        {        	
        	session = sessionFactory.openSession();
        	tx = session.beginTransaction();
            session.update(fundBean);
            session.flush();
            tx.commit();     
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

	private void delete() throws Exception 
	{
		try 
        {        	
        	session = sessionFactory.openSession();
        	tx = session.beginTransaction();
            session.delete(fundBean);
            session.flush();
            tx.commit();     
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
	
	
    
	private Transaction tx;
	private Session session ;
	private Fund fundBean;
	private DMLTransWrapper dmlResultSet;
    private SessionFactory sessionFactory;

}
