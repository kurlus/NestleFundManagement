package app.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SubGroupMenu implements Serializable
{
	private static final long serialVersionUID = -6352078608408484539L;
	
	public SubGroupMenu()
	{
		
	}
	
	public String getSubGroupMenuID() 
	{
		return subGroupMenuID;
	}
	
	public void setSubGroupMenuID(String subGroupMenuID) 
	{
		this.subGroupMenuID = subGroupMenuID;
	}
	
	public String getSubGroupMenuURL() 
	{
		return subGroupMenuURL;
	}
	
	public void setSubGroupMenuURL(String subGroupMenuURL) 
	{
		this.subGroupMenuURL = subGroupMenuURL;
	}
	
	public String getSubGroupMenuDesc() 
	{
		return subGroupMenuDesc;
	}
	
	public void setSubGroupMenuDesc(String subGroupMenuDesc) 
	{
		this.subGroupMenuDesc = subGroupMenuDesc;
	}
	
	public String getSubGroupMenuLabel() 
	{
		return subGroupMenuLabel;
	}
	
	public void setSubGroupMenuLabel(String subGroupMenuLabel) 
	{
		this.subGroupMenuLabel = subGroupMenuLabel;
	}
	
	public String getSubGroupMenuLevel() 
	{
		return subGroupMenuLevel;
	}
	
	public void setSubGroupMenuLevel(String subGroupMenuLevel) 
	{
		this.subGroupMenuLevel = subGroupMenuLevel;
	}
	
	public String getSubGroupParentMenuID() 
	{
		return subGroupParentMenuID;
	}
	
	public void setSubGroupParentMenuID(String subGroupParentMenuID) 
	{
		this.subGroupParentMenuID = subGroupParentMenuID;
	}
	
	public List<SubGroupSubMenu> getSubGroupSubMenuList() 
	{
		return subGroupSubMenuList;
	}
	
	public void setSubGroupSubMenuList(List<SubGroupSubMenu> subGroupSubMenuList) 
	{
		this.subGroupSubMenuList = subGroupSubMenuList;
	}
	public String getSubGroupMenuGroupID() 
	{
		return subGroupMenuGroupID;
	}
	public void setSubGroupMenuGroupID(String subGroupMenuGroupID) 
	{
		this.subGroupMenuGroupID = subGroupMenuGroupID;
	}

	private String subGroupMenuID;
	private String subGroupMenuURL;
	private String subGroupMenuGroupID;
	private String subGroupMenuDesc;
	private String subGroupMenuLabel;
	private String subGroupMenuLevel;
	private String subGroupParentMenuID;
	private List<SubGroupSubMenu> subGroupSubMenuList = new ArrayList<SubGroupSubMenu>();
}
