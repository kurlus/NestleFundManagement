package utilities;

import java.util.Calendar;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.FileHandler;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

import app.beans.ProjectProps;

public class LogHandler extends Handler
{

	public LogHandler(ProjectProps projectProps) 
	{
		super();
		this.projectProps = projectProps;
		
		calendar = Calendar.getInstance();
		formatter = new SimpleFormatter();
		dateformat = new SimpleDateFormat(AppConstants.dateTimeFormat);
		try
		{
			fileHandler = new FileHandler(getLogFilePath(this.projectProps));
			fileHandler.setFormatter(formatter);

		}
		catch(Exception e)
		{
			System.out.println(" Exception LogHandler :: "+e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void publish(LogRecord record) 
	{
		if (fileHandler != null)
		{
			fileHandler.publish(record);	
		}
		else
		{
			//@TODO; sets system.out print stream to default file
			System.out.println(setLogRecordMessage(record));
		}	
		
	}

	@Override
	public void flush() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws SecurityException 
	{
		if (formatter != null) formatter = null;
		
	}
	private String getLogFilePath(ProjectProps projectProps)
	{
		String logFilePath = projectProps.getLogFileLocation() + projectProps.getLogFileName();
		return logFilePath;
	}
	
	private String setLogRecordMessage(LogRecord logRecord)
	{
		try
		{
			String logMessage = calculateCurrentDateTime(logRecord)+
					": "+logRecord.getLevel()+": "+
					": logRecordSequenceNumber "+logRecord.getSequenceNumber()+
					": "+logRecord.getClass() == null ? AppConstants.BLANK : logRecord.getClass()+
							": "+logRecord.getSourceMethodName() == null ? AppConstants.BLANK : logRecord.getSourceMethodName()+  
									": "+logRecord.getMessage()+ "\n";

			return logMessage;
		}
		catch(Exception e)
		{
		}
		return logRecord.getMessage();
	}
	
	private String calculateCurrentDateTime(LogRecord logRecord) throws Exception
	{
		long currentDateTime = logRecord.getMillis();
		calendar.setTimeInMillis(currentDateTime);
		return dateformat.format(calendar.getTime());
	}
	
	
	private Calendar calendar;
	private DateFormat dateformat;
	private FileHandler fileHandler;
	private SimpleFormatter formatter;
	private ProjectProps projectProps;
	
	
}
