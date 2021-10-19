package app.beans;

import java.io.Serializable;

public class RoleDetail implements Serializable
{

	private static final long serialVersionUID = -3058402985778457918L;
	
	public int getPost() 
	{
		return post;
	}
	
	public void setPost(int post) 
	{
		this.post = post;
	}
	
	public long getMenuID() 
	{
		return menuID;
	}
	
	public void setMenuID(long menuID) 
	{
		this.menuID = menuID;
	}
	
	public String getRoleID() 
	{
		return roleID;
	}
	
	public void setRoleID(String roleID) 
	{
		this.roleID = roleID;
	}
	
	public String getOperationID() 
	{
		return operationID;
	}
	
	public void setOperationID(String operationID) 
	{
		this.operationID = operationID;
	}

	public EmployeeRights getEmployeeRightsBean() 
	{
		return employeeRightsBean;
	}

	public void setEmployeeRightsBean(EmployeeRights employeeRightsBean) 
	{
		this.employeeRightsBean = employeeRightsBean;
	}
	
	public String getMenuDescription() 
	{
		return menuDescription;
	}

	public void setMenuDescription(String menuDescription) 
	{
		this.menuDescription = menuDescription;
	}
	/************** hashCode Method ***************/
	public int hashCode()
	{
		return String.valueOf(getMenuID()).concat(getRoleID().trim().concat(getOperationID().trim())).hashCode();
	}
	
	/************** equals Method ***************/
	public boolean equals (Object obj)
	{
		try
		{
			if (obj instanceof RoleDetail)
			{
				RoleDetail argObject = (RoleDetail) obj;
				if (argObject.getMenuID() == getMenuID() 
						&& argObject.getRoleID().trim().equals(getRoleID().trim())
						&& argObject.getOperationID().trim().equals(getOperationID().trim()))
					return true;
			}
			return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	private int post = 0;
	private long menuID;
	private String roleID;
	private String operationID;
	private String menuDescription;
	private EmployeeRights employeeRightsBean;
	
}
