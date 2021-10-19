package workflow.loans;


public class TransactionException extends Exception
{
	private static final long serialVersionUID = -6906785011071450714L;


	public TransactionException()
	{
		this("Transaction-Manager Exception");
	}

	/**
	 * {@link java.lang.Exception#Exception(String)}
	 *
	 * @param msg
	 */
	public TransactionException(String msg)
	{
		super(msg);
	}        

	/**
	 * {@link java.lang.Exception#Exception(String, Throwable)}
	 * 
	 * @param msg
	 * @param cause
	 */
	public TransactionException(String msg, Throwable cause)
	{
		super(msg, cause);
	}        

	/**
	 * {@link java.lang.Exception#Exception(Throwable)}
	 *
	 * @param cause
	 */
	public TransactionException(Throwable cause)
	{
		super(cause);
	}

}
