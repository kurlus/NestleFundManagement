package dml.transacs;

import org.hibernate.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import database.DMLOperations;
import dml.def.DMLHandler;
import utilities.AppUtility;
import utilities.AppConstants;
import app.beans.ImportLoanApply;
import dml.def.DMLTransWrapper;

public class ImportLoanApplyTransacsDML implements DMLHandler {
	public ImportLoanApplyTransacsDML()
	{
	}

	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType)  
	{

		if (obj instanceof ImportLoanApply)
		{
			importLoanApply = (ImportLoanApply) obj;

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
				AppUtility.logger.log(Level.SEVERE, "Wrong operation type is requested " + oprType);
				break;
			}
			dmlResultSet.setImportLoanApply(importLoanApply);
		}
		return dmlResultSet;
	}

	public synchronized String toString(String oprType)
	{
		String str = "Loan Apply having employee" + importLoanApply.getEmployeeID() + " ID Loan ApplyID " + importLoanApply.getApplyID() + " " + " going to perform " + oprType;
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

			session.save(importLoanApply);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage()+"Caused By : "+e.getCause());
			importLoanApply.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause()); 

		} finally
		{
			session.close();
		}
	}

	private void update()  
	{
		Map<String, Object> map = null;
		try
		{
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			session.update(importLoanApply);
			session.flush();
			tx.commit();
			map = new HashMap<String, Object>();
			map.put(AppConstants.paramEmployeeValidationBatchID, importLoanApply.getBatchID());
			map.put(AppConstants.paramEmployeeValidationRecordID, importLoanApply.getRecordID());
			Map output = DMLOperations.callProcedure(null, AppConstants.prcImportLoanApplyValidation, map);

		} catch (Exception e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage()+"Caused By : "+e.getCause());
			importLoanApply.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause());  

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
			session.delete(importLoanApply);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage()+"Caused By : "+e.getCause());
			importLoanApply.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause()); 

		} finally
		{
			session.close();
		}

	}

	private Transaction tx;
	private Session session;
	private ImportLoanApply importLoanApply;
	private DMLTransWrapper dmlResultSet = null;
	private SessionFactory sessionFactory;
}
