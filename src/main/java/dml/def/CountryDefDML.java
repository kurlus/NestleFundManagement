package dml.def;

import org.hibernate.Session;
import java.util.logging.Level;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import app.beans.Country;
import utilities.AppUtility;
import utilities.AppConstants;

public class CountryDefDML implements DMLHandler 
{
	public CountryDefDML()
	{
	}
	
	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
			throws Exception 
	{
		if(obj instanceof Country)
		{
			dmlResultSet = new DMLTransWrapper();
			countryBean = (Country) obj;
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
			dmlResultSet.setCountryBean(countryBean);
		}
		return dmlResultSet;
	}
	
	public synchronized String toString(String oprType) 
	{
		String str = "Country having" + countryBean.getCountry() + " ID with title "+ countryBean.getCountry()
					+ " going to perform " 	+ oprType + " operation. reference log id is " + countryBean.getLogID();
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
            session.save(countryBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            countryBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
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
            session.update(countryBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            countryBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
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
            session.delete(countryBean);
            session.flush();
            tx.commit();     
        } 
        catch (RuntimeException e) 
        {
            tx.rollback();
            countryBean.setExceptionMsgString(e.getMessage()+" Cause by "+e.getCause().toString());
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+e.getMessage());
             

        } 
		finally 
        {
            session.close();
        }
		
	}
	
	private Transaction tx;
	private Session session ;
	private Country countryBean;
	private DMLTransWrapper dmlResultSet;
    private SessionFactory sessionFactory;

}
