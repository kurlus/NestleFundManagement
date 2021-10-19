package dml.def;

import java.util.HashSet;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utilities.AppConstants;
import utilities.AppUtility;
import app.beans.LoanAgeDef;
import app.beans.LoanAgeDefHistory;
import app.beans.LoanDef;
import app.beans.LoanDefHistory;
import app.beans.hibernate.HLoanAgeDef;

public class LoanDefHistoryDML implements DMLHandler
{
	public LoanDefHistoryDML()
	{
		
	}
	@Override
	public DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
	throws Exception 
	{
		if(obj instanceof LoanDef)
		{
			dmlResultSet = new DMLTransWrapper();
			loanDef = (LoanDef) obj;
			processedLoanDef(oprType);
			save();
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "Loan type having" + processedLoanDef.getLoanTypeId() + " ID with title "+ processedLoanDef.getLoanTypeName() 
					+ " going to perform " 	+ oprType + " operation. reference log id is "+processedLoanDef.getLogID();
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
            session.save(processedLoanDef);
            session.flush();
            tx.commit();        
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+"Caused By : "+e.getCause());
            processedLoanDef.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause());

        } finally 
        {
            session.close();
        }
		
	}
	private void processedLoanDef(String oprType)
	{
		LoanDefHistory hobj = new LoanDefHistory();
		
		
		hobj.setAgeDependent(loanDef.getAgeDependent());
		hobj.setConditions(loanDef.getConditions());
		hobj.setDescription(loanDef.getDescription());
		hobj.setFundBalance(loanDef.getFundBalance());
		hobj.setLoanDefDate(loanDef.getLoanDefDate());
		hobj.setLoanTypeName(loanDef.getLoanTypeName());
		hobj.setLogID(loanDef.getLogID());
		hobj.setRefundable(loanDef.getRefundable());
		hobj.setSalaryMonths(loanDef.getSalaryMonths());
		hobj.setLoanTypeId(loanDef.getLoanTypeId());
		hobj.setHistoryID(0);

		if (loanDef.getLoanAgeDef() != null && loanDef.getLoanAgeDef().size() > 0)
		{
			hobj.setLoanAgeDefHistory(new HashSet<LoanAgeDefHistory>());
			for (LoanAgeDef det : loanDef.getLoanAgeDef())
			{
				LoanAgeDefHistory loanAgeDet = new LoanAgeDefHistory();
				
				
				loanAgeDet.setLoanDefHistory(hobj);
				loanAgeDet.setSequence(det.getSequence());
				loanAgeDet.setAge(det.getAge());
				loanAgeDet.setFundPercentage(det.getFundPercentage());
				loanAgeDet.setLogicalOperator(det.getLogicalOperator());
				
				loanAgeDet.setHistoryID(0);	

				hobj.getLoanAgeDefHistory().add(loanAgeDet);
			}
		}

		this.processedLoanDef = hobj;

	}
	
	
	private Transaction tx;
	private Session session;
	private LoanDef loanDef;
	private LoanDefHistory processedLoanDef;
	private DMLTransWrapper dmlResultSet;
    private SessionFactory sessionFactory;
}
