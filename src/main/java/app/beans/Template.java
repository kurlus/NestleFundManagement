package app.beans;

import java.io.Serializable;

public class Template implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6619722937301483595L;

	public Template()
	{
			
	}
	
	public int getPost() 
	{
		return post;
	}
	
	public void setPost(int post) 
	{
		this.post = post;
	}
	
	public long getLogID() 
	{
		return logID;
	}
	
	public void setLogID(long logID)
	{
		this.logID = logID;
	}
	
	public long getTemplateID() 
	{
		return templateID;
	}

	public void setTemplateID(long templateID) 
	{
		this.templateID = templateID;
	}

	public String getTemplateName() 
	{
		return templateName;
	}

	public void setTemplateName(String templateName) 
	{
		this.templateName = templateName;
	}

	public String getShortName() 
	{
		return shortName;
	}

	public void setShortName(String shortName) 
	{
		this.shortName = shortName;
	}

	public String getFileName() 
	{
		return fileName;
	}

	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public String getExceptionMsgString() 
	{
		return exceptionMsgString;
	}
	
	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}

	private int post;
	private long logID;
	private String fileName;
	private String shortName;
	private long templateID;
	private String templateName;
	private String exceptionMsgString;
	
}
