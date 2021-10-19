package dml.def;

import java.util.HashSet;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utilities.AppConstants;
import utilities.AppUtility;
import app.beans.GlVoucherTemplateDet;
import app.beans.LoanAgeDef;
import app.beans.LoanDef;
import app.beans.LoanType;
import app.beans.hibernate.HGlVoucherTemplateDet;
import app.beans.hibernate.HGlVoucherTemplateMf;
import app.beans.hibernate.HLoanAgeDef;
import app.beans.hibernate.HLoanDef;

public class LoanDefDML implements DMLHandler
{

	public LoanDefDML()
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
			loanDef.setExceptionMsgString(null);
			AppUtility.logger.log(Level.INFO, toString(oprType));
			processedLoanDef(oprType);
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
			dmlResultSet.setLoanDefBean(loanDef);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "Loan type having" + loanDef.getLoanTypeId() + " ID with title "+ loanDef.getLoanTypeName() 
					+ " going to perform " 	+ oprType + " operation. reference log id is "+loanDef.getLogID();
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
            loanDef.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause());

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
        	session.update(processedLoanDef);
			session.flush();
			tx.commit();
        	if(processedLoanDefForRemove.getLoanAgeDef()!=null&&processedLoanDefForRemove.getLoanAgeDef().size()>0)
			{
				deleteOrphan();
			}
        	      
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+"Caused By : "+e.getCause());
            loanDef.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause());

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
            session.delete(processedLoanDef);
            session.flush();
            tx.commit();        
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+"Caused By : "+e.getCause());
            loanDef.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause());

        } finally 
        {
            session.close();
        }
		
	}
	
	private void deleteOrphan()
	{
		try
		{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			HLoanDef parent = (HLoanDef) session.get(HLoanDef.class,new Long(processedLoanDefForRemove.getLoanTypeId()));
			
			for(HLoanAgeDef child: processedLoanDefForRemove.getLoanAgeDef())
			{
				HLoanAgeDef obj = (HLoanAgeDef)session.get(HLoanAgeDef.class, new Long(child.getSequenceID()));
				parent.getLoanAgeDef().remove(obj);
			}
			session.saveOrUpdate(parent);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			loanDef.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

		} 
		/*finally
		{
			session.close();
		}*/

	}
	
	private void processedLoanDef(String oprType)
	{
		HLoanDef hobj = new HLoanDef();
		HLoanDef delete = new HLoanDef();
		
		hobj.setAgeDependent(loanDef.getAgeDependent());
		hobj.setConditions(loanDef.getConditions());
		hobj.setDescription(loanDef.getDescription());
		hobj.setFundBalance(loanDef.getFundBalance());
		hobj.setLoanDefDate(loanDef.getLoanDefDate());
		hobj.setLoanTypeName(loanDef.getLoanTypeName());
		hobj.setLogID(loanDef.getLogID());
		hobj.setRefundable(loanDef.getRefundable());
		hobj.setSalaryMonths(loanDef.getSalaryMonths());
		
		
		if (oprType.equals(AppConstants.oprSave))
			hobj.setLoanTypeId(0);
		else
			hobj.setLoanTypeId(loanDef.getLoanTypeId());
		

		if (loanDef.getLoanAgeDef() != null && loanDef.getLoanAgeDef().size() > 0)
		{
			hobj.setLoanAgeDef(new HashSet<HLoanAgeDef>());
			delete.setLoanAgeDef(new HashSet<HLoanAgeDef>());
			for (LoanAgeDef det : loanDef.getLoanAgeDef())
			{
				HLoanAgeDef loanAgeDet = new HLoanAgeDef();
				
				loanAgeDet.setLoanDef(hobj);
				loanAgeDet.setAge(det.getAge());
				loanAgeDet.setFundPercentage(det.getFundPercentage());
				loanAgeDet.setLogicalOperator(det.getLogicalOperator());
				loanAgeDet.setSequenceID(det.getSequence());
			
				if((oprType!=null && oprType.equals(AppConstants.oprSave))
						|| (oprType.equals(AppConstants.oprEdit) && (det.getDmlType()!= null && det.getDmlType().equals("I"))))
				{
					loanAgeDet.setSequenceID(0);	
				}
				else if((oprType!=null && !oprType.equals(AppConstants.oprSave)))
				{
					if(oprType.equals(AppConstants.oprDel) || (oprType.equals(AppConstants.oprEdit) && (det.getDmlType()!= null && !det.getDmlType().equals("I"))))
					{
						loanAgeDet.setSequenceID(det.getSequence());
					}
						
				}
				
				if(oprType.equals(AppConstants.oprEdit) && (det.getDmlType()!= null && det.getDmlType().equals("D")))
				{
					delete.getLoanAgeDef().add(loanAgeDet);
				}
				else				
					hobj.getLoanAgeDef().add(loanAgeDet);
				
			}
		}

		this.processedLoanDef = hobj;
		this.processedLoanDefForRemove = delete;
		this.processedLoanDefForRemove.setLoanTypeId(hobj.getLoanTypeId());
	}
	
	private Transaction tx;
	private Session session;
	private LoanDef loanDef;
	private HLoanDef processedLoanDef;
	private DMLTransWrapper dmlResultSet;
    private SessionFactory sessionFactory;
	private HLoanDef processedLoanDefForRemove;

}
