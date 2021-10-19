package dml.def;

import org.hibernate.Session;
import java.util.logging.Level;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import app.beans.City;
import utilities.AppUtility;
import utilities.AppConstants;

public class CityDefDML implements DMLHandler 
{
	public CityDefDML()
	{
	}

	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
			throws Exception 
	{
		if(obj instanceof City)
		{
			dmlResultSet = new DMLTransWrapper();
			cityBean = (City) obj;
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
			dmlResultSet.setCityBean(cityBean);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "City having" + cityBean.getCityID() + " ID with title "+ cityBean.getCityName()
					+ " going to perform " 	+ oprType + " operation. reference log id is " + cityBean.getLogID();
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
            session.save(cityBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            cityBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
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
            session.update(cityBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            cityBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
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
            session.delete(cityBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            cityBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
             

        } 
		finally 
        {
            session.close();
        }
		
	}
	
	
	private City cityBean;
	private Transaction tx;
	private Session session ;
	private DMLTransWrapper dmlResultSet;
    private SessionFactory sessionFactory;

}
