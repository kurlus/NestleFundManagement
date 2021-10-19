package exceptionhandling;

import java.util.logging.Logger;

import utilities.AppUtility;
import utilities.AppConstants;


public class AppLogger
{

	public AppLogger(AppFileHandler fileHandler)
	{
		this.fileHandler = fileHandler;
		logger.addHandler(fileHandler);
		AppUtility.setLogger(logger);
	}	

	public static Logger getLogger() 
	{
		return AppUtility.getLogger();
	}


	private AppFileHandler fileHandler;
	private static final Logger logger = Logger.getLogger(AppConstants.loggerName);	
}
