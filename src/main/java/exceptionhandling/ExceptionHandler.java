package exceptionhandling;

public class ExceptionHandler 
{

	public static String getExceptionResultMSg(String exception, String keyColumn)
	{
		if(exception.contains(ExceptionKeyMessages.violationOfPrimaryKey) && exception.contains(ExceptionKeyMessages.duplicateKeyObject))
			ExceptionHandler.exceptionMsg = ExceptionKeyMessages.violationOfPrimaryKey;
		
		else if(exception.contains(ExceptionKeyMessages.violationOfUniqueKey) && exception.contains(ExceptionKeyMessages.duplicateKeyObject))
			ExceptionHandler.exceptionMsg = ExceptionKeyMessages.violationOfUniqueKey;
		
		else
			ExceptionHandler.exceptionMsg = exception;
			
		return exceptionMsg;
	}

	public String getExceptionMsg() 
	{
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) 
	{
		ExceptionHandler.exceptionMsg = exceptionMsg;
	}
	
	private static String exceptionMsg;
}
