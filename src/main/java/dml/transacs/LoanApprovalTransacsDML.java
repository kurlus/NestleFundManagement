package dml.transacs;

import org.hibernate.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import database.DMLOperations;
import dml.def.DMLHandler;
import app.beans.LoanApply;
import app.beans.ProfileFiles;
import utilities.AppUtility;
import utilities.AppConstants;
import dml.def.DMLTransWrapper;

public class LoanApprovalTransacsDML implements DMLHandler {

	public LoanApprovalTransacsDML()
	{
	}

	@Override
	public DMLTransWrapper dmlDataExecutor(Object obj, String oprType)  
	{
		if (obj instanceof LoanApply)
		{
			dmlResultSet = new DMLTransWrapper();
			loanApprove = (LoanApply) obj;
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
			case AppConstants.oprGLoan:
				generateLoan();
				break;
			default:
				AppUtility.logger.log(Level.SEVERE, "Wrong operation type is requested " + oprType);
				break;

			}
			dmlResultSet.setLoanApprovalBean(loanApprove);
		}
		return dmlResultSet;
	}

	public synchronized String toString(String oprType)
	{
		String str = "Loan Approval having" + loanApprove.getApplyID() + " ID using employee " + loanApprove.getEmployeeID() + " going to perform " + oprType
				+ " operation. reference log id is " + loanApprove.getLogID();
		return str;
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	private void generateLoan()  
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(AppConstants.paramGernerateLoanProc, this.loanApprove.getApplyID());
	    	Map output = DMLOperations.callProcedure(null, AppConstants.prcGenerateLoan, map);
		
		} catch (Exception e)
		{
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage()+"Caused By : "+e.getCause());
			loanApprove.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause());// TODO: handle exception
		}
	}

	private void save()  
	{
		try
		{
			if (loanApprove.getProfileFiles() != null)
				for (ProfileFiles pf : loanApprove.getProfileFiles())
				{
					pf.setFileID(0);
				}
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(loanApprove);
			// long applyID = loanApprove.getApplyID();
			if (loanApprove.getProfileFiles() != null)
				for (ProfileFiles pf : loanApprove.getProfileFiles())
				{
					pf.setApplyID(loanApprove.getApplyID());
					// pf.setFileID(0);
				}
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage()+"Caused By : "+e.getCause());
			loanApprove.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause());
			 

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
			session.update(loanApprove);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage()+"Caused By : "+e.getCause());
			loanApprove.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause());
			 

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
			session.delete(loanApprove);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage()+"Caused By : "+e.getCause());
			loanApprove.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause());
			 

		} finally
		{
			session.close();
		}

	}

	private Transaction tx;
	private Session session;
	private LoanApply loanApprove;
	private DMLTransWrapper dmlResultSet;
	private SessionFactory sessionFactory;

}
