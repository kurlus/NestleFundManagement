package app.beans;

import java.io.Serializable;

public class GlBookType implements Serializable {

	private static final long serialVersionUID = -2786577461450013216L;

	public GlBookType()
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

	public String getFormType()
	{
		return formType;
	}

	public void setFormType(String formType)
	{
		this.formType = formType;
	}

	public String getGlMFCode()
	{
		return glMFCode;
	}

	public void setGlMFCode(String glMFCode)
	{
		this.glMFCode = glMFCode;
	}

	public String getBookType()
	{
		return bookType;
	}

	public void setBookType(String bookType)
	{
		this.bookType = bookType;
	}

	public String getFundShort()
	{
		return fundShort;
	}

	public void setFundShort(String fundShort)
	{
		this.fundShort = fundShort;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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
	private String formType;
	private String glMFCode;
	private String bookType;
	private String fundShort;
	private String description;
	private String exceptionMsgString;

}
