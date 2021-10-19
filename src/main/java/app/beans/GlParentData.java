package app.beans;

public class GlParentData  
{
	public GlParentData() 
	{
	
	}
	
	public GlParentData(String parentCode, String parentLevel,
			String parentDescription, String glLevelDescription) 
	{
		super();
		this.parentCode = parentCode;
		this.parentLevel = parentLevel;
		this.parentDescription = parentDescription;
		this.glLevelDescription = glLevelDescription;
	}
	
	public String getParentCode() 
	{
		return parentCode;
	}
	public void setParentCode(String parentCode) 
	{
		this.parentCode = parentCode;
	}
	public String getParentLevel() 
	{
		return parentLevel;
	}
	public void setParentLevel(String parentLevel) 
	{
		this.parentLevel = parentLevel;
	}
	public String getParentDescription() 
	{
		return parentDescription;
	}
	public void setParentDescription(String parentDescription) 
	{
		this.parentDescription = parentDescription;
	}
	public String getGlLevelDescription() 
	{
		return glLevelDescription;
	}
	public void setGlLevelDescription(String glLevelDescription) 
	{
		this.glLevelDescription = glLevelDescription;
	}
	
	public String getExceptionMsgString() 
	{
		return this.exceptionMsgString;
	}

	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}
	
	public String getParentFamily() 
	{
		return parentFamily;
	}

	public void setParentFamily(String parentFamily) 
	{
		this.parentFamily = parentFamily;
	}

	private String parentCode;
	private String parentLevel;
	private String parentFamily;
	private String parentDescription;
	private String glLevelDescription;
	private String exceptionMsgString;
	
}
