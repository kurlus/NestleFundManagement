package dml.def;

import org.hibernate.Session;
import java.util.logging.Level;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import utilities.AppUtility;
import app.beans.GlBankBranch;
import utilities.AppConstants;

public class BankBranchDefDML implements DMLHandler
{
	public BankBranchDefDML()
	{
	}
	
	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
			throws Exception 
	{
		if(obj instanceof GlBankBranch)
		{
			dmlResultSet = new DMLTransWrapper();
			bankBranchBean = (GlBankBranch) obj;
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
			dmlResultSet.setBankBranchBean(bankBranchBean);
		}
		return dmlResultSet;
	}
	
	public synchronized  String toString(String oprType) 
	{
		String str = "Bank Branch having" + bankBranchBean.getBankBranchID() + " ID with title "+ bankBranchBean.getBankBranchShort()
					+ " going to perform " 	+ oprType + " operation. reference log id is " + bankBranchBean.getLogID();
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
            session.save(bankBranchBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            bankBranchBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
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
            session.update(bankBranchBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            bankBranchBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
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
            session.delete(bankBranchBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            bankBranchBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
            

        } 
		finally 
        {
            session.close();
        }
		
	}
	
	private Transaction tx;
	private Session session ;
	private DMLTransWrapper dmlResultSet;
	private GlBankBranch bankBranchBean;
    private SessionFactory sessionFactory;

}
