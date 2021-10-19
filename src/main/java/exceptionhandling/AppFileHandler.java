package exceptionhandling;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.LogRecord;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;


public class AppFileHandler extends FileHandler
{

	public AppFileHandler(String logFileName) throws Exception
	{
		super(logFileName);

		calendar = Calendar.getInstance();
		formatter = new SimpleFormatter();
		df = new SimpleDateFormat("MM/dd/yy::HH:mm:ss:SS");

		this.setFormatter(new ExtendedFormatter());
	}

	class ExtendedFormatter extends SimpleFormatter
	{
		public ExtendedFormatter()
		{
			super();
			calendar = Calendar.getInstance();
			df = new SimpleDateFormat("MM/dd/yy::HH:mm:ss:SS");
		}

		public String formatMessage(LogRecord logRecord)
		{
			try
			{
				try
				{
					String logMessage = calculateCurrentDateTime(logRecord)+" "+
							": logRecordSequenceNumber "+logRecord.getSequenceNumber()+
							": LogMessage "+logRecord.getMessage()+ "\n";

					return logMessage;
				}
				catch(Exception e)
				{
				}
				return logRecord.getMessage();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return logRecord.getMessage();
		}

		private String calculateCurrentDateTime(LogRecord logRecord) throws Exception
		{
			long currentDateTime = logRecord.getMillis();
			calendar.setTimeInMillis(currentDateTime);
			return df.format(calendar.getTime());
		}

	}// end of class.


	private DateFormat df;
	private Calendar calendar;
	private SimpleFormatter formatter;	

}
