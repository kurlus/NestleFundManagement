package app.beans.hibernate;

import java.io.Serializable;

public class HGlVoucherTemplateType implements Serializable {

	private static final long serialVersionUID = -5156441423019463701L;
	
	public HGlVoucherTemplateType()
	{
		
	}

	public long getLogID()
	{
		return logID;
	}
	public void setLogID(long logID)
	{
		this.logID = logID;
	}
	public String getTypeShort()
	{
		return typeShort;
	}
	public void setTypeShort(String typeShort)
	{
		this.typeShort = typeShort;
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



	private long logID;
	private String typeShort;
	private String description;
	private String exceptionMsgString;
}
