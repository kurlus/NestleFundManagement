package workflow.loans;


public class WfException extends Exception 
{
	private static final long serialVersionUID = -6345933883863135530L;

	public WfException()
	{
		this("Workflow Exception");
	}

	/**
	 * {@link java.lang.Exception#Exception(String)}
	 *
	 * @param msg
	 */
	public WfException(String msg)
	{
		super(msg);
	}        

	/**
	 * {@link java.lang.Exception#Exception(String, Throwable)}
	 * 
	 * @param msg
	 * @param cause
	 */
	public WfException(String msg, Throwable cause)
	{
		super(msg, cause);
	}        

	/**
	 * {@link java.lang.Exception#Exception(Throwable)}
	 *
	 * @param cause
	 */
	public WfException(Throwable cause)
	{
		super(cause);
	}

}
