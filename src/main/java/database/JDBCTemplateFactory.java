package database;

import javax.sql.DataSource;
import utilities.AppUtility;
import utilities.AppConstants;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;



public class JDBCTemplateFactory 
{

	/*
	 * Copied from JDK-7 documentation
	 * 
	 * A driver that is accessed via a DataSource object does not register itself with the DriverManager. 
	 * Rather, a DataSource object is retrieved though a lookup operation and then used to create a Connection
	 * object. With a basic implementation, the connection obtained through a DataSource object is identical
	 * to a connection obtained through the DriverManager facility.
	 */
	public synchronized static DataSource getDataSource() throws Exception 
	{
		try
		{
			return (DataSource) AppUtility.getWebApplicationContext().getBean(AppConstants.dataSource);	
		}
		catch(Exception e)
		{
			try
			{
				return (DataSource) AppUtility.getClassPathApplicationCtx().getBean(AppConstants.dataSource);
			}
			catch(Exception ex)
			{
				throw e;
			}
		}
	}

	public synchronized static DataSourceTransactionManager getDSTransactionManager() throws Exception 
	{
		try
		{
			return (DataSourceTransactionManager) AppUtility.getWebApplicationContext().getBean("springDSTransacManager");	
		}
		catch(Exception e)
		{
			try
			{
				return (DataSourceTransactionManager) AppUtility.getClassPathApplicationCtx().getBean("springDSTransacManager");
			}
			catch(Exception ex)
			{
				throw e;
			}
		}
	}

	public synchronized static JdbcTemplate getJdbcTemplateInstance() throws Exception 
	{
		try
		{
			DataSource dataSource = (DataSource) AppUtility.getWebApplicationContext().getBean(AppConstants.dataSource);
			return new JdbcTemplate(dataSource);
		}
		catch(Exception e)
		{
			try
			{
				DataSource dataSource = (DataSource) AppUtility.getClassPathApplicationCtx().getBean(AppConstants.dataSource);
				return new JdbcTemplate(dataSource);
			}
			catch(Exception ex)
			{
				throw e;
			}
		}
	}

	public synchronized static NamedParameterJdbcTemplate getNamedParamJdbcTemplateInstance() throws Exception 
	{
		try
		{
			DataSource dataSource = (DataSource) AppUtility.getWebApplicationContext().getBean(AppConstants.dataSource);
			return new NamedParameterJdbcTemplate(dataSource);
		}
		catch(Exception e)
		{
			try
			{
				DataSource dataSource = (DataSource) AppUtility.getClassPathApplicationCtx().getBean(AppConstants.dataSource);
				return new NamedParameterJdbcTemplate(dataSource);
			}
			catch(Exception ex)
			{
				throw e;
			}
		}
	}


}
