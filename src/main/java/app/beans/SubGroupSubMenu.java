
package app.beans;

import java.io.Serializable;

public class SubGroupSubMenu implements Serializable
{

    private static final long serialVersionUID = 5724976609575761882L;
    
    public SubGroupSubMenu()
    {
    	
    }
    
    public String getSubGroupSubMenuID() 
    {
		return subGroupSubMenuID;
	}
    
	public void setSubGroupSubMenuID(String subGroupSubMenuID) 
	{
		this.subGroupSubMenuID = subGroupSubMenuID;
	}
	
	public String getSubGroupSubMenuUrl() 
	{
		return subGroupSubMenuUrl;
	}
	
	public void setSubGroupSubMenuUrl(String subGroupSubMenuUrl) 
	{
		this.subGroupSubMenuUrl = subGroupSubMenuUrl;
	}
	
	public String getSubGroupSubMenuLabel() 
	{
		return subGroupSubMenuLabel;
	}
	
	public void setSubGroupSubMenuLabel(String subGroupSubMenuLabel) 
	{
		this.subGroupSubMenuLabel = subGroupSubMenuLabel;
	}
	
	public String getSubGroupSubParentMenuID() 
	{
		return subGroupSubParentMenuID;
	}
	
	public void setSubGroupSubParentMenuID(String subGroupSubParentMenuID) 
	{
		this.subGroupSubParentMenuID = subGroupSubParentMenuID;
	}
	
	public String getSubGroupSubMenuDescription() 
	{
		return subGroupSubMenuDescription;
	}
	
	public void setSubGroupSubMenuDescription(String subGroupSubMenuDescription) 
	{
		this.subGroupSubMenuDescription = subGroupSubMenuDescription;
	}
	
	public String getSubGroupSubMenuGroupID() 
	{
		return subGroupSubMenuGroupID;
	}

	public void setSubGroupSubMenuGroupID(String subGroupSubMenuGroupID) 
	{
		this.subGroupSubMenuGroupID = subGroupSubMenuGroupID;
	}

	public String getSubGroupSubMenuModuleID() 
	{
		return subGroupSubMenuModuleID;
	}

	public void setSubGroupSubMenuModuleID(String subGroupSubMenuModuleID) 
	{
		this.subGroupSubMenuModuleID = subGroupSubMenuModuleID;
	}

	
    
	private String subGroupSubMenuID;
	private String subGroupSubMenuUrl;
    private String subGroupSubMenuLabel;
    private String subGroupSubMenuGroupID;
    private String subGroupSubMenuModuleID;
    private String subGroupSubParentMenuID;
    private String subGroupSubMenuDescription;
    
   
}
