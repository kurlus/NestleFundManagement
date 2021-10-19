
package app.beans;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;


public class Menus implements Serializable
{
    private static final long serialVersionUID = 3456789023457899L;
    
    public String getMenuID() 
    {
		return menuID;
	}

	public void setMenuID(String menuID) 
	{
		this.menuID = menuID;
	}

	public String getMenuDesc() 
	{
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) 
	{
		this.menuDesc = menuDesc;
	}

	public String getMenuLabel() 
	{
		return menuLabel;
	}

	public void setMenuLabel(String menuLabel) 
	{
		this.menuLabel = menuLabel;
	}

	public List<GroupMenus> getGroupMenusList() 
	{
		return groupMenusList;
	}

	public void setGroupMenusList(List<GroupMenus> groupmenusList) 
	{
		this.groupMenusList = groupmenusList;
	}
	
    public String getMenuGroupID() 
	{
		return menuGroupID;
	}

	public void setMenuGroupID(String menuGroupID) 
	{
		this.menuGroupID = menuGroupID;
	}

	
	private String menuID;
	private String menuDesc;
	private String menuLabel;
	private String menuGroupID;
	private List<GroupMenus> groupMenusList = new ArrayList<GroupMenus>();
    
}
