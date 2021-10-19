package app.beans;

public class GlFormType {

	public GlFormType()
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

	public String getFormType()
	{
		return formType;
	}

	public void setFormType(String formType)
	{
		this.formType = formType;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getVoucherInitialize()
	{
		return voucherInitialize;
	}

	public void setVoucherInitialize(String voucherInitialize)
	{
		this.voucherInitialize = voucherInitialize;
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
	private String description;
	private String voucherInitialize;
	private String exceptionMsgString;

}
