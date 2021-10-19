package dml.transacs;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utilities.AppConstants;
import utilities.AppUtility;
import app.beans.ImportLoanPayment;
import database.DMLOperations;
import dml.def.DMLHandler;
import dml.def.DMLTransWrapper;

public class ImportLoanPaymentTransacsDML implements DMLHandler 
{
	public ImportLoanPaymentTransacsDML()
	{
	}

	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType) throws Exception 
	{		
		
		if(obj instanceof ImportLoanPayment)
		{
			importLoanPayment = (ImportLoanPayment) obj;
			
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
			dmlResultSet.setImportLoanPayment(importLoanPayment);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "Loan Adjustment having" + importLoanPayment.getAdjustLoanID() + " ID Loan " + importLoanPayment.getLoanID() + " " 
				+ " going to perform " 	+ oprType;
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
        	
            session.save(importLoanPayment);
            session.flush();
            tx.commit();     
        } 
        catch (Exception e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+" Cause by "+e.getCause().toString());
            importLoanPayment.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());

        } 
		finally 
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
            session.update(importLoanPayment);
            session.flush();
            tx.commit(); 
            
            
            map = new HashMap<String, Object>();
        	map.put(AppConstants.paramEmployeeValidationBatchID,importLoanPayment.getBatchID());
        	map.put(AppConstants.paramEmployeeValidationRecordID,importLoanPayment.getRecordID());
        	Map output = DMLOperations.callProcedure(null, AppConstants.prcImportLoanApplyValidation, map);
        } 
        catch (Exception e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+" Cause by "+e.getCause().toString());
            importLoanPayment.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());

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
            session.delete(importLoanPayment);
            session.flush();
            tx.commit();        
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+" Cause by "+e.getCause().toString());
            importLoanPayment.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());


        } finally 
        {
            session.close();
        }

	}
	
	private Transaction tx;
	private Session session ;
	private ImportLoanPayment importLoanPayment;
	private DMLTransWrapper dmlResultSet = null;
	private SessionFactory sessionFactory;

}
