package app.beans;

import java.io.Serializable;



public class LoginEmployee implements Serializable
{
	private static final long serialVersionUID = 3115590084042756734L;
	
	public LoginEmployee()
	{
		
	}
	
	public String getRoleID() 
	{
		return roleID;
	}
	
	public void setRoleID(String roleID) 
	{
		this.roleID = roleID;
	}
	
	public String getMenuID() 
	{
		return menuID;
	}
	
	public void setMenuID(String menuID) 
	{
		this.menuID = menuID;
	}
	
	public String getUserID() 
	{
		return userID;
	}
	
	public void setUserID(String userID) 
	{
		this.userID = userID;
	}
	
	public String getEmployeeID() 
	{
		return employeeID;
	}
	
	public void setEmployeeID(String employeeID) 
	{
		this.employeeID = employeeID;
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
	
	public void setEmployeeRightsBean(EmployeeRights userRightsBean) 
	{
		this.employeeRightsBean = userRightsBean;
	}
	
	public String getSessionID() 
	{
		return sessionID;
	}

	public void setSessionID(String sessionID) 
	{
		this.sessionID = sessionID;
	}

	public String getTerminalID() 
	{
		return terminalID;
	}

	public void setTerminalID(String terminalID) 
	{
		this.terminalID = terminalID;
	}
	
	public String getReqIdentifier() 
	{
		return reqIdentifier;
	}

	public void setReqIdentifier(String reqIdentifier) 
	{
		this.reqIdentifier = reqIdentifier;
	}

	public long getBatchID()
	{
		return batchID;
	}

	public void setBatchID(long batchID)
	{
		this.batchID = batchID;
	}


	public long getLoaderID() 
	{
		return loaderID;
	}

	public void setLoaderID(long loaderID) 
	{
		this.loaderID = loaderID;
	}
	
	public boolean isOnBehalfOf() 
	{
		return onBehalfOf;
	}

	public void setOnBehalfOf(boolean onBehalfOf) 
	{
		this.onBehalfOf = onBehalfOf;
	}
	
	public String getFilterBy() 
	{
		return filterBy;
	}

	public void setFilterBy(String filterBy) 
	{
		this.filterBy = filterBy;
	}
	
	public String getRoleTypeID() 
	{
		return roleTypeID;
	}

	public void setRoleTypeID(String roleTypeID) 
	{
		this.roleTypeID = roleTypeID;
	}

	
	
	private long batchID;
	private long loaderID;
	private String roleID;
	private String menuID;
	private String userID;
	private String filterBy;
	
	private String sessionID;
	private String terminalID;
	private String employeeID;
	private String operationID;	
	private String reqIdentifier;
	private String roleTypeID;
 
	private boolean onBehalfOf = false;
	private EmployeeRights employeeRightsBean;
	

}
