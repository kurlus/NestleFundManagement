
package app.beans;

public class SubMenus 
{

    public String getMenuID() 
    {
        return menuID;
    }

    public void setMenuID(String menuID) 
    {
        this.menuID = menuID;
    }

    public String getMenuTitle() 
    {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) 
    {
        this.menuTitle = menuTitle;
    }

    public String getMenuDescription() 
    {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) 
    {
        this.menuDescription = menuDescription;
    }

    public String getMenuUrl() 
    {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) 
    {
        this.menuUrl = menuUrl;
    }
    

    public String getParentMenuID() 
    {
		return parentMenuID;
	}

	public void setParentMenuID(String parentMenuID) 
	{
		this.parentMenuID = parentMenuID;
	}
    
    private String menuID;
    private String menuUrl;
    private String menuTitle;
    private String parentMenuID;
    private String menuDescription;
    
   
}
