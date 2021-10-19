package dml.transacs;

import org.hibernate.Session;
import java.util.logging.Level;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import dml.def.DMLHandler;
import utilities.AppUtility;
import app.beans.LoanPayment;
import utilities.AppConstants;
import dml.def.DMLTransWrapper;

public class LoanPaymentTransacsDML implements DMLHandler 
{

	public LoanPaymentTransacsDML()
	{
	}

	@Override
	public DMLTransWrapper dmlDataExecutor(Object obj, String oprType)  
	{
		if (obj instanceof LoanPayment)
		{
			loanPayment = (LoanPayment) obj;

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
			dmlResultSet.setLoanPayment(loanPayment);
		}
		return dmlResultSet;
	}

	public synchronized String toString(String oprType)
	{
		String str = "Loan type having" + loanPayment.getLoanID()  + " going to perform " + oprType
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
			session.save(loanPayment);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage());
			loanPayment.setExceptionMsgString(e.getMessage()+"Caused By :"+e.getCause());

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
			session.update(loanPayment);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage());
			loanPayment.setExceptionMsgString(e.getMessage()+"Caused By :"+e.getCause());

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
			session.delete(loanPayment);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage());
			loanPayment.setExceptionMsgString(e.getMessage()+"Caused By :"+e.getCause());

		} finally
		{
			session.close();
		}

	}

	

	private Transaction tx;
	private Session session;
	private LoanPayment loanPayment;
	private DMLTransWrapper dmlResultSet = null;
	private SessionFactory sessionFactory;

}
