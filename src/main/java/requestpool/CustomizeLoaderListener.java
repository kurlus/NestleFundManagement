package requestpool;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import app.beans.ProjectProps;
import utilities.AppConstants;
import utilities.AppUtility;


public class CustomizeLoaderListener extends ContextLoaderListener 
{

	static 
	{
		logger = Logger.getLogger(AppConstants.loggerName);
	}

	public CustomizeLoaderListener() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) 
	{
		super.contextDestroyed(event);
		try 
		{
			System.gc();
		} 
		catch (Exception e) 
		{
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent event) 
	{
		// TODO Auto-generated method stub
		super.contextInitialized(event);

		try 
		{
			if (event.getServletContext() == null)
				throw new NullPointerException(" Passed argument as ServletContext is null...... ");

			WebApplicationContext applicationCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
			boolean webCtxInitialized = AppUtility.setWebApplicationContext(applicationCtx);

			if (webCtxInitialized) 
			{
				boolean projectPropsInitialized = AppUtility.setProjectProperties();
				if (projectPropsInitialized) 
				{
					ProjectProps projectProps = AppUtility.getProjectProperties();
						
						AppUtility.logger.log(Level.INFO, " context initialized successfully............");
					
				}

			} 
			else 
			{
				AppUtility.logger.log(Level.SEVERE," Failed to initialize WebApplicationContext ........");
			}
			
		} catch (Exception e) {
			AppUtility.logger.log(Level.SEVERE," Error in initializing the container ......"+ e.getMessage());
		}
	}

	private static Logger logger;

}
