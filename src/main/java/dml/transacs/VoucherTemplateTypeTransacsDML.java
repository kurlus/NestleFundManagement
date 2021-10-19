package dml.transacs;

import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dml.def.DMLHandler;
import utilities.AppUtility;
import utilities.AppConstants;
import dml.def.DMLTransWrapper;
import app.beans.GlVoucherTemplateType;

public class VoucherTemplateTypeTransacsDML implements DMLHandler
{
	public VoucherTemplateTypeTransacsDML()
	{
		
	}

	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType) throws Exception 
	{
		if(obj instanceof GlVoucherTemplateType)
		{
			dmlResultSet = new DMLTransWrapper();
			glVoucherTemplateType = (GlVoucherTemplateType) obj;
			AppUtility.logger.log(Level.INFO, toString(oprType));
			glVoucherTemplateType.setExceptionMsgString(AppConstants.BLANK);
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
			dmlResultSet.setGlVoucherTemplateType(glVoucherTemplateType);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "Voucher template type having" + glVoucherTemplateType.getTypeShort() + " ID with title "+ glVoucherTemplateType.getDescription() 
					+ " going to perform " 	+ oprType + " operation. reference log id is "+glVoucherTemplateType.getLogID();
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
            session.save(glVoucherTemplateType);
            session.flush();
            tx.commit();        
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+"Caused By : "+e.getCause());
            glVoucherTemplateType.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause());

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
            session.update(glVoucherTemplateType);
            session.flush();
            tx.commit();        
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+"Caused By : "+e.getCause());
            glVoucherTemplateType.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause());

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
            session.delete(glVoucherTemplateType);
            session.flush();
            tx.commit();        
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage()+"Caused By : "+e.getCause());
            glVoucherTemplateType.setExceptionMsgString(e.getMessage()+"Caused By : "+e.getCause());

        } finally 
        {
            session.close();
        }
		
	}
	
	private Transaction tx;
	private Session session ;
	private GlVoucherTemplateType glVoucherTemplateType;
	private DMLTransWrapper dmlResultSet = null;
    private SessionFactory sessionFactory;

}
