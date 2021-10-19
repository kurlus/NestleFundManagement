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
import app.beans.ImportPayroll;
import dml.def.DMLTransWrapper;

public class ImportPayrollTransacsDML implements DMLHandler 
{
	public ImportPayrollTransacsDML()
	{
	}

	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType) throws Exception 
	{		
		
		if(obj instanceof ImportPayroll)
		{
			importPayroll = (ImportPayroll) obj;
			
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
				default:
					AppUtility.logger.log(Level.SEVERE, "Wrong operation type is requested "+oprType);
					break;
			}
			dmlResultSet.setImportPayroll(importPayroll);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "Employee having" + importPayroll.getEmployeeID() + " ID Loan Installments " + importPayroll.getLoanInstallment() + " " 
				+ " going to perform " 	+ oprType;
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
        	
            session.save(importPayroll);
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
		Map<String, Object> map = null;
		try 
        {        	
        	session = sessionFactory.openSession();
        	
            tx = session.beginTransaction();
            session.update(importPayroll);
            session.flush();
            tx.commit(); 
            
            
            map = new HashMap<String, Object>();
        	map.put(AppConstants.paramEmployeeValidationBatchID,importPayroll.getBatchID());
        	map.put(AppConstants.paramEmployeeValidationRecordID,importPayroll.getRecordID());
        	Map output = DMLOperations.callProcedure(null, AppConstants.prcImportPayrollValidation, map);
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
            throw e;

        } finally 
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
            session.delete(importPayroll);
            session.flush();
            tx.commit();        
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
            throw e;

        } finally 
        {
            session.close();
        }

	}
	
	private Transaction tx;
	private Session session ;
	private ImportPayroll importPayroll;
	private DMLTransWrapper dmlResultSet = null;
	private SessionFactory sessionFactory;
}
