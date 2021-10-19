package utilities;

import java.io.File;
import java.util.Date;
import java.util.Calendar;
import java.util.Properties;
import java.text.DateFormat;
import java.io.FileInputStream;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.beans.ProjectProps;


public class AppUtility
{

	public AppUtility()
	{
	}

	public static synchronized boolean setWebApplicationContext(WebApplicationContext applicationCtx) throws Exception 
	{
		try 
		{
			if (applicationCtx == null)
				throw new NullPointerException(" Passed argument as WebApplicationContext is null...... ");

			AppUtility.applicationCtx = applicationCtx;
			return true;
		} 
		catch (Exception e) 
		{
			throw e;
		}
	}

	public static synchronized WebApplicationContext getWebApplicationContext() throws Exception 
	{
		if (applicationCtx == null) 
			throw new NullPointerException(" applicationCtx is null........ ");

		return applicationCtx;
	}

	public static synchronized boolean setApplicationCtx(ClassPathXmlApplicationContext classPathApplicationCtx) throws Exception
	{
		if (classPathApplicationCtx == null)
			throw new NullPointerException("Null ClassPathXmlApplicationContext........");

		AppUtility.classPathApplicationCtx = classPathApplicationCtx;
		return Boolean.TRUE;
	}

	public static synchronized ClassPathXmlApplicationContext getClassPathApplicationCtx() 
	{
		return classPathApplicationCtx;
	}

	

	public static synchronized String formatDate(Date date) throws Exception 
	{
		if (date != null) 
		{
			return dateFormat.format(date);
		} 
		else 
		{
			throw new Exception(" Invalid Date input i.e. null .........");
		}
	}

	public static synchronized Date formatDate(String date) throws Exception 
	{
		if (!StringUtils.isEmpty(date)) 
		{
			return dateFormat.parse(date.trim());
		} 
		else 
		{
			throw new Exception(" Invalid Date input i.e. null .........");
		}
	}
	
	public static synchronized String loaderFormatDate(Date date) throws Exception 
	{
		if (date != null) 
		{
			return loaderDateFormat.format(date);
		} 
		else 
		{
			throw new Exception(" Invalid Date input i.e. null .........");
		}
	}

	public static synchronized Date loaderFormatDate(String date) throws Exception 
	{
		if (!StringUtils.isEmpty(date)) 
		{
			return loaderDateFormat.parse(date.trim());
		} 
		else 
		{
			throw new Exception(" Invalid Date input i.e. null .........");
		}
	}

	public static synchronized Date validatesDateValue(Date argument) throws Exception 
	{
		if (argument == null) 
		{
			throw new NullPointerException(" Argument points to null....");
		} 
		else if (!(argument instanceof Date)) 
		{
			throw new NullPointerException(" Argument not instance of Date....");
		} 
		else 
		{
			return argument;
		}
	}

	public static synchronized String getCurrentDateTime() throws Exception
	{
		try 
		{
			DateFormat dateFormat =  new SimpleDateFormat(AppConstants.dateTimeFormatStr);
			Calendar cal = Calendar.getInstance();
			return dateFormat.format(cal.getTime());
		} 
		catch (Exception e) 
		{
			throw e;
		}
	}

	public static synchronized boolean validateStringValue(String argument) 
	{
		if (argument == null) 
		{
			return Boolean.FALSE;
		} 
		else if ((argument != null) && (argument.length() == AppConstants.zero)) 
		{
			return Boolean.FALSE;
		} 
		else 
		{
			return Boolean.TRUE;
		}
	}

	public static synchronized String validatesStringValue(String argument) throws NullPointerException 
	{
		if (argument == null) 
		{
			throw new NullPointerException(" Argument points to null....");
		} 
		else if ((argument != null) && (argument.length() == AppConstants.zero)) 
		{
			throw new NullPointerException(" Empty Argument ....");
		} 
		else 
		{
			return argument;
		}
	}

	public static synchronized int validatesIntgerValue(int argument) throws Exception 
	{
		if (argument == AppConstants.zero) 
		{
			throw new Exception(" Not valid data....");
		} 
		else 
		{
			return argument;
		}
	}

	public static synchronized Properties loadPropertiesFile(String propertiesFileName) throws Exception 
	{
		File file = null;
		Properties properties = null;
		FileInputStream fileInStream = null;

		try 
		{
			file = new File(propertiesFileName);
			fileInStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInStream);
		} 
		catch (Exception e) 
		{
			throw e;
		} 
		finally 
		{
			try 
			{
				if (fileInStream != null) 
				{
					fileInStream.close();
				}
				if (file != null) 
				{
					file = null;
				}
			} 
			catch (Exception e) 
			{
			}
		}
		return properties;
	}

	public static synchronized ProjectProps getProjectProperties() 
	{
		return projectProperties;
	}

	public static synchronized boolean setProjectProperties() throws Exception
	{
		try 
		{
			projectProperties = (ProjectProps) getWebApplicationContext().getBean(AppConstants.projectPropertiesBean);
			return Boolean.TRUE;
		} 
		catch (Exception e) 
		{
			projectProperties = (ProjectProps) getClassPathApplicationCtx().getBean(AppConstants.projectPropertiesBean);
			return Boolean.TRUE;
		}
	}

	public static synchronized ExecutorService getExecutorService() 
	{
		return executorService;
	}

	public static Logger getLogger() 
	{
		return logger;
	}

	public static void setLogger(Logger logger) 
	{
		AppUtility.logger = logger;
	}

	
	public static Logger logger = Logger.getLogger(AppConstants.appServerLogger);

	private static ProjectProps projectProperties;

	private static WebApplicationContext applicationCtx;
	private static ClassPathXmlApplicationContext classPathApplicationCtx;
	private static ExecutorService executorService = Executors.newCachedThreadPool();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(AppConstants.dateFormat4Web);
	private static final SimpleDateFormat loaderDateFormat = new SimpleDateFormat(AppConstants.dateFormat4Socket);
}
