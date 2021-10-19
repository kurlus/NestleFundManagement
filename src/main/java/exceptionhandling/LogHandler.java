package exceptionhandling;

import java.util.Calendar;
import java.text.DateFormat;
import java.util.logging.Handler;
import java.text.SimpleDateFormat;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

import utilities.AppConstants;


public class LogHandler extends Handler
{

	/**
	 * Initializes client GUI to log the exceptions in log window.
	 * @param logService
	 */
	public LogHandler(GuiLogService logService)
	{
		this.logService = logService;
		calendar = Calendar.getInstance();
		formatter = new SimpleFormatter();
		df = new SimpleDateFormat("MM/dd/yy::HH:mm:ss:SS");
	}

	/**
	 * @see java.util.logging.Handler#publish(LogRecord)
	 */
	@Override
	public void publish(LogRecord logRecord)
	{
		if (logRecord.getSequenceNumber() == AppConstants.zero)
			logService.setLog(setLogRecordMessage(logRecord));
		else
			logService.addToLog(setLogRecordMessage(logRecord));
	}

	/**
	 * @see java.util.logging.Handler#close()
	 */
	@Override
	public void close()
	{
		if (logService != null) logService = null;
		if (formatter != null) formatter = null;
	}

	/**
	 * @see java.util.logging.Handler#flush()
	 */
	@Override
	public void flush()
	{
	}

	private String calculateCurrentDateTime(LogRecord logRecord) throws Exception
	{
		long currentDateTime = logRecord.getMillis();
		calendar.setTimeInMillis(currentDateTime);
		return df.format(calendar.getTime());
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



	private DateFormat df;
	private Calendar calendar;
	private GuiLogService logService;
	private SimpleFormatter formatter;

}
