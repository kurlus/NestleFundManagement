package exceptionhandling;


/**
 * Exception log display interface to client GUI. Two methods used to set and append the
 * log messages in JTextArea of user GUI.
 */

public interface GuiLogService 
{
	/**
	 * Setter method for the exception message to client GUI. 
	 * @param log
	 */
	void setLog(String log);

	/**
	 * Setter method to append the exception message to client GUI.  
	 * @param newLogEvent
	 */
	void addToLog(String newLogEvent);

}
