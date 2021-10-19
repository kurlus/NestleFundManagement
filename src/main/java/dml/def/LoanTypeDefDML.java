package dml.def;

import org.hibernate.Session;
import java.util.logging.Level;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import app.beans.LoanType;
import utilities.AppUtility;
import utilities.AppConstants;

public class LoanTypeDefDML implements DMLHandler
{
	public LoanTypeDefDML()
	{
	}

	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
	  
	{
		if(obj instanceof LoanType)
		{
			dmlResultSet = new DMLTransWrapper();
			loanType = (LoanType) obj;
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
			dmlResultSet.setLoanType(loanType);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "Loan type having" + loanType.getLoanTypeId() + " ID with title "+ loanType.getLoanTypeName() 
					+ " going to perform " 	+ oprType + " operation. reference log id is "+loanType.getLogID();
		return str;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
    }

	private synchronized void save()   
	{
		
        try 
        {        	
        	session = sessionFactory.openSession();
        	tx = session.beginTransaction();
            session.save(loanType);
            session.flush();
            tx.commit();        
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+"Caused By : "+e.getCause());
            loanType.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause());

        } finally 
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
            session.update(loanType);
            session.flush();
            tx.commit();        
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+"Caused By : "+e.getCause());
            loanType.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause());

        } finally 
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
            session.delete(loanType);
            session.flush();
            tx.commit();        
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+"Caused By : "+e.getCause());
            loanType.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause());

        } finally 
        {
            session.close();
        }
		
	}
	
	private Transaction tx;
	private Session session ;
	private LoanType loanType;
	private DMLTransWrapper dmlResultSet = null;
    private SessionFactory sessionFactory;

}
