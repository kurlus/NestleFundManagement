package database;


public class DataSourceException extends Exception 
{
	
	private static final long serialVersionUID = -1554159284053646964L;

	/**
	 * Constructs an DBLockException with no detail message. A detail message is a String
	 * that describes this particular exception.
	 */
	public DataSourceException()
	{
		this("Sale Exception");
	}

	/**
	 * {@link java.lang.Exception#Exception(String)}
	 *
	 * @param msg
	 */
	public DataSourceException(String msg)
	{
		super(msg);
	}        

	/**
	 * {@link java.lang.Exception#Exception(String, Throwable)}
	 * 
	 * @param msg
	 * @param cause
	 */
	public DataSourceException(String msg, Throwable cause)
	{
		super(msg, cause);
	}        

	/**
	 * {@link java.lang.Exception#Exception(Throwable)}
	 *
	 * @param cause
	 */
	public DataSourceException(Throwable cause)
	{
		super(cause);
	}
	
}
