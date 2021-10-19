package dml.def;

import org.hibernate.Session;
import java.util.logging.Level;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import utilities.AppUtility;
import app.beans.FundCategory;
import utilities.AppConstants;

public class FundCategoryDefDML implements DMLHandler
{

	public FundCategoryDefDML()
	{
	}
	
	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
			throws Exception 
	{
		if(obj instanceof FundCategory)
		{
			dmlResultSet = new DMLTransWrapper();
			fundCategoryBean = (FundCategory) obj;
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
			dmlResultSet.setFundCategoryBean(fundCategoryBean);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "Fund Category having" + fundCategoryBean.getFundCategoryID() + " ID with title "+ fundCategoryBean.getDescription()
					+ " going to perform " 	+ oprType + " operation. reference log id is " + fundCategoryBean.getLogID();
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
            session.save(fundCategoryBean);
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
            session.update(fundCategoryBean);
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
            session.delete(fundCategoryBean);
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
	private SessionFactory sessionFactory;
	private DMLTransWrapper dmlResultSet;
	private FundCategory fundCategoryBean;

}
