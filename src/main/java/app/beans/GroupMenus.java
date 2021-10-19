package app.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroupMenus implements Serializable
{

	private static final long serialVersionUID = -7940709409622197923L;
	
	public GroupMenus()
	{
		
	}
	
	public String getGroupMenuID() 
	{
		return groupMenuID;
	}
	
	public void setGroupMenuID(String groupMenuID) 
	{
		this.groupMenuID = groupMenuID;
	}
	
	public String getGroupMenuURL() 
	{
		return groupMenuURL;
	}
	
	public void setGroupMenuURL(String groupMenuURL) 
	{
		this.groupMenuURL = groupMenuURL;
	}
	
	public String getGroupMenuDesc() 
	{
		return groupMenuDesc;
	}
	
	public void setGroupMenuDesc(String groupMenuDesc) 
	{
		this.groupMenuDesc = groupMenuDesc;
	}
	
	public String getGroupMenuLabel() 
	{
		return groupMenuLabel;
	}
	
	public void setGroupMenuLabel(String groupMenuLabel) 
	{
		this.groupMenuLabel = groupMenuLabel;
	}
	
	public String getGroupMenuLevel() 
	{
		return groupMenuLevel;
	}
	
	public void setGroupMenuLevel(String groupMenuLevel) 
	{
		this.groupMenuLevel = groupMenuLevel;
	}
	
	public String getGroupParentMenuID() 
	{
		return groupParentMenuID;
	}
	
	public void setGroupParentMenuID(String groupParentMenuID) 
	{
		this.groupParentMenuID = groupParentMenuID;
	}
	
	public List<SubGroupMenu> getSubGroupMenuList() 
	{
		return subGroupMenuList;
	}
	
	public void setSubGroupMenuList(List<SubGroupMenu> subGroupMenuList) 
	{
		this.subGroupMenuList = subGroupMenuList;
	}
	
	public String getGroupMenuGroupID() 
	{
		return groupMenuGroupID;
	}

	public void setGroupMenuGroupID(String groupMenuGroupID) 
	{
		this.groupMenuGroupID = groupMenuGroupID;
	}

	public String getGroupMenuModuleID() 
	{
		return groupMenuModuleID;
	}

	public void setGroupMenuModuleID(String groupMenuModuleID) 
	{
		this.groupMenuModuleID = groupMenuModuleID;
	}

	
	
	private String groupMenuID;
	private String groupMenuURL;
	private String groupMenuDesc;
	private String groupMenuLabel;
	private String groupMenuLevel;
	private String groupMenuGroupID;
	private String groupMenuModuleID;
	private String groupParentMenuID;
	private List<SubGroupMenu> subGroupMenuList = new ArrayList<SubGroupMenu>();
}
