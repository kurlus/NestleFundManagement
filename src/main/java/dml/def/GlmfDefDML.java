package dml.def;

import org.hibernate.Session;
import java.util.logging.Level;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import app.beans.Glmf;
import database.DMLDef;
import utilities.AppUtility;
import utilities.AppConstants;

public class GlmfDefDML implements DMLHandler 
{

	public GlmfDefDML()
	{
	}
	
	@Override
	public DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
			throws Exception 
	{
		if(obj instanceof Glmf)
		{
			dmlResultSet = new DMLTransWrapper();
			glmf = (Glmf) obj;
			AppUtility.logger.log(Level.INFO, toString(oprType));
			
			
			switch(oprType)
			{
				case AppConstants.oprSave:
					save();
					break;
				case AppConstants.oprEdit:
					update();
					break;
				default:
					glmf.setExceptionMsgString("Wrong operation type is requested "+oprType);
					AppUtility.logger.log(Level.SEVERE, "Wrong operation type is requested "+oprType);
					break;
			
			}
			dmlResultSet.setGlmf(glmf);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "Chart of Account having" + glmf.getGlmfCode() + " glmfCode with desc "+ glmf.getGlmfDesc()
					+ " going to perform " 	+ oprType + " operation. reference log id is " + glmf.getLogID();
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
			String glmfCode = DMLDef.getGlmfCode(glmf.getGlmfParent());
			glmf.setGlmfCode(glmfCode.toString());
			
			if(glmf.getGlmfLevel()==7)
			{
				glmf.setDetailCode(0);
			}
			else
				glmf.setDetailCode(0);
			
        	session = sessionFactory.openSession();
        	tx = session.beginTransaction();
            session.save(glmf);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            glmf.setExceptionMsgString(e.getMessage());
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
		try 
        {   			
        	session = sessionFactory.openSession();
        	tx = session.beginTransaction();
            session.update(glmf);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            glmf.setExceptionMsgString(e.getMessage());
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
            throw e;

        } 
		finally 
        {
            session.close();
        }

	}
	
	
	private Glmf glmf;
	private Transaction tx;
	private Session session ;
	private DMLTransWrapper dmlResultSet;
    private SessionFactory sessionFactory;

}
