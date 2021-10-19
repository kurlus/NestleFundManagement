package dml.transacs;

import org.hibernate.Session;
import java.util.logging.Level;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import dml.def.DMLHandler;
import utilities.AppUtility;
import app.beans.LoanSettlement;
import utilities.AppConstants;
import dml.def.DMLTransWrapper;

public class LoanSettlementTransacsDML implements DMLHandler 
{

	public LoanSettlementTransacsDML()
	{
	}

	@Override
	public DMLTransWrapper dmlDataExecutor(Object obj, String oprType)  
	{
		if (obj instanceof LoanSettlement)
		{
			loanSettlement = (LoanSettlement) obj;

			dmlResultSet = new DMLTransWrapper();
			AppUtility.logger.log(Level.INFO, toString(oprType));

			switch (oprType)
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
			dmlResultSet.setLoanSettlement(loanSettlement);
		}
		return dmlResultSet;
	}

	public synchronized String toString(String oprType)
	{
		String str = "Loan type having" + loanSettlement.getSettlementID() + " going to perform " + oprType
				+ " operation. " ;
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
			session.save(loanSettlement);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage());
			loanSettlement.setExceptionMsgString(e.getMessage()+"Caused By :"+e.getCause());

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
			session.update(loanSettlement);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage());
			loanSettlement.setExceptionMsgString(e.getMessage()+"Caused By :"+e.getCause());

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
			session.delete(loanSettlement);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage());
			loanSettlement.setExceptionMsgString(e.getMessage()+"Caused By :"+e.getCause());

		} finally
		{
			session.close();
		}

	}

	

	private Transaction tx;
	private Session session;
	private LoanSettlement loanSettlement;
	private DMLTransWrapper dmlResultSet = null;
	private SessionFactory sessionFactory;

}

