package dml.def;

import org.hibernate.Session;
import java.util.logging.Level;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import app.beans.CostCentre;
import utilities.AppUtility;
import utilities.AppConstants;

public class CostCentreDefDML implements DMLHandler 
{
	public CostCentreDefDML()
	{
	}
	
	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
			throws Exception 
	{
		if(obj instanceof CostCentre)
		{
			dmlResultSet = new DMLTransWrapper();
			costCentreBean = (CostCentre) obj;
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
			
			}
			dmlResultSet.setCostCentreBean(costCentreBean);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "Cost Centre having" + costCentreBean.getCostCentreID() + " ID with title "+ costCentreBean.getCostCentreName()
					+ " going to perform " 	+ oprType + " operation. reference log id is " + costCentreBean.getLogID();
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
            session.save(costCentreBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            costCentreBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
             

        } 
		finally 
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
            session.update(costCentreBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            costCentreBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
            

        } 
		finally 
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
            session.delete(costCentreBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            costCentreBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
             

        } 
		finally 
        {
            session.close();
        }
		
	}
	
	private Transaction tx;
	private Session session ;
	private CostCentre costCentreBean;
	private DMLTransWrapper dmlResultSet;
    private SessionFactory sessionFactory;

}
