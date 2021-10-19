package app.beans;

import java.util.List;

public class MenuGroupModule 
{


	public String getModuleId() 
	{
		return moduleId;
	}
	
	public void setModuleId(String moduleId) 
	{
		this.moduleId = moduleId;
	}
	
	public String getModuleDescription() 
	{
		return moduleDescription;
	}
	
	public void setModuleDescription(String moduleDescription) 
	{
		this.moduleDescription = moduleDescription;
	}
	
	public List<SubMenus> getSubMenues() 
	{
		return subMenues;
	}
	
	public void setSubMenues(List<SubMenus> subMenues) 
	{
		this.subMenues = subMenues;
	}
	
	private String moduleId;
	private String moduleDescription;
	private List<SubMenus> subMenues ;
}
