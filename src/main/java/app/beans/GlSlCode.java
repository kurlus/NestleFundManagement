package app.beans;

import java.io.Serializable;

public class GlSlCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8770894405468304341L;
	public GlSlCode()
	{
		// TODO Auto-generated constructor stub
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
	public int getActive()
	{
		return active;
	}
	public void setActive(int active)
	{
		this.active = active;
	}
	public long getSlType()
	{
		return slType;
	}
	public void setSlType(long slType)
	{
		this.slType = slType;
	}
	public String getSlCode()
	{
		return slCode;
	}
	public void setSlCode(String slCode)
	{
		this.slCode = slCode;
	}
	public String getSlShortName()
	{
		return slShortName;
	}
	public void setSlShortName(String slShortName)
	{
		this.slShortName = slShortName;
	}
	public String getSlDescription()
	{
		return slDescription;
	}
	public void setSlDescription(String slDescription)
	{
		this.slDescription = slDescription;
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
	private int active;
	private long slType;
	private String slCode;
	private String slShortName;
	private String slDescription;
	private String exceptionMsgString;

}
